package com.teste.service;

import javax.inject.Inject;

import org.primefaces.model.menu.MenuModel;

import com.teste.model.Usuario;
import com.teste.util.CriptografiaUtil;

import java.io.Serializable;

import lombok.*;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
public class AutenticacaoService implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private MenuService menuService;
	
	
	

	public Usuario autenticar(String idCriptografado) {
		
		try {
			Long usuarioId = Long.parseLong(CriptografiaUtil.descriptografar(idCriptografado));
			
			return this.buscarUsuario(usuarioId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		Usuario usuario = null;
		return usuario;
	}

	private Usuario buscarUsuario (Long id) {
		if(id != null) {
			Usuario usuario = usuarioService.buscarPorId(id);		
			
			if(usuario != null ) {			
				return usuario;	
			}
		}		
		return null;
			
	}
	   public MenuModel criarMenu(Usuario usuario) {
	       return menuService.montarMenu(usuario);
		}


}