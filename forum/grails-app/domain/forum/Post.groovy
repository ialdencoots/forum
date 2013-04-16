package forum

class Post {
	private static final Date NULL_DATE = new Date(0)

	static belongsTo = [thread: ForumThread]
	Date time = NULL_DATE
	String message
	ForumUser user

	def beforeInsert() {
		if (time == NULL_DATE) {
			time = new Date()
		}
	}

    static constraints = {
		message blank: false
    }

	String toString() {
		if (message.size() < 30) {
			return message
		}
		else return message[0..29] + '...'
	}
}
