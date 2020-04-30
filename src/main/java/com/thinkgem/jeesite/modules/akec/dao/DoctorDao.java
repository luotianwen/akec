/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.Doctor;

/**
 * 医生管理DAO接口
 * @author 医生管理
 * @version 2020-03-21
 */
@MyBatisDao
public interface DoctorDao extends CrudDao<Doctor> {
	
}