package forum

class Category {

	String name
	static hasMany = [threads: ForumThread]

    static constraints = {
    }
}
