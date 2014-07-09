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
        
    <title>角色列表</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
	<!-- Bootstrap core CSS -->
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">
  </head>
    <script type="text/javascript">
    	var webApp="${ctx}";
  		function addRole(){
  			window.location.href = "${ctx}/jsp/role/role_add.jsp";
  		}
  		
  		
  		function edit(id){
  			
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
		  		var saveUrl = "${ctx}/role/save";
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
  		
  		function deleteRole(roleId){
  			if(window.confirm("删除后将不可恢复，是否继续？")){
				var url="${ctx}/role/delete";
	  			$.ajax({
	  				 type: "POST",
	  	             url: url,
	  	             data: {id:roleId},
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
  		
  		function edit(id){
  			var result = window.showModalDialog("${ctx}/jsp/role/role_edit.jsp",id,"dialogHeight=50px,dialogWidth=50px,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, status=no");
  			if(result){
  				window.location.reload();
  			}
  		}
  	
  </script>
  
  <body>
	    <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="javascript:void(0)"><font style="font-weight: bold;color:blue">角色列表</font></a>
				</li>
				<li>
					<a href="javascript:void(0)" onclick="addRole()">新增</a>
				</li>
			</ul>
			
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>
							角色名称
						</th>
						<th>
							描述
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					 <c:forEach items="${page.result}" var="role" varStatus="index" >
		    			<tr>
		    			<td>${role.roleName}</td>
		    			<td>${role.description}</td>
		    			<td><a style="padding-right: 20px;" href="${ctx}/role/getById?id=${role.id}">修改</a>
		    			<a href="javascript:deleteRole('${role.id}')">删除</a></td>
	    			</tr>
	    		</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>	
	    	
	    	
	    	
    	<form id="formData"  method="post">
    		<table id="addFiled">
    			
    		</table>
    	</form>
    	
    	<form id="pageForm" action="${ctx}/role/findRolePage" method="get">
    	 	<ul class="pager">
    		每页数目<select name="pageSize" id="pageSize" onchange="pageQuery('query',${page.currentPage})">
    			<option value="5" <c:if test="${page.pageSize eq 5 }">selected</c:if>>5</option>
    			<option value="10" <c:if test="${page.pageSize eq 10 }">selected</c:if>>10</option>
    			<option value="15" <c:if test="${page.pageSize eq 15 }">selected</c:if>>15</option>
    			<option value="20" <c:if test="${page.pageSize eq 20 }">selected</c:if>>20</option>
    		</select>条
    		<td>第${page.currentPage}页/共${page.pageCount}页</td>
    		<td>共${page.totalCount}条记录</td>
				  <li>
				    <a href="javascript:void(0)" name="prePage" id="prePage" <c:if test="${page.hasPerPage eq false}"> onclick="return false;"</c:if>  onclick="pageQuery('pre',${page.currentPage})">上一页</a>
				  </li>
				  <li>
				    <a href="javascript:void(0)" name="nextPage" id="nextPage" <c:if test="${page.hasNextPage eq false}"> onclick="return false;"</c:if> onclick="pageQuery('next',${page.currentPage})">下一页</a>
				  </li>
			</ul>
				<!-- 
    		<input type="button" name="prePage" id="prePage" <c:if test="${page.hasPerPage eq false}"> disabled="true"</c:if>  onclick="pageQuery('pre',${page.currentPage})" value="上一页"/>
    		<input type="button" name="nextPage" id="nextPage" <c:if test="${page.hasNextPage eq false}"> disabled="true"</c:if> onclick="pageQuery('next',${page.currentPage})" value="下一页"/>
				 -->
    		<input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"/>
    	</form>
  </body>
</html>

