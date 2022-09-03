package com.peelsannaw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDetailVo {
    private String token;
    private UserInfoVo userInfo;
}
