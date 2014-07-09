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
        
    <title>用户注册</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">
	

  </head>
    <script type="text/javascript">
    $(document).ready(function(){
    
	 })
	 
	 
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
            		 $("#exists_div").show();
            		 $("#username").val("");
            		 flag = true;
            	 }
             }
		});
		return flag;
	} 
	 
	function ajaxSubmitForm(){
		if($("#username").val()==""){
			$("#exists_div").hide();
			$("#required_div").show();
			return false;
		}
		var f = checkUser();
		if(!f){
			var url = "${ctx}/user/save";
			$.ajax({
	              type: "POST",
	              url:url,
	              async:false,
	              dataType: "json",
	              data:$("#addForm").serialize(),// 你的formid
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
					<li class="active"><a href="javascript:void(0)"><font style="color:#A59999">角色分配</font>
					</a></li>
				</ul>
				<form class="form-horizontal" action="${ctx}/user/save" id="addForm">
					<div class="form-group">
						  <label for="inputEmail3">角色名称列表</label>
					      <select multiple class="form-control">
					         <option>1</option>
					         <option>2</option>
					         <option>3</option>
					         <option>4</option>
					         <option>5</option>
					      </select>
					</div>
					
						<div class="form-group">
						  <label for="inputEmail3">角色名称列表</label>
					      <select multiple class="form-control">
					         <option>1</option>
					         <option>2</option>
					         <option>3</option>
					         <option>4</option>
					         <option>5</option>
					      </select>
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
                
                <div id="success_div" class="col-sm-6 col-sm-offset-2 alert alert-success alert-dismissable" style="display: none">
                    <strong id="success_msg">操作成功！</strong>
                </div>
				</form>
				
				
			</div>
	</div>
	<!-- 
	    <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
	 -->
		<script src="${ctx}/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
  </body>
</html>

