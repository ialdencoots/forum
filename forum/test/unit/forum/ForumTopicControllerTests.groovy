package forum



import org.junit.*
import grails.test.mixin.*

@TestFor(ForumTopicController)
@Mock(ForumTopic)
class ForumTopicControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/forumTopic/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.forumTopicInstanceList.size() == 0
        assert model.forumTopicInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.forumTopicInstance != null
    }

    void testSave() {
        controller.save()

        assert model.forumTopicInstance != null
        assert view == '/forumTopic/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/forumTopic/show/1'
        assert controller.flash.message != null
        assert ForumTopic.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/forumTopic/list'

        populateValidParams(params)
        def forumTopic = new ForumTopic(params)

        assert forumTopic.save() != null

        params.id = forumTopic.id

        def model = controller.show()

        assert model.forumTopicInstance == forumTopic
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/forumTopic/list'

        populateValidParams(params)
        def forumTopic = new ForumTopic(params)

        assert forumTopic.save() != null

        params.id = forumTopic.id

        def model = controller.edit()

        assert model.forumTopicInstance == forumTopic
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/forumTopic/list'

        response.reset()

        populateValidParams(params)
        def forumTopic = new ForumTopic(params)

        assert forumTopic.save() != null

        // test invalid parameters in update
        params.id = forumTopic.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/forumTopic/edit"
        assert model.forumTopicInstance != null

        forumTopic.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/forumTopic/show/$forumTopic.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        forumTopic.clearErrors()

        populateValidParams(params)
        params.id = forumTopic.id
        params.version = -1
        controller.update()

        assert view == "/forumTopic/edit"
        assert model.forumTopicInstance != null
        assert model.forumTopicInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/forumTopic/list'

        response.reset()

        populateValidParams(params)
        def forumTopic = new ForumTopic(params)

        assert forumTopic.save() != null
        assert ForumTopic.count() == 1

        params.id = forumTopic.id

        controller.delete()

        assert ForumTopic.count() == 0
        assert ForumTopic.get(forumTopic.id) == null
        assert response.redirectedUrl == '/forumTopic/list'
    }
}
