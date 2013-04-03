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
	<g:textField name="password" required="" value="${forumUserInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: forumUserInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="forumUser.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${forumUserInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: forumUserInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="forumUser.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${forumUserInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: forumUserInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="forumUser.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${forumUserInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: forumUserInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="forumUser.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${forumUserInstance?.passwordExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: forumUserInstance, field: 'posts', 'error')} ">
	<label for="posts">
		<g:message code="forumUser.posts.label" default="Posts" />
		
	</label>
	<g:select name="posts" from="${forum.Post.list()}" multiple="multiple" optionKey="id" size="5" value="${forumUserInstance?.posts*.id}" class="many-to-many"/>
</div>

