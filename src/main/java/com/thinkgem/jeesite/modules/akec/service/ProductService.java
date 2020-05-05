/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.Product;
import com.thinkgem.jeesite.modules.akec.dao.ProductDao;

/**
 * 产品信息Service
 * @author 产品信息
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class ProductService extends CrudService<ProductDao, Product> {

	public Product get(String id) {
		return super.get(id);
	}
	
	public List<Product> findList(Product product) {
		return super.findList(product);
	}
	
	public Page<Product> findPage(Page<Product> page, Product product) {
		return super.findPage(page, product);
	}
	
	@Transactional(readOnly = false)
	public void save(Product product) {
		super.save(product);
	}
	
	@Transactional(readOnly = false)
	public void delete(Product product) {
		super.delete(product);
	}
	@Transactional(readOnly = false)
    public void tbsy() {
         List<Product> ps=dao.findtbsyList();
		for (Product p:ps
			 ) {
			Product p1=new Product();
			p1.setCode(p.getCode());
			List<Product> ss=dao.findList(p1);
			if(ss==null||ss.size()==0){
				super.save(p);
			}else{
				p.setStatus(ss.get(0).getStatus());
				p.setId(ss.get(0).getId());
				super.save(p);
			}
		}
    }
	@Transactional(readOnly = false)
	public void tbtoday() {
		List<Product> ps=dao.tbtoday();
		for (Product p:ps
		) {
			Product p1=new Product();
			p1.setCode(p.getCode());
			List<Product> ss=dao.findList(p1);
			if(ss==null||ss.size()==0){
				super.save(p);
			}else{
				p.setStatus(ss.get(0).getStatus());
				p.setId(ss.get(0).getId());
				super.save(p);
			}
		}
	}
}