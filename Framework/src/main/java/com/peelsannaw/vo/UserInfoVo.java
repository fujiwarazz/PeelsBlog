package com.peelsannaw.vo;


import com.peelsannaw.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo{
    private String avatar;
    private String email;
    private Long id;
    private String nickName;
    private String sex;
}
