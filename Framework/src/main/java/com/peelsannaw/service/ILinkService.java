package com.peelsannaw.service;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
public interface ILinkService extends IService<Link> {

    Res<?> getAllLink();
}
