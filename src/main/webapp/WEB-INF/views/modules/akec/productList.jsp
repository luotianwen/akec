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
			<li><label>校验个体码：</label>
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<a href="${ctx}/akec/product/tball"> <input  class="btn btn-primary" type="button" value="同步所有产品"/></a>
			</li>
			<li class="clearfix"></li>
		</ul>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column a.code">产品编码</th>
				<th class="sort-column a.material_code">物料编号</th>
				<th class="sort-column a.bar_code">条形码</th>
				<th class="sort-column a.material_desc">物料描述</th>
				<th class="sort-column a.type_name">产品大类</th>
				<th class="sort-column a.series_name">产品系列</th>
				<th class="sort-column a.material_spe_desc">物料规格型号</th>
				<th>产品积分</th>
				<th>标准售价</th>
				<th class="sort-column a.is_verify_individualcode">是否校验个体码</th>
				<th class="sort-column a.is_record_unit">是否记台</th>
				<th class="sort-column a.status">状态</th>
				<shiro:hasPermission name="akec:product:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td>
					${product.code}
			    </td>
				<td>
					${product.materialCode}
				</td>
				<td>
					${product.barCode}
				</td>
				<td>
						${product.materialDesc}
				</td>

				<td>
					${product.typeName}
				</td>

				<td>
					${product.seriesName}
				</td>
				<td>
						${product.materialSpeDesc}
				</td>


				<td>
						${product.integral}
				</td>
				<td>
					${product.standerSaleprice}
				</td>
				<td>
						${fns:getDictLabel(product.isVerifyIndividualcode, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(product.isRecordUnit, 'yes_no', '')}
				</td>

				<td>
					${fns:getDictLabel(product.status, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="akec:product:edit"><td>
    				<a href="${ctx}/akec/product/form?id=${product.id}">修改</a>
					 		</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>