package actividades;

import cuenta.Cuenta;
import interfaz.Utilitarios;

public class FondoLiquidezEmpresarial extends Inversion {

	private static final double MONTO_MINIMO = 20000000;
	
	private String activo;
	
	private double tasa;

	public FondoLiquidezEmpresarial(Cuenta cuenta, double monto, int plazoDias) {

		super(cuenta, monto, plazoDias);

		if (monto < MONTO_MINIMO)
			throw new IllegalArgumentException("El fondo requiere minimo 20 millones");
		
		this.tasa = 0.08;
		
		this.activo = "FLE";
	}

	public int getId() {
		return id;
	}

	public double getMontoMinimo() {
		return MONTO_MINIMO;
	}
	
	@Override
	public double calcularResultado() {
		if(!activa) {
			return 0.0;
		}
		
		long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, Utilitarios.hoy());
		
		double cotizacionFLE = Utilitarios.consultarCotizacion(activo);
		
		double intereses = montoInvertido * (tasa / 365.0) * dias * cotizacionFLE;
		
		return montoInvertido + intereses;
		
	}

	@Override
	public void precancelar() {

		throw new IllegalArgumentException("El Fondo Liquidez Empresarial no permite precancelacion");
	}

	@Override
	public String toString() {

		String estado;

		if (aprobada)
			estado = "Aprobada";
		else
			estado = "Rechazada";

		return "\ndesc: Fondo Liquidez Empresarial" + "\nmonto: " + monto + "\nplazo: " + plazoDias + "\n" + estado;
	}

}