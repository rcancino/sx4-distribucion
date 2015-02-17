package com.luxsoft.sx4.sec

import org.apache.commons.lang.builder.HashCodeBuilder

class UsuarioRole implements Serializable {

	private static final long serialVersionUID = 1

	Usuario usuario
	Role role

	boolean equals(other) {
		if (!(other instanceof UsuarioRole)) {
			return false
		}

		other.usuario?.id == usuario?.id &&
		other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (usuario) builder.append(usuario.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static UsuarioRole get(long usuarioId, long roleId) {
		UsuarioRole.where {
			usuario == Usuario.load(usuarioId) &&
			role == Role.load(roleId)
		}.get()
	}

	static boolean exists(long usuarioId, long roleId) {
		UsuarioRole.where {
			usuario == Usuario.load(usuarioId) &&
			role == Role.load(roleId)
		}.count() > 0
	}

	static UsuarioRole create(Usuario usuario, Role role, boolean flush = false) {
		def instance = new UsuarioRole(usuario: usuario, role: role)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Usuario u, Role r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UsuarioRole.where {
			usuario == Usuario.load(u.id) &&
			role == Role.load(r.id)
		}.deleteAll()

		if (flush) { UsuarioRole.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(Usuario u, boolean flush = false) {
		if (u == null) return

		UsuarioRole.where {
			usuario == Usuario.load(u.id)
		}.deleteAll()

		if (flush) { UsuarioRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		UsuarioRole.where {
			role == Role.load(r.id)
		}.deleteAll()

		if (flush) { UsuarioRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, UsuarioRole ur ->
			if (ur.usuario == null) return
			boolean existing = false
			UsuarioRole.withNewSession {
				existing = UsuarioRole.exists(ur.usuario.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['role', 'usuario']
		version false
	}
}
