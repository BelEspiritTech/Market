package com.essot.web.upload;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

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
	
	        //Iterate through each rows one by one
	        Iterator<Row> rowIterator = sheet.iterator();
	        	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
	        	
	        	if(row.getRowNum() == 0){
	        		excelData = new BasicExcelData();
	        		continue;
	        	}
	        	
	            this.processRow(row);	           
	        }
	        
	    }catch(Exception e){
	    	System.out.println("Exception caught while reading workbook : "+e.getMessage());
	    }
	}
	
	
	private void processRow(Row row){
		
		//Process Category Data 
		this.processCategory(row);
		//Process Product Data
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
		String subCategoryName  = subCategoryCell != null ? subCategoryCell.getStringCellValue() : null;
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
		
		if(subCategoryName != null){
			if(data.getSubCategory() == null  || !subCategoryName.equalsIgnoreCase(data.getSubCategory())){
				data.setSubCategory(subCategoryName);
				if(!MenuUtil.categoryExists(subCategoryName)){
					//insert new row if category does not exist in DB
					excelHelper.createCategoryObject(data.getSubCategory(), data.getCategory());
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
		Cell skuCell 	 	 = currentRow.getCell(2, Row.RETURN_BLANK_AS_NULL);
		Cell productNameCell = currentRow.getCell(3, Row.RETURN_BLANK_AS_NULL);
		Cell shortDescCell 	 = currentRow.getCell(4, Row.RETURN_BLANK_AS_NULL);
		Cell longDescCell 	 = currentRow.getCell(5, Row.RETURN_BLANK_AS_NULL);
		Cell priceCell 		 = currentRow.getCell(6, Row.RETURN_BLANK_AS_NULL);
		Cell activeFlagCell  =currentRow.getCell(7, Row.RETURN_BLANK_AS_NULL);
		
		String skuID  		= skuCell != null ? skuCell.getStringCellValue() : null;
		String productName  = productNameCell != null ? productNameCell.getStringCellValue() : null;
		String shortDesc  	= shortDescCell != null ? shortDescCell.getStringCellValue() : null;
		String longDesc  	= longDescCell != null ? longDescCell.getStringCellValue() : null;
		Double price  		= priceCell != null ? priceCell.getNumericCellValue() : null;
		String activeFlag   = activeFlagCell != null ? activeFlagCell.getStringCellValue():null;
		
		BasicExcelData data = this.getExcelData();
		
		if(skuID != null){
						
			if(skuID != null && !skuID.equalsIgnoreCase(data.getSkuName())){
				//Data for new SKU has started. Push any feature/techSpecs/EnCodes changes for previous SKU
				this.pushAdditionalData();
				
				data.setSkuName(skuID);
				data.setProductName(productName);
				data.setShortDesc(shortDesc);
				data.setLongDesc(longDesc);
				data.setPrice(new Integer(price.intValue()));
				data.setActiveFlag(activeFlag);
				//save product
				excelHelper.updateProductInfo(data);
			}			
		}
		
		Cell enCodeCell   = currentRow.getCell(8, Row.RETURN_BLANK_AS_NULL);
		Cell featureCell  = currentRow.getCell(9, Row.RETURN_BLANK_AS_NULL);
		Cell techSpecCell = currentRow.getCell(10, Row.RETURN_BLANK_AS_NULL);
		
		String enCode   = enCodeCell != null ? enCodeCell.getStringCellValue() : null;
		String feature  = featureCell != null ? featureCell.getStringCellValue() : null;
		String techSpec = techSpecCell != null ? techSpecCell.getStringCellValue() : null;
		
		if(enCode != null){
			data.addEnCode(enCode);
		}
		
		if(feature != null){
			data.addFeature(feature);
		}
		
		if(techSpec != null){
			data.addTechSpec(techSpec);
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
	}
	
}
