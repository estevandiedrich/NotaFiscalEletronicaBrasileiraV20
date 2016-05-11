package br.com.hs.nfe.queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.hs.nfe.vo.NFeVO;

public class XML2Txt {
	private XML2Txt()
	{
		
	}
	private static final Logger logger = Logger.getLogger("XML2Txt");
	private static XML2Txt me = null;
	private List<NFeVO> list = Collections.synchronizedList(new LinkedList<NFeVO>());
	public synchronized void add(NFeVO e)
	{
		logger.info("Inserindo elemento.");
		this.list.add(e);
	}
	public synchronized NFeVO remove()
	{
		logger.info("Removendo elemento.");
		return this.list.remove(0);
	}
	public synchronized NFeVO[] removeAll()
	{
		NFeVO[] array = new NFeVO[this.list.size()];
		array = this.list.toArray(array);
		this.list.clear();
		return array;
	}
	public synchronized NFeVO getNFeVOPorChaveAcesso(String chaveAcesso)
	{
		for(NFeVO nfeVO:this.list)
		{
			if(nfeVO.getChaveAcesso().equalsIgnoreCase(chaveAcesso))
			{
				if(this.list.remove(nfeVO))
				{
					return nfeVO;
				}
			}
		}
		return null;
	}
	public synchronized static XML2Txt getInstance()
	{
		if(me == null)
		{
			logger.info("Inicializando a lista");
			me = new XML2Txt();
		}
		return me;
	}
}
