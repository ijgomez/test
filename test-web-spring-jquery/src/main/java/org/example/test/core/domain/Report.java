package org.example.test.core.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Report implements Serializable {

	private static final long serialVersionUID = 8009230398613866922L;
	
	private Long id;

	private String name;
	
	private String description;
	
	private String engine;
	
	private String format;
	
	private Boolean programable;
	
	private Date lastExecute;
	
	public Report() {
		super();
	}
	
	public Report(Long id, String name, String description, String engine, String format, Boolean programable, Date lastExecute) {
		this(name, description, engine, format, programable, lastExecute);
		this.id = id;
	}

	public Report(String name, String description, String engine, String format, Boolean programable, Date lastExecute) {
		this();
		this.name = name;
		this.description = description;
		this.engine = engine;
		this.format = format;
		this.programable = programable;
		this.lastExecute = lastExecute;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Boolean getProgramable() {
		return programable;
	}

	public void setProgramable(Boolean programable) {
		this.programable = programable;
	}

	public Date getLastExecute() {
		return lastExecute;
	}

	public void setLastExecute(Date lastExecute) {
		this.lastExecute = lastExecute;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
}
