package viper.yash.mq;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import viper.yash.db.CreateOperation;

public class Consumer {
	private final static String QUEUE_NAME = "yash123";
	private final static int CONSUMER_ID = 1;

	  public static void main(String[] argv)throws java.io.IOException,java.lang.InterruptedException, TimeoutException {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    CreateOperation obj = new CreateOperation();
	    DefaultConsumer consumer = new DefaultConsumer(channel) {
	    	@Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)throws IOException {
	          String message = new String(body, "UTF-8");
	          obj.insertInDb(QUEUE_NAME,CONSUMER_ID,message);
	          System.out.println(" [x] Received '" + message + "'");
	        }
	    };
	    channel.basicConsume(QUEUE_NAME, true, consumer);
	 }
}
