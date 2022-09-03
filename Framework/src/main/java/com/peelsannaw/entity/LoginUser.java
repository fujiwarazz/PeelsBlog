package com.peelsannaw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 为了配合使用spring security
 * 由于这一步设计的动作为查表 这是一个同一的操作 所以放到framework，而config要放到web 因为登录需要使用到的权限不同 对前台登录的要求和对后台登录使用的要求不同
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginUser implements UserDetails {

    private User user;
    /**
     * 返回权限集合
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
//{
//        "@type": "com.peelsannaw.entity.LoginUser",
//        "accountNonExpired": true,
//        "accountNonLocked": true,
//        "credentialsNonExpired": true,
//        "enabled": true,
//        "password": "$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy",
//        "user": {
//        "avatar": "http://rfkb83lu8.bkt.clouddn.com/2022-07-2062593d574c8424b1ebae7ed25e4c9516a.jpg",
//        "createTime": "2022-01-05 09:01:56",
//        "delFlag": 0,
//        "email": "23412332@qq.com",
//        "id": "1",
//        "nickName": "peelsannaw",
//        "password": "$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy",
//        "phonenumber": "18888888888",
//        "sex": "0",
//        "status": "0",
//        "type": "1",
//        "updateBy": "1",
//        "updateTime": "2022-01-30 15:37:03",
//        "userName": "sg"
//        },
//        "username": "sg"
//        }