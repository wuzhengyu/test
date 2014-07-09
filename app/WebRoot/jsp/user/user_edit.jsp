<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
        
    <title>用户详细</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">
	

  </head>
    <script type="text/javascript">
    $(document).ready(function(){
    
	 })
	 
	 
	  //检查用户是否存在
		function  checkUser(){
			var flag = false;
			var url="${ctx}/user/checkUser";
			$.ajax({
				 type: "POST",
				 async:false,
	             url: url,
	             data: {username:$("#username").val()},
	             dataType: "json",
	             success: function(data){
	            	 if(data){
	            	 	 $("#required_div").hide();
	            	 	 $("#exists_div").hide();
	            		 $("#exists_div").show();
	            		 $("#username").val("");
	            		 flag = true;
	            	 }
	             }
			});
			return flag;
	} 
	 
	function ajaxSubmitForm(){
		var selectRoles = $("#selectRoles").val();
		//alert(selectRoles);
		//return false;
		var f = true;
		var usernameOld = "${user.username}";
		var usernameNew = $("#username").val();
		if(usernameNew==""){
			$("#exists_div").hide();
			$("#required_div").show();
			return false;
		}
		
		if(usernameNew==usernameOld){
			f = false;
		}else{
			f = checkUser();
		}
		
		if(!f){
			var url = "${ctx}/user/update";
			$.ajax({
	              type: "POST",
	              url:url,
	              data:$("#editForm").serialize(),// 你的formid
	              async: false,
	              error: function(request) {
	              	
	              },
	              success: function(data) {
	              	if(data){
	              		$("#required_div").hide();
	              		$("#exists_div").hide();
	              		$("#success_div").show();
	              	}else{
	              		$("#exists_div").hide();
	              		$("#required_div").hide();
	              		$("#success_div").hide();
	              		$("#error_div").show();
	              	}
	              }
	          });
		}
	}
	
	
  	function goBack(){
    	var url = "${ctx}/user/findUserPage?currentPage=1&pageSize=10";
    	window.location.href = url;
    }
  				
  </script>
  
  <body>
		<div class="container-fluid">
		<div class="row-fluid">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0)"><font style="color:#A59999">用户编辑</font>
					</a></li>
				</ul>
				<form class="form-horizontal" action="${ctx}/user/update" id="editForm">
					  <input type="hidden" name="id" id="id" value="${user.id}"/>
					  <input type="hidden" name="password" id="password" value="${user.password}"/>
					  
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="username" name=username value="${user.username}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">所属角色</label>
								<c:forEach items="${roleList}" var="role">
						<div class="col-sm-10">
								<!-- 
							<select class="form-control" multiple="multiple" name="selectRoles" id="selectRoles">
									<option value="${role.id}" >${role.roleName}</option>
							</select>
								 -->
								  <input type="checkbox" name="checkRoles" value="${role.id}"
								  <c:forEach items="${userRoleList}" var="userRole">
								  		<c:if test="${role.id eq userRole.roleId}">
								  			checked
								  		</c:if>
								  </c:forEach>
								  /> ${role.roleName}
						</div>
								</c:forEach>
					</div>
					
					 <div id="required_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
                   		 <strong id="error_msg">请输入用户名</strong>
               		 </div>
				
					 <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					     <button type="button" class="btn btn-default" onclick="goBack()">返回</button>
						 <button type="button" onclick="ajaxSubmitForm()" class="btn btn-primary">保存</button>
					    </div>
					  </div>
			   <div id="error_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
                    <strong id="error_msg">操作失败！</strong>
                </div>
                
                <div id="exists_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
                    <strong id="error_msg">操作失败！改用户已存在！</strong>
                </div>
                
                <div id="success_div" class="col-sm-6 col-sm-offset-2 alert alert-success alert-dismissable" style="display: none">
                    <strong id="success_msg">操作成功！</strong>
                </div>
				</form>
				
				
			</div>
	</div>
	<%--
	    <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
	 --%>
		<script src="${ctx}/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
  </body>
</html>

