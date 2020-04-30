<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医生管理管理</title>
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
		<li class="active"><a href="${ctx}/akec/doctor/">医生管理列表</a></li>
		<shiro:hasPermission name="akec:doctor:edit"><li><a href="${ctx}/akec/doctor/form">医生管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="doctor" action="${ctx}/akec/doctor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户：</label>
				<form:input path="user.name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>医院：</label>
				<form:input path="hospitalName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>

			<li><label>医生：</label>
				<form:input path="doctorName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>职称：</label>
				<form:select path="technical" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('technical')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>职务：</label>
				<form:select path="duties" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('duties')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${doctor.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${doctor.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	</form:form>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column a.user_id" >用户</th>
				<th class="sort-column a.hospital_name">医院</th>
				<th class="sort-column a.doctor_name">医生</th>
				<th class="sort-column a.technical">职称</th>
				<th class="sort-column a.duties">职务</th>
				<th class="sort-column a.sex" >性别</th>
				<th class="sort-column a.create_date">创建时间</th>
				<shiro:hasPermission name="akec:doctor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="doctor">
			<tr>
				<td><a href="${ctx}/akec/doctor/form?id=${doctor.id}">
					${doctor.user.name}
				</a></td>
				<td>
					${doctor.hospitalName}
				</td>
				<td>
					${doctor.doctorName}
				</td>
				<td>
					${fns:getDictLabel(doctor.technical, 'technical', '')}
				</td>
				<td>
						${fns:getDictLabel(doctor.duties, 'duties', '')}
				</td>
				<td>
					${fns:getDictLabel(doctor.sex, 'sex', '')}
				</td>
				<td>
					<fmt:formatDate value="${doctor.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="akec:doctor:edit"><td>
    				<a href="${ctx}/akec/doctor/form?id=${doctor.id}">修改</a>
					<a href="${ctx}/akec/doctor/delete?id=${doctor.id}" onclick="return confirmx('确认要删除该医生管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>