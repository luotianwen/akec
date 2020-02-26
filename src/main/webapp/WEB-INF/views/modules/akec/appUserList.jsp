<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>APP用户管理管理</title>
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
		<li class="active"><a href="${ctx}/akec/appUser/">APP用户管理列表</a></li>
		<shiro:hasPermission name="akec:appUser:edit"><li><a href="${ctx}/akec/appUser/form">APP用户管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="appUser" action="${ctx}/akec/appUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>账号名：</label>
				<form:input path="account" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>经销商：</label>
				<form:input path="dealerId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="auditStatus" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>创建类型：</label>
				<form:select path="createType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('create_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>允许输入：</label>
				<form:select path="inputFlag" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>账号名</th>
				<th>用户名</th>
				<th>经销商</th>
				<th>注册所属单位名字</th>
				<th>经销商名字</th>
				<th>系统参数表</th>
				<th>状态</th>
				<th>创建类型</th>
				<th>允许输入</th>
				<shiro:hasPermission name="akec:appUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="appUser">
			<tr>
				<td><a href="${ctx}/akec/appUser/form?id=${appUser.id}">
					${appUser.account}
				</a></td>
				<td>
					${appUser.name}
				</td>
				<td>
					${appUser.dealerId}
				</td>
				<td>
					${appUser.registerDealerName}
				</td>
				<td>
					${appUser.dealerName}
				</td>
				<td>
					${appUser.baseReportId}
				</td>
				<td>
					${appUser.auditStatus}
				</td>
				<td>
					${fns:getDictLabel(appUser.createType, 'create_type', '')}
				</td>
				<td>
					${fns:getDictLabel(appUser.inputFlag, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="akec:appUser:edit"><td>
    				<a href="${ctx}/akec/appUser/form?id=${appUser.id}">修改</a>
					<a href="${ctx}/akec/appUser/delete?id=${appUser.id}" onclick="return confirmx('确认要删除该APP用户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>