package com.ctpsp;

import com.ctpsp.service.SendService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class test {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(SendService.class);
        factoryBean.setAddress("http://127.0.0.1:8050/webservice/sendServie?wsdl");

        SendService readerService = (SendService)factoryBean.create();

        String tenderProjectPhase = readerService.sendOA("tenderProjectPhase");
        System.out.printf(tenderProjectPhase);

    }
}
