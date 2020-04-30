<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手术报数管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/akec/reportStandbookOperation/">手术报数列表</a></li>
		<li class="active"><a href="${ctx}/akec/reportStandbookOperation/form?id=${reportStandbookOperation.id}">手术报数<shiro:hasPermission name="akec:reportStandbookOperation:edit">${not empty reportStandbookOperation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="akec:reportStandbookOperation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="reportStandbookOperation" action="${ctx}/akec/reportStandbookOperation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="hospitalId"/>

		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">手术日期：</label>
			<div class="controls">
				<input name="operateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${reportStandbookOperation.operateDate}" pattern="yyyy-MM-dd"/>"
					 />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手术日期：</label>
			<div class="controls">
				<input name="operateEdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${reportStandbookOperation.operateEdate}" pattern="yyyy-MM-dd"/>"
					 />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医院名称：</label>
			<div class="controls">
				<form:input path="hospitalName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<form:hidden path="provinceCode"/>

		<div class="control-group">
			<label class="control-label">省：</label>
			<div class="controls">
				<form:input path="provinceName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">市：</label>
			<div class="controls">
				<form:input path="cityName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<form:hidden path="user.id"/>
		<div class="control-group">
			<label class="control-label">用户：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">医院总台数：</label>
			<div class="controls">
				<form:input path="unitCount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否提交：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新人：</label>
			<div class="controls">
				<form:input path="updateAdminName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">爱康总台数：</label>
			<div class="controls">
				<form:input path="akCount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">爱康手术：</label>
			<div class="controls">
				<form:select path="ak" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">手术产品表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>医生</th>
								<th>爱康总数</th>

								<th>类型名称</th>
								<th>备注</th>

							</tr>
						</thead>
						<tbody id="reportStandbookOperationDetailList">
						</tbody>

					</table>
					<script type="text/template" id="reportStandbookOperationDetailTpl">//<!--
						<tr id="reportStandbookOperationDetailList{{idx}}">
							<td class="hide">
								<input id="reportStandbookOperationDetailList{{idx}}_id" name="reportStandbookOperationDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="reportStandbookOperationDetailList{{idx}}_delFlag" name="reportStandbookOperationDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
								<input id="reportStandbookOperationDetailList{{idx}}_type" name="reportStandbookOperationDetailList[{{idx}}].type" type="hidden" value="{{row.type}}"/>

							</td>
							<td>
								<input id="reportStandbookOperationDetailList{{idx}}_doctor" name="reportStandbookOperationDetailList[{{idx}}].doctor" type="text" value="{{row.doctor}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="reportStandbookOperationDetailList{{idx}}_akCount" name="reportStandbookOperationDetailList[{{idx}}].akCount" type="text" value="{{row.akCount}}" class="input-small "/>
							</td>

							<td>
								<input id="reportStandbookOperationDetailList{{idx}}_typename" name="reportStandbookOperationDetailList[{{idx}}].typename" type="text" value="{{row.typename}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="reportStandbookOperationDetailList{{idx}}_remarks" name="reportStandbookOperationDetailList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>

						</tr>//-->
					</script>
					<script type="text/javascript">
						var reportStandbookOperationDetailRowIdx = 0, reportStandbookOperationDetailTpl = $("#reportStandbookOperationDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(reportStandbookOperation.reportStandbookOperationDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#reportStandbookOperationDetailList', reportStandbookOperationDetailRowIdx, reportStandbookOperationDetailTpl, data[i]);
								reportStandbookOperationDetailRowIdx = reportStandbookOperationDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="akec:reportStandbookOperation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>