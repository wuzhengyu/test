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
        
    <title>密码修改</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">
	

  </head>
    <script type="text/javascript">
    var userId = "${user.id}";
    $(document).ready(function(){
    
	 })
	 
	function ajaxCheckPwd(){
		var url = "${ctx}/user/checkPwd";
		$.ajax({
              type: "POST",
              url:url,
              data:{userId:userId,password:$("#password").val()},
              async: true,
              error: function(request) {
              	
              },
              success: function(data) {
              	if(data){
              		$("#required_div").hide();
              		$("#intput_pwd_error_div").hide();
              	}else{
              		$("#required_div").hide();
              		$("#intput_pwd_error_div").show();
              		$("#password").focus();
              	}
              }
          });
	}
	
	
	function ajaxSubmitForm(){
		var pwd_one = $("#pwd_one").val();
		var pwd_two = $("#pwd_two").val();
		
		if(pwd_one==""||pwd_two==""){
			$("#required_div").show();
			return false;
		}
		
		if(pwd_one==pwd_two){
			var url = "${ctx}/user/changePassword";
			$.ajax({
	              type: "POST",
	              url:url,
	              data:{userId:userId,newPassword:pwd_one},
	              async: true,
	              error: function(request) {
	              	
	              },
	              success: function(data) {
	              	if(data){
	              		$("#intput_pwd_notequal_div").hide();
	              		$("#required_div").hide();
	              		$("#success_div").show();
	              	}else{
	              		$("#intput_pwd_notequal_div").hide();
	              		$("#required_div").hide();
	              		$("#error_div").show();
	              	}
	              }
	          });
		}else{
			$("#required_div").hide();
			$("#intput_pwd_notequal_div").show();
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
					<li class="active"><a href="javascript:void(0)"><font style="color:#A59999">修改密码</font>
					</a></li>
				</ul>
				<form class="form-horizontal" action="${ctx}/user/update" id="editForm">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">旧密码</label>
						<div class="col-sm-10">
							<input type="password" onblur="ajaxCheckPwd()" class="form-control" id="password" name="password">
						</div>
					</div>
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">新密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pwd_one" name="pwd_one">
						</div>
					</div>
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">再次输入新密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pwd_two" name="pwd_two">
						</div>
					</div>
					
					 <div id="required_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
                    	<strong id="error_msg">请输入密码！</strong>
                	</div>
				
					 <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					     <button type="button" class="btn btn-default" onclick="goBack()">返回</button>
						 <button type="button" onclick="ajaxSubmitForm()" class="btn btn-primary">保存</button>
					    </div>
					  </div>
			   	<div id="intput_pwd_error_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
                    <strong id="error_msg">输入的密码不正确！</strong>
                </div>
                
                <div id="intput_pwd_notequal_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
                    <strong id="error_msg">输入的密码不一致！</strong>
                </div>
                
                 <div id="error_div" class="col-sm-6 col-sm-offset-2 alert alert-success alert-dismissable" style="display: none">
                    <strong id="success_msg">操作失败！</strong>
                </div>
                <div id="success_div" class="col-sm-6 col-sm-offset-2 alert alert-success alert-dismissable" style="display: none">
                    <strong id="success_msg">操作成功！</strong>
                </div>
				</form>
			</div>
	</div>
	    <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
		<script src="${ctx}/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
  </body>
</html>

