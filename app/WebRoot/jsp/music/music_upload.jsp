<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>歌曲上传</title>

     <!-- Bootstrap core CSS -->
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">


    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]>
    <![endif]-->
    <script src="${ctx}/bootstrap-3.1.1/dist/js/ie8-responsive-file-warning.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	<%--
	 --%>
	
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
    
    <%--
     <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
     --%>
    
    <!-- Bootstrap -->

    <link rel="stylesheet" href="${ctx}/resources/css/uploader.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/css/demo.css" rel="stylesheet" />
   
  </head>
  <body role="document">
<%-- 
    <div class="container demo-wrapper">
 --%>
 		<div class="container-fluid">
		<div class="row-fluid">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="javascript:void(0)"><font style="color:#A59999">歌曲上传</font></a>
					</li>
				</ul>
			
      <div class="row demo-columns">
        <div class="col-md-6">
          <!-- D&D Zone-->
          <div id="drag-and-drop-zone" class="uploader">
            <div>拖动文件到此处进行上传</div>
            <div class="or">-or-</div>
            <div class="browser">
              <label>
                <span>选择文件</span>
                <input type="file" name="files[]" multiple="multiple" title='Click to add Files'>
              </label>
            </div>
          </div>
          <!-- /D&D Zone -->

        </div>
        <!-- / Left column -->

        <div class="col-md-6">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">文件上传进度</h3>
            </div>
            <div class="panel-body demo-panel-files" id='demo-files'>
              <span class="demo-note">No Files have been selected/droped yet...</span>
            </div>
          </div>
        </div>
        <!-- / Right column -->
      </div>
</div>	
</div>
		 <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		     <button type="button" class="btn btn-default" onclick="goBack()">返回</button>
		    </div>
	 	 </div>

   	<div id="error_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
         <strong id="error_msg">上传失败！</strong>
     </div>
    <div id="success_div" class="col-sm-6 col-sm-offset-2 alert alert-success alert-dismissable" style="display: none">
        <strong id="success_msg">上传成功！</strong>
    </div>
   	<div id="file_size_error_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
         <strong id="error_msg">上传的文件大小不能超过50m！</strong>
     </div>
   	<div id="file_type_error_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
         <strong id="error_msg">只允许上传音频格式的文件！</strong>
     </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script> -->

    <script type="text/javascript" src="${ctx}/resources/js/demo.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/dmuploader.min.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
	   	function goBack(){
	    	var url = "${ctx}/webMusic/getMusicList?currentPage=1&pageSize=10";
	    	window.location.href = url;
	    }    
	    
      $('#drag-and-drop-zone').dmUploader({
        url: '${ctx}/webMusic/upload',
        dataType: 'json',
        allowedTypes: 'audio/*',//允许上传的文件格式为音频
        maxFileSize: 50000000,//允许上传的文件最大值为50M
        onInit: function(){
          //$.danidemo.addLog('#demo-debug', 'default', 'Plugin initialized correctly');
        },
        onBeforeUpload: function(id){
          //$.danidemo.addLog('#demo-debug', 'default', 'Starting the upload of #' + id);

          $.danidemo.updateFileStatus(id, 'default', '上传中...');
        },
        onNewFile: function(id, file){
          $.danidemo.addFile('#demo-files', id, file);
        },
        onComplete: function(){
          //$.danidemo.addLog('#demo-debug', 'default', 'All pending tranfers completed');
        },
        onUploadProgress: function(id, percent){
          var percentStr = percent + '%';

          $.danidemo.updateFileProgress(id, percentStr);
        },
        onUploadSuccess: function(id, data){
         // $.danidemo.addLog('#demo-debug', 'success', 'Upload of file #' + id + ' completed');

          //$.danidemo.addLog('#demo-debug', 'info', 'Server Response for file #' + id + ': ' + JSON.stringify(data));

          $.danidemo.updateFileStatus(id, 'success', '上传成功');
          $.danidemo.updateFileProgress(id, '100%');
	           $("#file_type_error_div").hide();
	           $("#file_size_error_div").hide();
	           $("#success_div").show();
          
        },
        onUploadError: function(id, message){
          $.danidemo.updateFileStatus(id, 'error', message);

          //$.danidemo.addLog('#demo-debug', 'error', 'Failed to Upload file #' + id + ': ' + message);
        },
        onFileTypeError: function(file){
         // $.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' cannot be added: must be an image');
          $("#success_div").hide();
         $("#file_type_error_div").show();
        },
        onFileSizeError: function(file){
          //$.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' cannot be added: size excess limit');
          $("#success_div").hide();
          $("#file_size_error_div").show();
        },
        onFallbackMode: function(message){
         $.danidemo.addLog('#demo-debug', 'info', 'Browser not supported(do something else here!): ' + message);
        }
      });
    </script>
  </body>
</html>
