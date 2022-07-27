package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.PedidoDto;
import com.jyaa.misclases.Pedido;

@Contract
public interface IPedidoDao {

	public void save(Pedido pedido);
	public Pedido find(long l);
	public PedidoDto findDto(long l);
	public boolean validar(long l);
	public List<PedidoDto> findall();
	public void remove(Pedido pedido);
	public void modify(Pedido pedidoModificado);
	
}
