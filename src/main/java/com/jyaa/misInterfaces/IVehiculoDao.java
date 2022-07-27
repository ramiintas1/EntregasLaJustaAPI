package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.VehiculoDto;
import com.jyaa.misclases.Vehiculo;

@Contract
public interface IVehiculoDao {

	public void save(Vehiculo vehiculo);
	public Vehiculo find(long l);
	public Vehiculo find(String l);
	public List<VehiculoDto> findall();
	public void remove(Vehiculo vehiculo);
	public void modify(Vehiculo vehiculoModificado);
}
