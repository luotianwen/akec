/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.Grade;
import com.thinkgem.jeesite.modules.akec.dao.GradeDao;

/**
 * 评分类型Service
 * @author 评分类型
 * @version 2020-03-14
 */
@Service
@Transactional(readOnly = true)
public class GradeService extends CrudService<GradeDao, Grade> {

	public Grade get(String id) {
		return super.get(id);
	}
	
	public List<Grade> findList(Grade grade) {
		return super.findList(grade);
	}
	
	public Page<Grade> findPage(Page<Grade> page, Grade grade) {
		return super.findPage(page, grade);
	}
	
	@Transactional(readOnly = false)
	public void save(Grade grade) {
		super.save(grade);
	}
	
	@Transactional(readOnly = false)
	public void delete(Grade grade) {
		super.delete(grade);
	}
	
}