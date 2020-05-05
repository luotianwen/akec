/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import com.thinkgem.jeesite.modules.akec.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.Dealer;
import com.thinkgem.jeesite.modules.akec.dao.DealerDao;

/**
 * 经销商信息Service
 * @author 经销商信息
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class DealerService extends CrudService<DealerDao, Dealer> {

	public Dealer get(String id) {
		return super.get(id);
	}
	
	public List<Dealer> findList(Dealer dealer) {
		return super.findList(dealer);
	}
	
	public Page<Dealer> findPage(Page<Dealer> page, Dealer dealer) {
		return super.findPage(page, dealer);
	}
	
	@Transactional(readOnly = false)
	public void save(Dealer dealer) {
		super.save(dealer);
	}
	
	@Transactional(readOnly = false)
	public void delete(Dealer dealer) {
		super.delete(dealer);
	}
	@Transactional(readOnly = false)
    public void tball() {
		List<Dealer>ds=dao.tball();
		for (Dealer p:ds
		) {
			Dealer p1=new Dealer();
			p1.setCode(p.getCode());
			List ss=dao.findList(p1);
			if(ss==null||ss.size()==0){
				super.save(p);
			}
		}
    }
	@Transactional(readOnly = false)
	public void tbtoday() {
		List<Dealer>ds=dao.tbtoday();
		for (Dealer p:ds
		) {
			Dealer p1=new Dealer();
			p1.setCode(p.getCode());
			List ss=dao.findList(p1);
			if(ss==null||ss.size()==0){
				super.save(p);
			}
		}
	}

}