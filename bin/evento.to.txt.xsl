<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:a="http://www.portalfiscal.inf.br/nfe" xmlns:b="http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento">
	<xsl:output method="text" encoding="ISO-8859-1" />
	<xsl:template match="/">
		<xsl:value-of select="b:nfeRecepcaoEventoResult/a:retEnvEvento/a:retEvento/a:infEvento/@Id"/>;<xsl:value-of select="b:nfeRecepcaoEventoResult/a:retEnvEvento/a:retEvento/a:infEvento/a:tpAmb"/>;<xsl:value-of select="b:nfeRecepcaoEventoResult/a:retEnvEvento/a:retEvento/a:infEvento/a:verAplic"/>;<xsl:value-of select="b:nfeRecepcaoEventoResult/a:retEnvEvento/a:retEvento/a:infEvento/a:chNFe"/>;<xsl:value-of select="b:nfeRecepcaoEventoResult/a:retEnvEvento/a:retEvento/a:infEvento/a:dhRegEvento"/>;<xsl:value-of select="b:nfeRecepcaoEventoResult/a:retEnvEvento/a:retEvento/a:infEvento/a:nProt"/>;<xsl:value-of select="b:nfeRecepcaoEventoResult/a:retEnvEvento/a:retEvento/a:infEvento/a:cStat"/>;<xsl:value-of select="b:nfeRecepcaoEventoResult/a:retEnvEvento/a:retEvento/a:infEvento/a:xMotivo"/>;<xsl:text disable-output-escaping="yes">
		</xsl:text>
	</xsl:template>
</xsl:stylesheet>
