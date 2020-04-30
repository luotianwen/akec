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
import com.thinkgem.jeesite.modules.akec.entity.AppVersion;
import com.thinkgem.jeesite.modules.akec.service.AppVersionService;

/**
 * App版本管理Controller
 * @author App版本管理
 * @version 2020-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/appVersion")
public class AppVersionController extends BaseController {

	@Autowired
	private AppVersionService appVersionService;
	
	@ModelAttribute
	public AppVersion get(@RequestParam(required=false) String id) {
		AppVersion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = appVersionService.get(id);
		}
		if (entity == null){
			entity = new AppVersion();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:appVersion:view")
	@RequestMapping(value = {"list", ""})
	public String list(AppVersion appVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AppVersion> page = appVersionService.findPage(new Page<AppVersion>(request, response), appVersion); 
		model.addAttribute("page", page);
		return "modules/akec/appVersionList";
	}

	@RequiresPermissions("akec:appVersion:view")
	@RequestMapping(value = "form")
	public String form(AppVersion appVersion, Model model) {
		model.addAttribute("appVersion", appVersion);
		return "modules/akec/appVersionForm";
	}

	@RequiresPermissions("akec:appVersion:edit")
	@RequestMapping(value = "save")
	public String save(AppVersion appVersion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, appVersion)){
			return form(appVersion, model);
		}
		appVersionService.save(appVersion);
		addMessage(redirectAttributes, "保存App版本管理成功");
		return "redirect:"+Global.getAdminPath()+"/akec/appVersion/?repage";
	}
	
	@RequiresPermissions("akec:appVersion:edit")
	@RequestMapping(value = "delete")
	public String delete(AppVersion appVersion, RedirectAttributes redirectAttributes) {
		appVersionService.delete(appVersion);
		addMessage(redirectAttributes, "删除App版本管理成功");
		return "redirect:"+Global.getAdminPath()+"/akec/appVersion/?repage";
	}

}