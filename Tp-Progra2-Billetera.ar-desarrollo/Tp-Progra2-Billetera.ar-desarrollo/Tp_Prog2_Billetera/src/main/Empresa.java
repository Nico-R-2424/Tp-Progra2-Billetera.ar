package main;

import java.util.HashMap;

public class Empresa {

	private String cuit;
	private String nombreFantasia;

	private HashMap<String, Usuario> autorizados;

	public Empresa(String cuit, String nombreFantasia, String telefono, String email, String nombreContacto) {

		this.cuit = cuit;

		this.nombreFantasia = nombreFantasia;

		this.autorizados = new HashMap<>();
	}

	/*
	 * CASO 1: El usuario ya existe
	 */

	public void agregarAutorizado(Usuario usuario) {

		if (usuario == null)
			throw new IllegalArgumentException("Usuario invalido");

		if (autorizados.containsKey(usuario.getDni()))

			throw new IllegalArgumentException("Usuario ya autorizado");

		autorizados.put(usuario.getDni(), usuario);
	}

	/*
	 * CASO 2: No existe usuario Se crea
	 */

	public void agregarAutorizado(String dni, String nombre, String telefono, String email) {

		if (autorizados.containsKey(dni))

			throw new IllegalArgumentException("Usuario ya autorizado");

		Usuario nuevo = new Usuario(dni, nombre, telefono, email);

		autorizados.put(dni, nuevo);
	}

	public boolean estaAutorizado(String dniUsuario) {

		return autorizados.containsKey(dniUsuario);
	}

	public Usuario getAutorizado(String dni) {

		return autorizados.get(dni);
	}

	public HashMap<String, Usuario> getAutorizados() {

		return autorizados;
	}

	public String getCuit() {
		return cuit;
	}

	public String getNombreFantasia() {

		return nombreFantasia;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Empresa: ");

		sb.append(nombreFantasia);

		sb.append(" | CUIT: ");

		sb.append(cuit);

		sb.append(" | Autorizados: ");

		sb.append(autorizados.size());

		return sb.toString();
	}

}