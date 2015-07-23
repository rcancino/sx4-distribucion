databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1437582239655-1") {
		createTable(tableName: "auxiliar_de_corte") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "auxiliar_de_cPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "corte_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(9)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-2") {
		createTable(tableName: "auxiliar_de_surtido") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "auxiliar_de_sPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "surtido_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-3") {
		createTable(tableName: "chofer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "choferPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "activo", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "telefono", type: "varchar(255)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-4") {
		createTable(tableName: "cliente") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "clientePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "varchar(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-5") {
		createTable(tableName: "corte") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cortePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "asignado", type: "varchar(255)")

			column(name: "cancelado", type: "date")

			column(name: "cancelado_user", type: "varchar(50)")

			column(name: "cantidad", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "cortes", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(250)") {
				constraints(nullable: "false")
			}

			column(name: "empacado_fin", type: "datetime")

			column(name: "empacado_inicio", type: "datetime")

			column(name: "empacador", type: "varchar(255)")

			column(name: "entregado", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "fin", type: "datetime")

			column(name: "inicio", type: "datetime")

			column(name: "instruccion", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pedido", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "pendiente", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "precio_corte", type: "decimal(19,4)")

			column(name: "producto", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "sucursal", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "surtido_det_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(9)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-6") {
		createTable(tableName: "embarque") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "embarquePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cerrado", type: "datetime")

			column(name: "chofer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "documento", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "date") {
				constraints(nullable: "false")
			}

			column(name: "kilometro_final", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "kilometro_inicial", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "kilos", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(255)")

			column(name: "regreso", type: "datetime")

			column(name: "salida", type: "datetime")

			column(name: "sucursal", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "valor", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-7") {
		createTable(tableName: "entrega") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "entregaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "arribo", type: "datetime")

			column(name: "cliente", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(255)")

			column(name: "comentario_de_comision", type: "varchar(255)")

			column(name: "comision", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "comision_por_tonelada", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "documento", type: "varchar(15)") {
				constraints(nullable: "false")
			}

			column(name: "embarque_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha_comision", type: "datetime")

			column(name: "fecha_de_documento", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "importe_comision", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "kilos", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "paquetes", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "parcial", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "por_cobrar", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "recepcion", type: "datetime")

			column(name: "recibio", type: "varchar(255)")

			column(name: "tipo_de_documento", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "total_documento", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "valor", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-8") {
		createTable(tableName: "entrega_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "entrega_detPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "decimal(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "entrega_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "producto_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "valor", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "venta_det_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-9") {
		createTable(tableName: "folio") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "folioPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "serie", type: "varchar(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-10") {
		createTable(tableName: "instruccion_de_entrega") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "instruccion_dPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(255)")

			column(name: "direccion_calle", type: "varchar(200)")

			column(name: "direccion_codigo_postal", type: "varchar(255)")

			column(name: "direccion_colonia", type: "varchar(255)")

			column(name: "direccion_estado", type: "varchar(255)")

			column(name: "direccion_municipio", type: "varchar(255)")

			column(name: "direccion_numero_exterior", type: "varchar(50)")

			column(name: "direccion_numero_interior", type: "varchar(50)")

			column(name: "direccion_pais", type: "varchar(100)")

			column(name: "entrega_id", type: "bigint")

			column(name: "venta_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-11") {
		createTable(tableName: "producto") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "productoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "acabado", type: "varchar(20)")

			column(name: "activo", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "ancho", type: "decimal(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "calibre", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "caras", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "clase", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "color", type: "varchar(20)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "de_linea", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(300)") {
				constraints(nullable: "false")
			}

			column(name: "gramos", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "inventariable", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "kilos", type: "decimal(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "largo", type: "decimal(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "linea", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "marca", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "modo_de_venta", type: "varchar(10)")

			column(name: "nacional", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "bigint")

			column(name: "precio_de_contado", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "precio_de_credito", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "presentacion", type: "varchar(20)")

			column(name: "unidad", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-12") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-13") {
		createTable(tableName: "sucursal") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sucursalPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-14") {
		createTable(tableName: "surtido") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "surtidoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "asignado", type: "varchar(50)")

			column(name: "cancelado", type: "date")

			column(name: "cancelado_user", type: "varchar(50)")

			column(name: "clasificacion", type: "varchar(30)")

			column(name: "cliente", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(255)")

			column(name: "corte_fin", type: "datetime")

			column(name: "corte_inicio", type: "datetime")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "entregado", type: "datetime")

			column(name: "entrego", type: "varchar(255)")

			column(name: "facturado", type: "datetime")

			column(name: "fecha", type: "date") {
				constraints(nullable: "false")
			}

			column(name: "fecha_puesto", type: "datetime")

			column(name: "forma", type: "varchar(10)")

			column(name: "forma_de_entrega", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "iniciado", type: "datetime")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pedido", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "pedido_creado", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "puesto", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "revision", type: "datetime")

			column(name: "revision_usuario", type: "varchar(40)")

			column(name: "sucursal", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(9)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_venta", type: "varchar(10)")

			column(name: "vendedor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "venta", type: "varchar(20)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-15") {
		createTable(tableName: "surtido_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "surtido_detPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(250)") {
				constraints(nullable: "false")
			}

			column(name: "entregado", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "factor", type: "decimal(19,3)")

			column(name: "fecha_hora", type: "datetime")

			column(name: "kilos", type: "decimal(19,3)")

			column(name: "origen", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pendiente", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "producto", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "sectores", type: "varchar(255)")

			column(name: "surtido_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "partidas_idx", type: "integer")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-16") {
		createTable(tableName: "usuario") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "usuarioPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "apellido_materno", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "apellido_paterno", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)")

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "nip", type: "varchar(4)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombres", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_empleado", type: "integer")

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "puesto", type: "varchar(30)")

			column(name: "sucursal", type: "varchar(20)")

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-17") {
		createTable(tableName: "usuario_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "usuario_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-18") {
		createTable(tableName: "venta") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ventaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "anticipo", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(255)")

			column(name: "comentario2", type: "varchar(255)")

			column(name: "comprador", type: "varchar(50)")

			column(name: "creado_factura", type: "datetime")

			column(name: "creado_pedido", type: "datetime")

			column(name: "creado_user", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "documento", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "fecha_entrega", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "flete", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "forma_entrega", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "forma_pago", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_comision_tarjeta", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impreso", type: "datetime")

			column(name: "impuesto", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "instruccion_id", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "misma", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "varchar(5)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(255)")

			column(name: "parcial", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "puesto_fecha", type: "datetime")

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "socio", type: "varchar(50)")

			column(name: "sucursal_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "tc", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_venta", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "update_user", type: "varchar(255)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-19") {
		createTable(tableName: "venta_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "venta_detPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "decimal(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "cortes", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(300)") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "descuento_origen", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "factor", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_cortes", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_descuento", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_neto", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "instruccion_de_cortes", type: "varchar(50)")

			column(name: "kilos", type: "decimal(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(255)")

			column(name: "precio", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "precio_cortes", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "precio_de_lista", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "producto_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "subtotal", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "unidad", type: "varchar(3)") {
				constraints(nullable: "false")
			}

			column(name: "venta_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "partidas_idx", type: "integer")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-20") {
		addPrimaryKey(columnNames: "role_id, usuario_id", constraintName: "usuario_rolePK", tableName: "usuario_role")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-38") {
		createIndex(indexName: "FK_b6v250v9kru9lvlxa4homcelf", tableName: "auxiliar_de_corte") {
			column(name: "corte_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-39") {
		createIndex(indexName: "FK_t94kkbohogrmcsakir9004jgg", tableName: "auxiliar_de_surtido") {
			column(name: "surtido_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-40") {
		createIndex(indexName: "FK_x6mu29toaxbhqvixalgwa5sp", tableName: "corte") {
			column(name: "surtido_det_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-41") {
		createIndex(indexName: "surtido_det_id_uniq_1437582239577", tableName: "corte", unique: "true") {
			column(name: "surtido_det_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-42") {
		createIndex(indexName: "FK_8t7yqs8e57hrd8iwk9glawk24", tableName: "embarque") {
			column(name: "chofer_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-43") {
		createIndex(indexName: "FK_3dopcmc0q2sy50pwvkkrj62t1", tableName: "entrega") {
			column(name: "embarque_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-44") {
		createIndex(indexName: "FK_7yqstm7a3bcxu5svl36xdxwu0", tableName: "entrega_det") {
			column(name: "entrega_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-45") {
		createIndex(indexName: "FK_ab3uliolfudk0r5svyii03gx7", tableName: "entrega_det") {
			column(name: "producto_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-46") {
		createIndex(indexName: "FK_lv0lyge5pyuio55ygi9l94b33", tableName: "entrega_det") {
			column(name: "venta_det_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-47") {
		createIndex(indexName: "FK_anx7fa8risgh9ifjqs662do9k", tableName: "instruccion_de_entrega") {
			column(name: "entrega_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-48") {
		createIndex(indexName: "FK_fllllsvpc5t5wqhx6t6wm43ve", tableName: "instruccion_de_entrega") {
			column(name: "venta_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-49") {
		createIndex(indexName: "venta_id_uniq_1437582239586", tableName: "instruccion_de_entrega", unique: "true") {
			column(name: "venta_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-50") {
		createIndex(indexName: "clave_uniq_1437582239587", tableName: "producto", unique: "true") {
			column(name: "clave")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-51") {
		createIndex(indexName: "authority_uniq_1437582239588", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-52") {
		createIndex(indexName: "FK_hrtihcylqqein7qhp60dt7r0p", tableName: "surtido_det") {
			column(name: "surtido_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-53") {
		createIndex(indexName: "nip_uniq_1437582239593", tableName: "usuario", unique: "true") {
			column(name: "nip")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-54") {
		createIndex(indexName: "username_uniq_1437582239594", tableName: "usuario", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-55") {
		createIndex(indexName: "FK_55sbft3wldu0yr078kdq6hwxe", tableName: "usuario_role") {
			column(name: "usuario_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-56") {
		createIndex(indexName: "FK_qpqh5on1cqa0ktsitg2vhmirv", tableName: "usuario_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-57") {
		createIndex(indexName: "FK_ga1c0oyebvdlmcw6s46875ura", tableName: "venta") {
			column(name: "cliente_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-58") {
		createIndex(indexName: "FK_s9v0j867vi8pcdvkec60cm6nm", tableName: "venta") {
			column(name: "sucursal_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-59") {
		createIndex(indexName: "FK_ct6vsk3aaug4ooeschvi2tymv", tableName: "venta_det") {
			column(name: "venta_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-60") {
		createIndex(indexName: "FK_ky077dqo04bpiu84fjub7h6yo", tableName: "venta_det") {
			column(name: "producto_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-21") {
		addForeignKeyConstraint(baseColumnNames: "corte_id", baseTableName: "auxiliar_de_corte", constraintName: "FK_b6v250v9kru9lvlxa4homcelf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "corte", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-22") {
		addForeignKeyConstraint(baseColumnNames: "surtido_id", baseTableName: "auxiliar_de_surtido", constraintName: "FK_t94kkbohogrmcsakir9004jgg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "surtido", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-23") {
		addForeignKeyConstraint(baseColumnNames: "surtido_det_id", baseTableName: "corte", constraintName: "FK_x6mu29toaxbhqvixalgwa5sp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "surtido_det", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-24") {
		addForeignKeyConstraint(baseColumnNames: "chofer_id", baseTableName: "embarque", constraintName: "FK_8t7yqs8e57hrd8iwk9glawk24", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "chofer", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-25") {
		addForeignKeyConstraint(baseColumnNames: "embarque_id", baseTableName: "entrega", constraintName: "FK_3dopcmc0q2sy50pwvkkrj62t1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "embarque", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-26") {
		addForeignKeyConstraint(baseColumnNames: "entrega_id", baseTableName: "entrega_det", constraintName: "FK_7yqstm7a3bcxu5svl36xdxwu0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "entrega", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-27") {
		addForeignKeyConstraint(baseColumnNames: "producto_id", baseTableName: "entrega_det", constraintName: "FK_ab3uliolfudk0r5svyii03gx7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "producto", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-28") {
		addForeignKeyConstraint(baseColumnNames: "venta_det_id", baseTableName: "entrega_det", constraintName: "FK_lv0lyge5pyuio55ygi9l94b33", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "venta_det", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-29") {
		addForeignKeyConstraint(baseColumnNames: "entrega_id", baseTableName: "instruccion_de_entrega", constraintName: "FK_anx7fa8risgh9ifjqs662do9k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "entrega", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-30") {
		addForeignKeyConstraint(baseColumnNames: "venta_id", baseTableName: "instruccion_de_entrega", constraintName: "FK_fllllsvpc5t5wqhx6t6wm43ve", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "venta", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-31") {
		addForeignKeyConstraint(baseColumnNames: "surtido_id", baseTableName: "surtido_det", constraintName: "FK_hrtihcylqqein7qhp60dt7r0p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "surtido", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-32") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "usuario_role", constraintName: "FK_qpqh5on1cqa0ktsitg2vhmirv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-33") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "usuario_role", constraintName: "FK_55sbft3wldu0yr078kdq6hwxe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-34") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "venta", constraintName: "FK_ga1c0oyebvdlmcw6s46875ura", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cliente", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-35") {
		addForeignKeyConstraint(baseColumnNames: "sucursal_id", baseTableName: "venta", constraintName: "FK_s9v0j867vi8pcdvkec60cm6nm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sucursal", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-36") {
		addForeignKeyConstraint(baseColumnNames: "producto_id", baseTableName: "venta_det", constraintName: "FK_ky077dqo04bpiu84fjub7h6yo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "producto", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1437582239655-37") {
		addForeignKeyConstraint(baseColumnNames: "venta_id", baseTableName: "venta_det", constraintName: "FK_ct6vsk3aaug4ooeschvi2tymv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "venta", referencesUniqueColumn: "false")
	}
}
