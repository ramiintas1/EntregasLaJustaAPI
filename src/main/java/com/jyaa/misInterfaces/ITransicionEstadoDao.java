package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.TransicionEstadoDto;
import com.jyaa.misclases.TransicionEstado;

@Contract
public interface ITransicionEstadoDao {

	public void save(TransicionEstado transicion);
	public TransicionEstado find(long l);
	public List<TransicionEstado> findEntr(long l);
	public List<TransicionEstadoDto> findall();
	public void remove(TransicionEstado estado);
	public void modify(TransicionEstado estadoModificado);
	
}
