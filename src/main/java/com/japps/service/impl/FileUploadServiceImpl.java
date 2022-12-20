package com.japps.service.impl;

import com.japps.constants.FileConstant;
import com.japps.repository.UserRepository;
import com.japps.service.FileUploadService;
import com.japps.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private UserRepository userRepository;

    @Override
    public String saveFile(String username, String path, MultipartFile file) {
        if (userRepository.findUserByUsername(username) == null) {
            log.info("用户 {} 不存在，无法上传文件", username);
            return "文件上传失败，" + "用户 " + username + " 不存在";
        }

        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
        String fileName = commonsMultipartFile.getFileItem().getName();
        File newFile = new File(FileConstant.FILE_SAVE_PATH + "/" + username + path + "/" + fileName);
        newFile.mkdirs();
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
        } catch (IOException e) {
            log.warn("文件 {} 上传失败", fileName);
            return "文件上传失败，文件数据异常";
        }

        String currentTime = String.valueOf(TimeUtil.getCurrentTime());
        // 更新用户最近更新时间
        long res = userRepository.updateRecentLogUpdateTime(username, currentTime);
        if (res == 0) {
            log.warn("用户 {} 更新最近日志更新时间失败", username);
        }

        log.info("用户 {} 于 {} 成功上传文件 {}", username, currentTime, fileName);
        return null;
    }
}