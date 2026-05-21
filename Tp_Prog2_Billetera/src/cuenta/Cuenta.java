package cuenta;

import java.util.*;

import actividades.Actividad;
import actividades.FondoLiquidezEmpresarial;
import actividades.InversionDivisa;
import actividades.InversionRentaFija;
import actividades.Transferencia;

public abstract class Cuenta {

	String cvu;
	String alias;

	double saldoDisponible;

	List<Actividad> actividades;

	int volumenTransacciones;

	public double getSaldoDisponible() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void debitar(double monto) {
		// TODO Auto-generated method stub
		
	}

	public void acreditar(double monto) {
		// TODO Auto-generated method stub
		
	}

	public void agregarActividad(Transferencia t) {
		// TODO Auto-generated method stub
		
	}

	public void agregarActividad(InversionRentaFija inv) {
		// TODO Auto-generated method stub
		
	}

	public void agregarActividad(InversionDivisa inv) {
		// TODO Auto-generated method stub
		
	}

	public void agregarActividad(FondoLiquidezEmpresarial inv) {
		// TODO Auto-generated method stub
		
	}

	public Actividad[] getActividades() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getVolumenTransacciones() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
