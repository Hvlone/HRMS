package com.example.demo.controller;

import com.example.demo.po.House;
import com.example.demo.service.HouseService;
import com.example.demo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("https://files.metaso.cn/api/houses")
public class HouseController {
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * 创建房源
     */
    @PostMapping
    public ApiResponse createHouse(@RequestBody House house) {
        try {
            boolean success = houseService.createHouse(house);
            return success ? ApiResponse.success("房源创建成功", house) :
                           ApiResponse.error("房源创建失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 发布房源
     */
    @PutMapping("/{houseId}/publish")
    public ApiResponse publishHouse(@PathVariable Integer houseId) {
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
    @GetMapping("/{houseId}")
    public ApiResponse getHouseDetail(@PathVariable Integer houseId) {
        House house = houseService.getHouseDetail(houseId);
        if (house != null) {
            // 转换价格为元
            house.setPriceYuan(house.getPrice() / 100.0);
            return ApiResponse.success(house);
        }
        return ApiResponse.error("房源不存在");
    }

    /**
     * 搜索房源
     */
    @GetMapping("/search")
    public ApiResponse searchHouses(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
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
        houses.forEach(house -> house.setPriceYuan(house.getPrice() / 100.0));
        
        return ApiResponse.success(houses);
    }
}