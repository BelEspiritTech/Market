package com.essot.web.backend.entity.concrete;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.essot.web.backend.entity.IEssotEntity;

@Entity
@Table(name = "ESSOT_PRODUCT_X_FEATURE")
public class ProductXFeature implements IEssotEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SKU_NAME")
	private String 	skuName;
	
	@Id
	@Column(name = "FEATURE_KEY")
	private Integer	featureKey;
	
	@Column(name = "DATA_TYPE")
	private Integer	dataType;
	
	@Column(name = "ASSIGN_TYPE")
	private Integer	assignType;
	
	@Column(name = "VALUE")
	private String 	value;
	
	@Column(name = "SEQUENCE_NUMBER")
	private Integer	sequenceNumber;
	
	@Column(name = "START_DATE")
	private Date	startDate;
	
	@Column(name = "END_DATE")
	private Date	endDate;
	
	@Column(name = "ACTIVE_FLAG")
	private String	activeFlag;
	
	@Column(name = "STATUS")
	private Integer	status;
	
	@Id
	@Column(name = "RELATION_TYPE")
	private Integer	relationType;
	
	public String getSkuName() {
		return skuName;
	}
	
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	public Integer getFeatureKey() {
		return featureKey;
	}
	
	public void setFeatureKey(Integer featureKey) {
		this.featureKey = featureKey;
	}
	
	public Integer getDataType() {
		return dataType;
	}
	
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	public Integer getAssignType() {
		return assignType;
	}
	
	public void setAssignType(Integer assignType) {
		this.assignType = assignType;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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
	
	public Integer getRelationType() {
		return relationType;
	}
	
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}	
}
