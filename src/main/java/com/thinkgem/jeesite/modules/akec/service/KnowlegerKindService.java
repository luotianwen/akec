/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.akec.entity.KnowlegerKind;
import com.thinkgem.jeesite.modules.akec.dao.KnowlegerKindDao;
import com.thinkgem.jeesite.modules.akec.entity.Studycenter;
import com.thinkgem.jeesite.modules.akec.dao.StudycenterDao;

/**
 * 知识分类Service
 * @author 知识分类
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class KnowlegerKindService extends CrudService<KnowlegerKindDao, KnowlegerKind> {

	@Autowired
	private StudycenterDao studycenterDao;
	
	public KnowlegerKind get(String id) {
		KnowlegerKind knowlegerKind = super.get(id);
		knowlegerKind.setStudycenterList(studycenterDao.findList(new Studycenter(knowlegerKind)));
		return knowlegerKind;
	}
	
	public List<KnowlegerKind> findList(KnowlegerKind knowlegerKind) {
		return super.findList(knowlegerKind);
	}
	
	public Page<KnowlegerKind> findPage(Page<KnowlegerKind> page, KnowlegerKind knowlegerKind) {
		return super.findPage(page, knowlegerKind);
	}
	
	@Transactional(readOnly = false)
	public void save(KnowlegerKind knowlegerKind) {
		super.save(knowlegerKind);
		for (Studycenter studycenter : knowlegerKind.getStudycenterList()){
			if (studycenter.getId() == null){
				continue;
			}
			if (Studycenter.DEL_FLAG_NORMAL.equals(studycenter.getDelFlag())){
				if (StringUtils.isBlank(studycenter.getId())){
					studycenter.setKk(knowlegerKind);
					studycenter.preInsert();
					studycenterDao.insert(studycenter);
				}else{
					studycenter.preUpdate();
					studycenterDao.update(studycenter);
				}
			}else{
				studycenterDao.delete(studycenter);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(KnowlegerKind knowlegerKind) {
		super.delete(knowlegerKind);
		studycenterDao.delete(new Studycenter(knowlegerKind));
	}
	
}