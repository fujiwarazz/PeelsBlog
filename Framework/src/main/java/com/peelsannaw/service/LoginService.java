package com.peelsannaw.service;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.User;

public interface LoginService {

    Res<?> login(User user);

    Res<?> logOut();
}
