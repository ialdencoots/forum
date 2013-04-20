
<%@ page import="forum.ForumTopic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'forumTopic.label', default: 'ForumTopic')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-forumTopic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-forumThread" class="content scaffold-show" role="main">
			<h1>Topics</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list forumTopic">

			<g:render template="displaytopic" collection="${forumTopicInstanceList}" var="topicInstance" />

			</ol>

			<div class="pagination">
				<g:paginate total="${forumTopicInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
