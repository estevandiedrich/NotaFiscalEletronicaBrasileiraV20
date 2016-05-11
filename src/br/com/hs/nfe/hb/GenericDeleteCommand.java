package br.com.hs.nfe.hb;

public abstract class GenericDeleteCommand<E> extends Command {
	
	protected E obj;
	
	public void clearCache() {
	}
	
	public GenericDeleteCommand (E obj) {
		this.obj = obj;
	}
	
	protected void doJob() {
		delete(obj);
		clearCache();
	}
	
}
