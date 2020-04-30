/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookProductDetail;

/**
 * 报台信息DAO接口
 * @author 报台信息
 * @version 2020-02-27
 */
@MyBatisDao
public interface ReportStandbookProductDetailDao extends CrudDao<ReportStandbookProductDetail> {

    int countReprotProduct(ReportStandbookProductDetail rd);

    int countHistoryReprotProduct(ReportStandbookProductDetail rd);
}