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
import com.thinkgem.jeesite.modules.akec.entity.TProduct;
import com.thinkgem.jeesite.modules.akec.service.TProductService;

/**
 * 产品信息Controller
 * @author 产品信息
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/tProduct")
public class TProductController extends BaseController {

	@Autowired
	private TProductService tProductService;
	
	@ModelAttribute
	public TProduct get(@RequestParam(required=false) String id) {
		TProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tProductService.get(id);
		}
		if (entity == null){
			entity = new TProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:tProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(TProduct tProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TProduct> page = tProductService.findPage(new Page<TProduct>(request, response), tProduct); 
		model.addAttribute("page", page);
		return "modules/akec/tProductList";
	}

	@RequiresPermissions("akec:tProduct:view")
	@RequestMapping(value = "form")
	public String form(TProduct tProduct, Model model) {
		model.addAttribute("tProduct", tProduct);
		return "modules/akec/tProductForm";
	}

	@RequiresPermissions("akec:tProduct:edit")
	@RequestMapping(value = "save")
	public String save(TProduct tProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tProduct)){
			return form(tProduct, model);
		}
		tProductService.save(tProduct);
		addMessage(redirectAttributes, "保存产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/tProduct/?repage";
	}
	
	@RequiresPermissions("akec:tProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(TProduct tProduct, RedirectAttributes redirectAttributes) {
		tProductService.delete(tProduct);
		addMessage(redirectAttributes, "删除产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/tProduct/?repage";
	}

}