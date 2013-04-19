
<%@ page import="forum.ForumUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'forumUser.label', default: 'ForumUser')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-forumUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-forumUser" class="content scaffold-show" role="main">
			<h1>Forum User ${forumUserInstance?.username}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list forumUser">
			
				<g:if test="${forumUserInstance?.posts}">

				<g:render template="/displayuserpost" collection="${forumUserPosts}" var="postInstance" />

				</g:if>
			
			</ol>
		</div>
	</body>
</html>
