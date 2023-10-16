package com.tcs.sgv.hr.payfixation.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.fms.valueobject.WfDocMst;
import com.tcs.sgv.hr.payfixation.dao.PayFixationDao;
import com.tcs.sgv.hr.payfixation.dao.PayFixationDaoImpl;
import com.tcs.sgv.hr.payfixation.dao.PayFixationInfoDao;
import com.tcs.sgv.hr.payfixation.dao.PayFixationInfoDaoImpl;
import com.tcs.sgv.hr.payfixation.valueobject.HrPayFixTxn;
import com.tcs.sgv.hr.payfixation.valueobject.HrPayfixMst;
import com.tcs.sgv.hr.payfixation.valueobject.HrPayfixReqDtl;
import com.tcs.sgv.hr.payfixation.valueobject.PayfixationEmpInfoVO;
import com.tcs.sgv.hr.payfixation.valueobject.SearchresultVO;
import com.tcs.sgv.hr.payincrement.valueobject.HrEisPayInc;
/**
 * @author 217977
 *
 */
public class PayFixationServiceImpl  extends ServiceImpl implements PayFixationService
{
	Log logger = LogFactory.getLog(getClass());

	    /* (non-Javadoc)
		 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#FindDetail(java.util.Map)
		 */
	    /* (non-Javadoc)
		 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#FindDetail(java.util.Map)
		 */
	    public ResultObject FindDetail(Map objectArgs) {

	   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	   	
        Map voToService = (Map)objectArgs.get("voToServiceMap");
        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        
        HrEisEmpMst hrEmpMst =(HrEisEmpMst)objectArgs.get("hrEmpMst");
	    
	  
	   	long empid=hrEmpMst.getEmpId();
	   	try
	    {
	        if (objRes!=null && objectArgs!=null)
	        {
	        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	            
	        	
                   if(hrEmpMst!=null){
	            	
                	  
                	   
	            	long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	            	
	            	PayFixationDao GemEmpinfo1 = new PayFixationDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
	            	long empIdE=GemEmpinfo1.Getalldatafromorgemp(empid);
	            	
	            	
	            	
	            	PayFixationInfoDao GemEmpinfo = new PayFixationInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
                    PayfixationEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(empIdE,langId,empid);
	            	
                    objectArgs.put("EmpDet",EmpDetailsVO);
                    
	            	 
	                PayFixationDao payfixationscale = new PayFixationDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
	            	 List empscale=payfixationscale.Scale(empIdE,langId);
	            	 HrEisScaleMst EmpScale=new HrEisScaleMst();
	            	 EmpScale=(HrEisScaleMst)empscale.get(0);
	            	 
	            	 objectArgs.put("empscale",EmpScale);
	            	 
	            	 EmpScale.getScaleStartAmt();
	            	 
	            	
	                    PayFixationDao scaleDao = new PayFixationDaoImpl(HrEisScaleMst.class,serv.getSessionFactory());
			            List <HrEisScaleMst> scalemstList =scaleDao.getScaleName(EmpScale.getScaleStartAmt());
						objectArgs.put("scalemstList",scalemstList);
						
						
		               CmnLookupMstDAO cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
						List<CmnLookupMst> PayFixationList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("PayFixation", langId);
						objectArgs.put("PayFixationList",PayFixationList);
						objRes.setResultCode(ErrorConstants.SUCCESS);
		                
		               objRes.setResultValue(objectArgs);
		                objRes.setViewName("Employeedetails");
	                 
	            }
	        }
	    }
		      		  
		      		  catch (Exception e)
			            {
			                
			            	e.printStackTrace();
		      			     logger.info("\n Error occur in Payfixation details");
			                 Map result = new HashMap();
			                 objRes.setResultValue(result);
			                 objRes.setResultCode(-1);
			                 objRes.setThrowable(e);
			                 objRes.setResultCode(ErrorConstants.ERROR);
			                 objRes.setThrowable(e);
			                 objRes.setViewName("errorPage");
			            }

		      		return objRes;
		     	  
	            }
	   
	
	   /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Employeeacceptance(java.util.Map)
	 */
	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Employeeacceptance(java.util.Map)
	 */
	public ResultObject Employeeacceptance(Map objectArgs) {

		   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		   	
	        Map voToService = (Map)objectArgs.get("voToServiceMap");
	        Map   loginMap = (Map) objectArgs.get("baseLoginMap");
			 long empid = Long.parseLong(loginMap.get("empId").toString());
			 long userID = Long.parseLong(loginMap.get("userId").toString());
			 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			 long dbId = Long.parseLong(loginMap.get("dbId").toString());
			 long locId =  Long.parseLong(loginMap.get("locationId").toString());
			 long langId = Long.parseLong(loginMap.get("langId").toString());
         try
		    {
		        if (objRes != null && objectArgs != null)
		        {
		        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		           
                 
	            	
	            	PayFixationDao GemEmpinfo1 = new PayFixationDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
	            	long empIdE=GemEmpinfo1.Getalldatafromorgemp(empid);
	            	
		        

	            	PayFixationInfoDao GemEmpinfo = new PayFixationInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
                    PayfixationEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(empIdE,langId,empid);
	            	
                    objectArgs.put("EmpDet",EmpDetailsVO);
		            	 
                    
		            	  PayFixationDao payfixationscale = new PayFixationDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
		            	 List empscale=payfixationscale.Scale(empIdE, langId);
		            	 HrEisScaleMst EmpScale=new HrEisScaleMst();
		            	 EmpScale=(HrEisScaleMst)empscale.get(0);
		            	
		            	 objectArgs.put("empscale",EmpScale);
		            	 long currbasic=EmpDetailsVO.getSalary();
		            	 long currincr=EmpScale.getScaleIncrAmt();
		            	 long currbas=EmpDetailsVO.getSalary();
		            	 long currb=EmpDetailsVO.getSalary();
		            	 long curri=EmpScale.getScaleIncrAmt();
		            	 PayFixationDao insert=new PayFixationDaoImpl(HrPayFixTxn.class,serv.getSessionFactory());
		            	 List Empfix=insert.Fixation(userID, langId);
		            	 HrPayFixTxn tran=new HrPayFixTxn();
		            	 tran=(HrPayFixTxn)Empfix.get(0);
		            	 objectArgs.put("EmpfixList",tran);
		            	  long scale=tran.getRevScaleId().getScaleId();
		            	  long revscalestrtamt=tran.getRevScaleId().getScaleStartAmt();
		            	  long revscalestamt=tran.getRevScaleId().getScaleStartAmt();
		            	  long revstart=tran.getRevScaleId().getScaleStartAmt();
		            	  long revicc=tran.getRevScaleId().getScaleIncrAmt();
		            	  long revscaleincr=tran.getRevScaleId().getScaleIncrAmt();
		            	 long revscaleincramt=tran.getRevScaleId().getScaleIncrAmt();
		            	  PayFixationDao scalename=new PayFixationDaoImpl(HrEisScaleMst.class,serv.getSessionFactory());
		                   List scalen=scalename.Amount(scale);
							HrEisScaleMst mst=new HrEisScaleMst();
							mst=(HrEisScaleMst)scalen.get(0);
							
							objectArgs.put("scalelist",mst);
							
							 long n=1;
		            			
		            			if(currbasic<revscalestrtamt)
		            			{
		            				currbasic=currbasic+currincr;
		            				if(currbasic<=revscalestrtamt)
		            				{
		            					currbasic=revscalestrtamt;
		            					
		            					
		            				}
		            				else
		            				{
		            					for(n=1;;n++)
		            					{
		            						if(currbasic<=(revscalestrtamt+(n*revscaleincr)))
		            						{
		            							currbasic=revscalestrtamt+(n*revscaleincr);
		            							
		            						
		            							break;
		            						}
		            						else
		            						{
		            							if((currbasic)<=(revscalestrtamt+((n+1)*revscaleincr)) && currbasic>=(revscalestrtamt+(n*revscaleincr)))
		            							{
		            								currbasic=revscalestrtamt+((n+1)*revscaleincr);
		            								
		            								
		            								break;
		            							}
		            						}
		            								            						
		            					}
		            				}
		            					
		            			}
		            			else
		            			{
		            				if(currbasic>=revscalestrtamt)
		            				{
		            					currbasic=currbasic+currincr;
		            					for(n=1;;n++)
		            					{
		            						if(currbasic<=(revscalestrtamt+(n*revscaleincr)))
		            						{
		            							currbasic=revscalestrtamt+(n*revscaleincr);
		            							
		            							
		            							break;
		            						}
		            						else
		            						{
		            							if((currbasic)<=(revscalestrtamt+((n+1)*revscaleincr)) && currbasic>=(revscalestrtamt+(n*revscaleincr)))
		            							{
		            								currbasic=revscalestrtamt+((n+1)*revscaleincr);
		            								
		            								
		            								break;
		            							}
		            						}
		            						
		            					}		
		            				}
		            			}
		            			objectArgs.put("option2",currbasic);
		            			Date PayfixDate=tran.getPayFixDate();
		            			 String Payfixdate=PayfixDate.getDate()+"/"+(PayfixDate.getMonth()+1)+"/"+(PayfixDate.getYear()+1901);
		            			 objectArgs.put("option2date",Payfixdate);
		            			 long a=0;
			            		 for(a=0;;a++)
	        					{
	        						if(currbas<=(revscalestamt+(a*revscaleincramt)))
	        						{
	        							currbas=revscalestamt+(a*revscaleincramt);
	        							
	        							break;
	        						}
	        						else
	        						{
	        							if((currbas)<=(revscalestamt+((a+1)*revscaleincramt)) && currbas>=(revscalestamt+(a*revscaleincramt)))
	        							{
	        								currbas=revscalestamt+((a+1)*revscaleincramt);
	        								
	        								
	        								break;
	        							}
	        						}
	        						
	        							
	        						
	        					}
			            		 objectArgs.put("option11",currbas);
							Date date1=tran.getIncrDate();
			            		 String incrdate=date1.getDate()+"/"+(date1.getMonth()+1)+"/"+(date1.getYear()+1900);
			            		 objectArgs.put("option11date",incrdate);
			            		 
			            		 
				            		long c=1;
				            		 currb=currb+curri;
				            			if(currb<revstart)
				            			{
				            				currb=currb+curri;
				            				if(currb<=revstart)
				            				{
				            					currb=revstart;
				            					
				            					
				            				}
				            				else
				            				{
				            					for(c=1;;c++)
				            					{
				            						if(currb<=(revstart+(c*revicc)))
				            						{
				            							currb=revstart+(c*revicc);
				            							
				            						    break;
				            						}
				            						else
				            						{
				            							if((currb)<=(revstart+((c+1)*revicc)) && currb>=(revstart+(c*revicc)))
				            							{
				            								currb=revstart+((c+1)*revicc);
				            								
				            								break;
				            							}
				            						}	
				            					}
				            				}	
				            			}
				            			else
				            			{
				            				if(currb>=revstart)
				            				{
				            					currb=currb+curri;
				            					for(c=1;;c++)
				            					{
				            						if(currb<=(revstart+(c*revicc)))
				            						{
				            							currb=revstart+(c*revicc);
				            							
				            							break;
				            						}
				            						else
				            						{
				            							if((currb)<=(revstart+((c+1)*revicc)) && currb>=(revstart+(c*revicc)))
				            							{
				            								currb=revstart+((c+1)*revicc);
				            								
				            								break;
				            							}
				            						}
				            						
				            					}		
				            				}
				            			}
				            			objectArgs.put("option12",currb);
				            			String incrd=date1.getDate()+"/"+(date1.getMonth()+1)+"/"+(date1.getYear()+1901);
				            			objectArgs.put("option12date",incrd);
							
						  objRes.setResultCode(ErrorConstants.SUCCESS);
			                
			               objRes.setResultValue(objectArgs);
			                objRes.setViewName("Employeeacceptance");		            
		        }
		    }
			      		   catch (Exception e)
				            {
				                e.printStackTrace();
			      			    logger.info("\n Error occur in PayFixation details");
				                 Map result = new HashMap();
				                 objRes.setResultValue(result);
				                 objRes.setResultCode(-1);
				                 objRes.setThrowable(e);
				                 objRes.setResultCode(ErrorConstants.ERROR);
				                 objRes.setThrowable(e);
				                 objRes.setViewName("errorPage");
				            }

			      		return objRes;
			     	  
		            }
	   /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Month(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Month(java.util.Map)
	 */
	public ResultObject Month(Map objectArgs)
		 {
			 	
			 	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
				ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				 Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

					try
					{
                              long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());  
                              Map map = new HashMap();

				             CmnLookupMstDAO cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
							List<CmnLookupMst> MonthList =cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Month",langId);
                             
							
							objectArgs.put("MonthList",MonthList);

							CmnLookupMstDAO cmnLookupMstDAOImpl1=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					       List<CmnLookupMst> YearList =cmnLookupMstDAOImpl1.getAllChildrenByLookUpNameAndLang("Year",langId);
							
							objectArgs.put("YearList",YearList); 

                               
						        resultObject.setResultValue(objectArgs);
					            resultObject.setResultCode(ErrorConstants.SUCCESS);		
						        resultObject.setViewName("Searchbymthyrdesig");
			}
					catch(Exception e)
					{
						e.printStackTrace();
						objRes.setResultCode(-1);
						 
					}
                 return resultObject; 
		 }
	   /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#SearchPayFix(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#SearchPayFix(java.util.Map)
	 */
	public ResultObject SearchPayFix(Map objectArgs) {

		   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		   	HrPayFixTxn HrEssTranPayFix=(HrPayFixTxn)objectArgs.get("hrtranpayfix");
		   	HrModEmpRlt HrModEmpRlt=(HrModEmpRlt)objectArgs.get("hrmod");
		   
		   	List actionList;
		    /* login code */
		   Map   loginMap = (Map) objectArgs.get("baseLoginMap");
				 long EmpID = Long.parseLong(loginMap.get("empId").toString());
				 long userID = Long.parseLong(loginMap.get("userId").toString());
				 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
				 long dbId = Long.parseLong(loginMap.get("dbId").toString());
				 long locId =  Long.parseLong(loginMap.get("locationId").toString());
				 long langId = Long.parseLong(loginMap.get("langId").toString());

		   	try
		    {
		        if (objRes != null && objectArgs != null)
		        {
		        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		           
		        	 if(HrEssTranPayFix!=null)
		            {
		            	
		            Date startdate=HrEssTranPayFix.getApplDate();
		            Date enddate=HrEssTranPayFix.getPayFixDate();
		          
		           String option=HrEssTranPayFix.getPayFixation();
		           
		            
		            java.util.GregorianCalendar gc=new java.util.GregorianCalendar(2007,startdate.getMonth(),1);
		            
		             
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		            String stda= sdf.format(startdate);
		            String edda= sdf.format(enddate); 
		          
		            if(option.equals("1"))
		            {
		            String [] month={"January","February","March","April","May","June","July","August","September","October","November","December"};
		            String stdate=startdate.getDate()+"/"+month[startdate.getMonth()]+"/"+(startdate.getYear()+1900);
		            String endddate=enddate.getDate()+"/"+month[enddate.getMonth()]+"/"+(enddate.getYear()+1900);
		            PayFixationDao payfix=new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		          
		            actionList=payfix.SearchByPayFixDate(stda,edda);
		            
		            ArrayList newlist=new ArrayList();
		            for(int i=0;i<actionList.size();i++)
		            {
		            	HrPayFixTxn transfix=(HrPayFixTxn)actionList.get(i);
		            	SearchresultVO searchresult=new SearchresultVO();
		            	PayFixationDao payfix1=new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		            	List name=payfix1.Getorgempbyuserid(transfix.getUserId().getUserId(), langId);
		            	OrgEmpMst org=(OrgEmpMst)name.get(0);
		            	searchresult.setEmpPrefix(org.getEmpPrefix());
		            	searchresult.setEmpFName(org.getEmpFname());
		            	searchresult.setEmpMName(org.getEmpMname());
		            	searchresult.setEmpLName(org.getEmpLname());
		            	long empid=org.getEmpId();
		            	PayFixationDao GemEmpinfo1 = new PayFixationDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
		            	long empIdE=GemEmpinfo1.Getalldatafromorgemp(empid);
		            	
		            	 PayFixationInfoDao GemEmpinfo = new PayFixationInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
		                    PayfixationEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(empIdE,langId,empid);
		            	searchresult.setDesignation(EmpDetailsVO.getDesignation());
		            	 PayFixationDao payfixationscale = new PayFixationDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
		            	 List empscale=payfixationscale.Scale(empIdE, langId);
		            	 HrEisScaleMst EmpScale=new HrEisScaleMst();
		            	 EmpScale=(HrEisScaleMst)empscale.get(0);
		            	 String presentpayscale=EmpScale.getScaleStartAmt()+"-"+EmpScale.getScaleIncrAmt()+"-"+EmpScale.getScaleEndAmt();
		            	searchresult.setPresentpayscale(presentpayscale);
		            	String revisedpayscale=transfix.getRevScaleId().getScaleStartAmt()+"-"+transfix.getRevScaleId().getScaleIncrAmt()+"-"+transfix.getRevScaleId().getScaleEndAmt();
		            	searchresult.setRevisedpayscale(revisedpayscale);
		            	searchresult.setReasonPayfixed(transfix.getCmnLookupMst().getLookupName());
		            	Date payfixdate=transfix.getPayFixDate();
		            	String fixdate=payfixdate.getDate()+"/"+(payfixdate.getMonth()+1)+"/"+(payfixdate.getYear()+1900);
		            	searchresult.setDateofPayfixation(fixdate);
		            	searchresult.setPayfixid(transfix.getPayFixId());
		            	
		            	
		            	newlist.add(searchresult);
		            }
		            /*for (int i=0;i<actionList.size();i++)
	                {
		        	   HrEssTranPayFix hrEssTranPayFix = new HrEssTranPayFix();
		        	   hrEssTranPayFix = actionList.get(i);
	                	String x = hrEssTranPayFix.getOrgEmpMst().getEmpFname()+hrEssTranPayFix.getOrgEmpMst().getOrgUserMst().get+hrEssTranPayFix.getCmnLookupMst().getLookupName()+hrEssTranPayFix.getRevScaleId().getScaleStartAmt()+hrEssTranPayFix.getHrModEmpRlt().getScaleId().getScaleStartAmt();               
	                }*/
	              
		           Map map = new HashMap();
	                map.put("newlist",newlist);
		                    objRes.setResultCode(ErrorConstants.SUCCESS);
		                 	
			                objRes.setResultValue(map);
			                objRes.setViewName("SearchPayFix");
		            
		            
		            }
		            
		            else if(option.equals("0"))
		            	
		            {
		            	String [] month={"January","February","March","April","May","June","July","August","September","October","November","December"};
		            String stdate=startdate.getDate()+"/"+month[startdate.getMonth()]+"/"+(startdate.getYear()+1900);
		            String endddate=enddate.getDate()+"/"+month[enddate.getMonth()]+"/"+(enddate.getYear()+1900);
		            PayFixationDao payfix=new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		           
		            
		            actionList=payfix.SearchByIncrementDate(stda,edda);
		            
		            ArrayList newlist=new ArrayList();
		            for(int i=0;i<actionList.size();i++)
		            {
		            	HrPayFixTxn transfix=(HrPayFixTxn)actionList.get(i);
		            	SearchresultVO searchresult=new SearchresultVO();
		            	PayFixationDao payfix1=new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		            	List name=payfix1.Getorgempbyuserid(transfix.getUserId().getUserId(), langId);
		            	OrgEmpMst org=(OrgEmpMst)name.get(0);
		            	searchresult.setEmpPrefix(org.getEmpPrefix());
		            	searchresult.setEmpFName(org.getEmpFname());
		            	searchresult.setEmpMName(org.getEmpMname());
		            	searchresult.setEmpLName(org.getEmpLname());
		            	long empid=org.getEmpId();
		            	PayFixationDao GemEmpinfo1 = new PayFixationDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
		            	long empIdE=GemEmpinfo1.Getalldatafromorgemp(empid);
		            	
		            	 PayFixationInfoDao GemEmpinfo = new PayFixationInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
		                    PayfixationEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(empIdE,langId,empid);
		            	searchresult.setDesignation(EmpDetailsVO.getDesignation());
		            	 PayFixationDao payfixationscale = new PayFixationDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
		            	 List empscale=payfixationscale.Scale(empIdE, langId);
		            	 HrEisScaleMst EmpScale=new HrEisScaleMst();
		            	 EmpScale=(HrEisScaleMst)empscale.get(0);
		            	 String presentpayscale=EmpScale.getScaleStartAmt()+"-"+EmpScale.getScaleIncrAmt()+"-"+EmpScale.getScaleEndAmt();
		            	searchresult.setPresentpayscale(presentpayscale);
		            	String revisedpayscale=transfix.getRevScaleId().getScaleStartAmt()+"-"+transfix.getRevScaleId().getScaleIncrAmt()+"-"+transfix.getRevScaleId().getScaleEndAmt();
		            	searchresult.setRevisedpayscale(revisedpayscale);
		            	searchresult.setReasonPayfixed(transfix.getCmnLookupMst().getLookupName());
		            	Date payfixdate=transfix.getIncrDate();
		            	String fixdate=payfixdate.getDate()+"/"+(payfixdate.getMonth()+1)+"/"+(payfixdate.getYear()+1900);
		            	searchresult.setDateofPayfixation(fixdate);
		            	searchresult.setPayfixid(transfix.getPayFixId());
		            	
		            	
		            	newlist.add(searchresult);
		            }
		            /*for (int i=0;i<actionList.size();i++)
	                {
		        	   HrEssTranPayFix hrEssTranPayFix = new HrEssTranPayFix();
		        	   hrEssTranPayFix = actionList.get(i);
	                	String x = hrEssTranPayFix.getOrgEmpMst().getEmpFname()+hrEssTranPayFix.getOrgEmpMst().getOrgUserMst().get+hrEssTranPayFix.getCmnLookupMst().getLookupName()+hrEssTranPayFix.getRevScaleId().getScaleStartAmt()+hrEssTranPayFix.getHrModEmpRlt().getScaleId().getScaleStartAmt();               
	                }*/
	              
		           Map map = new HashMap();
	                map.put("newlist",newlist);
		                    objRes.setResultCode(ErrorConstants.SUCCESS);
		                 	
			                objRes.setResultValue(map);
			                objRes.setViewName("SearchPayFixByIncr");
		            
		            
		            }
		            
		            
		            
		            }
		        }
		    }
			      		  
			      		  catch (Exception e)
				            {
				                
				            	e.printStackTrace();
			      			 
				                 Map result = new HashMap();
				                 objRes.setResultValue(result);
				                 objRes.setResultCode(-1);
				                 objRes.setThrowable(e);
				                 objRes.setResultCode(ErrorConstants.ERROR);
				                 objRes.setThrowable(e);
				                 objRes.setViewName("errorPage");
				            
				            }
			      		return objRes;
			     	  
		            }
	   /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Payfix(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Payfix(java.util.Map)
	 */
	public ResultObject Payfix(Map objectArgs) {

		   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		   	
	        Map voToService = (Map)objectArgs.get("voToServiceMap");
	        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	        /* login code */
			   Map   loginMap = (Map) objectArgs.get("baseLoginMap");
					 long EmpID = Long.parseLong(loginMap.get("empId").toString());
					 long userID = Long.parseLong(loginMap.get("userId").toString());
					 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
					 long dbId = Long.parseLong(loginMap.get("dbId").toString());
					 long locId =  Long.parseLong(loginMap.get("locationId").toString());
					 long langId = Long.parseLong(loginMap.get("langId").toString());
	        
					 HrPayFixTxn hrtranpayfix =(HrPayFixTxn)objectArgs.get("hrtranpayfix");
		    long PayfixId=hrtranpayfix.getPayFixId();
		   	try
		    {
		        if (objRes != null && objectArgs != null)
		        {
		        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		          
		        	
                         if(hrtranpayfix!=null)
                         {
		            	
		              // long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
                        	 PayFixationDao insertfixationDao = new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		            	 List tranpayfix=insertfixationDao.Getalldatafromhrtrans(PayfixId);
		            	 HrPayFixTxn trans=new HrPayFixTxn();
		            	 trans=(HrPayFixTxn)tranpayfix.get(0);
		            	 String rule=trans.getPayFixation();
		            	 String rule1=trans.getPayFixationAtIncr();
		            	 long userid=trans.getUserId().getUserId();
		            	 PayFixationDao insertfixationDao1 = new PayFixationDaoImpl(OrgEmpMst.class, serv.getSessionFactory());
		            	long empidguj=insertfixationDao1.Getgujemp(userid);
		            	long empide= insertfixationDao1.Getalldatafromorgemp(empidguj);
		            	 
		            	 
		            	 
		            	 
		            	 PayFixationInfoDao GemEmpinfo = new PayFixationInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
		                    PayfixationEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(empide,langId,empidguj);
		                    long currbasic=EmpDetailsVO.getSalary();
		                    long desigid=EmpDetailsVO.getDesigid();
		                    PayFixationDao payfixationscale = new PayFixationDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
			            	 List empscale=payfixationscale.Scale(empide,langId);
			            	 HrEisScaleMst EmpScale=new HrEisScaleMst();
			            	 EmpScale=(HrEisScaleMst)empscale.get(0);
			            	 HrEisScaleMst scalems=new HrEisScaleMst();
			            	 scalems.setScaleId(EmpScale.getScaleId());
		                    long currincr=EmpScale.getScaleIncrAmt();
		            	
		            	 long revscalestrtamt=trans.getRevScaleId().getScaleStartAmt();
		            	 long revscaleincr=trans.getRevScaleId().getScaleIncrAmt();
		            	 Date PayfixDate=trans.getPayFixDate();
		            	 Date date1=trans.getIncrDate();
		            /*	 objectArgs.put("tablename", "HR_ESS_PAYFIX_REQ_DTLS");
		     			objectArgs.put("serviceLocator", serv);

		     			ResultObject resultObj = serv.executeService(
		     					"GENERATE_ID_SRVC", objectArgs);
		     			int receivedcode = resultObj.getResultCode();

		     			if (receivedcode == -1) {
		     				return objRes;
		     			}
		     			
		     			Map resultMap = (Map) resultObj.getResultValue();
		     			long reqId = (Long) resultMap.get("newID");*/
		            	 objectArgs.put("Subject","300003");//doc id
		     			OrgPostDetailsRltDaoImpl orgPostMstDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
		     			OrgPostDetailsRlt orgpostMst=orgPostMstDaoImpl.getPostDetailsRltByPostIdAndLangId(postId, langId);
		     			long branchid=orgpostMst.getCmnBranchMst().getBranchId();
		     			 objectArgs.put("Description","PayFixation Request");
		     			 objectArgs.put("section",branchid+"");//branch id
		     			 objectArgs.put("letterno","PFR");//Tri code
		     			 objectArgs.put("SubCode","10");
		     			PayFixationDao payfixdao = new PayFixationDaoImpl(WfDocMst.class, serv.getSessionFactory());
		    			long nxtpostId=payfixdao.getHigherHierachyPostId(postId+"",300003);
		    			if(nxtpostId!=0)
		    			{
		    			objectArgs.put("postIdForwardTo",nxtpostId);
		    			objectArgs.put("fileForwardFlag","yes");
		     			 
		    			}
		     			 ResultObject objres1 = serv.executeService("CREATEFMSFILEVO", objectArgs);
		     			 Map resultMap = (Map) objres1.getResultValue();
		     			 String fileId=resultMap.get("fileId").toString();
		     			 long reqId=Long.parseLong(fileId);
		     			
		            	 HrPayfixReqDtl reqdtls=new HrPayfixReqDtl();
		            	 HrModEmpRlt hrmod=new HrModEmpRlt();
		            	 hrmod.setReqId(reqId);
		            	 
		            	 reqdtls.setReqId(reqId);
		            	 long a=1;
		            	 reqdtls.setReqType(a);
		            	
		            	 hrmod.setUserId(userid);
		            	 hrmod.setBasicSal(currbasic);
		            	 hrmod.setScaleId(scalems);
		            	 HrModMst hrModMst=new HrModMst();
		            	 hrModMst.setModId(1000);
		            	 hrmod.setHrModMst(hrModMst);
		            	 OrgDesignationMst desid=new OrgDesignationMst();
		            	 desid.setDsgnId(desigid);
		            	 hrmod.setDsgnId(desid);
		            	 OrgUserMst user=new OrgUserMst();
		            	 user.setUserId(userID);
		            	 hrmod.setCreatedBy(userID);
		            	 CmnDatabaseMst cmnDatabaseMst=new CmnDatabaseMst();
		            	 cmnDatabaseMst.setDbId(dbId);
		            	 reqdtls.setDbId(cmnDatabaseMst);
		            	 CmnLocationMst cmnLocationMst=new  CmnLocationMst();
		            	 cmnLocationMst.setLocId(locId);
		            	 reqdtls.setLocId(cmnLocationMst);
		            	 reqdtls.setCreatedBy(user);
		            	
		            	 hrmod.setCreatedByPost(postId);
		            	 OrgPostMst post=new OrgPostMst();
		            	 post.setPostId(postId);
		            	 reqdtls.setCreatedByPost(post);
		            	 reqdtls.setCompFlag("s");
		            	 Date dt=new Date();
		     			int day=dt.getDate();
		     			int mont=dt.getMonth()+1;
		     			int yr=dt.getYear()+1900;
		     			String dt1=day+"/"+mont+"/"+yr;
		     			Date appdate=StringUtility.convertStringToDate(dt1);
		     			hrmod.setCreatedDate(appdate);
		     			reqdtls.setCreatedDate(appdate);
		     			OrgUserMst user1=new OrgUserMst();
		     			user1.setUserId(userid);
		            	 reqdtls.setUserId(user1);
		            	 HrPayFixTxn hrEssTranPayFix=new HrPayFixTxn();
		            	 hrEssTranPayFix.setPayFixId(PayfixId);
		            	 reqdtls.setHrPayFixTxn(hrEssTranPayFix);
		            	 reqdtls.setApprove("p");
		            	 
		            	 
		            	 if(rule.equals("y"))
		            	 {
		            		 long n=1;
		            			
		            			if(currbasic<revscalestrtamt)
		            			{
		            				currbasic=currbasic+currincr;
		            				if(currbasic<=revscalestrtamt)
		            				{
		            					currbasic=revscalestrtamt;
		            					
		            					
		            				}
		            				else
		            				{
		            					for(n=1;;n++)
		            					{
		            						if(currbasic<=(revscalestrtamt+(n*revscaleincr)))
		            						{
		            							currbasic=revscalestrtamt+(n*revscaleincr);
		            							
		            						
		            							break;
		            						}
		            						else
		            						{
		            							if((currbasic)<=(revscalestrtamt+((n+1)*revscaleincr)) && currbasic>=(revscalestrtamt+(n*revscaleincr)))
		            							{
		            								currbasic=revscalestrtamt+((n+1)*revscaleincr);
		            								
		            								
		            								break;
		            							}
		            						}
		            								            						
		            					}
		            				}
		            					
		            			}
		            			else
		            			{
		            				if(currbasic>=revscalestrtamt)
		            				{
		            					currbasic=currbasic+currincr;
		            					for(n=1;;n++)
		            					{
		            						if(currbasic<=(revscalestrtamt+(n*revscaleincr)))
		            						{
		            							currbasic=revscalestrtamt+(n*revscaleincr);
		            							
		            							
		            							break;
		            						}
		            						else
		            						{
		            							if((currbasic)<=(revscalestrtamt+((n+1)*revscaleincr)) && currbasic>=(revscalestrtamt+(n*revscaleincr)))
		            							{
		            								currbasic=revscalestrtamt+((n+1)*revscaleincr);
		            								
		            								
		            								break;
		            							}
		            						}
		            						
		            					}		
		            				}
		            			}
		            			
		            			 String Payfixdate=PayfixDate.getDate()+"/"+(PayfixDate.getMonth()+1)+"/"+(PayfixDate.getYear()+1901);
		            			 Date payfix=StringUtility.convertStringToDate(Payfixdate);
		            			 reqdtls.setSysCompNxtIncrDate(payfix);
		            			 
		            			 reqdtls.setSysCompNewBasicSal(currbasic);
		            			
		            	 }
		            	 if (rule.equals("n"))
		            	 {
		            		 
		            		 long n=0;
		            		 for(n=0;;n++)
        					{
        						if(currbasic<=(revscalestrtamt+(n*revscaleincr)))
        						{
        							currbasic=revscalestrtamt+(n*revscaleincr);
        							
        						
        							break;
        						}
        						else
        						{
        							if((currbasic)<=(revscalestrtamt+((n+1)*revscaleincr)) && currbasic>=(revscalestrtamt+(n*revscaleincr)))
        							{
        								currbasic=revscalestrtamt+((n+1)*revscaleincr);
        								
        								
        								break;
        							}
        						}
        						
        							
        						
        					}
		            		 
		            		
		            		 String incrdate=date1.getDate()+"/"+(date1.getMonth()+1)+"/"+(date1.getYear()+1900);
		            		 Date payfix=StringUtility.convertStringToDate(incrdate);
	            			 reqdtls.setSysCompNxtIncrDate(payfix);
	            			 
	            			 reqdtls.setSysCompNewBasicSal(currbasic);
		            		 //InsertFixationDaoImpl insertfixationDao2 = new InsertFixationDaoImpl(HrEssTranPayFix.class, serv.getSessionFactory());
					           // insertfixationDao2.UpdateBasic(PayfixId,currbasic,incrdate);		            		
		            	 }
		            	 
		            	 PayFixationDao pay1=new PayFixationDaoImpl(HrModEmpRlt.class,serv.getSessionFactory());
		            	 pay1.create(hrmod);
		            	 PayFixationDao pay=new PayFixationDaoImpl(HrPayfixReqDtl.class,serv.getSessionFactory());
		            	 
		            	 pay.create(reqdtls);
		           		
		           		
		            	 // for work flow		            	 
		            	 
		            	// Map loginMap = (Map) objectArgs.get("baseLoginMap");
		     		/*	langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		     			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		     			OrgPostMstDaoImpl orgDaoImpl  = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		     			OrgPostMst orgPostMstVoFrom =  orgDaoImpl.getPrimaryPost(StringUtility.convertToLong(loginMap.get("userId").toString()), langId);
		     			objectArgs.put("Pkvalue",reqId);
		     			objectArgs.put("Docid", "300003");
		     			objectArgs.put("Hierarchy_ref_id", "300002"); 
		                objectArgs.put("loggedInPost", orgPostMstVoFrom.getPostId());
		                
		               
		     			objectArgs.put("DisplayJobTitle", reqId+"/payfixation");

		     		    WorkFlowDelegate.create(objectArgs);
		     		    WorkFlowDelegate.forward(objectArgs);
		     		    PayWF.getForwardToDetail(serv, objectArgs, "300003", reqId);
		     		    
		            	 
		            	 
		            	 /* end work flow
		            	 objectArgs.put("Pkvalue",reqId);//job ref id
			     			objectArgs.put("Docid","300003");
			     			//objectArgs.put("ToPostId", "2");
			     			objectArgs.put("FromPostId", orgPostMstVoFrom.getPostId());

                           objectArgs.put("ShortDescription", "payfixation");
//			     			objectArgs.put("LongDescription", "Description about Crime Modus Operandi....");
                            objectArgs.put("SendNotification", "Yes");
			     			Map MessageMap = new HashMap();
			     			MessageMap.put("$var1$","param 1");
//			     			MessageMap.put("$var2$","param 2");*/
		            //	 objectArgs.put("msg","Request With No."+ reqId+"/PayFix"+"  " +"     "+ " is submitted to "+objectArgs.get("fwdTo").toString());

//			     			TODO primary post of target user I have to pass from here
			     			

			     			//end work flow 
		            	 
		            	// end work flow
		            	 //objectArgs.put("RequestId",PayfixId);
		                 objRes.setResultCode(ErrorConstants.SUCCESS);
			             logger.info("The objectArgs is : "+objectArgs);
			             objRes.setResultValue(objectArgs);
			             objRes.setViewName("FileCreatedPayFix");
			                 
		            }
		        }
		    }
			      		  
			      		  catch (Exception e)
				            {
				             e.printStackTrace();
			      			    Map result = new HashMap();
				                 objRes.setResultValue(result);
				                 objRes.setResultCode(-1);
				                 objRes.setThrowable(e);
				                 objRes.setResultCode(ErrorConstants.ERROR);
				                 objRes.setThrowable(e);
				                 objRes.setViewName("errorPage");
				            }

			      		return objRes;
			     	  
		            }
		   
	   /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#PayfixByIncr(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#PayfixByIncr(java.util.Map)
	 */
	public ResultObject PayfixByIncr(Map objectArgs)
	{
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		   	Map voToService = (Map)objectArgs.get("voToServiceMap");
	        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	        Map   loginMap = (Map) objectArgs.get("baseLoginMap");
			 long EmpID = Long.parseLong(loginMap.get("empId").toString());
			 long userID = Long.parseLong(loginMap.get("userId").toString());
			 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			 long dbId = Long.parseLong(loginMap.get("dbId").toString());
			 long locId =  Long.parseLong(loginMap.get("locationId").toString());
			 long langId = Long.parseLong(loginMap.get("langId").toString());
			 
			 HrPayFixTxn hrtranpayfix =(HrPayFixTxn)objectArgs.get("hrtranpayfix");
		    long PayfixId=hrtranpayfix.getPayFixId();
		   	try
		    {
		        if (objRes != null && objectArgs != null)
		        {
		        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		           
		        	 if(hrtranpayfix!=null)
		               {
		        		 PayFixationDao insertfixationDao = new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		            	 List tranpayfix=insertfixationDao.Getalldatafromhrtrans(PayfixId);
		            	 HrPayFixTxn trans=new HrPayFixTxn();
		            	 trans=(HrPayFixTxn)tranpayfix.get(0);
		            	 String rule=trans.getPayFixation();
		            	 String rule1=trans.getPayFixationAtIncr();
		            	 long userid=trans.getUserId().getUserId();
		            	 PayFixationDao insertfixationDao1 = new PayFixationDaoImpl(OrgEmpMst.class, serv.getSessionFactory());
		            	long empidguj=insertfixationDao1.Getgujemp(userid);
		            	long empide= insertfixationDao1.Getalldatafromorgemp(empidguj);
		            	 
		            	 
		            	 
		            	 
		            	 PayFixationInfoDao GemEmpinfo = new PayFixationInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
		                    PayfixationEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(empide,langId,empidguj);
		                    long currbasic=EmpDetailsVO.getSalary();
		                    long desigid=EmpDetailsVO.getDesigid();
		                    PayFixationDao payfixationscale = new PayFixationDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
			            	 List empscale=payfixationscale.Scale(empide,langId);
			            	 HrEisScaleMst EmpScale=new HrEisScaleMst();
			            	 EmpScale=(HrEisScaleMst)empscale.get(0);
			            	 HrEisScaleMst scalems=new HrEisScaleMst();
			            	 scalems.setScaleId(EmpScale.getScaleId());
		                    long currincr=EmpScale.getScaleIncrAmt();
		            	
		            	 long revscalestrtamt=trans.getRevScaleId().getScaleStartAmt();
		            	 long revscaleincr=trans.getRevScaleId().getScaleIncrAmt();
		            	 Date PayfixDate=trans.getPayFixDate();
		            	 Date date2=trans.getIncrDate();
		            /*	 objectArgs.put("tablename", "HR_ESS_PAYFIX_REQ_DTLS");
		     			objectArgs.put("serviceLocator", serv);

		     			ResultObject resultObj = serv.executeService(
		     					"GENERATE_ID_SRVC", objectArgs);
		     			int receivedcode = resultObj.getResultCode();

		     			if (receivedcode == -1) {
		     				return objRes;
		     			}
		     			
		     			Map resultMap = (Map) resultObj.getResultValue();
		     			long reqId = (Long) resultMap.get("newID");*/
		            	 objectArgs.put("Subject","300003");//doc id
			     			OrgPostDetailsRltDaoImpl orgPostMstDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
			     			OrgPostDetailsRlt orgpostMst=orgPostMstDaoImpl.getPostDetailsRltByPostIdAndLangId(postId, langId);
			     			long branchid=orgpostMst.getCmnBranchMst().getBranchId();
			     			 objectArgs.put("Description","PayFixation Request");
			     			 objectArgs.put("section",branchid+"");//branch id
			     			 objectArgs.put("letterno","PFR");//Tri code
			     			 objectArgs.put("SubCode","10");
			     			PayFixationDao payfixdao = new PayFixationDaoImpl(WfDocMst.class, serv.getSessionFactory());
			    			long nxtpostId=payfixdao.getHigherHierachyPostId(postId+"",300003);
			    			if(nxtpostId!=0)
			    			{
			    			objectArgs.put("postIdForwardTo",nxtpostId);
			    			objectArgs.put("fileForwardFlag","yes");
			    			}
			     			
			     			 ResultObject objres1 = serv.executeService("CREATEFMSFILEVO", objectArgs);
			     			 Map resultMap = (Map) objres1.getResultValue();
			     			 String fileId=resultMap.get("fileId").toString();
			     			 long reqId=Long.parseLong(fileId);
		            	 HrPayfixReqDtl reqdtls=new HrPayfixReqDtl();
		            	 HrModEmpRlt hrmod=new HrModEmpRlt();
		            	 hrmod.setReqId(reqId);
		            	 
		            	 reqdtls.setReqId(reqId);
		            	 long a=2;
		            	 reqdtls.setReqType(a);
		            	
		            	 hrmod.setUserId(userid);
		            	 hrmod.setBasicSal(currbasic);
		            	 hrmod.setScaleId(scalems);
		            	 HrModMst hrModMst=new HrModMst();
		            	 hrModMst.setModId(1000);
		            	 hrmod.setHrModMst(hrModMst);
		            	 OrgDesignationMst desid=new OrgDesignationMst();
		            	 desid.setDsgnId(desigid);
		            	 hrmod.setDsgnId(desid);
		            	 hrmod.setCreatedBy(userID);
		            	 OrgUserMst user=new OrgUserMst();
		            	 user.setUserId(userID);
		            	 reqdtls.setCreatedBy(user);
		            	 hrmod.setCreatedByPost(postId);
		            	 OrgPostMst post=new OrgPostMst();
		            	 post.setPostId(postId);
		            	 
		            	 reqdtls.setCreatedByPost(post);
		            	 reqdtls.setCompFlag("s");
		            	 Date dt=new Date();
		     			int day=dt.getDate();
		     			int mont=dt.getMonth()+1;
		     			int yr=dt.getYear()+1900;
		     			String dt1=day+"/"+mont+"/"+yr;
		     			Date appdate=StringUtility.convertStringToDate(dt1);
		     			hrmod.setCreatedDate(appdate);
		     			CmnDatabaseMst databaseMst=new CmnDatabaseMst();
			      		databaseMst.setDbId(dbId);
			      		//HrEssTranPayFix.setDbId(databaseMst);
			      		CmnLocationMst cmnLocationMst=new CmnLocationMst();
			      		cmnLocationMst.setLocId(locId);
		     			reqdtls.setDbId(databaseMst);
		     			reqdtls.setLocId(cmnLocationMst);
		     			reqdtls.setCreatedDate(appdate);
		     			OrgUserMst user1=new OrgUserMst();
		     			user1.setUserId(userid);
		            	 reqdtls.setUserId(user1);
		            	 HrPayFixTxn hrEssTranPayFix=new HrPayFixTxn();
		            	 hrEssTranPayFix.setPayFixId(PayfixId);
		            	 reqdtls.setHrPayFixTxn(hrEssTranPayFix);
		            	 reqdtls.setApprove("p");
		            	 
		            	 long n=1;
		            		 currbasic=currbasic+currincr;
		            			if(currbasic<revscalestrtamt)
		            			{
		            				currbasic=currbasic+currincr;
		            				if(currbasic<=revscalestrtamt)
		            				{
		            					currbasic=revscalestrtamt;
		            					
		            					
		            				}
		            				else
		            				{
		            					for(n=1;;n++)
		            					{
		            						if(currbasic<=(revscalestrtamt+(n*revscaleincr)))
		            						{
		            							currbasic=revscalestrtamt+(n*revscaleincr);
		            							
		            						    break;
		            						}
		            						else
		            						{
		            							if((currbasic)<=(revscalestrtamt+((n+1)*revscaleincr)) && currbasic>=(revscalestrtamt+(n*revscaleincr)))
		            							{
		            								currbasic=revscalestrtamt+((n+1)*revscaleincr);
		            								
		            								break;
		            							}
		            						}	
		            					}
		            				}	
		            			}
		            			else
		            			{
		            				if(currbasic>=revscalestrtamt)
		            				{
		            					currbasic=currbasic+currincr;
		            					for(n=1;;n++)
		            					{
		            						if(currbasic<=(revscalestrtamt+(n*revscaleincr)))
		            						{
		            							currbasic=revscalestrtamt+(n*revscaleincr);
		            							
		            							break;
		            						}
		            						else
		            						{
		            							if((currbasic)<=(revscalestrtamt+((n+1)*revscaleincr)) && currbasic>=(revscalestrtamt+(n*revscaleincr)))
		            							{
		            								currbasic=revscalestrtamt+((n+1)*revscaleincr);
		            								
		            								break;
		            							}
		            						}
		            						
		            					}		
		            				}
		            			}
		            			
			            		 String incrdate=date2.getDate()+"/"+(date2.getMonth()+1)+"/"+(date2.getYear()+1901);
			            		 Date payfix=StringUtility.convertStringToDate(incrdate);
		            			 reqdtls.setSysCompNxtIncrDate(payfix);
		            			 
		            			 reqdtls.setSysCompNewBasicSal(currbasic);
		            			 PayFixationDao pay1=new PayFixationDaoImpl(HrModEmpRlt.class,serv.getSessionFactory());
				            	 pay1.create(hrmod);
				            	 PayFixationDao pay=new PayFixationDaoImpl(HrPayfixReqDtl.class,serv.getSessionFactory());
				            	 
				            	 pay.create(reqdtls);
				           		
		            			
				            
				     			

				     			
			            	 
			            	// objectArgs.put("RequestId",PayfixId);
			                 objRes.setResultCode(ErrorConstants.SUCCESS);
				             logger.info("The objectArgs is : "+objectArgs);
				             objRes.setResultValue(objectArgs);
				             objRes.setViewName("FileCreatedPayFix");
				                
		            }
		        }
		    }
			      		  
			      		  catch (Exception e)
				            {
			      			  e.printStackTrace();
			      			   Map result = new HashMap();
				                 objRes.setResultValue(result);
				                 objRes.setResultCode(-1);
				                 objRes.setThrowable(e);
				                 objRes.setResultCode(ErrorConstants.ERROR);
				                 objRes.setThrowable(e);
				                 objRes.setViewName("errorPage");
				            }
			      		return objRes;
		            }
		   
	   /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#PayfixAtPayFixDate(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#PayfixAtPayFixDate(java.util.Map)
	 */
	public ResultObject PayfixAtPayFixDate(Map objectArgs) {

		   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		   	
	        Map voToService = (Map)objectArgs.get("voToServiceMap");
	        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	        //long userID = Long.parseLong(loginMap.get("userID").toString());
	       long reqId= Long.parseLong((objectArgs.get("reqid").toString()));
	         
		   try
		    {
		       ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		            
		            
                        long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
                        
                        PayFixationDao insertfixationDao = new PayFixationDaoImpl(HrPayfixReqDtl.class, serv.getSessionFactory());
		            	List tranpayfix=insertfixationDao.Getalldatafromhrpayreqdtls(reqId);
		            	HrPayfixReqDtl transf=new HrPayfixReqDtl();
		            	transf=(HrPayfixReqDtl)tranpayfix.get(0);
		            	
		            	long userid=transf.getUserId().getUserId();
		            	long reqid=transf.getReqId();
		            	PayFixationDao insertfixati= new PayFixationDaoImpl(HrModEmpRlt.class, serv.getSessionFactory());
		            	List act=insertfixati.Getalldatafromhrmodemprlt(reqid);
		            	HrModEmpRlt mod=(HrModEmpRlt)act.get(0);
		            	PayFixationDao insertfixationDao1 = new PayFixationDaoImpl(OrgEmpMst.class, serv.getSessionFactory());
		            	long empidguj=insertfixationDao1.Getgujemp(userid);
		            	long empide= insertfixationDao1.Getalldatafromorgemp(empidguj);
		            //long currbasic=transf.getHrModEmpRlt().getBasicSal();
		            	long currbasic=mod.getBasicSal();
		            long scaleid=transf.getHrPayFixTxn().getRevScaleId().getScaleId();
		            long revscal=transf.getHrPayFixTxn().getRevScaleId().getScaleStartAmt();
		            //long scaleamt=transf.getHrModEmpRlt().getScaleId().getScaleStartAmt();
		            long scaleamt=mod.getScaleId().getScaleStartAmt();
		            String compflag=transf.getCompFlag();
		            String approve=transf.getApprove();
		            Date nxtincrdate=transf.getUserCompNxtIncrDate();
		            if(nxtincrdate!=null)
		            {
		            	String date=nxtincrdate+" 00:00:00";
				           
				            	
					            objectArgs.put("nxtdate",date);
		            }
		             
		            	long reqtype=transf.getReqType();
		            	
		            	
		            	
		            	if(reqtype==1)
		            	{
		            		Date payfixdate=transf.getHrPayFixTxn().getPayFixDate();
		            		objectArgs.put("date",payfixdate);
		            	}
		            	if(reqtype==2)
		            	{
		            		Date payfixdate=transf.getHrPayFixTxn().getIncrDate();
		            		objectArgs.put("date",payfixdate);
		            	}
		            	objectArgs.put("trans",transf);
		            	objectArgs.put("modgs",mod);
		            	 
		                       PayFixationInfoDao GemEmpinfo = new PayFixationInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
		                    PayfixationEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(empide,langId,empidguj);
			            	
		                    objectArgs.put("EmpDet",EmpDetailsVO);
		            	 
		                   
		                 	
		            	objRes.setResultCode(ErrorConstants.SUCCESS);
			               
			              objRes.setResultValue(objectArgs);
			              objRes.setViewName("Payfix");
		                 
		             }
			      		  catch (Exception e)
				            {
				                 e.printStackTrace();
			      			     Map result=new HashMap();
				                 objRes.setResultValue(result);
				                 objRes.setResultCode(-1);
				                 objRes.setThrowable(e);
				                 objRes.setResultCode(ErrorConstants.ERROR);
				                 objRes.setViewName("errorPage");
				            }
			      		return objRes;
		            } 
	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#InsertDetail(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#InsertDetail(java.util.Map)
	 */
	public ResultObject InsertDetail(Map objectArgs) {

	   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	   	HrPayFixTxn HrEssTranPayFix=(HrPayFixTxn)objectArgs.get("Transaction");
	  
	   	
	   	
	   
	    /* login code */
	   Map   loginMap = (Map) objectArgs.get("baseLoginMap");
			 long EmpID = Long.parseLong(loginMap.get("empId").toString());
			 long userID = Long.parseLong(loginMap.get("userId").toString());
			 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			 long dbId = Long.parseLong(loginMap.get("dbId").toString());
			 long locId =  Long.parseLong(loginMap.get("locationId").toString());
			 long langId = Long.parseLong(loginMap.get("langId").toString());

	   	try
	    {
	        if (objRes != null && objectArgs != null)
	        {
	        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	           
	        	


	            if(HrEssTranPayFix!=null){
	            	
	            	 
	            	
	            	
	      		
	      		objectArgs.put("tablename", "hr_payfix_txn");
				objectArgs.put("serviceLocator", serv);

				ResultObject resultObj = serv.executeService(
						"GENERATE_ID_SRVC", objectArgs);
				int receivedcode = resultObj.getResultCode();

				if (receivedcode == -1) {
					return objRes;
				}
				
				Map resultMap = (Map) resultObj.getResultValue();
				long PayfixId = (Long) resultMap.get("newID");
				
				long userid=HrEssTranPayFix.getUserId().getUserId();
				
				HrEssTranPayFix.setPayFixId(PayfixId);
				HrEssTranPayFix.setEmplResp("n");
				OrgUserMst user=new OrgUserMst();
				user.setUserId(userID);
	      		HrEssTranPayFix.setCreatedBy(user);
	      		OrgPostMst post=new OrgPostMst();
	      		post.setPostId(postId);
	      		HrEssTranPayFix.setCreatedByPost(post);
	      		Date dt=new Date();
				int day=dt.getDate();
				int mont=dt.getMonth()+1;
				int yr=dt.getYear()+1900;
				String dt1=day+"/"+mont+"/"+yr;
				Date appdate=StringUtility.convertStringToDate(dt1);
	      		HrEssTranPayFix.setCreatedDate(appdate);
	      		CmnDatabaseMst databaseMst=new CmnDatabaseMst();
	      		databaseMst.setDbId(dbId);
	      		HrEssTranPayFix.setDbId(databaseMst);
	      		CmnLocationMst cmnLocationMst=new CmnLocationMst();
	      		cmnLocationMst.setLocId(locId);
	      		HrEssTranPayFix.setLocId(cmnLocationMst);
	      		PayFixationDao insertfixationDaof = new PayFixationDaoImpl(HrEisPayInc.class, serv.getSessionFactory());
	       	 List  hresstranpay=insertfixationDaof.Getalldatafromhrpayincr(userid);
	       	 HrEisPayInc transf1=new HrEisPayInc();
	       	 transf1=(HrEisPayInc)hresstranpay.get(0);
	   		 Date date1=transf1.getNextIncDt();
	   		HrEssTranPayFix.setIncrDate(date1);
	   		
	      		
	      		
	               
	      		PayFixationDao insertDao=new PayFixationDaoImpl(HrPayFixTxn.class,serv.getSessionFactory());      		
	      		
	      		insertDao.create(HrEssTranPayFix);
	                 	objRes.setResultCode(ErrorConstants.SUCCESS);
	                 	 //PayFixationInfoDaoImpl GemEmpinfo = new PayFixationInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
	                   // PayfixationEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(HrEssTranPayFix.getOrgEmpMst().getEmpId(),langId);
	                 	//String name=HrEssTranPayFix.getOrgEmpMst().getEmpPrefix()+HrEssTranPayFix.getOrgEmpMst().getEmpFname()+HrEssTranPayFix.getOrgEmpMst().getEmpMname()+HrEssTranPayFix.getOrgEmpMst().getEmpLname();
	                 	PayFixationDao gene=new PayFixationDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
	                 	
	                 	List name=gene.Getorgempbyuserid(userid,langId);
	                 	OrgEmpMst org=(OrgEmpMst)name.get(0);
	                 	String name1=org.getEmpPrefix()+""+org.getEmpFname()+""+org.getEmpMname()+""+org.getEmpLname();
	                 	
	                 	objectArgs.put("Name",name1);
	                 	objectArgs.put("empid",org.getEmpId());	
		                
		                objRes.setResultValue(objectArgs);
		                objRes.setViewName("RequestGenerated");
	                 
	            }
	        }
	    }
		      		  
		      		  catch (Exception e)
			            {
			                
			            	e.printStackTrace();
			                 Map result = new HashMap();
			                 objRes.setResultValue(result);
			                 objRes.setResultCode(-1);
			                 objRes.setThrowable(e);
			                 objRes.setResultCode(ErrorConstants.ERROR);
			                 objRes.setThrowable(e);
			                 objRes.setViewName("errorPage");
			            }

		      		return objRes;
		     	  
	            }
	   /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#FixationDetail(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#FixationDetail(java.util.Map)
	 */
	public ResultObject FixationDetail(Map objectArgs)
		{
			
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map   loginMap = (Map) objectArgs.get("baseLoginMap");
			 
			
			try
			{
				 if (objectArgs != null)
			        {
				
				   long empid = Long.parseLong(loginMap.get("empId").toString());
				   long userID = Long.parseLong(loginMap.get("userId").toString());
				 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
				 long dbId = Long.parseLong(loginMap.get("dbId").toString());
				 long locId =  Long.parseLong(loginMap.get("locationId").toString());
				 long langId = Long.parseLong(loginMap.get("langId").toString());
				 
				
				 HrPayFixTxn tran=null;
		      		List <HrPayFixTxn> fixdate=new ArrayList();
		      			List reason=new ArrayList();
		      			PayFixationDao insertDao1 = new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		      		List emp=insertDao1.Fixation(userID,langId);
		      		if(emp.size()!=0)
		      		{
		      		 tran=new HrPayFixTxn();
		      		tran=(HrPayFixTxn)emp.get(0);
		      		fixdate.add(tran);
		      		
		      		String reasonpayname=tran.getCmnLookupMst().getLookupName();
		      		
		      		CmnLookupMstDAO cmnLookupMstDAO=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		      		CmnLookupMst cmnLookupMst=cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(reasonpayname, langId);
		      		
			      		
		      		reason.add(cmnLookupMst.getLookupDesc());
		      		
			      		
			      		
		      		}
		      		if(emp.size()!=0)
		      		{
		      			objectArgs.put("EmpfixList",fixdate);
		      			objectArgs.put("reasonfixList",reason);
		      		}
		      	
		      		
		      		
		      		objRes.setResultValue(objectArgs);
		      		objRes.setResultCode(ErrorConstants.SUCCESS);		
		      		objRes.setViewName("Fixation");  
			        }
		      		
	}
			catch(Exception e)
			{
				
				e.printStackTrace();
				
	             objRes.setResultCode(-1);
	             objRes.setThrowable(e);
	             objRes.setResultCode(ErrorConstants.ERROR);
	            
	             objRes.setViewName("errorPage");
			}
		
			return objRes;
		}
	   /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Employeeselecteddate(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Employeeselecteddate(java.util.Map)
	 */
	public ResultObject Employeeselecteddate(Map objectArgs) {

		   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		   	HrPayFixTxn HrEssTranPayFix=(HrPayFixTxn)objectArgs.get("hrTrans");
		    
		   	
		   	

			
			long payfixid=HrEssTranPayFix.getPayFixId();
			String date=HrEssTranPayFix.getPayFixation();
			
			
			
		    /* login code */
		   Map   loginMap = (Map) objectArgs.get("baseLoginMap");
				 long EmpID = Long.parseLong(loginMap.get("empId").toString());
				 long userID = Long.parseLong(loginMap.get("userId").toString());
				 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
				 long dbId = Long.parseLong(loginMap.get("dbId").toString());
				 long locId =  Long.parseLong(loginMap.get("locationId").toString());
				 long langId = Long.parseLong(loginMap.get("langId").toString());

		   	try
		    {
		        if (objRes != null && objectArgs != null)
		        {
		        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		           
		        	
	                   if(HrEssTranPayFix!=null){
		            	if(date.equals("1"))
			      			
			      		{
		            		PayFixationDao insertDao = new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		      		          insertDao.UpdateRulepayincrdate(payfixid);
		      		         
			      		}
		            	else if(date.equals("0"))
		            	{
		            		PayFixationDao insertDao = new PayFixationDaoImpl(HrPayFixTxn.class, serv.getSessionFactory());
		      		          insertDao.UpdateRulepayfixdate(payfixid);
		      		         
		            	}
		                 	objRes.setResultCode(ErrorConstants.SUCCESS);
		                 	
			                
			                objRes.setResultValue(objectArgs);
			                objRes.setViewName("OptionDateselected");
		                 
		            }
		        }
		    }
			      		   catch (Exception e)
				            {
				            	e.printStackTrace();
				                 Map result = new HashMap();
				                 objRes.setResultValue(result);
				                 objRes.setResultCode(-1);
				                 objRes.setThrowable(e);
				                 objRes.setResultCode(ErrorConstants.ERROR);
				                 objRes.setViewName("errorPage");
				            }

			      		return objRes;
			     	  
		            }

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#InsertDetailinMst(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#InsertDetailinMst(java.util.Map)
	 */
	public ResultObject ApprovePayFix(Map objectArgs) {

	   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	  
	   
	   
	  
	    /* login code */
	   Map   loginMap = (Map) objectArgs.get("baseLoginMap");
			 long EmpID = Long.parseLong(loginMap.get("empId").toString());
			 long userID = Long.parseLong(loginMap.get("userId").toString());
			 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			 long dbId = Long.parseLong(loginMap.get("dbId").toString());
			 long locId =  Long.parseLong(loginMap.get("locationId").toString());
			 long langId = Long.parseLong(loginMap.get("langId").toString());

	   	try
	    {
	        if (objRes != null && objectArgs != null)
	        {/*
	        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	           
	        	


	            String fileid=objectArgs.get("fileId").toString();
	            
	            long reqid=Long.parseLong(fileid);
	             
	        			PayFixationDao insertDao = new PayFixationDaoImpl(HrPayfixReqDtl.class, serv.getSessionFactory());
	        	          insertDao.Updateapprovalcomp(reqid);
	        		
	    				
	    			
	            	 
	            	//long reqid=hrEssPayfixReqDtls.getReqId();
	            	PayFixationDao insertfixationDao = new PayFixationDaoImpl(HrPayfixReqDtl.class, serv.getSessionFactory());
	            	List tranpayfix=insertfixationDao.Getalldatafromhrpayreqdtls(reqid);
	            	HrPayfixReqDtl transf=new HrPayfixReqDtl();
	            	transf=(HrPayfixReqDtl)tranpayfix.get(0);
	            	long reqtype=transf.getReqType();
	            	long a=1;
	            	long b=2;
	              
	       			PayFixationDao insertDao1=new PayFixationDaoImpl(HrPayfixMst.class,serv.getSessionFactory());
	       			List  resultpayfixMst=insertDao1.Resultwhetherempexists(transf.getUserId().getUserId());
	       			
	       			for (Iterator iter = resultpayfixMst.iterator(); iter.hasNext();) {
	       				HrPayfixMst element = (HrPayfixMst) iter.next();
	       				PayFixationDao UpdatepayfixMst=new PayFixationDaoImpl(HrPayfixMst.class,serv.getSessionFactory());
	       				HrPayfixMst hrPayfixMst=(HrPayfixMst)UpdatepayfixMst.read(element.getPayfixId());
	       				Character flag='N';
	       				hrPayfixMst.setFlagType(flag);
	       				UpdatepayfixMst.update(hrPayfixMst);
					}
	       			
	       			
	       			
	       			
	       		      long payFixId = IDGenerateDelegate.getNextId("hr_payfix_mst", objectArgs);
	       				
	       				HrPayfixMst payfixmst=new HrPayfixMst();
		            	 
	       				payfixmst.setPayfixId(payFixId);
	       				
	       				long reqId=transf.getReqId();
	       				//PayFixationDao insertDao2=new PayFixationDaoImpl(HrPayfixMst.class,serv.getSessionFactory());
		            	   PayFixationDao insertfixati= new PayFixationDaoImpl(HrModEmpRlt.class, serv.getSessionFactory());
			            	List act=insertfixati.Getalldatafromhrmodemprlt(reqid);
			            	HrModEmpRlt mod=(HrModEmpRlt)act.get(0);
		            	   payfixmst.setUserId(transf.getUserId());
		            	   payfixmst.setPayFixDate(transf.getHrPayFixTxn().getPayFixDate());
		            	 // String prevPayScale=transf.getHrModEmpRlt().getScaleId().getScaleStartAmt()+"-"+transf.getHrModEmpRlt().getScaleId().getScaleIncrAmt()+"-"+transf.getHrModEmpRlt().getScaleId().getScaleEndAmt();
		            	   String prevPayScale=mod.getScaleId().getScaleStartAmt()+"-"+mod.getScaleId().getScaleIncrAmt()+"-"+mod.getScaleId().getScaleEndAmt();
		            	  // String prevPayScale=transf.getHrModEmpRlt().getScaleId().getScaleId()+"";
		            	   
		            	   payfixmst.setPrevPayScale(mod.getScaleId());
		            	   //payfixmst.setPrevPay(transf.getHrModEmpRlt().getBasicSal());
		            	   payfixmst.setPrevPay(mod.getBasicSal());
		            	  // String revPayScale=transf.getHrEssTranPayFix().getRevScaleId().getScaleStartAmt()+"-"+transf.getHrEssTranPayFix().getRevScaleId().getScaleIncrAmt()+"-"+transf.getHrEssTranPayFix().getRevScaleId().getScaleEndAmt();
		            	  // String revPayScale=transf.getHrEssTranPayFix().getRevScaleId().getScaleId()+"";
		            	   payfixmst.setRevPayScale(transf.getHrPayFixTxn().getRevScaleId());
		            	   
		            	   CmnLookupMst cmnLookupMst=new CmnLookupMst();
		            	   cmnLookupMst.setLookupId(transf.getHrPayFixTxn().getCmnLookupMst().getLookupId());
		            	   payfixmst.setCmnLookupMst(cmnLookupMst);
		            	   String compflag=transf.getCompFlag();
		            	   if(compflag.equalsIgnoreCase("s"))
		            	   {
		            		   payfixmst.setRevPay(transf.getSysCompNewBasicSal());
		            		   payfixmst.setNxtIncrDate(transf.getSysCompNxtIncrDate());
		            		   payfixmst.setRemarks("");
		            		   PayFixationDao insertDaoPayfix=new PayFixationDaoImpl(HrEisPayInc.class,serv.getSessionFactory());
		            		   insertDaoPayfix.UpdateIncrdateinHrPayIncr(transf.getUserId().getUserId(),transf.getSysCompNxtIncrDate());
		            	   
		            	   }
		            	   if(compflag.equalsIgnoreCase("u"))
		            	   {
		            		   payfixmst.setRevPay(transf.getUserCompNewBasicSal());
		            		   payfixmst.setNxtIncrDate(transf.getUserCompNxtIncrDate());
		            		   payfixmst.setRemarks(transf.getUserRemarks());
		            		   PayFixationDao insertDaoPayfix=new PayFixationDaoImpl(HrEisPayInc.class,serv.getSessionFactory());
		                	   insertDaoPayfix.UpdateIncrdateinHrPayIncr(transf.getUserId().getUserId(),transf.getUserCompNxtIncrDate());
		            	   }
		            	   CmnLanguageMst cmnLanguageMst=new CmnLanguageMst();
		            	   cmnLanguageMst.setLangId(langId);
		            	   payfixmst.setCmnLanguageMst(cmnLanguageMst);
		            	   CmnDatabaseMst cmnDatabaseMst=new CmnDatabaseMst();
		            	   cmnDatabaseMst.setDbId(dbId);
		            	   payfixmst.setCmnDatabaseMst(cmnDatabaseMst);
		            	   CmnLocationMst cmnLocationMst=new CmnLocationMst();
		            	   cmnLocationMst.setLocId(locId);
		            	   payfixmst.setCmnLocationMst(cmnLocationMst);
		            	   OrgUserMst userMst = new OrgUserMst();
		            	   userMst.setUserId(userID);
		            	   payfixmst.setOrgUserMstCreatedBy(userMst);
		            	   
		            	   OrgPostMst postMst = new OrgPostMst();
		            	   postMst.setPostId(postId);
		            	   payfixmst.setOrgPostMstCreatedByPost(postMst);
		            	   Date dt=new Date();
		            	   payfixmst.setFlagType('Y');
		       			payfixmst.setCreatedDate(dt);
		       		 PayFixationDao insertPayfix=new PayFixationDaoImpl(HrEisPayInc.class,serv.getSessionFactory());
		       		insertPayfix.create(payfixmst);
	       			
	               
//	             
	               objectArgs.put("reqid",reqid);
	                 	objRes.setResultCode(ErrorConstants.SUCCESS);
	                 	
		                logger.info("The objectArgs is : "+objectArgs);
		                objRes.setResultValue(objectArgs);
		              
	            
	        */}
	    }
		      		  
		      		  catch (Exception e)
			            {
			                
			            	e.printStackTrace();
			                 Map result = new HashMap();
			                 objRes.setResultValue(result);
			                 objRes.setResultCode(-1);
			                 objRes.setThrowable(e);
			                 objRes.setResultCode(ErrorConstants.ERROR);
			                 
			                 objRes.setViewName("errorPage");
			            }

		      		return objRes;
		     	  
	            }
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Reject(java.util.Map)
	 */
	public ResultObject Reject(Map objectArgs)
	 {
		 	
		 	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			 Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			 long reqid=Long.parseLong(objectArgs.get("reqid").toString());
			
				try
				{
                       
					PayFixationDao insertDao = new PayFixationDaoImpl(HrPayfixReqDtl.class, serv.getSessionFactory());
					insertDao.Updatereject(reqid);
					        resultObject.setResultValue(objectArgs);
				            resultObject.setResultCode(ErrorConstants.SUCCESS);		
				            objRes.setViewName("Reject");
		}
				catch(Exception e)
				{
					e.printStackTrace();
					objRes.setResultCode(-1);
					 
				}
            return resultObject; 
	 }
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Forward(java.util.Map)
	 */
	public ResultObject SubmitPayfixation(Map objectArgs)
	 {
		 	
		 	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			 Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			 HrPayfixReqDtl payfxdtls=(HrPayfixReqDtl)objectArgs.get("hrpayfixdtls");
			 String value1=objectArgs.get("value1").toString();
				try
				{
					if(value1.equalsIgnoreCase("2"))
					{
						
						PayFixationDao insertDao = new PayFixationDaoImpl(HrPayfixReqDtl.class, serv.getSessionFactory());
						insertDao.Updateforwardusercomp(payfxdtls.getUserCompNewBasicSal(),payfxdtls.getUserCompNxtIncrDate(),payfxdtls.getUserRemarks(),payfxdtls.getReqId());
					}
					else
					{
						PayFixationDao insertDao = new PayFixationDaoImpl(HrPayfixReqDtl.class, serv.getSessionFactory());
						HrPayfixReqDtl hressPayfixReqDtls=(HrPayfixReqDtl)insertDao.read(payfxdtls.getReqId());
						hressPayfixReqDtls.setCompFlag("s");
						hressPayfixReqDtls.setUserCompNewBasicSal(null);
						hressPayfixReqDtls.setUserCompNxtIncrDate(null);
						hressPayfixReqDtls.setUserRemarks("");
						insertDao.update(hressPayfixReqDtls);
					}
					long reqId=payfxdtls.getReqId();
					
					Map redirectMap = new HashMap();
					redirectMap.put("actionFlag", "searchpayfixdate");				
					redirectMap.put("fileId", reqId);
					objRes.setResultCode(ErrorConstants.SUCCESS);
					objectArgs.put("redirectMap", redirectMap);
					objRes.setResultValue(objectArgs);
					objRes.setViewName("redirect view");
					
					
		}
				catch(Exception e)
				{
					e.printStackTrace();
					objRes.setResultCode(-1);
					 
				}
           return objRes; 
	 }
}
