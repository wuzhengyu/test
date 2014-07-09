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

    <title>用户登陆界面</title>

     <!-- Bootstrap core CSS -->

    <!-- Custom styles for this template -->
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/bootstrap-3.1.1/dist/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
  </head>
    <script type="text/javascript">
    	var msg = "${verlification.message}";
    	var webApp="${ctx}";
   </script>
   
	<body>
      <form class="form-signin" action="${ctx}/login/toLogin" method="post">
        <h2 class="form-signin-heading" align="center">幻音app后台管理登陆 </h2>
        <input type="text" class="form-control" name="username" id="username" placeholder="用户名" required autofocus>
        <input type="password" class="form-control" name="password" id="password" placeholder="密码" required>
       
          <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
      </form>
    </div> <!-- /container -->
	

	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     <%--
      <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
    <script src="${ctx}/bootstrap-3.1.1/dist/js/alert.js"></script>
      --%>
    	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-1.9.1.min.js"></script>
  </body>
</html>



