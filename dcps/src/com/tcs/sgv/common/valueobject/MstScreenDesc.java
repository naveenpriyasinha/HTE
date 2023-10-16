package com.tcs.sgv.common.valueobject;

import java.util.Date;

public class MstScreenDesc
{
    private Long screenId;
    private String screenDesc;
    private Date createdDate;
    private int activateFlag;

    public int getActivateFlag()
    {
        return this.activateFlag;
    }

    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    public String getScreenDesc()
    {
        return this.screenDesc;
    }

    public Long getScreenId()
    {
        return this.screenId;
    }

    public void setActivateFlag(final int activateFlag)
    {
        this.activateFlag = activateFlag;
    }

    public void setCreatedDate(final Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public void setScreenDesc(final String screenDesc)
    {
        this.screenDesc = screenDesc;
    }

    public void setScreenId(final Long screenId)
    {
        this.screenId = screenId;
    }

}
