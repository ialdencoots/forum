package forum

import org.springframework.dao.DataIntegrityViolationException

class ForumTopicController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [forumTopicInstanceList: ForumTopic.list(sort: "name"), forumTopicInstanceTotal: ForumTopic.count()]
    }

    def create() {
        [forumTopicInstance: new ForumTopic(params)]
    }

    def save() {
        def forumTopicInstance = new ForumTopic(params)
        if (!forumTopicInstance.save(flush: true)) {
            render(view: "create", model: [forumTopicInstance: forumTopicInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), forumTopicInstance.id])
        redirect(action: "list")
    }

    def show(Long id) {
        def forumTopicInstance = ForumTopic.get(id)
        if (!forumTopicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), id])
            redirect(action: "list")
            return
        }

        [forumTopicInstance: forumTopicInstance]
    }

    def edit(Long id) {
        def forumTopicInstance = ForumTopic.get(id)
        if (!forumTopicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), id])
            redirect(action: "list")
            return
        }

        [forumTopicInstance: forumTopicInstance]
    }

    def update(Long id, Long version) {
        def forumTopicInstance = ForumTopic.get(id)
        if (!forumTopicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (forumTopicInstance.version > version) {
                forumTopicInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'forumTopic.label', default: 'ForumTopic')] as Object[],
                          "Another user has updated this ForumTopic while you were editing")
                render(view: "edit", model: [forumTopicInstance: forumTopicInstance])
                return
            }
        }

        forumTopicInstance.properties = params

        if (!forumTopicInstance.save(flush: true)) {
            render(view: "edit", model: [forumTopicInstance: forumTopicInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), forumTopicInstance.id])
        redirect(action: "show", id: forumTopicInstance.id)
    }

    def delete(Long id) {
        def forumTopicInstance = ForumTopic.get(id)
        if (!forumTopicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), id])
            redirect(action: "list")
            return
        }

        try {
            forumTopicInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), id])
            redirect(action: "show", id: id)
        }
    }
}
