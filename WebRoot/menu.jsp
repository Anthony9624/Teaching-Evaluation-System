<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="skin/js/frame/menu.js"></script>
<base target="main" />
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
	 <c:if test="${user.role==1}">
	<!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>管理系统</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            
           
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!neironglist' target='main'>评估内容管理</a></div>
               
              </div>
            </li>
            
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!userlist' target='main'>学生用户管理</a></div>
               
              </div>
            </li>
            
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!userlist2' target='main'>教师用户管理</a></div>
               
              </div>
            </li>
            
          </ul>
        </dd>
      </dl>
      
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>评估查询统计</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            
           
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!jilulist6' target='main'>评估记录查询</a></div>
               
              </div>
            </li>
             <li>
              <div class='items'>
                <div class='fllct'><a href='method!tongjilist2' target='main'>评估统计</a></div>
               
              </div>
            </li>
            
            
          </ul>
        </dd>
      </dl>
      
      
      <!-- Item 1 End -->
      
          </c:if>
      
        <c:if test="${user.role==2}">
      <!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>自我评估</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            
           
            
          
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!neironglist2' target='main'>自我评估</a></div>
               
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!jilulist' target='main'>自我评估查询</a></div>
               
              </div>
            </li>
            
            
            
          </ul>
        </dd>
      </dl>
      
      
      
      
      <!-- Item 1 End -->
      
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>教师互评</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            
           
            
          
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!userlist3' target='main'>评估教师列表</a></div>
               
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!jilulist2' target='main'>发起的评估</a></div>
               
              </div>
            </li>
            
             <li>
              <div class='items'>
                <div class='fllct'><a href='method!jilulist3' target='main'>收到的评估</a></div>
               
              </div>
            </li>
            
            
            
          </ul>
        </dd>
      </dl>
      
      
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>学生评教</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
            
           
            
          
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!jilulist5' target='main'>学生评教查询</a></div>
               
              </div>
            </li>
            
            
            
            
            
          </ul>
        </dd>
      </dl>
      
      
      <dl class='bitem'>
        <dt onClick='showHide("items4_1")'><b>评估统计</b></dt>
        <dd style='display:block' class='sitem' id='items4_1'>
          <ul class='sitemu'>
            
           
            
          
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!tongjilist' target='main'>评估统计</a></div>
               
              </div>
            </li>
            
            
            
            
            
          </ul>
        </dd>
      </dl>
      
      </c:if>
      
      
      
      <c:if test="${user.role==3}">
      <!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>学生评教</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            
           
            
          
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!userlist4' target='main'>评估教师列表</a></div>
               
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!jilulist4' target='main'>评教记录查询</a></div>
               
              </div>
            </li>
            
            
            
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      
      
           
            
          
     
      
      </c:if>
      
      
	  </td>
  </tr>
</table>
</body>
</html>