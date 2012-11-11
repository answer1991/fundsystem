<%@ page language="java" pageEncoding="gb2312"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>基金历史走势Json数据请求测试页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<h2>基金历史走势Json数据请求测试页面</h2>
    <html:form action="jjjz" method="post">
      <table border="0">
        <tr>
          <td>代码:</td>
          <td><html:text property="dm" /></td>
        </tr>
        
        <tr>
          <td>天数:</td>
          <td><html:text property="lastDate" /></td>
        </tr>
   
        <tr>
          <td colspan="2" align="center"><html:submit /></td>
        </tr>
      </table>
    </html:form>
  </body>
</html:html>
