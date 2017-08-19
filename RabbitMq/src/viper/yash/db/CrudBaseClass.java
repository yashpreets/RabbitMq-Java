package viper.yash.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrudBaseClass {
	 public final static SessionFactory sf = new Configuration().configure().buildSessionFactory();

}
