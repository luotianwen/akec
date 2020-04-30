/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.AppVersion;
import com.thinkgem.jeesite.modules.akec.dao.AppVersionDao;

/**
 * App版本管理Service
 * @author App版本管理
 * @version 2020-04-12
 */
@Service
@Transactional(readOnly = true)
public class AppVersionService extends CrudService<AppVersionDao, AppVersion> {

	public AppVersion get(String id) {
		return super.get(id);
	}
	
	public List<AppVersion> findList(AppVersion appVersion) {
		return super.findList(appVersion);
	}
	
	public Page<AppVersion> findPage(Page<AppVersion> page, AppVersion appVersion) {
		return super.findPage(page, appVersion);
	}
	
	@Transactional(readOnly = false)
	public void save(AppVersion appVersion) {
		super.save(appVersion);
	}
	
	@Transactional(readOnly = false)
	public void delete(AppVersion appVersion) {
		super.delete(appVersion);
	}
	
}