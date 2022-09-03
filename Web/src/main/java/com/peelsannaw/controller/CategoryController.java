package com.peelsannaw.controller;


import com.peelsannaw.common.Res;
import com.peelsannaw.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private ICategoryService categoryService;


    @GetMapping("getCategoryList")
    public Res<?> getCateList(){
        return categoryService.getCateList();
    }

}
