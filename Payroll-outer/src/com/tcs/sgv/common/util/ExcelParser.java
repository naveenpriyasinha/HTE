package com.tcs.sgv.common.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExcelParser {
	Log logger = LogFactory.getLog(ExcelParser.class);
	
	/**
	 * This method parse an excel worksheet and gives a nested ArrayList (Workbook, worksheet, cell)
	 * @param file File object of a file to be parse
	 * @return ArrayList
	 */
	public static ArrayList parseExcel(File file){
	    {
		    ArrayList lArrExcelFile=null;
		    ArrayList lArrExcelSheet=null;
		    ArrayList lArrExcelRow=null;

	    	try {

		    	    boolean lbFullRowBlank=true;
		    	    
		    	    Workbook lWorkbook=null;
		    	    Sheet[] lSheet=null;
		    	    Cell lCell=null;
		    	    int liSheetCount;
		    	    int liRowCount;
		    	    int liColCount;
		    	    
		    		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		    		System.out.print("Reading file");
		    	      //Create workbook from input file
		    	      lWorkbook = Workbook.getWorkbook(dis);
		    	      if (lWorkbook==null)
		    	      {
		    	        return null;
		    	      }
		    	       
		    	      lSheet = lWorkbook.getSheets();                           //Get worksheets
		    	      liSheetCount=lSheet.length;                                  //Get no. of worksheets
		    	                                                                //ArrayList representing whole Excel file
		    	      lArrExcelFile=new ArrayList();  
		    	      
		    	      for(int i=0;i<liSheetCount;i++)//for each worksheet
		    	      {
		    	        lArrExcelSheet=new ArrayList();
		    	        //ArrayList representing one worksheet
		    	        liRowCount=lSheet[i].getRows();//Get row count
		    	        liColCount=lSheet[i].getColumns();//Get column count
		    	        //System.out.println(" In read excel method sheet count is : " + liSheetCount + " and row count is + : "+liRowCount);
		    	        
		    	        for(int j=0;j<liRowCount;j++)                           //For each row
		    	        {
		    	          lArrExcelRow=new ArrayList();
		    	          //ArrayList representing one row
		    	          lbFullRowBlank=true;
		    	          //Flag indiacting whether full row is blank or not
		    	          for(int k=0;k<liColCount;k++)//For each column
		    	          {
		    	            //Read cell contents
		    	            lCell = lSheet[i].getCell(k,j);
		    	            //if content is number or text
		    	            if(!(lCell.getType()==CellType.EMPTY))
		    	            //if((lCell.getType()==CellType.LABEL)||(lCell.getType()==CellType.NUMBER))
		    	            {
		    	              lArrExcelRow.add(lCell.getContents());//Add to Row ArrayList
		    	              lbFullRowBlank=false;//Full row is not blank
		    	            }
		    	            else //if cell is blank
		    	            {
		    	              lArrExcelRow.add("-");
		    	            }
		    	            lCell=null;
		    	          }     
		    	          if(!(lbFullRowBlank))//If full row is not blank
		    	          {
		    	            //Add Row ArrayList to Sheet ArrayList
		    	            lArrExcelSheet.add(lArrExcelRow);
		    	          }
		    	          lArrExcelRow=null;
		    	        }
		    	        //Add Sheet ArrayList to File ArrayList
		    	        lArrExcelFile.add(lArrExcelSheet);
		    	        lArrExcelSheet=null;
		    	      }

	    	} catch (Exception e) {
				e.printStackTrace();
			}
	    	return(lArrExcelFile);
	    }
	}
}
