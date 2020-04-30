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
import com.thinkgem.jeesite.modules.akec.entity.ReportDStandbook;
import com.thinkgem.jeesite.modules.akec.service.ReportDStandbookService;

/**
 * 待提交报台Controller
 * @author 待提交报台
 * @version 2020-03-26
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/reportDStandbook")
public class ReportDStandbookController extends BaseController {

	@Autowired
	private ReportDStandbookService reportDStandbookService;
	
	@ModelAttribute
	public ReportDStandbook get(@RequestParam(required=false) String id) {
		ReportDStandbook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reportDStandbookService.get(id);
		}
		if (entity == null){
			entity = new ReportDStandbook();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:reportDStandbook:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReportDStandbook reportDStandbook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReportDStandbook> page = reportDStandbookService.findPage(new Page<ReportDStandbook>(request, response), reportDStandbook); 
		model.addAttribute("page", page);
		return "modules/akec/reportDStandbookList";
	}

	@RequiresPermissions("akec:reportDStandbook:view")
	@RequestMapping(value = "form")
	public String form(ReportDStandbook reportDStandbook, Model model) {
		model.addAttribute("reportDStandbook", reportDStandbook);
		return "modules/akec/reportDStandbookForm";
	}

	@RequiresPermissions("akec:reportDStandbook:edit")
	@RequestMapping(value = "save")
	public String save(ReportDStandbook reportDStandbook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reportDStandbook)){
			return form(reportDStandbook, model);
		}
		reportDStandbookService.save(reportDStandbook);
		addMessage(redirectAttributes, "保存待提交报台成功");
		return "redirect:"+Global.getAdminPath()+"/akec/reportDStandbook/?repage";
	}
	
	@RequiresPermissions("akec:reportDStandbook:edit")
	@RequestMapping(value = "delete")
	public String delete(ReportDStandbook reportDStandbook, RedirectAttributes redirectAttributes) {
		reportDStandbookService.delete(reportDStandbook);
		addMessage(redirectAttributes, "删除待提交报台成功");
		return "redirect:"+Global.getAdminPath()+"/akec/reportDStandbook/?repage";
	}

}