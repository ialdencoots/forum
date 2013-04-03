package forum



import org.junit.*
import grails.test.mixin.*

@TestFor(ForumUserController)
@Mock(ForumUser)
class ForumUserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/forumUser/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.forumUserInstanceList.size() == 0
        assert model.forumUserInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.forumUserInstance != null
    }

    void testSave() {
        controller.save()

        assert model.forumUserInstance != null
        assert view == '/forumUser/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/forumUser/show/1'
        assert controller.flash.message != null
        assert ForumUser.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/forumUser/list'

        populateValidParams(params)
        def forumUser = new ForumUser(params)

        assert forumUser.save() != null

        params.id = forumUser.id

        def model = controller.show()

        assert model.forumUserInstance == forumUser
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/forumUser/list'

        populateValidParams(params)
        def forumUser = new ForumUser(params)

        assert forumUser.save() != null

        params.id = forumUser.id

        def model = controller.edit()

        assert model.forumUserInstance == forumUser
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/forumUser/list'

        response.reset()

        populateValidParams(params)
        def forumUser = new ForumUser(params)

        assert forumUser.save() != null

        // test invalid parameters in update
        params.id = forumUser.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/forumUser/edit"
        assert model.forumUserInstance != null

        forumUser.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/forumUser/show/$forumUser.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        forumUser.clearErrors()

        populateValidParams(params)
        params.id = forumUser.id
        params.version = -1
        controller.update()

        assert view == "/forumUser/edit"
        assert model.forumUserInstance != null
        assert model.forumUserInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/forumUser/list'

        response.reset()

        populateValidParams(params)
        def forumUser = new ForumUser(params)

        assert forumUser.save() != null
        assert ForumUser.count() == 1

        params.id = forumUser.id

        controller.delete()

        assert ForumUser.count() == 0
        assert ForumUser.get(forumUser.id) == null
        assert response.redirectedUrl == '/forumUser/list'
    }
}
