package com.ai.activemq.controller;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.activemq.manager.ActiveMQManager;
import com.ai.activemq.util.StringUtils;

public class ActiveMQStressThread extends Thread {
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	private ActiveMQManager activeMQManager;
	private int messageNumber;
	CountDownLatch latch;
	
	public ActiveMQStressThread(int messageNumber, String taskName, ActiveMQManager activeMQManager, CountDownLatch latch) {
		this.setName(taskName);
		this.messageNumber = messageNumber;
		this.activeMQManager = activeMQManager;
		this.latch = latch;
	}
	
	public void run() {
		
		logger.debug("线程" + this.getName() + "启动");
		
		Long start = new Date().getTime();
		String message = StringUtils.genRandamString(2000);
		
		int sendFail = 0;
		for (int i=1; i<=messageNumber; i++) {
			if (!activeMQManager.sendMessageWebApp(this.getName() + " " + i + ":" + message)) {
				sendFail ++;
			}
		}
		Long end = new Date().getTime();
		Long cost = end - start;
		logger.error("线程" + this.getName() + "发送失败数目: {}", sendFail);
		logger.debug("线程" + this.getName() + "执行完毕" + ",耗时:" + cost +"毫秒");
		latch.countDown();
	}
}
