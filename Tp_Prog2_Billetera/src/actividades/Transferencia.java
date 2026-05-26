package actividades;

import cuenta.Cuenta;

public class Transferencia extends Actividad {

	private Cuenta origen;
	private Cuenta destino;

	public Transferencia(Cuenta origen, Cuenta destino, double monto) {

		super(monto);

		this.origen = origen;
		this.destino = destino;
	}

	public Cuenta getOrigen() {
		return origen;
	}

	public Cuenta getDestino() {
		return destino;
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

		return "\nmonto: " + monto + "\n" + estado;
	}

}