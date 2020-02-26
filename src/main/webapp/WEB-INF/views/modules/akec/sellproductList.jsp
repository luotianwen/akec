<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>已售产品信息管理</title>
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
		<li class="active"><a href="${ctx}/akec/sellproduct/">已售产品信息列表</a></li>
		<shiro:hasPermission name="akec:sellproduct:edit"><li><a href="${ctx}/akec/sellproduct/form">已售产品信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sellproduct" action="${ctx}/akec/sellproduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品名称：</label>
				<form:input path="materialDesc" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>条形码：</label>
				<form:input path="barCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>产品个体码：</label>
				<form:input path="individualcode" htmlEscape="false" maxlength="56" class="input-medium"/>
			</li>
			<li><label>产品编码：</label>
				<form:input path="proudctCode" htmlEscape="false" maxlength="56" class="input-medium"/>
			</li>
			<li><label>材料编码：</label>
				<form:input path="materialCode" htmlEscape="false" maxlength="56" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品名称</th>
				<th>条形码</th>
				<th>产品规格</th>
				<th>产品类别</th>
				<th>产品系列</th>
				<th>产品批号</th>
				<th>经销商编码</th>
				<th>经销商名称</th>
				<th>业务统计伙计编码</th>
				<th>业务统计伙计名称</th>
				<th>产品个体码</th>
				<th>产品编码</th>
				<th>材料编码</th>
				<th>销售类型</th>
				<shiro:hasPermission name="akec:sellproduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sellproduct">
			<tr>
				<td><a href="${ctx}/akec/sellproduct/form?id=${sellproduct.id}">
					${sellproduct.materialDesc}
				</a></td>
				<td>
					${sellproduct.barCode}
				</td>
				<td>
					${sellproduct.materialSpecificationDesc}
				</td>
				<td>
					${sellproduct.typeCode}
				</td>
				<td>
					${sellproduct.series}
				</td>
				<td>
					${sellproduct.batchCode}
				</td>
				<td>
					${sellproduct.dealerCode}
				</td>
				<td>
					${sellproduct.dealerName}
				</td>
				<td>
					${sellproduct.businessDealerCode}
				</td>
				<td>
					${sellproduct.businessDealerName}
				</td>
				<td>
					${sellproduct.individualcode}
				</td>
				<td>
					${sellproduct.proudctCode}
				</td>
				<td>
					${sellproduct.materialCode}
				</td>
				<td>
					${sellproduct.saleType}
				</td>
				<shiro:hasPermission name="akec:sellproduct:edit"><td>
    				<a href="${ctx}/akec/sellproduct/form?id=${sellproduct.id}">修改</a>
					<a href="${ctx}/akec/sellproduct/delete?id=${sellproduct.id}" onclick="return confirmx('确认要删除该已售产品信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>