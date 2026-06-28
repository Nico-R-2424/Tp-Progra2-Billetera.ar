package actividades;

import cuenta.Cuenta;
import interfaz.Utilitarios;
import main.Usuario;

public class FondoLiquidezEmpresarial extends Inversion {

	private static final double MONTO_MINIMO = 20000000;
	
	private String activoFondoLiquidez;
	
	private double tasaInteresFondoLiquidez;

	public FondoLiquidezEmpresarial(Cuenta cuenta, double monto, int plazoDias, Usuario usuario) {

		super(cuenta, monto, plazoDias, usuario);

		if (monto < MONTO_MINIMO)
			throw new IllegalArgumentException("El fondo requiere minimo 20 millones");
		
		this.tasaInteresFondoLiquidez = 0.08; //Porcentaje del 8%
		
		this.activoFondoLiquidez = "FLE"; 
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
		
		double cotizacionFLE = Utilitarios.consultarCotizacion(activoFondoLiquidez);
		
		double intereses = montoInvertido * (tasaInteresFondoLiquidez / 365.0) * dias * cotizacionFLE;
		
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