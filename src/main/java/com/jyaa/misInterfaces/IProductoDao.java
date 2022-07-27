package com.jyaa.misInterfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.jyaa.misDTO.ProductoDto;
import com.jyaa.misclases.Producto;

@Contract
public interface IProductoDao {

	public void save(Producto producto);
	public Producto find(long l);
	public ProductoDto findDto(long l);
	public List<ProductoDto> findall();
	public void remove(Producto producto);
	public void modify(Producto productoModificado);

	
}
