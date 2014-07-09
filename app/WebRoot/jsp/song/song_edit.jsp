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
        
    <title>幻音歌曲</title>
    

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
		var url = "${ctx}/webSong/update";
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
              	}
              }
          });
	}
	
	
  	function goBack(){
    	var url = "${ctx}/webSong/getSongList?currentPage=1&pageSize=10";
    	window.location.href = url;
    }
  				
  </script>
  
  <body>
		<div class="container-fluid">
		<div class="row-fluid">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0)"><font style="color:#A59999">幻音歌曲编辑</font>
					</a></li>
				</ul>
				<form class="form-horizontal" action="${ctx}/webSong/update" id="editForm">
					  <input type="hidden" name="id" id="id" value="${song.id}"/>
					  <input type="hidden" name="songHost" id="songHost" value="${song.songHost}"/>
					  <input type="hidden" name="songPath" id="songPath" value="${song.songPath}"/>
					  <input type="hidden" name="contentType" id="contentType" value="${song.contentType}"/>
					  <input type="hidden" name="songType" id="songType" value="${song.songType}"/>
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">幻音歌曲名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="songName" name="songName" value="${song.songName}">
						</div>
					</div>
			
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">点赞数</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="clickNum" id="clickNum" value="${song.clickNum}" required>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">设置铃声数</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="ringtoneNum" id="ringtoneNum" value="${song.ringtoneNum}" required>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">设置彩铃数</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="colorRingToneNum" id="colorRingToneNum" value="${song.colorRingToneNum}" required>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">幻音歌曲描述</label>
						<div class="col-sm-10">
							<textarea type="text" class="form-control" name="description" id="description" value="">${song.description}</textarea>
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
	    <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
		<script src="${ctx}/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
  </body>
</html>

