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
import com.thinkgem.jeesite.modules.akec.entity.Sellproduct;
import com.thinkgem.jeesite.modules.akec.service.SellproductService;

/**
 * 已售产品信息Controller
 * @author 已售产品信息
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/sellproduct")
public class SellproductController extends BaseController {

	@Autowired
	private SellproductService sellproductService;
	
	@ModelAttribute
	public Sellproduct get(@RequestParam(required=false) String id) {
		Sellproduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sellproductService.get(id);
		}
		if (entity == null){
			entity = new Sellproduct();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:sellproduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(Sellproduct sellproduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sellproduct> page = sellproductService.findPage(new Page<Sellproduct>(request, response), sellproduct); 
		model.addAttribute("page", page);
		return "modules/akec/sellproductList";
	}

	@RequiresPermissions("akec:sellproduct:view")
	@RequestMapping(value = "form")
	public String form(Sellproduct sellproduct, Model model) {
		model.addAttribute("sellproduct", sellproduct);
		return "modules/akec/sellproductForm";
	}

	@RequiresPermissions("akec:sellproduct:edit")
	@RequestMapping(value = "save")
	public String save(Sellproduct sellproduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sellproduct)){
			return form(sellproduct, model);
		}
		sellproductService.save(sellproduct);
		addMessage(redirectAttributes, "保存已售产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/sellproduct/?repage";
	}
	
	@RequiresPermissions("akec:sellproduct:edit")
	@RequestMapping(value = "delete")
	public String delete(Sellproduct sellproduct, RedirectAttributes redirectAttributes) {
		sellproductService.delete(sellproduct);
		addMessage(redirectAttributes, "删除已售产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/sellproduct/?repage";
	}

}