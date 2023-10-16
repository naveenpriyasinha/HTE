package com.tcs.sgv.eis.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcelFile extends HttpServlet{
	public Map readFileContent(Workbook nonGovWorkbook){
		//String file="src/Non_Gov_deduc.xls";
	
		List lstNonGovAccountNo = new ArrayList();
		List lstEmpName =  new ArrayList();
	    List lstNonGovAmount = new ArrayList();
	    Map mapExcelData = new HashMap();
	    int columns=0;
	    int rows =0;
		try {
			for(int i=0;i<nonGovWorkbook.getNumberOfSheets();i++){
				Sheet sheet = nonGovWorkbook.getSheet(i);
				columns = sheet.getColumns();
				rows = sheet.getRows();
				for(int row=2;row<rows;row++){		    	
					//System.out.println("The Account Number is:-"+sheet.getCell(1, row).getContents()+" And Amount is:-"+sheet.getCell(3, row).getContents());
					if(sheet.getCell(2, row).getContents()!=null && !sheet.getCell(2, row).getContents().trim().equals("")) {
						lstNonGovAccountNo.add(sheet.getCell(1, row).getContents());
						lstEmpName.add(sheet.getCell(2, row).getContents());
						lstNonGovAmount.add(sheet.getCell(3, row).getContents());
					}
				}
			}
		    mapExcelData.put("lstNonGovAccountNo",lstNonGovAccountNo);
		    mapExcelData.put("lstEmpName",lstEmpName);
		    mapExcelData.put("lstNonGovAmount",lstNonGovAmount);
		     
		} catch(Exception ioe) {
		    //System.out.println("Error: " + ioe);
		}
		return mapExcelData;
	}

	//This method takes a file object of Excel file to be read and returns ArrayList of data
	  public ArrayList getExcelData(File flFile) throws Exception
	  {
	    //Initiation
	    ArrayList lArrExcelFile=null;
	    ArrayList lArrExcelSheet=null;
	    ArrayList lArrExcelRow=null;

	    int liSheetCount=0;
	    int liRowCount=0;
	    int liColCount=0;
	    boolean lbFullRowBlank=true;

	    FileInputStream fis=null;
	    DataInputStream dis=null;

	    Workbook lWorkbook=null;
	    Sheet[] lSheet=null;
	    Cell lCell=null;

	    if (flFile==null)
	    {
	      return null;
	    }
	    try
	    {
	      fis = new FileInputStream(flFile);
	      //Create File Input Stream for input file
	    }
	    catch(FileNotFoundException fne)
	    {
	    	//System.out.println("Error reading file in getExcelData method of ReadExcel"+fne);
	      
	      //throw fne;
	    }
	    catch(Exception e)
	    {
	    	//System.out.println("Error reading file in getExcelData method of ReadExcel"+e);
	      //throw e;
	    }
	    //Create data input stream
	    dis=new DataInputStream(fis);

	    if (dis==null)
	    {
	      return null;
	    }

	    //Read Excel File...
	    try
	    {  
	      //Create workbook from input file
	      lWorkbook = Workbook.getWorkbook(dis);
	      if (lWorkbook==null)
	      {
	        return null;
	      }

	      lSheet = lWorkbook.getSheets();//Get worksheets
	      liSheetCount=lSheet.length;//Get no. of worksheets
	      //ArrayList representing whole Excel file
	      lArrExcelFile=new ArrayList();

	      for(int i=0;i<liSheetCount;i++)//for each worksheet
	      {
	        lArrExcelSheet=new ArrayList();
	        //ArrayList representing one worksheet
	        liRowCount=lSheet[i].getRows();//Get row count
	        liColCount=lSheet[i].getColumns();//Get column count

	        for(int j=0;j<liRowCount;j++)//For each row
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
	              lArrExcelRow.add(lCell.getContents().trim() );//Add to Row ArrayList 
	              //.trim() added by Gopi to get the original value
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
	    }
	    catch(Exception e)
	    {
	      
	    	//System.out.println("Error reading file in getExcelData method of ReadExcel"+e);
	      //throw e;
	    }
	    //Retturn ArrayList
	    return(lArrExcelFile);
	  }	
	
}
