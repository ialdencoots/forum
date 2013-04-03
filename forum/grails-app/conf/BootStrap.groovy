import forum.ForumUser
import forum.ForumRole
import forum.ForumUserForumRole
import forum.ForumTopic

class BootStrap {

    def init = { servletContext ->

	def adminRole = new ForumRole(authority: 'ROLE_ADMIN').save(flush: true)
	def userRole = new ForumRole(authority: 'ROLE_USER').save(flush: true)

	def testAdmin = new ForumUser(username: 'me', enabled: true, password: 'password').save(flush: true)
	def testUser = new ForumUser(username: 'apa', enabled: true, password: 'banan').save(flush: true)

	ForumUserForumRole.create testAdmin, adminRole, true
	ForumUserForumRole.create testUser, userRole, true

	def top1 = new ForumTopic(name: "Cars").save(flush: true)
	def top2 = new ForumTopic(name: "Chicks").save(flush: true)
	def top3 = new ForumTopic(name: "Beer").save(flush: true)

	assert ForumUser.count() == 2
	assert ForumRole.count() == 2
	assert ForumUserForumRole.count() == 2

    }
    def destroy = {
    }
}
