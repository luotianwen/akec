<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手术报数管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#export").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        var oldAction = $("#searchForm").attr("action");
                        $("#searchForm").attr("target", "_blank");
                        $("#searchForm").attr("action", "${ctx}/akec/reportStandbookOperation/exportReportStandbookOperation");
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
		<li class="active"><a href="${ctx}/akec/reportStandbookOperation/">手术报数列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="reportStandbookOperation" action="${ctx}/akec/reportStandbookOperation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>手术日期：</label>
				<input name="beginOperateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbookOperation.beginOperateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endOperateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbookOperation.endOperateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>手术日期：</label>
				<input name="beginOperateEdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbookOperation.beginOperateEdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endOperateEdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbookOperation.endOperateEdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>医院名称：</label>
				<form:input path="hospitalName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>省：</label>
				<form:input path="provinceName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>市：</label>
				<form:input path="cityName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>

			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbookOperation.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>爱康手术：</label>
				<form:select path="ak" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否提交：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="export" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column a.operate_date">手术日期</th>
				<th class="sort-column a.operate_edate">手术日期</th>
				<th class="sort-column a.hospital_name">医院名称</th>
				<th class="sort-column a.province_name">省名称</th>
				<th class="sort-column a.city_name">市名称</th>
				<th>医院总台数</th>
				<th>爱康总台数</th>
				<th>爱康手术</th>
				<th>是否提交</th>
				<th class="sort-column a.create_date">创建时间</th>


				<shiro:hasPermission name="akec:reportStandbookOperation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportStandbookOperation">
			<tr>
				<td><a href="${ctx}/akec/reportStandbookOperation/form?id=${reportStandbookOperation.id}">
					<fmt:formatDate value="${reportStandbookOperation.operateDate}" pattern="yyyy-MM-dd"/>
				</a></td>
				<td>
					<fmt:formatDate value="${reportStandbookOperation.operateEdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${reportStandbookOperation.hospitalName}
				</td>
				<td>
					${reportStandbookOperation.provinceName}
				</td>
				<td>
					${reportStandbookOperation.cityName}
				</td>

				<td>
					${reportStandbookOperation.unitCount}
				</td>

				<td>
						${reportStandbookOperation.akCount}
				</td>
				<td>
						${fns:getDictLabel(reportStandbookOperation.ak, 'yes_no', '')}
				</td>
				<td>
						${fns:getDictLabel(reportStandbookOperation.status, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${reportStandbookOperation.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<shiro:hasPermission name="akec:reportStandbookOperation:edit"><td>
    				<a href="${ctx}/akec/reportStandbookOperation/form?id=${reportStandbookOperation.id}">修改</a>
					<a href="${ctx}/akec/reportStandbookOperation/delete?id=${reportStandbookOperation.id}" onclick="return confirmx('确认要删除该手术报数吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>