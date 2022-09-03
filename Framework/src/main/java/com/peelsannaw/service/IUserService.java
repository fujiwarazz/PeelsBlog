package com.peelsannaw.service;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peelsannaw.vo.UserInfoVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
public interface IUserService extends IService<User> {

    Res<?> getUserInfo();

    Res<?> updateUser(UserInfoVo vo);

    Res<?> getInfo();

    Res<?> getRouters();
}
