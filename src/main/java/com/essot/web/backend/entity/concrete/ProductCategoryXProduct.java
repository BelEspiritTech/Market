package com.essot.web.backend.entity.concrete;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.essot.web.backend.entity.IEssotEntity;

@Entity
@Table(name = "ESSOT_PRDCT_CAT_X_PRDCT")
public class ProductCategoryXProduct implements IEssotEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name= "PRODUCT_CATEGORY_KEY")
	private Integer productCategoryKey;
	
	@Id
	@Column(name= "SKU_NAME")
	private String 	skuName;
	
	@Column(name= "SEQUENCE_ID")
	private Integer	sequenceId;
	
	@Column(name= "START_DATE")
	private Date	startDate;
	
	@Column(name= "END_DATE")
	private Date 	endDate;
	
	@Column(name= "UPDATE_DATE")
	private Date	updateDate;
	
	@Column(name= "UPDATED_BY")
	private Integer updatedBy;
	
	@Column(name= "CREATION_DATE")
	private Date	creationDate;
	
	@Column(name= "CREATED_BY")
	private Integer	createdBy;
	
	@Column(name= "ACTIVE_FLAG")
	private String 	activeFlag;
	
	@Column(name= "STATUS")
	private Integer	status;


	public Integer getProductCategoryKey() {
		return productCategoryKey;
	}


	public void setProductCategoryKey(Integer productCategoryKey) {
		this.productCategoryKey = productCategoryKey;
	}


	public String getSkuName() {
		return skuName;
	}


	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}


	public Integer getSequenceId() {
		return sequenceId;
	}


	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public Integer getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Integer getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	public String getActiveFlag() {
		return activeFlag;
	}


	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}	
}
