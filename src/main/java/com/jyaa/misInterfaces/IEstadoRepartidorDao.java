package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.EstadoRepartidorDto;
import com.jyaa.misclases.EstadoRepartidor;

@Contract
public interface IEstadoRepartidorDao {

	public void save(EstadoRepartidor estado);
	public EstadoRepartidor find(long l);
	public EstadoRepartidor find(String l);
	public List<EstadoRepartidorDto> findall();
	public void remove(EstadoRepartidor estado);
	public void modify(EstadoRepartidor estadoModificado) ;
	
}
