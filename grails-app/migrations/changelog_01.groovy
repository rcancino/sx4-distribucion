databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1437486194147-1") {
		createTable(tableName: "auxiliar_de_corte") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "corte_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(9)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-2") {
		createTable(tableName: "auxiliar_de_surtido") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "surtido_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-3") {
		createTable(tableName: "chofer") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "activo", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "telefono", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-4") {
		createTable(tableName: "cliente") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "VARCHAR(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "VARCHAR(13)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-5") {
		createTable(tableName: "corte") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "asignado", type: "VARCHAR(255)")

			column(name: "cantidad", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "cortes", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(250)") {
				constraints(nullable: "false")
			}

			column(name: "empacado_fin", type: "DATETIME")

			column(name: "empacado_inicio", type: "DATETIME")

			column(name: "empacador", type: "VARCHAR(255)")

			column(name: "entregado", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "fin", type: "DATETIME")

			column(name: "inicio", type: "DATETIME")

			column(name: "instruccion", type: "VARCHAR(255)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "pedido", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "pendiente", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "precio_corte", type: "DECIMAL(19,4)")

			column(name: "producto", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "sucursal", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "surtido_det_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(9)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-6") {
		createTable(tableName: "embarque") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cerrado", type: "DATETIME")

			column(name: "chofer_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "documento", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "kilometro_final", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "kilometro_inicial", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "kilos", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "VARCHAR(255)")

			column(name: "regreso", type: "DATETIME")

			column(name: "salida", type: "DATETIME")

			column(name: "sucursal", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "valor", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-7") {
		createTable(tableName: "entrega") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "arribo", type: "DATETIME")

			column(name: "cliente", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "comentario_de_comision", type: "VARCHAR(255)")

			column(name: "comision", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "comision_por_tonelada", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "documento", type: "VARCHAR(15)") {
				constraints(nullable: "false")
			}

			column(name: "embarque_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha_comision", type: "DATETIME")

			column(name: "fecha_de_documento", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "importe_comision", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "kilos", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "paquetes", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "parcial", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "por_cobrar", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "recepcion", type: "DATETIME")

			column(name: "recibio", type: "VARCHAR(255)")

			column(name: "tipo_de_documento", type: "VARCHAR(10)") {
				constraints(nullable: "false")
			}

			column(name: "total_documento", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "valor", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-8") {
		createTable(tableName: "entrega_det") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "DECIMAL(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "entrega_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "producto_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "valor", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "venta_det_id", type: "BIGINT")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-9") {
		createTable(tableName: "folio") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "serie", type: "VARCHAR(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-10") {
		createTable(tableName: "instruccion_de_entrega") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "direccion_calle", type: "VARCHAR(200)")

			column(name: "direccion_codigo_postal", type: "VARCHAR(255)")

			column(name: "direccion_colonia", type: "VARCHAR(255)")

			column(name: "direccion_estado", type: "VARCHAR(255)")

			column(name: "direccion_municipio", type: "VARCHAR(255)")

			column(name: "direccion_numero_exterior", type: "VARCHAR(50)")

			column(name: "direccion_numero_interior", type: "VARCHAR(50)")

			column(name: "direccion_pais", type: "VARCHAR(100)")

			column(name: "entrega_id", type: "BIGINT")

			column(name: "venta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-11") {
		createTable(tableName: "producto") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "acabado", type: "VARCHAR(20)")

			column(name: "activo", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "ancho", type: "DECIMAL(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "calibre", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "caras", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "clase", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "color", type: "VARCHAR(20)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "de_linea", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(300)") {
				constraints(nullable: "false")
			}

			column(name: "gramos", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "inventariable", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "kilos", type: "DECIMAL(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "largo", type: "DECIMAL(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "linea", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "marca", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "modo_de_venta", type: "VARCHAR(10)")

			column(name: "nacional", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "BIGINT")

			column(name: "precio_de_contado", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "precio_de_credito", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "presentacion", type: "VARCHAR(20)")

			column(name: "unidad", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-12") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-13") {
		createTable(tableName: "sucursal") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-14") {
		createTable(tableName: "surtido") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "asignado", type: "VARCHAR(50)")

			column(name: "cancelado", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "cliente", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "corte_fin", type: "DATETIME")

			column(name: "corte_inicio", type: "DATETIME")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "entregado", type: "DATETIME")

			column(name: "entrego", type: "VARCHAR(255)")

			column(name: "facturado", type: "DATETIME")

			column(name: "fecha", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "fecha_puesto", type: "DATETIME")

			column(name: "forma", type: "VARCHAR(10)")

			column(name: "forma_de_entrega", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "iniciado", type: "DATETIME")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "pedido", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "pedido_creado", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "puesto", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "sucursal", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "VARCHAR(9)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_venta", type: "VARCHAR(10)")

			column(name: "vendedor", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "venta", type: "VARCHAR(20)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-15") {
		createTable(tableName: "surtido_det") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(250)") {
				constraints(nullable: "false")
			}

			column(name: "entregado", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "factor", type: "DECIMAL(19,3)")

			column(name: "fecha_hora", type: "DATETIME")

			column(name: "kilos", type: "DECIMAL(19,3)")

			column(name: "origen", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "pendiente", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "producto", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "sectores", type: "VARCHAR(255)")

			column(name: "surtido_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "partidas_idx", type: "INT")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-16") {
		createTable(tableName: "usuario") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "apellido_materno", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "apellido_paterno", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "VARCHAR(255)")

			column(name: "enabled", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "nip", type: "VARCHAR(6)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombres", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_empleado", type: "INT")

			column(name: "password", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "puesto", type: "VARCHAR(30)")

			column(name: "sucursal", type: "VARCHAR(20)")

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-17") {
		createTable(tableName: "usuario_role") {
			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "usuario_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-18") {
		createTable(tableName: "venta") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "anticipo", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "VARCHAR(255)")

			column(name: "comentario2", type: "VARCHAR(255)")

			column(name: "comprador", type: "VARCHAR(50)")

			column(name: "creado_factura", type: "DATETIME")

			column(name: "creado_pedido", type: "DATETIME")

			column(name: "creado_user", type: "VARCHAR(255)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "documento", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "fecha_entrega", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "flete", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "forma_entrega", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "forma_pago", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_comision_tarjeta", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impreso", type: "DATETIME")

			column(name: "impuesto", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "instruccion_id", type: "VARCHAR(255)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "misma", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "VARCHAR(5)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "VARCHAR(255)")

			column(name: "parcial", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "puesto_fecha", type: "DATETIME")

			column(name: "rfc", type: "VARCHAR(13)") {
				constraints(nullable: "false")
			}

			column(name: "socio", type: "VARCHAR(50)")

			column(name: "sucursal_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "tc", type: "DECIMAL(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_venta", type: "VARCHAR(10)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "update_user", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-19") {
		createTable(tableName: "venta_det") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "DECIMAL(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "cortes", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(300)") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "descuento_origen", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "factor", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_cortes", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_descuento", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_neto", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "instruccion_de_cortes", type: "VARCHAR(50)")

			column(name: "kilos", type: "DECIMAL(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "VARCHAR(255)")

			column(name: "precio", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "precio_cortes", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "precio_de_lista", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "producto_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "subtotal", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "unidad", type: "VARCHAR(3)") {
				constraints(nullable: "false")
			}

			column(name: "venta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "partidas_idx", type: "INT")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-20") {
		addPrimaryKey(columnNames: "role_id, usuario_id", tableName: "usuario_role")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-38") {
		createIndex(indexName: "UK_iac48enn17bc50wqa3p5ld46b", tableName: "producto", unique: "true") {
			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-39") {
		createIndex(indexName: "UK_irsamgnera6angm0prq1kemt2", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-40") {
		createIndex(indexName: "UK_863n1y3x0jalatoir4325ehal", tableName: "usuario", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-41") {
		createIndex(indexName: "UK_gs8rdlrg0dmwbv5h16h9wv2se", tableName: "usuario", unique: "true") {
			column(name: "nip")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-21") {
		addForeignKeyConstraint(baseColumnNames: "corte_id", baseTableName: "auxiliar_de_corte", baseTableSchemaName: "sx4", constraintName: "FK_b6v250v9kru9lvlxa4homcelf", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "corte", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-22") {
		addForeignKeyConstraint(baseColumnNames: "surtido_id", baseTableName: "auxiliar_de_surtido", baseTableSchemaName: "sx4", constraintName: "FK_t94kkbohogrmcsakir9004jgg", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "surtido", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-23") {
		addForeignKeyConstraint(baseColumnNames: "surtido_det_id", baseTableName: "corte", baseTableSchemaName: "sx4", constraintName: "FK_x6mu29toaxbhqvixalgwa5sp", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "surtido_det", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-24") {
		addForeignKeyConstraint(baseColumnNames: "chofer_id", baseTableName: "embarque", baseTableSchemaName: "sx4", constraintName: "FK_8t7yqs8e57hrd8iwk9glawk24", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "chofer", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-25") {
		addForeignKeyConstraint(baseColumnNames: "embarque_id", baseTableName: "entrega", baseTableSchemaName: "sx4", constraintName: "FK_3dopcmc0q2sy50pwvkkrj62t1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "embarque", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-26") {
		addForeignKeyConstraint(baseColumnNames: "entrega_id", baseTableName: "entrega_det", baseTableSchemaName: "sx4", constraintName: "FK_7yqstm7a3bcxu5svl36xdxwu0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "entrega", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-27") {
		addForeignKeyConstraint(baseColumnNames: "producto_id", baseTableName: "entrega_det", baseTableSchemaName: "sx4", constraintName: "FK_ab3uliolfudk0r5svyii03gx7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "producto", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-28") {
		addForeignKeyConstraint(baseColumnNames: "venta_det_id", baseTableName: "entrega_det", baseTableSchemaName: "sx4", constraintName: "FK_lv0lyge5pyuio55ygi9l94b33", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "venta_det", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-29") {
		addForeignKeyConstraint(baseColumnNames: "entrega_id", baseTableName: "instruccion_de_entrega", baseTableSchemaName: "sx4", constraintName: "FK_anx7fa8risgh9ifjqs662do9k", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "entrega", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-30") {
		addForeignKeyConstraint(baseColumnNames: "venta_id", baseTableName: "instruccion_de_entrega", baseTableSchemaName: "sx4", constraintName: "FK_fllllsvpc5t5wqhx6t6wm43ve", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "venta", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-31") {
		addForeignKeyConstraint(baseColumnNames: "surtido_id", baseTableName: "surtido_det", baseTableSchemaName: "sx4", constraintName: "FK_hrtihcylqqein7qhp60dt7r0p", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "surtido", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-32") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "usuario_role", baseTableSchemaName: "sx4", constraintName: "FK_qpqh5on1cqa0ktsitg2vhmirv", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-33") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "usuario_role", baseTableSchemaName: "sx4", constraintName: "FK_55sbft3wldu0yr078kdq6hwxe", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "usuario", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-34") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "venta", baseTableSchemaName: "sx4", constraintName: "FK_ga1c0oyebvdlmcw6s46875ura", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cliente", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-35") {
		addForeignKeyConstraint(baseColumnNames: "sucursal_id", baseTableName: "venta", baseTableSchemaName: "sx4", constraintName: "FK_s9v0j867vi8pcdvkec60cm6nm", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "sucursal", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-36") {
		addForeignKeyConstraint(baseColumnNames: "producto_id", baseTableName: "venta_det", baseTableSchemaName: "sx4", constraintName: "FK_ky077dqo04bpiu84fjub7h6yo", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "producto", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1437486194147-37") {
		addForeignKeyConstraint(baseColumnNames: "venta_id", baseTableName: "venta_det", baseTableSchemaName: "sx4", constraintName: "FK_ct6vsk3aaug4ooeschvi2tymv", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "venta", referencedTableSchemaName: "sx4", referencesUniqueColumn: "false")
	}
}
