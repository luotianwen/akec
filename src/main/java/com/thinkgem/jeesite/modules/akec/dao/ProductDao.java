/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.Product;

import java.util.List;

/**
 * 产品信息DAO接口
 * @author 产品信息
 * @version 2020-02-27
 */
@MyBatisDao
public interface ProductDao extends CrudDao<Product> {

    List<Product> findtbsyList();

    List<Product> tbtoday();
}