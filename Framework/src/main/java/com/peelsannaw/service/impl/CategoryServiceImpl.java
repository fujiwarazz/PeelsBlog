package com.peelsannaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Category;
import com.peelsannaw.mapper.ArticleMapper;
import com.peelsannaw.mapper.CategoryMapper;
import com.peelsannaw.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peelsannaw.utils.BeanCopyUtils;
import com.peelsannaw.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.peelsannaw.common.SystemConstants.CATEGORY_STATUS_NORMAL;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {


    private final ArticleMapper articleMapper;
    public CategoryServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

//    <bean id="ArticleMapper" class = "ArticleMapper" >
//
//
//    <bean id="CategoryService" class = "CategoryServiceImpl">\
//        <constructure ref = 'ArticleMapper'/>
//
    @Override
    public Res<?> getCateList() {
        LambdaQueryWrapper<Category>queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus,CATEGORY_STATUS_NORMAL);
        queryWrapper.in(Category::getId,articleMapper.getUsefulIds());
        List<Category> list = this.list(queryWrapper);
        List<CategoryVo> voList = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return Res.okResult(voList);
    }
}
