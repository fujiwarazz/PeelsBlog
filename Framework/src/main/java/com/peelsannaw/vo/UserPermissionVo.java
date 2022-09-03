package com.peelsannaw.vo;

import com.peelsannaw.enums.AppHttpCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 27365
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionVo {
    public UserInfoVo user;
    public List<String> roles;
    public List<String>permissions;

}
