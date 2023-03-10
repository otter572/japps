package com.japps.model.dto;

import com.japps.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String username;

    private String recentLogUpdateTime;

    private String telephone;

    private String occupation;

    private String device;

    private String startFrom;

    private String fromLastUpdate;

    private String infoFileUpdateTime;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.recentLogUpdateTime = user.getRecent_log_update_time();
        this.telephone = user.getTelephone();
        this.occupation = user.getOccupation();
        this.device = user.getDevice();
        this.startFrom = user.getStart_from();
        this.infoFileUpdateTime = user.getInfo_file_update_time();
    }

}
