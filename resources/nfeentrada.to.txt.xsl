<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:a="http://www.portalfiscal.inf.br/nfe">
	<xsl:output method="text" encoding="ISO-8859-1"/>	
	<xsl:template name="Newline"><xsl:text>
</xsl:text></xsl:template>
	<xsl:template match="/">
		<xsl:text>0000;2.00;ENVIO</xsl:text><xsl:call-template name="Newline" />
		<xsl:text>1000;000000000000001</xsl:text><xsl:call-template name="Newline" />
		<xsl:text>2000;</xsl:text><xsl:value-of select="a:nfeProc/@versao"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/@Id"/><xsl:call-template name="Newline" />
		<xsl:text>2100;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:cUF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:cNF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:natOp"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:indPag"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:mod"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:serie"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:nNF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:dEmi"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:dSaiEnt"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:hSaiEnt"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:tpNF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:cMunFG"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:tpImp"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:tpEmis"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:cDV"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:tpAmb"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:finNFe"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:procEmi"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:verProc"/><xsl:call-template name="Newline" />
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:NFref">			
			<xsl:if test="a:refNFe">
				<xsl:text>2110;</xsl:text><xsl:value-of select="a:refNFe"/><xsl:call-template name="Newline" />
			</xsl:if>
		</xsl:for-each>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:NFref">
			<xsl:if test="a:refNF">
				<xsl:text>2120;</xsl:text><xsl:value-of select="a:refNF/a:cUF"/>;<xsl:value-of select="a:refNF/a:AAMM"/>;<xsl:value-of select="a:refNF/a:CNPJ"/>;<xsl:value-of select="a:refNF/a:mod"/>;<xsl:value-of select="a:refNF/a:serie"/>;<xsl:value-of select="a:refNF/a:nNF"/><xsl:call-template name="Newline" />
			</xsl:if>	
		</xsl:for-each>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:NFref">
			<xsl:if test="a:refNFP">
				<xsl:text>2130;</xsl:text><xsl:value-of select="a:refNFP/a:cUF"/>;<xsl:value-of select="a:refNFP/a:AAMM"/>;<xsl:value-of select="a:refNFP/a:CNPJ"/>;<xsl:value-of select="a:refNFP/a:CPF"/>;<xsl:value-of select="a:refNFP/a:IE"/>;<xsl:value-of select="a:refNFP/a:mod"/>;<xsl:value-of select="a:refNFP/a:serie"/>;<xsl:value-of select="a:refNFP/a:nNF"/><xsl:call-template name="Newline" />
			</xsl:if>	
		</xsl:for-each>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:NFref">
			<xsl:if test="a:refCTe">
				<xsl:text>2140;</xsl:text><xsl:value-of select="a:refCTe"/><xsl:call-template name="Newline" />
			</xsl:if>
		</xsl:for-each>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:NFref">
			<xsl:if test="a:refECF">
				<xsl:text>2150;</xsl:text><xsl:value-of select="a:refECF/a:mod"/>;<xsl:value-of select="a:refECF/a:nECF"/>;<xsl:value-of select="a:refECF/a:nCOO"/><xsl:call-template name="Newline" />
			</xsl:if>	
		</xsl:for-each>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:ide/a:dhCont">
			<xsl:text>2180;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:dhCont"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:ide/a:xJust"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:text>2200;</xsl:text><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:emit/a:CNPJ"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:CNPJ"/></xsl:if><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:emit/a:CPF"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:CPF"/></xsl:if>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:xNome"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:xFant"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:IE"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:IEST"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:IM"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:CNAE"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:CRT"/><xsl:call-template name="Newline" />
		<xsl:text>2210;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:xLgr"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:nro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:xCpl"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:xBairro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:cMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:xMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:UF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:CEP"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:cPais"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:xPais"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:emit/a:enderEmit/a:fone"/><xsl:call-template name="Newline" />
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:avulsa">
			<xsl:text>2250;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:CNPJ"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:xOrgao"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:matr"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:xAgente"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:fone"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:UF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:nDAR"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:dEmi"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:vDAR"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:repEmi"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:avulsa/a:dPag"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:text>2300;</xsl:text><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:dest/a:CNPJ"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:CNPJ"/></xsl:if><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:dest/a:CPF"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:CPF"/></xsl:if>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:xNome"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:IE"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:ISUF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:email"/><xsl:call-template name="Newline" />
		<xsl:text>2310;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:xLgr"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:nro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:xCpl"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:xBairro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:cMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:xMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:UF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:CEP"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:cPais"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:xPais"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:dest/a:enderDest/a:fone"/><xsl:call-template name="Newline" />
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:retirada">
			<xsl:text>2400;</xsl:text><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:CNPJ"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:CNPJ"/></xsl:if><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:CPF"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:CPF"/></xsl:if>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:xLgr"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:nro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:xCpl"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:xBairro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:cMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:xMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:retirada/a:UF"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:entrega">
			<xsl:text>2500;</xsl:text><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:CNPJ"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:CNPJ"/></xsl:if><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:CPF"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:CPF"/></xsl:if>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:xLgr"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:nro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:xCpl"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:xBairro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:cMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:xMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:entrega/a:UF"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:det">
			<xsl:text>3000;</xsl:text><xsl:value-of select="a:prod/a:cProd"/>;<xsl:value-of select="a:prod/a:cEAN"/>;<xsl:value-of select="a:prod/a:xProd"/>;<xsl:value-of select="a:prod/a:NCM"/>;<xsl:value-of select="a:prod/a:EXTIPI"/>;<xsl:value-of select="a:prod/a:CFOP"/>;<xsl:value-of select="a:prod/a:uCom"/>;<xsl:value-of select="a:prod/a:qCom"/>;<xsl:value-of select="a:prod/a:vUnCom"/>;<xsl:value-of select="a:prod/a:vProd"/>;<xsl:value-of select="a:prod/a:cEANTrib"/>;<xsl:value-of select="a:prod/a:uTrib"/>;<xsl:value-of select="a:prod/a:qTrib"/>;<xsl:value-of select="a:prod/a:vUnTrib"/>;<xsl:value-of select="a:prod/a:vFrete"/>;<xsl:value-of select="a:prod/a:vSeg"/>;<xsl:value-of select="a:prod/a:vDesc"/>;<xsl:value-of select="a:prod/a:vOutro"/>;<xsl:value-of select="a:prod/a:indTot"/>;<xsl:value-of select="a:prod/a:xPed"/>;<xsl:value-of select="a:prod/a:nItemPed"/><xsl:call-template name="Newline" />
			<xsl:for-each select="a:prod/a:DI">
				<xsl:text>3001;</xsl:text><xsl:value-of select="a:nDI"/>;<xsl:value-of select="a:dDI"/>;<xsl:value-of select="a:xLocDesemb"/>;<xsl:value-of select="a:UFDesemb"/>;<xsl:value-of select="a:dDesemb"/>;<xsl:value-of select="a:cExportador"/><xsl:call-template name="Newline" />
				<xsl:for-each select="a:adi">
					<xsl:text>3002;</xsl:text><xsl:value-of select="a:nAdicao"/>;<xsl:value-of select="a:nSeqAdic"/>;<xsl:value-of select="a:cFabricante"/>;<xsl:value-of select="a:vDescDI"/><xsl:call-template name="Newline" />
				</xsl:for-each>
			</xsl:for-each>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS00">
				<xsl:text>3100;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS00/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS00/a:CST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS00/a:modBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS00/a:vBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS00/a:pICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS00/a:vICMS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS10">
				<xsl:text>3103;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:CST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:modBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:vBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:pICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:vICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:modBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:pMVAST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:pRedBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:vBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:pICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS10/a:vICMSST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS20">
				<xsl:text>3106;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS20/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS20/a:CST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS20/a:modBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS20/a:pRedBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS20/a:vBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS20/a:pICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS20/a:vICMS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS30">
				<xsl:text>3110;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS30/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS30/a:CST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS30/a:modBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS30/a:pMVAST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS30/a:pRedBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS30/a:vBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS30/a:pICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS30/a:vICMSST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS40">
				<xsl:text>3113;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS40/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS40/a:CST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS41">
				<xsl:text>3113;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS41/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS41/a:CST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS50">
				<xsl:text>3113;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS50/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS50/a:CST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS51">
				<xsl:text>3116;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS51/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS51/a:CST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS51/a:modBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS51/a:pRedBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS51/a:vBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS51/a:pICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS51/a:vICMS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS60">
				<xsl:text>3120;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS60/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS60/a:CST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS60">
				<xsl:text>3121;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS60/a:vBCSTRet"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS60/a:vICMSSTRet"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS70">
				<xsl:text>3123;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:CST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:modBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:pRedBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:vBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:pICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:vICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:modBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:pMVAST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:pRedBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:vBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:pICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS70/a:vICMSST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS90">
				<xsl:text>3126;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS90/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90/a:CST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS90A">
				<xsl:text>3127;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS90A/a:modBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90A/a:vBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90A/a:pICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90A/a:vICMS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMS90B">
				<xsl:text>3128;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMS90B/a:modBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90B/a:pMVAST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90B/a:pRedBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90B/a:vBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90B/a:pICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMS90B/a:vICMSST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSPart">
				<xsl:text>3130;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:CST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:modBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:vBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:pRedBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:pICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:vICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:modBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:pMVAST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:pRedBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:vBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:pICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:vICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:pBCOp"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSPart/a:UFST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSST">
				<xsl:text>3133;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSST/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSST/a:CST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSST/a:vBCSTRet"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSST/a:vICMSSTRet"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSST/a:vBCSTDest"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSST/a:vICMSSTDest"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN101">
				<xsl:text>3136;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN101/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN101/a:CSOSN"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN101/a:pCredSN"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN101/a:vCredICMSSN"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN102">
				<xsl:text>3140;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN102/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN102/a:CSOSN"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN201">
				<xsl:text>3143;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:CSOSN"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:modBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:pMVAST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:pRedBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:vBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:pICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:vICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:pCredSN"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN201/a:vCredICMSSN"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN202">
				<xsl:text>3146;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN202/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN202/a:CSOSN"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN202/a:modBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN202/a:pMVAST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN202/a:pRedBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN202/a:vBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN202/a:pICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN202/a:vICMSST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN500">
				<xsl:text>3150;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN500/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN500/a:CSOSN"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN500">
				<xsl:text>3151;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN500/a:vBCSTRet"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN500/a:vICMSSTRet"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN900">
				<xsl:text>3153;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:orig"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:CSOSN"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN900/a:modBC">
				<xsl:text>3154;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:modBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:vBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:pRedBC"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:pICMS"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:vICMS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN900/a:modBCST">
				<xsl:text>3155;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:modBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:pMVAST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:pRedBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:vBCST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:pICMSST"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:vICMSST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ICMS/a:ICMSSN900/a:pCredSN">
				<xsl:text>3156;</xsl:text><xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:pCredSN"/>;<xsl:value-of select="a:imposto/a:ICMS/a:ICMSSN900/a:vCredICMSSN"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:IPI">
				<xsl:text>3180;</xsl:text><xsl:value-of select="a:imposto/a:IPI/a:clEnq"/>;<xsl:value-of select="a:imposto/a:IPI/a:CNPJProd"/>;<xsl:value-of select="a:imposto/a:IPI/a:cSelo"/>;<xsl:value-of select="a:imposto/a:IPI/a:qSelo"/>;<xsl:value-of select="a:imposto/a:IPI/a:cEnq"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:IPI/a:IPITrib/a:vBC">
				<xsl:text>3181;</xsl:text><xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:CST"/>;<xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:vBC"/>;<xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:pIPI"/>;<xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:qUnid"/>;<xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:vUnid"/>;<xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:vIPI"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:IPI/a:IPITrib/a:qUnid">
				<xsl:text>3182;</xsl:text><xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:CST"/>;<xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:qUnid"/>;<xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:vUnid"/>;<xsl:value-of select="a:imposto/a:IPI/a:IPITrib/a:vIPI"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:IPI/a:IPINT">
				<xsl:text>3190;</xsl:text><xsl:value-of select="a:imposto/a:IPI/a:IPINT/a:CST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:II">
				<xsl:text>3195;</xsl:text><xsl:value-of select="a:imposto/a:II/a:vBC"/>;<xsl:value-of select="a:imposto/a:II/a:vDespAdu"/>;<xsl:value-of select="a:imposto/a:II/a:vII"/>;<xsl:value-of select="a:imposto/a:II/a:vIOF"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:ISSQN">
				<xsl:text>3197;</xsl:text><xsl:value-of select="a:imposto/a:ISSQN/a:vBC"/>;<xsl:value-of select="a:imposto/a:ISSQN/a:vAliq"/>;<xsl:value-of select="a:imposto/a:ISSQN/a:vISSQN"/>;<xsl:value-of select="a:imposto/a:ISSQN/a:cMunFG"/>;<xsl:value-of select="a:imposto/a:ISSQN/a:cListServ"/>;<xsl:value-of select="a:imposto/a:ISSQN/a:cSitTrib"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:PIS/a:PISAliq">
				<xsl:text>3200;</xsl:text><xsl:value-of select="a:imposto/a:PIS/a:PISAliq/a:CST"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISAliq/a:vBC"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISAliq/a:pPIS"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISAliq/a:vPIS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:PIS/a:PISQtde">
				<xsl:text>3210;</xsl:text><xsl:value-of select="a:imposto/a:PIS/a:PISQtde/a:CST"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISQtde/a:qBCProd"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISQtde/a:vAliqProd"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISQtde/a:vPIS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:PIS/a:PISNT">
				<xsl:text>3220;</xsl:text><xsl:value-of select="a:imposto/a:PIS/a:PISNT/a:CST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:PIS/a:PISOutr/a:vBC">
				<xsl:text>3230;</xsl:text><xsl:value-of select="a:imposto/a:PIS/a:PISOutr/a:CST"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISOutr/a:vBC"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISOutr/a:pPIS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:PIS/a:PISOutr/a:qBCProd">
				<xsl:text>3231;</xsl:text><xsl:value-of select="a:imposto/a:PIS/a:PISOutr/a:CST"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISOutr/a:qBCProd"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISOutr/a:vAliqProd"/>;<xsl:value-of select="a:imposto/a:PIS/a:PISOutr/a:vPIS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:PIS/a:PISST/a:vBC">
				<xsl:text>3240;</xsl:text><xsl:value-of select="a:imposto/a:PISST/a:vBC"/>;<xsl:value-of select="a:imposto/a:PISST/a:pPIS"/>;<xsl:value-of select="a:imposto/a:PISST/a:vPIS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:PIS/a:PISST/a:qBCProd">
				<xsl:text>3250;</xsl:text><xsl:value-of select="a:imposto/a:PISST/a:qBCProd"/>;<xsl:value-of select="a:imposto/a:PISST/a:vAliqProd"/>;<xsl:value-of select="a:imposto/a:PISST/a:vPIS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:COFINS/a:COFINSAliq">
				<xsl:text>3300;</xsl:text><xsl:value-of select="a:imposto/a:COFINS/a:COFINSAliq/a:CST"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSAliq/a:vBC"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSAliq/a:pCOFINS"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSAliq/a:vCOFINS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:COFINS/a:COFINSQtde">
				<xsl:text>3310;</xsl:text><xsl:value-of select="a:imposto/a:COFINS/a:COFINSQtde/a:CST"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSQtde/a:qBCProd"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSQtde/a:vAliqProd"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSQtde/a:vCOFINS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:COFINS/a:COFINSNT">
				<xsl:text>3320;</xsl:text><xsl:value-of select="a:imposto/a:COFINS/a:COFINSNT/a:CST"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:COFINS/a:COFINSOutr/a:vBC">
				<xsl:text>3330;</xsl:text><xsl:value-of select="a:imposto/a:COFINS/a:COFINSOutr/a:CST"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSOutr/a:vBC"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSOutr/a:pCOFINS"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSOutr/a:vCOFINS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:COFINS/a:COFINSOutr/a:qBCProd">
				<xsl:text>3331;</xsl:text><xsl:value-of select="a:imposto/a:COFINS/a:COFINSOutr/a:CST"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSOutr/a:qBCProd"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSOutr/a:vAliqProd"/>;<xsl:value-of select="a:imposto/a:COFINS/a:COFINSOutr/a:vCOFINS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:COFINSST/a:pCOFINS">
				<xsl:text>3400;</xsl:text><xsl:value-of select="a:imposto/a:COFINSST/a:vBC"/>;<xsl:value-of select="a:imposto/a:COFINSST/a:pCOFINS"/>;<xsl:value-of select="a:imposto/a:COFINSST/a:vCOFINS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:imposto/a:COFINSST/a:qBCProd">
				<xsl:text>3401;</xsl:text><xsl:value-of select="a:imposto/a:COFINSST/a:qBCProd"/>;<xsl:value-of select="a:imposto/a:COFINSST/a:vAliqProd"/>;<xsl:value-of select="a:imposto/a:COFINSST/a:vCOFINS"/><xsl:call-template name="Newline" />
			</xsl:if>
			<xsl:if test="a:infAdProd">
				<xsl:text>3600;</xsl:text><xsl:value-of select="a:infAdProd"/><xsl:call-template name="Newline" />
			</xsl:if>
		</xsl:for-each>
		<xsl:text>4000;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vBC"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vICMS"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vBCST"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vST"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vProd"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vFrete"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vSeg"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vDesc"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vII"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vIPI"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vPIS"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vCOFINS"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vOutro"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ICMSTot/a:vNF"/><xsl:call-template name="Newline" />
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:total/a:ISSQNtot">
			<xsl:text>4100;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ISSQNtot/a:vServ"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ISSQNtot/a:vBC"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ISSQNtot/a:vISS"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ISSQNtot/a:vPIS"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:ISSQNtot/a:vCOFINS"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:total/a:retTrib">
			<xsl:text>4200;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:retTrib/a:vRetPIS"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:retTrib/a:vRetCOFINS"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:retTrib/a:vRetCSLL"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:retTrib/a:vBCIRRF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:retTrib/a:vIRRF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:retTrib/a:vBCRetPrev"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:total/a:retTrib/a:vRetPrev"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:text>5000;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:modFrete"/><xsl:call-template name="Newline" />
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta">
			<xsl:text>5100;</xsl:text><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:CNPJ"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:CNPJ"/></xsl:if><xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:CPF"><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:CPF"/></xsl:if>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:xNome"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:IE"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:xEnder"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:xMun"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:transporta/a:UF"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:transp/a:retTransp">
			<xsl:text>5200;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:retTransp/a:vServ"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:retTransp/a:vBCRet"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:retTransp/a:pICMSRet"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:retTransp/a:vICMSRet"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:retTransp/a:CFOP"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:retTransp/a:cMunFG"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:transp/a:veicTransp">
			<xsl:text>5300;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:veicTransp/a:placa"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:veicTransp/a:UF"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:veicTransp/a:RNTC"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:reboque">
			<xsl:text>5400;</xsl:text><xsl:value-of select="a:placa"/>;<xsl:value-of select="a:UF"/>;<xsl:value-of select="a:RNTC"/><xsl:call-template name="Newline" />
		</xsl:for-each>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:transp/a:vol">
			<xsl:text>5700;</xsl:text><xsl:value-of select="a:qVol"/>;<xsl:value-of select="a:esp"/>;<xsl:value-of select="a:marca"/>;<xsl:value-of select="a:nVol"/>;<xsl:value-of select="a:pesoL"/>;<xsl:value-of select="a:pesoB"/><xsl:call-template name="Newline" />
			<xsl:for-each select="a:lacres">
				<xsl:text>5710;</xsl:text><xsl:value-of select="a:nLacre"/><xsl:call-template name="Newline" />
			</xsl:for-each>
		</xsl:for-each>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:cobr/a:fat">
			<xsl:text>6000;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:cobr/a:fat/a:nFat"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:cobr/a:fat/a:vOrig"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:cobr/a:fat/a:vDesc"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:cobr/a:fat/a:vLiq"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:cobr/a:dup">
			<xsl:text>6100;</xsl:text><xsl:value-of select="a:nDup"/>;<xsl:value-of select="a:dVenc"/>;<xsl:value-of select="a:vDup"/><xsl:call-template name="Newline" />
		</xsl:for-each>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:infAdic">
			<xsl:text>7000;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:infAdic/a:infAdFisco"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:infAdic/a:infCpl"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:infAdic/a:obsCont">
			<xsl:text>7100;</xsl:text><xsl:value-of select="a:xTexto"/><xsl:call-template name="Newline" />
		</xsl:for-each>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:infAdic/a:obsFisco">
			<xsl:text>7200;</xsl:text><xsl:value-of select="a:xTexto"/><xsl:call-template name="Newline" />
		</xsl:for-each>
		<xsl:for-each select="a:nfeProc/a:NFe/a:infNFe/a:infAdic/a:procRef">
			<xsl:text>7300;</xsl:text><xsl:value-of select="a:nProc"/>;<xsl:value-of select="a:indProc"/><xsl:call-template name="Newline" />
		</xsl:for-each>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:exporta">
			<xsl:text>8000;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:exporta/a:UFEmbarq"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:exporta/a:xLocEmbarq"/><xsl:call-template name="Newline" />
		</xsl:if>
		<xsl:if test="a:nfeProc/a:NFe/a:infNFe/a:compra">
			<xsl:text>9000;</xsl:text><xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:compra/a:xNEmp"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:compra/a:xPed"/>;<xsl:value-of select="a:nfeProc/a:NFe/a:infNFe/a:compra/a:xCont"/><xsl:call-template name="Newline" />
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
