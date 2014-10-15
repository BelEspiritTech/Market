package com.essot.web.upload;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.upload.data.BasicExcelData;
import com.essot.web.util.MenuUtil;
import com.essot.web.util.ResourceReader;

public class DataUploadDelegate {
	
	private BasicExcelData excelData;
	
	@Autowired
	private ExcelDataHelper excelHelper;
	
	@Autowired
	private ResourceReader resourceReader;
	
	private static final String EXCEL_FILE = "upload.excel.file";
	
	/**
	 * @return the excelData
	 */
	public BasicExcelData getExcelData(){
		return excelData;
	}


	/**
	 * @param excelData the excelData to set
	 */
	public void setExcelData(BasicExcelData excelData) {
		this.excelData = excelData;
	}


	
	
	public void updateData(){
		try{
			FileInputStream file = new FileInputStream(new File(resourceReader.getMessage(DataUploadDelegate.EXCEL_FILE)));
			 
	        //Create Workbook instance holding reference to .xlsx file
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	
	        //Get first/desired sheet from the workbook
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        
	        Iterator<Row> catIterator = sheet.iterator();
	        
	        while (catIterator.hasNext()) {
	        	Row row = catIterator.next();
	        	
	        	if(row.getRowNum() == 0){
	        		excelData = new BasicExcelData();
	        		continue;
	        	}
	        	
	            this.processCatRow(row);	           
	        }
	        
	        //Iterate through each rows one by one
	        Iterator<Row> prodxcatIterator = sheet.iterator();
	        try{	        
		        while (prodxcatIterator.hasNext()) {
		        	Row row = prodxcatIterator.next();
		        	
		        	if(row.getRowNum() == 0){
		        		excelData = new BasicExcelData();
		        		continue;
		        	}
		        	
		            this.processRow(row);	           
		        }
	        }finally{
	        	this.pushAdditionalData();
	        }
	        //check valid category.
	        excelHelper.setValidCategoryCache();
	    }catch(Exception e){
	    	System.out.println("Exception caught while reading workbook : "+e.getMessage());
	    }
	}
	private void processCatRow(Row row){
		//Process Category Data 
		this.processCategory(row);
	}
	
	private void processRow(Row row){
		this.processProductData(row);
	}
	
	/**
	 * 
	 * @param categoryCell
	 * @param subCategoryCell
	 */
	private void processCategory(Row currentRow){
		Cell categoryCell 	 = currentRow.getCell(0, Row.RETURN_BLANK_AS_NULL);
		Cell subCategoryCell = currentRow.getCell(1, Row.RETURN_BLANK_AS_NULL);
		String categoryName 	= categoryCell != null ? categoryCell.getStringCellValue() : null;
		String subCategoryNames  = subCategoryCell != null ? subCategoryCell.getStringCellValue() : null;
		if(categoryName == null && subCategoryNames==null)
			return;
		BasicExcelData data = this.getExcelData();
		if(categoryName != null){			
			if(data.getCategory() == null  || !categoryName.equalsIgnoreCase(data.getCategory())){
				data.setCategory(categoryName);
				if(!MenuUtil.categoryExists(categoryName)){
					//insert new row if category does not exist in DB
					excelHelper.createCategoryObject(data.getCategory(), null);
				}
			}			
		}
		
		if(subCategoryNames != null){
			String [] subCategoryName = subCategoryNames.split("/");
			String parentCategory = data.getCategory();
			for(int i=0;i<subCategoryName.length;i++){
				if(null != subCategoryName[i]){
					if(!MenuUtil.categoryExists(subCategoryName[i])){
						//insert new row if category does not exist in DB
						excelHelper.createCategoryObject(subCategoryName[i], parentCategory);
						parentCategory = subCategoryName[i];
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param categoryCell
	 * @param subCategoryCell
	 */
	private void processProductData(Row currentRow){
		Cell catxprodCell	 = currentRow.getCell(2,Row.RETURN_BLANK_AS_NULL);
		Cell skuCell 	 	 = currentRow.getCell(3, Row.RETURN_BLANK_AS_NULL);
		Cell skuPrCell		 = currentRow.getCell(4, Row.RETURN_BLANK_AS_NULL);
		Cell productNameCell = currentRow.getCell(5, Row.RETURN_BLANK_AS_NULL);
		Cell shortDescCell 	 = currentRow.getCell(6, Row.RETURN_BLANK_AS_NULL);
		Cell longDescCell 	 = currentRow.getCell(7, Row.RETURN_BLANK_AS_NULL);
		Cell priceCell 		 = currentRow.getCell(8, Row.RETURN_BLANK_AS_NULL);
		Cell activeFlagCell  = currentRow.getCell(9, Row.RETURN_BLANK_AS_NULL);
		
		String catxprod		= catxprodCell != null ? catxprodCell.getStringCellValue() : null;
		String skuID  		= skuCell != null ? skuCell.getStringCellValue() : null;
		Double skuPriority  = skuPrCell != null ? skuPrCell.getNumericCellValue() : null;
		String productName  = productNameCell != null ? productNameCell.getStringCellValue() : null;
		String shortDesc  	= shortDescCell != null ? shortDescCell.getStringCellValue() : null;
		String longDesc  	= longDescCell != null ? longDescCell.getStringCellValue() : null;
		Double price  		= priceCell != null ? priceCell.getNumericCellValue() : null;
		String activeFlag   = activeFlagCell != null ? activeFlagCell.getStringCellValue():null;
		
		BasicExcelData data = this.getExcelData();
		
		if(catxprodCell != null){
			Set<String> subCatSet = new LinkedHashSet<String>(Arrays.asList(catxprod.split(",")));
			data.setSubCategory(subCatSet);
		}
		
		if(skuID != null){
						
			if(skuID != null && !skuID.equalsIgnoreCase(data.getSkuName())){
				//Data for new SKU has started. Push any feature/techSpecs/EnCodes changes for previous SKU
				this.pushAdditionalData();
				
				data.setSkuName(skuID);
				data.setProductName(productName);
				data.setShortDesc(shortDesc);
				data.setLongDesc(longDesc);
				data.setPrice(new Integer(price.intValue()));
				data.setPriority(skuPriority.intValue());
				data.setActiveFlag(activeFlag);
				//save product
				excelHelper.updateProductInfo(data);
			}			
		}
		Cell relSKUCell	  = currentRow.getCell(9, Row.RETURN_BLANK_AS_NULL);
		Cell enCodeCell   = currentRow.getCell(10, Row.RETURN_BLANK_AS_NULL);
		Cell featureCell  = currentRow.getCell(11, Row.RETURN_BLANK_AS_NULL);
		Cell techSpecCell = currentRow.getCell(12, Row.RETURN_BLANK_AS_NULL);
		
		String enCode   	= enCodeCell != null ? enCodeCell.getStringCellValue() : null;
		String feature  	= featureCell != null ? featureCell.getStringCellValue() : null;
		String techSpec		= techSpecCell != null ? techSpecCell.getStringCellValue() : null;
		String relSKU 		= relSKUCell != null ? relSKUCell.getStringCellValue() : null;
		
		if(enCode != null){
			data.addEnCode(enCode);
		}
		
		if(feature != null){
			data.addFeature(feature);
		}
		
		if(techSpec != null){
			data.addTechSpec(techSpec);
		}
		if(relSKU != null){
			data.addRelatedSKUs(relSKU);
		}
	}
	
	private void pushAdditionalData(){
		BasicExcelData data = this.getExcelData();
		
		if(data.getEncodes() != null && !data.getEncodes().isEmpty()){
			excelHelper.pushEnCodes(data);
			data.getEncodes().clear();
		}
		
		if(data.getFeatures() != null && !data.getFeatures().isEmpty()){
			excelHelper.pushFeatures(data);
			data.getFeatures().clear();
		}
		
		if(data.getTechSpecs() != null && !data.getTechSpecs().isEmpty()){
			excelHelper.pushTechSpecs(data);
			data.getTechSpecs().clear();
		}
		if(data.getRelSKU() != null && !data.getRelSKU().isEmpty()){
			excelHelper.pushRelSKUs(data);
			data.getRelSKU().clear();
		}
	}
}
