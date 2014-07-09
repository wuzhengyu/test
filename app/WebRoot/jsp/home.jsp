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
    <link href="${ctx}/bootstrap-3.1.1/dist/css/bootstrap.css" rel="stylesheet">
	<link href="${ctx}/bootstrap-3.1.1/dist/css/example-fluid-layout.css" rel="stylesheet">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->

  </head>
  <body>
  <div class="container-fluid">
	<div class="row-fluid">
		<div class="span10">
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
		<form action="${ctx}/appSong/compoundSong" method="post" enctype="multipart/form-data">
     	<input type="hidden" name="appid" value="9sky0001"/>
     	<input type="hidden" name="appkey" value="27f2acb90f4b5147074b331db1c61cc7"/>
     	<input type="hidden" name="phoneKey" value="b5ad8754bfffa9d8c0117ecf0091f310"/>
     	<input type="hidden" name="speechfmt" value="amr"/>
     	<input type="hidden" name="musicId" value="1"/>
     	<table>
     		 <tr>
     		 <td><input class="input-file" name="file" id="file" type="file"></td>
     		 <td><button type="submit" class="btn btn-info">合成请求</button></td>
     		 </tr>
     	</table>
     </form>
     
     
     <form action="${ctx}/appSong/compoundSongAgain" method="post">
     	<input type="hidden" name="appid" value="9sky0001"/>
     	<input type="hidden" name="appkey" value="27f2acb90f4b5147074b331db1c61cc7"/>
     	<input type="hidden" name="phoneKey" value="b5ad8754bfffa9d8c0117ecf0091f310"/>
     	<input type="hidden" name="recordId" value="BFF7FB39214F45118220499EB4360ED4"/>
     	<input type="hidden" name="speechid" value="f2f32b730b1c7a02a1a417763df4d698"/>
     	<input type="hidden" name="musicId" value="3"/>
     	<table>
     		<!-- 
     		<input type="file" name="file">
     		<input type="submit" value="合成请求">
     		 -->
     		 <tr>
     		 <td><button type="submit" class="btn btn-info">重新合成请求</button></td>
     		 </tr>
     	</table>
     </form>
     
     
     
     <form action="${ctx}/appSong/getSongResStatus" method="post">
     		<input type="hidden" name="appid" value="9sky0001"/>
     		<input type="hidden" name="appkey" value="27f2acb90f4b5147074b331db1c61cc7"/>
     		<input type="hidden" name="phoneKey" value="b5ad8754bfffa9d8c0117ecf0091f310"/>
     		<input type="text" name="songId" value=""/>
     		<tr>
     			<td></td>
     			<td><button type="submit" class="btn btn-info">合成查询</button></td>
    		</tr>
     	<table>
     	</table>
     </form>
     
     <form action="${ctx}/appSong/save" method="post">
     		<input type="hidden" name="phoneKey" value="b5ad8754bfffa9d8c0117ecf0091f310"/>
     		<input type="text" name="tempId" value=""/>
     		<input type="hidden" name="songName" value="中文乱码test"/>
     		<tr>
     			<td></td>
     			<td><button type="submit" class="btn btn-info">保存</button></td>
    		</tr>
     	<table>
     	</table>
     </form>
          </div><!--/.well -->
        </div><!--/span-->
        </div>
    </div><!--/.fluid-container-->
    
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    

    
    <!-- JiaThis Button BEGIN -->
<div class="jiathis_style">
	<span class="jiathis_txt">分享到：</span>
	<a class="jiathis_button_tools_1"></a>
	<a class="jiathis_button_tools_2"></a>
	<a class="jiathis_button_tools_3"></a>
	<a class="jiathis_button_tools_4"></a>
	<a class="jiathis_counter_style"></a>
</div>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1401854584449650" charset="utf-8"></script>
<!--JiaThis Button END -->
  </body>
  
  

<script type="text/javascript">
var jiathis_config={
	url:"http://218.244.128.50:8080/app/upload/song/2014/5/30/e204b713f3e4c66a6eea59e414d609a0.1.mp3",
	summary:"点一下你将会乐趣无穷！还等什么？赶快行动吧！",
	title:"幻音app分享测试",
	shortUrl:true,
	hideMore:true
}
</script>
<!-- JiaThis Button END -->
  
</html>