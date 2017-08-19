package viper.yash.mq;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class Producer {
	 private final static String QUEUE_NAME = "yash123";

	  public static void main(String[] argv)throws java.io.IOException, TimeoutException {
		  ConnectionFactory factory = new ConnectionFactory();
		  factory.setHost("localhost");
		  Connection connection = factory.newConnection();
		  //Connection connection = CreateConnectionObject.getconnection(); 
		  Channel channel = connection.createChannel();
		  channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		  String message = "Hello Viper ";
		  channel.basicPublish("", QUEUE_NAME, null,(message+"1").getBytes());
		  channel.basicPublish("", QUEUE_NAME, null,(message+"2").getBytes());
		  channel.basicPublish("", QUEUE_NAME, null,(message+"3").getBytes());
		  channel.basicPublish("", QUEUE_NAME, null,(message+"4").getBytes());
		  System.out.println(" [x] Sent '" + message + "'");
		  channel.close();
		  connection.close();
	  }
}
