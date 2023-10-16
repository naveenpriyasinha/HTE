package com.tcs.sgv.eis.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayrollBeamsMpg;
import com.tcs.sgv.eis.valueobject.TrnIfmsBeamsIntegration;

public class PayrollBEAMSMpgDAOImpl extends GenericDaoHibernateImpl<HrPayrollBeamsMpg, Long> implements
        PayrollBEAMSMpgDAO
{
    public PayrollBEAMSMpgDAOImpl(final Class type, final SessionFactory sessionFactory)
    {
        super(type);
        this.setSessionFactory(sessionFactory);
    }

    public TrnIfmsBeamsIntegration getPayBillAuthSlipDtls(final String authNo)
    {
        TrnIfmsBeamsIntegration trnIfmsBeamsIntegration = null;
        final Session hibSession = this.getSession();
        final StringBuffer strQuery = new StringBuffer(100);
        strQuery
                .append("  from TrnIfmsBeamsIntegration trnIfmsBeamsIntegration where trnIfmsBeamsIntegration.authNo=:authNo");
        final Query query = hibSession.createQuery(strQuery.toString());
        query.setString("authNo", authNo);
        this.logger.info("Query For getPayBillAuthSlipDtls" + strQuery.toString());
        trnIfmsBeamsIntegration = (TrnIfmsBeamsIntegration) query.uniqueResult();

        return trnIfmsBeamsIntegration;
    }
}
