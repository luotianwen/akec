<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医院管理管理</title>
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
		<li><a href="${ctx}/akec/hospital/">医院管理列表</a></li>
		<li class="active"><a href="${ctx}/akec/hospital/form?id=${hospital.id}">医院管理<shiro:hasPermission name="akec:hospital:edit">${not empty hospital.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="akec:hospital:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="hospital" action="${ctx}/akec/hospital/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">医院名称：</label>
			<div class="controls">
				<form:input path="hospitalName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">hospital_id：</label>
			<div class="controls">
				<form:input path="hospitalId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>--%>
		<%--<div class="control-group">
			<label class="control-label">province_code：</label>
			<div class="controls">
				<form:input path="provinceCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>--%>
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
		<form:hidden path="cityCode"/>
		<%--<div class="control-group">
			<label class="control-label">city_code：</label>
			<div class="controls">
				<form:input path="cityCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">创建人：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<form:hidden path="user.id"/>
		<%--<div class="control-group">
			<label class="control-label">user_id：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${hospital.user.id}" labelName="user.name" labelValue="${hospital.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">更新人：</label>
			<div class="controls">
				<form:input path="updateAdminName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">医院年度手术量：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>年度</th>
								<th>爱康台数</th>
								<th>产品类型</th>
								<th>类型名称</th>
								<th>备注</th>
								<shiro:hasPermission name="akec:hospital:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="hospitalCountDetailList">
						</tbody>
						<shiro:hasPermission name="akec:hospital:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#hospitalCountDetailList', hospitalCountDetailRowIdx, hospitalCountDetailTpl);hospitalCountDetailRowIdx = hospitalCountDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="hospitalCountDetailTpl">//<!--
						<tr id="hospitalCountDetailList{{idx}}">
							<td class="hide">
								<input id="hospitalCountDetailList{{idx}}_id" name="hospitalCountDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="hospitalCountDetailList{{idx}}_delFlag" name="hospitalCountDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="hospitalCountDetailList{{idx}}_year" name="hospitalCountDetailList[{{idx}}].year" type="text" value="{{row.year}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="hospitalCountDetailList{{idx}}_akCount" name="hospitalCountDetailList[{{idx}}].akCount" type="text" value="{{row.akCount}}" class="input-small  digits"/>
							</td>
							<td>
								<input id="hospitalCountDetailList{{idx}}_type" name="hospitalCountDetailList[{{idx}}].type" type="text" value="{{row.type}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="hospitalCountDetailList{{idx}}_typename" name="hospitalCountDetailList[{{idx}}].typename" type="text" value="{{row.typename}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="hospitalCountDetailList{{idx}}_remarks" name="hospitalCountDetailList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="akec:hospital:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#hospitalCountDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var hospitalCountDetailRowIdx = 0, hospitalCountDetailTpl = $("#hospitalCountDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(hospital.hospitalCountDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#hospitalCountDetailList', hospitalCountDetailRowIdx, hospitalCountDetailTpl, data[i]);
								hospitalCountDetailRowIdx = hospitalCountDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">医院产品入院信息：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>是否入院</th>
								<th>产品类型</th>
								<th>类型名称</th>
								<th>备注</th>
								<shiro:hasPermission name="akec:hospital:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="hospitalProductDetailList">
						</tbody>
						<shiro:hasPermission name="akec:hospital:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#hospitalProductDetailList', hospitalProductDetailRowIdx, hospitalProductDetailTpl);hospitalProductDetailRowIdx = hospitalProductDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="hospitalProductDetailTpl">//<!--
						<tr id="hospitalProductDetailList{{idx}}">
							<td class="hide">
								<input id="hospitalProductDetailList{{idx}}_id" name="hospitalProductDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="hospitalProductDetailList{{idx}}_delFlag" name="hospitalProductDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<select id="hospitalProductDetailList{{idx}}_status" name="hospitalProductDetailList[{{idx}}].status" data-value="{{row.status}}" class="input-small required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('yes_no')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="hospitalProductDetailList{{idx}}_type" name="hospitalProductDetailList[{{idx}}].type" type="text" value="{{row.type}}" maxlength="255" class="input-small required"/>
							</td>
							<td>
								<input id="hospitalProductDetailList{{idx}}_typename" name="hospitalProductDetailList[{{idx}}].typename" type="text" value="{{row.typename}}" maxlength="255" class="input-small required"/>
							</td>
							<td>
								<textarea id="hospitalProductDetailList{{idx}}_remarks" name="hospitalProductDetailList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="akec:hospital:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#hospitalProductDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var hospitalProductDetailRowIdx = 0, hospitalProductDetailTpl = $("#hospitalProductDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(hospital.hospitalProductDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#hospitalProductDetailList', hospitalProductDetailRowIdx, hospitalProductDetailTpl, data[i]);
								hospitalProductDetailRowIdx = hospitalProductDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="akec:hospital:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>