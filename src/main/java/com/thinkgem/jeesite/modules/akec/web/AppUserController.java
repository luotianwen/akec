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
import com.thinkgem.jeesite.modules.akec.entity.AppUser;
import com.thinkgem.jeesite.modules.akec.service.AppUserService;

/**
 * APP用户管理Controller
 * @author APP用户管理
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/appUser")
public class AppUserController extends BaseController {

	@Autowired
	private AppUserService appUserService;
	
	@ModelAttribute
	public AppUser get(@RequestParam(required=false) String id) {
		AppUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = appUserService.get(id);
		}
		if (entity == null){
			entity = new AppUser();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:appUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(AppUser appUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AppUser> page = appUserService.findPage(new Page<AppUser>(request, response), appUser); 
		model.addAttribute("page", page);
		return "modules/akec/appUserList";
	}

	@RequiresPermissions("akec:appUser:view")
	@RequestMapping(value = "form")
	public String form(AppUser appUser, Model model) {
		model.addAttribute("appUser", appUser);
		return "modules/akec/appUserForm";
	}

	@RequiresPermissions("akec:appUser:edit")
	@RequestMapping(value = "save")
	public String save(AppUser appUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, appUser)){
			return form(appUser, model);
		}
		appUserService.save(appUser);
		addMessage(redirectAttributes, "保存APP用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/akec/appUser/?repage";
	}
	
	@RequiresPermissions("akec:appUser:edit")
	@RequestMapping(value = "delete")
	public String delete(AppUser appUser, RedirectAttributes redirectAttributes) {
		appUserService.delete(appUser);
		addMessage(redirectAttributes, "删除APP用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/akec/appUser/?repage";
	}

}