package com.japps.control;

import com.japps.model.dto.UserDto;
import com.japps.model.vo.UserVo;
import com.japps.model.vo.UsersVo;
import com.japps.service.UserService;
import com.japps.utils.ResultBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 新用户注册
     *
     * @param userVo
     * @return {@link ResultBean}<{@link String}>
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultBean register(@RequestBody UserVo userVo) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userVo, userDto);
        try {
            userService.register(userDto);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return new ResultBean();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResultBean getUsers() {
        List<UserDto> users = userService.getUsers();
        List<UserVo> fileUpdate = new ArrayList<>();
        for (UserDto user : users) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            fileUpdate.add(userVo);
        }
        return new ResultBean(new UsersVo(fileUpdate));
    }
}
