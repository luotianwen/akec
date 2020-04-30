/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookOperation;

import java.util.List;

/**
 * 手术报数DAO接口
 * @author 手术报数
 * @version 2020-03-27
 */
@MyBatisDao
public interface ReportStandbookOperationDao extends CrudDao<ReportStandbookOperation> {

    List findOnlyList(ReportStandbookOperation reportStandbook);
}