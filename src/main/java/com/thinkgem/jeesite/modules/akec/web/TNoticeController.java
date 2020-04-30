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
import com.thinkgem.jeesite.modules.akec.entity.TNotice;
import com.thinkgem.jeesite.modules.akec.service.TNoticeService;

/**
 * 通知中心Controller
 * @author 通知中心
 * @version 2020-03-10
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/tNotice")
public class TNoticeController extends BaseController {

	@Autowired
	private TNoticeService tNoticeService;
	
	@ModelAttribute
	public TNotice get(@RequestParam(required=false) String id) {
		TNotice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tNoticeService.get(id);
		}
		if (entity == null){
			entity = new TNotice();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:tNotice:view")
	@RequestMapping(value = {"list", ""})
	public String list(TNotice tNotice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TNotice> page = tNoticeService.findPage(new Page<TNotice>(request, response), tNotice); 
		model.addAttribute("page", page);
		return "modules/akec/tNoticeList";
	}

	@RequiresPermissions("akec:tNotice:view")
	@RequestMapping(value = "form")
	public String form(TNotice tNotice, Model model) {
		model.addAttribute("tNotice", tNotice);
		return "modules/akec/tNoticeForm";
	}

	@RequiresPermissions("akec:tNotice:edit")
	@RequestMapping(value = "save")
	public String save(TNotice tNotice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tNotice)){
			return form(tNotice, model);
		}
		tNoticeService.save(tNotice);
		addMessage(redirectAttributes, "保存通知中心成功");
		return "redirect:"+Global.getAdminPath()+"/akec/tNotice/?repage";
	}
	
	@RequiresPermissions("akec:tNotice:edit")
	@RequestMapping(value = "delete")
	public String delete(TNotice tNotice, RedirectAttributes redirectAttributes) {
		tNoticeService.delete(tNotice);
		addMessage(redirectAttributes, "删除通知中心成功");
		return "redirect:"+Global.getAdminPath()+"/akec/tNotice/?repage";
	}

}