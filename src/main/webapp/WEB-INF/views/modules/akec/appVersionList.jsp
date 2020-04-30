<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>App版本管理管理</title>
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
		<li class="active"><a href="${ctx}/akec/appVersion/">App版本管理列表</a></li>
		<shiro:hasPermission name="akec:appVersion:edit"><li><a href="${ctx}/akec/appVersion/form">App版本管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="appVersion" action="${ctx}/akec/appVersion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>是否最新：</label>
				<form:select path="isCurrent" class="input-medium">
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
				<th>名称</th>
				<th>安卓下载地址</th>
				<th>描述</th>
				<th>是否最新</th>
				<th>更新时间</th>
				<th>版本号</th>
				<th>ios下载地址</th>
				<shiro:hasPermission name="akec:appVersion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="appVersion">
			<tr>
				<td><a href="${ctx}/akec/appVersion/form?id=${appVersion.id}">
					${appVersion.name}
				</a></td>
				<td>
					${appVersion.androidUrl}
				</td>
				<td>
					${appVersion.vdesc}
				</td>
				<td>
					${fns:getDictLabel(appVersion.isCurrent, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${appVersion.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${appVersion.versionNum}
				</td>
				<td>
					${appVersion.iosUrl}
				</td>
				<shiro:hasPermission name="akec:appVersion:edit"><td>
    				<a href="${ctx}/akec/appVersion/form?id=${appVersion.id}">修改</a>
					<a href="${ctx}/akec/appVersion/delete?id=${appVersion.id}" onclick="return confirmx('确认要删除该App版本管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>