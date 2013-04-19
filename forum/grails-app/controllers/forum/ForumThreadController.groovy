package forum

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class ForumThreadController {
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [forumThreadInstanceList: ForumThread.list(params), forumThreadInstanceTotal: ForumThread.count()]
    }

	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def create() {

        [forumThreadInstance: new ForumThread(params), topicID: params.topicID, postInstance: new Post(params)]
    }

	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def save() {
		def postInstance = new Post()
		postInstance.message = params.message
		postInstance.user = springSecurityService.currentUser
        def forumThreadInstance = new ForumThread(params).addToPosts(postInstance)
        if (!forumThreadInstance.save(flush: true)) {
            render(view: "create", model: [forumThreadInstance: forumThreadInstance, postInstance: postInstance, topicID: params.topic.id])
            return
        }
        if (!postInstance.save(flush: true)) {
            render(view: "show", model: [forumThreadInstance: forumThreadInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'forumThread.label', default: 'ForumThread'), forumThreadInstance.id])
        redirect(action: "show", id: forumThreadInstance.id)
    }

    def show(Long id) {
        def forumThreadInstance = ForumThread.get(id)
        if (!forumThreadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumThread.label', default: 'ForumThread'), id])
            redirect(action: "list")
            return
        }

        [forumThreadInstance: forumThreadInstance, forumThreadPosts: forumThreadInstance.getPosts()]
    }

    def edit(Long id) {
        def forumThreadInstance = ForumThread.get(id)
        if (!forumThreadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumThread.label', default: 'ForumThread'), id])
            redirect(action: "list")
            return
        }

        [forumThreadInstance: forumThreadInstance]
    }

    def update(Long id, Long version) {
        def forumThreadInstance = ForumThread.get(id)
        if (!forumThreadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumThread.label', default: 'ForumThread'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (forumThreadInstance.version > version) {
                forumThreadInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'forumThread.label', default: 'ForumThread')] as Object[],
                          "Another user has updated this ForumThread while you were editing")
                render(view: "edit", model: [forumThreadInstance: forumThreadInstance])
                return
            }
        }

        forumThreadInstance.properties = params

        if (!forumThreadInstance.save(flush: true)) {
            render(view: "edit", model: [forumThreadInstance: forumThreadInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'forumThread.label', default: 'ForumThread'), forumThreadInstance.id])
        redirect(action: "show", id: forumThreadInstance.id)
    }

    def delete(Long id) {
        def forumThreadInstance = ForumThread.get(id)
        if (!forumThreadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumThread.label', default: 'ForumThread'), id])
            redirect(action: "list")
            return
        }

        try {
            forumThreadInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'forumThread.label', default: 'ForumThread'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'forumThread.label', default: 'ForumThread'), id])
            redirect(action: "show", id: id)
        }
    }
}
