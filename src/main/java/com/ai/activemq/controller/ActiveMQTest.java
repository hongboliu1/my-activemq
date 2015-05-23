package com.ai.activemq.controller;

import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.activemq.manager.ActiveMQManager;

@Controller
public class ActiveMQTest {

	@Resource
	private ActiveMQManager activeMQManager;
	
	@RequestMapping(value = "activemq/send", method = RequestMethod.POST, produces="plain/text;charset=UTF-8")
	@ResponseBody
    public String send(){
		
		String message = "{\"msgtype\":\"work\",\"pushtype\":\"opush\",\"isoffline\":\"true\","
				+ "\"termofvalid\":\"100\",\"msgbody\":\"消息内容\",\"frisp\":\"123456\","
				+ "\"toroles\":[{\"torole\":\"member\"},{\"torole\":\"admin\"}],"
				+ "\"totype\":\"user\",\"todatas\":[{\"todata\":\"100001\"},{\"todata\":\"100002\"},{\"todata\":\"100003\"}]}";
		
//		String message = "测试";
		
		activeMQManager.sendMessageWebApp(message);
		return "发送成功";
	}
	
	@RequestMapping(value = "activemq/receive", method = RequestMethod.POST, produces="plain/text;charset=UTF-8")
	@ResponseBody
    public String receive(){
		String message = activeMQManager.receiveMessageWebApp();
		return message;
	}
	
	@RequestMapping(value = "activemq/stress", method = RequestMethod.POST, produces="plain/text;charset=UTF-8")
	@ResponseBody
    public String stress(){
		
		int threadNumber = 100;
		int messageNumber = 10000;
		
		final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);  
		
		for (int i = 0; i<= threadNumber; i++) {
			ActiveMQStressThread thread = new ActiveMQStressThread(messageNumber, "ActiveMQStress"+i, activeMQManager, countDownLatch);
			thread.start();
		}
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "测试完成";
	}
	
}
