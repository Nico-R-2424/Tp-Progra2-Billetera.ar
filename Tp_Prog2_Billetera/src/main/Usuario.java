package main;

import java.util.*;

import cuenta.Cuenta;
import cuenta.CuentaCorporativa;
import cuenta.CuentaPremium;
import cuenta.CuentaRegular;

public class Usuario {

	public Usuario(String dni2, String nombre2, String telefono2, String email2) {
		// TODO Auto-generated constructor stub
	}

	String dni;
	String nombre;
	String telefono;
	String email;

	HashMap<String, Cuenta> cuentas;

	double totalInvertido;

	public void agregarCuenta(CuentaRegular cuenta) {
		// TODO Auto-generated method stub
		
	}

	public void agregarCuenta(CuentaPremium cuenta) {
		// TODO Auto-generated method stub
		
	}

	public void agregarCuenta(CuentaCorporativa cuenta) {
		// TODO Auto-generated method stub
		
	}

	public Cuenta[] getCuentas() {
		// TODO Auto-generated method stub
		return null;
	}

	public void sumarInversion(double monto) {
		// TODO Auto-generated method stub
		
	}

	public double getTotalInvertido() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
