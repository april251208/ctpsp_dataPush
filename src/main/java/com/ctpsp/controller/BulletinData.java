package com.ctpsp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctpsp.service.BulletinDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

/**
 * @author:
 */
public class BulletinData {

    @Autowired
    private BulletinDataService bulletinDataService;
    @ResponseBody
    @RequestMapping(value = "/getBulletinData/{noticeType}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public JSONObject getBulletinData(@PathVariable() String noticeType){
        String jsonData= bulletinDataService.getBulletinData(noticeType);
        JSONObject jsonObject = JSON.parseObject(jsonData);
        return  jsonObject;
    }

}
