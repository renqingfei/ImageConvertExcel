package com.excel.demo.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/{pageName}")
    public String toPage(@PathVariable("pageName") String pageName){
        if(StringUtils.isEmpty(pageName))
            return "index";
        return pageName;
    }
}
