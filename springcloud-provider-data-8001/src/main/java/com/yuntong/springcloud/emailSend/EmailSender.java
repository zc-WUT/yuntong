package com.yuntong.springcloud.emailSend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSender {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Scheduled(cron = "0 41 7 * * 1 ")
    public void RoportOfOneWeek() throws MessagingException {

        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("本周的钢丝绳检测报告");
        mimeMessageHelper.setText("<a href=\"123.56.96.224:8001/user/login\">点击进入系统查看详情</a>", true);
        mimeMessageHelper.addAttachment("历史数据.xls", new File("D:\\ExcelReport\\历史数据.xls"));

        mimeMessageHelper.setTo("1049379728@qq.com");
        mimeMessageHelper.setFrom("1049379728@qq.com");

        mailSender.send(mimeMessage);

    }

//    @Scheduled(cron = "0 41 7 * * 1 ")
//    public void aa(ControlRequest request) throws MessagingException {
//        UtgardTutorialHelper utgardTutorialHelper = new UtgardTutorialHelper();
//        utgardTutorialHelper.setHost(request.getHost()).setDomain(request.getDomain())
//                .setUser(request.getUser()).setPassword(request.getPassword())
//                .setClsid(request.getClsid()).addItemId(request.getItemId());
//        if (utgardTutorialHelper.access.bind() && utgardTutorialHelper.getDeviceState == 1) {
//            if (utgardTutorialHelper.write(request.getValue)) {
//                logger.info(request.getItemId() + " control success");
//            } else {
//                logger.info(request.getItemId() + " control fail");
//            }
//        } else {
//            logger.info(request.getItemId() + "控制失败，请检查设备连接并查看设备是否开启");
//        }
//    }
}
