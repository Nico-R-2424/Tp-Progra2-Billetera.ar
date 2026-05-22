package actividades;

import cuenta.Cuenta;
import interfaz.Utilitarios;

public class InversionRentaFija extends Inversion {

	private double tasaInteres;

	public InversionRentaFija(Cuenta cuenta, double monto, int plazoDias) {

		super(cuenta, monto, plazoDias);

		this.tasaInteres = 0.10; // tasa fija ejemplo
	}

	public int getId() {
		return id;
	}

	public double getTasaInteres() {
		return tasaInteres;
	}

	@Override
	public void precancelar() {

		if (!activa)
			return;

		long dias = fechaInicio.until(Utilitarios.hoy()).getDays();

		double tasa = 0.20;

		double intereses = montoInvertido * (tasa / 365.0) * dias;

		intereses /= 2.0;

		cuenta.acreditar(montoInvertido + intereses);

		activa = false;
	}

	@Override
	public String toString() {

		String estado;

		if (aprobada)
			estado = "Aprobada";
		else
			estado = "Rechazada";

		return "fecha: " + fecha + "\norigen: " + cuenta.getCvu() + "\ndesc: Renta Fija" + "\nmonto: " + monto
				+ "\nplazo: " + plazoDias + "\n" + estado;
	}

}