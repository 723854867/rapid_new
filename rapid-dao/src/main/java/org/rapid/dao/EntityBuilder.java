package org.rapid.dao;

import java.io.Serializable;

/**
 * Entity builder
 * 
 * @author lynn
 *
 * @param <KEY>
 * @param <ENTITY>
 */
public interface EntityBuilder<KEY, ENTITY extends Entity<KEY, ?>> extends Serializable {
	
	ENTITY build();
}
