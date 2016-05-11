package br.com.hs.nfe.hb;

import br.com.hs.nfe.entity.Sistema;

public class SistemaUpdateCommand extends GenericUpdateCommand<Sistema> {
	public SistemaUpdateCommand(Sistema s)
	{
		super(s);
	}
}
