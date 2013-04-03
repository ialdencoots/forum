
<%@ page import="forum.ForumUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'forumUser.label', default: 'ForumUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-forumUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-forumUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'forumUser.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'forumUser.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="accountExpired" title="${message(code: 'forumUser.accountExpired.label', default: 'Account Expired')}" />
					
						<g:sortableColumn property="accountLocked" title="${message(code: 'forumUser.accountLocked.label', default: 'Account Locked')}" />
					
						<g:sortableColumn property="enabled" title="${message(code: 'forumUser.enabled.label', default: 'Enabled')}" />
					
						<g:sortableColumn property="passwordExpired" title="${message(code: 'forumUser.passwordExpired.label', default: 'Password Expired')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${forumUserInstanceList}" status="i" var="forumUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${forumUserInstance.id}">${fieldValue(bean: forumUserInstance, field: "username")}</g:link></td>
					
						<td>${fieldValue(bean: forumUserInstance, field: "password")}</td>
					
						<td><g:formatBoolean boolean="${forumUserInstance.accountExpired}" /></td>
					
						<td><g:formatBoolean boolean="${forumUserInstance.accountLocked}" /></td>
					
						<td><g:formatBoolean boolean="${forumUserInstance.enabled}" /></td>
					
						<td><g:formatBoolean boolean="${forumUserInstance.passwordExpired}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${forumUserInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
