package com.ctpsp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author devel
 */
@WebService
public interface SendService {
    /**
     * @param param
     * @return
     */
    public String sendOA(@WebParam(name="param")String param);
}
