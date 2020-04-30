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
import com.thinkgem.jeesite.modules.akec.entity.Region;
import com.thinkgem.jeesite.modules.akec.service.RegionService;

import java.util.List;

/**
 * 医院信息Controller
 * @author 医院信息
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/region")
public class RegionController extends BaseController {

	@Autowired
	private RegionService regionService;
	
	@ModelAttribute
	public Region get(@RequestParam(required=false) String id) {
		Region entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = regionService.get(id);
		}
		if (entity == null){
			entity = new Region();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:region:view")
	@RequestMapping(value = {"list", ""})
	public String list(Region region, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Region> page = regionService.findPage(new Page<Region>(request, response), region);
		List<Region> rs=page.getList();
		/*for (Region r:rs
			 ) {
			Region c=regionService.get(r.getParentCode());
			r.setCityName(c.getName());
			r.setProvinceName(regionService.get(c.getParentCode()).getName());
		}*/
		model.addAttribute("page", page);
		return "modules/akec/regionList";
	}

	@RequiresPermissions("akec:region:view")
	@RequestMapping(value = "form")
	public String form(Region region, Model model) {
		model.addAttribute("region", region);
		return "modules/akec/regionForm";
	}

	@RequiresPermissions("akec:region:edit")
	@RequestMapping(value = "save")
	public String save(Region region, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, region)){
			return form(region, model);
		}
		regionService.save(region);
		addMessage(redirectAttributes, "保存医院信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/region/?repage";
	}
	
	@RequiresPermissions("akec:region:edit")
	@RequestMapping(value = "delete")
	public String delete(Region region, RedirectAttributes redirectAttributes) {
		regionService.delete(region);
		addMessage(redirectAttributes, "删除医院信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/region/?repage";
	}

}