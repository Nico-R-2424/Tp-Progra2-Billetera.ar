package actividades;

import cuenta.Cuenta;
import main.Usuario;

public class Transferencia extends Actividad {

	private Cuenta origen;
	private Cuenta destino;
	private Usuario Origen;
	private Usuario Destino;

	public Transferencia(Cuenta origen, Cuenta destino, double monto, Usuario Origen, Usuario Destino) {

		super(monto);

		this.origen = origen;
		this.destino = destino;
		this.Origen = Origen;
		this.Destino = Destino;
	}

	public Cuenta getOrigen() {
		return origen;
	}

	public Cuenta getDestino() {
		return destino;
	}
	
	public Usuario getUsuarioDestino() {
		return Destino;
	}
	
	public Usuario getUsuarioOrigen() {
		return Origen;
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

		return "Transferencia:" + "\nfecha: " + fecha + "\norigen: " + Origen.getDni() + "(" + origen.getCvu() + ")" + "\ndestino: "  + "(" + destino.getCvu() + ")" + "\nmonto: " + monto + "\n" + estado;
	}

}