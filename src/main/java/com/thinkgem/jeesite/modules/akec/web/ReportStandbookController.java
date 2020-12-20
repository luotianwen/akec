/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.web;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.*;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.utils.excel.JxlsTemplate;
import com.thinkgem.jeesite.modules.akec.dao.BasedataDao;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookGradeDetailDao;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookProductDetailDao;
import com.thinkgem.jeesite.modules.akec.entity.*;
import com.thinkgem.jeesite.modules.akec.service.*;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 报台信息Controller
 *
 * @author 报台信息
 * @version 2020-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/akec/reportStandbook")
public class ReportStandbookController extends BaseController {

    @Autowired
    private ReportStandbookService reportStandbookService;

    @ModelAttribute
    public ReportStandbook get(@RequestParam(required = false) String id) {
        ReportStandbook entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = reportStandbookService.get(id);
        }
        if (entity == null) {
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

        Basedata b = new Basedata();
        b.setStatus("1");
        b.setBase(new BasedataType("518370AD648C42B79759D2B6DB04DF6C"));
        model.addAttribute("surgeryIds", basedataDao.findList(b));
        model.addAttribute("page", page);
        return "modules/akec/reportStandbookList";
    }
    @Autowired
    ReportStandbookProductDetailDao rspdao;
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  报台查询

     * @throws
     */
    @RequiresPermissions("akec:reportStandbook:view")
    @RequestMapping("/queryGetiReportStandbook")
    public String queryGetiReportStandbook(ReportStandbookProductDetail reportStandbookProductDetail, Model model){

        if(StringUtils.isEmpty(reportStandbookProductDetail.getIndividualcode())){
            model.addAttribute("reportStandbooks", null);
            return "modules/akec/reportStandbookGetiList";
        }

        List<ReportStandbookProductDetail> result= rspdao.findList(reportStandbookProductDetail);
        for (ReportStandbookProductDetail r2:result
        ) {
            AppUser appUser= appUserService.get(r2.getReport().getUserId());
            r2.setRemarks(appUser.getBaseReportName() + "-" + appUser.getName());
            r2.getReport().setSurgeryGrade(basedataDao.get(r2.getReport().getSurgeryId()).getParamName());
        }
        model.addAttribute("reportStandbooks", result);
        return "modules/akec/reportStandbookGetiList";
    }
    /**
     * 经销商报台
     *
     * @param reportStandbook
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("akec:reportStandbook:jxsview")
    @RequestMapping(value = {"jxslist"})
    public String jxslist(ReportStandbook reportStandbook, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        User u = UserUtils.getUser();
        if (u == null) {
            throw new Exception("手机号不存在");
        }
        AppUser a = appUserService.get(u);
        if (StringUtils.isEmpty(a.getId())) {
            throw new Exception("用户不存在");
        }
        reportStandbook.setUserId(a.getId());

        Page<ReportStandbook> page = reportStandbookService.findPage(new Page<ReportStandbook>(request, response), reportStandbook);
        Basedata b = new Basedata();
        b.setStatus("1");
        b.setBase(new BasedataType("518370AD648C42B79759D2B6DB04DF6C"));
        model.addAttribute("surgeryIds", basedataDao.findList(b));
        model.addAttribute("page", page);
        return "modules/akec/reportStandbookjxsList";
    }

    @RequiresPermissions("akec:reportStandbook:jxsview")
    @RequestMapping("/exportjxsListReportStandbook")
    public ReqResponse exportjxsListReportStandbook(HttpServletResponse response, ReportStandbook reportStandbook) throws Exception {
        User u = UserUtils.getUser();
        if (u == null) {
            throw new Exception("手机号不存在");
        }
        AppUser a = appUserService.get(u);
        if (StringUtils.isEmpty(a.getId())) {
            throw new Exception("用户不存在");
        }
        reportStandbook.setUserId(a.getId());
        ReqResponse r = new ReqResponse();
        List<DetailVo> result = reportStandbookService.excelList(reportStandbook);
        for (DetailVo v:result
             ) {
            v.setPriceAfVAT("");
        }
        if(result.size()>60000){
            throw new Exception("数据超60000太多");
        }
        try {
            String fileName = "报台" + DateUtils.getDate("yyyyMMdd") + ".xls";

            ServletOutputStream out = null;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("items", result);
            try {
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
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

    @Autowired
    private RegionService regionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private DealerService dealerService;

    @RequiresPermissions("akec:reportStandbook:jxsview")
    @RequestMapping(value = "jxsform")
    public String jxsform(ReportStandbook reportStandbook, Model model) {





        model.addAttribute("reportStandbook", reportStandbook);

        return "modules/akec/reportStandbookjxsForm";
    }
    /**
     * 导入 数据
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("akec:reportStandbook:jxsview")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(HttpServletResponse response, MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
        User u = UserUtils.getUser();
        if (u == null) {
            throw new Exception("手机号不存在");
        }
        AppUser au = appUserService.get(u);
        if (StringUtils.isEmpty(au.getId())) {
            throw new Exception("用户不存在");
        }
        List<ReportImportData> errorlist = Lists.newArrayList();

        int successNum = 0;
        int failureNum = 0;


        StringBuilder failureMsg = new StringBuilder();
        List<ReportImportData> list = ExcelUtils.importExcel(file, ReportImportData.class);
        List<ReportImportData> zqlist = Lists.newArrayList();
        for (ReportImportData r : list) {
            try {
                BeanValidators.validateWithException(validator, r);
                zqlist.add(r);
            } catch (Exception e) {
                r.setError(e.getMessage());
                errorlist.add(r);
            }
        }


        Map<String, List<ReportImportData>> map = new HashMap<>();


        for (ReportImportData r : zqlist) {
            List<ReportImportData> rds = map.get(r.getHospitalName());
            if (rds == null || rds.size() == 0) {
                rds = Lists.newArrayList();
            }
            rds.add(r);
            map.put(r.getHospitalName(), rds);
        }
        for (Map.Entry<String, List<ReportImportData>> entry : map.entrySet()) {
            int error=0;
            String dealerName = null;
            List<ReportImportData> mapValue = entry.getValue();
            ReportImportData rid = mapValue.get(0);
            ReportStandbook reportStandbook2 = new ReportStandbook();
            String type = au.getBaseReportId();
            if (reportStandbookService.AKEC_TYPE.indexOf("," + type + ",") >= 0) {
                type = "1";
            } else if (reportStandbookService.DEALER_TYPE.indexOf("," + type + ",") >= 0) {
                type = "2";
            }
            String dealerName2 = au.getDealerName();
            reportStandbook2.setBaseReportName(au.getBaseReportName());
            reportStandbook2.setDealerName2(dealerName2);
            reportStandbook2.setSurgeryGrade(rid.getSurgeryGrade());
            reportStandbook2.setUserId(au.getId());
            reportStandbook2.setOperateDate(rid.getOperateDate());
            reportStandbook2.setHospitalName(rid.getHospitalName());
            reportStandbook2.setProvinceName(rid.getProvinceName());
            reportStandbook2.setCityName(rid.getCityName());
            reportStandbook2.setDoctorName(rid.getDoctorName());
            reportStandbook2.setPatientSex(rid.getPatientSex());
            reportStandbook2.setPatientAge(rid.getPatientAge());
            Basedata  basedata2=new Basedata();
            basedata2.setParamName(rid.getSurgeryGrade());
            List<Basedata>  basedatas=   basedataDao.getByName(basedata2);
            if (basedatas == null|| basedatas.size() == 0) {
                rid.setError("类别不存在");
                errorlist.add(rid);
                continue;

            }
            reportStandbook2.setSurgeryId(basedatas.get(0).getId());
            reportStandbook2.setUserName(au.getName());
            reportStandbook2.setType(type);
            //市
            Region region = new Region();

            region.setName(rid.getProvinceName());
            List<Region> rs = regionService.findProList(region);
            if (rs == null || rs.size() == 0) {
                rid.setError("省不存在 请重新选择医院");
                errorlist.add(rid);
                continue;

            }
            reportStandbook2.setProvinceCode(rs.get(0).getCode());
            region = new Region();
            region.setParentCode(reportStandbook2.getProvinceCode());
            region.setName(rid.getCityName());
            rs = regionService.findProList(region);
            if (rs == null || rs.size() == 0) {
                rid.setError("市不存在 请重新选择医院");
                errorlist.add(rid);
                continue;

            }
            reportStandbook2.setCityCode(rs.get(0).getCode());
            region = new Region();
            region.setParentCode(reportStandbook2.getCityCode());
            region.setName(rid.getHospitalName());
            region.setStatus("1");
            String hospitalId = "";
            rs = regionService.findList(region);
            if (null == rs || rs.size() == 0) {
                Region region2 = new Region();
                region2.setStatus("1");
                region2.setName(rid.getHospitalName());
                region2.setParentCode(reportStandbook2.getCityCode());
                region2.setDegree("");
                region2.setLevel("3");
                region2.setSeqno("1");
                regionService.save(region2);
                hospitalId = region2.getId();
            } else {
                hospitalId = rs.get(0).getId();

            }
            reportStandbook2.setHospitalId(hospitalId);


            Set<String> verifySet = new HashSet<String>();
            List<ReportStandbookProductDetail> productList = new ArrayList<>();
            int unitCount = 0;
            for (ReportImportData rids : mapValue) {
                try {
                    Product product2 = new Product();
                    String scanCode = "";
                    String individualcode = "";
                    if (StringUtils.isNotEmpty(rids.getMaterialCode())) {
                        product2.setMaterialCode(rids.getMaterialCode());
                        scanCode = rids.getMaterialCode() + "," + rids.getIndivualcode();

                    } else {
                        String code = rids.getBarCode();
                        String valid = code.substring(34, code.length());
                        String f11 = valid.substring(9, 11);

                        if (f11.endsWith("21")) {
                            rids.setIndivualcode(valid.substring(0, 9) + valid.substring(11, valid.length()));
                        } else {
                            rids.setIndivualcode(valid);
                        }
                        scanCode = rids.getBarCode();
                        product2.setBarCode(code.substring(3, 12));

                    }
                    individualcode = rids.getIndivualcode();
                    List<Product> ps = productService.findList(product2);

                    if (null == ps || ps.size() == 0) {
                        rid.setError("系统中无此" + product2.getMaterialCode() + "或者 " + product2.getBarCode() + "产品");
                        errorlist.add(rid);
                        failureNum++;
                        error++;
                        continue;
                    }
                    product2 = ps.get(0);


                    ReportStandbookProductDetail product = new ReportStandbookProductDetail();
                    product.setProductId(product2.getId());
                    product.setScanCode(scanCode);
                    product.setIndividualcode(individualcode);
                    unitCount += Integer.parseInt(product2.getIsRecordUnit());

                    product.setIsRecordUnit(product2.getIsRecordUnit());
                    product.setIsVerifyIndividualcode(product2.getIsVerifyIndividualcode());

                    product2.setIntegral("1");
                    String isVerifyindivualcode = product2.getIsVerifyIndividualcode();
                    String indivualcode = rids.getIndivualcode();
                    product.setReport(reportStandbook2);
                    if ("1".equals(isVerifyindivualcode)) {
                        Product productVo = productService.get(product.getProductId());

                        product.setMaterialCode(productVo.getMaterialCode());
                        product.setMaterialDesc(productVo.getMaterialDesc());
                        product.setMaterialSpeDesc(productVo.getMaterialSpeDesc());

                        boolean putSuccess = verifySet.add(productVo.getCode() + indivualcode);
                        if (!putSuccess) {

                            rid.setError(productVo.getCode() + "产品不唯一");
                            errorlist.add(rid);
                            failureNum++;
                            error++;
                            continue;

                        }
                        Sellproduct sp = new Sellproduct();
                        sp.setProudctCode(productVo.getCode());
                        sp.setIndividualcode(indivualcode);
                        List<Sellproduct> sellProducts = sellproductService.findList(sp);
                        if (sellProducts == null || sellProducts.size() == 0) {
                            rid.setError(productVo.getCode() + "已售产品中未找到报台产品" + indivualcode);
                            errorlist.add(rid);
                            failureNum++;
                            error++;
                            continue;
                        }
                        Sellproduct sellProduct = sellProducts.get(0);

                        product.setPriceAfVAT(sellProduct.getPriceAfVAT());
                        product.setSeriesName(sellProduct.getSeries());
                        product.setDealerCode(sellProduct.getDealerCode());
                        product.setDealerName(sellProduct.getDealerName());
                        product.setComments(sellProduct.getComments().contains("无报台返利") ? "否" : "是");
                        product.setSaleType(sellProduct.getSaleType());


                        Dealer dv = new Dealer();

                        dv.setCode(sellProduct.getDealerCode());
                        List<Dealer> dealers = dealerService.findList(dv);

                        if (dealers != null) {
                            Dealer dealer = dealers.get(0);
                            String isRecordIntegral = dealer.getIsRecordIntegral();
                            if ("0".equals(isRecordIntegral)) {
                                product.setIntegral("0");
                            }
                        }
                        dealerName = sellProduct.getDealerName();


                        ReportStandbookProductDetail rd = new ReportStandbookProductDetail();
                        rd.setIndividualcode(indivualcode);
                        rd.setScanCode(productVo.getCode());
                        rd.setType(type);

                        int reportProductCount = reportStandbookProductDetailDao.countReprotProduct(rd);
                        if (reportProductCount > 0) {
                            rid.setError(productVo.getCode() + "产品已被报台" + indivualcode);
                            errorlist.add(rid);
                            failureNum++;
                            error++;
                            continue;

                        }
                        int historyReportProductCount = reportStandbookProductDetailDao.countHistoryReprotProduct(rd);
                        if (historyReportProductCount > 0) {
                            rid.setError(productVo.getCode() + "产品已被报台" + indivualcode);
                            errorlist.add(rid);
                            failureNum++;
                            error++;
                            continue;

                        }


                    }
                    productList.add(product);
                    successNum++;
                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>二维码 " + rids.getBarCode() + "或者物料号" + rids.getMaterialCode());
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    failureMsg.append("<br/>二维码 " + rids.getBarCode() + "或者物料号" + rids.getMaterialCode() + " 导入失败：" + ex.getMessage());
                }
            }
            reportStandbook2.setReportStandbookProductDetailList(productList);
            reportStandbook2.setUnitCount(unitCount + "");
            reportStandbook2.setDealerName(dealerName);
            reportStandbook2.setStatus("1");
            reportStandbook2.setCreateDate(new Date());
            if (error !=mapValue.size()) {
                reportStandbookService.save2(reportStandbook2);
            }
        }


        if (failureNum > 0) {
            failureMsg.insert(0, "，失败 " + failureNum + " 条，导入信息如下："+ failureMsg);
        }

        addMessage(redirectAttributes, "已成功导入 " + successNum + " 条" );

        if (errorlist != null && errorlist.size() > 0) {
            ExcelUtils.exportExcel(errorlist, "报台错误数据", "报台错误数据", ReportImportData.class, "报台错误数据", response);
            return null;
        }
        return "redirect:" + adminPath + "/akec/reportStandbook/jxslist";
    }

    /**
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("akec:reportStandbook:jxsview")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "报台导入模板.xlsx";
            List<ReportImportData> list = Lists.newArrayList();
            ReportImportData a = new ReportImportData();
            a.setBarCode("0106943735160325172205241317052521A00042626A09");
            a.setCityName("菏泽市");
            a.setDoctorName("王运岳");
            a.setHospitalName("菏泽惠慈医院");
            a.setIndivualcode("T00029707A17");
            a.setMaterialCode("2323-5042");
            a.setOperateDate(new Date());
            a.setPatientAge("29");
            a.setPatientSex("1");
            a.setProvinceName("山东省");
            a.setSurgeryGrade("髋关节双侧");
            list.add(a);


            ExcelUtils.exportExcel(list, "报台数据", "报台数据", ReportImportData.class, "报台数据", response);

            // new ExportExcel("报台数据", ReportImportData.class, 2).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/akec/reportStandbook/jxslist";
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
    @RequiresPermissions("akec:reportStandbook:report")
    @RequestMapping("/exportListReportStandbookreport")
    public ReqResponse exportListReportStandbookreport(HttpServletResponse response, ReportStandbook reportStandbook) throws Exception {
        ReqResponse r = new ReqResponse();
        List<DetailVo> result = reportStandbookService.excelList(reportStandbook);
        if(result.size()>60000){
            throw new Exception("数据超60000太多");
        }
        try {
            String fileName = "报台" + DateUtils.getDate("yyyyMMdd") + ".xls";

            ServletOutputStream out = null;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("items", result);
            try {
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
                response.setHeader("Pragma", "public");
                response.setContentType("application/x-excel;charset=UTF-8");
                out = response.getOutputStream();
                JxlsTemplate.processTemplate("/reportStandbookreport.xls", out, params);
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
    @RequestMapping("/exportListReportStandbook2")
    public ReqResponse exportListReportStandbook2(HttpServletResponse response, ReportStandbook reportStandbook) throws Exception {
        ReqResponse r = new ReqResponse();
        List<DetailVo> result = reportStandbookService.excelList(reportStandbook);
         if(result.size()>60000){
             throw new Exception("数据超60000太多");
         }
        try {
            String fileName = "报台" + DateUtils.getDate("yyyyMMdd") + ".xls";

            ServletOutputStream out = null;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("items", result);
            try {
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
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
    @RequestMapping("/exportListReportStandbook")
    public ReqResponse exportListReportStandbook(HttpServletResponse response, ReportStandbook reportStandbook) {
        ReqResponse r = new ReqResponse();
        List<ReportStandbook> result = reportStandbookService.findAllList(reportStandbook);

        for (ReportStandbook r2 : result
        ) {
            r2.setPatientSex(r2.getPatientSex().equals("1") ? "男" : "女");
            r2.setStatus(r2.getStatus().equals("1") ? "启用" : "禁用");
            r2.setType(r2.getType().equals("2") ? "经销商报台" : "直属/子公司人员报台");
            r2.setUser(appUserService.get(r2.getUserId()));
            r2.setSurgeryGrade(basedataDao.get(r2.getSurgeryId()).getParamName());
            StringBuffer sb = new StringBuffer();
            List<ReportStandbookGradeDetail> ss = reportStandbookGradeDetailDao.findList(new ReportStandbookGradeDetail(r2));
            for (ReportStandbookGradeDetail rs : ss
            ) {
                sb.append(rs.getGradeName() + ":" + rs.getGrade() + ",");
            }

            r2.setRemarks(sb.toString());

            List<ReportStandbookProductDetail> ps = reportStandbookProductDetailDao.findList(new ReportStandbookProductDetail(r2));
            for (ReportStandbookProductDetail rd : ps
            ) {
                rd.setIntegral(rd.getIntegral().equals("1") ? "是" : "否");
                if (StringUtils.isNotEmpty(rd.getIndividualcode())) {
                    Sellproduct sellproduct = new Sellproduct();
                    sellproduct.setIndividualcode(rd.getIndividualcode());
                    sellproduct.setProudctCode(rd.getProduct().getCode());

                    List<Sellproduct> sss = sellproductService.getByIndividualcode(sellproduct);
                    if (sss == null || sss.size() == 0) {
                        rd.setSellproduct(new Sellproduct());
                    } else {
                        Sellproduct sellproduct2 = sss.get(0);
                        if (sellproduct2 != null) {
                            if (StringUtils.isNotEmpty(sellproduct2.getComments())) {
                                sellproduct2.setComments(sellproduct2.getComments().contains("无报台返利") ? "否" : "是");
                            } else {
                                sellproduct2.setComments("是");
                            }
                            rd.setSellproduct(sellproduct2);
                        } else {
                            rd.setSellproduct(new Sellproduct());
                        }
                    }
                }

            }
            r2.setReportStandbookProductDetailList(ps);
        }

        try {
            String fileName = "报台" + DateUtils.getDate("yyyyMMdd") + ".xls";

            ServletOutputStream out = null;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("items", result);
            try {
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
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

        Basedata b = new Basedata();
        b.setStatus("1");
        b.setBase(new BasedataType("518370AD648C42B79759D2B6DB04DF6F"));
        model.addAttribute("patientSexs", basedataDao.findList(b));
        b.setBase(new BasedataType("518370AD648C42B79759D2B6DB04DF6C"));
        model.addAttribute("surgeryIds", basedataDao.findList(b));

        reportStandbook.setSurgeryGrade(basedataDao.get(reportStandbook.getSurgeryId()).getParamName());

        List<ReportStandbookProductDetail> ps = reportStandbook.getReportStandbookProductDetailList();
        for (ReportStandbookProductDetail rd : ps
        ) {
            Sellproduct sellproduct = new Sellproduct();
            sellproduct.setIndividualcode(rd.getIndividualcode());
            sellproduct.setProudctCode(rd.getProduct().getCode());
            List<Sellproduct> ss = sellproductService.getByIndividualcode(sellproduct);
            if (ss == null || ss.size() == 0) {

            } else {
                Sellproduct sellproduct2 = ss.get(0);
                if (sellproduct2 != null) {
                    if (StringUtils.isNotEmpty(sellproduct2.getComments())) {
                        sellproduct2.setComments(sellproduct2.getComments().contains("无报台返利") ? "否" : "是");
                    } else {
                        sellproduct2.setComments("是");
                    }
                    rd.setSellproduct(sellproduct2);
                }
            }

        }
        reportStandbook.setUser(appUserService.get(reportStandbook.getUserId()));
        model.addAttribute("reportStandbook", reportStandbook);

        return "modules/akec/reportStandbookForm";
    }


    @RequiresPermissions("akec:reportStandbook:edit")
    @RequestMapping(value = "save")
    public String save(ReportStandbook reportStandbook, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, reportStandbook)) {
            return form(reportStandbook, model);
        }
        reportStandbookService.save(reportStandbook);
        addMessage(redirectAttributes, "保存报台信息成功");
        return "redirect:" + Global.getAdminPath() + "/akec/reportStandbook/?repage";
    }

    @RequiresPermissions("akec:reportStandbook:edit")
    @RequestMapping(value = "delete")
    public String delete(ReportStandbook reportStandbook, RedirectAttributes redirectAttributes) {
        reportStandbookService.delete(reportStandbook);
        addMessage(redirectAttributes, "删除报台信息成功");
        return "redirect:" + Global.getAdminPath() + "/akec/reportStandbook/?repage";
    }
    @RequiresPermissions("akec:reportStandbook:edit")
    @RequestMapping(value = "deletegeti")
    public String deletegeti(ReportStandbookProductDetail reportStandbook, RedirectAttributes redirectAttributes) {
        reportStandbookService.deletegeti(reportStandbook);
        addMessage(redirectAttributes, "删除报台明细信息成功");
        return "redirect:" + Global.getAdminPath() + "/akec/reportStandbook/queryGetiReportStandbook?repage";
    }
}