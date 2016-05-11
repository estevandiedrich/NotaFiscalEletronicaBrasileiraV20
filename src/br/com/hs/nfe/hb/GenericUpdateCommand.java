package br.com.hs.nfe.hb;

public abstract class GenericUpdateCommand<E> extends Command {
	
	E obj;
	
	protected void clearCache() {
	}
	
	public void beforeUpdate(E obj) {
	}
	
	public GenericUpdateCommand(E obj) {
		this.obj = obj;
	}
	
	protected void doJob() {
		beforeUpdate(obj);
		merge(obj);
		clearCache();
	}
	
}
