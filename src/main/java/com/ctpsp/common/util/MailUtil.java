package com.ctpsp.common.util;

import com.ctpsp.common.mail.MailSenderInfo;
import com.ctpsp.common.mail.SimpleMailSender;
import org.apache.commons.lang3.StringUtils;

/**
 * 邮件发送工具类
 * Created by cyj on 2017/1/5.
 */
public class MailUtil {
    public  static void sendMessageEmail(String toAddress, String subject, String content)throws Exception{
        if (StringUtils.isNotBlank(toAddress)&&StringUtils.isNotBlank(subject)&&StringUtils.isNotBlank(content)){
            MailSenderInfo mailInfo = new MailSenderInfo();
            mailInfo.setMailServerHost(PropertyUtil.getValues("serverHost"));
            mailInfo.setMailServerPort(PropertyUtil.getValues("serverPort"));
            mailInfo.setValidate(true);
            mailInfo.setUserName(PropertyUtil.getValues("serverName"));// 设置发送邮箱的用户名
            mailInfo.setPassword(PropertyUtil.getValues("serverPassword"));// 您的邮箱密码
            mailInfo.setFromAddress(PropertyUtil.getValues("serverAddress")); // 发送的邮箱地址
            mailInfo.setToAddress(toAddress); // 接收的邮箱地址
            mailInfo.setSubject(subject); // 邮件标题
            mailInfo.setContent(content); // 邮件内容
            // 这个类主要来发送邮件
            SimpleMailSender sms = new SimpleMailSender();
            boolean flag= SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
            System.out.println("----------"+flag);
        }
    }
}
