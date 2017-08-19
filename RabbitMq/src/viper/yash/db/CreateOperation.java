package viper.yash.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import viper.yash.model.Student;

public class CreateOperation extends CrudBaseClass {

	public void insertInDb(String queueName,int consumerId,String message) {
		try {
			Student st = new Student();
			st.setCreated_by_queue(queueName);
			st.setConsumerId(consumerId);
			st.setMessage(message);
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(st);
			session.getTransaction().commit();
			session.close();
		} catch (Exception E) {
			E.printStackTrace();
		}
	}
	
}
