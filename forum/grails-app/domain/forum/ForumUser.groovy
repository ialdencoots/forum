package forum

class ForumUser {
	private static final Date NULL_DATE = new Date(0)

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Date signupDate = NULL_DATE

	static hasMany = [posts: Post]

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
		posts sort: 'time', order: 'asc'
	}

	Set<ForumRole> getAuthorities() {
		ForumUserForumRole.findAllByForumUser(this).collect { it.forumRole } as Set
	}

	def beforeInsert() {
		encodePassword()
		if (signupDate == NULL_DATE) {
			signupDate = new Date()
		}
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

	String toString() {
		return username
	}
}
