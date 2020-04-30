/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.akec.entity.TNotice;

import java.util.List;

/**
 * 通知中心DAO接口
 * @author 通知中心
 * @version 2020-03-10
 */
@MyBatisDao
public interface TNoticeDao extends CrudDao<TNotice> {

    List<TNotice> findUnreadList(TNotice tNotice);

    void deleteNoticeUserRead(TNotice tNotice);
}