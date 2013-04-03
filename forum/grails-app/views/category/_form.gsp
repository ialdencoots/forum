<%@ page import="forum.Category" %>



<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="category.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${categoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'threads', 'error')} ">
	<label for="threads">
		<g:message code="category.threads.label" default="Threads" />
		
	</label>
	<g:select name="threads" from="${forum.ForumThread.list()}" multiple="multiple" optionKey="id" size="5" value="${categoryInstance?.threads*.id}" class="many-to-many"/>
</div>

