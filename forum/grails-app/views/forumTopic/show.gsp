
<%@ page import="forum.ForumTopic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'forumTopic.label', default: 'ForumTopic')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-forumTopic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Topic List</g:link></li>
				<li><g:link controller="forumThread" class="create" action="create" params="[topicID:forumTopicInstance?.id]">New Thread</g:link></li>
			</ul>
		</div>
		<div id="show-forumTopic" class="content scaffold-show" role="main">
			<h1>${forumTopicInstance?.name}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list forumTopic">
			
				<g:if test="${forumTopicInstance?.threads}">
					
					<g:render template="/displaythread" collection="${forumTopicInstance?.threads}" var="threadInstance"/>

				</g:if>
			
			</ol>
		</div>
	</body>
</html>
