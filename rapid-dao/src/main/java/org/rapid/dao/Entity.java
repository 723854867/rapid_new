package org.rapid.dao;

import java.io.Serializable;

/**
 * Dao操作的对象
 * 
 * @author lynn
 *
 * @param <KEY>
 * @param <BUILDER>
 */
public abstract class Entity<KEY, BUILDER extends EntityBuilder<?, ?>> implements Serializable {

	private static final long serialVersionUID = -2567934411893130015L;

	public Entity() {}
	
	public Entity(BUILDER builder) {}
	
	public abstract KEY key();
}
