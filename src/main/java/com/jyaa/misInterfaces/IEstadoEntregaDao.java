package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.EstadoEntregaDto;
import com.jyaa.misclases.EstadoEntrega;

@Contract
public interface IEstadoEntregaDao {
	
	public void save(EstadoEntrega estado);
	public EstadoEntrega find(long l);
	public EstadoEntrega find(String st);
	public List<EstadoEntregaDto> findall();
	public void remove(EstadoEntrega estado);
	public void modify(EstadoEntrega estadoModificado);

}
