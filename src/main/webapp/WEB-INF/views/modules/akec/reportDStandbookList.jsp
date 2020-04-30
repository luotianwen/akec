<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待提交报台管理</title>
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
		<li class="active"><a href="${ctx}/akec/reportDStandbook/">待提交报台列表</a></li>
		<shiro:hasPermission name="akec:reportDStandbook:edit"><li><a href="${ctx}/akec/reportDStandbook/form">待提交报台添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reportDStandbook" action="${ctx}/akec/reportDStandbook/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>operate_date</th>
				<th>hospital_name</th>
				<th>province_code</th>
				<th>province_name</th>
				<th>city_name</th>
				<th>city_code</th>
				<th>update_date</th>
				<shiro:hasPermission name="akec:reportDStandbook:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportDStandbook">
			<tr>
				<td><a href="${ctx}/akec/reportDStandbook/form?id=${reportDStandbook.id}">
					<fmt:formatDate value="${reportDStandbook.operateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${reportDStandbook.hospitalName}
				</td>
				<td>
					${reportDStandbook.provinceCode}
				</td>
				<td>
					${reportDStandbook.provinceName}
				</td>
				<td>
					${reportDStandbook.cityName}
				</td>
				<td>
					${reportDStandbook.cityCode}
				</td>
				<td>
					<fmt:formatDate value="${reportDStandbook.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="akec:reportDStandbook:edit"><td>
    				<a href="${ctx}/akec/reportDStandbook/form?id=${reportDStandbook.id}">修改</a>
					<a href="${ctx}/akec/reportDStandbook/delete?id=${reportDStandbook.id}" onclick="return confirmx('确认要删除该待提交报台吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>