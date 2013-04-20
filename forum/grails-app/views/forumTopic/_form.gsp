<%@ page import="forum.ForumTopic" %>



<div class="fieldcontain ${hasErrors(bean: forumTopicInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="forumTopic.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${forumTopicInstance?.name}"/>
</div>
