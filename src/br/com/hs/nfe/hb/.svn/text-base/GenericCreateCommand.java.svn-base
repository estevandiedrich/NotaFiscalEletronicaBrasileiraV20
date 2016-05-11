package br.com.hs.nfe.hb;

import org.apache.log4j.Logger;

public abstract class GenericCreateCommand<E> extends Command {
	private static final Logger logger = Logger.getLogger("GenericCreateCommand");
	E obj;

	protected void beforeCreate(E obj) {
		logger.info("beforeCreate");
	}

	protected void afterCreate(E obj) {
		logger.info("afterCreate");
	}

	public GenericCreateCommand(E obj) {
		this.obj = obj;
	}

	protected void doJob() {
		beforeCreate(obj);
		save(obj);
		afterCreate(obj);
	}
	
}
