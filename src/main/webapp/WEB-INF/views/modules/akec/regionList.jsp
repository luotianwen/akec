<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医院信息管理</title>
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
		<li class="active"><a href="${ctx}/akec/region/">医院信息列表</a></li>
		<shiro:hasPermission name="akec:region:edit"><li><a href="${ctx}/akec/region/form">医院信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="region" action="${ctx}/akec/region/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>医院等级：</label>
				<form:input path="degree" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>状态</th>
				<th>医院等级</th>
				<shiro:hasPermission name="akec:region:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="region">
			<tr>
				<td><a href="${ctx}/akec/region/form?id=${region.id}">
					${region.name}
				</a></td>
				<td>
					${region.status}
				</td>
				<td>
					${region.degree}
				</td>
				<shiro:hasPermission name="akec:region:edit"><td>
    				<a href="${ctx}/akec/region/form?id=${region.id}">修改</a>
					<a href="${ctx}/akec/region/delete?id=${region.id}" onclick="return confirmx('确认要删除该医院信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>