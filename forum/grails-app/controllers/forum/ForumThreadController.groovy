package forum

import org.springframework.dao.DataIntegrityViolationException

class ForumThreadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [forumThreadInstanceList: ForumThread.list(params), forumThreadInstanceTotal: ForumThread.count()]
    }

    def create() {
        [forumThreadInstance: new ForumThread(params)]
    }

    def save() {
        def forumThreadInstance = new ForumThread(params)
        if (!forumThreadInstance.save(flush: true)) {
            render(view: "create", model: [forumThreadInstance: forumThreadInstance])
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

        [forumThreadInstance: forumThreadInstance]
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
