<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>APP用户管理管理</title>
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
        var fid="";
        function cpass(id){
            fid=id;
            $('#tmyModal').modal();
		}
        function tcheckDeliver() {
            var pass = $("#pass").val();
            if (pass == '') {
                top.$.jBox.alert("请填写密码");
                return;
            }

            $("#searchForm").attr("action", "${ctx}/akec/appUser/pass?id=" + fid);

            $("#searchForm").submit();


        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/akec/appUser/">APP用户管理列表</a></li>
		<shiro:hasPermission name="akec:appUser:edit"><li><a href="${ctx}/akec/appUser/form">APP用户管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="appUser" action="${ctx}/akec/appUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>账号名：</label>
				<form:input path="account" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>经销商：</label>
				<form:input path="dealerName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="auditStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>	报台人类型：</label>
				<form:select path="baseReportId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${basedataList}" itemLabel="paramName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>允许输入：</label>
				<form:select path="inputFlag" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>

		<div class="modal fade" id="tmyModal" tabindex="-1" role="dialog" aria-labelledby="tmyModalLabel"
			 aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="tmyModalLabel">
							重置密码
						</h4>
					</div>
					<div class="modal-body">
						<label class="span1 control-label">密码：</label>
						<div class="span2 ">
							<form:input path="pass" class="input-medium "/>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="tcheckDeliver()" data-dismiss="modal">
							确认
						</button>
					</div>
				</div>
			</div>
		</div>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column account">账号名</th>
				<th class="sort-column name">用户名</th>
				<th class="sort-column base_report_id">报台人类型</th>
				<th class="sort-column dealer_id">所属单位</th>
				<th class="sort-column create_date" >创建时间</th>
				<th class="sort-column audit_status">状态</th>
				<th class="sort-column input_flag">允许输入</th>

				<shiro:hasPermission name="akec:appUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="appUser">
			<tr>
				<td>
					${appUser.account}
				 </td>
				<td>
					${appUser.name}
				</td>
				<td>
						${appUser.baseReportName}
				</td>

				<td>
					${appUser.dealerName}
				</td>
				<td>
					<fmt:formatDate value="${appUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fns:getDictLabel(appUser.auditStatus, 'yes_no', '')}
				</td>
				<td>
						${fns:getDictLabel(appUser.inputFlag, 'yes_no', '')}
				</td>

				<shiro:hasPermission name="akec:appUser:edit"><td>
    				<a href="${ctx}/akec/appUser/form?id=${appUser.id}">修改</a>
					<a style="cursor: pointer;" onclick="cpass('${appUser.id}')" )>重置密码</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>