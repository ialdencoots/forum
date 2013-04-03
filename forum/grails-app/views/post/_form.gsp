<%@ page import="forum.Post" %>



<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="post.message.label" default="Message" />
		
	</label>
	<g:textField name="message" value="${postInstance?.message}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'thread', 'error')} required">
	<label for="thread">
		<g:message code="post.thread.label" default="Thread" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="thread" name="thread.id" from="${forum.ForumThread.list()}" optionKey="id" required="" value="${postInstance?.thread?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="post.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${postInstance?.time}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="post.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${forum.ForumUser.list()}" optionKey="id" required="" value="${postInstance?.user?.id}" class="many-to-one"/>
</div>

