<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for SearchForm form</title>
	</head>
	<body>
		<h2>
		基金搜索Json数据请求测试页面
		</h2>
		<html:form action="search" method="post">
			<table border="0">
				<tr>
					<td>
						关键字:
					</td>
					<td>
						<html:text property="info" />
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<html:submit />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>

