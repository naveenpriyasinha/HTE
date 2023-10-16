package com.tcs.sgv.pdpla.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.pdpla.dao.PDPLAChallanDataDAOImpl;
import com.tcs.sgv.pdpla.dao.PDPLAChqDataDAOImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallan;
import com.tcs.sgv.pdpla.valueobject.TrnPdChq;

public class PDPLADataServiceImpl extends ServiceImpl implements PDPLADataService
{
   Log logger = LogFactory.getLog(getClass());
   HashMap hashReturn=new HashMap();
   HashMap hashReturnChallan = new HashMap();
   HashMap hashReturnChq = new HashMap();
   ArrayList  lArrayLstChallanVO = new ArrayList();
   ArrayList  lArrayLstChallanVO_DAO = new ArrayList();
   ArrayList  lArrayLstChqVO = new ArrayList();
   ArrayList  lArrayLstChqVO_DAO = new ArrayList();
	
	  
   Log glogger = LogFactory.getLog(getClass());
	
	
   public HashMap insertChallan(Map inputMap)
	  
	 {
		     Map map = null;
		     HttpServletRequest request = null;
				  
		 try
		    {
			    if(inputMap != null)
			     {
				     logger.info("-----------------------insertChallan function of PDPLADataServiceImpl starts--------------------");
				     map = (HashMap)inputMap.get("map");
					 request = (HttpServletRequest)map.get("requestObj");
					 ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
					 PDPLAChallanDataDAOImpl lObjChallanDataDAO = new PDPLAChallanDataDAOImpl (TrnPdChallan.class, serv.getSessionFactory());
					 
					
			
                      if(map.containsKey("serviceLocator"))
					    {
					        if(inputMap.containsKey("TrnPdChallanVOArrlst"))
						      {
						             lArrayLstChallanVO = (ArrayList)inputMap.get("TrnPdChallanVOArrlst");
								     logger.info("The sizeof ChallanVo is ------------------------> "+ lArrayLstChallanVO.size());

								       for(int i =0; i < lArrayLstChallanVO.size();i++ )
								        {
									      TrnPdChallan lObjChallanData = (TrnPdChallan )lArrayLstChallanVO.get(i);
									      long challanId = BptmCommonServiceImpl.getNextSeqNum("TRN_PD_CHALLAN", map);
									      lObjChallanData.setChallanId(challanId);							
									      lArrayLstChallanVO_DAO.add(lObjChallanData);
								        }
								     hashReturnChallan =lObjChallanDataDAO.insertVO(lArrayLstChallanVO_DAO);
							       
						         
							    hashReturn.put("flag", "true");
							    hashReturn.put("Status", "Successful Transaction");
							    hashReturn.put("voName", "TrnPdChallan");
						      }
					    }
					else
					  {
					    hashReturn.put("flag", "false");
						hashReturn.put("Status", "Data is not Sufficient in Map");
						hashReturn.put("voName", "TrnPdChallan");
					  }
				
                      
			     }     
                
			    else
   				  {
   					hashReturn.put("flag", "false");
   					hashReturn.put("Status", "Map is Null");
   					hashReturn.put("voName", "TrnPdChallan");
   				   }
   				
   				 
   				 
   			   return hashReturn;
   			  
			}
   			
		 catch(Exception e)
   			{
   				logger.error("This is Error: -", e);
   				hashReturn.put("flag", "false");
   				hashReturn.put("Status", e);
   				hashReturn.put("voName", " ");
   				return hashReturn;
   			}

		  
     }				    

		 
	
   public HashMap insertChq(Map inputMap)
		  
		 {
			     Map map = null;
			     HttpServletRequest request = null;
					  
			 try
			    {
				    if(inputMap != null)
				     {
					     logger.info("-----------------------insertChq function of PDPLADataServiceImpl starts--------------------");
					     map = (HashMap)inputMap.get("map");
						 request = (HttpServletRequest)map.get("requestObj");
						 ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
						 TrnPdChq TrnPdChqVO = (TrnPdChq)inputMap.get("TrnPdChqVO");
						 
						 PDPLAChqDataDAOImpl lObjChqDataDAO = new PDPLAChqDataDAOImpl (TrnPdChq.class, serv.getSessionFactory());
						
				
	                      if(map.containsKey("serviceLocator"))
						    {
						        if(inputMap.containsKey("TrnPdChqVOArrlst"))
							      {
							         
	 							         lArrayLstChqVO = (ArrayList)inputMap.get("TrnPdChqVOArrlst");
									     logger.info("The sizeof ChqVo is ------------------------> "+ lArrayLstChqVO.size());

									       for(int i =0; i < lArrayLstChqVO.size();i++ )
									        {
										      TrnPdChq lObjChqData = (TrnPdChq )lArrayLstChqVO.get(i);
										      long chqId = BptmCommonServiceImpl.getNextSeqNum("TRN_PD_CHQ", map);
										      lObjChqData.setChqId(chqId);							
										      lArrayLstChqVO_DAO.add(lObjChqData);
									        }
									     hashReturnChq =lObjChqDataDAO.insertVO(lArrayLstChqVO_DAO);
								     
							         
								    hashReturn.put("flag", "true");
								    hashReturn.put("Status", "Successful Transaction");
								    hashReturn.put("voName", "TrnPdChq");
							      }
						    }
						else
						  {
						    hashReturn.put("flag", "false");
							hashReturn.put("Status", "Data is not Sufficient in Map");
							hashReturn.put("voName", "TrnPdChq");
						  }
				     }	
	                else
	   				  {
	   					hashReturn.put("flag", "false");
	   					hashReturn.put("Status", "Map is Null");
	   					hashReturn.put("voName", "TrnPdChq");
	   				 }
	   				
	   				 
	   				 
	   			   return hashReturn;
	   			  
				}
	   			
			 catch(Exception e)
	   			{
	   				logger.error("This is Error: -", e);
	   				hashReturn.put("flag", "false");
	   				hashReturn.put("Status", e);
	   				hashReturn.put("voName", " ");
	   				return hashReturn;
	   			}

	  }				    		 		 
		 
		 		 
		 
		 
}					         
	