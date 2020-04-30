/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.Grade;

/**
 * 评分类型DAO接口
 * @author 评分类型
 * @version 2020-03-14
 */
@MyBatisDao
public interface GradeDao extends CrudDao<Grade> {
	
}