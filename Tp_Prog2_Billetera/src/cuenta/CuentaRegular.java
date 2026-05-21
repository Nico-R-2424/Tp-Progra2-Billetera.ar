package cuenta;

public class CuentaRegular extends Cuenta {

	private static final double LIMITE_SALARIAL = 5000000;

	public CuentaRegular(String cvu, String alias) {

		super(cvu, alias);

	}

	public double getLimiteSalarial() {
		return LIMITE_SALARIAL;
	}

	@Override
	public String toString() {

		return "Regular: " + alias + " (" + cvu + ")";
	}

}