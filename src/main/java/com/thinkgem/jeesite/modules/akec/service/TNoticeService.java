/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.TNotice;
import com.thinkgem.jeesite.modules.akec.dao.TNoticeDao;

/**
 * 通知中心Service
 * @author 通知中心
 * @version 2020-03-10
 */
@Service
@Transactional(readOnly = true)
public class TNoticeService extends CrudService<TNoticeDao, TNotice> {

	public TNotice get(String id) {
		return super.get(id);
	}
	
	public List<TNotice> findList(TNotice tNotice) {
		return super.findList(tNotice);
	}
	
	public Page<TNotice> findPage(Page<TNotice> page, TNotice tNotice) {
		return super.findPage(page, tNotice);
	}
	
	@Transactional(readOnly = false)
	public void save(TNotice tNotice) {
		super.save(tNotice);
	}
	
	@Transactional(readOnly = false)
	public void delete(TNotice tNotice) {
		super.delete(tNotice);
	}

    public List<TNotice> findUnreadList(TNotice tNotice) {
		return dao.findUnreadList(tNotice);
    }
	@Transactional(readOnly = false)
	public void deleteNoticeUserRead(TNotice tNotice) {
		dao.deleteNoticeUserRead(tNotice);
	}
}