databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1437582406094-1") {
		modifyDataType(columnName: "cancelado", newDataType: "date", tableName: "surtido")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582406094-2") {
		dropNotNullConstraint(columnDataType: "date", columnName: "cancelado", tableName: "surtido")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582406094-3") {
		dropNotNullConstraint(columnDataType: "varchar(30)", columnName: "clasificacion", tableName: "surtido")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582406094-4") {
		createIndex(indexName: "surtido_det_id_uniq_1437582405455", tableName: "corte", unique: "true") {
			column(name: "surtido_det_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582406094-5") {
		createIndex(indexName: "venta_id_uniq_1437582405461", tableName: "instruccion_de_entrega", unique: "true") {
			column(name: "venta_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582406094-6") {
		createIndex(indexName: "FK_qpqh5on1cqa0ktsitg2vhmirv", tableName: "usuario_role") {
			column(name: "role_id")
		}
	}
}
