package com.jyaa.misDAO;

public class IFactoriaDao {
	
	public static UsuarioDao getUsuarioDao() {
		return new UsuarioDao();
	}
	
	public static VehiculoDao getVehiculoDao() {
		return new VehiculoDao();
	}
	
	public static ZonaDao getZoneDao() {
		return new ZonaDao();
	}
	
	public static TransicionEstadoDao getTransicionEstadoDao() {
		return new TransicionEstadoDao();
	}
	
	public static RolDao getRolDao() {
		return new RolDao();
	}
	
	public static ProductoDao getProductoDao() {
		return new ProductoDao();
	}
	
	public static PedidoDao getPedidoDao() {
		return new PedidoDao();
	}
	
	public static EstadoRepartidorDao getEstadoRepartidorDao() {
		return new EstadoRepartidorDao();
	}
	
	public static EstadoEntregaDao getEstadoEntregaDao() {
		return new EstadoEntregaDao();
	}
	
	public static EntregaDao getEntregaDao() {
		return new EntregaDao();
	}
}
