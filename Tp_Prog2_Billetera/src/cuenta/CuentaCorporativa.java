package cuenta;

import main.Empresa;

public class CuentaCorporativa extends Cuenta {

	private Empresa empresa;

	public CuentaCorporativa(String cvu, String alias, Empresa empresa) {

		super(cvu, alias);

		if (empresa == null)
			throw new IllegalArgumentException("Empresa invalida");

		this.empresa = empresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	@Override
	public String toString() {

		return "Corporativa: " + alias + " (" + cvu + ")";
	}

}