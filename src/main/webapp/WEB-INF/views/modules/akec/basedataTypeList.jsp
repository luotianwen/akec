<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>常用参数管理</title>
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
		<li class="active"><a href="${ctx}/akec/basedataType/">常用参数列表</a></li>
		<shiro:hasPermission name="akec:basedataType:edit"><li><a href="${ctx}/akec/basedataType/form">常用参数添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="basedataType" action="${ctx}/akec/basedataType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编码：</label>
				<form:input path="baseTypeCode" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="baseTypeName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编码</th>
				<th>名称</th>
				<shiro:hasPermission name="akec:basedataType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="basedataType">
			<tr>
				<td><a href="${ctx}/akec/basedataType/form?id=${basedataType.id}">
					${basedataType.baseTypeCode}
				</a></td>
				<td>
					${basedataType.baseTypeName}
				</td>
				<shiro:hasPermission name="akec:basedataType:edit"><td>
    				<a href="${ctx}/akec/basedataType/form?id=${basedataType.id}">修改</a>
					<a href="${ctx}/akec/basedataType/delete?id=${basedataType.id}" onclick="return confirmx('确认要删除该常用参数吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>