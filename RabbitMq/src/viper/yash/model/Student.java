package viper.yash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "studentMq")
public class Student {
	@Id
	@Column(name = "id")
	@GeneratedValue
	int id;
	@Column(name = "queueName")
	String created_by_queue;
	@Column(name = "message")
	String message;
	@Column(name = "consumerId")
	int consumerId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreated_by_queue() {
		return created_by_queue;
	}

	public void setCreated_by_queue(String created_by_queue) {
		this.created_by_queue = created_by_queue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}
}
