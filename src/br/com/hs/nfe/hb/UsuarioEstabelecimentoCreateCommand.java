package br.com.hs.nfe.hb;

import br.com.hs.nfe.entity.UsuarioEstabelecimento;

public class UsuarioEstabelecimentoCreateCommand extends
		GenericCreateCommand<UsuarioEstabelecimento> {
	public UsuarioEstabelecimentoCreateCommand(UsuarioEstabelecimento ue)
	{
		super( ue );
	}
}
