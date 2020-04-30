/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.akec.entity.Doctor;
import com.thinkgem.jeesite.modules.akec.service.DoctorService;

/**
 * 医生管理Controller
 * @author 医生管理
 * @version 2020-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/doctor")
public class DoctorController extends BaseController {

	@Autowired
	private DoctorService doctorService;
	
	@ModelAttribute
	public Doctor get(@RequestParam(required=false) String id) {
		Doctor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = doctorService.get(id);
		}
		if (entity == null){
			entity = new Doctor();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:doctor:view")
	@RequestMapping(value = {"list", ""})
	public String list(Doctor doctor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Doctor> page = doctorService.findPage(new Page<Doctor>(request, response), doctor); 
		model.addAttribute("page", page);
		return "modules/akec/doctorList";
	}

	@RequiresPermissions("akec:doctor:view")
	@RequestMapping(value = "form")
	public String form(Doctor doctor, Model model) {
		model.addAttribute("doctor", doctor);
		return "modules/akec/doctorForm";
	}

	@RequiresPermissions("akec:doctor:edit")
	@RequestMapping(value = "save")
	public String save(Doctor doctor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, doctor)){
			return form(doctor, model);
		}
		doctorService.save(doctor);
		addMessage(redirectAttributes, "保存医生管理成功");
		return "redirect:"+Global.getAdminPath()+"/akec/doctor/?repage";
	}
	
	@RequiresPermissions("akec:doctor:edit")
	@RequestMapping(value = "delete")
	public String delete(Doctor doctor, RedirectAttributes redirectAttributes) {
		doctorService.delete(doctor);
		addMessage(redirectAttributes, "删除医生管理成功");
		return "redirect:"+Global.getAdminPath()+"/akec/doctor/?repage";
	}

}