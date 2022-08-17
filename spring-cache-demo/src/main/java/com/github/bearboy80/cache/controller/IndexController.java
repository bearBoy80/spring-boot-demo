package com.github.bearboy80.cache.controller;

import com.github.bearboy80.cache.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/index")
    public String index(@RequestParam("name") String str) {
        return indexService.getIndexNum(str);
    }
}
