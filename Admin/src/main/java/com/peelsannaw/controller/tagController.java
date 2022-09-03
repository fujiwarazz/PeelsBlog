package com.peelsannaw.controller;

import com.peelsannaw.common.Res;
import com.peelsannaw.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/tag")
public class tagController {


    @Autowired
    ITagService tagService;

    @GetMapping("/list")
    public Res<?> list(){
        return Res.okResult(tagService.list());
    }
}
