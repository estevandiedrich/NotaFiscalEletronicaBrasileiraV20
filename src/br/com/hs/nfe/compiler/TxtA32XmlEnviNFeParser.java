package br.com.hs.nfe.compiler;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TxtA32XmlEnviNFeParser {
	public static final Logger logger = Logger.getLogger("TxtA32XmlEnviNFeParser");
	public static synchronized void txtA32XmlEnviNFeParser(String txtLine,Document xml,HashMap<String, String> params)
	{
		String[] tokens = txtLine.split("|");
		if(tokens[0].trim().equalsIgnoreCase("NOTA FISCAL"))
		{
			logger.info("Contem : NOTA FISCAL");
			if(tokens[1].trim().equalsIgnoreCase("1"))
			{
				logger.info("Contem : 1");
			}
		}
		else
		{
			if(tokens[0].trim().equalsIgnoreCase("A"))
			{
				logger.info("Contem : A");
				if(tokens[1].trim().equalsIgnoreCase("2.00"))
				{
					logger.info("Contem : 2.00");
					Element enviNFe = (org.apache.xerces.dom.ElementImpl)xml.createElement("enviNFe");
					enviNFe.setAttribute("versao","2.00");
					enviNFe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
					//enviNFe.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
					//enviNFe.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
					xml.appendChild(enviNFe);
					if(tokens[2].trim().equalsIgnoreCase("NFe"))
					{
						logger.info("Contem : NFe");
					}
				}
			}
			else
			{
				if(tokens[0].trim().equalsIgnoreCase("B"))
				{
					logger.info("Contem : B");
					Element nFe = (ElementImpl)xml.createElement("NFe");
					Element infNFe = (ElementImpl)xml.createElement("infNFe");
					Element enviNFe = null;
					NodeList enviNFeNL = xml.getElementsByTagName("enviNFe");
					if(enviNFeNL.getLength() > 0)
					{
						enviNFe = (ElementImpl)enviNFeNL.item(enviNFeNL.getLength()-1);
					}
					infNFe.setAttribute("versao", "2.00");
					infNFe.setAttribute("Id", "");
					Element cUF = (ElementImpl)xml.createElement("cUF");
					cUF.setTextContent(tokens[1].trim());
					Element cNF = (ElementImpl)xml.createElement("cNF");
					cNF.setTextContent(tokens[2].trim());
					Element natOp = (ElementImpl)xml.createElement("natOp");
					natOp.setTextContent(tokens[3].trim());
					Element indPag = (ElementImpl)xml.createElement("indPag");
					indPag.setTextContent(tokens[4].trim());
					Element mod = (ElementImpl)xml.createElement("mod");
					mod.setTextContent(tokens[5].trim());
					Element serie = (ElementImpl)xml.createElement("serie");
					serie.setTextContent(tokens[6].trim());
					Element nNF = (ElementImpl)xml.createElement("nNF");
					nNF.setTextContent(tokens[7].trim());
					Element dEmi = (ElementImpl)xml.createElement("dEmi");
					dEmi.setTextContent(tokens[8].trim());
					Element dSaiEnt = (ElementImpl)xml.createElement("dSaiEnt");
					dSaiEnt.setTextContent(tokens[9].trim());
					Element hSaiEnt = (ElementImpl)xml.createElement("hSaiEnt");
					hSaiEnt.setTextContent(tokens[10].trim());
					Element tpNF = (ElementImpl)xml.createElement("tpNF");
					tpNF.setTextContent(tokens[11].trim());
					Element cMunFG = (ElementImpl)xml.createElement("cMunFG");
					cMunFG.setTextContent(tokens[12].trim());
					Element tpImp = (ElementImpl)xml.createElement("tpImp");
					tpImp.setTextContent(tokens[13].trim());
					Element tpEmis = (ElementImpl)xml.createElement("tpEmis");
					tpEmis.setTextContent(tokens[14].trim());
					Element cDV = (ElementImpl)xml.createElement("cDV");
					cDV.setTextContent(tokens[15].trim());
					Element tpAmb = (ElementImpl)xml.createElement("tpAmb");
					tpAmb.setTextContent(tokens[16].trim());
					Element finNFe = (ElementImpl)xml.createElement("finNFe");
					finNFe.setTextContent(tokens[17].trim());
					Element procEmi = (ElementImpl)xml.createElement("procEmi");
					procEmi.setTextContent(tokens[18].trim());
					Element verProc = (ElementImpl)xml.createElement("verProc");
					verProc.setTextContent(tokens[19].trim());
					
					Element ide = (ElementImpl)xml.createElement("ide");
					ide.appendChild(cUF);
					ide.appendChild(cNF);
					ide.appendChild(natOp);
					ide.appendChild(indPag);
					ide.appendChild(mod);
					ide.appendChild(serie);
					ide.appendChild(nNF);
					ide.appendChild(dEmi);
					ide.appendChild(dSaiEnt);
					ide.appendChild(hSaiEnt);
					ide.appendChild(tpNF);
					ide.appendChild(cMunFG);
					ide.appendChild(tpImp);
					ide.appendChild(tpEmis);
					ide.appendChild(cDV);
					ide.appendChild(tpAmb);
					ide.appendChild(finNFe);
					ide.appendChild(procEmi);
					ide.appendChild(verProc);
					infNFe.appendChild(ide);
					nFe.appendChild(infNFe);
					enviNFe.appendChild(nFe);
					
				}
				else
				{
					if(tokens[0].trim().equalsIgnoreCase("C"))
					{
						logger.info("Contem : C");
						Element emit = (ElementImpl)xml.createElement("emit");
						Element xNome = (ElementImpl)xml.createElement("xNome");
						xNome.setTextContent(tokens[1].trim());
						emit.appendChild(xNome);
						Element xFant = (ElementImpl)xml.createElement("xFant");
						xFant.setTextContent(tokens[2].trim());
						emit.appendChild(xFant);
						Element ie = (ElementImpl)xml.createElement("IE");
						ie.setTextContent(tokens[3].trim());
						emit.appendChild(ie);
						if(!tokens[4].trim().equalsIgnoreCase(""))
						{
							Element ieST = (ElementImpl)xml.createElement("IEST");
							ieST.setTextContent(tokens[4].trim());
							emit.appendChild(ieST);
						}
						Element im = (ElementImpl)xml.createElement("IM");
						im.setTextContent(tokens[5].trim());
						emit.appendChild(im);
						Element cnae = (ElementImpl)xml.createElement("CNAE");
						cnae.setTextContent(tokens[6].trim());
						emit.appendChild(cnae);
						Element crt = (ElementImpl)xml.createElement("CRT");
						crt.setTextContent(tokens[7].trim());
						emit.appendChild(crt);
						
						NodeList infNFeNL = xml.getElementsByTagName("infNFe");
						if(infNFeNL.getLength() > 0)
						{
							Element infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
							infNFe.appendChild(emit);
						}
					}
					else
					{
						if(tokens[0].trim().equalsIgnoreCase("C02"))
						{
							logger.info("Contem : C02");
							Element infNFe = null;
							Element emit = null;
							Element xNome = null;
							NodeList xNomeNL = xml.getElementsByTagName("xNome");
							if(xNomeNL.getLength() > 0)
							{
								xNome = (ElementImpl)xNomeNL.item(xNomeNL.getLength()-1);
							}
							NodeList emitNL = xml.getElementsByTagName("emit");
							if(emitNL.getLength() > 0)
							{
								emit = (ElementImpl)emitNL.item(emitNL.getLength()-1);
							}
							Element cnpj = (ElementImpl)xml.createElement("CNPJ");
							cnpj.setTextContent(tokens[1].trim());
							emit.insertBefore(cnpj, xNome);
							NodeList infNFeNL = xml.getElementsByTagName("infNFe");
							if(infNFeNL.getLength() > 0)
							{
								infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
								infNFe.appendChild(emit);
							}
						}
						else
						{
							if(tokens[0].trim().equalsIgnoreCase("C05"))
							{
								logger.info("Contem : C05");
								Element enderEmit = (ElementImpl)xml.createElement("enderEmit");
								Element xLgr = (ElementImpl)xml.createElement("xLgr");
								xLgr.setTextContent(tokens[1].trim());
								enderEmit.appendChild(xLgr);
								Element nro = (ElementImpl)xml.createElement("nro");
								nro.setTextContent(tokens[2].trim());
								enderEmit.appendChild(nro);
								if(!tokens[3].trim().equalsIgnoreCase(""))
								{
									Element xCpl = (ElementImpl)xml.createElement("xCpl");
									xCpl.setTextContent(tokens[3].trim());
									enderEmit.appendChild(xCpl);
								}
								Element xBairro = (ElementImpl)xml.createElement("xBairro");
								xBairro.setTextContent(tokens[4].trim());
								enderEmit.appendChild(xBairro);
								Element cMun = (ElementImpl)xml.createElement("cMun");
								cMun.setTextContent(tokens[5].trim());
								enderEmit.appendChild(cMun);
								Element xMun = (ElementImpl)xml.createElement("xMun");
								xMun.setTextContent(tokens[6].trim());
								enderEmit.appendChild(xMun);
								Element uf = (ElementImpl)xml.createElement("UF");
								uf.setTextContent(tokens[7].trim());
								enderEmit.appendChild(uf);
								Element cep = (ElementImpl)xml.createElement("CEP");
								cep.setTextContent(tokens[8].trim());
								enderEmit.appendChild(cep);
								Element cPais = (ElementImpl)xml.createElement("cPais");
								cPais.setTextContent(tokens[9].trim());
								enderEmit.appendChild(cPais);
								Element xPais = (ElementImpl)xml.createElement("xPais");
								xPais.setTextContent(tokens[10].trim());
								enderEmit.appendChild(xPais);
								if(!tokens[11].trim().equalsIgnoreCase(""))
								{
									Element fone = (ElementImpl)xml.createElement("fone");
									fone.setTextContent(tokens[11].trim());
									enderEmit.appendChild(fone);
								}
								
								NodeList emitNL = xml.getElementsByTagName("emit");
								Element emit = null;
								if(emitNL.getLength() > 0)
								{
									emit = (ElementImpl)emitNL.item(emitNL.getLength()-1);
								}
								NodeList IENL = emit.getElementsByTagName("IE");
								if(IENL.getLength() > 0)
								{
									Element IE = (ElementImpl)IENL.item(IENL.getLength()-1);
									emit.insertBefore(enderEmit, IE);
								}
							}
							else
							{
								if(tokens[0].trim().equalsIgnoreCase("E"))
								{
									logger.info("Contem : E");
									Element dest = (ElementImpl)xml.createElement("dest");
									Element xNome = (ElementImpl)xml.createElement("xNome");
									xNome.setTextContent(tokens[1].trim());
									dest.appendChild(xNome);
									Element ie = (ElementImpl)xml.createElement("IE");
									ie.setTextContent(tokens[2].trim());
									dest.appendChild(ie);
									if(!tokens[3].trim().equalsIgnoreCase(""))
									{
										Element isuf = (ElementImpl)xml.createElement("ISUF");
										isuf.setTextContent(tokens[3].trim());
										dest.appendChild(isuf);
									}
									if(!tokens[4].trim().equalsIgnoreCase(""))
									{
										Element email = (ElementImpl)xml.createElement("email");
										email.setTextContent(tokens[4].trim());
										dest.appendChild(email);
									}
									NodeList infNFeNL = xml.getElementsByTagName("infNFe");
									if(infNFeNL.getLength() > 0)
									{
										Element infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
										infNFe.appendChild(dest);
									}
								}
								else
								{
									if(tokens[0].trim().equalsIgnoreCase("E02"))
									{
										logger.info("Contem : E02");
										
										Element cnpj = (ElementImpl)xml.createElement("CNPJ");
										cnpj.setTextContent(tokens[1].trim());
										Element dest = null;
										NodeList destNL = (ElementImpl)xml.getElementsByTagName("dest");
										if(destNL.getLength() > 0)
										{
											dest = (ElementImpl)destNL.item(destNL.getLength()-1);
										}
										Element xNome = null;
										NodeList xNomeNL = (ElementImpl)dest.getElementsByTagName("xNome");
										if(xNomeNL.getLength() > 0)
										{
											xNome = (ElementImpl)xNomeNL.item(xNomeNL.getLength()-1);
										}
										dest.insertBefore(cnpj,xNome);
									}
									else
									{
										if(tokens[0].trim().equalsIgnoreCase("E05"))
										{
											logger.info("Contem : E05");
											Element enderDest = (ElementImpl)xml.createElement("enderDest");
											Element xLgr = (ElementImpl)xml.createElement("xLgr");
											xLgr.setTextContent(tokens[1].trim());
											enderDest.appendChild(xLgr);
											Element nro = (ElementImpl)xml.createElement("nro");
											nro.setTextContent(tokens[2].trim());
											enderDest.appendChild(nro);
											if(!tokens[3].trim().equalsIgnoreCase(""))
											{
												Element xCpl = (ElementImpl)xml.createElement("xCpl");
												xCpl.setTextContent(tokens[3].trim());
												enderDest.appendChild(xCpl);
											}
											Element xBairro = (ElementImpl)xml.createElement("xBairro");
											xBairro.setTextContent(tokens[4].trim());
											enderDest.appendChild(xBairro);
											Element cMun = (ElementImpl)xml.createElement("cMun");
											cMun.setTextContent(tokens[5].trim());
											enderDest.appendChild(cMun);
											Element xMun = (ElementImpl)xml.createElement("xMun");
											xMun.setTextContent(tokens[6].trim());
											enderDest.appendChild(xMun);
											Element uf = (ElementImpl)xml.createElement("UF");
											uf.setTextContent(tokens[7].trim());
											enderDest.appendChild(uf);
											Element cep = (ElementImpl)xml.createElement("CEP");
											cep.setTextContent(tokens[8].trim());
											enderDest.appendChild(cep);
											Element cPais = (ElementImpl)xml.createElement("cPais");
											cPais.setTextContent(tokens[9].trim());
											enderDest.appendChild(cPais);
											Element xPais = (ElementImpl)xml.createElement("xPais");
											xPais.setTextContent(tokens[10].trim());
											enderDest.appendChild(xPais);
											if(!tokens[11].trim().equalsIgnoreCase(""))
											{
												Element fone = (ElementImpl)xml.createElement("fone");
												fone.setTextContent(tokens[11].trim());
												enderDest.appendChild(fone);
											}
											NodeList destNL = (ElementImpl)xml.getElementsByTagName("dest");
											Element dest = null;
											if(destNL.getLength() > 0)
											{
												dest = (ElementImpl)destNL.item(destNL.getLength()-1);
											}
											NodeList IENL = dest.getElementsByTagName("IE");
											if(IENL.getLength() > 0)
											{
												Element IE = (ElementImpl)IENL.item(IENL.getLength()-1);
												dest.insertBefore(enderDest, IE);
											}
										}
										else
										{
											if(tokens[0].trim().equalsIgnoreCase("H"))
											{
												logger.info("Contem : H");
												Element det = (ElementImpl)xml.createElement("det");
												Element infAdProd = null;
												det.setAttribute("nItem", tokens[1].trim());
												if(!tokens[2].trim().equalsIgnoreCase(""))
												{
													infAdProd = (ElementImpl)xml.createElement("infAdProd");
													infAdProd.setTextContent(tokens[2].trim());
												}
												det.appendChild(infAdProd);
												NodeList infNFeNL = xml.getElementsByTagName("infNFe");
												if(infNFeNL.getLength() > 0)
												{
													Element infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
													infNFe.appendChild(det);
												}
											}
											else
											{
												if(tokens[0].trim().equalsIgnoreCase("I"))
												{
													logger.info("Contem : I");
													NodeList detNL = xml.getElementsByTagName("det");
													//Element det = (ElementImpl)xml.createElement("det");
													//det.setAttribute("nItem", String.valueOf(detNL.getLength()+1));
													Element prod = (ElementImpl)xml.createElement("prod");
													Element cProd = (ElementImpl)xml.createElement("cProd");
													cProd.setTextContent(tokens[1].trim());
													prod.appendChild(cProd);
													Element cEAN = (ElementImpl)xml.createElement("cEAN");
													cEAN.setTextContent(tokens[2].trim());
													prod.appendChild(cEAN);
													Element xProd = (ElementImpl)xml.createElement("xProd");
													xProd.setTextContent(tokens[3].trim());
													prod.appendChild(xProd);
													Element ncm = (ElementImpl)xml.createElement("NCM");
													ncm.setTextContent(tokens[4].trim());
													prod.appendChild(ncm);
													if(!tokens[5].trim().equalsIgnoreCase(""))
													{
														Element extipi = (ElementImpl)xml.createElement("EXTIPI");
														extipi.setTextContent(tokens[5].trim());
														prod.appendChild(extipi);
													}
													Element cfop = (ElementImpl)xml.createElement("CFOP");
													cfop.setTextContent(tokens[6].trim());
													prod.appendChild(cfop);
													Element uCom = (ElementImpl)xml.createElement("uCom");
													uCom.setTextContent(tokens[7].trim());
													prod.appendChild(uCom);
													Element qCom = (ElementImpl)xml.createElement("qCom");
													qCom.setTextContent(tokens[8].trim());
													prod.appendChild(qCom);
													Element vUnCom = (ElementImpl)xml.createElement("vUnCom");
													vUnCom.setTextContent(tokens[9].trim());
													prod.appendChild(vUnCom);
													Element vProd = (ElementImpl)xml.createElement("vProd");
													vProd.setTextContent(tokens[10].trim());
													prod.appendChild(vProd);
													Element cEANTrib = (ElementImpl)xml.createElement("cEANTrib");
													cEANTrib.setTextContent(tokens[11].trim());
													prod.appendChild(cEANTrib);
													Element uTrib = (ElementImpl)xml.createElement("uTrib");
													uTrib.setTextContent(tokens[12].trim());
													prod.appendChild(uTrib);
													Element qTrib = (ElementImpl)xml.createElement("qTrib");
													qTrib.setTextContent(tokens[13].trim());
													prod.appendChild(qTrib);
													Element vUnTrib = (ElementImpl)xml.createElement("vUnTrib");
													vUnTrib.setTextContent(tokens[14].trim());
													prod.appendChild(vUnTrib);
													if(!tokens[15].trim().equalsIgnoreCase(""))
													{
														Element vFrete = (ElementImpl)xml.createElement("vFrete");
														vFrete.setTextContent(tokens[15].trim());
														prod.appendChild(vFrete);
													}
													if(!tokens[16].trim().equalsIgnoreCase(""))
													{
														Element vSeg = (ElementImpl)xml.createElement("vSeg");
														vSeg.setTextContent(tokens[16].trim());
														prod.appendChild(vSeg);
													}
													if(!tokens[17].trim().equalsIgnoreCase(""))
													{
														Element vDesc = (ElementImpl)xml.createElement("vDesc");
														vDesc.setTextContent(tokens[17].trim());
														prod.appendChild(vDesc);
													}
													if(!tokens[18].trim().equalsIgnoreCase(""))
													{
														Element vOutro = (ElementImpl)xml.createElement("vOutro");
														vOutro.setTextContent(tokens[18].trim());
														prod.appendChild(vOutro);
													}
													Element indTot = (ElementImpl)xml.createElement("indTot");
													indTot.setTextContent(tokens[19].trim());
													prod.appendChild(indTot);
													if(!tokens[20].trim().equalsIgnoreCase(""))
													{
														Element xPed = (ElementImpl)xml.createElement("xPed");
														xPed.setTextContent(tokens[20].trim());
														prod.appendChild(xPed);
													}
													if(!tokens[21].trim().equalsIgnoreCase(""))
													{
														Element nItemPed = (ElementImpl)xml.createElement("nItemPed");
														nItemPed.setTextContent(tokens[21].trim());
														prod.appendChild(nItemPed);
													}
													Element det = null;
													if(detNL.getLength() > 0)
													{
														det = (ElementImpl)detNL.item(detNL.getLength()-1);
													}
													det.appendChild(prod);
													
													NodeList infNFeNL = xml.getElementsByTagName("infNFe");
													if(infNFeNL.getLength() > 0)
													{
														Element infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
														infNFe.appendChild(det);
													}
												}
												else
												{
													if(tokens[0].trim().equalsIgnoreCase("M"))
													{
														logger.info("Contem : M");
														
													}
													else
													{
														if(tokens[0].trim().equalsIgnoreCase("N"))
														{
															logger.info("Contem : N");
														}
														else
														{
															if(tokens[0].trim().equalsIgnoreCase("N10c"))
															{
																boolean impostoNovo = false;
																boolean icmsNovo = false;
																NodeList impostoNL = xml.getElementsByTagName("imposto");
																Element imposto = null;
																if(impostoNL.getLength() > 0)
																{
																	imposto = (ElementImpl)impostoNL.item(impostoNL.getLength()-1);
																}
																else
																{
																	impostoNovo = true;
																	imposto = (ElementImpl)xml.createElement("imposto");
																}
																NodeList icmsNL = xml.getElementsByTagName("ICMS");
																Element icms = null;
																if(icmsNL.getLength() > 0)
																{
																	icms = (ElementImpl)icmsNL.item(icmsNL.getLength()-1);
																}
																else
																{
																	icmsNovo = true;
																	icms = (ElementImpl)xml.createElement("ICMS");
																}
																
																Element icmsSn101 = (ElementImpl)xml.createElement("ICMSSN101");
																Element orig = (ElementImpl)xml.createElement("orig");
																orig.setTextContent(tokens[1].trim());
																icmsSn101.appendChild(orig);
																Element csosn = (ElementImpl)xml.createElement("CSOSN");
																csosn.setTextContent(tokens[2].trim());
																icmsSn101.appendChild(csosn);
																Element pCredSN = (ElementImpl)xml.createElement("pCredSN");
																pCredSN.setTextContent(tokens[3].trim());
																icmsSn101.appendChild(pCredSN);
																Element vCredICMSSN = (ElementImpl)xml.createElement("vCredICMSSN");
																vCredICMSSN.setTextContent(tokens[4].trim());
																icmsSn101.appendChild(vCredICMSSN);
																
																
																icms.appendChild(icmsSn101);
																
																if(icmsNovo)
																{
																	imposto.appendChild(icms);
																}
																if(impostoNovo)
																{
																	NodeList detNL = xml.getElementsByTagName("det");
																	Element det = null;
																	if(detNL.getLength() > 0)
																	{
																		det = (ElementImpl)detNL.item(detNL.getLength()-1);
																		det.appendChild(imposto);
																	}
																}
															}
															else
															{
																if(tokens[0].trim().equalsIgnoreCase("O"))
																{
																	logger.info("Contem : O");
																	boolean impostoNovo = false;
																	NodeList impostoNL = xml.getElementsByTagName("imposto");
																	Element imposto = null;
																	if(impostoNL.getLength() > 0)
																	{
																		imposto = (ElementImpl)impostoNL.item(impostoNL.getLength()-1);
																	}
																	else
																	{
																		impostoNovo = true;
																		imposto = (ElementImpl)xml.createElement("imposto");
																	}
																	
																	Element ipi = (ElementImpl)xml.createElement("IPI");
																	if(!tokens[1].trim().equalsIgnoreCase(""))
																	{
																		Element clEnq = (ElementImpl)xml.createElement("clEnq");
																		clEnq.setTextContent(tokens[1].trim());
																		ipi.appendChild(clEnq);
																	}
																	if(!tokens[2].trim().equalsIgnoreCase(""))
																	{
																		Element CNPJProd = (ElementImpl)xml.createElement("CNPJProd");
																		CNPJProd.setTextContent(tokens[2].trim());
																		ipi.appendChild(CNPJProd);
																	}
																	if(!tokens[3].trim().equalsIgnoreCase(""))
																	{
																		Element cSelo = (ElementImpl)xml.createElement("cSelo");
																		cSelo.setTextContent(tokens[3].trim());
																		ipi.appendChild(cSelo);
																	}
																	if(!tokens[4].trim().equalsIgnoreCase(""))
																	{
																		Element qSelo = (ElementImpl)xml.createElement("qSelo");
																		qSelo.setTextContent(tokens[4].trim());
																		ipi.appendChild(qSelo);
																	}
																	Element cEnq = (ElementImpl)xml.createElement("cEnq");
																	cEnq.setTextContent(tokens[5].trim());
																	ipi.appendChild(cEnq);
																	
																	imposto.appendChild(ipi);
																	
																	if(impostoNovo)
																	{
																		NodeList detNL = xml.getElementsByTagName("det");
																		Element det = null;
																		if(detNL.getLength() > 0)
																		{
																			det = (ElementImpl)detNL.item(detNL.getLength()-1);
																			det.appendChild(imposto);
																		}
																	}
																}
																else
																{
																	if(tokens[0].trim().equalsIgnoreCase("O08"))
																	{
																		logger.info("Contem : O08");
																		NodeList ipiNL = xml.getElementsByTagName("IPI");
																		Element ipi = null;
																		if(ipiNL.getLength() > 0)
																		{
																			ipi = (ElementImpl)ipiNL.item(ipiNL.getLength()-1);
																		}
																		Element ipiNT = (ElementImpl)xml.createElement("IPINT");
																		Element cst = (ElementImpl)xml.createElement("CST");
																		cst.setTextContent(tokens[1].trim());
																		ipiNT.appendChild(cst);
																		ipi.appendChild(ipiNT);
																	}
																	else
																	{
																		if(tokens[0].trim().equalsIgnoreCase("Q"))
																		{
																			logger.info("Contem : Q");
																			
																		}
																		else
																		{
																			if(tokens[0].trim().equalsIgnoreCase("Q04"))
																			{
																				logger.info("Contem : Q04");
																				
																			}
																			else
																			{
																				
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
