package forum

class Post {

	static belongsTo = [thread: ForumThread]
	Date time
	String message
	ForumUser user

    static constraints = {
    }
}
