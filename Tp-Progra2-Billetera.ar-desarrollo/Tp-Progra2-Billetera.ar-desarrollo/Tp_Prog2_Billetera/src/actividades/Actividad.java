package actividades;

import java.time.LocalDate;

public abstract class Actividad {

	protected static int contadorId = 1;

	protected int id;
	protected LocalDate fecha;
	protected double monto;
	protected boolean aprobada;

	public Actividad(double monto) {

		this.id = contadorId++;
		this.fecha = LocalDate.now(); // después podemos cambiar a Utilitarios.hoy()

		this.monto = monto;

		this.aprobada = true;
	}

	public int getId() {
		return id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public double getMonto() {
		return monto;
	}

	public boolean fueAprobada() {
		return aprobada;
	}

	public void rechazar() {
		this.aprobada = false;
	}

	@Override
	public abstract String toString();

}