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
import com.thinkgem.jeesite.modules.akec.entity.Grade;
import com.thinkgem.jeesite.modules.akec.service.GradeService;

/**
 * 评分类型Controller
 * @author 评分类型
 * @version 2020-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/grade")
public class GradeController extends BaseController {

	@Autowired
	private GradeService gradeService;
	
	@ModelAttribute
	public Grade get(@RequestParam(required=false) String id) {
		Grade entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeService.get(id);
		}
		if (entity == null){
			entity = new Grade();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:grade:view")
	@RequestMapping(value = {"list", ""})
	public String list(Grade grade, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Grade> page = gradeService.findPage(new Page<Grade>(request, response), grade); 
		model.addAttribute("page", page);
		return "modules/akec/gradeList";
	}

	@RequiresPermissions("akec:grade:view")
	@RequestMapping(value = "form")
	public String form(Grade grade, Model model) {
		model.addAttribute("grade", grade);
		return "modules/akec/gradeForm";
	}

	@RequiresPermissions("akec:grade:edit")
	@RequestMapping(value = "save")
	public String save(Grade grade, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, grade)){
			return form(grade, model);
		}
		gradeService.save(grade);
		addMessage(redirectAttributes, "保存评分类型成功");
		return "redirect:"+Global.getAdminPath()+"/akec/grade/?repage";
	}
	
	@RequiresPermissions("akec:grade:edit")
	@RequestMapping(value = "delete")
	public String delete(Grade grade, RedirectAttributes redirectAttributes) {
		gradeService.delete(grade);
		addMessage(redirectAttributes, "删除评分类型成功");
		return "redirect:"+Global.getAdminPath()+"/akec/grade/?repage";
	}

}