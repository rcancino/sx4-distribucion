databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "changelog") {
		// TODO add changes and preconditions here
	}
	
	include file: 'changelog_01.groovy'

	include file: 'changelog_02.groovy'

	include file: 'changelog_03.groovy'

	include file: 'changelog_04.groovy'
}
