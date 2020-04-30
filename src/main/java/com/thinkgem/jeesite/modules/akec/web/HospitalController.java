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
import com.thinkgem.jeesite.modules.akec.entity.Hospital;
import com.thinkgem.jeesite.modules.akec.service.HospitalService;

/**
 * 医院管理Controller
 * @author 医院管理
 * @version 2020-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/hospital")
public class HospitalController extends BaseController {

	@Autowired
	private HospitalService hospitalService;
	
	@ModelAttribute
	public Hospital get(@RequestParam(required=false) String id) {
		Hospital entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hospitalService.get(id);
		}
		if (entity == null){
			entity = new Hospital();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:hospital:view")
	@RequestMapping(value = {"list", ""})
	public String list(Hospital hospital, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Hospital> page = hospitalService.findPage(new Page<Hospital>(request, response), hospital); 
		model.addAttribute("page", page);
		return "modules/akec/hospitalList";
	}

	@RequiresPermissions("akec:hospital:view")
	@RequestMapping(value = "form")
	public String form(Hospital hospital, Model model) {
		model.addAttribute("hospital", hospital);
		return "modules/akec/hospitalForm";
	}

	@RequiresPermissions("akec:hospital:edit")
	@RequestMapping(value = "save")
	public String save(Hospital hospital, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hospital)){
			return form(hospital, model);
		}
		hospitalService.save(hospital);
		addMessage(redirectAttributes, "保存医院管理成功");
		return "redirect:"+Global.getAdminPath()+"/akec/hospital/?repage";
	}
	
	@RequiresPermissions("akec:hospital:edit")
	@RequestMapping(value = "delete")
	public String delete(Hospital hospital, RedirectAttributes redirectAttributes) {
		hospitalService.delete(hospital);
		addMessage(redirectAttributes, "删除医院管理成功");
		return "redirect:"+Global.getAdminPath()+"/akec/hospital/?repage";
	}

}