<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>APP用户管理管理</title>
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
		<li><a href="${ctx}/akec/appUser/">APP用户管理列表</a></li>
		<li class="active"><a href="${ctx}/akec/appUser/form?id=${appUser.id}">APP用户管理<shiro:hasPermission name="akec:appUser:edit">${not empty appUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="akec:appUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="appUser" action="${ctx}/akec/appUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="createType"/>
		<form:hidden path="pass"/>
		<form:hidden path="adminName"/>
		<form:hidden path="dealerName"/>

		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">账号名：</label>
			<div class="controls">
				<form:input path="account" htmlEscape="false" maxlength="20" class="input-xlarge  required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">经销商：</label>
			<div class="controls">
				<form:select path="dealerId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${dealers}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册所属单位名字：</label>
			<div class="controls">
				<form:input path="registerDealerName" htmlEscape="false" maxlength="80" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">报台人类型：</label>
			<div class="controls">
				<form:select path="baseReportId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${basedataList}" itemLabel="paramName" itemValue="id" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用状态：</label>
			<div class="controls">
				<form:radiobuttons path="auditStatus" cssClass="required" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>



			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="note" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核时间：</label>
			<div class="controls">
				<input name="auditDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${appUser.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">允许输入：</label>
			<div class="controls">
				<form:radiobuttons path="inputFlag" cssClass="required"  items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="akec:appUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>