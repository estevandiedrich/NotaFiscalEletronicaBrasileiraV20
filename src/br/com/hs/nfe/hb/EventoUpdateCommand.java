package br.com.hs.nfe.hb;

import br.com.hs.nfe.entity.Evento;

public class EventoUpdateCommand extends GenericUpdateCommand<Evento> {
	public EventoUpdateCommand(Evento e)
	{
		super(e);
	}
}
