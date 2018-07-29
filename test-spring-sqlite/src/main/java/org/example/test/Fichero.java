package org.example.test;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Fichero {
 
	private Long id;
	
	private String fileName;
	
	private String path;
	
	private String extension;
	
	private Date fxCreationFile;
	
	private Byte[] checksum;
	
	private String mediaInfo;
	
	private Long length;
	
	public Fichero(){
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the fxCreationFile
	 */
	public Date getFxCreationFile() {
		return fxCreationFile;
	}

	/**
	 * @param fxCreationFile the fxCreationFile to set
	 */
	public void setFxCreationFile(Date fxCreationFile) {
		this.fxCreationFile = fxCreationFile;
	}

	/**
	 * @return the checksum
	 */
	public Byte[] getChecksum() {
		return checksum;
	}

	/**
	 * @param checksum the checksum to set
	 */
	public void setChecksum(Byte[] checksum) {
		this.checksum = checksum;
	}

	/**
	 * @return the mediaInfo
	 */
	public String getMediaInfo() {
		return mediaInfo;
	}

	/**
	 * @param mediaInfo the mediaInfo to set
	 */
	public void setMediaInfo(String mediaInfo) {
		this.mediaInfo = mediaInfo;
	}

	/**
	 * @return the length
	 */
	public Long getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Long length) {
		this.length = length;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
