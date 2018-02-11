package org.rapid.dao.mybatis;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.util.common.model.Unique;

public class MemTrigger implements Unique<Integer> {

	private static final long serialVersionUID = 370806392747777968L;

	@Id
	@GeneratedValue
	private int id;
	private int uid;
	private int triggerId;
	private int count;
	private String key;
	private int created;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(int triggerId) {
		this.triggerId = triggerId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public Integer id() {
		return this.id;
	}
}
