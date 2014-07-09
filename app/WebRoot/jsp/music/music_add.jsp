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
	 --%>
	
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
    <script src="${ctx}/resources/js/uploadFile.js"></script>
    
    <!-- Bootstrap -->

    <link rel="stylesheet" href="${ctx}/resources/css/uploader.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/resources/css/demo.css" rel="stylesheet" />
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
  </head>
    <script type="text/javascript">
    var size = "${page.pageSize}";
    $(document).ready(function(){
	 })
	 
	 
	function ajaxSubmitForm(){
         $("#uploadForm").submit();
	}
    
    function goBack(){
    	var url = "${ctx}/webMusic/getMusicList?currentPage=1&pageSize=10";
    	window.location.href = url;
    }
    
  </script>
  <!-- 
  <body  role="document">
	<div class="container-fluid">
		<div class="row-fluid">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0)"><font style="color:#A59999">歌曲上传</font>
					</a></li>
				</ul>
				 <div id="drag-and-drop-zone" class="uploader">
		            <div>拖动文件到此处直接上传</div>
		            <div class="or">-或者-</div>
		            <div class="browser">
		              <label>
		                <span>选择文件</span>
		                <input type="file" name="files[]" multiple="multiple" title='单击按钮选择文件'>
		              </label>
		            </div>
		          </div>
		           <div class="uploader">
			          <div class="panel panel-default">
			            <div class="panel-heading">
			              <h3 class="panel-title">Uploads</h3>
			            </div>
			            <div class="panel-body demo-panel-files" id='demo-files'>
			              <span class="demo-note">No Files have been selected/droped yet...</span>
			            </div>
			          </div>
			        </div>
			   	<div id="error_div" class="col-sm-6 col-sm-offset-2 alert alert-danger alert-dismissable" style="display: none">
                    <strong id="error_msg">操作失败！</strong>
                </div>
                <div id="success_div" class="col-sm-6 col-sm-offset-2 alert alert-success alert-dismissable" style="display: none">
                    <strong id="success_msg">操作成功！</strong>
                </div>
			</div>
	</div>
   -->
	<script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
	<script src="${ctx}/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
</body>
</html>

