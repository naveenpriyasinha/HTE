package com.tcs.sgv.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.OrgTicketMst;
import com.tcs.sgv.common.valueobject.OrgTicketMstHistory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.dao.ChangeDetailsDaoImpl;

public class PRTrackingDAOImpl extends GenericDaoHibernateImpl implements PRTrackingDAO
{
    Logger logger = Logger.getLogger(PRTrackingDAOImpl.class);
    Session hibSession = null;

    public PRTrackingDAOImpl(Class type, SessionFactory sessionFactory)
    {
        super(type);
        this.hibSession = sessionFactory.getCurrentSession();
        this.setSessionFactory(sessionFactory);
    }

    public List getScreenMaster()
    {

        List screenName = null;
        final StringBuilder lSBQuery = new StringBuilder();
        final Session session = this.getSession();
        lSBQuery.append(" select screen_id,screen_desc from mst_screen_desc where activate_flag = 1 order by screen_desc");
        final Query lQuery = session.createSQLQuery(lSBQuery.toString());
        this.logger.info("lSBQuery.toString()----------" + lSBQuery.toString());
        screenName = lQuery.list();
        return screenName;

    }

    public void insertTicketMst(final String title, final Long screen, final String desc, final String credentials,
            final Long contactNo, final String emailId, final String  remarks, final String ddoCode, Date acknowledgeDate, final Map inputMap,Long fileId)
    throws Exception
    {
        Long lLngTicketId = null;
        try
        {
            

            final OrgTicketMst lObjTicketMst = new OrgTicketMst();
            lLngTicketId = IFMSCommonServiceImpl.getNextSeqNum("org_ticket_mst", inputMap);
            this.logger.info("lLngTicketId ......." + lLngTicketId); //$NON-NLS-1$
            lObjTicketMst.setTicketId(lLngTicketId);
            lObjTicketMst.setTicketTitle(title);
            lObjTicketMst.setTicketDesc(desc);
            lObjTicketMst.setScreenId(screen);
            lObjTicketMst.setCredentials(credentials);
            lObjTicketMst.setContactNo(contactNo);
            lObjTicketMst.setEmailId(emailId);
            lObjTicketMst.setUserName(null);
            lObjTicketMst.setDdoCode(ddoCode);
            lObjTicketMst.setCreatedDate(DBUtility.getCurrentDateFromDB());
            lObjTicketMst.setStatus(10001198189l);
            lObjTicketMst.setAcknowledgementDate(acknowledgeDate);
            lObjTicketMst.setRootCause(10001198238l);
           // lObjTicketMst.setPriority(10001198198l);
            lObjTicketMst.setSeverity(10001198241l);
            lObjTicketMst.setFileId(fileId);
            //remarks
            java.sql.Clob myClob = new SerialClob(remarks.toCharArray());
            
            //Writer clobWriter = myClob.setCharacterStream(1);
            //myClob.setString(1, remarks);
            lObjTicketMst.setRemarks(myClob);
            
            
            lObjTicketMst.setUpdatedBy(null);
            lObjTicketMst.setUpdatedDate(null);
            lObjTicketMst.setResolvedBy(null);
            lObjTicketMst.setResolvedDate(null);
            

            this.hibSession.save(lObjTicketMst);
            this.hibSession.flush();
        } catch (final Exception e)
        {
            this.logger.error(" Error is : " + e, e);
            throw e;
        }

    }


    public void updateTicketMst(final Long ticketId, final Long status, /*final Long priority,*/ final String remarks, Map inputMap , String user, String prevRemarks, Long severity, Long rootCause, Long fileId)
    throws Exception
    {
      
        int result=0;
        System.out.println("in update");
        
        try{
            
            StringBuilder lSBQuery = new StringBuilder();
            lSBQuery.append("  update ORG_TICKET_MST set REMARKS =  ");
            lSBQuery.append(" ('"+prevRemarks+"'||chr(10)|| '####' ||chr(10)|| '"+user+"'||chr(10)|| sysdate||chr(10)|| '"+remarks+"'||chr(10)), ");
            if(user.equals("Resolver"))
            {
	            if(status==10001198192l)
	            {
	                lSBQuery.append("  resolved_date = sysdate, ");
	            }
            }
            else
            {
	            if(status==10001198191l || status==10001198193l)
	            {
	                lSBQuery.append("  closed_date = sysdate, ");
	            }
            }
          //  lSBQuery.append(" status = '"+status+"', updated_date = sysdate, update_flag = 1 ,priority = '"+priority+"', severity='"+severity+"', root_Cause='"+rootCause+"',");
            lSBQuery.append(" status = '"+status+"', updated_date = sysdate, update_flag = 1 , severity='"+severity+"', root_Cause='"+rootCause+"',");
            lSBQuery.append(" file_Id = '"+fileId+"' ");
            lSBQuery.append(" where TICKET_ID = '"+ticketId+"' ");
            
            Query lQuery1 = hibSession.createSQLQuery(lSBQuery.toString());
            logger.info("query is ***********"+lQuery1.toString());
            
            
            result = lQuery1.executeUpdate();

            logger.info("updated rows::::::::"+result);

        }catch(Exception e){
            logger.error(" Error is : " + e, e);
            try {
                throw e;
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
      
    
        /* try
        {
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            PRTrackingDAO lObjNewRegPayroll= new PRTrackingDAOImpl(OrgTicketMst.class, serv.getSessionFactory());
            OrgTicketMst lObjTicketMst = (OrgTicketMst) lObjNewRegPayroll.read(ticketId);

            lObjTicketMst.setStatus(status);
            lObjTicketMst.setRemarks(remarks);
            lObjTicketMst.setUpdatedBy(null);
            lObjTicketMst.setUpdatedDate(DBUtility.getCurrentDateFromDB());
            lObjTicketMst.setResolvedBy(null);

            lObjTicketMst.setPriority(priority);

            if(status==10001198181l || status==10001198183l)
            {
                lObjTicketMst.setResolvedDate(DBUtility.getCurrentDateFromDB());
            }
            else
            {
                lObjTicketMst.setResolvedDate(null);
            }

            this.hibSession.update(lObjTicketMst);
            this.hibSession.flush();
        } catch (final Exception e)
        {
            this.logger.error(" Error is : " + e, e);
            throw e;
        }*/

        
        
    

    }

    public List getLoadEmpTickets(long locId,String roleid, long status, String fromDate, String toDate)
    {
        System.out.println("locId::"+locId);
        List lLstRADetails=new ArrayList();
        System.out.println("in Details");
        lLstRADetails = null;
        int chkStatus = 0;

        try{
            StringBuilder lSBQuery = new StringBuilder();
           //lSBQuery.append("SELECT otm.TICKET_ID,otm.TICKET_TITLE,otm.TICKET_DESC, status.LOOKUP_NAME||'' ,otm.CREATED_DATE,priority.LOOKUP_NAME||'', nvl(days(sysdate)-days(otm.CREATED_DATE),0),cast(otm.remarks as varchar(32000)),otm.resolved_date,otm.update_flag,severity.LOOKUP_NAME||'',root.LOOKUP_NAME||'',otm.CREATED_DATE + 30 Minute,otm.file_id");
            lSBQuery.append("SELECT otm.TICKET_ID,otm.TICKET_TITLE,otm.TICKET_DESC, status.LOOKUP_NAME||'',otm.CREATED_DATE, nvl(days(sysdate)-days(otm.CREATED_DATE),0),cast(otm.remarks as varchar(32000)),otm.resolved_date,otm.update_flag,severity.LOOKUP_NAME||'',root.LOOKUP_NAME||'',otm.CREATED_DATE + 30 Minute,otm.file_id");
            lSBQuery.append(" from ORG_TICKET_MST otm ");
            lSBQuery.append("join org_ddo_mst mdo on otm.DDO_CODE=mdo.DDO_CODE ");
            lSBQuery.append("join CMN_LOOKUP_MST status on status.LOOKUP_ID=otm.STATUS "); 
            //lSBQuery.append("join CMN_LOOKUP_MST priority on priority.LOOKUP_ID=otm.PRIORITY ");
            lSBQuery.append("left outer join CMN_LOOKUP_MST severity on severity.LOOKUP_ID=otm.SEVERITY "); 
            lSBQuery.append("left outer join CMN_LOOKUP_MST root on root.LOOKUP_ID=otm.ROOT_CAUSE ");

            if(!roleid.equalsIgnoreCase("442400074"))    //Changed by Akshay
            {
                lSBQuery.append(" WHERE mdo.LOCATION_CODE= "+locId);

            }
            else
            {
                if(status != 0)
                {
                    lSBQuery.append("where otm.status = "+status);
                    if(!fromDate.equals("") && !toDate.equals("")){

                        lSBQuery.append(" and otm.created_date between '"+fromDate+"'and '"+toDate+"'");
                        chkStatus = 1;
                    }
                }
                if(!fromDate.equals("") && !toDate.equals("") && chkStatus == 0){

                    lSBQuery.append(" where otm.created_date between '"+fromDate+"'and '"+toDate+"'");
                }
            }
            lSBQuery.append(" order by otm.CREATED_DATE desc");
            Query lQuery1 = hibSession.createSQLQuery(lSBQuery.toString());
            logger.info("query is ***********"+lQuery1.toString());

            logger.info("locId: "+locId);
            lLstRADetails= lQuery1.list();
            logger.info("getLoadEmpTickets slLstRADetails::::::::"+lLstRADetails.size());


        }catch(Exception e){
            logger.error(" Error is : " + e, e);
            try {
                throw e;
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        return lLstRADetails;
    }

    public String getRoleID(String roleid)
    {
        System.out.println("locId::"+roleid);
        List lLstRADetails=new ArrayList();
        System.out.println("in Details");
        lLstRADetails = null;
        String rollid=null;
        try{
            StringBuilder lSBQuery = new StringBuilder();
            lSBQuery.append("select role_id from acl_postrole_rlt where post_id=:roleid");
            Query lQuery1 = hibSession.createSQLQuery(lSBQuery.toString());
            logger.info("query is ***********"+lQuery1.toString());
            lQuery1.setParameter("roleid", roleid+"");
            logger.info("roleid: "+roleid);
            lLstRADetails= lQuery1.list();
            rollid=lLstRADetails.get(0).toString(); 
            logger.info("getLoadEmpTickets slLstRADetails::::::::"+lLstRADetails.size());

        }catch(Exception e){
            logger.error(" Error is : " + e, e);
            try {
                throw e;
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        return rollid;
    }

    public void updateTicketFlag(Long ticketId)
    {

        int result=0;
        List screenName = null;
        final StringBuilder lSBQuery = new StringBuilder();
        final Session session = this.getSession();
        lSBQuery.append(" update ORG_TICKET_MST set update_flag = 0 where TICKET_ID = '"+ticketId+"'");
      
        Query lQuery1 = hibSession.createSQLQuery(lSBQuery.toString());
        logger.info("query is ***********"+lQuery1.toString());
        
        result = lQuery1.executeUpdate();
        logger.info("updated rows::::::::"+result);

    }

    public String getPrevRemarks(Long ticketId)
    {
        String prevR = "";
        List listRemarks;
        Session session = this.getSession();
        StringBuilder lSBQuery = new StringBuilder();
        lSBQuery.append(" select nvl(cast(remarks as varchar(32000)),'') from org_ticket_mst where ticket_id="+ticketId);
        Query lQuery = session.createSQLQuery(lSBQuery.toString());
        this.logger.info("lSBQuery.toString()----------" + lSBQuery.toString());
        listRemarks = lQuery.list();
        if (listRemarks != null) {
            if (listRemarks.size() != 0) {
                if (listRemarks.get(0) != null) {
                    prevR = listRemarks.get(0).toString();
                }
            }
        }
        return prevR;
    }

	@Override
	public void updateHistoryMst(OrgTicketMst orgTicketMst)   throws Exception {
		
        try
        {
            

            final OrgTicketMstHistory lObjTicketMst = new OrgTicketMstHistory();
            lObjTicketMst.setTicketId(orgTicketMst.getTicketId());
            lObjTicketMst.setTicketTitle(orgTicketMst.getTicketTitle());
            lObjTicketMst.setTicketDesc(orgTicketMst.getTicketDesc());
            lObjTicketMst.setScreenId(orgTicketMst.getScreenId());
            lObjTicketMst.setCredentials(orgTicketMst.getCredentials());
            lObjTicketMst.setContactNo(orgTicketMst.getContactNo());
            lObjTicketMst.setEmailId(orgTicketMst.getEmailId());
            lObjTicketMst.setUserName(null);
            lObjTicketMst.setDdoCode(orgTicketMst.getDdoCode());
            lObjTicketMst.setCreatedDate(orgTicketMst.getCreatedDate());
            lObjTicketMst.setStatus(orgTicketMst.getStatus());
            lObjTicketMst.setAcknowledgementDate(orgTicketMst.getAcknowledgementDate());
            lObjTicketMst.setRemarks(orgTicketMst.getRemarks());
            lObjTicketMst.setUpdatedBy(orgTicketMst.getUpdatedBy());
            lObjTicketMst.setUpdatedDate(orgTicketMst.getUpdatedDate());
            lObjTicketMst.setResolvedBy(orgTicketMst.getResolvedBy());
            lObjTicketMst.setResolvedDate(orgTicketMst.getResolvedDate());
           // lObjTicketMst.setPriority(orgTicketMst.getPriority());
            lObjTicketMst.setSeverity(orgTicketMst.getSeverity());
            lObjTicketMst.setClosedDate(DBUtility.getCurrentDateFromDB());
            lObjTicketMst.setRootCause(orgTicketMst.getRootCause());
            //added by Saurabh S
            lObjTicketMst.setFileId(orgTicketMst.getFileId());
            //ended by Saurabh S

            this.hibSession.save(lObjTicketMst);
            this.hibSession.flush();
        } catch (final Exception e)
        {
            this.logger.error(" Error is : " + e, e);
            throw e;
        }
		
	}


}
