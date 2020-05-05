<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经销商信息管理</title>
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
		<li class="active"><a href="${ctx}/akec/dealer/">经销商信息列表</a></li>
		<shiro:hasPermission name="akec:dealer:edit"><li><a href="${ctx}/akec/dealer/form">经销商信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dealer" action="${ctx}/akec/dealer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>经销商编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>是否计分：</label>
				<form:select path="isRecordIntegral" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>原有编码：</label>
				<form:input path="originalCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>市场：</label>
				<form:input path="market" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>经销商名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<a href="${ctx}/akec/dealer/tball"> <input  class="btn btn-primary" type="button" value="同步所有经销商"/></a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>原始编码</th>
				<th>业务区域</th>
				<th>业务省份</th>
				<th>市场</th>
				<th>财务区域</th>
				<th>财务省区</th>
				<th>编码</th>
				<th>名称</th>
				<th>资质有效期</th>
				<th>业务统计客户编码</th>
				<th>业务统计客户名称</th>
				<th>是否计分</th>
				<th>状态</th>
				<shiro:hasPermission name="akec:dealer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dealer">
			<tr>
				<td>
					${dealer.originalCode}
				 </td>
				<td>
						${dealer.businessRegion}
				</td>
				<td>
						${dealer.businessProvince}
				</td>
				<td>
					${dealer.market}
				</td>
				<td>
					${dealer.financeRegion}
				</td>
				<td>
					${dealer.financeProvince}
				</td>
				<td>
						${dealer.code}
				</td>
				<td>
					${dealer.name}
				</td>
				<td>
					${dealer.qualityValidity}
				</td>

				<td>
					${dealer.businessStaticsCode}
				</td>
				<td>
					${dealer.businessStaticsName}
				</td>
				<td>
						${fns:getDictLabel(dealer.isRecordIntegral, 'yes_no', '')}
				</td>
				<td>
						${fns:getDictLabel(dealer.status, 'yes_no', '')}
				</td>

				<shiro:hasPermission name="akec:dealer:edit"><td>
    				<a href="${ctx}/akec/dealer/form?id=${dealer.id}">修改</a>
					 	</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>