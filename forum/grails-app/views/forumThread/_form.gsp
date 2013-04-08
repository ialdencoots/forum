<%@ page import="forum.ForumThread" %>
<%@ page import="forum.Post" %>


<div class="fieldcontain ${hasErrors(bean: forumThreadInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="forumThread.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${forumThreadInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="post.message.label" default="Message" />
		
	</label>
	<g:textArea name="message" value="${postInstance?.message}" rows="5"/>
</div>

<input type="hidden" name="topic.id" value="${topicID}" />
