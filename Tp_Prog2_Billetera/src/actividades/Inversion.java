package actividades;

import java.time.LocalDate;

import cuenta.Cuenta;
import interfaz.Utilitarios;

public abstract class Inversion extends Actividad {

    protected Cuenta cuenta;

    protected double montoInvertido;

    protected int plazoDias;

    protected LocalDate fechaInicio;

    protected boolean activa;

    public Inversion(Cuenta cuenta, double monto, int plazoDias) {

        super(monto);

        this.cuenta = cuenta;

        this.montoInvertido = monto;

        this.plazoDias = plazoDias;

        this.fechaInicio = Utilitarios.hoy();

        this.activa = true;
    }

    public double getMontoInvertido() {
        return montoInvertido;
    }

    public boolean estaActiva() {
        return activa;
    }

    public void cancelar() {
        activa = false;
    }

    public abstract void precancelar();

	public abstract double calcularResultado();
	
}