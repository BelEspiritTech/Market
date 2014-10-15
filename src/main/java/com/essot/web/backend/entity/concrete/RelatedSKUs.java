package com.essot.web.backend.entity.concrete;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.essot.web.backend.entity.IEssotEntity;

@Entity
@Table(name = "ESSOT_RELATED_SKUS")
public class RelatedSKUs implements IEssotEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SKU")
	private String 	sku;
	
	@Id
	@Column(name = "RELATED_SKUS")
	private String 	relatedsku;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Column(name="UPDATE_DATE")
	private Date updateDate;
	
	@Column(name="ACTIVE_FLAG")
	private String activeFlag;
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getRelatedsku() {
		return relatedsku;
	}

	public void setRelatedsku(String relatedsku) {
		this.relatedsku = relatedsku;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
}
