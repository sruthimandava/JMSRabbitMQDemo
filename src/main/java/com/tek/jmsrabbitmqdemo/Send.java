package com.tek.jmsrabbitmqdemo;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) {

		try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost("localhost");
			
			Connection connection = connectionFactory.newConnection();
			
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);

			String message = "Hello Rabbitmq";

			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "'");

			channel.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

}
