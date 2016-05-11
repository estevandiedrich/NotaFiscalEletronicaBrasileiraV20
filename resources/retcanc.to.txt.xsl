<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:a="http://www.portalfiscal.inf.br/nfe" xmlns:b="http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2">
	<xsl:output method="text" encoding="ISO-8859-1" />
	<xsl:template match="/">
		<xsl:value-of select="b:nfeCancelamentoNF2Result/a:retCancNFe/a:infCanc/@Id"/>;<xsl:value-of select="b:nfeCancelamentoNF2Result/a:retCancNFe/a:infCanc/a:cStat"/>;<xsl:value-of select="b:nfeCancelamentoNF2Result/a:retCancNFe/a:infCanc/a:xMotivo"/>;<xsl:value-of select="b:nfeCancelamentoNF2Result/a:retCancNFe/a:infCanc/a:chNFe"/>;<xsl:value-of select="b:nfeCancelamentoNF2Result/a:retCancNFe/a:infCanc/a:dhRecbto"/>;<xsl:value-of select="b:nfeCancelamentoNF2Result/a:retCancNFe/a:infCanc/a:nProt"/>;<xsl:text disable-output-escaping="yes">
		</xsl:text>
	</xsl:template>
</xsl:stylesheet>
