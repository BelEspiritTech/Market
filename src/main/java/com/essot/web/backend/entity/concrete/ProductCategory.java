package com.essot.web.backend.entity.concrete;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.essot.web.backend.entity.IEssotEntity;

@Entity
@Table(name = "ESSOT_PRODUCT_CATEGORY")
public class ProductCategory implements IEssotEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name= "PRODUCT_CATEGORY_KEY")
	private Integer	productCategoryKey;
	
	@Column(name= "NAME")
	private String 	name;
	
	@Column(name= "DESCRIPTION")
	private String 	description;
	
	@Column(name= "CATEGORY_TITLE")
	private String	categoryTitle;
	
	@Column(name= "PARENT_CATEGORY_KEY")
	private Integer	parentCategoryKey;
	
	@Column(name= "SEQUENCE_ID")
	private Integer	sequenceId;
	
	@Column(name= "RESOURCE_KEY")
	private Integer	resourceKey;
	
	@Column(name= "ACCESS_KEY")
	private	Integer	accessKey;
	
	@Column(name= "OWNED_BY")
	private Integer ownedBy;
	
	@Column(name= "UPDATE_DATE")
	private Date	updateDate;
	
	@Column(name= "UPDATED_BY")
	private Integer updatedBy;
	
	@Column(name= "CREATION_DATE")
	private Date	creationDate;
	
	@Column(name= "CREATED_BY")
	private Integer	createdBy;
	
	@Column(name = "START_DATE")
	private Date	startDate;
	
	@Column(name = "END_DATE")
	private Date	endDate;
	
	@Column(name = "ACTIVE_FLAG")
	private String	activeFlag;
	
	@Column(name = "STATUS")
	private Integer	status;
	
	@Column(name = "CAT_PRIORITY")
	private Integer	priority;

	public Integer getProductCategoryKey() {
		return productCategoryKey;
	}

	public void setProductCategoryKey(Integer productCategoryKey) {
		this.productCategoryKey = productCategoryKey;
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

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public Integer getParentCategoryKey() {
		return parentCategoryKey;
	}

	public void setParentCategoryKey(Integer parentCategoryKey) {
		this.parentCategoryKey = parentCategoryKey;
	}

	public Integer getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	public Integer getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(Integer resourceKey) {
		this.resourceKey = resourceKey;
	}

	public Integer getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(Integer accessKey) {
		this.accessKey = accessKey;
	}

	public Integer getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Integer ownedBy) {
		this.ownedBy = ownedBy;
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}		
}
