package com.japps.repository;

import com.japps.model.dto.UserDto;
import com.japps.model.entity.User;

import java.util.List;

public interface UserRepository {

    /**
     * 根据用户名查找用户，返回一条数据库实例
     *
     * @param username 用户名
     * @return {@link User}
     */
    User findUserByUsername(String username);

    /**
     * 更新用户最近日志更新时间：成功返回1,；失败返回0
     *
     * @param username 用户名
     * @param time 最近更新时间
     * @return long
     */
    long updateRecentLogUpdateTime(String username, String time);

    /**
     * 往数据库插入一个用户
     *
     * @param userDto 用户dto
     * @return {@link String}
     */
    String insertOneUser(UserDto userDto);

    /**
     * 查找所有用户
     *
     * @return {@link List}<{@link User}>
     */
    List<User> findAllUser();

    /**
     * 更新INFO文件更新时间
     *
     * @param username 用户名
     * @param time     时间
     * @return long
     */
    long updateInfoFileUpdateTime(String username, String time);
}
