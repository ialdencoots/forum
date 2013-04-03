<%@ page import="forum.ForumTopic" %>



<div class="fieldcontain ${hasErrors(bean: forumTopicInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="forumTopic.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${forumTopicInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: forumTopicInstance, field: 'threads', 'error')} ">
	<label for="threads">
		<g:message code="forumTopic.threads.label" default="Threads" />
		
	</label>
	<g:select name="threads" from="${forum.ForumThread.list()}" multiple="multiple" optionKey="id" size="5" value="${forumTopicInstance?.threads*.id}" class="many-to-many"/>
</div>

