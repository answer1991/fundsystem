<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for AddSelfCheckFundForm form</title>
	</head>
	<body>
		<html:form action="/addSelfCheckFund">
			username : <html:text property="username"/><html:errors property="username"/><br/>
			dm : <html:text property="dm"/><html:errors property="dm"/><br/>
			password : <html:text property="password"/><html:errors property="password"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>

