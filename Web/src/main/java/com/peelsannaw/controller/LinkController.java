package com.peelsannaw.controller;

import com.peelsannaw.common.Res;
import com.peelsannaw.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private ILinkService linkService;

    @GetMapping("/getAllLink")
    public Res<?> getLinks() {
        return linkService.getAllLink();
    }
}
