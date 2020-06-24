<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报台信息管理</title>
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
		<li class="active"><a href="${ctx}/akec/reportStandbook/queryGetiReportStandbook">报台信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="reportStandbookProductDetail" action="${ctx}/akec/reportStandbook/queryGetiReportStandbook" method="post" class="breadcrumb form-search">

		<ul class="ul-form">

			<li><label>个体码：</label>
				<form:input path="individualcode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>

			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th   >类型</th>
				<th   >报台日期</th>
				<th  >医院</th>
				<th   >产品个体码</th>
				<th>产品名称</th>
				<th  >报台人</th>
				<th  >查看</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${reportStandbooks}" var="reportStandbook">
			<tr>
				<td>
						${reportStandbook.report.surgeryGrade}
				</td>
				<td>
					<fmt:formatDate value="${reportStandbook.report.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>


				<td>
					${reportStandbook.report.hospitalName}
				</td>
				<td>
						${reportStandbook.individualcode}
				</td>
				<td>
						${reportStandbook.product.materialDesc}
				</td>
				<td>
					${reportStandbook.remarks}
				</td>
				 <td>
					<a href="${ctx}/akec/reportStandbook/form?id=${reportStandbook.report.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

</body>
</html>