
<%@ page import="forum.ForumThread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:javascript library="jquery" />
		<g:set var="entityName" value="${message(code: 'forumThread.label', default: 'ForumThread')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-forumThread" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-forumThread" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list forumThread">
			
				<g:if test="${forumThreadInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="forumThread.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${forumThreadInstance}" field="title"/></span>
					
				</li>
				</g:if>

				<g:if test="${forumThreadInstance?.topic}">
				<li class="fieldcontain">
					<span id="topic-label" class="property-label"><g:message code="forumThread.topic.label" default="Topic" /></span>
					
						<span class="property-value" aria-labelledby="topic-label"><g:link controller="forumTopic" action="show" id="${forumThreadInstance?.topic?.id}">${forumThreadInstance?.topic?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			
				<g:if test="${forumThreadInstance?.posts}">
					
				<g:render template="/displaypost" collection="${forumThreadPosts}" var="postInstance" />
				</g:if>
			
			</ol>

			<div id="newPost">


			<g:form>
				<fieldset class="buttons">
					<script type="text/javascript">
						var threadID = ${forumThreadInstance?.id};
					</script>
					<button type="button" class="edit"
						onclick="${ remoteFunction(
										action: 'create',
										controller: 'post',
										params: '\'threadID=\' + threadID',
										update: 'newPost')}">Comment</button>
				</fieldset>
			</g:form>
			</div>
		</div>
	</body>
</html>
