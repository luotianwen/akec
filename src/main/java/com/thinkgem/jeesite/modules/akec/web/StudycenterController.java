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
import com.thinkgem.jeesite.modules.akec.entity.Studycenter;
import com.thinkgem.jeesite.modules.akec.service.StudycenterService;

/**
 * 发布资料Controller
 * @author 发布资料
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/studycenter")
public class StudycenterController extends BaseController {

	@Autowired
	private StudycenterService studycenterService;
	
	@ModelAttribute
	public Studycenter get(@RequestParam(required=false) String id) {
		Studycenter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studycenterService.get(id);
		}
		if (entity == null){
			entity = new Studycenter();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:studycenter:view")
	@RequestMapping(value = {"list", ""})
	public String list(Studycenter studycenter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Studycenter> page = studycenterService.findPage(new Page<Studycenter>(request, response), studycenter); 
		model.addAttribute("page", page);
		return "modules/akec/studycenterList";
	}

	@RequiresPermissions("akec:studycenter:view")
	@RequestMapping(value = "form")
	public String form(Studycenter studycenter, Model model) {
		model.addAttribute("studycenter", studycenter);
		return "modules/akec/studycenterForm";
	}

	@RequiresPermissions("akec:studycenter:edit")
	@RequestMapping(value = "save")
	public String save(Studycenter studycenter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studycenter)){
			return form(studycenter, model);
		}
		studycenterService.save(studycenter);
		addMessage(redirectAttributes, "保存发布资料成功");
		return "redirect:"+Global.getAdminPath()+"/akec/studycenter/?repage";
	}
	
	@RequiresPermissions("akec:studycenter:edit")
	@RequestMapping(value = "delete")
	public String delete(Studycenter studycenter, RedirectAttributes redirectAttributes) {
		studycenterService.delete(studycenter);
		addMessage(redirectAttributes, "删除发布资料成功");
		return "redirect:"+Global.getAdminPath()+"/akec/studycenter/?repage";
	}

}