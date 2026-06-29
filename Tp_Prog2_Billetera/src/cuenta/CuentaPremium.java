package cuenta;

public class CuentaPremium extends Cuenta {

	public static final double MONTO_MINIMO = 500000;

	public CuentaPremium(String cvu, String alias, double depositoInicial) {

		super(cvu, alias);

		this.saldoDisponible = depositoInicial;
	}

	public double getMontoMinimo() {
		return MONTO_MINIMO;
	}

	@Override
	public String toString() {

		return "\u25CB  Premium: " + alias + " (" + cvu + ")";
	}

}