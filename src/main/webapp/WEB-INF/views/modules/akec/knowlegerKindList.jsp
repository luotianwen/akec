<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>知识分类管理</title>
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
		<li class="active"><a href="${ctx}/akec/knowlegerKind/">知识分类列表</a></li>
		<shiro:hasPermission name="akec:knowlegerKind:edit"><li><a href="${ctx}/akec/knowlegerKind/form">知识分类添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="knowlegerKind" action="${ctx}/akec/knowlegerKind/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>知识分类：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
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
				<th>知识分类</th>
				<th>序号</th>
				<th>状态</th>
				<shiro:hasPermission name="akec:knowlegerKind:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="knowlegerKind">
			<tr>
				<td><a href="${ctx}/akec/knowlegerKind/form?id=${knowlegerKind.id}">
					${knowlegerKind.name}
				</a></td>
				<td>
					${knowlegerKind.seqno}
				</td>
				<td>
					${fns:getDictLabel(knowlegerKind.status, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="akec:knowlegerKind:edit"><td>
    				<a href="${ctx}/akec/knowlegerKind/form?id=${knowlegerKind.id}">修改</a>
					<a href="${ctx}/akec/knowlegerKind/delete?id=${knowlegerKind.id}" onclick="return confirmx('确认要删除该知识分类吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>