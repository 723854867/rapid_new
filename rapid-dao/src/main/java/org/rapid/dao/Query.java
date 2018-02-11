package org.rapid.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Query implements Serializable {
	
	private static final long serialVersionUID = 5743929462273191657L;
	
	private int page;
	private int pageSize;
	private Set<String> selects = new HashSet<String>();
	private LinkedList<OrderBy> orderBies;
	private String[] groupBies;
	private Condition condition;
	private boolean forUpdate;
	
	public Query all() {
		selects.add("*");
		return this;
	}
	
	public Query forUpdate() {
		this.forUpdate = true;
		return this;
	}
	
	public Query orderBy(String col) {
		if (null == orderBies)
			this.orderBies = new LinkedList<OrderBy>();
		this.orderBies.addLast(new OrderBy(col, true));
		return this;
	}
	
	public Query orderByDesc(String col) {
		if (null == orderBies)
			this.orderBies = new LinkedList<OrderBy>();
		this.orderBies.addLast(new OrderBy(col, false));
		return this;
	}
	
	public Query groupBy(String... cols) {
		this.groupBies = cols;
		return this;
	}
	
	public Query condition(Condition condition) {
		this.condition = condition;
		return this;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public String[] getGroupBies() {
		return groupBies;
	}
	
	public Set<String> getSelects() {
		return selects;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public LinkedList<OrderBy> getOrderBies() {
		return orderBies;
	}
	
	public boolean isForUpdate() {
		return forUpdate;
	}
	
	public static class OrderBy implements Serializable {
		private static final long serialVersionUID = -8093202561051749679L;
		private String col;
		private String order;
		public OrderBy() {}
		public OrderBy(String col, boolean asc) {
			this.col = col;
			this.order = asc ? "ASC" : "DESC";
		}
		public String getCol() {
			return col;
		}
		public String getOrder() {
			return order;
		}
	}
}
