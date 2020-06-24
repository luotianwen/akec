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
		<%--<shiro:hasPermission name="akec:sellproduct:edit"><li><a href="${ctx}/akec/sellproduct/form">已售产品信息添加</a></li></shiro:hasPermission>--%>
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<a href="${ctx}/akec/sellproduct/tball"> <input  class="btn btn-primary" type="button" value="同步所有已销售产品"/></a>
				<a href="${ctx}/akec/sellproduct/tbday"> <input  class="btn btn-primary" type="button" value="同步当天已销售产品"/></a>
			</li>

			<li class="clearfix"></li>
		</ul>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column a.material_desc">产品名称</th>
				<th class="sort-column a.bar_code">条形码</th>
				<th class="sort-column a.material_specification_desc">产品规格</th>
				<th class="sort-column a.type_code">产品类别</th>
				<th class="sort-column a.series">产品系列</th>
				<th class="sort-column a.batch_code">产品批号</th>
				<th class="sort-column a.individualcode">产品个体码</th>

				<th class="sort-column a.dealer_code">经销商编码</th>
				<th class="sort-column a.dealer_name">经销商名称</th>
				<th class="sort-column a.business_dealer_code">业务统计伙计编码</th>
				<th>业务统计伙计名称</th>
			     <th class="sort-column a.proudct_code">产品编码</th>
				<th class="sort-column a.material_code">材料编码</th>
				<th>销售类型</th>

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
						${sellproduct.individualcode}
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
					${sellproduct.proudctCode}
				</td>
				<td>
					${sellproduct.materialCode}
				</td>
				<td>
					${sellproduct.saleType}
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>