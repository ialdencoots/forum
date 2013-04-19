package forum

class ForumThread {

	static hasMany = [posts: Post]
	static belongsTo = [topic: ForumTopic]
	String title
	Date created

    static constraints = {
		title blank: false
    }

	static mapping = {
		posts sort: 'time', order: 'asc'
	}

	String toString() {
		return title
	}
}
