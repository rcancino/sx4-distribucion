databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1439054148568-1") {
		addColumn(tableName: "surtido") {
			column(name: "depurado", type: "datetime")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1439054148568-2") {
		addColumn(tableName: "surtido") {
			column(name: "depurado_user", type: "varchar(50)")
		}
	}
}
