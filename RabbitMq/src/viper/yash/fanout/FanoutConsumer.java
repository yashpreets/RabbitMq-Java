package viper.yash.fanout;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import viper.yash.db.CreateOperation;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutConsumer {
	
	public static final String EXCHANGE_NAME = "yashFanoutExchange";
	public static final String ROUTING_KEY = "";
	private final static int CONSUMER_ID = 3;
	
	public static void main(String[] args) throws IOException, TimeoutException {
		 ConnectionFactory factory = new ConnectionFactory();
		 factory.setHost("localhost");
		 Connection connection = factory.newConnection();
		 Channel channel = (Channel) connection.createChannel();
		 channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		 String queueName = channel.queueDeclare().getQueue();
		 channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
		 System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		 CreateOperation obj = new CreateOperation();
		 DefaultConsumer consumer = new DefaultConsumer(channel) {
		      public void handleDelivery(String consumerTag, Envelope envelope,
		                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
		        String message = new String(body, "UTF-8");
		        obj.insertInDb(ROUTING_KEY,CONSUMER_ID,message);
		        System.out.println(" [x] Received '" + message + "'");
		      }
		    };
		 channel.basicConsume(queueName, true, consumer);

	}

}
