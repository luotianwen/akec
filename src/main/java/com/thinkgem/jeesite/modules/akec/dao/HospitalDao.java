/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.Hospital;

/**
 * 医院管理DAO接口
 * @author 医院管理
 * @version 2020-04-04
 */
@MyBatisDao
public interface HospitalDao extends CrudDao<Hospital> {
	
}