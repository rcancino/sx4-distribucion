databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1440173572962-1") {
		addColumn(tableName: "corte") {
			column(name: "asignacion", type: "datetime")
		}
	}
}
