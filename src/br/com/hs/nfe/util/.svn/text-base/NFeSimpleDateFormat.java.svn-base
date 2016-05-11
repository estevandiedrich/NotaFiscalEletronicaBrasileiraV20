package br.com.hs.nfe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NFeSimpleDateFormat {
	private static NFeSimpleDateFormat me = null;  
	public SimpleDateFormat sdf = null;
	private NFeSimpleDateFormat()
	{
		this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	}
	public synchronized static NFeSimpleDateFormat getInstance()
	{
		if(me == null)
		{
			me = new NFeSimpleDateFormat();
		}
		return me;
	}
	public Date parse(String date)
	{
		try {
			return this.sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}
	public String parse(Date date)
	{
		return this.sdf.format(date);
	}
}
