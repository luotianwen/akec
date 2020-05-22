package com.thinkgem.jeesite.modules.akec.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.*;
import com.thinkgem.jeesite.common.utils.excel.JxlsTemplate;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.akec.dao.*;
import com.thinkgem.jeesite.modules.akec.entity.*;
import com.thinkgem.jeesite.modules.akec.service.*;
import com.thinkgem.jeesite.modules.akec.utils.SmsUtils;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/akec")
public class AppController extends BaseController {
    Logger log = LogManager.getLogger(AppController.class);
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private DoctorService doctorService;
    @RequestMapping(value = "reg")
    @ResponseBody
    public ReqResponse save(AppUser appUser) {
        ReqResponse r=new ReqResponse();
        try {
            AppUser a=new AppUser();
            a.setAccount(appUser.getAccount());
            List l=appUserService.findList(a);
            if(null==l||l.size()==0) {
                //appUser.setPass(MD5Util.GetMD5Code(appUser.getPass()));
                appUser.setCreateDate(new Date());
                if(appUser.getCreateType().equals("101")){

                            appUser.setBaseReportId("18370AD648C42B79759D2B6DB04DF6BB");
                }
                else if(appUser.getCreateType().equals("102")){
                    appUser.setBaseReportId("B18370AD648C42B79759D2B6DB04DF6B");
                }
                else if(appUser.getCreateType().equals("100")){
                    appUser.setBaseReportId("1EDE364642A542428CE115CEB82B8132");
                }
                else if(appUser.getCreateType().equals("104")){
                    appUser.setBaseReportId("B18370AD648C42B79759D2B6DB04DF6D");
                }
                if(!appUser.getCreateType().equals("103")){
                    appUser.setAuditStatus(UserConstant.USER_AUDIT_STATUS_NO);
                    appUser.setCreateType(UserConstant.USER_CREATE_TYPE_APP);
                    appUserService.save(appUser);
                }
                else{
                    appUser.setCreateType(UserConstant.USER_CREATE_TYPE_DEALER);
                    appUser.setAuditStatus(UserConstant.USER_AUDIT_STATUS_YES);
                    appUserService.save(appUser);
                    appUserService.modifyUserReportType(appUser);
                }
            }else{
                r.setCode(1);
                r.setMsg("账号已存在!");
            }
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("注册失败!");
        }
        return r;
    }
    @RequestMapping("/login")
    @ResponseBody
    public ReqResponse login(AppUser queryUser ){
        ReqResponse r=new ReqResponse();
         log.error("------------请求账号-----------"+queryUser.getAccount());
        AppUser userInfo = appUserService.queryUserInfo(queryUser);
        if(userInfo == null){
            log.error("------------用户不存在-----------"+queryUser.getAccount());
            r.setCode(1);
            r.setMsg("用户不存在");
            return r;
        }else{
            String pass = userInfo.getPass();
            if(!pass.equals(queryUser.getPass())){
                log.error("------------用户密码错误-----------"+queryUser.getAccount());
                r.setCode(1);
                r.setMsg("用户密码错误");
                return r;
            }
        }
        if(userInfo.getAuditStatus().equals("0")){
            r.setCode(1);
            r.setMsg("您的用户正在审核中…");
            return r;
        }
        else if(userInfo.getAuditStatus().equals("2")){
            r.setCode(1);
            r.setMsg("您的用户不存在或者已经注销了…");
            return r;
        }
        log.error("------------请求成功-----------"+queryUser.getAccount());
        userInfo.setPass("");
        r.setData(userInfo);
        return r;
    }
    /**
     * 注销用户
     * @return
     */
    @RequestMapping("/outUser")
    @ResponseBody
    public ReqResponse outUser(AppUser queryUser){
        ReqResponse r=new ReqResponse();
        try {
            String phone=queryUser.getAccount();
            String p= (String) CacheUtils.get("phone",phone);
            if(StringUtils.isEmpty(p)){
                r.setMsg("验证码不存在或者已失效");
                r.setCode(1);
                return r;
            }
            if(!p.equals(queryUser.getRemarks())){
                r.setMsg("验证码错误");
                r.setCode(1);
                return r;
            }

            boolean modifySuccess = appUserService.outUser(queryUser);
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("修改失败");
        }
        return r;
    }
    /**
     * 查询医生
     * @param doctor
     * @return
     */
    @RequestMapping("/queryListUserDoctor")
    @ResponseBody
    public ReqResponse queryListUserDoctor(Doctor doctor ){
        ReqResponse r=new ReqResponse();
        List<Doctor> as= doctorService.findList(doctor);
        for (Doctor d:as
             ) {
            d.setHospitalId(DictUtils.getDictLabel(d.getTechnical(),"technical","1"));
        }
        r.setData(as);
        return r;
    }
    /**
     * 删除医生
     * @param doctor
     * @return
     */
    @RequestMapping("/deleteUserDoctor")
    @ResponseBody
    public ReqResponse deleteUserDoctor(Doctor doctor ){
        ReqResponse r=new ReqResponse();
        try {
            doctorService.delete(doctor);
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("删除失败");
        }

        return r;
    }
    /**
     * 添加医生
     * @param doctor
     * @return
     */
    @RequestMapping("/saveUserDoctor")
    @ResponseBody
    public ReqResponse saveUserDoctor(Doctor doctor ){
        ReqResponse r=new ReqResponse();
        try {

            doctorService.save(doctor);
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("操作失败");
        }

        return r;
    }
    /**
     * 查询业务员
     * @param queryUser
     * @return
     */
    @RequestMapping("/queryListUserBelongDealer")
    @ResponseBody
    public ReqResponse queryListUserBelongDealer(AppUser queryUser ){
        ReqResponse r=new ReqResponse();
        List<AppUser> as= appUserService.queryListUserBelongDealer(queryUser);
        r.setData(as);
        return r;
    }
    /**
     * 删除业务员
     * @param
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public ReqResponse deleteUser(AppUser queryUser ){
        ReqResponse r=new ReqResponse();
        try {
            appUserService.delete(queryUser);
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("删除失败");
        }
        return r;
    }
    /**
     * @Description: (这里用一句话描述这个方法的作用)  修改用户
     * @throws
     */
    @RequestMapping("/modifyUser")
    @ResponseBody
    public ReqResponse modifyUser( AppUser queryUser){
        ReqResponse r=new ReqResponse();

        try {
            boolean modifySuccess = appUserService.modifyUser(queryUser);
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("修改失败");
        }
        return r;
    }

    /**
     *
     * @Title: modifyUserPass
     * @Description: (这里用一句话描述这个方法的作用)  修改用户密码

     * @return Map<String,Object>    返回类型
     * @throws
     */
    @RequestMapping("/modifyUserPass")
    @ResponseBody
    public ReqResponse modifyUserPass(AppUser queryUser){
        ReqResponse r=new ReqResponse();
        AppUser queryUser2=appUserService.get(queryUser.getId());

        try {
            if(queryUser2.getPass().equals(queryUser.getAccount())) {
                boolean modifySuccess = appUserService.modifyUserPass(queryUser);
            }else{
                r.setCode(1);
                r.setMsg("原密码不对");
            }
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("修改失败");
        }
        return  r;
    }



    /**
     *
     * @Title: queryResultKnowledgeKindList
     * @Description: (这里用一句话描述这个方法的作用)  查询知识分类
     * @throws
     */
    @RequestMapping("/queryKnowledgeKindList")
    @ResponseBody
    public ReqResponse queryKnowledgeKindList(KnowlegerKind knowlegerKind){
        ReqResponse r=new ReqResponse();
        List<KnowlegerKind> knowledgeKindList = knowlegerKindService.findList(new KnowlegerKind());
        r.setData(knowledgeKindList);
        return  r;
    }
    /**
     * @Description: (这里用一句话描述这个方法的作用)  查询知识内容
     */
    @RequestMapping("/queryKnowledgeContentList")
    @ResponseBody

    public ReqResponse queryKnowledgeContentList(KnowlegerKind knowlegerKind){
        ReqResponse r=new ReqResponse();
        KnowlegerKind kkd=knowlegerKindService.get(knowlegerKind.getId());
        List<Studycenter> studycenterList=kkd.getStudycenterList();
        if(StringUtils.isNotEmpty(knowlegerKind.getName())){
            studycenterList=Lists.newArrayList();
            for (Studycenter sc:studycenterList
                 ) {
                if(sc.getTitle().contains(knowlegerKind.getName())){
                    studycenterList.add(sc);
                }
            }
        }
        r.setData(studycenterList);
        return r;
    }
    @Autowired
    private KnowlegerKindService knowlegerKindService;
    @Autowired
    private TNoticeService tNoticeService;
    /**
     *
     * @Title: queryNoticeUnread
     * @Description: TODO(这里用一句话描述这个方法的作用)  查询未读通知

     */
    @RequestMapping("/queryListUnreadNotice")
    @ResponseBody

    public  ReqResponse queryListUnreadNotice(TNotice tNotice){
        ReqResponse r=new ReqResponse();
        List<TNotice> ts =tNoticeService.findUnreadList(tNotice);
        r.setData(ts);
        return r;
    }


    /**
     *
     * @Title: deleteNoticeUserRead
     * @Description: TODO(这里用一句话描述这个方法的作用)  删除用户已读通知

     */
    @RequestMapping("/deleteNoticeUserRead")
    @ResponseBody

    public  ReqResponse deleteNoticeUserRead(TNotice tNotice){
        ReqResponse r=new ReqResponse();
        tNoticeService.deleteNoticeUserRead(tNotice);
        return r;
    }


    @Autowired
    private ReportStandbookGradeDetailDao reportStandbookGradeDetailDao;

    @Autowired
    private ReportStandbookProductDetailDao reportStandbookProductDetailDao;
    @Autowired
    private ReportStandbookService reportStandbookService;
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  删除查询

     * @throws
     */
    @RequestMapping("/delReportStandbook")
    @ResponseBody
    public ReqResponse delReportStandbook(ReportStandbook reportStandbook){
        ReqResponse r=new ReqResponse();
        reportStandbookService.delete(reportStandbook);
        return r;
    }
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  报台查询

     * @throws
     */
    @RequestMapping("/queryListReportStandbook")
    @ResponseBody
    public ReqResponse queryListReportStandbook(ReportStandbook reportStandbook){
        ReqResponse r=new ReqResponse();
        List<ReportStandbook> result = reportStandbookService.queryListReportStandbook(reportStandbook);

        for (ReportStandbook r2:result
             ) {
            if(DateUtils.pastHour(r2.getCreateDate())>24){
                r2.setShow(false);
            }
            else{
                r2.setShow(true);
            }
             r2.setSurgeryGrade(basedataDao.get(r2.getSurgeryId()).getParamName());
        }
        r.setData(result);
        return r;
    }
    @Autowired
    private SellproductService sellproductService;
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  导出报台查询

     * @throws
     */
    @RequestMapping("/exportListReportStandbook")
    public ReqResponse exportListReportStandbook(HttpServletResponse response,ReportStandbook reportStandbook){
        ReqResponse r=new ReqResponse();
        List<DetailVo> result = reportStandbookService.excelList(reportStandbook);

        try {
            String fileName =  DateUtils.getDate("yyyyMMdd")+".xls";

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
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  报台查询

     * @throws
     */
    @RequestMapping("/getReportStandbook")
    @ResponseBody
    public ReqResponse getReportStandbook(ReportStandbook reportStandbook){
        ReqResponse r=new ReqResponse();
        ReportStandbook reportStandbook2= reportStandbookService.get(reportStandbook.getId());
        r.setData(reportStandbook2);
        return r;
    }

    /**
     * @throws Exception
        保存报台

     * @throws
     */
    @RequestMapping("/saveReportStandbook")
    @ResponseBody
    public ReqResponse saveReportStandbook(ReportStandbook reportStandbook) {
        //ReportStandbook reportStandbook= JSON.parseObject(datas,ReportStandbook.class);
        ReqResponse r=reportStandbookService.saveReportStandbook(reportStandbook);
        return r;
    }
    @Autowired
    private BasedataDao basedataDao;
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  系统参数　baseTypeName=手术类别

     * @throws

    @RequestMapping("/queryBaseData")
    @ResponseBody
    public ReqResponse queryBaseData(BasedataType basedata){
        ReqResponse r=new ReqResponse();
        List<BasedataType> result = basedataTypeService.findList(basedata);
        for (BasedataType r2:result
        ) {
            r2.setBasedataList(basedataDao.findList(new Basedata(r2)));
        }
        r.setData(result);
        return r;
    } */

    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  系统参数　baseTypeName=手术类别

     * @throws
     */
    @RequestMapping("/queryBaseData")
    @ResponseBody
    public ReqResponse queryBaseData(String type ){
        ReqResponse r=new ReqResponse();
        Map map=new HashMap();
        String []cs=type.split(",");
        for (String c:cs
        ) {
            BasedataType basedata=new BasedataType();
            basedata.setBaseTypeName(c);
            List<BasedataType> result = basedataTypeService.findList(basedata);
            for (BasedataType r2:result
            ) {
                Basedata b= new Basedata(r2);
                b.setStatus("1");
                r2.setBasedataList(basedataDao.findList(b));
            }
            map.put(c, result);
        }
        r.setData(map);
        return r;
    }


    @Autowired
    private BasedataTypeService basedataTypeService;


    /**
     * 查询数据字典
     * @param type
     * @return
     */
    @RequestMapping("/queryDict")
    @ResponseBody
    public ReqResponse queryDict(String type ){
        ReqResponse r=new ReqResponse();
         Map map=new HashMap();
         String []cs=type.split(",");
        for (String c:cs
             ) {
            map.put(c, DictUtils.getDictList(c));
        }
        r.setData(map);
        return r;
    }

    /**
     * 查询省

     * @return
     */
    @RequestMapping("/queryProvince")
    @ResponseBody
    public ReqResponse queryProvince(){
        ReqResponse r=new ReqResponse();
        List<Provice> ps = (List<Provice>) CacheUtils.get("queryProvince", "queryProvince");
        if(ps!=null){
            r.setData(ps);
            return r;
        }
         ps=new ArrayList<>();
        Region rr= new Region();
        rr.setLevel("1");


        List<Region> rs=regionService.queryProvince(rr);
        for (Region rd:rs
             ) {
            Provice p=new Provice();
            p.setLabel(rd.getName());
            p.setValue(rd.getCode());
            rr.setLevel("2");
            rr.setParentCode(rd.getCode());
            List<Provice.ChildrenBean> children=Lists.newArrayList();
            List<Region> rs2=regionService.queryProvince(rr);
            for (Region rd2:rs2
            ) {
                Provice.ChildrenBean pc=new Provice.ChildrenBean();
                pc.setLabel(rd2.getName());
                pc.setValue(rd2.getCode());
                children.add(pc);
            }
            p.setChildren(children);
            ps.add(p);
         }

        r.setData(ps);
        CacheUtils.put("queryProvince", "queryProvince",ps);
        return r;
    }
    /**
     * 查询省

     * @return
     */
    @RequestMapping("/queryHosptail")
    @ResponseBody
    public ReqResponse queryHosptail(Region region){
        ReqResponse r=new ReqResponse();
        r.setData(regionService.queryHosptail(region));
        return r;
    }

    @Autowired
    private RegionService regionService;


    /**
     *
     * @Title: queryResultProductInfo
     * @Description: (这里用一句话描述这个方法的作用)  查询产品信息
     * @throws
     */
    @RequestMapping("/queryResultProductInfo")
    @ResponseBody
    public ReqResponse queryResultProductInfo(Product product){
        ReqResponse r=new ReqResponse();
        product.setStatus("1");
        List<Product> ps=productService.findList(product);
        if(null==ps||ps.size()==0){
            r.setCode(1);
            r.setMsg("系统中无此"+product.getMaterialCode()+"产品");
            return r;
        }

        r.setData(ps.get(0));
        return r;
    }

    /**
     *
     * @Title: queryGradeList
     * @Description: (这里用一句话描述这个方法的作用)  评分
     * @throws
     */
    @RequestMapping("/queryGradeList")
    @ResponseBody
    public ReqResponse queryGradeList(){
        ReqResponse r=new ReqResponse();
        List<Grade> ps=gradeService.findList(new Grade());
        r.setData(ps);
        return r;
    }



    @Autowired
    private GradeService gradeService;
    @Autowired
    private ProductService productService;




    @Autowired
    private ReportDStandbookService reportDStandbookService;
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  待提交报台查询

     * @throws
     */
    @RequestMapping("/queryListReportDStandbook")
    @ResponseBody
    public ReqResponse queryListReportDStandbook(ReportDStandbook reportStandbook){
        ReqResponse r=new ReqResponse();
        List<ReportDStandbook> result = reportDStandbookService.findList(reportStandbook);

        for (ReportDStandbook r2:result
        ) {

            r2.setSurgeryGrade(basedataDao.get(r2.getSurgeryId()).getParamName());
        }
        r.setData(result);
        return r;
    }
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  待提交报台查询

     * @throws
     */
    @RequestMapping("/getReportDStandbook")
    @ResponseBody
    public ReqResponse getReportDStandbook(ReportDStandbook reportStandbook){
        ReqResponse r=new ReqResponse();
        ReportDStandbook reportStandbook2= reportDStandbookService.get(reportStandbook.getId());
        reportStandbook2.setSurgeryGrade(basedataDao.get(reportStandbook2.getSurgeryId()).getParamName());
        r.setData(reportStandbook2);
        return r;
    }

    /**
     * @throws Exception
    待提交保存报台

     * @throws
     */
    @RequestMapping("/saveReportDStandbook")
    @ResponseBody
    public ReqResponse saveReportDStandbook(ReportDStandbook reportStandbook) {
        //ReportStandbook reportStandbook= JSON.parseObject(datas,ReportStandbook.class);
        ReqResponse r=new ReqResponse();
                reportDStandbookService.save(reportStandbook);
        return r;
    }

    @Autowired
    private ReportStandbookOperationService reportStandbookOperationService;


    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  报数查询

     * @throws
     */
    @RequestMapping("/queryListReportStandbookOperation")
    @ResponseBody
    public ReqResponse queryListReportStandbookOperation(ReportStandbookOperation reportStandbook){
        ReqResponse r=new ReqResponse();
        List<ReportStandbookOperation> result = reportStandbookOperationService.findList(reportStandbook);
        r.setData(result);
        return r;
    }
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  报数明细

     * @throws
     */
    @RequestMapping("/getReportStandbookOperation")
    @ResponseBody
    public ReqResponse getReportStandbookOperation(ReportStandbookOperation reportStandbook){
        ReqResponse r=new ReqResponse();
         ReportStandbookOperation  result = reportStandbookOperationService.get(reportStandbook.getId());




        r.setData(result);
        return r;
    }

    /**
     * @throws Exception
    报数删除

     * @throws
     */
    @RequestMapping("/delReportStandbookOperation")
    @ResponseBody
    public ReqResponse delReportStandbookOperation(ReportStandbookOperation reportStandbook) {
        ReqResponse r=new ReqResponse();

        reportStandbookOperationService.delete(reportStandbook);

        return r;
    }
    /**
     * @throws Exception
    报数保存

     * @throws
     */
    @RequestMapping("/saveReportStandbookOperation")
    @ResponseBody
    public ReqResponse saveReportStandbookOperation(ReportStandbookOperation reportStandbook) {
        ReqResponse r=new ReqResponse();
        if(!DateUtils.equalsMonth(reportStandbook.getOperateDate(),reportStandbook.getOperateEdate())){
            r.setCode(1);
            r.setMsg("该医院报数日期不能夸月");
            return r;
        }
        if(null==reportStandbook.getReportStandbookOperationDetailList()||reportStandbook.getReportStandbookOperationDetailList().size()==0){
            r.setCode(1);
            r.setMsg("手术明细不能为空");
            return r;
        }
        List list=reportStandbookOperationService.findOnlyList(reportStandbook);
        if(list!=null&&list.size()>0){
            r.setCode(1);
            r.setMsg("该医院报数日期重复");
            return r;
        }
        reportStandbookOperationService.save(reportStandbook);

        return r;
    }
    @Autowired
    private HospitalService hospitalService;


    /**
     * @throws Exception
    医院保存

     * @throws
     */
    @RequestMapping("/saveUserHospital")
    @ResponseBody
    public ReqResponse saveUserHospital(Hospital hospital) {
        ReqResponse r=new ReqResponse();
        try {
            hospitalService.save(hospital);
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("医院已存在");
            return r;
        }
        return r;
    }


    /**
     * @throws Exception
    医院保存

     * @throws
     */
    @RequestMapping("/sendsms")
    @ResponseBody
    public ReqResponse sendsms(AppUser queryUser) {
        String phone=queryUser.getAccount();
        ReqResponse r=new ReqResponse();
        String str="0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }

        boolean f= SmsUtils.send(phone,sb.toString());
        if(f){
            CacheUtils.put("phone",phone,sb.toString());
        }
        else{
            r.setCode(1);
            r.setMsg("短信发送失败");
        }
        return r;
    }


    /**
     *
     * @Title: forgetUserPass
     * @Description: (这里用一句话描述这个方法的作用)  忘记密码
     * @return Map<String,Object>    返回类型
     * @throws
     */
    @RequestMapping("/forgetUserPass")
    @ResponseBody
    public ReqResponse forgetUserPass(AppUser queryUser){
        ReqResponse r=new ReqResponse();
        try {
            String phone=queryUser.getAccount();
            String p= (String) CacheUtils.get("phone",phone);
            if(StringUtils.isEmpty(p)){
                r.setMsg("验证码不存在或者已失效");
                r.setCode(1);
                return r;
            }
            if(!p.equals(queryUser.getRemarks())){
                r.setMsg("验证码错误");
                r.setCode(1);
                return r;
            }

           boolean modifySuccess = appUserService.forgetUserPass(queryUser);
        }catch (Exception e){
            r.setCode(1);
            r.setMsg("修改失败");
        }
        return  r;
    }


    @Autowired
    ReportStandbookProductDetailDao rspdao;
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  报台查询

     * @throws
     */
    @RequestMapping("/queryGetiReportStandbook")
    @ResponseBody
    public ReqResponse queryGetiReportStandbook(ReportStandbookProductDetail reportStandbookProductDetail){
        ReqResponse r=new ReqResponse();
        List<ReportStandbookProductDetail> result= rspdao.findList(reportStandbookProductDetail);
        for (ReportStandbookProductDetail r2:result
        ) {
            AppUser appUser= appUserService.get(r2.getReport().getUserId());
            r2.setRemarks(appUser.getBaseReportName()+"-"+appUser.getName());
            r2.getReport().setSurgeryGrade(basedataDao.get(r2.getReport().getSurgeryId()).getParamName());
        }
        r.setData(result);
        return r;
    }
    /**
     * @throws Exception
    医院查询

     * @throws
     */
    @RequestMapping("/queryListUserHospital")
    @ResponseBody
    public ReqResponse queryListUserHospital(Hospital hospital) {
        ReqResponse r=new ReqResponse();
        List<Hospital> result = hospitalService.findList(hospital);
        r.setData(result);
        return r;
    }
    /**
     * @throws Exception
    医院保存

     * @throws
     */
    @RequestMapping("/getUserHospital")
    @ResponseBody
    public ReqResponse getUserHospital(Hospital hospital) {
        ReqResponse r=new ReqResponse();
            r.setData(hospitalService.get(hospital.getId()));
        return r;
    }
    @Autowired
    private ReportStandbookOperationDetailDao reportStandbookOperationDetailDao;
    /**
     *

     * @Description: TODO(这里用一句话描述这个方法的作用)  导出报数

     * @throws
     */
    @RequestMapping("/exportReportStandbookOperation")
    public ReqResponse exportReportStandbookOperation(HttpServletResponse response,ReportStandbookOperation reportStandbook){
        ReqResponse r=new ReqResponse();
        List<ReportStandbookOperation> result = reportStandbookOperationService.findList(reportStandbook);

        List<ReportStandbookOperationDetail> ds=Lists.newArrayList();
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
    @Autowired
    private AppVersionService appVersionService;


    /**
     * @throws Exception
    版本

     * @throws
     */
    @RequestMapping("/sversion")
    @ResponseBody
    public ReqResponse sversion(AppVersion appVersion) {

        ReqResponse r=new ReqResponse();
        int old= Integer.parseInt(appVersion.getVersionNum().replaceAll("\\.",""));

            AppVersion a=new AppVersion();
            a.setIsCurrent("1");
            List<AppVersion> as =appVersionService.findList(a);
            if(null==as ||as.size()==0 ){
                return r;
            }
        AppVersion asnew =as.get(0);
        int newApp=Integer.parseInt(asnew.getVersionNum().replaceAll("\\.",""));
            if(newApp>old){
                r.setData(asnew);
                r.setCode(1);
            }

        return r;
    }
    @RequestMapping("/upload")
    @ResponseBody
    public ReqResponse upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {

        // 获取文件存储路径（绝对路径）
        String path = Global.getUserfilesBaseDir();
        String realPath =  Global.USERFILES_BASE_URL + "/" + DateUtils.getYear() + "/" + DateUtils.getMonth() + "/"+DateUtils.getDay();
        FileUtils.createDirectory(FileUtils.path(path+"/"+realPath));

        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path+ "/" +realPath, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        // 写入文件
        file.transferTo(filePath);
        ReqResponse r=new ReqResponse();
        r.setData(realPath+"/"+fileName);
        return r;
    }
}
