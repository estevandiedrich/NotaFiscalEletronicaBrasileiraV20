package br.com.hs.nfe.util;

import java.util.Date;

import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.conf.PropriedadesSistema;

public class InsereXjustDhCont {
	public static synchronized void insereXjustDhCont(Document doc)
	{
		Element ide = null;
		NodeList ideNL = doc.getElementsByTagName("ide");
		if(ideNL.getLength()>0)
		{
			ide = (ElementImpl)ideNL.item(0);
		}
		NodeList dhContNL = ide.getElementsByTagName("dhCont");
		NodeList xJustNL = ide.getElementsByTagName("xJust");	
		if(dhContNL.getLength()<=0)
		{
			Element dhCont = doc.createElement("dhCont");
			dhCont.setTextContent(NFeSimpleDateFormat.getInstance().parse(new Date()));
			ide.appendChild(dhCont);
		}
		if(xJustNL.getLength()<=0)
		{
			Element xJust = doc.createElement("xJust");
			xJust.setTextContent(PropriedadesSistema.getInstance().getSistema().getXjustDpec());
			ide.appendChild(xJust);
		}
	}
}
