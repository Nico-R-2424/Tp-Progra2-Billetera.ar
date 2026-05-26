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
	    public void acreditar(
	            double monto){

	        if(
	        saldoDisponible
	        +
	        monto
	        >
	        LIMITE_SALARIAL)

	            throw new IllegalStateException(
	                    "Limite excedido");

	        saldoDisponible += monto;
	    }


	@Override
	public String toString() {

		return "Regular: " + alias + " (" + cvu + ")";
	}

}