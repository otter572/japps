package com.japps.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    private String username;

    private String telephone;

    private String recentLogUpdateTime;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String occupation;

    private String device;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String startFrom;

    private String fromLastUpdate;

    private String infoFileUpdateTime;

}
