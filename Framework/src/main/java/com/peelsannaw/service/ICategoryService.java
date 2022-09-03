package com.peelsannaw.service;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
public interface ICategoryService extends IService<Category> {

    Res<?> getCateList();
}
