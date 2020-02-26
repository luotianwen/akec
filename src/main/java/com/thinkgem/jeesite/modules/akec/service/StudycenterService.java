/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.Studycenter;
import com.thinkgem.jeesite.modules.akec.dao.StudycenterDao;

/**
 * 发布资料Service
 * @author 发布资料
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class StudycenterService extends CrudService<StudycenterDao, Studycenter> {

	public Studycenter get(String id) {
		return super.get(id);
	}
	
	public List<Studycenter> findList(Studycenter studycenter) {
		return super.findList(studycenter);
	}
	
	public Page<Studycenter> findPage(Page<Studycenter> page, Studycenter studycenter) {
		return super.findPage(page, studycenter);
	}
	
	@Transactional(readOnly = false)
	public void save(Studycenter studycenter) {
		super.save(studycenter);
	}
	
	@Transactional(readOnly = false)
	public void delete(Studycenter studycenter) {
		super.delete(studycenter);
	}
	
}