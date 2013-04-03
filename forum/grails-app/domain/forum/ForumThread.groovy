package forum

class ForumThread {

	static hasMany = [posts: Post]
	String title
	Date date

    static constraints = {
    }
}
