<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医院管理管理</title>
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
		<li class="active"><a href="${ctx}/akec/hospital/">医院管理列表</a></li>
		<shiro:hasPermission name="akec:hospital:edit"><li><a href="${ctx}/akec/hospital/form">医院管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hospital" action="${ctx}/akec/hospital/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>医院名称：</label>
				<form:input path="hospitalName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>省：</label>
				<form:input path="provinceName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>市：</label>
				<form:input path="cityName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>创建人：</label>
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${hospital.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${hospital.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新人：</label>
				<form:input path="updateAdminName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>医院名称</th>
				<th>省</th>
				<th>市</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>更新人</th>
				<shiro:hasPermission name="akec:hospital:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hospital">
			<tr>
				<td><a href="${ctx}/akec/hospital/form?id=${hospital.id}">
					${hospital.hospitalName}
				</a></td>
				<td>
					${hospital.provinceName}
				</td>
				<td>
					${hospital.cityName}
				</td>
				<td>
					${hospital.userName}
				</td>
				<td>
					<fmt:formatDate value="${hospital.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${hospital.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${hospital.updateAdminName}
				</td>
				<shiro:hasPermission name="akec:hospital:edit"><td>
    				<a href="${ctx}/akec/hospital/form?id=${hospital.id}">修改</a>
					<a href="${ctx}/akec/hospital/delete?id=${hospital.id}" onclick="return confirmx('确认要删除该医院管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>