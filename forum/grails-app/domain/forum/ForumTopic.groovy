package forum

class ForumTopic {

	String name
	static hasMany = [threads: ForumThread]

    static constraints = {
    }

	String toString() {
		return name
		}
}
