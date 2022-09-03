package com.peelsannaw.service.impl;

import com.peelsannaw.entity.Tag;
import com.peelsannaw.mapper.TagMapper;
import com.peelsannaw.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
