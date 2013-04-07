<%@ page import="forum.Post" %>


<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'thread', 'error')} required">
	<label for="thread">
		<g:message code="post.thread.label" default="Thread" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="thread" name="thread.id" from="${forum.ForumThread.list()}" optionKey="id" required="" value="${postInstance?.thread?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="post.message.label" default="Message" />
		
	</label>
	<g:textArea name="message" value="${postInstance?.message}" rows="5"/>
</div>

<input type="hidden" name="user.id" value="${currentUserID}" />
