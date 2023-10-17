package com.tcs.sgv.onlinebillprep.valueobject;
// Generated Oct 3, 2007 3:03:25 PM by Hibernate Tools 3.2.0.beta8

/**
 * ConfigOnlineBill generated by hbm2java
 */
public class ConfigOnlineBill implements java.io.Serializable
{

    // Fields    

    private Long configOnlineBillId;

    private Long subjectId;

    private String isConfigured;

    private String rqstSrvc;

    private String jspPath;

    private String vogen;

    private String saveBillSrvc;

    private String fetchBillDataSrvc;

    private String digisigSaveSrvc;

    private String gtrFormatView;

    private String digisigApplId;

    private String digisigVrfySrvc;


    // Constructors

    /** default constructor */
    public ConfigOnlineBill()
    {
    }


    /** minimal constructor */
    public ConfigOnlineBill(Long configOnlineBillId, Long subjectId, String isConfigured)
    {
        this.configOnlineBillId = configOnlineBillId;
        this.subjectId = subjectId;
        this.isConfigured = isConfigured;
    }


    /** full constructor */
    public ConfigOnlineBill(Long configOnlineBillId, Long subjectId, String isConfigured, String rqstSrvc,
            String jspPath, String vogen, String saveBillSrvc, String fetchBillDataSrvc, String digisigSaveSrvc,
            String gtrFormatView, String digisigApplId, String digisigVrfySrvc)
    {
        this.configOnlineBillId = configOnlineBillId;
        this.subjectId = subjectId;
        this.isConfigured = isConfigured;
        this.rqstSrvc = rqstSrvc;
        this.jspPath = jspPath;
        this.vogen = vogen;
        this.saveBillSrvc = saveBillSrvc;
        this.fetchBillDataSrvc = fetchBillDataSrvc;
        this.digisigSaveSrvc = digisigSaveSrvc;
        this.gtrFormatView = gtrFormatView;
        this.digisigApplId = digisigApplId;
        this.digisigVrfySrvc = digisigVrfySrvc;
    }


    // Property accessors
    public Long getConfigOnlineBillId()
    {
        return this.configOnlineBillId;
    }


    public void setConfigOnlineBillId(Long configOnlineBillId)
    {
        this.configOnlineBillId = configOnlineBillId;
    }


    public Long getSubjectId()
    {
        return this.subjectId;
    }


    public void setSubjectId(Long subjectId)
    {
        this.subjectId = subjectId;
    }


    public String getIsConfigured()
    {
        return this.isConfigured;
    }


    public void setIsConfigured(String isConfigured)
    {
        this.isConfigured = isConfigured;
    }


    public String getRqstSrvc()
    {
        return this.rqstSrvc;
    }


    public void setRqstSrvc(String rqstSrvc)
    {
        this.rqstSrvc = rqstSrvc;
    }


    public String getJspPath()
    {
        return this.jspPath;
    }


    public void setJspPath(String jspPath)
    {
        this.jspPath = jspPath;
    }


    public String getVogen()
    {
        return this.vogen;
    }


    public void setVogen(String vogen)
    {
        this.vogen = vogen;
    }


    public String getSaveBillSrvc()
    {
        return this.saveBillSrvc;
    }


    public void setSaveBillSrvc(String saveBillSrvc)
    {
        this.saveBillSrvc = saveBillSrvc;
    }


    public String getFetchBillDataSrvc()
    {
        return this.fetchBillDataSrvc;
    }


    public void setFetchBillDataSrvc(String fetchBillDataSrvc)
    {
        this.fetchBillDataSrvc = fetchBillDataSrvc;
    }


    public String getDigisigSaveSrvc()
    {
        return this.digisigSaveSrvc;
    }


    public void setDigisigSaveSrvc(String digisigSaveSrvc)
    {
        this.digisigSaveSrvc = digisigSaveSrvc;
    }


    public String getGtrFormatView()
    {
        return this.gtrFormatView;
    }


    public void setGtrFormatView(String gtrFormatView)
    {
        this.gtrFormatView = gtrFormatView;
    }


    public String getDigisigApplId()
    {
        return this.digisigApplId;
    }


    public void setDigisigApplId(String digisigApplId)
    {
        this.digisigApplId = digisigApplId;
    }


    public String getDigisigVrfySrvc()
    {
        return this.digisigVrfySrvc;
    }


    public void setDigisigVrfySrvc(String digisigVrfySrvc)
    {
        this.digisigVrfySrvc = digisigVrfySrvc;
    }

}