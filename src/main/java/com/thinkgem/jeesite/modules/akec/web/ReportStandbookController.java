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
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbook;
import com.thinkgem.jeesite.modules.akec.service.ReportStandbookService;

/**
 * 报台信息Controller
 * @author 报台信息
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/reportStandbook")
public class ReportStandbookController extends BaseController {

	@Autowired
	private ReportStandbookService reportStandbookService;
	
	@ModelAttribute
	public ReportStandbook get(@RequestParam(required=false) String id) {
		ReportStandbook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reportStandbookService.get(id);
		}
		if (entity == null){
			entity = new ReportStandbook();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:reportStandbook:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReportStandbook reportStandbook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReportStandbook> page = reportStandbookService.findPage(new Page<ReportStandbook>(request, response), reportStandbook); 
		model.addAttribute("page", page);
		return "modules/akec/reportStandbookList";
	}

	@RequiresPermissions("akec:reportStandbook:view")
	@RequestMapping(value = "form")
	public String form(ReportStandbook reportStandbook, Model model) {
		model.addAttribute("reportStandbook", reportStandbook);
		return "modules/akec/reportStandbookForm";
	}

	@RequiresPermissions("akec:reportStandbook:edit")
	@RequestMapping(value = "save")
	public String save(ReportStandbook reportStandbook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reportStandbook)){
			return form(reportStandbook, model);
		}
		reportStandbookService.save(reportStandbook);
		addMessage(redirectAttributes, "保存报台信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/reportStandbook/?repage";
	}
	
	@RequiresPermissions("akec:reportStandbook:edit")
	@RequestMapping(value = "delete")
	public String delete(ReportStandbook reportStandbook, RedirectAttributes redirectAttributes) {
		reportStandbookService.delete(reportStandbook);
		addMessage(redirectAttributes, "删除报台信息成功");
		return "redirect:"+Global.getAdminPath()+"/akec/reportStandbook/?repage";
	}

}