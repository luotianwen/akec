<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报台信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#export").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示 数据量比较大稍等片刻", function (v, h, f) {
                    if (v == "ok") {
                        $("#export").attr("disabled",true);
                        var oldAction = $("#searchForm").attr("action");
                        $("#searchForm").attr("target", "_blank");
                        $("#searchForm").attr("action", "${ctx}/akec/reportStandbook/exportListReportStandbook2");
                        $("#searchForm").submit();
                        $("#searchForm").attr("target", "_self");
                        $("#searchForm").attr("action", oldAction);
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/akec/reportStandbook/">报台信息列表</a></li>
		<shiro:hasPermission name="akec:reportStandbook:edit"><li><a href="${ctx}/akec/reportStandbook/form">报台信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reportStandbook" action="${ctx}/akec/reportStandbook/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>报台时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${reportStandbook.beginCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${reportStandbook.endCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>手术时间：</label>
				<input name="beginOperateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbook.beginOperateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endOperateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbook.endOperateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>医院：</label>
				<form:input path="hospitalName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>省：</label>
				<form:input path="provinceName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>市：</label>
				<form:input path="cityName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>医生：</label>
				<form:input path="doctorName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>

			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>手术类型：</label>
				<form:select path="surgeryId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${surgeryIds}" itemLabel="paramName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>报台人：</label>
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>报台人单位：</label>
				<form:input path="dealerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="export" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th  class="sort-column a.create_date">报台时间</th>
				<th  class="sort-column a.operate_date">手术日期</th>
				<th  class="sort-column a.province_name">省</th>
				<th  class="sort-column a.city_name">市</th>
				<th  class="sort-column a.hospital_name">医院</th>
				<th  class="sort-column a.doctor_name">医生</th>
				<th>患者年龄</th>
				<th>患者性别</th>
				<th  class="sort-column a.user_name">报台人</th>
				<th>报台人单位</th>
				<th>台数</th>
				<th  class="sort-column a.surgery_id">手术类型</th>
				<th>状态</th>
				<shiro:hasPermission name="akec:reportStandbook:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportStandbook">
			<tr>
				<td>
					<fmt:formatDate value="${reportStandbook.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${reportStandbook.operateDate}" pattern="yyyy-MM-dd"/>
				 </td>

				<td>
					${reportStandbook.provinceName}
				</td>
				<td>
					${reportStandbook.cityName}
				</td>
				<td>
						${reportStandbook.hospitalName}
				</td>
				<td>
						${reportStandbook.doctorName}
				</td>
				<td>
					${reportStandbook.patientAge}
				</td>
				<td>
						${reportStandbook.patientSex}
				</td>

				<td>
						${reportStandbook.userName}
				</td>

				<td>
						${reportStandbook.dealerName}
				</td>

				<td>
						${reportStandbook.unitCount}
				</td>

				<td>
						${reportStandbook.surgeryGrade}
				</td>



				<td>
						${fns:getDictLabel(reportStandbook.status, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="akec:reportStandbook:edit"><td>
    				<a href="${ctx}/akec/reportStandbook/form?id=${reportStandbook.id}">修改</a>
					<%--<a href="${ctx}/akec/reportStandbook/delete?id=${reportStandbook.id}" onclick="return confirmx('确认要删除该报台信息吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>