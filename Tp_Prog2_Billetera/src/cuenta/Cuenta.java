package cuenta;

import java.util.*;
import actividades.*;

public abstract class Cuenta {

	protected String cvu;
	protected String alias;

	protected double saldoDisponible;

	protected HashMap<Integer, Actividad> actividades;

	protected int volumenTransacciones;

	public Cuenta(String cvu, String alias) {

		this.cvu = cvu;
		this.alias = alias;

		this.saldoDisponible = 0;

		this.actividades = new HashMap<>();

		this.volumenTransacciones = 0;
	}

	public String getCvu() {
		return cvu;
	}

	public String getAlias() {
		return alias;
	}

	public double getSaldoDisponible() {
		return saldoDisponible;
	}

	public void debitar(double monto) {

		if (monto <= 0)
			throw new IllegalArgumentException("Monto invalido");

		if (saldoDisponible < monto)
			throw new IllegalArgumentException("Saldo insuficiente");

		saldoDisponible -= monto;
	}

	public void acreditar(double monto) {

		if (monto <= 0)
			throw new IllegalArgumentException("Monto invalido");

		saldoDisponible += monto;
	}

	public void agregarActividad(Transferencia transferencia) {

		actividades.put(transferencia.getId(), transferencia);

		volumenTransacciones++;
	}

	public void agregarActividad(InversionRentaFija inversion) {

		actividades.put(inversion.getId(), inversion);

		volumenTransacciones++;
	}

	public void agregarActividad(InversionDivisa inversion) {

		actividades.put(inversion.getId(), inversion);

		volumenTransacciones++;
	}

	public void agregarActividad(FondoLiquidezEmpresarial inversion) {

		actividades.put(inversion.getId(), inversion);

		volumenTransacciones++;
	}

	public Actividad[] getActividades() {

		return actividades.values().toArray(new Actividad[0]);
	}

	public int getVolumenTransacciones() {
		return volumenTransacciones;
	}

	@Override
	public String toString() {

		return alias + " (" + cvu + ") - Saldo: $" + saldoDisponible;
	}

}