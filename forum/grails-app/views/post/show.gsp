
<%@ page import="forum.Post" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-post" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-post" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list post">
			
				<g:if test="${postInstance?.message}">
				<li class="fieldcontain">
					<span id="message-label" class="property-label"><g:message code="post.message.label" default="Message" /></span>
					
						<span class="property-value" aria-labelledby="message-label"><g:fieldValue bean="${postInstance}" field="message"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${postInstance?.thread}">
				<li class="fieldcontain">
					<span id="thread-label" class="property-label"><g:message code="post.thread.label" default="Thread" /></span>
					
						<span class="property-value" aria-labelledby="thread-label"><g:link controller="forumThread" action="show" id="${postInstance?.thread?.id}">${postInstance?.thread?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${postInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="post.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${postInstance?.time}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${postInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="post.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="forumUser" action="show" id="${postInstance?.user?.id}">${postInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${postInstance?.id}" />
					<g:link class="edit" action="edit" id="${postInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
