package forum

import org.apache.commons.lang.builder.HashCodeBuilder

class ForumUserForumRole implements Serializable {

	ForumUser forumUser
	ForumRole forumRole

	boolean equals(other) {
		if (!(other instanceof ForumUserForumRole)) {
			return false
		}

		other.forumUser?.id == forumUser?.id &&
			other.forumRole?.id == forumRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (forumUser) builder.append(forumUser.id)
		if (forumRole) builder.append(forumRole.id)
		builder.toHashCode()
	}

	static ForumUserForumRole get(long forumUserId, long forumRoleId) {
		find 'from ForumUserForumRole where forumUser.id=:forumUserId and forumRole.id=:forumRoleId',
			[forumUserId: forumUserId, forumRoleId: forumRoleId]
	}

	static ForumUserForumRole create(ForumUser forumUser, ForumRole forumRole, boolean flush = false) {
		new ForumUserForumRole(forumUser: forumUser, forumRole: forumRole).save(flush: flush, insert: true)
	}

	static boolean remove(ForumUser forumUser, ForumRole forumRole, boolean flush = false) {
		ForumUserForumRole instance = ForumUserForumRole.findByForumUserAndForumRole(forumUser, forumRole)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(ForumUser forumUser) {
		executeUpdate 'DELETE FROM ForumUserForumRole WHERE forumUser=:forumUser', [forumUser: forumUser]
	}

	static void removeAll(ForumRole forumRole) {
		executeUpdate 'DELETE FROM ForumUserForumRole WHERE forumRole=:forumRole', [forumRole: forumRole]
	}

	static mapping = {
		id composite: ['forumRole', 'forumUser']
		version false
	}
}
