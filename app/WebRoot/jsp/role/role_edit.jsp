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
        
    <title>角色详细</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">
	

  </head>
    <script type="text/javascript">
    $(document).ready(function(){
    
	 })
	 
	function ajaxSubmitForm(){
			var url = "${ctx}/role/update";
			$.ajax({
	              type: "POST",
	              url:url,
	              data:$("#editForm").serialize(),// 你的formid
	              async: false,
	              error: function(request) {
	              	
	              },
	              success: function(data) {
	              	if(data){
	              		$("#success_div").show();
	              	}else{
	              		$("#error_div").show();
	              	}
	              }
	          });
	}
	
	
  	function goBack(){
    	var url = "${ctx}/role/findRolePage?currentPage=1&pageSize=10";
    	window.location.href = url;
    }
  				
  </script>
  
  <body>
		<div class="container-fluid">
		<div class="row-fluid">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0)"><font style="color:#A59999">角色编辑</font>
					</a></li>
				</ul>
				<form class="form-horizontal" action="${ctx}/role/update" id="editForm">
					  <input type="hidden" name="id" id="id" value="${role.id}"/>
					  
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">角色名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="roleName" name="roleName" value="${role.roleName}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">角色描述</label>
						<div class="col-sm-10">
							<textarea type="text" class="form-control" id="description" name="description">${role.description}</textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">菜单权限</label>
						<c:forEach items="${menuList}" var="menu">
							<div class="col-sm-10">
								 <input type="checkbox" name="checkMenus" value="${menu.id}"
								 <c:forEach items="${roleMenuList}" var="roleMenu">
								 	<c:if test="${menu.id eq roleMenu.menuId}">
								 		checked
								 	</c:if>
								 </c:forEach>
								 />${menu.menuName}
							</div>
						</c:forEach>
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
	<%--
	    <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
	 --%>
		<script src="${ctx}/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
  </body>
</html>

