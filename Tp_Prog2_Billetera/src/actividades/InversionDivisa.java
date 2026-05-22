package actividades;

import cuenta.Cuenta;
import interfaz.Utilitarios;

public class InversionDivisa extends Inversion {

	private String divisa;

	private double tasa;

	private double cotizacionInicial;

	public InversionDivisa(Cuenta cuenta, double monto, int plazoDias, String divisa, double tasa) {

		super(cuenta, monto, plazoDias);

		this.divisa = divisa;

		this.tasa = tasa;

		this.cotizacionInicial = Utilitarios.consultarCotizacion(divisa);
	}

	public int getId() {
		return id;
	}

	public String getDivisa() {
		return divisa;
	}

	public double getTasa() {
		return tasa;
	}

	@Override
	public void precancelar() {

		if (!activa)
			return;

		long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, Utilitarios.hoy());

		// USD comprados al momento original
		double cantidadDivisas = montoInvertido / cotizacionInicial;

		double interesesDivisa = cantidadDivisas * (tasa / 365.0) * dias;

		interesesDivisa /= 2.0;

		double cotizacionActual = Utilitarios.consultarCotizacion(divisa);

		double pesosDevueltos = (cantidadDivisas + interesesDivisa) * cotizacionActual;

		cuenta.acreditar(pesosDevueltos);

		activa = false;
	}

	@Override
	public String toString() {

		String estado;

		if (aprobada)
			estado = "Aprobada";
		else
			estado = "Rechazada";

		return "fecha: " + fecha + "\norigen: " + cuenta.getCvu() + "\ndesc: Divisa " + divisa + "\nmonto: " + monto
				+ "\nplazo: " + plazoDias + "\n" + estado;
	}

}