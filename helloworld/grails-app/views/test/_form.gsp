<%@ page import="helloworld.Test" %>



<div class="fieldcontain ${hasErrors(bean: testInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="test.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${testInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: testInstance, field: 'userName', 'error')} ">
	<label for="userName">
		<g:message code="test.userName.label" default="User Name" />
		
	</label>
	<g:textField name="userName" value="${testInstance?.userName}"/>
</div>

