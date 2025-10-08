package com.example.demo.controller;

import com.example.demo.po.House;
import com.example.demo.service.HouseService;
import com.example.demo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {
    @Autowired
    HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * 创建房源
     */
    @RequestMapping("/to-createHouse")
    public ApiResponse createHouse(@RequestBody House house) {
        try {
            boolean success = houseService.createHouse(house);
            return success ? ApiResponse.success("房源创建成功,待审核") :
                           ApiResponse.error("房源创建失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 发布房源
     */
    @RequestMapping("/publishHouse")
    public ApiResponse publishHouse(Integer houseId) {
        try {
            boolean success = houseService.updateHouseStatus(
                houseId, House.Status.PUBLISHED);
            return success ? ApiResponse.success("房源发布成功") : 
                           ApiResponse.error("房源发布失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 获取房源详情
     */
    @RequestMapping ("/houseDetail")
    public String getHouseDetail(Integer houseId,Model model) {
        House house = houseService.getHouseDetail(houseId);
        model.addAttribute("house", house);
        model.addAttribute("pics", Arrays.asList(house.getPictures().split(",")));
        return "includes/detail";   // 去拼 classpath:/templates/house/detail.html
    }

    /**
     * 搜索房源
     */
    @RequestMapping("/selectHouse")
    public ApiResponse searchHouses(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        // 转换枚举状态
        House.Status statusEnum = null;
        if (status != null) {
            try {
                statusEnum = House.Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ApiResponse.error("无效的状态值");
            }
        }
        
        List<House> houses = houseService.searchHouses(
            minPrice, maxPrice, statusEnum, keyword, page, size);
        
        // 转换价格为元
        houses.forEach(house -> house.setPrice(house.getPrice()));
        
        return ApiResponse.success(houses);
    }

    /**
     * 根据houseId删除房源
     */
    @RequestMapping("/deleteHouse")
    public ApiResponse deleteHouse(Integer houseId){
        House house=houseService.getHouseDetail(houseId);
        if (house!=null){
            int res=houseService.deleteHouseByHouseId(houseId);
            return res==1?ApiResponse.success("删除成功！"):ApiResponse.error("删除失败！");
        }
        return ApiResponse.error("房源不存在，删除失败！");
    }

    /*更新房源信息*/
    @RequestMapping("/updateHouseDetail")
    public ApiResponse updateHouse(House house){
        int res=houseService.updateHouse(house);
        return res==1?ApiResponse.success("更新成功！"):ApiResponse.error("更新失败");
    }

    @PostMapping("/addHouse")
    @ResponseBody
    public ApiResponse publish(House dto,
                             @RequestParam("pics") MultipartFile[] pics) throws IOException {
        int res= houseService.addHouse(dto, pics);
        return res==1?ApiResponse.success("添加房源成功！"):ApiResponse.error("添加房源失败");
    }


}