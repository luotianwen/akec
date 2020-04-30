/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.web;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.excel.JxlsTemplate;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookOperationDetailDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookOperationDetail;
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
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookOperation;
import com.thinkgem.jeesite.modules.akec.service.ReportStandbookOperationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手术报数Controller
 * @author 手术报数
 * @version 2020-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/reportStandbookOperation")
public class ReportStandbookOperationController extends BaseController {

	@Autowired
	private ReportStandbookOperationService reportStandbookOperationService;
	
	@ModelAttribute
	public ReportStandbookOperation get(@RequestParam(required=false) String id) {
		ReportStandbookOperation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reportStandbookOperationService.get(id);
		}
		if (entity == null){
			entity = new ReportStandbookOperation();
		}
		return entity;
	}
	
	@RequiresPermissions("akec:reportStandbookOperation:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReportStandbookOperation reportStandbookOperation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReportStandbookOperation> page = reportStandbookOperationService.findPage(new Page<ReportStandbookOperation>(request, response), reportStandbookOperation); 
		model.addAttribute("page", page);
		return "modules/akec/reportStandbookOperationList";
	}

	@RequiresPermissions("akec:reportStandbookOperation:view")
	@RequestMapping(value = "form")
	public String form(ReportStandbookOperation reportStandbookOperation, Model model) {
		model.addAttribute("reportStandbookOperation", reportStandbookOperation);
		return "modules/akec/reportStandbookOperationForm";
	}

	@RequiresPermissions("akec:reportStandbookOperation:edit")
	@RequestMapping(value = "save")
	public String save(ReportStandbookOperation reportStandbookOperation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reportStandbookOperation)){
			return form(reportStandbookOperation, model);
		}
		reportStandbookOperationService.save(reportStandbookOperation);
		addMessage(redirectAttributes, "保存手术报数成功");
		return "redirect:"+Global.getAdminPath()+"/akec/reportStandbookOperation/?repage";
	}
	@Autowired
	private ReportStandbookOperationDetailDao reportStandbookOperationDetailDao;
	@RequiresPermissions("akec:reportStandbookOperation:view")
	/**
	 *

	 * @Description: TODO(这里用一句话描述这个方法的作用)  导出报数

	 * @throws
	 */
	@RequestMapping("/exportReportStandbookOperation")
	public ReqResponse exportReportStandbookOperation(HttpServletResponse response,ReportStandbookOperation reportStandbook){
		ReqResponse r=new ReqResponse();
		List<ReportStandbookOperation> result = reportStandbookOperationService.findList(reportStandbook);

		List<ReportStandbookOperationDetail> ds= Lists.newArrayList();
		for (ReportStandbookOperation r2:result
		) {
			r2.setAk(r2.getAk().equals("1")?"是":"否");
			List<ReportStandbookOperationDetail>ss= reportStandbookOperationDetailDao.findList(new ReportStandbookOperationDetail(r2));
			for (ReportStandbookOperationDetail s:ss
			) {
				s.setOperate(r2);
				ds.add(s);
			}
		}

		try {
			String fileName = "报数"+ DateUtils.getDate("yyyyMMdd")+".xls";

			ServletOutputStream out = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("items", ds);
			try {
				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Content-Disposition", "attachment; filename="+ Encodes.urlEncode(fileName));
				response.setHeader("Pragma", "public");
				response.setContentType("application/x-excel;charset=UTF-8");
				out = response.getOutputStream();
				JxlsTemplate.processTemplate("/reportOperation.xls", out, params);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} catch (Exception e) {

		}

		return r;
	}
	@RequiresPermissions("akec:reportStandbookOperation:edit")
	@RequestMapping(value = "delete")
	public String delete(ReportStandbookOperation reportStandbookOperation, RedirectAttributes redirectAttributes) {
		reportStandbookOperationService.delete(reportStandbookOperation);
		addMessage(redirectAttributes, "删除手术报数成功");
		return "redirect:"+Global.getAdminPath()+"/akec/reportStandbookOperation/?repage";
	}

}