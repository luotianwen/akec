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
import com.thinkgem.jeesite.modules.akec.entity.BasedataType;
import com.thinkgem.jeesite.modules.akec.service.BasedataTypeService;

/**
 * 常用参数Controller
 * @author 常用参数
 * @version 2020-03-10
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/basedataType")
public class BasedataTypeController extends BaseController {

	@Autowired
	private BasedataTypeService basedataTypeService;
	
	@ModelAttribute
	public BasedataType get(@RequestParam(required=false) String id) {
		BasedataType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = basedataTypeService.get(id);
		}
		if (entity == null){
			entity = new BasedataType();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:basedataType:view")
	@RequestMapping(value = {"list", ""})
	public String list(BasedataType basedataType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BasedataType> page = basedataTypeService.findPage(new Page<BasedataType>(request, response), basedataType); 
		model.addAttribute("page", page);
		return "modules/akec/basedataTypeList";
	}

	@RequiresPermissions("akec:basedataType:view")
	@RequestMapping(value = "form")
	public String form(BasedataType basedataType, Model model) {
		model.addAttribute("basedataType", basedataType);
		return "modules/akec/basedataTypeForm";
	}

	@RequiresPermissions("akec:basedataType:edit")
	@RequestMapping(value = "save")
	public String save(BasedataType basedataType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, basedataType)){
			return form(basedataType, model);
		}
		basedataTypeService.save(basedataType);
		addMessage(redirectAttributes, "保存常用参数成功");
		return "redirect:"+Global.getAdminPath()+"/akec/basedataType/?repage";
	}
	
	@RequiresPermissions("akec:basedataType:edit")
	@RequestMapping(value = "delete")
	public String delete(BasedataType basedataType, RedirectAttributes redirectAttributes) {
		basedataTypeService.delete(basedataType);
		addMessage(redirectAttributes, "删除常用参数成功");
		return "redirect:"+Global.getAdminPath()+"/akec/basedataType/?repage";
	}

}