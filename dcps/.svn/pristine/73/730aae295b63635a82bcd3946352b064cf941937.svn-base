<?xml version="1.0" encoding="UTF-8"?> 

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:param name="dtd" select="''"/>
 <xsl:value-of select="$dtd"></xsl:value-of>
<xsl:template match="/">
<![CDATA[<!DOCTYPE  document  PUBLIC  "specialEntities.dtd" "]]><xsl:value-of select="$dtd"/><![CDATA[">]]>
<![CDATA[<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">]]>
<![CDATA[<xsl:template match="/">]]>
	<xsl:apply-templates/>
<![CDATA[</xsl:template>]]>
<![CDATA[</xsl:stylesheet>]]>
</xsl:template>

<xsl:template match="span">
	<xsl:if test="@type='xnode'">
		<xsl:call-template name="xnodeTemp"/>
	</xsl:if>
	<xsl:if test="@type='xforeach'">
		<xsl:variable name="forpath"><xsl:value-of select="@xpath"/></xsl:variable>
		<xsl:variable name="rowType"><xsl:value-of select="@rowTypes"/></xsl:variable>
		<span type="xforeach" xpath="{$forpath}" rowTypes="{$rowType}">
		<table width="100%">
			<![CDATA[<xsl:for-each select="]]><xsl:value-of select="@xpath"/><![CDATA[">]]>
			<![CDATA[<xsl:variable name="pos"><xsl:number value="position()"/></xsl:variable>]]>			     
			     <xsl:for-each select="table/tr">
			     <tr>
			     <xsl:choose>
			         <xsl:when test="$rowType">  
			         	<xsl:if test= "$rowType >= position()">										
							<xsl:apply-templates select="node()"/>										
						</xsl:if>				
					</xsl:when>
    				<xsl:when test="position()=1">
    						<xsl:apply-templates select="node()"/>
    				</xsl:when>
			      </xsl:choose>	
			      </tr>		     	
		      </xsl:for-each>			   
			<![CDATA[</xsl:for-each>]]>
		</table>
		</span>
	</xsl:if>
	<xsl:if test="@type='forxnode'">
		<xsl:call-template name="forxnodeTemp"/>
	</xsl:if>
	<xsl:if test="@type=''">
		<xsl:copy>
		<xsl:apply-templates select="node()"/>
		</xsl:copy>
	</xsl:if>
</xsl:template>

<xsl:template name="xnodeTemp">
	<xsl:variable name="path"><xsl:value-of select="@xpath"/></xsl:variable>
	<span type="xnode" xpath="{$path}" class="mceNonEditable">
		<b><![CDATA[<xsl:value-of select="]]><xsl:value-of select="@xpath"/><![CDATA["/>]]></b>
	</span>
</xsl:template>

<xsl:template name="xforeachTemp">
		<xsl:apply-templates select="node()"/>
	
</xsl:template>

<xsl:template name="forxnodeTemp">
	     <xsl:variable name="fornodepath"><xsl:value-of select="@xpath"/></xsl:variable> 
	     <span type="forxnode" xpath="{$fornodepath}" class="mceNonEditable">
	     <b><![CDATA[<xsl:value-of select="]]><xsl:value-of select="@xpath"/><![CDATA["/>]]></b>
	     </span>	
</xsl:template>

<xsl:template match="tbody">
	<xsl:apply-templates select="node()"/>
</xsl:template>

<xsl:template match="table">
	<xsl:variable name="width"><xsl:value-of select="@width"/></xsl:variable>
	<xsl:variable name="border"><xsl:value-of select="@border"/></xsl:variable>
	<xsl:variable name="align"><xsl:value-of select="@align"/></xsl:variable>
	<table align="{$align}" width="{$width}" border="{$border}" >
	<xsl:apply-templates select="node()"/>
	</table>
</xsl:template>

<xsl:template match="tr">
	   <tr>
		<xsl:apply-templates select="node()"/>
	    </tr>	
</xsl:template>


<xsl:template match="td">
	<xsl:variable name="align"><xsl:value-of select="@align"/></xsl:variable>
	<xsl:variable name="style"><xsl:value-of select="@style"/></xsl:variable>
	<xsl:variable name="width"><xsl:value-of select="@width"/></xsl:variable>
	<td align="{$align}" style="{$style}" width="{$width}">
		<xsl:apply-templates select="node()"/>
	</td>
</xsl:template>

<xsl:template match="h2">
	<h2>
		<xsl:apply-templates select="node()"/>
	</h2>
</xsl:template>

<xsl:template match="img">
	<xsl:variable name="src"><xsl:value-of select="@src"/></xsl:variable>
	
	<img src="{$src}"/>
	<![CDATA[</img>]]>
	
</xsl:template>

<xsl:template match="hr">
	<![CDATA[<hr />]]>
</xsl:template>

<xsl:template match="br">
	<![CDATA[<br />]]>
</xsl:template>

<xsl:template match="@*|node()">
	<xsl:copy>
		<xsl:apply-templates select="@*|node()"/>
	</xsl:copy>
</xsl:template>

</xsl:stylesheet>