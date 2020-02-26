/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.Studycenter;

/**
 * 知识分类DAO接口
 * @author 知识分类
 * @version 2020-02-27
 */
@MyBatisDao
public interface StudycenterDao extends CrudDao<Studycenter> {
	
}