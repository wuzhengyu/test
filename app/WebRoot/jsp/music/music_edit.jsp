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
    <title>曲库歌曲</title>

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
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
	 --%>
    <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
  </head>
    <script type="text/javascript">
    var size = "${page.pageSize}";
    $(document).ready(function(){
	 })
	 
	 
	function ajaxSubmitForm(){
    	 $("#success_div").hide();
         $("#error_div").hide();
		var url = "${ctx}/webMusic/update";
		$.ajax({
              type: "POST",
              url:url,
              data:$("#editForm").serialize(),// 你的formid
              error: function(request) {
              	
              },
              success: function(data) {
              	if(data){
              		$("#success_div").show();
              	}
              }
          });
	}
    
    function goBack(){
    	var url = "${ctx}/webMusic/getMusicList?currentPage=1&pageSize=10";
    	window.location.href = url;
    }
  </script>
  
  <body>
	<div class="container-fluid">
		<div class="row-fluid">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0)"><font style="color:#A59999">歌曲编辑</font>
					</a></li>
				</ul>
				<form class="form-horizontal" action="${ctx}/webMusic/update" id="editForm">
					<input type="hidden" name="id" id="id" value="${music.id}">
					<input type="hidden" name=musicHost id="musicHost" value="${music.musicHost}">
					<input type="hidden" name="musicPath" id="musicPath" value="${music.musicPath}">
					<input type="hidden" name="contentType" id="contentType" value="${music.contentType}">
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">歌曲名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="musicName" name="musicName" value="${music.musicName}">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">歌曲类型</label>
						<div class="col-sm-10">
							<select class="form-control" id="musicType" name="musicType">
							  <option <c:if test="${music.musicType eq '1' }">selected</c:if> value="1">热门歌曲</option>
							  
							  <option <c:if test="${music.musicType eq '2' }">selected</c:if> value="2">经典歌曲</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">歌曲描述</label>
						<div class="col-sm-10">
							<textarea type="text" class="form-control" name="description" id="description" value="">${music.description}</textarea>
						</div>
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

	<form id="formData"  method="post" action="${ctx}/webMusic/upload" enctype="multipart/form-data">
    		<table id="addFiled">
    			
    		</table>
   	</form>
    	

<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>-->
	<script src="${ctx}/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
</body>
  
</html>

