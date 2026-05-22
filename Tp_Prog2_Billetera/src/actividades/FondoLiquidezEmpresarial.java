package actividades;

import cuenta.Cuenta;

public class FondoLiquidezEmpresarial extends Inversion {

	private static final double MONTO_MINIMO = 20000000;

	public FondoLiquidezEmpresarial(Cuenta cuenta, double monto, int plazoDias) {

		super(cuenta, monto, plazoDias);

		if (monto < MONTO_MINIMO)
			throw new IllegalArgumentException("El fondo requiere minimo 20 millones");
	}

	public int getId() {
		return id;
	}

	public double getMontoMinimo() {
		return MONTO_MINIMO;
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

		return "fecha: " + fecha + "\norigen: " + cuenta.getCvu() + "\ndesc: Fondo Liquidez Empresarial" + "\nmonto: "
				+ monto + "\nplazo: " + plazoDias + "\n" + estado;
	}

}