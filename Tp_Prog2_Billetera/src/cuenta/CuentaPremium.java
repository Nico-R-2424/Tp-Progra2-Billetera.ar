package cuenta;

public class CuentaPremium extends Cuenta {

	private static final double MONTO_MINIMO = 500000;

	public CuentaPremium(String cvu, String alias, double depositoInicial) {

		super(cvu, alias);

		if (depositoInicial < MONTO_MINIMO)
			throw new IllegalArgumentException("Cuenta Premium requiere minimo $500000");

		this.saldoDisponible = depositoInicial;
	}

	public double getMontoMinimo() {
		return MONTO_MINIMO;
	}

	@Override
	public String toString() {

		return "Premium: " + alias + " (" + cvu + ")";
	}

}