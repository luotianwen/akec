/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookOperation;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookOperationDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookOperationDetail;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookOperationDetailDao;

/**
 * 手术报数Service
 * @author 手术报数
 * @version 2020-03-27
 */
@Service
@Transactional(readOnly = true)
public class ReportStandbookOperationService extends CrudService<ReportStandbookOperationDao, ReportStandbookOperation> {

	@Autowired
	private ReportStandbookOperationDetailDao reportStandbookOperationDetailDao;
	
	public ReportStandbookOperation get(String id) {
		ReportStandbookOperation reportStandbookOperation = super.get(id);
		reportStandbookOperation.setReportStandbookOperationDetailList(reportStandbookOperationDetailDao.findList(new ReportStandbookOperationDetail(reportStandbookOperation)));
		return reportStandbookOperation;
	}
	
	public List<ReportStandbookOperation> findList(ReportStandbookOperation reportStandbookOperation) {
		return super.findList(reportStandbookOperation);
	}
	
	public Page<ReportStandbookOperation> findPage(Page<ReportStandbookOperation> page, ReportStandbookOperation reportStandbookOperation) {
		return super.findPage(page, reportStandbookOperation);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportStandbookOperation reportStandbookOperation) {
		super.save(reportStandbookOperation);
		reportStandbookOperationDetailDao.delete(new ReportStandbookOperationDetail(reportStandbookOperation));
		for (ReportStandbookOperationDetail reportStandbookOperationDetail : reportStandbookOperation.getReportStandbookOperationDetailList()){


			reportStandbookOperationDetail.setOperate(reportStandbookOperation);
			reportStandbookOperationDetail.preInsert();
			reportStandbookOperationDetailDao.insert(reportStandbookOperationDetail);

		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportStandbookOperation reportStandbookOperation) {
		super.delete(reportStandbookOperation);
		reportStandbookOperationDetailDao.delete(new ReportStandbookOperationDetail(reportStandbookOperation));
	}

    public List findOnlyList(ReportStandbookOperation reportStandbook) {
		return dao.findOnlyList(reportStandbook);
    }
}