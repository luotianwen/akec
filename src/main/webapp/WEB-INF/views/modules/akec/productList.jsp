<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品信息管理</title>
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
		<li class="active"><a href="${ctx}/akec/product/">产品信息列表</a></li>
		<shiro:hasPermission name="akec:product:edit"><li><a href="${ctx}/akec/product/form">产品信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/akec/product/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>物料编号：</label>
				<form:input path="materialCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>条形码：</label>
				<form:input path="barCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>是否记台：</label>
				<form:select path="isRecordUnit" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否校验个体码：</label>
				<form:select path="isVerifyIndividualcode" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>产品编码</th>
				<th>物料编号</th>
				<th>条形码</th>
				<th>系统参数表分类类型</th>
				<th>产品大类名称</th>
				<th>参照系统参数表</th>
				<th>产品系列</th>
				<th>产品批号</th>
				<th>标准售价</th>
				<th>积分</th>
				<th>是否记台</th>
				<th>是否校验个体码</th>
				<th>状态</th>
				<shiro:hasPermission name="akec:product:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td><a href="${ctx}/akec/product/form?id=${product.id}">
					${product.code}
				</a></td>
				<td>
					${product.materialCode}
				</td>
				<td>
					${product.barCode}
				</td>
				<td>
					${product.baseTypeId}
				</td>
				<td>
					${product.typeName}
				</td>
				<td>
					${product.baseSeriesId}
				</td>
				<td>
					${product.seriesName}
				</td>
				<td>
					${product.batchCode}
				</td>
				<td>
					${product.standerSaleprice}
				</td>
				<td>
					${product.integral}
				</td>
				<td>
					${fns:getDictLabel(product.isRecordUnit, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(product.isVerifyIndividualcode, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(product.status, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="akec:product:edit"><td>
    				<a href="${ctx}/akec/product/form?id=${product.id}">修改</a>
					<a href="${ctx}/akec/product/delete?id=${product.id}" onclick="return confirmx('确认要删除该产品信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>