/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.Doctor;
import com.thinkgem.jeesite.modules.akec.dao.DoctorDao;

/**
 * 医生管理Service
 * @author 医生管理
 * @version 2020-03-21
 */
@Service
@Transactional(readOnly = true)
public class DoctorService extends CrudService<DoctorDao, Doctor> {

	public Doctor get(String id) {
		return super.get(id);
	}
	
	public List<Doctor> findList(Doctor doctor) {
		return super.findList(doctor);
	}
	
	public Page<Doctor> findPage(Page<Doctor> page, Doctor doctor) {
		return super.findPage(page, doctor);
	}
	
	@Transactional(readOnly = false)
	public void save(Doctor doctor) {
		super.save(doctor);
	}
	
	@Transactional(readOnly = false)
	public void delete(Doctor doctor) {
		super.delete(doctor);
	}
	
}