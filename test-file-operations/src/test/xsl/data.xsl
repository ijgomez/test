<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="text" omit-xml-declaration="yes" indent="no" />
	<xsl:template match="/">
		Host_Name,IP_address,OS,Load_avg_1min,Load_avg_5min,Load_avg_15min
		<xsl:for-each select="//Host">
			<xsl:value-of
				select="concat(Host_Name,',',IP_address,',',OS,Load_avg_1min,',',Load_avg_5min,',',Load_avg_15min,'&#xA;')" />
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>