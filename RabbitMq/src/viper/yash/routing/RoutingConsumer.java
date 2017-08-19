package viper.yash.routing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import viper.yash.db.CreateOperation;

public class RoutingConsumer {
	private static final String EXCHANGE_NAME = "yashDirectExchange";
	private final static int CONSUMER_ID = 4;
	private static ArrayList<String> routingKeylist = new ArrayList<>(Arrays.asList("black", "red", "white"));

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		String queueName = channel.queueDeclare().getQueue();

		for (String routingKey : routingKeylist) {
			channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
		}
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		CreateOperation obj = new CreateOperation();
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				obj.insertInDb(envelope.getRoutingKey(), CONSUMER_ID, message);
				System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
