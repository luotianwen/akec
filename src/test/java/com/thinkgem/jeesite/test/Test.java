package com.thinkgem.jeesite.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbook;

public class Test {

	public static void main(String[] args) {
		/*String json="{\"suggest\":\"\",\"doctorName\":\"罗医生\",\"patientSex\":\"518370AD648C42B79759D2B6DB04DF6D\",\"patientAge\":\"333\",\"surgeryId\":\"518370AD648C42B79759D2B6DB04DF6C\",\"operateDate\":\"2020-03-17\",\"hospitalName\":\"天安门广场\",\"reportStandbookGradeDetailList\":[{\"grade\":3,\"gradeId\":\"6B8326C12F3C4258BA730C8D964FCFBD\"},{\"grade\":3,\"gradeId\":\"6B8326C12F3C4258BA730C8D964FCFBD\"},{\"grade\":3,\"gradeId\":\"6B8326C12F3C4258BA730C8D964FCFBD\"},{\"grade\":3,\"gradeId\":\"6B8326C12F3C4258BA730C8D964FCFBD\"}],\"reportStandbookImageDetailList\":[],\"reportStandbookProductDetailList\":[]}";
		ReportStandbook reportStandbook= JSON.parseObject(json,ReportStandbook.class);
		System.out.println(reportStandbook);*/
		String a="9.6.88";
		System.out.println(a.replaceAll("\\.",""));
	}
}
