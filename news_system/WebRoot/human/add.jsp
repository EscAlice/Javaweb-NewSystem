<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript">
	function del() {
			var msg = "确定要删除?";
			if (confirm(msg)==true){
				return true;
			}else{
				return false;
			}
		}
	function edit() {
			var msg = "确定要修改?";
			if (confirm(msg)==true){
				return true;
			}else{
				return false;
			}
		}
	</script>
  </head>
  
  <body>
  <form action="Action/HumanAction?method=add" method="post">
  	<table align="center">
	  	<tr>
	  		<td>人物姓名:</td>
	  		<td><input type="text" name="humanName"></td>
	  	</tr>
	  	<tr>
	  		<td>人物籍贯:</td>
  			<td><input type="text" name="humanAddress"></td>
	  	</tr>
	  	<tr>
	  		<td>人物年龄:</td>
	  		<td><input type="text" name="humanAge"></td>
	  	</tr>
	  	<tr>
	  		<td>人物工作:</td>
	  		<td><input type="text" name="humanWork"></td>
	  	</tr>
	  	<tr>
	  		<td>人物简介:</td>
	  		<td><textarea rows="10" cols="30"  name="humanIntr"></textarea></td>
	  	</tr>
	  	<tr>
	  		<td>人物票数:</td>
	  		<td><input type="text" value="0" name="humanNum"></td>
	  	</tr>
	  	<tr>
	  		<td colspan="2" align="center"><input type="submit" value="提交">
	  		<input type="button" value="取消添加" onclick="window.history.back()"></td>
	  	</tr>
  	</table>
  </form>
  </body>
</html>
