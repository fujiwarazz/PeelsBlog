package com.peelsannaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Link;
import com.peelsannaw.mapper.LinkMapper;
import com.peelsannaw.service.ILinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peelsannaw.utils.BeanCopyUtils;
import com.peelsannaw.vo.LinkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.peelsannaw.common.SystemConstants.GENERAL_NORMAL;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

    @Override
    public Res<?> getAllLink() {
        LambdaQueryWrapper<Link>queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus,GENERAL_NORMAL);
        List<Link> linkList = this.list(queryWrapper);

        List<LinkVo> vos = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        return Res.okResult(vos);
    }
}
