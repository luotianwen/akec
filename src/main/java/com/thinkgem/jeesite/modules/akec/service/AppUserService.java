/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import com.thinkgem.jeesite.modules.akec.web.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.AppUser;
import com.thinkgem.jeesite.modules.akec.dao.AppUserDao;

/**
 * APP用户管理Service
 * @author APP用户管理
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class AppUserService extends CrudService<AppUserDao, AppUser> {

	public AppUser get(String id) {
		return super.get(id);
	}
	
	public List<AppUser> findList(AppUser appUser) {
		return super.findList(appUser);
	}
	
	public Page<AppUser> findPage(Page<AppUser> page, AppUser appUser) {
		return super.findPage(page, appUser);
	}
	
	@Transactional(readOnly = false)
	public void save(AppUser appUser) {
		super.save(appUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(AppUser appUser) {
		super.delete(appUser);
	}

    public void modifyUserReportType(AppUser appUser) {
		dao.modifyUserReportType(appUser);
    }

	public AppUser queryUserInfo(AppUser queryUser) {
		return  dao.queryUserInfo(queryUser);
	}

    public List<AppUser> queryListUserBelongDealer(AppUser queryUser) {
	    return dao.queryListUserBelongDealer(queryUser);
    }
    @Transactional(readOnly = false)
    public boolean modifyUser(AppUser queryUser) {
	    return dao.modifyUser(queryUser);
    }

    public boolean modifyUserPass(AppUser queryUser) {
        return dao.modifyUserPass(queryUser);
    }

	public boolean forgetUserPass(AppUser queryUser) {
		return dao.forgetUserPass(queryUser);
	}
	@Transactional(readOnly = false)
    public void pass(AppUser appUser) {
		String modifyPassword = appUser.getPass();

			modifyPassword = MD5Util.GetMD5Code(modifyPassword);
		appUser.setPass(modifyPassword);
		dao.pass(appUser);
    }
	@Transactional(readOnly = false)
	public boolean outUser(AppUser appUser) {
		return dao.outUser(appUser);
	}
}