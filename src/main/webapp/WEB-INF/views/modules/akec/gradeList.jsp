<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分类型管理</title>
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
		<li class="active"><a href="${ctx}/akec/grade/">评分类型列表</a></li>
		<shiro:hasPermission name="akec:grade:edit"><li><a href="${ctx}/akec/grade/form">评分类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="grade" action="${ctx}/akec/grade/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评分名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>评分名称</th>
				<th>等级</th>
				<th>状态</th>
				<th>排序号</th>
				<shiro:hasPermission name="akec:grade:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="grade">
			<tr>
				<td><a href="${ctx}/akec/grade/form?id=${grade.id}">
					${grade.name}
				</a></td>
				<td>
					${grade.level}
				</td>
				<td>
					${grade.status}
				</td>
				<td>
					${grade.seqno}
				</td>
				<shiro:hasPermission name="akec:grade:edit"><td>
    				<a href="${ctx}/akec/grade/form?id=${grade.id}">修改</a>
					<a href="${ctx}/akec/grade/delete?id=${grade.id}" onclick="return confirmx('确认要删除该评分类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>