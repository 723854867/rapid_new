package org.rapid.dao.mybatis;

import org.apache.ibatis.annotations.Select;

public interface BasUserDao extends DBDao<Integer, BasUser> {

	@Select("SELECT * from bas_user WHERE id=#{id}")
	BasUser getByIdsss(int id);
}
