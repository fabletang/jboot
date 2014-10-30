<html> 
	<head> 
		<title>welcome jboot shiro!</title>
	</head> 
	<body>
		<h1>welcome jboot shiro!</h1>
		<ul>
			<#if Session["org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"]?exists>
				<li>Hi,${Session["org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"]}</li>
			<#else>
				<li><a href="${request.contextPath}/login">登录</a></li>
			</#if>
			<li><a href="${request.contextPath}/home">个人中心</a></li>
			<li><a href="${request.contextPath}/admin">系统管理</a></li>
			<#if Session["org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"]?exists>
				<li><a href="${request.contextPath}/logout">退出</a></li>
			</#if>
		</ul>
	</body>
</html>