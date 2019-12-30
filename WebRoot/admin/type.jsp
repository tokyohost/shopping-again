<%@ page language="java" import="java.util.*,com.scmpi.book.entity.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<ProductType> ptype =(List<ProductType>) session.getAttribute("ptype");
%>
<!DOCTYPE>
<html lang="en">
	<head>
		<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
	   <meta http-equiv="cache-control" content="no-cache">
		<link rel="stylesheet" href="<%=path%>/css/cssreset-min.css">
		<link rel="stylesheet" href="<%=path%>/css/index.css">
		<link rel="stylesheet" href="<%=path%>/css/global.css">
		<style type="text/css">
/* CSS Document */
body {
	font: normal 12px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
	background: #E6EAE9;
}

a {
	color: #c75f3e;
}

#showdata{
    position:absolute;
	font: bold 14px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	margin-top: 180px;
	margin-bottom:200px;
	margin-left: 600px;
	color: #FF0000;
}
#mytable {
	margin-top:100px;
	width: 700px;
	padding: 0;
	margin: 0 auto;
	margin-left:40px;
	margin-right:40px;
}
/*---------for IE 5.x bug*/
html>body td {
	font-size: 11px;
}

.contant{
	background-color:#0000;
	height:400px;
	width:400px;
	margin-left:40px;
	margin-right:40px;

}
td {
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	background: #fff;
	font-size: 11px;
	padding: 6px 6px 6px 12px;
	color: #4f6b72;
}

td.alt {
	background: #F5FAFA;
	color: #797268;
}
th {
	font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: left;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA url(images/bg_header.jpg) no-repeat;
}

th.nobg {
	border-top: 0;
	border-left: 0;
	border-right: 1px solid #C1DAD7;
	background: none;
}

</style>
		<!-- IE6、7、8支持HTML5标签 -->
		<!--[if lte IE 8]><script src="js/html5.js"></script><![endif]-->
		<!-- IE6、7、8支持CSS3特效 -->
		<!--[if lte IE 8]><script src="js/PIE.js"></script><![endif]-->
		<!--[if lt IE 9]><script type="text/javascript" src="selectivizr-min.js"></script><![endif]-->

<script type="text/javascript">

	function user(){
		var ca = document.getElementById("userID");
		ca.action = "/shopping/servlet/UserAdminServlet";
		ca.submit();
	
	}
	function type(){
		var ca = document.getElementById("typeID");
		ca.action = "/shopping/servlet/TypeAdminServlet";
		ca.submit();
	
	}
	
	function product(){
		var ca = document.getElementById("productID");
		ca.action = "/shopping/servlet/ProductAdminServlet";
		ca.submit();
	
	}
	function order(){
		var ca = document.getElementById("orderID");
		ca.action = "/shopping/servlet/OrderAdminServlet";
		ca.submit();
	
	}
	
	<%for(ProductType pt :ptype){%>
	
	function changet<%= pt.getCid() %>(){
		var ca = document.getElementById("tid<%=pt.getCid() %>");
		ca.action = "/shopping/servlet/ChangeTypeServlet";
		ca.submit();
	
	}
	
	
	<%}%>
	
	
	function addNewProductType() {
    	var ca = document.getElementById("addNew");
		ca.action = "/shopping/servlet/AddNewProductTypeServlet";
		ca.submit();
	}
	function search() {
		var ca = document.getElementById("ProductTypeSearch");
		ca.action = "/shopping/servlet/SearchProductTypeServlet";
		ca.submit();
		
	}

</script>
	</head>
	<body >
		<!-- 头部 -->
		<header>
		<nav>
		<div id="topNav">
			<ul>
				<li class="welcome">
					您好${user.uname}，欢迎登录后台系统！
				</li>
				
				<li>
					<a href="<%=path%>/admin/admin.jsp">[返回主页]</a>
				</li>
			</ul>
		</div>
		</nav>
		</header>
		
		<div  style="overfloat:hidden;height:1200px;width:90%;margin:0 auto; margin-top:80px;">
			<div style="width:15%;height:1000px;float:left;background-color:#ccc">
				<!-- 左边菜单 -->
				<ul style="width:100%;height:100%;">
					<li style="list-style-type:none;border:1px #000 solid;height:30px;width:90%;margin:0 auto;margin-left:10px;mrigin-right:10px;margin-top:10px;">
					<form id="userID" method="post" action="">
						<a href="javascript:user();" style="width:100%;height:100%;border:0px;line-height:30px; text-align:center;display:block;background-color:#272822">用户管理</a>
					</form>
					</li>
					<li style="list-style-type:none;border:1px #000 solid;height:30px;width:90%;margin:0 auto;margin-left:10px;mrigin-right:10px;margin-top:10px;">
						<form id="productID" method="get" action="">
						<a href="javascript:product();" style="width:100%;height:100%;border:0px;line-height:30px; text-align:center;display:block;background-color:#272822">商品管理</a>
					</form>
					</li>
					<li style="list-style-type:none;border:1px #000 solid;height:30px;width:90%;margin:0 auto;margin-left:10px;mrigin-right:10px;margin-top:10px;">
						<form id="orderID" method="get" action="">
						<a href="javascript:order();" style="width:100%;height:100%;border:0px;line-height:30px; text-align:center;display:block;background-color:#272822" >订单管理</a>
						</form>
					</li>
					<li style="list-style-type:none;border:1px #000 solid;height:30px;width:90%;margin:0 auto;margin-left:10px;mrigin-right:10px;margin-top:10px;">
						<form id="typeID" method="get" action="">
						<a href="javascript:type();" style="width:100%;height:100%;border:0px;line-height:30px; text-align:center;display:block;background-color:#272822" >商品类型管理</a>
						</form>
					</li>
				</ul>
				
			</div>
			<div style="float:right;width:85%;background-color:#fff;height:1000px;">
				<!-- 右边详情页 -->
				
				<h1 style="margin-left:40px;margin-top:40px;margin-bottom:20px;font-size:24px;">商品类型管理页面</h1>
				<table id="mytable" cellspacing="0"
						summary="The technical specifications of the Apple PowerMac G5 series">
						<tr>
						<th scope="col" abbr="Dual 1.8">
								类型id
							</th>
							<th scope="col" abbr="Dual 1.8">
								类型名称
							</th>
							<th scope="col" abbr="Dual 2">
								型号
							</th>
							
							<th scope="col" abbr="Dual 2.5">
								类型操作
							</th>
						</tr>
						
						<%for(ProductType item:ptype){ %>
						<form id="tid<%=item.getCid() %>" method="post" action="" >
							<tr>
								<td>
									<%= item.getCid() %>
									<input type="hidden" name="tid" value="<%= item.getCid()%>">
								</td>
								<td>
									<input type="text" name="tname" value="<%= item.getcName()%>">
								</td>
								<td>
									<input type="text" name="txinhao" value="<%= item.getCxinhao()%>">
								</td>
								<td>
								<div style="width:45px;height:100%;float:left;">
								
 								<input  type="button"  value="提交修改" style="float:left;" onclick="changet<%=item.getCid() %>()" >
								</div>
								<!-- <div style="width:45px;height:100%;float:right;">
								<input type="button" value="删除商品" style="float:right;" onclick="deletep<%=item.getCid() %>()">
								</div>
								 -->
								
								</td>
							</tr>
							</form>
							<%} %>
							<form id="addNew" action="" method="post">
							<tr>
								<td>
									
									新商品类型*
								</td>
								<td>
									<input type="text" name="cname" value=""><span style="color:red">*</span>
								</td>
								<td>
									<input type="text" name="cxinhao" value="" ><span style="color:red">*</span>
								</td>
								
								<td style="width:100px;">
								<input type="button" value="添加商品类型" onclick="addNewProductType()">
								</td>
							</tr>
							</form>
					</table>
					<div style="width:100%;height:100px;margin-left:40px;margin-right:40px;margin-top:100px;">
					 <form id="ProductTypeSearch" action="" method="post" >
					 <input type="text" name="qureyKey" value="" placeholder="输入商品类型名字" style="margin-left:0px;">

 					 <input type="button" value="查询" onclick="search()">
 					 </form>
					 </div>
					
				
			</div>
		
		
		</div>

		
		
	</body>
</html>