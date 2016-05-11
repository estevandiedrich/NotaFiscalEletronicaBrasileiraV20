<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:a="http://www.portalfiscal.inf.br/nfe" xmlns:b="http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2">
	<xsl:output method="text" encoding="ISO-8859-1" />
	<xsl:template match="/">
		<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/@Id"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:tpAmb"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:verAplic"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:cStat"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:xMotivo"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:cUF"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:ano"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:CNPJ"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:mod"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:serie"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:nNFIni"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:nNFFin"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:dhRecbto"/>;<xsl:value-of select="b:nfeInutilizacaoNF2Result/a:retInutNFe/a:infInut/a:nProt"/>;<xsl:text disable-output-escaping="yes">
		</xsl:text>
	</xsl:template>
</xsl:stylesheet>
