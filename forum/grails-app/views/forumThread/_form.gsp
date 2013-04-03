<%@ page import="forum.ForumThread" %>



<div class="fieldcontain ${hasErrors(bean: forumThreadInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="forumThread.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${forumThreadInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: forumThreadInstance, field: 'posts', 'error')} ">
	<label for="posts">
		<g:message code="forumThread.posts.label" default="Posts" />
		
	</label>
	<g:select name="posts" from="${forum.Post.list()}" multiple="multiple" optionKey="id" size="5" value="${forumThreadInstance?.posts*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: forumThreadInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="forumThread.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${forumThreadInstance?.title}"/>
</div>

