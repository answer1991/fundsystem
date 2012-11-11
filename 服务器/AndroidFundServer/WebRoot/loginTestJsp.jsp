<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for LoginForm form</title>
	</head>
	<body>
		<html:form action="/login">
			account : <html:text property="username"/><html:errors property="username"/><br/>
			password : <html:text property="password"/><html:errors property="password"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>

