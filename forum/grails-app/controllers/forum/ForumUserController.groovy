package forum

import org.springframework.dao.DataIntegrityViolationException

class ForumUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [forumUserInstanceList: ForumUser.list(params), forumUserInstanceTotal: ForumUser.count()]
    }

    def create() {
        [forumUserInstance: new ForumUser(params)]
    }

    def save() {
        def forumUserInstance = new ForumUser(params)
        if (!forumUserInstance.save(flush: true)) {
            render(view: "create", model: [forumUserInstance: forumUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'forumUser.label', default: 'ForumUser'), forumUserInstance.id])
        redirect(action: "show", id: forumUserInstance.id)
    }

    def show(Long id) {
        def forumUserInstance = ForumUser.get(id)
        if (!forumUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumUser.label', default: 'ForumUser'), id])
            redirect(action: "list")
            return
        }

        [forumUserInstance: forumUserInstance]
    }

    def edit(Long id) {
        def forumUserInstance = ForumUser.get(id)
        if (!forumUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumUser.label', default: 'ForumUser'), id])
            redirect(action: "list")
            return
        }

        [forumUserInstance: forumUserInstance]
    }

    def update(Long id, Long version) {
        def forumUserInstance = ForumUser.get(id)
        if (!forumUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumUser.label', default: 'ForumUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (forumUserInstance.version > version) {
                forumUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'forumUser.label', default: 'ForumUser')] as Object[],
                          "Another user has updated this ForumUser while you were editing")
                render(view: "edit", model: [forumUserInstance: forumUserInstance])
                return
            }
        }

        forumUserInstance.properties = params

        if (!forumUserInstance.save(flush: true)) {
            render(view: "edit", model: [forumUserInstance: forumUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'forumUser.label', default: 'ForumUser'), forumUserInstance.id])
        redirect(action: "show", id: forumUserInstance.id)
    }

    def delete(Long id) {
        def forumUserInstance = ForumUser.get(id)
        if (!forumUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumUser.label', default: 'ForumUser'), id])
            redirect(action: "list")
            return
        }

        try {
            forumUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'forumUser.label', default: 'ForumUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'forumUser.label', default: 'ForumUser'), id])
            redirect(action: "show", id: id)
        }
    }
}
