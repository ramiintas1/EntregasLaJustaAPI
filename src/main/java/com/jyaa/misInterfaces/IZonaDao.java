package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misclases.Usuario;
import com.jyaa.misclases.Zona;

@Contract
public interface IZonaDao {
	
	public void save(Zona zona);
	public Zona find(long l);
	public List<ZonaDto> findall();
	public Zona find(String st);
	public List<Usuario> findUs(String st);
	public Zona find(float lat,float lon);
	public void remove(Zona zona);
	public void modify(Zona zonaModificado);

}
