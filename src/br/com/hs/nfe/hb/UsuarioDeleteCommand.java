package br.com.hs.nfe.hb;

import br.com.hs.nfe.entity.Usuario;

public class UsuarioDeleteCommand extends GenericDeleteCommand<Usuario> {
	public UsuarioDeleteCommand(Usuario u)
	{
		super(u);
	}
}
