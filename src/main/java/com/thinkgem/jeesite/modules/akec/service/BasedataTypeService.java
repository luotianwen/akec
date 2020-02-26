/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.BasedataType;
import com.thinkgem.jeesite.modules.akec.dao.BasedataTypeDao;

/**
 * 常用参数Service
 * @author 常用参数
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class BasedataTypeService extends CrudService<BasedataTypeDao, BasedataType> {

	public BasedataType get(String id) {
		return super.get(id);
	}
	
	public List<BasedataType> findList(BasedataType basedataType) {
		return super.findList(basedataType);
	}
	
	public Page<BasedataType> findPage(Page<BasedataType> page, BasedataType basedataType) {
		return super.findPage(page, basedataType);
	}
	
	@Transactional(readOnly = false)
	public void save(BasedataType basedataType) {
		super.save(basedataType);
	}
	
	@Transactional(readOnly = false)
	public void delete(BasedataType basedataType) {
		super.delete(basedataType);
	}
	
}