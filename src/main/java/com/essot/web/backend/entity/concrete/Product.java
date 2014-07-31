package com.essot.web.backend.entity.concrete;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.essot.web.backend.entity.IEssotEntity;

@Entity
@Table(name = "ESSOT_PRODUCT")
public class Product implements IEssotEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SKU_NAME")
	private String 	skuName;
	
	@Column(name= "PARENT_SKU_NAME")
	private String	parentSkuName;
	
	@Column(name= "NAME")
	private String 	name;
	
	@Column(name= "DESCRIPTION")
	private String 	description;
	
	@Column(name= "LONG_DESCRIPTION")
	private String 	longDescription;
	
	@Column(name= "COMPONENT_TYPE")
	private Integer componentType;
	
	@Column(name= "UNIT_OF_MEASURE")
	private String 	unitOfMeasure;
	
	@Column(name= "UNIT_OF_MEASURE_CODE")
	private Integer	unitOfMeasureCode;
	
	@Column(name= "QTY_PER_UNIT")
	private Double	qtyPerUnit;
	
	@Column(name= "SELLABLE_FLAG")
	private Integer sellableFlag;
	
	@Column(name= "VALID_FLAG")
	private Integer validFlag;
	
	@Column(name= "ASSIGNED_FLAG")
	private Integer assignedFlag;
	
	@Column(name= "START_DATE_ACTIVE")
	private Date	startDateActive;
	
	@Column(name= "END_DATE_ACTIVE")
	private Date 	endDateActive;
	
	@Column(name= "RESOURCE_KEY")
	private Integer	resourceKey;
	
	@Column(name= "PRE_CONFIG_FLAG")
	private Integer	preConfigFlag;
	
	@Column(name= "MODEL_KEY")
	private Integer modelKey;
	
	@Column(name= "SERVICE_MAINTENANCE_MODEL_KEY")
	private Integer serviceMaintenanceModelKey;
	
	@Column(name= "ACCESS_KEY")
	private Integer	accessKey;
	
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
	
	@Column(name= "SKU_AUTHORITY")
	private String	skuAuthority;
	
	@Column(name= "MINIMUM_ORDER_QUANTITY")
	private Integer	minimumOrderQty;
	
	@Column(name= "STATUS_CODE")
	private Integer	statusCode;
	
	@Column(name= "ACTIVE_FLAG")
	private String activeFlag;
	
	@Column(name= "SERVICE_FLAG")
	private Integer	serviceFlag;
	
	@Column(name= "MFG_ID")
	private Integer	mfgId;
	
	@Column(name= "MFG_SKU")
	private String	mfgSku;
	
	@Column(name= "COMPONENT_SUB_TYPE")
	private Integer	componentSubType;
	
	@Column(name= "PICKUPABLE_FLAG")
	private String	pickableFlag;
	
	@Column(name= "SHIPPING_DISCOUNT_FLAG")
	private String 	shippingDiscountFlag;
	
	@Column(name= "SHIPPABLE_FLAG")
	private String	shippableFlag;
	
	@Column(name= "LOCALUPDATE_FLAG")
	private String 	localUpdateFlag;
	
	@Column(name= "IS_MARKED_FOR_DELETION")
	private Integer	isMarkedForDeletion;
	
	@Column(name= "BRAND_ID")
	private String	brandId;
	
	@Column(name= "BRAND_NAME")
	private String 	brandName;
	
	@Column(name= "PRODUCT_COLOUR")
	private String 	productColor;
	
	@Column(name= "SIZE_CHART")
	private String 	sizeChart;
	
	@Column(name= "B2C_NOWPRICE")
	private	Integer	b2cNowPrice;
	
	@Column(name= "SKU_PRIORITY")
	private	Integer	priority;
	
	public String getSkuName() {
		return skuName;
	}
	
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	public String getParentSkuName() {
		return parentSkuName;
	}
	
	public void setParentSkuName(String parentSkuName) {
		this.parentSkuName = parentSkuName;
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
	
	public Integer getComponentType() {
		return componentType;
	}
	
	public void setComponentType(Integer componentType) {
		this.componentType = componentType;
	}
	
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
	public Integer getUnitOfMeasureCode() {
		return unitOfMeasureCode;
	}
	
	public void setUnitOfMeasureCode(Integer unitOfMeasureCode) {
		this.unitOfMeasureCode = unitOfMeasureCode;
	}
	
	public Double getQtyPerUnit() {
		return qtyPerUnit;
	}
	
	public void setQtyPerUnit(Double qtyPerUnit) {
		this.qtyPerUnit = qtyPerUnit;
	}
	
	public Integer getSellableFlag() {
		return sellableFlag;
	}
	
	public void setSellableFlag(Integer sellableFlag) {
		this.sellableFlag = sellableFlag;
	}
	
	public Integer getValidFlag() {
		return validFlag;
	}
	
	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}
	
	public Integer getAssignedFlag() {
		return assignedFlag;
	}
	
	public void setAssignedFlag(Integer assignedFlag) {
		this.assignedFlag = assignedFlag;
	}
	
	public Date getStartDateActive() {
		return startDateActive;
	}
	
	public void setStartDateActive(Date startDateActive) {
		this.startDateActive = startDateActive;
	}
	
	public Date getEndDateActive() {
		return endDateActive;
	}
	
	public void setEndDateActive(Date endDateActive) {
		this.endDateActive = endDateActive;
	}
	
	public Integer getResourceKey() {
		return resourceKey;
	}
	
	public void setResourceKey(Integer resourceKey) {
		this.resourceKey = resourceKey;
	}
	
	public Integer getPreConfigFlag() {
		return preConfigFlag;
	}
	
	public void setPreConfigFlag(Integer preConfigFlag) {
		this.preConfigFlag = preConfigFlag;
	}
	
	public Integer getModelKey() {
		return modelKey;
	}
	
	public void setModelKey(Integer modelKey) {
		this.modelKey = modelKey;
	}
	
	public Integer getServiceMaintenanceModelKey() {
		return serviceMaintenanceModelKey;
	}
	
	public void setServiceMaintenanceModelKey(Integer serviceMaintenanceModelKey) {
		this.serviceMaintenanceModelKey = serviceMaintenanceModelKey;
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
	
	public String getSkuAuthority() {
		return skuAuthority;
	}
	
	public void setSkuAuthority(String skuAuthority) {
		this.skuAuthority = skuAuthority;
	}
	
	public Integer getMinimumOrderQty() {
		return minimumOrderQty;
	}
	
	public void setMinimumOrderQty(Integer minimumOrderQty) {
		this.minimumOrderQty = minimumOrderQty;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getActiveFlag() {
		return activeFlag;
	}
	
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	public Integer getServiceFlag() {
		return serviceFlag;
	}

	public void setServiceFlag(Integer serviceFlag) {
		this.serviceFlag = serviceFlag;
	}

	public Integer getMfgId() {
		return mfgId;
	}
	
	public void setMfgId(Integer mfgId) {
		this.mfgId = mfgId;
	} 
	public String getMfgSku() {
		return mfgSku;
	}
	
	public void setMfgSku(String mfgSku) {
		this.mfgSku = mfgSku;
	}
	
	public Integer getComponentSubType() {
		return componentSubType;
	}
	
	public void setComponentSubType(Integer componentSubType) {
		this.componentSubType = componentSubType;
	}
	
	public String getPickableFlag() {
		return pickableFlag;
	}
	
	public void setPickableFlag(String pickableFlag) {
		this.pickableFlag = pickableFlag;
	}
	
	public String getShippingDiscountFlag() {
		return shippingDiscountFlag;
	}
	
	public void setShippingDiscountFlag(String shippingDiscountFlag) {
		this.shippingDiscountFlag = shippingDiscountFlag;
	}
	
	public String getShippableFlag() {
		return shippableFlag;
	}
	
	public void setShippableFlag(String shippableFlag) {
		this.shippableFlag = shippableFlag;
	}
	
	public String getLocalUpdateFlag() {
		return localUpdateFlag;
	}
	
	public void setLocalUpdateFlag(String localUpdateFlag) {
		this.localUpdateFlag = localUpdateFlag;
	}
	
	public Integer getIsMarkedForDeletion() {
		return isMarkedForDeletion;
	}
	
	public void setIsMarkedForDeletion(Integer isMarkedForDeletion) {
		this.isMarkedForDeletion = isMarkedForDeletion;
	}
	
	public String getBrandId() {
		return brandId;
	}
	
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getProductColor() {
		return productColor;
	}
	
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	
	public String getSizeChart() {
		return sizeChart;
	}
	
	public void setSizeChart(String sizeChart) {
		this.sizeChart = sizeChart;
	}
	
	public Integer getB2cNowPrice() {
		return b2cNowPrice;
	}
	
	public void setB2cNowPrice(Integer b2cNowPrice) {
		this.b2cNowPrice = b2cNowPrice;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
}
