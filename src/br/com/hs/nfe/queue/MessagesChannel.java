package br.com.hs.nfe.queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.hs.nfe.vo.Message;

public class MessagesChannel {
	private static final Logger logger = Logger.getLogger("MessagesChannel");
	private static MessagesChannel me = null;
	private List<Message> list = Collections.synchronizedList(new LinkedList<Message>());
	public synchronized void add(Message e)
	{
		logger.info("Inserindo elemento.");
		this.list.add(e);
	}
	public synchronized Message remove()
	{
		logger.info("Removendo elemento.");
		return this.list.remove(0);
	}
	public synchronized Message[] removeAll()
	{
		Message[] array = new Message[this.list.size()];
		array = this.list.toArray(array);
		this.list.clear();
		return array;
	}
	public synchronized Message getMessage(String to)
	{
		for(Message message:this.list)
		{
			if(message.getTo().equalsIgnoreCase(to))
			{
				if(this.list.remove(message))
				{
					return message;
				}
			}
		}
		return null;
	}
	public synchronized static MessagesChannel getInstance()
	{
		if(me == null)
		{
			logger.info("Inicializando a lista");
			me = new MessagesChannel();
		}
		return me;
	}
}
