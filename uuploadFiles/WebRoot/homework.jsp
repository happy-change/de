<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'homework.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
  
   <form action="${pageContext.request.contextPath}/Upload" method="post" enctype="multipart/form-data">
   <table border="1">
     <tr><td>姓名：</td><td><input type="text" name="sname"></td></tr>
     <tr><td>学号：</td><td><input type="text" name="sno"></td></tr>
     <tr><td>班级：</td>
     	 <td><select>
 			 <option value ="volvo">软件工程1801</option>
  			 <option value ="saab">软件工程1802</option>
 			 <option value="opel">软件工程1803</option>
 			 </select></td></tr>
     <tr><td>文件选择</td><td><input type="file" name="photo"></td></tr>
  
   
     <tr><td align="center" colspan="2"><input type="submit" value="文件上传"></td></tr>

     </table>
   </form>
  
  
 
  </body>
</html>
