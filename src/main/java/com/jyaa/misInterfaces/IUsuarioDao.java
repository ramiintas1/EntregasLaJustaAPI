package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.LoginDto;
import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misclases.Entrega;
import com.jyaa.misclases.Usuario;

@Contract
public interface IUsuarioDao {

	public void save(Usuario usuario);
	public UsuarioDto find(long l);
	public Usuario findUs(long l);
	public List<UsuarioDto> findall();
	public List<UsuarioDto> findallAdm();
	public List<UsuarioDto> findallRep();
	public Usuario findDNI(String st1);
	public Usuario findEmailCont(LoginDto login);
	public UsuarioDto find(String st1, String st2);
	public List<Entrega> findEntregas(String st1, String st2);
	public boolean validar(LoginDto login);
	public boolean validarUs(UsuarioDto us);
	public void remove(UsuarioDto u);
	public void modify(UsuarioDto usModificado);
	
}
