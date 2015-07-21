databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1437490979878-1") {
		addColumn(tableName: "corte") {
			column(name: "cancelado", type: "date")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-2") {
		addColumn(tableName: "corte") {
			column(name: "cancelado_user", type: "varchar(50)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-3") {
		addColumn(tableName: "surtido") {
			column(name: "cancelado_user", type: "varchar(50)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-4") {
		addColumn(tableName: "surtido") {
			column(name: "clasificacion", type: "varchar(30)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-5") {
		addColumn(tableName: "surtido") {
			column(name: "revision", type: "datetime")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-6") {
		addColumn(tableName: "surtido") {
			column(name: "revision_usuario", type: "varchar(40)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-7") {
		modifyDataType(columnName: "cancelado", newDataType: "date", tableName: "surtido")
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-8") {
		dropNotNullConstraint(columnDataType: "date", columnName: "cancelado", tableName: "surtido")
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-9") {
		createIndex(indexName: "surtido_det_id_uniq_1437490979432", tableName: "corte", unique: "true") {
			column(name: "surtido_det_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-10") {
		createIndex(indexName: "venta_id_uniq_1437490979443", tableName: "instruccion_de_entrega", unique: "true") {
			column(name: "venta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437490979878-11") {
		createIndex(indexName: "FK_qpqh5on1cqa0ktsitg2vhmirv", tableName: "usuario_role") {
			column(name: "role_id")
		}
	}
}
