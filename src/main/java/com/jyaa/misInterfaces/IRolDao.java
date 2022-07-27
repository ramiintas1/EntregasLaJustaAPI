package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.RolDto;
import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misclases.Rol;

@Contract
public interface IRolDao {
	
	public void save(Rol rol);
	public Rol find(long l);
	public Rol find(String st) ;
	public List<UsuarioDto> findUs(String st);
	public List<UsuarioDto> findAdmin(String st);
	public List<RolDto> findall();
	public void remove(Rol r);
	public void modify(Rol rModificado);

}
