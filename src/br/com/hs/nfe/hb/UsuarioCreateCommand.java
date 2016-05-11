package br.com.hs.nfe.hb;

import br.com.hs.nfe.entity.Usuario;

public class UsuarioCreateCommand extends GenericCreateCommand<Usuario> {
	public UsuarioCreateCommand(Usuario u)
	{
		super(u);
	}
}
