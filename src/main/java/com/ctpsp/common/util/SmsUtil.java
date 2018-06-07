package com.ctpsp.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

/**
 * Created by yxm on 2016/10/1.
 * 短信发送工具类
 */
public class SmsUtil {

    private static final String USER_NAME = "xjwjoa";

    private static final String PASSWORD = "xjsdfb4f1fbn19ba89070881effc4oswddoa";

    private static final String METHOD = "xjwjMT";

    public static String sendSms(String phone,String randCode) throws Exception{
        String content = "验证码为" + randCode + "感谢您使用官兵应用服务平台办，本短信不收取任何费用.【应用平台】";
        String[] sms_keys = {"username", "password", "method", "mobile","content"};
        String[] sms_values = {USER_NAME, PASSWORD, METHOD, phone, content};
        String statusURL = getResponseFromUrl(PropertyUtil.getValues("sendSMSUrl"), sms_keys, sms_values);
        System.out.println("发送验证码返回结果------->"+statusURL);
        return statusURL;
    }

    public static String getRandCode(){
        // 获取四位数的验证码
        String str = "0,1,2,3,4,5,6,7,8,9";
        String[] str1 = str.split(",");
        Random rand = new Random();
        String randCode = "";
        for (int i = 0; i < 4; i++) {
            int num = rand.nextInt(str1.length - 1);
            randCode += str1[num];
        }
        return randCode;
    }

    /** 根据外部url，参数和值获取返回值 */
    private static String getResponseFromUrl(String baseUrl, String[] keys,
                                            String[] values) throws Exception {
        String url = baseUrl;
        for (int i = 0; i < keys.length; i++) {
            url += (i == 0 && baseUrl.indexOf("?") < 0 ? "?" : "&");
            url += keys[i] + "=" + URLEncoder.encode(values[i], "UTF-8");
        }
        System.out.println("url:"+url);
        URL uRL = new URL(url);
        InputStream is = uRL.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,
                "UTF-8"));
        String line = "";
        StringBuffer result = new StringBuffer();
        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        br.close();
        is.close();
        return result.toString();
    }

    public static void main(String[] args) throws Exception{
        SmsUtil.sendSms("13305317072",SmsUtil.getRandCode());
    }
}
