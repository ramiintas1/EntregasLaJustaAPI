package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.EntregaDto;
import com.jyaa.misclases.Entrega;

@Contract
public interface IEntregaDao {

	public void save(Entrega entrega);
	public Entrega find(long l);
	public EntregaDto findDto(long l);
	public List<Entrega> findall();
	public List<EntregaDto>findallEntregas();
	public void remove(Entrega entrega);
	public void modify(Entrega entregaModificado);
	
}
