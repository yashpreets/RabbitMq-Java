package viper.yash.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class CreateConnectionObject {
	public static Connection getconnection() throws IOException, TimeoutException{
		  ConnectionFactory factory = new ConnectionFactory();
		  factory.setHost("localhost");
		  Connection connection = factory.newConnection();
		  return connection;
	}
}
