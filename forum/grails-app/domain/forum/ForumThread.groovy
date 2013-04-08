package forum

class ForumThread {

	static hasMany = [posts: Post]
	static belongsTo = [topic: ForumTopic]
	String title

    static constraints = {
		title blank: false
    }

	String toString() {
		return title
	}
}
