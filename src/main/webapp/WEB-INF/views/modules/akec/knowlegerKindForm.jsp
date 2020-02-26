<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>知识分类管理</title>
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
		<li><a href="${ctx}/akec/knowlegerKind/">知识分类列表</a></li>
		<li class="active"><a href="${ctx}/akec/knowlegerKind/form?id=${knowlegerKind.id}">知识分类<shiro:hasPermission name="akec:knowlegerKind:edit">${not empty knowlegerKind.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="akec:knowlegerKind:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="knowlegerKind" action="${ctx}/akec/knowlegerKind/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">知识分类：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="seqno" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">学习资料：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>标题</th>
								<th>链接地址</th>
								<th>序号</th>
								<th>状态</th>
								<th>发布日期</th>
								<shiro:hasPermission name="akec:knowlegerKind:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="studycenterList">
						</tbody>
						<shiro:hasPermission name="akec:knowlegerKind:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#studycenterList', studycenterRowIdx, studycenterTpl);studycenterRowIdx = studycenterRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="studycenterTpl">//<!--
						<tr id="studycenterList{{idx}}">
							<td class="hide">
								<input id="studycenterList{{idx}}_id" name="studycenterList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="studycenterList{{idx}}_delFlag" name="studycenterList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="studycenterList{{idx}}_title" name="studycenterList[{{idx}}].title" type="text" value="{{row.title}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="studycenterList{{idx}}_href" name="studycenterList[{{idx}}].href" type="text" value="{{row.href}}" maxlength="1000" class="input-small "/>
							</td>
							<td>
								<input id="studycenterList{{idx}}_seqno" name="studycenterList[{{idx}}].seqno" type="text" value="{{row.seqno}}" class="input-small "/>
							</td>
							<td>
								<input id="studycenterList{{idx}}_status" name="studycenterList[{{idx}}].status" type="text" value="{{row.status}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<input id="studycenterList{{idx}}_releaseDate" name="studycenterList[{{idx}}].releaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.releaseDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<shiro:hasPermission name="akec:knowlegerKind:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#studycenterList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var studycenterRowIdx = 0, studycenterTpl = $("#studycenterTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(knowlegerKind.studycenterList)};
							for (var i=0; i<data.length; i++){
								addRow('#studycenterList', studycenterRowIdx, studycenterTpl, data[i]);
								studycenterRowIdx = studycenterRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="akec:knowlegerKind:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>