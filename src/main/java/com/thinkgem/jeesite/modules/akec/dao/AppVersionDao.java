/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.AppVersion;

/**
 * App版本管理DAO接口
 * @author App版本管理
 * @version 2020-04-12
 */
@MyBatisDao
public interface AppVersionDao extends CrudDao<AppVersion> {
	
}