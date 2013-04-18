<%@ page import="forum.Post" %>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="post.message.label" default="Message" />
		
	</label>
	<g:textArea name="message" value="${postInstance?.message}" rows="5"/>
</div>

<input type="hidden" name="user.id" value="${currentUserID}" />
<input type="hidden" name="thread.id" value="${threadID}" />
