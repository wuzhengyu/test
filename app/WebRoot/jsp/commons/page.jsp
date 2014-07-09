<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


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
