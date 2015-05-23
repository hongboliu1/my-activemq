package com.ai.activemq.manager;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

@Component("activeMQManager")
public class ActiveMQManager {
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * WEB应用 JMS模版
	 */
	@Resource
	private JmsTemplate jmsTemplateWebApp;
	
	/**
	 * 群应用 JMS模版
	 */
	@Resource
	private JmsTemplate jmsTemplateGroupApp;

    /**
     * 群操作通知 JMS模版
     */
    @Resource
    private JmsTemplate jmsTemplateGroupNotify;
	
	/**
	 * WEB应用 发送消息
	 * @param message
	 * @return true: 发送成功, false: 发送失败
	 */
	public boolean sendMessageWebApp(final String message) {
		
		logger.debug("WEB应用 发送消息: {} ", message);
		try {
			jmsTemplateWebApp.send(new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	            	TextMessage textMessage = session.createTextMessage(message);
	                return textMessage;
	            }
	        });
		} catch (Exception e) {
			logger.error("ActiveMQ 异常", e);
			return false;
		}
		
		return true;
    }
	
	/**
	 * WEB应用  接受消息
	 * @return
	 */
    public String receiveMessageWebApp() {
        String message = "";
        try {
        	TextMessage textMessage = (TextMessage) jmsTemplateWebApp.receive();
        	if (textMessage == null) {
        		logger.debug("WEB应用  接受消息为null");
        		return "";
        	}
            message = textMessage.getText();
            logger.debug("WEB应用  接受消息: {}", message);
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
        return message;
    }
	
    /**
	 * 群应用 发送消息
	 * @param message
	 * @return true: 发送成功, false: 发送失败
	 */
	public boolean sendMessageGroupApp(final String message) {
		logger.debug("群应用 发送消息: {} ", message);
		try {
			jmsTemplateGroupApp.send(new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	            	
	            	TextMessage textMessage = session.createTextMessage(message);
	                return textMessage;
	            }

	        });
		} catch (Exception e) {
			logger.error("ActiveMQ 异常", e);
			return false;
		}
		return true;
    }

	/**
	 * 群应用  接受消息
	 * @return
	 */
    public String receiveMessageGroupApp() {
        String message = "";
        TextMessage textMessage = (TextMessage) jmsTemplateGroupApp.receive();
        try {
        	if (textMessage == null) {
        		logger.debug("WEB应用  接受消息为null");
        		return "";
        	}
            message = textMessage.getText();
            logger.debug("WEB应用  接受消息: {}", message);
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
        return message;
    }

    /***
     * 群操作通知 发送消息
     * @return
     */
    public boolean sendGroupNotify(final String message){
        logger.debug("群操作通知 发送消息: {} ", message);
        try {
            jmsTemplateGroupNotify.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {

                    TextMessage textMessage = session.createTextMessage(message);
                    return textMessage;
                }

            });
        } catch (Exception e) {
            logger.error("ActiveMQ 异常", e);
            return false;
        }
        return true;
    }

    /***
     * 群操作通知 发送消息
     * @return
     */
    public String receiveGroupNotify(){
        String message = "";
        TextMessage textMessage = (TextMessage) jmsTemplateGroupNotify.receive();
        try {
            if (textMessage == null) {
                logger.debug("群操作通知  接受消息为null");
                return "";
            }
            message = textMessage.getText();
            logger.debug("群操作通知  接受消息: {}", message);
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
        return message;
    }
}
