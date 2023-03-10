package com.japps.model.entity;

import com.japps.model.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private String _id;

    private String username;

    private String recent_log_update_time;

    private String telephone;

    private String occupation;

    private String device;

    private String start_from;

    private int app_status;

    private int pa_threshold;

    private String info_file_update_time;

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.telephone = userDto.getTelephone();
        this.occupation = userDto.getOccupation();
        this.device = userDto.getDevice();
        this.start_from = userDto.getStartFrom();
    }
}
