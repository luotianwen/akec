/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.akec.entity.*;
import com.thinkgem.jeesite.modules.akec.web.ReqResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookDao;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookGradeDetailDao;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookImageDetailDao;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookProductDetailDao;
import sun.misc.BASE64Decoder;

/**
 * 报台信息Service
 * @author 报台信息
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class ReportStandbookService extends CrudService<ReportStandbookDao, ReportStandbook> {

	@Autowired
	private ReportStandbookGradeDetailDao reportStandbookGradeDetailDao;
	@Autowired
	private ReportStandbookImageDetailDao reportStandbookImageDetailDao;
	@Autowired
	private ReportStandbookProductDetailDao reportStandbookProductDetailDao;
	
	public ReportStandbook get(String id) {
		ReportStandbook reportStandbook = super.get(id);
		reportStandbook.setReportStandbookGradeDetailList(reportStandbookGradeDetailDao.findList(new ReportStandbookGradeDetail(reportStandbook)));
		reportStandbook.setReportStandbookImageDetailList(reportStandbookImageDetailDao.findList(new ReportStandbookImageDetail(reportStandbook)));
		reportStandbook.setReportStandbookProductDetailList(reportStandbookProductDetailDao.findList(new ReportStandbookProductDetail(reportStandbook)));
		return reportStandbook;
	}
	
	public List<ReportStandbook> findList(ReportStandbook reportStandbook) {
		return super.findList(reportStandbook);
	}
	
	public Page<ReportStandbook> findPage(Page<ReportStandbook> page, ReportStandbook reportStandbook) {
		return super.findPage(page, reportStandbook);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportStandbook reportStandbook) {
		super.save(reportStandbook);
		/*for (ReportStandbookGradeDetail reportStandbookGradeDetail : reportStandbook.getReportStandbookGradeDetailList()){
			if (reportStandbookGradeDetail.getId() == null){
				continue;
			}
			if (ReportStandbookGradeDetail.DEL_FLAG_NORMAL.equals(reportStandbookGradeDetail.getDelFlag())){
				if (StringUtils.isBlank(reportStandbookGradeDetail.getId())){
					reportStandbookGradeDetail.setReport(reportStandbook);
					reportStandbookGradeDetail.preInsert();
					reportStandbookGradeDetailDao.insert(reportStandbookGradeDetail);
				}else{
					reportStandbookGradeDetail.preUpdate();
					reportStandbookGradeDetailDao.update(reportStandbookGradeDetail);
				}
			}else{
				reportStandbookGradeDetailDao.delete(reportStandbookGradeDetail);
			}
		}
		for (ReportStandbookImageDetail reportStandbookImageDetail : reportStandbook.getReportStandbookImageDetailList()){
			if (reportStandbookImageDetail.getId() == null){
				continue;
			}
			if (ReportStandbookImageDetail.DEL_FLAG_NORMAL.equals(reportStandbookImageDetail.getDelFlag())){
				if (StringUtils.isBlank(reportStandbookImageDetail.getId())){
					reportStandbookImageDetail.setReport(reportStandbook);
					reportStandbookImageDetail.preInsert();
					reportStandbookImageDetailDao.insert(reportStandbookImageDetail);
				}else{
					reportStandbookImageDetail.preUpdate();
					reportStandbookImageDetailDao.update(reportStandbookImageDetail);
				}
			}else{
				reportStandbookImageDetailDao.delete(reportStandbookImageDetail);
			}
		}
		for (ReportStandbookProductDetail reportStandbookProductDetail : reportStandbook.getReportStandbookProductDetailList()){
			if (reportStandbookProductDetail.getId() == null){
				continue;
			}
			if (ReportStandbookProductDetail.DEL_FLAG_NORMAL.equals(reportStandbookProductDetail.getDelFlag())){
				if (StringUtils.isBlank(reportStandbookProductDetail.getId())){
					reportStandbookProductDetail.setReport(reportStandbook);
					reportStandbookProductDetail.preInsert();
					reportStandbookProductDetailDao.insert(reportStandbookProductDetail);
				}else{
					reportStandbookProductDetail.preUpdate();
					reportStandbookProductDetailDao.update(reportStandbookProductDetail);
				}
			}else{
				reportStandbookProductDetailDao.delete(reportStandbookProductDetail);
			}
		}*/
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportStandbook reportStandbook) {
		super.delete(reportStandbook);
		reportStandbookGradeDetailDao.delete(new ReportStandbookGradeDetail(reportStandbook));
		reportStandbookImageDetailDao.delete(new ReportStandbookImageDetail(reportStandbook));
		reportStandbookProductDetailDao.delete(new ReportStandbookProductDetail(reportStandbook));
	}

	public List<ReportStandbook> queryListReportStandbook(ReportStandbook reportStandbook) {
		return dao.queryListReportStandbook(reportStandbook);
	}

	@Autowired
	private ProductService productService;
	@Autowired
	private SellproductService sellproductService;
	@Autowired
	private DealerService dealerService;
    @Autowired
    private RegionService regionService;
	@Autowired
	private AppUserService appUserService;
	private static final String AKEC_TYPE = ",18370AD648C42B79759D2B6DB04DF6BB,1EDE364642A542428CE115CEB82B8132,B18370AD648C42B79759D2B6DB04DF6D,";

	private static final String DEALER_TYPE = ",B18370AD648C42B79759D2B6DB04DF6B,B18370AD648C42B79759D2B6DB04DF6C,";

	@Transactional(readOnly = false)
    public ReqResponse saveReportStandbook(ReportStandbook reportStandbook) {




		ReqResponse r=new ReqResponse();
		List<ReportStandbookProductDetail> productList = reportStandbook.getReportStandbookProductDetailList();
		String dealerName = null;


		AppUser au=appUserService.get(reportStandbook.getUserId());
		String type = au.getBaseReportId();
		if(AKEC_TYPE.indexOf(","+type+",")>=0){
			type = "1";
		}else if(DEALER_TYPE.indexOf(","+type+",")>=0){
			type = "2";
		}
		reportStandbook.setType(type);
		//市
        Region region=new Region();

        region.setName(reportStandbook.getProvinceName());
        List<Region> rs=regionService.findList(region);
        if(rs==null||rs.size()==0){
            r.setCode(1);
            r.setMsg("省不存在 请重新选择医院");
            return r;
        }
        reportStandbook.setProvinceCode(rs.get(0).getCode());
        region=new Region();
        region.setParentCode(reportStandbook.getProvinceCode());
        region.setName(reportStandbook.getCityName());
        rs=regionService.findList(region);
        if(rs==null||rs.size()==0){
            r.setCode(1);
            r.setMsg("市不存在 请重新选择医院");
            return r;
        }
        reportStandbook.setCityCode(rs.get(0).getCode());
        region=new Region();
        region.setParentCode(reportStandbook.getCityCode());
        region.setName(reportStandbook.getHospitalName());
        region.setStatus("1");
        String hospitalId="";
         rs=regionService.findList(region);
        if(null==rs||rs.size()==0){

           String degree=reportStandbook.getDegree();
           if("090101".equals(degree)){
               degree="三甲";
           }
           else{
               degree="";
           }
            Region region2=new Region();
            region2.setStatus("1");
            region2.setName(reportStandbook.getHospitalName());
            region2.setParentCode(reportStandbook.getCityCode());
            region2.setDegree(degree);
            region2.setLevel("3");
            region2.setSeqno("1");
            regionService.save(region2);
            hospitalId=region2.getId();
        }
        else{
            hospitalId=rs.get(0).getId();

        }
        reportStandbook.setHospitalId(hospitalId);


		String reportId =IdGen.uuid();
		if(reportStandbook.getId()!=null  && reportStandbook.getId().length()>0){
			reportId = reportStandbook.getId();
		}
		boolean isSave = !(reportStandbook.getId()!=null && reportStandbook.getId().length()>0);
		Set<String> verifySet = new HashSet<String>();
		for(ReportStandbookProductDetail product:productList){
			product.setIntegral("1");
			String isVerifyindivualcode = product.getIsVerifyIndividualcode();
			String indivualcode = product.getIndividualcode();
			product.setReport(reportStandbook);
			if("1".equals(isVerifyindivualcode)){
				Product productVo  = productService.get(product.getProductId());
				boolean putSuccess = verifySet.add(productVo.getCode()+indivualcode);
				if(!putSuccess){
					 r.setCode(1);
					 r.setMsg("产品不唯一");
					return r;
				}
				Sellproduct sp=new Sellproduct();
				sp.setProudctCode(productVo.getCode());
				sp.setIndividualcode(indivualcode);
				List<Sellproduct> sellProducts = sellproductService.findList(sp);
				if(sellProducts == null||sellProducts.size()==0){
					r.setCode(1);
					r.setMsg("已售产品中未找到报台产品");
					return r;

				}
				Sellproduct sellProduct=sellProducts.get(0);
				Dealer dv=new Dealer();
				dv.setCode(sellProduct.getDealerCode());
				List<Dealer> dealers= dealerService.findList(dv);

					if(dealers!=null){
						Dealer dealer=dealers.get(0);
						String isRecordIntegral  = dealer.getIsRecordIntegral();
						if("0".equals(isRecordIntegral)){
							product.setIntegral("0");
						}
					}
					dealerName = sellProduct.getDealerName();


				  ReportStandbookProductDetail rd=new ReportStandbookProductDetail();
				  rd.setIndividualcode(indivualcode);
				  rd.setScanCode(productVo.getCode());
				  rd.setType(type);

					if(!isSave){
						rd.setReport(reportStandbook);
					}
					int reportProductCount = reportStandbookProductDetailDao.countReprotProduct(rd);
					if(reportProductCount>0){
						r.setCode(1);
						r.setMsg("产品已被报台");
						return r;
					}
					int historyReportProductCount = reportStandbookProductDetailDao.countHistoryReprotProduct(rd);
					if(historyReportProductCount>0){
						r.setCode(1);
						r.setMsg("产品已被报台");
						return r;
					}


			}
		}
		reportStandbook.setDealerName(dealerName);
		reportStandbook.setStatus("1");
		reportStandbook.setCreateDate(new Date());
		super.save(reportStandbook);

		/*String realPath ="/"+ DateUtils.formatDate(new Date(),"yyyy/MM/") ;
		FileUtils.createDirectory( FileUtils.path(Global.getUserfilesBaseDir()+realPath));*/

		for (ReportStandbookImageDetail reportStandbookImageDetail : reportStandbook.getReportStandbookImageDetailList()) {
		/*	String uploadFileName= IdGen.uuid()+".jpg";
			File targetFile = new File(FileUtils.path(Global.getUserfilesBaseDir()+realPath), uploadFileName);
			BASE64Decoder decoder = new BASE64Decoder();*/
			/*try(OutputStream out = new FileOutputStream(targetFile)){
				byte[] b = decoder.decodeBuffer(URLDecoder.decode(reportStandbookImageDetail.getReportImgUrl()));
				for (int i = 0; i <b.length ; i++) {
					if (b[i] <0) {
						b[i]+=256;
					}
				}
				out.write(b);
				out.flush();
				reportStandbookImageDetail.setReportImgUrl(realPath+uploadFileName);
				System.out.println(realPath+uploadFileName);*/
			if(reportStandbookImageDetail.getReportImgUrl().length()>300){
				reportStandbookImageDetail.setReportImgUrl("");
			}
				reportStandbookImageDetail.setAddDate(new Date());
			/*}catch (Exception e){
				e.printStackTrace();
				r.setCode(1);
				return r;
			}*/

		}
		/*
		List<String> imageUploadResult = FileOperateUtil.uploadImage(request);
		List<SaveReportStandbookImageVo> imageList = new ArrayList<SaveReportStandbookImageVo>();
		for(String imageName:imageUploadResult){
			String imageId = uuid.next();
			SaveReportStandbookImageVo saveImage = new SaveReportStandbookImageVo();
			saveImage.setReportId(reportId);
			saveImage.setImageId(imageId);
			saveImage.setReportImgUrl(imageName);
			imageList.add(saveImage);
		}*/


		if(!isSave){
			reportStandbookGradeDetailDao.delete(new ReportStandbookGradeDetail(reportStandbook));
			reportStandbookImageDetailDao.delete(new ReportStandbookImageDetail(reportStandbook));
			reportStandbookProductDetailDao.delete(new ReportStandbookProductDetail(reportStandbook));
		}
		for (ReportStandbookImageDetail reportStandbookImageDetail : reportStandbook.getReportStandbookImageDetailList()){
					reportStandbookImageDetail.setReport(reportStandbook);
					reportStandbookImageDetail.preInsert();
			reportStandbookImageDetail.setAddDate(new Date());
					reportStandbookImageDetailDao.insert(reportStandbookImageDetail);
		}

		for (ReportStandbookGradeDetail reportStandbookGradeDetail : reportStandbook.getReportStandbookGradeDetailList()){

					reportStandbookGradeDetail.setReport(reportStandbook);
					reportStandbookGradeDetail.preInsert();
					reportStandbookGradeDetailDao.insert(reportStandbookGradeDetail);

		}

		for (ReportStandbookProductDetail reportStandbookProductDetail : reportStandbook.getReportStandbookProductDetailList()){

					reportStandbookProductDetail.setReport(reportStandbook);
					reportStandbookProductDetail.preInsert();
					reportStandbookProductDetailDao.insert(reportStandbookProductDetail);

		}


		return r;
	}
}