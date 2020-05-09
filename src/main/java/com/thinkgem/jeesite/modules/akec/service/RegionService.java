/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.Region;
import com.thinkgem.jeesite.modules.akec.dao.RegionDao;

/**
 * 医院信息Service
 * @author 医院信息
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class RegionService extends CrudService<RegionDao, Region> {

	public Region get(String id) {
		return super.get(id);
	}
	
	public List<Region> findList(Region region) {
		return super.findList(region);
	}
	
	public Page<Region> findPage(Page<Region> page, Region region) {
		return super.findPage(page, region);
	}
	
	@Transactional(readOnly = false)
	public void save(Region region) {
		super.save(region);
	}
	
	@Transactional(readOnly = false)
	public void delete(Region region) {
		super.delete(region);
	}

    public List<Region> queryProvince(Region region) {
		return dao.queryProvince(region);
    }

	public List<Region> queryHosptail(Region region) {
		return dao.queryHosptail(region);
	}

    public List<Region> findProList(Region region) {
		return dao.findProList(region);
    }
}