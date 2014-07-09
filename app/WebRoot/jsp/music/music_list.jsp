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
    <title>曲库列表</title>

     <!-- Bootstrap core CSS -->
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">


    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	<%--
    <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
	 --%>
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
  </head>
    <script type="text/javascript">
    	var webApp="${ctx}";
  		function addMusic(){
  			//window.location.href="${ctx}/jsp/music/music_add.jsp";
  			window.location.href="${ctx}/jsp/music/music_upload.jsp";
  		}
  		
  		function pageQuery(flag,currentPageVal){
  			if("pre"==flag){
  				$("#currentPage").val(currentPageVal-1);
  			}else if("next"==flag){
  				$("#currentPage").val(currentPageVal+1)
  			}else if("query"==flag){
  				$("#currentPage").val(1);
  			}
  			$("#pageForm").submit();
  		}
  		
  		//歌曲删除
  		function deleteMusic(musicId){
			if(window.confirm("删除后将不可恢复，是否继续？")){	
				var url="${ctx}/webMusic/delete";
	  			$.ajax({
	  				 type: "POST",
	  	             url: url,
	  	             data: {id:musicId},
	  	             dataType: "json",
	  	             success: function(data){
	  	            	 if(data.flag){
	  	            		alert(data.msg);
	  	            		window.location.reload();
	  	            	 }
	  	             }
	  			});
  			}
  		}
  		
  		//歌曲上传
  		function fileUpload(){
  			var file = $("#file").val();
  			if(!file){
  				return alert("请选择要上传的文件");
  			}
  			$("#formData").submit();
  		}
  	
  </script>
  
  <body>
  		<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="javascript:void(0)"><font style="font-weight: bold;color:blue">曲库列表</font></a>
				</li>
				<li>
					 <a href="javascript:void(0)" onclick="addMusic()">新增</a>
				</li>
			</ul>
			
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>
							歌曲名称
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
					 <c:forEach items="${page.result}" var="music" varStatus="index" >
		    			<tr>
		    			<td>${music.musicName}</td>
		    			<td><fmt:formatDate value="${music.createDate}" type="both"/></td>
		    			<td>
		    			<!-- 
		    			 -->
		    			<a style="padding-right: 20px;" href="${ctx}/webMusic/getById?id=${music.id}">修改</a>
		    			<a href="javascript:void(0)" onclick="deleteMusic('${music.id}')">删除</a>
		    			</td>
	    			</tr>
	    		</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
  
    	<form id="formData"  method="post" action="${ctx}/webMusic/upload" enctype="multipart/form-data">
    		<table id="addFiled">
    			
    		</table>
    	</form>
    	
    	<form id="pageForm" action="${ctx}/webMusic/getMusicList" method="get">
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
		</div>
    	</form>

<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>-->
	<script src="${ctx}/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
</body>
  
</html>

