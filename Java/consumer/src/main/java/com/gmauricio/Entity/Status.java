package com.gmauricio.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Status")
public class Status implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final Long ATIVO = 1L;
	public static final Long INATIVO = 2L;
	
	public static final List<String> SUBSCRIPTION_STATUS_ATIVO = new ArrayList<String>();
	static {
		SUBSCRIPTION_STATUS_ATIVO.add("SUBSCRIPTION_PURCHASED");
		SUBSCRIPTION_STATUS_ATIVO.add("SUBSCRIPTION_RESTARTED");
	}
	
	private Long id;
	private String name;
	
	public Status(){
		super();
	}
	
	public Status(Long id) {
		super();
		this.id = id;
	}
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
