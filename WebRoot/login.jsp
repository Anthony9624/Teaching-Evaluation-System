<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="ceping.util.Util"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Util.init(request);
%>


<title>网上教务评教系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
<script language="JavaScript">
function correctPNG()
{
    var arVersion = navigator.appVersion.split("MSIE")
    var version = parseFloat(arVersion[1])
    if ((version >= 5.5) && (document.body.filters)) 
    {
       for(var j=0; j<document.images.length; j++)
       {
          var img = document.images[j]
          var imgName = img.src.toUpperCase()
          if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
          {
             var imgID = (img.id) ? "id='" + img.id + "' " : ""
             var imgClass = (img.className) ? "class='" + img.className + "' " : ""
             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
             var imgStyle = "display:inline-block;" + img.style.cssText 
             if (img.align == "left") imgStyle = "float:left;" + imgStyle
             if (img.align == "right") imgStyle = "float:right;" + imgStyle
             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle
             var strNewHTML = "<span " + imgID + imgClass + imgTitle
             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
             img.outerHTML = strNewHTML
             j = j-1
          }
       }
    }    
}
window.attachEvent("onload", correctPNG);
</script>



<script language="javascript" type="text/javascript"> 

		
function registershow(){
		var now = new Date(); 
		var t = now.getTime()+''; 
		//window.showModalDialog("register.jsp?t="+t, null,'dialogWidth:700px;dialogHeight:500px;help:no;unadorned:no;resizable:no;status:no;scroll:no');

		if(window.showModalDialog == undefined){  //谷歌,火狐等浏览器
		     window.myNewWindow = window.open("register.jsp?t="+t,'newwindow', 'height=500, width=700, top=100, left=250, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no'); 
		}else{  //IE
			window.showModalDialog("register.jsp?t="+t, null, 
			'dialogWidth:700px;dialogHeight:500px;help:no;unadorned:no;resizable:no;status:no;scroll:no');
		}
	}
</script>

<script language="javascript" type="text/javascript"> 

		
function registershow2(){
		var now = new Date(); 
		var t = now.getTime()+''; 
		//window.showModalDialog("register2.jsp?t="+t, null,'dialogWidth:700px;dialogHeight:500px;help:no;unadorned:no;resizable:no;status:no;scroll:no');

		if(window.showModalDialog == undefined){  //谷歌,火狐等浏览器
		     window.myNewWindow = window.open("register2.jsp?t="+t,'newwindow', 'height=500, width=700, top=100, left=250, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no'); 
		}else{  //IE
			window.showModalDialog("register2.jsp?t="+t, null, 
			'dialogWidth:700px;dialogHeight:500px;help:no;unadorned:no;resizable:no;status:no;scroll:no');
		}
	}
</script>

<link href="images2/skin.css" rel="stylesheet" type="text/css">
<body>
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="center" valign="top"><span style="font-size: 30px;color: blue;font-weight: bold;">网上教务评教系统</span></td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    
                   
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">用户登录</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
                    <form name="myform" action="method!login" method="post">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text"><span class="login_txt">用户名：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="2" class="top_hui_text"><input name="username" class="editbox4" style="width: 150px;">                            

			</td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="password" style="width: 150px;" name="password">
                              <img src="images2/luck.gif" width="19" height="18"> </td>
                          </tr>
                          
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 用户角色： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text">
                            <select name="role">
                            <option value="3">学生用户</option>
                            <option value="2">教师用户</option>
                            <option value="1">系统管理员</option>
                            </select>
                             </td>
                          </tr>
                         
                          <tr>
                            <td height="35" >&nbsp;</td>
                            <td width="20%" height="35" ><input name="Submit" type="submit" class="button" id="Submit" value="登 录"> </td>
                            <td width="67%" class="top_hui_text">
                            <input  type="button" class="button" id="Submit" value="学生用户注册" onclick="registershow()"> 
                            &nbsp; &nbsp; &nbsp;
                       <input  type="button" class="button" id="Submit" value="教师用户注册" onclick="registershow2()"> 
                            </td>
                          </tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="images2/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt"></span></td>
      </tr>
    </table></td>
  </tr>
</table>
