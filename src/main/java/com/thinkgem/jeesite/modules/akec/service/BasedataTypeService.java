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
import com.thinkgem.jeesite.modules.akec.entity.BasedataType;
import com.thinkgem.jeesite.modules.akec.dao.BasedataTypeDao;
import com.thinkgem.jeesite.modules.akec.entity.Basedata;
import com.thinkgem.jeesite.modules.akec.dao.BasedataDao;

/**
 * 常用参数Service
 * @author 常用参数
 * @version 2020-03-10
 */
@Service
@Transactional(readOnly = true)
public class BasedataTypeService extends CrudService<BasedataTypeDao, BasedataType> {

	@Autowired
	private BasedataDao basedataDao;
	
	public BasedataType get(String id) {
		BasedataType basedataType = super.get(id);
		basedataType.setBasedataList(basedataDao.findList(new Basedata(basedataType)));
		return basedataType;
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
		for (Basedata basedata : basedataType.getBasedataList()){
			if (basedata.getId() == null){
				continue;
			}
			if (Basedata.DEL_FLAG_NORMAL.equals(basedata.getDelFlag())){
				if (StringUtils.isBlank(basedata.getId())){
					basedata.setBase(basedataType);
					basedata.preInsert();
					basedataDao.insert(basedata);
				}else{
					basedata.preUpdate();
					basedataDao.update(basedata);
				}
			}else{
				basedataDao.delete(basedata);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BasedataType basedataType) {
		super.delete(basedataType);
		basedataDao.delete(new Basedata(basedataType));
	}
	
}