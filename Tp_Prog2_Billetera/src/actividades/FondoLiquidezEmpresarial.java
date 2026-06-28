package actividades;

import cuenta.Cuenta;
import interfaz.Utilitarios;
import main.Usuario;

public class FondoLiquidezEmpresarial extends Inversion {

	private static final double MONTO_MINIMO = 20000000;
	
	private static final String activoFondoLiquidez = "FLE"; // Activo llamado "FLE"
	
	private static final double tasaInteresFondoLiquidez = 0.08; // Activo con una tasa del 8% 

	public FondoLiquidezEmpresarial(Cuenta cuenta, double monto, int plazoDias, Usuario usuario) {

		super(cuenta, monto, plazoDias, usuario);

		if (monto < MONTO_MINIMO)
			throw new IllegalArgumentException("El fondo requiere un mínimo de " MONTO_MINIMO);
		
		this.tasa = tasaInteresFondoLiquidez; //Porcentaje del 8%
		
		this.activo = activoFondoLiquidez; // Activo FLE
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

		return
    		"\u25CB Inversion:\n" +
    		"   \u25A0 fecha: " + fecha + "\n" +
    		"     origen: " + usuario.getDni() + " (" + cuenta.getCvu() + ")\n" +
    		"     desc: Fondo Liquidez Empresarial" + "\n" +
    		"     monto: " + monto + "\n" +
    		"     plazo: " + plazoDias + "\n" +
    		"     " + estado;
	}

}
