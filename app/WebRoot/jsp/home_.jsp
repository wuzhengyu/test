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
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Example of Fluid Layout with Bootstrap version 2.0 from w3cschool.cc">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap-layout.css" rel="stylesheet">
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap-responsive.css" rel="stylesheet">
     <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->

  </head>
  <body>
 <div class="navbar navbar-inverse navbar-fixed-top">
     <!-- 
       <div class="navbar-inner">
      -->
      <div style="background-color:#0088CC">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="javascript:void(0)">幻音APP后台管理</a>
          <div class="nav-collapse collapse">
              	
            <p class="navbar-text pull-right">
            	<a href="javascript:void(0)"><font color="#ffffff">您好！${sessionScope.user.username}</font></a><a href="${ctx}/login/quit"><font color="#ffffff">【安全退出】</font></a></font>
            </p>
            
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">系统管理</li>
              <c:forEach items="${menuList}" var="menu" begin="0" varStatus="menuStatus">
	              <li id="tab_${menuStatus.index}"  <c:if test="${menuStatus.index eq 0 }">class="active" </c:if>>
	              <a href="javascript:getData('${menuStatus.index}','${ctx}/${menu.menuUrl}')">${menu.menuName}</a></li>
              </c:forEach>
              
              <%-- 
              <li id="tab_0" class="active"><a href="javascript:getData('0','${ctx}/webMusic/getMusicList?currentPage=1&pageSize=10')">曲库管理</a></li>
              <li id="tab_1"><a href="javascript:getData('1','${ctx}/webSong/getSongList?currentPage=1&pageSize=10')">幻音管理</a></li>
              <li id="tab_2"><a href="javascript:getData('2','${ctx}/user/findUserPage?currentPage=1&pageSize=10')">用户管理</a></li>
              <li id="tab_3"><a href="javascript:getData('3','${ctx}/role/findRolePage?currentPage=1&pageSize=10')">角色管理</a></li>
              <li id="tab_4"><a href="javascript:getData('4','${ctx}/menu/findMenuPage?currentPage=1&pageSize=10')">资源管理</a></li>
              <li id="tab_6"><a href="javascript:getData('6','${ctx}/jsp/user/user_edit_pwd.jsp?userId=${sessionScope.user.id}')">修改密码</a></li>
              <li id="tab_5"><a href="javascript:getData('5','${ctx}/jsp/home.jsp')">合成请求及查询</a></li>
            </ul>
               --%>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span10">
        <!-- 
          <div class="hero-unit">
          </div>
         -->
        	<iframe id="list_frame" src="${ctx}/webMusic/getMusicList" width="100%" frameborder="0" scrolling="no" onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>700?fdh:700)" ></iframe>
        <!-- 
         -->
          		
			
          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->
      
      
     </body>
           <hr>

      <footer>
        <p>&copy; Company 2014</p>
      </footer>

    </div><!--/.fluid-container-->
	
	
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <%--
     --%>
     <script src="${ctx}/bootstrap-3.1.1/dist/js/jquery.js"></script>
     <script src="${ctx}/resources/js/home.js"></script>
  <script type="text/javascript">
  </script>
</html>