<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>已售产品信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/akec/sellproduct/">已售产品信息列表</a></li>
		<li class="active"><a href="${ctx}/akec/sellproduct/form?id=${sellproduct.id}">已售产品信息<shiro:hasPermission name="akec:sellproduct:edit">${not empty sellproduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="akec:sellproduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sellproduct" action="${ctx}/akec/sellproduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<form:input path="materialDesc" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条形码：</label>
			<div class="controls">
				<form:input path="barCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品规格：</label>
			<div class="controls">
				<form:input path="materialSpecificationDesc" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品类别：</label>
			<div class="controls">
				<form:input path="typeCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品系列：</label>
			<div class="controls">
				<form:input path="series" htmlEscape="false" maxlength="56" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品批号：</label>
			<div class="controls">
				<form:input path="batchCode" htmlEscape="false" maxlength="56" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经销商编码：</label>
			<div class="controls">
				<form:input path="dealerCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经销商名称：</label>
			<div class="controls">
				<form:input path="dealerName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务统计伙计编码：</label>
			<div class="controls">
				<form:input path="businessDealerCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务统计伙计名称：</label>
			<div class="controls">
				<form:input path="businessDealerName" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品个体码：</label>
			<div class="controls">
				<form:input path="individualcode" htmlEscape="false" maxlength="56" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品编码：</label>
			<div class="controls">
				<form:input path="proudctCode" htmlEscape="false" maxlength="56" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料编码：</label>
			<div class="controls">
				<form:input path="materialCode" htmlEscape="false" maxlength="56" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="comments" htmlEscape="false" maxlength="800" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售类型：</label>
			<div class="controls">
				<form:input path="saleType" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="akec:sellproduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>