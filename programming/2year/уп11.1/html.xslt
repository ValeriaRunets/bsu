<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>People</h2>
                <table border="1" align="center">
                    <tr bgcolor="pink">
                        <th>Name</th>
                        <th>Country</th>
                        <th>Ages</th>
                    </tr>
                    <xsl:for-each select="list/human">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="@country"/></td>
                            <td><xsl:value-of select="age"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
                <tr>
                    <td align="center">Number of people = <xsl:value-of select="count(list/human)"/></td>
                </tr>
                <tr>
                    <td>Average age =<xsl:value-of select="sum(list/human/age) div count(list/human)"/></td>
                </tr>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>