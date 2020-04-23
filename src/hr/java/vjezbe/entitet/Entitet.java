package hr.java.vjezbe.entitet;

import java.io.Serializable;

public abstract class Entitet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8113324545658305902L;
	private long id;

	public Entitet(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
