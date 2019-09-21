<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:for-each select="list/human">
            Human: name:<xsl:value-of select="name"/>, country:<xsl:value-of select="@country"/>, age:<xsl:value-of select="age"/>
        </xsl:for-each>
        Average age =<xsl:value-of select="sum(list/human/age) div count(list/human)"/>
    </xsl:template>

</xsl:stylesheet>