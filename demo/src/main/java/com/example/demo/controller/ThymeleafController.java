package com.example.demo.controller;

import com.example.demo.po.House;
import com.example.demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping("/test")
public class ThymeleafController {

    @Autowired
    HouseService houseService;

    @GetMapping("/index")
    public String index(Model model){

        model.addAttribute("data","传递的数据");
        return "includes/test";
    }

    @RequestMapping ("/houseDetail")
    public String getHouseDetail(@RequestParam Integer houseId, Model model) {
        House house = houseService.getHouseDetail(houseId);
        model.addAttribute("house", house);
        System.out.println(house);
        model.addAttribute("pics", Arrays.asList(house.getPictures().split(",")));
        return "includes/detail";   // 去拼 classpath:/templates/house/detail.html
    }
}
