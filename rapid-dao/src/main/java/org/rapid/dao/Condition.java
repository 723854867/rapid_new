package org.rapid.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.rapid.util.collection.CollectionUtil;

/**
 * 所有条件都是 and
 * 
 * @author lynn
 */
public class Condition implements Serializable {

	private static final long serialVersionUID = 2612308680240154423L;

	private LinkedList<Entry> equals = new LinkedList<Entry>();
	private LinkedList<Range> ranges = new LinkedList<Range>();
	private LinkedList<Entry> notEquals = new LinkedList<Entry>();
	private Map<String, Set<Object>> ins = new HashMap<String, Set<Object>>();
	private Map<String, Set<Object>> notIns = new HashMap<String, Set<Object>>();

	/**
	 * 等于
	 * 
	 * @param col
	 * @param value
	 * @return
	 */
	public Condition equal(String col, Object value) {
		this.equals.addLast(new Entry(col, value));
		return this;
	}

	public Condition notEqual(String col, Object value) {
		this.notEquals.addLast(new Entry(col, value));
		return this;
	}

	/**
	 * 大于等于且小于等于
	 * 
	 * @param col
	 * @param min
	 * @param max
	 * @return
	 */
	public Condition between(String col, Object min, Object max) {
		this.ranges.addLast(new Range(col, min, max, true, true));
		return this;
	}

	/**
	 * 大于
	 * 
	 * @param col
	 * @param value
	 * @return
	 */
	public Condition gt(String col, Object value) {
		this.ranges.addLast(new Range(col, value, null, false, true));
		return this;
	}

	/**
	 * 大于等于
	 * 
	 * @param col
	 * @param value
	 * @return
	 */
	public Condition gte(String col, Object value) {
		this.ranges.addLast(new Range(col, value, null, true, true));
		return this;
	}

	/**
	 * 小于
	 * 
	 * @param col
	 * @param value
	 * @return
	 */
	public Condition lt(String col, Object value) {
		this.ranges.addLast(new Range(col, null, value, true, false));
		return this;
	}

	/**
	 * 小于等于
	 * 
	 * @param col
	 * @param value
	 * @return
	 */
	public Condition lte(String col, Object value) {
		this.ranges.addLast(new Range(col, null, value, true, true));
		return this;
	}

	public Condition in(String col, Object... values) {
		this.ins.put(col, CollectionUtil.toSet(values));
		return this;
	}

	public Condition notIn(String col, Object... values) {
		this.notIns.put(col, CollectionUtil.toSet(values));
		return this;
	}

	public LinkedList<Entry> getEquals() {
		return equals;
	}

	public LinkedList<Range> getRanges() {
		return ranges;
	}

	public LinkedList<Entry> getNotEquals() {
		return notEquals;
	}

	public Map<String, Set<Object>> getIns() {
		return ins;
	}

	public Map<String, Set<Object>> getNotIns() {
		return notIns;
	}

	public static class Entry implements Serializable {
		private static final long serialVersionUID = -5922886819191768602L;
		private String col;
		private Object value;

		public Entry() {
		}

		public Entry(String col, Object value) {
			this.col = col;
			this.value = value;
		}

		public String getCol() {
			return col;
		}

		public Object getValue() {
			return value;
		}
	}

	public static class Range implements Serializable {
		private static final long serialVersionUID = -4520393376519100083L;
		private boolean lInclude;
		private boolean rInclude;
		private Object min;
		private Object max;
		private String col;

		public Range() {
		}

		public Range(String col, Object min, Object max, boolean lInclude, boolean rInclude) {
			this.col = col;
			this.min = min;
			this.max = max;
			this.lInclude = true;
			this.rInclude = true;
		}

		public String getCol() {
			return col;
		}

		public boolean islInclude() {
			return lInclude;
		}

		public boolean isrInclude() {
			return rInclude;
		}

		public Object getMin() {
			return min;
		}

		public void setMin(Object min) {
			this.min = min;
		}

		public Object getMax() {
			return max;
		}
	}
}
