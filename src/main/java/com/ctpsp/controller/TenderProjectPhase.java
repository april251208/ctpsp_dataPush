package com.ctpsp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/getTenderProjectData")
/**
 * @author:
 */
public class TenderProjectPhase {
    @ResponseBody
    @RequestMapping(produces = "application/json;charset=UTF-8")
    public JSONObject getTenderProjectData(){
        String jsonData="{\"招标项目\":{\"TENDER_PROJECT_CODE\":\"M1100000015013153001\",\"TENDER_PROJECT_NAME\":\"中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程\",\"REGION_CODE\":\"文章链接\",\"TENDER_CONTENT\":\"墙体拆除、屋顶拆除、车间洁净板装修、固化剂与自流平地面装修、洁净车间温度及换风控制。\",\"SUPERVISE_DEPT_NAME\":\"中粮集团\",\"TRADE_PLAT\":\"M1100000015\"},\"项目\":{\"PROJECT_CODE\":\"M1100000015013153\",\"PROJECT_NAME\":\"中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程\"},\"标段\":[{\"BID_SECTION_CODE\":\"M1100000015013153001001\",\"BID_SECTION_NAME\":\"中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程\",\"CONTRACT_RECKON_PRICE\":\"900000.000000\"},{\"BID_SECTION_CODE\":\"M1100000015013153001001\",\"BID_SECTION_NAME\":\"中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程\",\"CONTRACT_RECKON_PRICE\":\"900000.000000\"}],\"招标代理机构\":{\"TENDER_AGENCY_CODE\":\"91110108710925518R\",\"TENDER_AGENCY_NAME\":\"国信招标集团股份有限公司\"},\"招标人\":[{\"TENDERER_CODE\":\"111111\",\"TENDERER_NAME\":\"中粮丰通（北京）食品有限公司\"},{\"TENDERER_CODE\":\"222222\",\"TENDERER_NAME\":\"中粮丰通（上海）食品有限公司\"}]}";
        JSONObject jsonObject = JSON.parseObject(jsonData);
        return  jsonObject;
    }

}
