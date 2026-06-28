package main;

import java.util.*;
import cuenta.*;

public class Usuario {

	private String dni;

	private String nombre;

	private String telefono;

	private String email;

	private HashMap<String, Cuenta> cuentas;

	private double totalInvertido;

	public Usuario(String dni, String nombre, String telefono, String email) {

		this.dni = dni;

		this.nombre = nombre;

		this.telefono = telefono;

		this.email = email;

		this.cuentas = new HashMap<>();

		this.totalInvertido = 0;
	}

	public String getDni() {
		return dni;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public void agregarCuenta(CuentaRegular cuenta) {

		cuentas.put(cuenta.getCvu(), cuenta);
	}

	public void agregarCuenta(CuentaPremium cuenta) {

		cuentas.put(cuenta.getCvu(), cuenta);
	}

	public void agregarCuenta(CuentaCorporativa cuenta) {

		cuentas.put(cuenta.getCvu(), cuenta);
	}

	public Cuenta[] getCuentas() {

		return cuentas.values().toArray(new Cuenta[0]);
	}

	public Cuenta getCuenta(String cvu) {

		return cuentas.get(cvu);
	}
	
	public boolean tieneCuenta(String cvu) {

		return cuentas.containsKey(cvu);
	}

	public void sumarInversion(double monto) {

		totalInvertido += monto;
	}

	public void restarInversion(double monto) {

		totalInvertido -= monto;

		if (totalInvertido < 0)

			totalInvertido = 0;
	}

	public double getTotalInvertido() {
		
		return totalInvertido;
		
	}
		

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Usuario: ");

		sb.append(nombre);

		sb.append(" (");

		sb.append(dni);

		sb.append(") ");

		sb.append("| cuentas: ");

		sb.append(cuentas.size());

		sb.append(" | invertido: $");

		sb.append(totalInvertido);

		return sb.toString();
	}

}