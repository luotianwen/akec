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
import com.thinkgem.jeesite.modules.akec.entity.Hospital;
import com.thinkgem.jeesite.modules.akec.dao.HospitalDao;
import com.thinkgem.jeesite.modules.akec.entity.HospitalCountDetail;
import com.thinkgem.jeesite.modules.akec.dao.HospitalCountDetailDao;
import com.thinkgem.jeesite.modules.akec.entity.HospitalProductDetail;
import com.thinkgem.jeesite.modules.akec.dao.HospitalProductDetailDao;

/**
 * 医院管理Service
 * @author 医院管理
 * @version 2020-04-04
 */
@Service
@Transactional(readOnly = true)
public class HospitalService extends CrudService<HospitalDao, Hospital> {

	@Autowired
	private HospitalCountDetailDao hospitalCountDetailDao;
	@Autowired
	private HospitalProductDetailDao hospitalProductDetailDao;
	
	public Hospital get(String id) {
		Hospital hospital = super.get(id);
		hospital.setHospitalCountDetailList(hospitalCountDetailDao.findList(new HospitalCountDetail(hospital)));
		hospital.setHospitalProductDetailList(hospitalProductDetailDao.findList(new HospitalProductDetail(hospital)));
		return hospital;
	}
	
	public List<Hospital> findList(Hospital hospital) {
		return super.findList(hospital);
	}
	
	public Page<Hospital> findPage(Page<Hospital> page, Hospital hospital) {
		return super.findPage(page, hospital);
	}
	
	@Transactional(readOnly = false)
	public void save(Hospital hospital) {
		super.save(hospital);
		hospitalCountDetailDao.delete(new HospitalCountDetail(hospital));
		hospitalProductDetailDao.delete(new HospitalProductDetail(hospital));
		for (HospitalCountDetail hospitalCountDetail : hospital.getHospitalCountDetailList()){
					hospitalCountDetail.setH(hospital);
					hospitalCountDetail.preInsert();
					hospitalCountDetailDao.insert(hospitalCountDetail);

		}
		for (HospitalProductDetail hospitalProductDetail : hospital.getHospitalProductDetailList()){


					hospitalProductDetail.setH(hospital);
					hospitalProductDetail.preInsert();
					hospitalProductDetailDao.insert(hospitalProductDetail);

		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Hospital hospital) {
		super.delete(hospital);
		hospitalCountDetailDao.delete(new HospitalCountDetail(hospital));
		hospitalProductDetailDao.delete(new HospitalProductDetail(hospital));
	}
	
}