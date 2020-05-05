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
import com.thinkgem.jeesite.modules.akec.entity.Dealer;
import com.thinkgem.jeesite.modules.akec.service.DealerService;

/**
 * 经销商信息Controller
 * @author 经销商信息
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/dealer")
public class DealerController extends BaseController {

	@Autowired
	private DealerService dealerService;
	
	@ModelAttribute
	public Dealer get(@RequestParam(required=false) String id) {
		Dealer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dealerService.get(id);
		}
		if (entity == null){
			entity = new Dealer();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:dealer:view")
	@RequestMapping(value = {"list", ""})
	public String list(Dealer dealer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Dealer> page = dealerService.findPage(new Page<Dealer>(request, response), dealer); 
		model.addAttribute("page", page);
		return "modules/akec/dealerList";
	}

	@RequiresPermissions("akec:dealer:view")
	@RequestMapping(value = "form")
	public String form(Dealer dealer, Model model) {
		model.addAttribute("dealer", dealer);
		return "modules/akec/dealerForm";
	}

	@RequiresPermissions("akec:dealer:edit")
	@RequestMapping(value = "save")
	public String save(Dealer dealer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dealer)){
			return form(dealer, model);
		}
		dealerService.save(dealer);
		addMessage(redirectAttributes, "保存经销商信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/dealer/?repage";
	}
	
	@RequiresPermissions("akec:dealer:edit")
	@RequestMapping(value = "delete")
	public String delete(Dealer dealer, RedirectAttributes redirectAttributes) {
		dealerService.delete(dealer);
		addMessage(redirectAttributes, "删除经销商信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/dealer/?repage";
	}
	@RequiresPermissions("akec:dealer:edit")
	@RequestMapping(value = "tball")
	public String tball(RedirectAttributes redirectAttributes) {
		dealerService.tball();
		addMessage(redirectAttributes, "同步所有经销商信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/dealer/?repage";
	}
}