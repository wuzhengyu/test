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
    <title>用户列表</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	 <!-- Bootstrap core CSS -->
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">
  </head>
    <script type="text/javascript">
    var userId = "${sessionScope.user.id}";
    	var webApp="${ctx}";
  		function addUser(){
  			window.location.href = "${ctx}/jsp/user/user_add.jsp";
  		}
  		
  		function updatePassword(){
  			window.location.href = "${ctx}/jsp/user/user_edit_pwd.jsp?id="+userId;
  		}
  		
  		function setRole(){
  			window.location.href = "${ctx}/jsp/user_role/user_role_add.jsp?id="+userId;
  		}
  		
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
  	            		 alert("该用户已存在");
  	            		 $("#username").val("");
  	            		 flag = true;
  	            	 }
  	             }
  			});
  			return flag;
  		}
  		
  		function pageQuery(flag,currentPageVal){
  			if("pre"==flag){
  				$("#currentPage").val(currentPageVal-1);
  			}else if("next"==flag){
  				$("#currentPage").val(currentPageVal+1)
  			}else if("query"==flag){
  				$("#currentPage").val(1)
  			}
  			$("#pageForm").submit();
  		}
  		
  		
		function ajaxSubmitForm(){
			var flag = checkUser();
			if(!flag){
		  		var saveUrl = "${ctx}/user/save";
		  		$.ajax({
		  			url:saveUrl,
		  			data:$("#formData").serialize(),
		  			type:"post",
		  			success:function(data){
		  				if(data){
		  					alert("新增成功");
		  					window.location.reload();
		  				}
		  			}
		  		});
			}
  		}
  		
  		function deleteUser(userId){
  			if(window.confirm("删除后将不可恢复，是否继续？")){
				var url="${ctx}/user/delete";
	  			$.ajax({
	  				 type: "POST",
	  	             url: url,
	  	             data: {id:userId},
	  	             dataType: "json",
	  	             success: function(data){
	  	            	 if(data){
	  	            		alert("删除成功");
	  	            		window.location.reload();
	  	            	 }
	  	             }
	  			});
	  		}	
  		}
  		
  		function resetPwd(userid){
			if(window.confirm("当前正在执行密码重置操作，是否继续？")){
				var url="${ctx}/user/resetPwd";
	  			$.ajax({
	  				 type: "POST",
	  	             url: url,
	  	             data: {id:userid},
	  	             dataType: "json",
	  	             success: function(data){
	  	            	 if(data){
	  	            		$("#success_div").show();
	  	            	 }else{
	  	            	 	$("#error_div").show();
	  	            	 }
	  	             }
	  			});
	  		}	
  		}	
  		
  	
  	
  </script>
  
  <body>
    	
    	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="javascript:void(0)"><font style="font-weight: bold;color:blue">用户列表</font></a>
				</li>
				<li>
					<a href="javascript:void(0)" onclick="addUser()">新增</a>
				</li>
			</ul>
			
			<table class="table table-bordered">
				<thead>
					<tr align="center">
						<th>
							用户名
						</th>
						<th>
							创建时间
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					 <c:forEach items="${page.result}" var="user" varStatus="index" >
		    			<tr>
		    			<td>${user.username}</td>
		    			<td><fmt:formatDate value="${user.createDate}" type="both"/></td>
		    			<td><a style="padding-right: 30px;" href="${ctx}/user/getById?id=${user.id}">修改</a><a href="javascript:deleteUser('${user.id}')">删除</a>
		    			<a style="padding-left: 20px;" href="javascript:resetPwd('${user.id}')">重置密码</a>
		    			</td>
	    			</tr>
	    		</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
    	
    	<form id="pageForm" action="${ctx}/user/findUserPage" method="get">
		<ul class="pager">
			每页数目
			<select name="pageSize" id="pageSize"
				onchange="pageQuery('query',${page.currentPage})">
				<option value="5"
					<c:if test="${page.pageSize eq 5 }">selected</c:if>>5</option>
				<option value="10"
					<c:if test="${page.pageSize eq 10 }">selected</c:if>>10</option>
				<option value="15"
					<c:if test="${page.pageSize eq 15 }">selected</c:if>>15</option>
				<option value="20"
					<c:if test="${page.pageSize eq 20 }">selected</c:if>>20</option>
			</select>条
			<td>第${page.currentPage}页/共${page.pageCount}页</td>
			<td>共${page.totalCount}条记录</td>
			<li><a href="javascript:void(0)" name="prePage" id="prePage"
				<c:if test="${page.hasPerPage eq false}"> onclick="return false;"</c:if>
				onclick="pageQuery('pre',${page.currentPage})">上一页</a></li>
			<li><a href="javascript:void(0)" name="nextPage" id="nextPage"
				<c:if test="${page.hasNextPage eq false}"> onclick="return false;"</c:if>
				onclick="pageQuery('next',${page.currentPage})">下一页</a></li>
		</ul>
		<!-- 
    		<input type="button" name="prePage" id="prePage" <c:if test="${page.hasPerPage eq false}"> disabled="true"</c:if>  onclick="pageQuery('pre',${page.currentPage})" value="上一页"/>
    		<input type="button" name="nextPage" id="nextPage" <c:if test="${page.hasNextPage eq false}"> disabled="true"</c:if> onclick="pageQuery('next',${page.currentPage})" value="下一页"/>
				 -->
    		<input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"/>
    		
    		 <div id="error_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
                    <strong id="error_msg">操作失败！</strong>
                </div>
                <div id="success_div" class="col-sm-6 col-sm-offset-2 alert alert-success alert-dismissable" style="display: none">
                    <strong id="success_msg">操作成功！</strong>
                </div>
    	</form>
  </body>
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
</html>

