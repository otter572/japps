package com.japps.service.impl;

import com.japps.constants.FileConstant;
import com.japps.repository.UserRepository;
import com.japps.service.FileUploadService;
import com.japps.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private UserRepository userRepository;

    @Override
    public int saveFile(String username, String path, MultipartFile file) throws Exception {
        if (userRepository.findUserByUsername(username) == null) {
            log.info("用户 {} 不存在，无法上传文件", username);
            throw new Exception("文件上传失败，" + "用户 " + username + " 不存在");
        }

        File newFile = new File(FileConstant.FILE_SAVE_PATH + "/" + username + path);
        newFile.mkdirs();
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
        } catch (IOException e) {
            log.warn("文件 {} 上传失败", path);
            throw new Exception("文件上传失败，文件数据异常");
        }

        String currentTime = updateTime(username, path);
        int paThreshold = getPaThreshold(username);
        log.info("用户 {} 于 {} 成功上传文件 {}，阈值为 {}", username, currentTime, path, paThreshold);

        return paThreshold;
    }

    public String updateTime(String username, String path) {
        String currentTime = TimeUtil.getCurrentTimeStr(TimeUtil.FORMAT_MINUTE);
        // 更新INFO更新文件时间
        if (path.contains("INFO")) {
            long res = userRepository.updateInfoFileUpdateTime(username, currentTime);
            log.info("INFO文件：{}", currentTime);
            if (res == 0) {
                log.warn("用户 {} 更新INFO文件更新时间失败", username);
            }
        }
        // 更新用户最近更新时间
        long res = userRepository.updateRecentLogUpdateTime(username, currentTime);
        if (res == 0) {
            log.warn("用户 {} 更新最近日志更新时间失败", username);
        }
        return currentTime;
    }

    public int getPaThreshold(String username) {
        return userRepository.findUserByUsername(username).getPa_threshold();
    }
}
