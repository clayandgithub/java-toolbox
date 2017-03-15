package com.clayoverwind.toolbox.controller;

/**
 * Created by wangweiwei on 2017/3/2.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PageController {

    @RequestMapping("/")
    public ModelAndView getIndexPage() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("repositoryURL", "https://github.com/clayandgithub/java-toolbox");
        return mv;
    }
}