package br.com.hs.nfe.base;

public class ThreadBase extends Thread {
	private boolean go = true;
	public boolean isRunning()
	{
		return this.go;
	}
	public void stopThread()
	{
		this.go = false;
	}
}
