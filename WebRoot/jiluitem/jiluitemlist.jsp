<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>网上教务评教系统</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">

</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="98%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td align="center">
  <span style="font-weight: bold;"> ${title } </span>
 </td>
 </tr>
</table>
</td>
</tr>
</table>
  
<!--  内容列表   -->


<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="skin/images/tbg.gif">
<input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />
	
	</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td >评估内容</td>
	<td >获得评估等级</td>
	<td >得分</td>
	<td >评估时间</td>
	
	
</tr>

 <c:forEach items="${list}"  var="bean">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >

	<td>${bean.neirong.neirong  }</td>
	<td>${bean.dengji }</td>
	<td>${bean.defen }</td>
	<td>${bean.shijian }</td>
	

</tr>
</c:forEach>




<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center">${pagerinfo }</td>
</tr>
</table>


</body>
</html>