/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.AppUser;

import java.util.List;

/**
 * APP用户管理DAO接口
 * @author APP用户管理
 * @version 2020-02-27
 */
@MyBatisDao
public interface AppUserDao extends CrudDao<AppUser> {

    void modifyUserReportType(AppUser appUser);

    AppUser queryUserInfo(AppUser queryUser);

    List<AppUser> queryListUserBelongDealer(AppUser queryUser);

    boolean modifyUser(AppUser queryUser);

    boolean modifyUserPass(AppUser queryUser);

    boolean forgetUserPass(AppUser queryUser);

    void pass(AppUser appUser);

    boolean outUser(AppUser appUser);
}