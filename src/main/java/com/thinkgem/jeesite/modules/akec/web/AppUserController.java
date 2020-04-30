/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.akec.dao.BasedataDao;
import com.thinkgem.jeesite.modules.akec.entity.*;
import com.thinkgem.jeesite.modules.akec.service.BasedataTypeService;
import com.thinkgem.jeesite.modules.akec.service.DealerService;
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
import com.thinkgem.jeesite.modules.akec.service.AppUserService;

import java.util.List;

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
	@Autowired
	private BasedataDao basedataDao;
	@RequiresPermissions("akec:appUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(AppUser appUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AppUser> page = appUserService.findPage(new Page<AppUser>(request, response), appUser);
		List<Basedata> basedataList =basedataTypeService.get("518370AD648C42B79759D2B6DB04DF6B").getBasedataList();
		model.addAttribute("basedataList",basedataList);
		model.addAttribute("page", page);
		return "modules/akec/appUserList";
	}
	@Autowired
	private BasedataTypeService basedataTypeService;
	@Autowired
	private DealerService dealerService;
	@RequiresPermissions("akec:appUser:view")
	@RequestMapping(value = "form")
	public String form(AppUser appUser, Model model) {

		List<Basedata> basedataList =basedataTypeService.get("518370AD648C42B79759D2B6DB04DF6B").getBasedataList();
		model.addAttribute("basedataList",basedataList);
		List<Dealer> dealers =dealerService.findList(new Dealer());
		model.addAttribute("dealers",dealers);
		if(StringUtils.isEmpty(appUser.getDealerId())){
			for (Dealer d:dealers
				 ) {
				if(appUser.getDealerName().equals(d.getName())){
					appUser.setDealerId(d.getId());
					break;
				}
			}
		}
		model.addAttribute("appUser", appUser);
		return "modules/akec/appUserForm";
	}

	@RequiresPermissions("akec:appUser:edit")
	@RequestMapping(value = "pass")
	public String pass(AppUser appUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, appUser)){
			return form(appUser, model);
		}
		appUserService.pass(appUser);
		addMessage(redirectAttributes, "重置密码成功");
		return "redirect:"+Global.getAdminPath()+"/akec/appUser/?repage";
	}

	@RequiresPermissions("akec:appUser:edit")
	@RequestMapping(value = "save")
	public String save(AppUser appUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, appUser)){
			return form(appUser, model);
		}
		appUser.setDealerName(dealerService.get(appUser.getDealerId()).getName());
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