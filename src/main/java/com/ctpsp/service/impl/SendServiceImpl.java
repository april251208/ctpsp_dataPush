package com.ctpsp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctpsp.service.BulletinDataService;
import com.ctpsp.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

/**
 * @author devel
 */
@WebService(endpointInterface="com.ctpsp.service.SendService",serviceName="sendService")
public class SendServiceImpl implements SendService {


    @Autowired
    private BulletinDataService bulletinDataService;


    @Override
    public String sendOA(String param) {
        String jsonData= bulletinDataService.getBulletinData(param);
        return jsonData;
    }
}
