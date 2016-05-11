package br.com.hs.nfe.pe.file;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;

import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.vo.NFeVO;

public class FileSender {
	private static final Logger logger = Logger.getLogger("FileSender");
	private FileReceiver srv = null;
	private String ip = "";
	private String port = "";
	public FileSender(Estabelecimento config)
	{
		try
		{
			String[] temp = new String[2];
			if(config.getUrlRMI().contains(":"))
			{
				temp = config.getUrlRMI().split(":");
				ip = temp[0];
				port = temp[1];
			}
			else
			{
				ip = config.getUrlRMI();
				port = "1099";
			}
			Registry registry = LocateRegistry.getRegistry(ip,Integer.parseInt(port));
			srv = (FileReceiver)registry.lookup("FileReceiver");
		}
		catch(RemoteException re)
        {
            logger.error(re);
        }
		catch(NotBoundException nbe)
        {
        	logger.error(nbe);
        }
	}
	public Character sendFileFSDA(NFeVO enviNFeXML,Nfe nfe) throws Exception
	{
		Character ret = null;
		try
		{
			ret = srv.enviarZipFSDA(enviNFeXML.getZip(),nfe.getChecksum());
		}
		catch(NullPointerException npe)
		{
			Registry registry = LocateRegistry.getRegistry(ip,Integer.parseInt(port));
			srv = (FileReceiver)registry.lookup("FileReceiver");
			ret = '0';
		}
		catch(java.rmi.NoSuchObjectException e)
		{
			Registry registry = LocateRegistry.getRegistry(ip,Integer.parseInt(port));
			srv = (FileReceiver)registry.lookup("FileReceiver");
			ret = '0';
		}
		return ret;
	}
	public Character sendFile(NFeVO enviNFeXML,Nfe nfe) throws Exception
	{
		Character ret = null;
		try
		{
			ret = srv.enviarZip(enviNFeXML.getZip(),nfe.getChecksum());
		}
		catch(NullPointerException npe)
		{
			Registry registry = LocateRegistry.getRegistry(ip,Integer.parseInt(port));
			srv = (FileReceiver)registry.lookup("FileReceiver");
			ret = '0';
		}
		catch(java.rmi.NoSuchObjectException e)
		{
			Registry registry = LocateRegistry.getRegistry(ip,Integer.parseInt(port));
			srv = (FileReceiver)registry.lookup("FileReceiver");
			ret = '0';
		}
		return ret;
	}
}
