<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>常用参数管理</title>
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
		<li><a href="${ctx}/akec/basedataType/">常用参数列表</a></li>
		<li class="active"><a href="${ctx}/akec/basedataType/form?id=${basedataType.id}">常用参数<shiro:hasPermission name="akec:basedataType:edit">${not empty basedataType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="akec:basedataType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="basedataType" action="${ctx}/akec/basedataType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">编码：</label>
			<div class="controls">
				<form:input path="baseTypeCode" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="baseTypeName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">系统参数：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>参数编码</th>
								<th>参数名称</th>
								<th>状态</th>
								<th>序号</th>
								<shiro:hasPermission name="akec:basedataType:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="basedataList">
						</tbody>
						<shiro:hasPermission name="akec:basedataType:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#basedataList', basedataRowIdx, basedataTpl);basedataRowIdx = basedataRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="basedataTpl">//<!--
						<tr id="basedataList{{idx}}">
							<td class="hide">
								<input id="basedataList{{idx}}_id" name="basedataList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="basedataList{{idx}}_delFlag" name="basedataList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="basedataList{{idx}}_paramCode" name="basedataList[{{idx}}].paramCode" type="text" value="{{row.paramCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="basedataList{{idx}}_paramName" name="basedataList[{{idx}}].paramName" type="text" value="{{row.paramName}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<select id="basedataList{{idx}}_status" name="basedataList[{{idx}}].status" data-value="{{row.status}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('yes_no')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="basedataList{{idx}}_seqno" name="basedataList[{{idx}}].seqno" type="text" value="{{row.seqno}}" class="input-small "/>
							</td>
							<shiro:hasPermission name="akec:basedataType:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#basedataList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var basedataRowIdx = 0, basedataTpl = $("#basedataTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(basedataType.basedataList)};
							for (var i=0; i<data.length; i++){
								addRow('#basedataList', basedataRowIdx, basedataTpl, data[i]);
								basedataRowIdx = basedataRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="akec:basedataType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>