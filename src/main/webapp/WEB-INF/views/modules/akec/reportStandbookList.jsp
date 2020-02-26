<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报台信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
				<input name="beginOperateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbook.beginOperateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endOperateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbook.endOperateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbook.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>更新时间：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportStandbook.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>报台人单位：</label>
				<form:input path="dealerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>报台时间</th>
				<th>医院</th>
				<th>省</th>
				<th>市</th>
				<th>医生</th>
				<th>创建时间</th>
				<th>状态</th>
				<th>更新时间</th>
				<th>报台人单位</th>
				<shiro:hasPermission name="akec:reportStandbook:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportStandbook">
			<tr>
				<td><a href="${ctx}/akec/reportStandbook/form?id=${reportStandbook.id}">
					<fmt:formatDate value="${reportStandbook.operateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${reportStandbook.hospitalName}
				</td>
				<td>
					${reportStandbook.provinceName}
				</td>
				<td>
					${reportStandbook.cityName}
				</td>
				<td>
					${reportStandbook.doctorName}
				</td>
				<td>
					<fmt:formatDate value="${reportStandbook.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(reportStandbook.status, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${reportStandbook.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${reportStandbook.dealerName}
				</td>
				<shiro:hasPermission name="akec:reportStandbook:edit"><td>
    				<a href="${ctx}/akec/reportStandbook/form?id=${reportStandbook.id}">修改</a>
					<a href="${ctx}/akec/reportStandbook/delete?id=${reportStandbook.id}" onclick="return confirmx('确认要删除该报台信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>