<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>发布资料管理</title>
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
		<li class="active"><a href="${ctx}/akec/studycenter/">发布资料列表</a></li>
		<shiro:hasPermission name="akec:studycenter:edit"><li><a href="${ctx}/akec/studycenter/form">发布资料添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="studycenter" action="${ctx}/akec/studycenter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>发布日期：</label>
				<input name="beginReleaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${studycenter.beginReleaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endReleaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${studycenter.endReleaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>kkid：</label>
				<form:input path="kk.id" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>

				<th>序号</th>
				<th>状态</th>
				<th>发布日期</th>
				<th>所属分类</th>
				<th>链接地址</th>
				<shiro:hasPermission name="akec:studycenter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="studycenter">
			<tr>
				<td><a href="${ctx}/akec/studycenter/form?id=${studycenter.id}">
					${studycenter.title}
				</a></td>

				<td>
					${studycenter.seqno}
				</td>
				<td>
					${studycenter.status}
				</td>
				<td>
					<fmt:formatDate value="${studycenter.releaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${studycenter.kk.name}
				</td>
				<td>
						${studycenter.href}
				</td>
				<shiro:hasPermission name="akec:studycenter:edit"><td>
    				<a href="${ctx}/akec/studycenter/form?id=${studycenter.id}">修改</a>
					<a href="${ctx}/akec/studycenter/delete?id=${studycenter.id}" onclick="return confirmx('确认要删除该发布资料吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>