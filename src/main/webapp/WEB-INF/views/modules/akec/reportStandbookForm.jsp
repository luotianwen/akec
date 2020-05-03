<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报台信息管理</title>
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
		<li><a href="${ctx}/akec/reportStandbook/">报台信息列表</a></li>
		<li class="active"><a href="${ctx}/akec/reportStandbook/form?id=${reportStandbook.id}">报台信息<shiro:hasPermission name="akec:reportStandbook:edit">${not empty reportStandbook.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="akec:reportStandbook:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="reportStandbook" action="${ctx}/akec/reportStandbook/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<form:hidden path="hospitalId"/>
		<form:hidden path="provinceCode"/>
		<form:hidden path="cityCode"/>
		<div class="control-group">
			<label class="control-label">手术日期：</label>
			<div class="controls">
				<input name="operateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${reportStandbook.operateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>


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
		<div class="control-group">
			<label class="control-label">医院：</label>
			<div class="controls">
				<form:input path="hospitalName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医生：</label>
			<div class="controls">
				<form:input path="doctorName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者性别：</label>
			<div class="controls">
				<form:select path="patientSex" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者年龄：</label>
			<div class="controls">
				<form:input path="patientAge" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手术类别：</label>
			<div class="controls">
				<form:select path="surgeryId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${surgeryIds}" itemLabel="paramName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报台人：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>

		<form:hidden path="type"/>
		<div class="control-group">
			<label class="control-label">报台类型：</label>
			<div class="controls">
				<c:if test="${reportStandbook.type=='1' }">
					直属/子公司人员报台
				</c:if>
				<c:if test="${reportStandbook.type=='2' }">
					经销商报台
				</c:if>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">台数：</label>
			<div class="controls">
				<form:input path="unitCount" htmlEscape="false" class="input-xlarge "/>
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
			<label class="control-label">建议：</label>
			<div class="controls">
				<form:textarea path="suggest" htmlEscape="false" rows="4" maxlength="1000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报台人类型：</label>
			<div class="controls">
				<form:input path="user.baseReportName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报台人单位：</label>
			<div class="controls">
				<form:input path="user.dealerName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">报台评分信息：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>评分名称</th>
								<th>评分</th>
								<shiro:hasPermission name="akec:reportStandbook:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="reportStandbookGradeDetailList">
						</tbody>

					</table>
					<script type="text/template" id="reportStandbookGradeDetailTpl">//<!--
						<tr id="reportStandbookGradeDetailList{{idx}}">
							<td class="hide">
								<input id="reportStandbookGradeDetailList{{idx}}_id" name="reportStandbookGradeDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="reportStandbookGradeDetailList{{idx}}_delFlag" name="reportStandbookGradeDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
								<input id="reportStandbookGradeDetailList{{idx}}_gradeId" name="reportStandbookGradeDetailList[{{idx}}].gradeId" type="hidden" value="{{row.gradeId}}"/>
							</td>
							<td>
								{{row.gradeName}}	</td>
							<td>
								{{row.grade}}
							</td>


						</tr>//-->
					</script>
					<script type="text/javascript">
						var reportStandbookGradeDetailRowIdx = 0, reportStandbookGradeDetailTpl = $("#reportStandbookGradeDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(reportStandbook.reportStandbookGradeDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#reportStandbookGradeDetailList', reportStandbookGradeDetailRowIdx, reportStandbookGradeDetailTpl, data[i]);
								reportStandbookGradeDetailRowIdx = reportStandbookGradeDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">报台图片信息：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>图片</th>
								<shiro:hasPermission name="akec:reportStandbook:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="reportStandbookImageDetailList">
						</tbody>

					</table>
					<script type="text/template" id="reportStandbookImageDetailTpl">//<!--
						<tr id="reportStandbookImageDetailList{{idx}}">
							<td class="hide">
								<input id="reportStandbookImageDetailList{{idx}}_id" name="reportStandbookImageDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="reportStandbookImageDetailList{{idx}}_delFlag" name="reportStandbookImageDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
							    <img src="{{row.reportImgUrl}}" width="200px" height="200px"  >
								<input id="reportStandbookImageDetailList{{idx}}_reportImgUrl" name="reportStandbookImageDetailList[{{idx}}].reportImgUrl" type="hidden" value="{{row.reportImgUrl}}" maxlength="100"/>
							</td>

						</tr>//-->
					</script>
					<script type="text/javascript">
						var reportStandbookImageDetailRowIdx = 0, reportStandbookImageDetailTpl = $("#reportStandbookImageDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(reportStandbook.reportStandbookImageDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#reportStandbookImageDetailList', reportStandbookImageDetailRowIdx, reportStandbookImageDetailTpl, data[i]);
								reportStandbookImageDetailRowIdx = reportStandbookImageDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">报台产品表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>

								<th>
									产品编码
								</th>
								<th>
									个体码
								</th>
								<th>
									条形码
								</th>
								<th>
									产品名称
								</th>
								<th>
									配送经销商名称
								</th>
								<th>
									是否记台
								</th>
								<th>
									是否校验个体码
								</th>
								<th>
									是否返利
								</th>

								<th>
									生产日期
								</th>
								<th>
									失效日期
								</td>

							</tr>
						</thead>
						<tbody id="reportStandbookProductDetailList">
						</tbody>

					</table>
					<script type="text/template" id="reportStandbookProductDetailTpl">//<!--
						<tr id="reportStandbookProductDetailList{{idx}}">
							<td class="hide">
								<input id="reportStandbookProductDetailList{{idx}}_id" name="reportStandbookProductDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="reportStandbookProductDetailList{{idx}}_delFlag" name="reportStandbookProductDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								{{row.product.code}}	</td>
							<td>
								{{row.individualcode}}
							 </td>
							 <td>
									{{row.product.barCode}}
								</td>
								<td>
									{{row.product.materialDesc}}
								</td>
								<td>
									{{row.sellproduct.dealerName}}
								</td>
								<td>
								<select  disabled data-value="{{row.isRecordUnit}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('yes_no')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>

								</td>
								<td>
								<select  disabled data-value="{{row.isVerifyIndividualcode}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('yes_no')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>

								</td>
								<td>
								{{row.integral}}

								</td>
								<td>
									{{row.produceDate }}
								</td>
								<td>
									{{row.outdate }}
								</td>



						</tr>//-->
					</script>
					<script type="text/javascript">
						var reportStandbookProductDetailRowIdx = 0, reportStandbookProductDetailTpl = $("#reportStandbookProductDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(reportStandbook.reportStandbookProductDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#reportStandbookProductDetailList', reportStandbookProductDetailRowIdx, reportStandbookProductDetailTpl, data[i]);
								reportStandbookProductDetailRowIdx = reportStandbookProductDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="akec:reportStandbook:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>