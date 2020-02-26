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
import com.thinkgem.jeesite.modules.akec.entity.KnowlegerKind;
import com.thinkgem.jeesite.modules.akec.service.KnowlegerKindService;

/**
 * 知识分类Controller
 * @author 知识分类
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/knowlegerKind")
public class KnowlegerKindController extends BaseController {

	@Autowired
	private KnowlegerKindService knowlegerKindService;
	
	@ModelAttribute
	public KnowlegerKind get(@RequestParam(required=false) String id) {
		KnowlegerKind entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = knowlegerKindService.get(id);
		}
		if (entity == null){
			entity = new KnowlegerKind();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:knowlegerKind:view")
	@RequestMapping(value = {"list", ""})
	public String list(KnowlegerKind knowlegerKind, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KnowlegerKind> page = knowlegerKindService.findPage(new Page<KnowlegerKind>(request, response), knowlegerKind); 
		model.addAttribute("page", page);
		return "modules/akec/knowlegerKindList";
	}

	@RequiresPermissions("akec:knowlegerKind:view")
	@RequestMapping(value = "form")
	public String form(KnowlegerKind knowlegerKind, Model model) {
		model.addAttribute("knowlegerKind", knowlegerKind);
		return "modules/akec/knowlegerKindForm";
	}

	@RequiresPermissions("akec:knowlegerKind:edit")
	@RequestMapping(value = "save")
	public String save(KnowlegerKind knowlegerKind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, knowlegerKind)){
			return form(knowlegerKind, model);
		}
		knowlegerKindService.save(knowlegerKind);
		addMessage(redirectAttributes, "保存知识分类成功");
		return "redirect:"+Global.getAdminPath()+"/akec/knowlegerKind/?repage";
	}
	
	@RequiresPermissions("akec:knowlegerKind:edit")
	@RequestMapping(value = "delete")
	public String delete(KnowlegerKind knowlegerKind, RedirectAttributes redirectAttributes) {
		knowlegerKindService.delete(knowlegerKind);
		addMessage(redirectAttributes, "删除知识分类成功");
		return "redirect:"+Global.getAdminPath()+"/akec/knowlegerKind/?repage";
	}

}