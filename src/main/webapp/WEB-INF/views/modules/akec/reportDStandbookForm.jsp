<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待提交报台管理</title>
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
		<li><a href="${ctx}/akec/reportDStandbook/">待提交报台列表</a></li>
		<li class="active"><a href="${ctx}/akec/reportDStandbook/form?id=${reportDStandbook.id}">待提交报台<shiro:hasPermission name="akec:reportDStandbook:edit">${not empty reportDStandbook.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="akec:reportDStandbook:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="reportDStandbook" action="${ctx}/akec/reportDStandbook/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">operate_date：</label>
			<div class="controls">
				<input name="operateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${reportDStandbook.operateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">hospital_name：</label>
			<div class="controls">
				<form:input path="hospitalName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">hospital_id：</label>
			<div class="controls">
				<form:input path="hospitalId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">province_code：</label>
			<div class="controls">
				<form:input path="provinceCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">province_name：</label>
			<div class="controls">
				<form:input path="provinceName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">city_name：</label>
			<div class="controls">
				<form:input path="cityName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">city_code：</label>
			<div class="controls">
				<form:input path="cityCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">doctor_name：</label>
			<div class="controls">
				<form:input path="doctorName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">patient_sex：</label>
			<div class="controls">
				<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">patient_age：</label>
			<div class="controls">
				<form:input path="patientAge" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">surgery_id：</label>
			<div class="controls">
				<form:input path="surgeryId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">surgery_grade：</label>
			<div class="controls">
				<form:input path="surgeryGrade" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">user_name：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">user_id：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${reportDStandbook.user.id}" labelName="user.name" labelValue="${reportDStandbook.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">type：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">unit_count：</label>
			<div class="controls">
				<form:input path="unitCount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">status：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">suggest：</label>
			<div class="controls">
				<form:input path="suggest" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">update_admin_name：</label>
			<div class="controls">
				<form:input path="updateAdminName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">dealer_name：</label>
			<div class="controls">
				<form:input path="dealerName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">patient_name：</label>
			<div class="controls">
				<form:input path="patientName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">待提交报台评分：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>grade</th>
								<th>grade_id</th>
								<shiro:hasPermission name="akec:reportDStandbook:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="reportDStandbookGradeDetailList">
						</tbody>
						<shiro:hasPermission name="akec:reportDStandbook:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#reportDStandbookGradeDetailList', reportDStandbookGradeDetailRowIdx, reportDStandbookGradeDetailTpl);reportDStandbookGradeDetailRowIdx = reportDStandbookGradeDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="reportDStandbookGradeDetailTpl">//<!--
						<tr id="reportDStandbookGradeDetailList{{idx}}">
							<td class="hide">
								<input id="reportDStandbookGradeDetailList{{idx}}_id" name="reportDStandbookGradeDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="reportDStandbookGradeDetailList{{idx}}_delFlag" name="reportDStandbookGradeDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="reportDStandbookGradeDetailList{{idx}}_grade" name="reportDStandbookGradeDetailList[{{idx}}].grade" type="text" value="{{row.grade}}" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookGradeDetailList{{idx}}_gradeId" name="reportDStandbookGradeDetailList[{{idx}}].gradeId" type="text" value="{{row.gradeId}}" maxlength="32" class="input-small "/>
							</td>
							<shiro:hasPermission name="akec:reportDStandbook:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#reportDStandbookGradeDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var reportDStandbookGradeDetailRowIdx = 0, reportDStandbookGradeDetailTpl = $("#reportDStandbookGradeDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(reportDStandbook.reportDStandbookGradeDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#reportDStandbookGradeDetailList', reportDStandbookGradeDetailRowIdx, reportDStandbookGradeDetailTpl, data[i]);
								reportDStandbookGradeDetailRowIdx = reportDStandbookGradeDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">待提交报台图片：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>report_img_url</th>
								<th>add_date</th>
								<shiro:hasPermission name="akec:reportDStandbook:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="reportDStandbookImageDetailList">
						</tbody>
						<shiro:hasPermission name="akec:reportDStandbook:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#reportDStandbookImageDetailList', reportDStandbookImageDetailRowIdx, reportDStandbookImageDetailTpl);reportDStandbookImageDetailRowIdx = reportDStandbookImageDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="reportDStandbookImageDetailTpl">//<!--
						<tr id="reportDStandbookImageDetailList{{idx}}">
							<td class="hide">
								<input id="reportDStandbookImageDetailList{{idx}}_id" name="reportDStandbookImageDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="reportDStandbookImageDetailList{{idx}}_delFlag" name="reportDStandbookImageDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="reportDStandbookImageDetailList{{idx}}_reportImgUrl" name="reportDStandbookImageDetailList[{{idx}}].reportImgUrl" type="text" value="{{row.reportImgUrl}}" maxlength="100" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookImageDetailList{{idx}}_addDate" name="reportDStandbookImageDetailList[{{idx}}].addDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.addDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<shiro:hasPermission name="akec:reportDStandbook:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#reportDStandbookImageDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var reportDStandbookImageDetailRowIdx = 0, reportDStandbookImageDetailTpl = $("#reportDStandbookImageDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(reportDStandbook.reportDStandbookImageDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#reportDStandbookImageDetailList', reportDStandbookImageDetailRowIdx, reportDStandbookImageDetailTpl, data[i]);
								reportDStandbookImageDetailRowIdx = reportDStandbookImageDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">待提交报台产品表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>产品ID（参照产品表主键）</th>
								<th>产品个体码</th>
								<th>积分(根据经销商是否记分)</th>
								<th>备注</th>
								<th>1：是，0：否</th>
								<th>1：是，0：否</th>
								<th>scan_code</th>
								<th>produce_date</th>
								<th>outdate</th>
								<shiro:hasPermission name="akec:reportDStandbook:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="reportDStandbookProductDetailList">
						</tbody>
						<shiro:hasPermission name="akec:reportDStandbook:edit"><tfoot>
							<tr><td colspan="11"><a href="javascript:" onclick="addRow('#reportDStandbookProductDetailList', reportDStandbookProductDetailRowIdx, reportDStandbookProductDetailTpl);reportDStandbookProductDetailRowIdx = reportDStandbookProductDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="reportDStandbookProductDetailTpl">//<!--
						<tr id="reportDStandbookProductDetailList{{idx}}">
							<td class="hide">
								<input id="reportDStandbookProductDetailList{{idx}}_id" name="reportDStandbookProductDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="reportDStandbookProductDetailList{{idx}}_delFlag" name="reportDStandbookProductDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_productId" name="reportDStandbookProductDetailList[{{idx}}].productId" type="text" value="{{row.productId}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_individualcode" name="reportDStandbookProductDetailList[{{idx}}].individualcode" type="text" value="{{row.individualcode}}" maxlength="100" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_integral" name="reportDStandbookProductDetailList[{{idx}}].integral" type="text" value="{{row.integral}}" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_note" name="reportDStandbookProductDetailList[{{idx}}].note" type="text" value="{{row.note}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_isRecordUnit" name="reportDStandbookProductDetailList[{{idx}}].isRecordUnit" type="text" value="{{row.isRecordUnit}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_isVerifyIndividualcode" name="reportDStandbookProductDetailList[{{idx}}].isVerifyIndividualcode" type="text" value="{{row.isVerifyIndividualcode}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_scanCode" name="reportDStandbookProductDetailList[{{idx}}].scanCode" type="text" value="{{row.scanCode}}" maxlength="100" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_produceDate" name="reportDStandbookProductDetailList[{{idx}}].produceDate" type="text" value="{{row.produceDate}}" maxlength="6" class="input-small "/>
							</td>
							<td>
								<input id="reportDStandbookProductDetailList{{idx}}_outdate" name="reportDStandbookProductDetailList[{{idx}}].outdate" type="text" value="{{row.outdate}}" maxlength="6" class="input-small "/>
							</td>
							<shiro:hasPermission name="akec:reportDStandbook:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#reportDStandbookProductDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var reportDStandbookProductDetailRowIdx = 0, reportDStandbookProductDetailTpl = $("#reportDStandbookProductDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(reportDStandbook.reportDStandbookProductDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#reportDStandbookProductDetailList', reportDStandbookProductDetailRowIdx, reportDStandbookProductDetailTpl, data[i]);
								reportDStandbookProductDetailRowIdx = reportDStandbookProductDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="akec:reportDStandbook:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>