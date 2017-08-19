package viper.yash.fanout;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class FanoutProducer {
	public static final String EXCHANGE_NAME = "yashFanoutExchange";
	public static final String ROUTING_KEY = "";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = (Channel) connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String message = "fannout exchange done";

        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
	}
}
