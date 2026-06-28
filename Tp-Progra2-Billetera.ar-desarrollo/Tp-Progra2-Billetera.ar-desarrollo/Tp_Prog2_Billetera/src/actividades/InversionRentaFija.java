package actividades;

import cuenta.Cuenta;
import interfaz.Utilitarios;
import main.Usuario;

public class InversionRentaFija extends Inversion {

	private double tasaInteres;

	public InversionRentaFija(Cuenta cuenta, double monto, int plazoDias, Usuario usuario) {

		super(cuenta, monto, plazoDias, usuario);

		this.tasaInteres = 0.20; // tasa fija ejemplo
	}

	public int getId() {
		return id;
	}

	public double getTasaInteres() {
		return tasaInteres;
	}
	
	@Override
	public double calcularResultado() {
		
		if (!activa)
			return 0.0;

		long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, Utilitarios.hoy());

		double intereses = montoInvertido * (tasaInteres / 365.0) * dias;

		return montoInvertido + intereses;
	}

	@Override
	public void precancelar() {
		
		if(activa) {
			
			long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, Utilitarios.hoy());
			
			double interes = montoInvertido * (tasaInteres / 365.0) * dias;
			
			interes /= 2.0 ;
			
			double totalAcreditar = montoInvertido + interes;
			
			cuenta.acreditar(totalAcreditar);
			
			activa = false;
			
			montoInvertido = 0.0;
		}

		
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
    		"     desc: Renta Fija" + "\n" +
    		"     monto: " + monto + "\n" +
    		"     plazo: " + plazoDias + "\n" +
    		"     " + estado;
	}

}