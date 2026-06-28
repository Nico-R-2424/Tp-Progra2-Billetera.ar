package actividades;

import cuenta.Cuenta;
import main.Usuario;

public class Transferencia extends Actividad {

	private Cuenta origen;
	private Cuenta destino;
	private Usuario origenUsuario;
	private Usuario destinoUsuario;


	public Transferencia(Cuenta origen, Cuenta destino, double monto, Usuario Origen, Usuario Destino) {

		super(monto);

		this.origen = origen;
		this.destino = destino;
		this.origenUsuario = Origen;
		this.destinoUsuario = Destino;
	}

	public Cuenta getOrigen() {
		return origen;
	}

	public Cuenta getDestino() {
		return destino;
	}
	
	public Usuario getUsuarioDestino() {
		return destinoUsuario;
	}
	
	public Usuario getUsuarioOrigen() {
		return origenUsuario;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {

		String estado;

		if (aprobada)
			estado = "Aprobada";
		else
			estado = "Rechazada";

		return
			"\u25CB Transferencia:\n" +
    		"   \u25A0 fecha: " + fecha + "\n" +
    		"     origen: " + origenUsuario.getDni() + " (" + origen.getCvu() + ")\n" +
    		"     destino: " + destinoUsuario.getDni() + " (" + destino.getCvu() + ")\n" +
    		"     monto: " + monto + "\n" +
    		"     " + estado;
	}

}