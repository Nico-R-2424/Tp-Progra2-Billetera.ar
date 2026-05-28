package main;

import java.util.*;

import actividades.*;
import cuenta.*;
import interfaz.*;

public class Billetera implements IBilletera {

	private HashMap<String, Usuario> usuarios;
	private HashMap<String, Cuenta> cuentas;
	private HashMap<String, Empresa> empresas;

	private HashMap<Integer, Inversion> inversiones;

	private HashMap<String, String> aliasToCvu;

	private List<Actividad> historialGlobal;

	public Billetera() {

		usuarios = new HashMap<>();
		cuentas = new HashMap<>();
		empresas = new HashMap<>();

		inversiones = new HashMap<>();

		aliasToCvu = new HashMap<>();

		historialGlobal = new ArrayList<>();
	}

	@Override
	public void registrarEmpresa(String cuit, String nombreFantasia, String telefono, String email,
			String nombreContacto) {

		if (empresas.containsKey(cuit))
			throw new IllegalArgumentException("Empresa existente");

		Empresa e = new Empresa(cuit, nombreFantasia, telefono, email, nombreContacto);

		empresas.put(cuit, e);
	}

	@Override
	public void agregarPersonaAutorizada(String cuitEmpresa, String dniAutorizado) {

		Empresa empresa = empresas.get(cuitEmpresa);

		if (empresa == null)
			throw new IllegalArgumentException("Empresa inexistente");

		if (empresa.estaAutorizado(dniAutorizado))
			throw new IllegalArgumentException("Ya autorizado");

		if (usuarios.containsKey(dniAutorizado)) {

			Usuario u = usuarios.get(dniAutorizado);

			empresa.agregarAutorizado(u);

		} else {

			empresa.agregarAutorizado(dniAutorizado, "Usuario " + dniAutorizado, "-", "-");

			Usuario nuevo = empresa.getAutorizado(dniAutorizado);

			usuarios.put(dniAutorizado, nuevo);
		}
	}

	@Override
	public void registrarUsuario(String dni, String nombre, String telefono, String email) {

		if (usuarios.containsKey(dni))
			throw new IllegalArgumentException("Usuario existente");

		Usuario usuario = new Usuario(dni, nombre, telefono, email);

		usuarios.put(dni, usuario);
	}

	@Override
	public String crearCuentaRegular(String dniUsuario, String alias) {

		Usuario usuario = usuarios.get(dniUsuario);

		if (usuario == null)
			throw new IllegalArgumentException("Usuario inexistente");

		if (aliasToCvu.containsKey(alias))
			throw new IllegalArgumentException("Alias existente");

		String cvu = Utilitarios.generarSiguienteCvu();

		CuentaRegular cuenta = new CuentaRegular(cvu, alias);

		usuario.agregarCuenta(cuenta);

		cuentas.put(cvu, cuenta);

		aliasToCvu.put(alias, cvu);

		return cvu;
	}

	@Override
	public String crearCuentaPremium(String dniUsuario, String alias, double depositoInicial) {

		if (depositoInicial < 500000)
			throw new IllegalArgumentException("Monto insuficiente");

		Usuario usuario = usuarios.get(dniUsuario);

		if (usuario == null)
			throw new IllegalArgumentException("Usuario inexistente");

		if (aliasToCvu.containsKey(alias))
			throw new IllegalArgumentException("Alias existente");

		String cvu = Utilitarios.generarSiguienteCvu();

		CuentaPremium cuenta = new CuentaPremium(cvu, alias, depositoInicial);

		usuario.agregarCuenta(cuenta);

		cuentas.put(cvu, cuenta);

		aliasToCvu.put(alias, cvu);

		return cvu;
	}

	@Override
	public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {

		Usuario usuario = usuarios.get(dniUsuario);

		Empresa empresa = empresas.get(cuitEmpresa);

		if (usuario == null)
			throw new IllegalArgumentException("Usuario inexistente");

		if (empresa == null)
			throw new IllegalArgumentException("Empresa inexistente");

		if (!empresa.estaAutorizado(dniUsuario))
			throw new IllegalArgumentException("No autorizado");

		String cvu = Utilitarios.generarSiguienteCvu();

		CuentaCorporativa cuenta = new CuentaCorporativa(cvu, alias, empresa);

		usuario.agregarCuenta(cuenta);

		cuentas.put(cvu, cuenta);

		aliasToCvu.put(alias, cvu);

		return cvu;
	}

	@Override
	public List<String> obtenerCuentas(String dniUsuario) {

		Usuario usuario = usuarios.get(dniUsuario);

		if (usuario == null)
			throw new IllegalArgumentException();

		List<String> lista = new ArrayList<>();

		for (Cuenta cuenta : usuario.getCuentas()) {

			lista.add(cuenta.toString());
		}

		return lista;
	}

	@Override
	public double obtenerSaldoDisponible(String cvu) {

		Cuenta cuenta = cuentas.get(cvu);

		if (cuenta == null)
			throw new IllegalArgumentException();

		return cuenta.getSaldoDisponible();
	}

	@Override
	public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {

		Usuario Origen = consultarCuenta(cvuOrigen);
		
		Usuario Destino = consultarCuenta(cvuDestino);
		
		Cuenta origen = Origen.getCuenta(cvuOrigen);

		Cuenta destino = Destino.getCuenta(cvuDestino);

		if (origen == null || destino == null)

			throw new IllegalArgumentException();

		if (monto <= 0)
			throw new IllegalArgumentException();

		origen.debitar(monto);

		destino.acreditar(monto);

		Transferencia t = new Transferencia(origen, destino, monto, Origen, Destino);

		origen.agregarActividad(t);

		destino.agregarActividad(t);

		historialGlobal.add(t);
	}

	@Override
	public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {

		Usuario usuario = usuarios.get(dni);

		Cuenta cuenta = usuario.getCuenta(cvu);

		if (usuario == null || cuenta == null)
			throw new IllegalArgumentException();

		cuenta.debitar(monto);

		InversionRentaFija inv = new InversionRentaFija(cuenta, monto, plazoDias, usuario);

		inversiones.put(inv.getId(), inv);

		cuenta.agregarActividad(inv);

		historialGlobal.add(inv);

		usuario.sumarInversion(monto);

		return inv.getId();
	}

	@Override
	public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa,
			double tasa) {

		Usuario usuario = usuarios.get(dni);

		Cuenta cuenta = usuario.getCuenta(cvu);
		
		if (usuario == null || cuenta == null)
			throw new IllegalArgumentException();

		cuenta.debitar(monto);

		InversionDivisa inv = new InversionDivisa(cuenta, monto, plazoDias, divisa, tasa, usuario);

		inversiones.put(inv.getId(), inv);

		cuenta.agregarActividad(inv);

		historialGlobal.add(inv);

		usuario.sumarInversion(monto);

		return inv.getId();
	}

	@Override
	public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {

		Usuario usuario = usuarios.get(dni);
		
		Cuenta cuenta = usuario.getCuenta(cvu);

		if (usuario == null || cuenta == null||!(cuenta instanceof CuentaCorporativa))
			throw new IllegalArgumentException();

		FondoLiquidezEmpresarial inv = new FondoLiquidezEmpresarial(cuenta, monto, plazoDias, usuario);
		
		cuenta.debitar(monto);

		inversiones.put(inv.getId(), inv);

		cuenta.agregarActividad(inv);

		historialGlobal.add(inv);

		return inv.getId();
	}

	@Override
	public void precancelarInversion(String dni, String cvu, int idInversion) {

		Usuario usuario = usuarios.get(dni);
		
		Cuenta cuenta = usuario.getCuenta(cvu);
		
		if (usuario == null || cuenta == null)
			throw new IllegalArgumentException("Inversion inexistente");
		
		Actividad [] actividad = cuenta.getActividades();
		
		Inversion inv = null;
		
		for(Actividad act : actividad) {
			
			if(act != null && act.getId() == idInversion && act instanceof Inversion) {
				
				inv = (Inversion) act;
				
				break;
			}
		}

		if (inv == null || !inv.estaActiva())
			throw new IllegalArgumentException("Inversion inexistente");

		inv.precancelar();
		
	}

	@Override
	public String consultarCvu(String alias) {

		if (!aliasToCvu.containsKey(alias))
			throw new IllegalArgumentException();

		return aliasToCvu.get(alias);
	}

	@Override
	public List<String> consultarHistorialGlobal() {

		List<String> lista = new ArrayList<>();

		for (Actividad act : historialGlobal) {

			lista.add(act.toString());
		}

		return lista;
	}

	@Override
	public List<String> consultarHistorialCuenta(String cvu) {

		Cuenta cuenta = cuentas.get(cvu);
		
		if(cuenta == null)
			throw new IllegalArgumentException();

		List<String> lista = new ArrayList<>();

		for (Actividad act : cuenta.getActividades())

			lista.add(act.toString());

		return lista;
	}

	@Override
	public List<String> consultarHistorialUsuario(String dniUsuario) {

		Usuario usuario = usuarios.get(dniUsuario);
		
		if(usuario == null)
			throw new IllegalArgumentException();

		List<String> lista = new ArrayList<>();

		for (Cuenta cuenta : usuario.getCuentas())

			for (Actividad act : cuenta.getActividades())

				lista.add(act.toString());

		return lista;
	}

	@Override
	public double obtenerTotalInvertido(String dniUsuario) {

		Usuario usuario = usuarios.get(dniUsuario);
		
		if(usuario == null)
			throw new IllegalArgumentException();
		
		double totalInvertido = 0.0;
		
		for(Cuenta cuenta : usuario.getCuentas()) {
			
			Actividad [] cuentaActividad = cuenta.getActividades();
			
			for(Actividad act : cuentaActividad) {
				
				if(act instanceof Inversion) {
					
					Inversion inv = (Inversion) act;
					
					if(!inv.estaActiva()) {
						
						return totalInvertido;
				}
			}
		}
			
			totalInvertido += usuario.getTotalInvertido();
	}
					
		return totalInvertido;
	}

	@Override
	public List<String> cuentasConMayorVolumen(int cantidadTop) {

		if(cantidadTop <= 0)
			throw new IllegalArgumentException();
		
		List<Cuenta> lista = new ArrayList<>(cuentas.values());

		lista.sort((a, b) -> b.getVolumenTransacciones() - a.getVolumenTransacciones());

		List<String> resultado = new ArrayList<>();

		for (int i = 0; i < cantidadTop && i < lista.size(); i++) {

			resultado.add(lista.get(i).toString());
		}

		return resultado;
	}
	
	public Usuario consultarCuenta(String cvuOrigen) {
		
		for(Usuario usuario : usuarios.values()) {
			
			if(usuario.tieneCuenta(cvuOrigen)) {
				
				return usuario;
			}
		}
		
		return null;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("=== BILLETERA ===\n");

		sb.append("Usuarios: ").append(usuarios.size()).append("\n");

		sb.append("Cuentas: ").append(cuentas.size()).append("\n");

		sb.append("Empresas: ").append(empresas.size()).append("\n");

		sb.append("Actividades: ").append(historialGlobal.size()).append("\n");

		return sb.toString();
	}
}