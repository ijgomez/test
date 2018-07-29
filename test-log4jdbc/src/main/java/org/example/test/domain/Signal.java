package org.example.test.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Signal implements Serializable {

	private static final long serialVersionUID = -8349178137788031624L;

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false)
	private String value;

	@Column(nullable = false)
	private Timestamp creationTime;
	
	@Column(nullable = false)
	private Boolean dst;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SignalQuality signalQuality;
	
	private Integer iecNumber;
	
	@Column(nullable = false)
	private Integer elementFk;
	
	@Column(nullable = false)
	private Integer parentFk;
	
	@Column(nullable = false)
	private Integer signalInfoFk;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	
	public Boolean getDst() {
		return dst;
	}
	
	public void setDst(Boolean dst) {
		this.dst = dst;
	}
	
	public SignalQuality getSignalQuality() {
		return signalQuality;
	}

	public void setSignalQuality(SignalQuality signalQuality) {
		this.signalQuality = signalQuality;
	}

	public Integer getSignalInfoFk() {
		return signalInfoFk;
	}

	public void setSignalInfoFk(Integer signalInfoFk) {
		this.signalInfoFk = signalInfoFk;
	}
	
	public Integer getIecNumber() {
		return iecNumber;
	}

	public void setIecNumber(Integer iecNumber) {
		this.iecNumber = iecNumber;
	}

	public Integer getElementFk() {
		return elementFk;
	}

	public void setElementFk(Integer elementFk) {
		this.elementFk = elementFk;
	}

	public Integer getParentFk() {
		return parentFk;
	}

	public void setParentFk(Integer parentFk) {
		this.parentFk = parentFk;
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
