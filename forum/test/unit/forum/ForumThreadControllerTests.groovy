package forum



import org.junit.*
import grails.test.mixin.*

@TestFor(ForumThreadController)
@Mock(ForumThread)
class ForumThreadControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/forumThread/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.forumThreadInstanceList.size() == 0
        assert model.forumThreadInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.forumThreadInstance != null
    }

    void testSave() {
        controller.save()

        assert model.forumThreadInstance != null
        assert view == '/forumThread/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/forumThread/show/1'
        assert controller.flash.message != null
        assert ForumThread.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/forumThread/list'

        populateValidParams(params)
        def forumThread = new ForumThread(params)

        assert forumThread.save() != null

        params.id = forumThread.id

        def model = controller.show()

        assert model.forumThreadInstance == forumThread
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/forumThread/list'

        populateValidParams(params)
        def forumThread = new ForumThread(params)

        assert forumThread.save() != null

        params.id = forumThread.id

        def model = controller.edit()

        assert model.forumThreadInstance == forumThread
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/forumThread/list'

        response.reset()

        populateValidParams(params)
        def forumThread = new ForumThread(params)

        assert forumThread.save() != null

        // test invalid parameters in update
        params.id = forumThread.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/forumThread/edit"
        assert model.forumThreadInstance != null

        forumThread.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/forumThread/show/$forumThread.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        forumThread.clearErrors()

        populateValidParams(params)
        params.id = forumThread.id
        params.version = -1
        controller.update()

        assert view == "/forumThread/edit"
        assert model.forumThreadInstance != null
        assert model.forumThreadInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/forumThread/list'

        response.reset()

        populateValidParams(params)
        def forumThread = new ForumThread(params)

        assert forumThread.save() != null
        assert ForumThread.count() == 1

        params.id = forumThread.id

        controller.delete()

        assert ForumThread.count() == 0
        assert ForumThread.get(forumThread.id) == null
        assert response.redirectedUrl == '/forumThread/list'
    }
}
