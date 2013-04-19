package forum

class ForumTopic {

	String name
	static hasMany = [threads: ForumThread]

    static constraints = {
		name blank: false
    }

	static mapping = {
		threads sort: 'created', order: 'asc'
	}


	String toString() {
		return name
		}
}
