package pers.hzh.gss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ResponseBody
    private String list1(Model model) {//返回字符串
        return "test pass";
    }
}
