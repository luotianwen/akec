/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.web;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.excel.JxlsTemplate;
import com.thinkgem.jeesite.modules.akec.dao.BasedataDao;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookGradeDetailDao;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookProductDetailDao;
import com.thinkgem.jeesite.modules.akec.entity.*;
import com.thinkgem.jeesite.modules.akec.service.AppUserService;
import com.thinkgem.jeesite.modules.akec.service.BasedataTypeService;
import com.thinkgem.jeesite.modules.akec.service.SellproductService;
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
import com.thinkgem.jeesite.modules.akec.service.ReportStandbookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private BasedataDao basedataDao;
	@RequiresPermissions("akec:reportStandbook:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReportStandbook reportStandbook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReportStandbook> page = reportStandbookService.findPage(new Page<ReportStandbook>(request, response), reportStandbook);
		List<ReportStandbook> rs=page.getList();
		for (ReportStandbook r:rs
		) {
			 r.setSurgeryGrade(basedataDao.get(r.getSurgeryId()).getParamName());
		}
		Basedata b= new Basedata();
		b.setStatus("1");
		b.setBase(new BasedataType("518370AD648C42B79759D2B6DB04DF6C"));
		model.addAttribute("surgeryIds", basedataDao.findList(b));
		model.addAttribute("page", page);
		return "modules/akec/reportStandbookList";
	}

	@Autowired
	private BasedataTypeService basedataTypeService;
	@Autowired
	private SellproductService sellproductService;
	@Autowired
	private AppUserService appUserService;

	@Autowired
	private ReportStandbookGradeDetailDao reportStandbookGradeDetailDao;

	@Autowired
	private ReportStandbookProductDetailDao reportStandbookProductDetailDao;
	@RequiresPermissions("akec:reportStandbook:view")
	 @RequestMapping("/exportListReportStandbook")
    public ReqResponse exportListReportStandbook(HttpServletResponse response,ReportStandbook reportStandbook){
        ReqResponse r=new ReqResponse();
        List<ReportStandbook> result = reportStandbookService.findList(reportStandbook);

        for (ReportStandbook r2:result
        ) {
            r2.setPatientSex(r2.getPatientSex().equals("1")?"男":"女");
            r2.setType(r2.getType().equals("2")?"经销商报台":"直属/子公司人员报台");
            r2.setUser(appUserService.get(r2.getUserId()));
            r2.setSurgeryGrade(basedataDao.get(r2.getSurgeryId()).getParamName());
            StringBuffer sb=new StringBuffer();
            List<ReportStandbookGradeDetail> ss= reportStandbookGradeDetailDao.findList(new ReportStandbookGradeDetail(r2));
            for (ReportStandbookGradeDetail rs:ss
                 ) {
                sb.append(rs.getGradeName()+":"+rs.getGrade()+",");
            }

           r2.setRemarks(sb.toString());

            List<ReportStandbookProductDetail> ps= reportStandbookProductDetailDao.findList(new ReportStandbookProductDetail(r2));
            for (ReportStandbookProductDetail rd:ps
                 ) {
                rd.setIntegral(rd.getIntegral().equals("1")?"是":"否");
                if(StringUtils.isNotEmpty(rd.getIndividualcode())) {
                    Sellproduct sellproduct = new Sellproduct();
                    sellproduct.setIndividualcode(rd.getIndividualcode());
                    sellproduct.setProudctCode(rd.getProduct().getCode());
                    rd.setSellproduct(sellproductService.getByIndividualcode(sellproduct).get(0));
                }

            }
            r2.setReportStandbookProductDetailList(ps);
        }

        try {
            String fileName = "报台"+ DateUtils.getDate("yyyyMMdd")+".xls";

            ServletOutputStream out = null;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("items", result);
            try {
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Content-Disposition", "attachment; filename="+ Encodes.urlEncode(fileName));
                response.setHeader("Pragma", "public");
                response.setContentType("application/x-excel;charset=UTF-8");
                out = response.getOutputStream();
                JxlsTemplate.processTemplate("/reportStandbook.xls", out, params);
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
	@RequiresPermissions("akec:reportStandbook:view")
	@RequestMapping(value = "form")
	public String form(ReportStandbook reportStandbook, Model model) {
		model.addAttribute("reportStandbook", reportStandbook);

		Basedata b= new Basedata();
		b.setStatus("1");
		b.setBase(new BasedataType("518370AD648C42B79759D2B6DB04DF6F"));
		model.addAttribute("patientSexs", basedataDao.findList(b));
		b.setBase(new BasedataType("518370AD648C42B79759D2B6DB04DF6C"));
		model.addAttribute("surgeryIds", basedataDao.findList(b));

		reportStandbook.setSurgeryGrade(basedataDao.get(reportStandbook.getSurgeryId()).getParamName());

		List<ReportStandbookProductDetail> ps=reportStandbook.getReportStandbookProductDetailList();
		for (ReportStandbookProductDetail rd:ps
		) {
			rd.setIntegral(rd.getIntegral().equals("1")?"是":"否");
			if(StringUtils.isNotEmpty(rd.getIndividualcode())) {
				Sellproduct sellproduct = new Sellproduct();
				sellproduct.setIndividualcode(rd.getIndividualcode());
				sellproduct.setProudctCode(rd.getProduct().getCode());
				rd.setSellproduct(sellproductService.getByIndividualcode(sellproduct).get(0));
			}

		}
		reportStandbook.setUser(appUserService.get(reportStandbook.getUserId()));
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