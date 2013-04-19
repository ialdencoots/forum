<%@ page import="forum.ForumUser" %>



<div class="fieldcontain ${hasErrors(bean: forumUserInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="forumUser.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${forumUserInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: forumUserInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="forumUser.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="password" type="password" required="" value="${forumUserInstance?.password}"/>
</div>
