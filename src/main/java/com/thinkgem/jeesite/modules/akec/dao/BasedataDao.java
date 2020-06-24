/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.Basedata;

import java.util.List;

/**
 * 常用参数DAO接口
 * @author 常用参数
 * @version 2020-03-10
 */
@MyBatisDao
public interface BasedataDao extends CrudDao<Basedata> {

    List<Basedata> getByName(Basedata surgeryGrade);
}