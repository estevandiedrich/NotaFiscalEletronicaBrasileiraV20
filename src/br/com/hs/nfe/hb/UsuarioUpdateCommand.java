package br.com.hs.nfe.hb;

import br.com.hs.nfe.entity.Usuario;

public class UsuarioUpdateCommand extends GenericUpdateCommand<Usuario> {
	public UsuarioUpdateCommand(Usuario u)
	{
		super(u);
	}
}
