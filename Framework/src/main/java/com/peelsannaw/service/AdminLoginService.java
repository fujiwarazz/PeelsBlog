package com.peelsannaw.service;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.User;

public interface AdminLoginService {

    Res<?> login(User user);
}
