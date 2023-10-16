package com.tcs.sgv.eis.valueobject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

public class HrPayrollBeamsMpg
  implements Serializable
{
  private long id;
  private Long paybillId;
  private Long billNo;
  private Long billNetAmount;
  private Long billGrossAmt;
  private String authNo;
  private String statusCode;
  private Blob authSlip;
  private Integer locId;
  private Short dbId;
  private Long createdBy;
  private Date createdDate;
  private Long updatedBy;
  private Date updatedDate;
  private Long createdByPost;
  private Long updatedByPost;

  public HrPayrollBeamsMpg()
  {
  }

  public HrPayrollBeamsMpg(long id)
  {
    this.id = id;
  }

  public HrPayrollBeamsMpg(long id, Long paybillId, Long billNo, Long billNetAmount, Long billGrossAmt, String authNo, String statusCode, Blob authSlip, Integer locId, Short dbId, Long createdBy, Date createdDate, Long updatedBy, Date updatedDate, Long createdByPost, Long updatedByPost)
  {
    this.id = id;
    this.paybillId = paybillId;
    this.billNo = billNo;
    this.billNetAmount = billNetAmount;
    this.billGrossAmt = billGrossAmt;
    this.authNo = authNo;
    this.statusCode = statusCode;
    this.authSlip = authSlip;
    this.locId = locId;
    this.dbId = dbId;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.createdByPost = createdByPost;
    this.updatedByPost = updatedByPost;
  }

  public long getId()
  {
    return this.id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public Long getPaybillId()
  {
    return this.paybillId;
  }

  public void setPaybillId(Long paybillId)
  {
    this.paybillId = paybillId;
  }

  public Long getBillNo()
  {
    return this.billNo;
  }

  public void setBillNo(Long billNo)
  {
    this.billNo = billNo;
  }

  public Long getBillNetAmount()
  {
    return this.billNetAmount;
  }

  public void setBillNetAmount(Long billNetAmount)
  {
    this.billNetAmount = billNetAmount;
  }

  public Long getBillGrossAmt()
  {
    return this.billGrossAmt;
  }

  public void setBillGrossAmt(Long billGrossAmt)
  {
    this.billGrossAmt = billGrossAmt;
  }

  public String getAuthNo()
  {
    return this.authNo;
  }

  public void setAuthNo(String authNo)
  {
    this.authNo = authNo;
  }

  public String getStatusCode()
  {
    return this.statusCode;
  }

  public void setStatusCode(String statusCode)
  {
    this.statusCode = statusCode;
  }

  public Integer getLocId()
  {
    return this.locId;
  }

  public void setLocId(Integer locId)
  {
    this.locId = locId;
  }

  public Short getDbId()
  {
    return this.dbId;
  }

  public void setDbId(Short dbId)
  {
    this.dbId = dbId;
  }

  public Long getCreatedBy()
  {
    return this.createdBy;
  }

  public void setCreatedBy(Long createdBy)
  {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate()
  {
    return this.createdDate;
  }

  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }

  public Long getUpdatedBy()
  {
    return this.updatedBy;
  }

  public void setUpdatedBy(Long updatedBy)
  {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }

  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }

  public Long getCreatedByPost()
  {
    return this.createdByPost;
  }

  public void setCreatedByPost(Long createdByPost)
  {
    this.createdByPost = createdByPost;
  }

  public Long getUpdatedByPost()
  {
    return this.updatedByPost;
  }

  public void setUpdatedByPost(Long updatedByPost)
  {
    this.updatedByPost = updatedByPost;
  }

  public void setauthSlip(Blob finalAttachmentBlob)
  {
    this.authSlip = finalAttachmentBlob;
  }

  public Blob getauthSlip()
  {
    return this.authSlip;
  }

 /* private byte[] toByteArray(Blob fromBlob)
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try
    {
     
      return toByteArrayImpl(fromBlob, baos);
    }
    catch (SQLException e)
    {
    }
    catch (IOException e)
    {
    }
    finally
    {
      if (baos != null)
      {
        try
        {
          baos.close();
        }
        catch (IOException ex) {
          ex.getMessage();
        }
      }
    }
  }*/

 /* // ERROR //
  private byte[] toByteArrayImpl(Blob fromBlob, ByteArrayOutputStream baos)
    throws SQLException, IOException
  {
    // Byte code:
    //   0: sipush 4000
    //   3: newarray byte
    //   5: astore_3
    //   6: aload_1
    //   7: invokeinterface 158 1 0
    //   12: astore 4
    //   14: aload 4
    //   16: aload_3
    //   17: invokevirtual 162	java/io/InputStream:read	([B)I
    //   20: istore 5
    //   22: iload 5
    //   24: iconst_m1
    //   25: if_icmpne +6 -> 31
    //   28: goto +40 -> 68
    //   31: aload_2
    //   32: aload_3
    //   33: iconst_0
    //   34: iload 5
    //   36: invokevirtual 168	java/io/ByteArrayOutputStream:write	([BII)V
    //   39: goto -25 -> 14
    //   42: astore 6
    //   44: aload 4
    //   46: ifnull +19 -> 65
    //   49: aload 4
    //   51: invokevirtual 172	java/io/InputStream:close	()V
    //   54: goto +11 -> 65
    //   57: astore 7
    //   59: aload 7
    //   61: invokevirtual 131	IOException:getMessage	()Ljava/lang/String;
    //   64: pop
    //   65: aload 6
    //   67: athrow
    //   68: aload 4
    //   70: ifnull +19 -> 89
    //   73: aload 4
    //   75: invokevirtual 172	java/io/InputStream:close	()V
    //   78: goto +11 -> 89
    //   81: astore 7
    //   83: aload 7
    //   85: invokevirtual 131	IOException:getMessage	()Ljava/lang/String;
    //   88: pop
    //   89: aload_2
    //   90: invokevirtual 173	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   93: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   14	42	42	finally
    //   49	54	57	IOException
    //   73	78	81	IOException
  }*/
}