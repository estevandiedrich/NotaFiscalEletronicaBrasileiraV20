package br.com.hs.nfe.compiler;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.util.TrataCaracter;


public class Txt2XmlEnviNFeParser {
	public static final Logger logger = Logger.getLogger("Txt2XmlEnviNFeParser");
	public static synchronized void txt2XmlEnviNFeParser(String txtLine,Document xml,HashMap<String, String> params)
	{
		txtLine = TrataCaracter.trata(txtLine);
		String[] tokens = txtLine.split(";");
		if(tokens[0].trim().equalsIgnoreCase("0000"))
		{
			logger.info("Contem : 0000");
			if(tokens[1].equalsIgnoreCase("2.00"))
			{
				logger.info("Contem : 2.00");
				if(tokens[2].equalsIgnoreCase("ENVIO"))
				{
					logger.info("Contem : ENVIO");
					Element enviNFe = (org.apache.xerces.dom.ElementImpl)xml.createElement("enviNFe");
					enviNFe.setAttribute("versao","2.00");
					enviNFe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
					//enviNFe.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
					//enviNFe.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
					xml.appendChild(enviNFe);
				}
				else
				{
					
				}
			}
			else
			{
				
			}
		}
		else
		{
			if(tokens[0].trim().equalsIgnoreCase("1000"))
			{
				logger.info("Contem : 1000");
				NodeList enviNFeNL = xml.getElementsByTagName("enviNFe");
				logger.info("idLote : "+tokens[1].trim());
				Element idLote = (ElementImpl)xml.createElement("idLote");
				idLote.setTextContent(tokens[1].trim());
				if(enviNFeNL.getLength() > 0)
				{
					Element enviNFe = (ElementImpl)enviNFeNL.item(enviNFeNL.getLength()-1);
					enviNFe.appendChild(idLote);
				}
			}
			else
			{
				if(tokens[0].trim().equalsIgnoreCase("2000"))
				{
					logger.info("Contem : 2000");
					if(tokens[1].equalsIgnoreCase("2.00"))
					{
						logger.info("Contem : 2.00");
						Element nFe = (ElementImpl)xml.createElement("NFe");
						Element infNFe = (ElementImpl)xml.createElement("infNFe");
						nFe.appendChild(infNFe);
						NodeList enviNFeNL = xml.getElementsByTagName("enviNFe");
						infNFe.setAttribute("versao", "2.00");
						infNFe.setAttribute("Id", tokens[2].trim());
						if(enviNFeNL.getLength() > 0)
						{
							Element enviNFe = (ElementImpl)enviNFeNL.item(enviNFeNL.getLength()-1);
							enviNFe.appendChild(nFe);
						}
					}
					else
					{
						
					}
				}
				else
				{
					if(tokens[0].trim().equalsIgnoreCase("2100"))
					{
						logger.info("Contem : 2100");
						Element cUF = (ElementImpl)xml.createElement("cUF");
						cUF.setTextContent(tokens[1].trim());
						Element cNF = (ElementImpl)xml.createElement("cNF");
						if(tokens[2].trim().equalsIgnoreCase("cNF"))
						{
							String cNFTemp = Long.toString(Math.round(Math.random()*99999999));
							cNFTemp = "00000000".substring(0, 8 - cNFTemp.length()) + cNFTemp;
							cNF.setTextContent(cNFTemp);
						}
						else
						{
							cNF.setTextContent(tokens[2].trim());
						}
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
						if(!tokens[9].trim().equalsIgnoreCase(""))
						{
							ide.appendChild(dSaiEnt);
						}
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
						
						NodeList infNFeNL = xml.getElementsByTagName("infNFe");
						if(infNFeNL.getLength() > 0)
						{
							Element infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
							infNFe.appendChild(ide);
						}
					}
					else
					{
						if(tokens[0].trim().equalsIgnoreCase("2110"))
						{
							logger.info("Contem : 2110");
//							NodeList NFrefNL = xml.getElementsByTagName("NFref");
							Element NFref = null;
//							if(NFrefNL.getLength() > 0)
//							{
//								NFref = (ElementImpl)NFrefNL.item(NFrefNL.getLength()-1);
//							}
//							else
//							{
								NFref = (ElementImpl)xml.createElement("NFref");
//							}
							Element refNFe = (ElementImpl)xml.createElement("refNFe");
							refNFe.setTextContent(tokens[1].trim());
							
							NFref.appendChild(refNFe);
							
							NodeList tpImpNL = xml.getElementsByTagName("tpImp");
							Element tpImp = null;
							if(tpImpNL.getLength() > 0)
							{
								tpImp = (ElementImpl)tpImpNL.item(tpImpNL.getLength()-1);
							}
							NodeList ideNL = xml.getElementsByTagName("ide");
							if(ideNL.getLength() > 0)
							{
								Element ide = (ElementImpl)ideNL.item(ideNL.getLength()-1);
								ide.insertBefore(NFref, tpImp);
							}
						}
						else
						{
							if(tokens[0].trim().equalsIgnoreCase("2120"))
							{
								Element NFref = (ElementImpl)xml.createElement("NFref");
								Element refNF = (ElementImpl)xml.createElement("refNF");
								Element refNFcUF = (ElementImpl)xml.createElement("cUF");
								refNFcUF.setTextContent(tokens[1].trim());
								Element refNFAAMM = (ElementImpl)xml.createElement("AAMM");
								refNFAAMM.setTextContent(tokens[2].trim());
								Element refNFCNPJ = (ElementImpl)xml.createElement("CNPJ");
								refNFCNPJ.setTextContent(tokens[3].trim());
								Element refNFmod = (ElementImpl)xml.createElement("mod");
								refNFmod.setTextContent(tokens[4].trim());
								Element refNFserie = (ElementImpl)xml.createElement("serie");
								refNFserie.setTextContent(tokens[5].trim());
								Element refNFnNF = (ElementImpl)xml.createElement("nNF");
								refNFnNF.setTextContent(tokens[6].trim());
								
								refNF.appendChild(refNFcUF);
								refNF.appendChild(refNFAAMM);
								refNF.appendChild(refNFCNPJ);
								refNF.appendChild(refNFmod);
								refNF.appendChild(refNFserie);
								refNF.appendChild(refNFnNF);
								
								NFref.appendChild(refNF);
								
								NodeList tpImpNL = xml.getElementsByTagName("tpImp");
								Element tpImp = null;
								if(tpImpNL.getLength() > 0)
								{
									tpImp = (ElementImpl)tpImpNL.item(tpImpNL.getLength()-1);
								}
								NodeList ideNL = xml.getElementsByTagName("ide");
								if(ideNL.getLength() > 0)
								{
									Element ide = (ElementImpl)ideNL.item(ideNL.getLength()-1);
									ide.insertBefore(NFref, tpImp);
								}
								
							}
							else
							{
								if(tokens[0].trim().equalsIgnoreCase("2130"))
								{
									NodeList NFrefNL = xml.getElementsByTagName("NFref");
									Element NFref = null;
									if(NFrefNL.getLength() > 0)
									{
										NFref = (ElementImpl)NFrefNL.item(NFrefNL.getLength()-1);
									}
									else
									{
										NFref = (ElementImpl)xml.createElement("NFref");
									}
									
									Element refNFP = (ElementImpl)xml.createElement("refNFP");
									Element refNFPcUF = (ElementImpl)xml.createElement("cUF");
									refNFPcUF.setTextContent(tokens[1].trim());
									Element refNFPAAMM = (ElementImpl)xml.createElement("AAMM");
									refNFPAAMM.setTextContent(tokens[2].trim());
									Element refNFPCNPJ = (ElementImpl)xml.createElement("CNPJ");
									refNFPCNPJ.setTextContent(tokens[3].trim());
									Element refNFPCPF = (ElementImpl)xml.createElement("CPF");
									refNFPCPF.setTextContent(tokens[4].trim());
									Element refNFPIE = (ElementImpl)xml.createElement("IE");
									refNFPIE.setTextContent(tokens[5].trim());
									Element refNFPmod = (ElementImpl)xml.createElement("mod");
									refNFPmod.setTextContent(tokens[6].trim());
									Element refNFPserie = (ElementImpl)xml.createElement("serie");
									refNFPserie.setTextContent(tokens[7].trim());
									Element refNFPnNF = (ElementImpl)xml.createElement("nNF");
									refNFPnNF.setTextContent(tokens[8].trim());
									
									refNFP.appendChild(refNFPcUF);
									refNFP.appendChild(refNFPAAMM);
									refNFP.appendChild(refNFPCNPJ);
									refNFP.appendChild(refNFPCPF);
									refNFP.appendChild(refNFPIE);
									refNFP.appendChild(refNFPmod);
									refNFP.appendChild(refNFPserie);
									refNFP.appendChild(refNFPnNF);
									NFref.appendChild(refNFP);
									
									NodeList tpImpNL = xml.getElementsByTagName("tpImp");
									Element tpImp = null;
									if(tpImpNL.getLength() > 0)
									{
										tpImp = (ElementImpl)tpImpNL.item(tpImpNL.getLength()-1);
									}
									NodeList ideNL = xml.getElementsByTagName("ide");
									if(ideNL.getLength() > 0)
									{
										Element ide = (ElementImpl)ideNL.item(ideNL.getLength()-1);
										ide.insertBefore(NFref, tpImp);
									}
								}
								else
								{
									if(tokens[0].trim().equalsIgnoreCase("2140"))
									{
										
										NodeList NFrefNL = xml.getElementsByTagName("NFref");
										Element NFref = null;
										if(NFrefNL.getLength() > 0)
										{
											NFref = (ElementImpl)NFrefNL.item(NFrefNL.getLength()-1);
										}
										else
										{
											NFref = (ElementImpl)xml.createElement("NFref");
										}
										
										Element refCTe = (ElementImpl)xml.createElement("refCTe");
										refCTe.setTextContent(tokens[1]);
										
										NFref.appendChild(refCTe);
										
										NodeList tpImpNL = xml.getElementsByTagName("tpImp");
										Element tpImp = null;
										if(tpImpNL.getLength() > 0)
										{
											tpImp = (ElementImpl)tpImpNL.item(tpImpNL.getLength()-1);
										}
										NodeList ideNL = xml.getElementsByTagName("ide");
										if(ideNL.getLength() > 0)
										{
											Element ide = (ElementImpl)ideNL.item(ideNL.getLength()-1);
											ide.insertBefore(NFref, tpImp);
										}
									}
									else
									{
										if(tokens[0].trim().equalsIgnoreCase("2150"))
										{
											NodeList NFrefNL = xml.getElementsByTagName("NFref");
											Element NFref = null;
											if(NFrefNL.getLength() > 0)
											{
												NFref = (ElementImpl)NFrefNL.item(NFrefNL.getLength()-1);
											}
											else
											{
												NFref = (ElementImpl)xml.createElement("NFref");
											}
											Element refECF = (ElementImpl)xml.createElement("refECF");
											Element refECFmod = (ElementImpl)xml.createElement("mod");
											refECFmod.setTextContent(tokens[1].trim());
											Element refECFnECF = (ElementImpl)xml.createElement("nECF");
											refECFnECF.setTextContent(tokens[2].trim());
											Element refECFnCOO = (ElementImpl)xml.createElement("nCOO");
											refECFnCOO.setTextContent(tokens[3].trim());
											
											refECF.appendChild(refECFmod);
											refECF.appendChild(refECFnECF);
											refECF.appendChild(refECFnCOO);
											
											NFref.appendChild(refECF);
											
											NodeList tpImpNL = xml.getElementsByTagName("tpImp");
											Element tpImp = null;
											if(tpImpNL.getLength() > 0)
											{
												tpImp = (ElementImpl)tpImpNL.item(tpImpNL.getLength()-1);
											}
											NodeList ideNL = xml.getElementsByTagName("ide");
											if(ideNL.getLength() > 0)
											{
												Element ide = (ElementImpl)ideNL.item(ideNL.getLength()-1);
												ide.insertBefore(NFref, tpImp);
											}
										}
										else
										{
											if(tokens[0].trim().equalsIgnoreCase("2180"))
											{
												
												Element dhCont = (ElementImpl)xml.createElement("dhCont");
												dhCont.setTextContent(tokens[1].trim());
												Element xJust = (ElementImpl)xml.createElement("xJust");
												xJust.setTextContent(tokens[2].trim());
												
												NodeList ideNL = xml.getElementsByTagName("ide");
												if(ideNL.getLength() > 0)
												{
													Element ide = (ElementImpl)ideNL.item(ideNL.getLength()-1);
													ide.appendChild(dhCont);
													ide.appendChild(xJust);
												}
											}
											else
											{
												if(tokens[0].trim().equalsIgnoreCase("2200"))
												{
													logger.info("Contem : 2200");
													Element emit = (ElementImpl)xml.createElement("emit");
													if(tokens[1].trim().length() == 14)
													{
														Element cnpj = (ElementImpl)xml.createElement("CNPJ");
														cnpj.setTextContent(tokens[1].trim());
														emit.appendChild(cnpj);
													}
													else
													{
														if(tokens[1].trim().length() == 11)
														{
															Element cpf = (ElementImpl)xml.createElement("CPF");
															cpf.setTextContent(tokens[1].trim());
															emit.appendChild(cpf);
														}
													}
													Element xNome = (ElementImpl)xml.createElement("xNome");
													xNome.setTextContent(tokens[2].trim());
													emit.appendChild(xNome);
													Element xFant = (ElementImpl)xml.createElement("xFant");
													xFant.setTextContent(tokens[3].trim());
													emit.appendChild(xFant);
													Element ie = (ElementImpl)xml.createElement("IE");
													ie.setTextContent(tokens[4].trim());
													emit.appendChild(ie);
													if(!tokens[5].trim().equalsIgnoreCase(""))
													{
														Element ieST = (ElementImpl)xml.createElement("IEST");
														ieST.setTextContent(tokens[5].trim());
														emit.appendChild(ieST);
													}
													if(!tokens[6].trim().equalsIgnoreCase(""))
													{
														Element im = (ElementImpl)xml.createElement("IM");
														im.setTextContent(tokens[6].trim());
														emit.appendChild(im);
													}
													if(!tokens[7].trim().equalsIgnoreCase(""))
													{
														Element cnae = (ElementImpl)xml.createElement("CNAE");
														cnae.setTextContent(tokens[7].trim());
														emit.appendChild(cnae);
													}
													Element crt = (ElementImpl)xml.createElement("CRT");
													crt.setTextContent(tokens[8].trim());
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
													if(tokens[0].trim().equalsIgnoreCase("2210"))
													{
														logger.info("Contem : 2210");
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
														if(tokens[0].trim().equalsIgnoreCase("2250"))
														{
															logger.info("Contem : 2250");
															Element avulsa = (ElementImpl)xml.createElement("avulsa");
															Element cnpj = (ElementImpl)xml.createElement("CNPJ");
															cnpj.setTextContent(tokens[1].trim());
															avulsa.appendChild(cnpj);
															Element xOrgao = (ElementImpl)xml.createElement("xOrgao");
															xOrgao.setTextContent(tokens[2].trim());
															avulsa.appendChild(xOrgao);
															Element matr = (ElementImpl)xml.createElement("matr");
															matr.setTextContent(tokens[3].trim());
															avulsa.appendChild(matr);
															Element xAgente = (ElementImpl)xml.createElement("xAgente");
															xAgente.setTextContent(tokens[4].trim());
															avulsa.appendChild(xAgente);
															if(!tokens[5].trim().equalsIgnoreCase(""))
															{
																Element fone = (ElementImpl)xml.createElement("fone");
																fone.setTextContent(tokens[5].trim());
																avulsa.appendChild(fone);
															}
															Element uf = (ElementImpl)xml.createElement("UF");
															uf.setTextContent(tokens[6].trim());
															avulsa.appendChild(uf);
															Element nDAR = (ElementImpl)xml.createElement("nDAR");
															nDAR.setTextContent(tokens[7].trim());
															avulsa.appendChild(nDAR);
															Element dEmi = (ElementImpl)xml.createElement("dEmi");
															dEmi.setTextContent(tokens[8].trim());
															avulsa.appendChild(dEmi);
															Element vDAR = (ElementImpl)xml.createElement("vDAR");
															vDAR.setTextContent(tokens[9].trim());
															avulsa.appendChild(vDAR);
															Element repEmi = (ElementImpl)xml.createElement("repEmi");
															repEmi.setTextContent(tokens[10].trim());
															avulsa.appendChild(repEmi);
															Element dPag = (ElementImpl)xml.createElement("dPag");
															dPag.setTextContent(tokens[11].trim());
															avulsa.appendChild(dPag);
															
															NodeList infNFeNL = xml.getElementsByTagName("infNFe");
															if(infNFeNL.getLength() > 0)
															{
																Element infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																infNFe.appendChild(avulsa);
															}
														}
														else
														{
															if(tokens[0].trim().equalsIgnoreCase("2300"))
															{
																logger.info("Contem : 2300");
																Element dest = (ElementImpl)xml.createElement("dest");
																if(!tokens[1].trim().equalsIgnoreCase(""))
																{
																	if(tokens[1].trim().length() == 14)
																	{
																		Element cnpj = (ElementImpl)xml.createElement("CNPJ");
																		cnpj.setTextContent(tokens[1].trim());
																		dest.appendChild(cnpj);
																	}
																	else
																	{
																		if(tokens[1].trim().length() == 11)
																		{
																			Element cpf = (ElementImpl)xml.createElement("CPF");
																			cpf.setTextContent(tokens[1].trim());
																			dest.appendChild(cpf);
																		}
																	}
																}
																else
																{
																	Element cnpj = (ElementImpl)xml.createElement("CNPJ");
																	cnpj.setTextContent("");
																	dest.appendChild(cnpj);
																}
																Element xNome = (ElementImpl)xml.createElement("xNome");
																xNome.setTextContent(tokens[2].trim());
																dest.appendChild(xNome);
																Element ie = (ElementImpl)xml.createElement("IE");
																ie.setTextContent(tokens[3].trim());
																dest.appendChild(ie);
																if(tokens.length >= 5)
																{
																	if(!tokens[4].trim().equalsIgnoreCase(""))
																	{
																		Element isuf = (ElementImpl)xml.createElement("ISUF");
																		isuf.setTextContent(tokens[4].trim());
																		dest.appendChild(isuf);
																	}
																}
																if(tokens.length >= 6)
																{
																	if(!tokens[5].trim().equalsIgnoreCase(""))
																	{
																		Element email = (ElementImpl)xml.createElement("email");
																		email.setTextContent(tokens[5].trim());
																		dest.appendChild(email);
																		params.put("email",tokens[5].trim());
																	}
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
																if(tokens[0].trim().equalsIgnoreCase("2310"))
																{
																	logger.info("Contem : 2310");
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
																	if(tokens.length == 12 && !tokens[11].trim().equalsIgnoreCase(""))
																	{
																		Element fone = (ElementImpl)xml.createElement("fone");
																		fone.setTextContent(tokens[11].trim());
																		enderDest.appendChild(fone);
																	}
																	NodeList destNL = xml.getElementsByTagName("dest");
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
																	if(tokens[0].trim().equalsIgnoreCase("2400"))
																	{
																		logger.info("Contem : 2400");
																		Element retirada = (ElementImpl)xml.createElement("retirada");
																		if(tokens[1].trim().length() == 14)
																		{
																			Element cnpj = (ElementImpl)xml.createElement("CNPJ");
																			cnpj.setTextContent(tokens[1].trim());
																			retirada.appendChild(cnpj);
																		}
																		else
																		{
																			if(tokens[1].trim().length() == 11)
																			{
																				Element cpf = (ElementImpl)xml.createElement("CPF");
																				cpf.setTextContent(tokens[1].trim());
																				retirada.appendChild(cpf);
																			}
																		}
																		Element xLgr = (ElementImpl)xml.createElement("xLgr");
																		xLgr.setTextContent(tokens[2].trim());
																		retirada.appendChild(xLgr);
																		Element nro = (ElementImpl)xml.createElement("nro");
																		nro.setTextContent(tokens[3].trim());
																		retirada.appendChild(nro);
																		if(!tokens[4].trim().equalsIgnoreCase(""))
																		{
																			Element xCpl = (ElementImpl)xml.createElement("xCpl");
																			xCpl.setTextContent(tokens[4].trim());
																			retirada.appendChild(xCpl);
																		}
																		Element xBairro = (ElementImpl)xml.createElement("xBairro");
																		xBairro.setTextContent(tokens[5].trim());
																		retirada.appendChild(xBairro);
																		Element cMun = (ElementImpl)xml.createElement("cMun");
																		cMun.setTextContent(tokens[6].trim());
																		retirada.appendChild(cMun);
																		Element xMun = (ElementImpl)xml.createElement("xMun");
																		xMun.setTextContent(tokens[7].trim());
																		retirada.appendChild(xMun);
																		Element uf = (ElementImpl)xml.createElement("UF");
																		uf.setTextContent(tokens[8].trim());
																		retirada.appendChild(uf);
																		
																		NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																		if(infNFeNL.getLength() > 0)
																		{
																			Element infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																			infNFe.appendChild(retirada);
																		}
																	}
																	else
																	{
																		if(tokens[0].trim().equalsIgnoreCase("2500"))
																		{
																			logger.info("Contem : 2500");
																			Element entrega = (ElementImpl)xml.createElement("entrega");
																			if(tokens[1].trim().length() == 14)
																			{
																				Element cnpj = (ElementImpl)xml.createElement("CNPJ");
																				cnpj.setTextContent(tokens[1].trim());
																				entrega.appendChild(cnpj);
																			}
																			else
																			{
																				if(tokens[1].trim().length() == 11)
																				{
																					Element cpf = (ElementImpl)xml.createElement("CPF");
																					cpf.setTextContent(tokens[1].trim());
																					entrega.appendChild(cpf);
																				}
																			}
																			Element xLgr = (ElementImpl)xml.createElement("xLgr");
																			xLgr.setTextContent(tokens[2].trim());
																			entrega.appendChild(xLgr);
																			Element nro = (ElementImpl)xml.createElement("nro");
																			nro.setTextContent(tokens[3].trim());
																			entrega.appendChild(nro);
																			if(!tokens[4].trim().equalsIgnoreCase(""))
																			{
																				Element xCpl = (ElementImpl)xml.createElement("xCpl");
																				xCpl.setTextContent(tokens[4].trim());
																				entrega.appendChild(xCpl);
																			}
																			Element xBairro = (ElementImpl)xml.createElement("xBairro");
																			xBairro.setTextContent(tokens[5].trim());
																			entrega.appendChild(xBairro);
																			Element cMun = (ElementImpl)xml.createElement("cMun");
																			cMun.setTextContent(tokens[6].trim());
																			entrega.appendChild(cMun);
																			Element xMun = (ElementImpl)xml.createElement("xMun");
																			xMun.setTextContent(tokens[7].trim());
																			entrega.appendChild(xMun);
																			Element uf = (ElementImpl)xml.createElement("UF");
																			uf.setTextContent(tokens[8].trim());
																			entrega.appendChild(uf);
																			
																			NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																			if(infNFeNL.getLength() > 0)
																			{
																				Element infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																				infNFe.appendChild(entrega);
																			}
																		}
																		else
																		{
																			if(tokens[0].trim().equalsIgnoreCase("3000"))
																			{
																				logger.info("Contem : 3000");
																				NodeList detNL = xml.getElementsByTagName("det");
																				Element det = (ElementImpl)xml.createElement("det");
																				det.setAttribute("nItem", String.valueOf(detNL.getLength()+1));
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
																				if(tokens.length >= 21 && !tokens[20].trim().equalsIgnoreCase(""))
																				{
																					Element xPed = (ElementImpl)xml.createElement("xPed");
																					xPed.setTextContent(tokens[20].trim());
																					prod.appendChild(xPed);
																				}
																				if(tokens.length >= 22)
																				{
																					if(!tokens[21].trim().equalsIgnoreCase(""))
																					{
																						Element nItemPed = (ElementImpl)xml.createElement("nItemPed");
																						nItemPed.setTextContent(tokens[21].trim());
																						prod.appendChild(nItemPed);
																					}
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
																				if(tokens[0].trim().equalsIgnoreCase("3001"))
																				{
																					logger.info("Contem : 3001");
																					Element di = (ElementImpl)xml.createElement("DI");
																					Element nDI = (ElementImpl)xml.createElement("nDI");
																					nDI.setTextContent(tokens[1].trim());
																					di.appendChild(nDI);
																					Element dDI = (ElementImpl)xml.createElement("dDI");
																					dDI.setTextContent(tokens[2].trim());
																					di.appendChild(dDI);
																					Element xLocDesemb = (ElementImpl)xml.createElement("xLocDesemb");
																					xLocDesemb.setTextContent(tokens[3].trim());
																					di.appendChild(xLocDesemb);
																					Element UFDesemb = (ElementImpl)xml.createElement("UFDesemb");
																					UFDesemb.setTextContent(tokens[4].trim());
																					di.appendChild(UFDesemb);
																					Element dDesemb = (ElementImpl)xml.createElement("dDesemb");
																					dDesemb.setTextContent(tokens[5].trim());
																					di.appendChild(dDesemb);
																					Element cExportador = (ElementImpl)xml.createElement("cExportador");
																					cExportador.setTextContent(tokens[6].trim());
																					di.appendChild(cExportador);
																					
																					NodeList prodNL = xml.getElementsByTagName("prod");
																					Element prod = null;
																					if(prodNL.getLength() > 0)
																					{
																						prod = (ElementImpl)prodNL.item(prodNL.getLength()-1);
																						prod.appendChild(di);
																					}
//																					NodeList xPedNL = prod.getElementsByTagName("xPed");
//																					if(xPedNL.getLength() > 0)
//																					{
//																						Element xPed = (ElementImpl)xPedNL.item(xPedNL.getLength()-1);
//																						prod.insertBefore(di, xPed);
//																					}
																				}
																				else
																				{
																					if(tokens[0].trim().equalsIgnoreCase("3002"))
																					{
																						logger.info("Contem : 3002");
																						Element adi = (ElementImpl)xml.createElement("adi");
																						Element nAdicao = (ElementImpl)xml.createElement("nAdicao");
																						nAdicao.setTextContent(tokens[1].trim());
																						adi.appendChild(nAdicao);
																						Element nSeqAdic = (ElementImpl)xml.createElement("nSeqAdic");
																						nSeqAdic.setTextContent(tokens[2].trim());
																						adi.appendChild(nSeqAdic);
																						Element cFabricante = (ElementImpl)xml.createElement("cFabricante");
																						cFabricante.setTextContent(tokens[3].trim());
																						adi.appendChild(cFabricante);
																						if(!tokens[4].trim().equalsIgnoreCase(""))
																						{
																							Element vDescDI = (ElementImpl)xml.createElement("vDescDI");
																							vDescDI.setTextContent(tokens[4].trim());
																							adi.appendChild(vDescDI);
																						}
																						NodeList diNL = xml.getElementsByTagName("DI");
																						Element di = null;
																						if(diNL.getLength() > 0)
																						{
																							di = (ElementImpl)diNL.item(diNL.getLength()-1);
																						}
																						else
																						{
																							di = (ElementImpl)xml.createElement("DI");
																						}
																						di.appendChild(adi);
																					}
																					else
																					{
																						if(tokens[0].trim().equalsIgnoreCase("3010"))
																						{
																							logger.info("Contem : 3010");
																							Element veicProd = (ElementImpl)xml.createElement("veicProd");
																							Element tpOp = (ElementImpl)xml.createElement("tpOp");
																							tpOp.setTextContent(tokens[1].trim());
																							veicProd.appendChild(tpOp);
																							Element chassi = (ElementImpl)xml.createElement("chassi");
																							chassi.setTextContent(tokens[2].trim());
																							veicProd.appendChild(chassi);
																							Element cCor = (ElementImpl)xml.createElement("cCor");
																							cCor.setTextContent(tokens[3].trim());
																							veicProd.appendChild(cCor);
																							Element xCor = (ElementImpl)xml.createElement("xCor");
																							xCor.setTextContent(tokens[4].trim());
																							veicProd.appendChild(xCor);
																							Element pot = (ElementImpl)xml.createElement("pot");
																							pot.setTextContent(tokens[5].trim());
																							veicProd.appendChild(pot);
																							Element cilin = (ElementImpl)xml.createElement("cilin");
																							cilin.setTextContent(tokens[6].trim());
																							veicProd.appendChild(cilin);
																							Element pesoL = (ElementImpl)xml.createElement("pesoL");
																							pesoL.setTextContent(tokens[7].trim());
																							veicProd.appendChild(pesoL);
																							Element pesoB = (ElementImpl)xml.createElement("pesoB");
																							pesoB.setTextContent(tokens[8].trim());
																							veicProd.appendChild(pesoB);
																							Element nSerie = (ElementImpl)xml.createElement("nSerie");
																							nSerie.setTextContent(tokens[9].trim());
																							veicProd.appendChild(nSerie);
																							Element tpComb = (ElementImpl)xml.createElement("tpComb");
																							tpComb.setTextContent(tokens[10].trim());
																							veicProd.appendChild(tpComb);
																							Element nMotor = (ElementImpl)xml.createElement("nMotor");
																							nMotor.setTextContent(tokens[11].trim());
																							veicProd.appendChild(nMotor);
																							Element cmt = (ElementImpl)xml.createElement("CMT");
																							cmt.setTextContent(tokens[12].trim());
																							veicProd.appendChild(cmt);
																							Element dist = (ElementImpl)xml.createElement("dist");
																							dist.setTextContent(tokens[13].trim());
																							veicProd.appendChild(dist);
																							Element anoMod = (ElementImpl)xml.createElement("anoMod");
																							anoMod.setTextContent(tokens[14].trim());
																							veicProd.appendChild(anoMod);
																							Element anoFab = (ElementImpl)xml.createElement("anoFab");
																							anoFab.setTextContent(tokens[15].trim());
																							veicProd.appendChild(anoFab);
																							Element tpPint = (ElementImpl)xml.createElement("tpPint");
																							tpPint.setTextContent(tokens[16].trim());
																							veicProd.appendChild(tpPint);
																							Element tpVeic = (ElementImpl)xml.createElement("tpVeic");
																							tpVeic.setTextContent(tokens[17].trim());
																							veicProd.appendChild(tpVeic);
																							Element espVeic = (ElementImpl)xml.createElement("espVeic");
																							espVeic.setTextContent(tokens[18].trim());
																							veicProd.appendChild(espVeic);
																							Element vin = (ElementImpl)xml.createElement("VIN");
																							vin.setTextContent(tokens[19].trim());
																							veicProd.appendChild(vin);
																							Element condVeic = (ElementImpl)xml.createElement("condVeic");
																							condVeic.setTextContent(tokens[20].trim());
																							veicProd.appendChild(condVeic);
																							Element cMod = (ElementImpl)xml.createElement("cMod");
																							cMod.setTextContent(tokens[21].trim());
																							veicProd.appendChild(cMod);
																							Element cCorDENATRAN = (ElementImpl)xml.createElement("cCorDENATRAN");
																							cCorDENATRAN.setTextContent(tokens[22].trim());
																							veicProd.appendChild(cCorDENATRAN);
																							Element lota = (ElementImpl)xml.createElement("lota");
																							lota.setTextContent(tokens[23].trim());
																							veicProd.appendChild(lota);
																							Element tpRest = (ElementImpl)xml.createElement("tpRest");
																							tpRest.setTextContent(tokens[24].trim());
																							veicProd.appendChild(tpRest);
																							
																							NodeList prodNL = xml.getElementsByTagName("prod");
																							Element prod = null;
																							if(prodNL.getLength() > 0)
																							{
																								prod = (ElementImpl)prodNL.item(prodNL.getLength()-1);
																								prod.appendChild(veicProd);
																							}
																						}
																						else
																						{
																							if(tokens[0].trim().equalsIgnoreCase("3020"))
																							{
																								logger.info("Contem : 3020");
																								Element med = (ElementImpl)xml.createElement("med");
																								Element nLote = (ElementImpl)xml.createElement("nLote");
																								nLote.setTextContent(tokens[1].trim());
																								med.appendChild(nLote);
																								Element qLote = (ElementImpl)xml.createElement("qLote");
																								qLote.setTextContent(tokens[2].trim());
																								med.appendChild(qLote);
																								Element dFab = (ElementImpl)xml.createElement("dFab");
																								dFab.setTextContent(tokens[3].trim());
																								med.appendChild(dFab);
																								Element dVal = (ElementImpl)xml.createElement("dVal");
																								dVal.setTextContent(tokens[4].trim());
																								med.appendChild(dVal);
																								Element vPMC = (ElementImpl)xml.createElement("vPMC");
																								vPMC.setTextContent(tokens[5].trim());
																								med.appendChild(vPMC);
																								
																								NodeList prodNL = xml.getElementsByTagName("prod");
																								Element prod = null;
																								if(prodNL.getLength() > 0)
																								{
																									prod = (ElementImpl)prodNL.item(prodNL.getLength()-1);
																									prod.appendChild(med);
																								}
																							}
																							else
																							{
																								if(tokens[0].trim().equalsIgnoreCase("3030"))
																								{
																									logger.info("Contem : 3030");
																									Element arma = (ElementImpl)xml.createElement("arma");
																									Element tpArma = (ElementImpl)xml.createElement("tpArma");
																									tpArma.setTextContent(tokens[1].trim());
																									arma.appendChild(tpArma);
																									Element nSerie = (ElementImpl)xml.createElement("nSerie");
																									nSerie.setTextContent(tokens[2].trim());
																									arma.appendChild(nSerie);
																									Element nCano = (ElementImpl)xml.createElement("nCano");
																									nCano.setTextContent(tokens[3].trim());
																									arma.appendChild(nCano);
																									Element descr = (ElementImpl)xml.createElement("descr");
																									descr.setTextContent(tokens[4].trim());
																									arma.appendChild(descr);
																									
																									NodeList prodNL = xml.getElementsByTagName("prod");
																									Element prod = null;
																									if(prodNL.getLength() > 0)
																									{
																										prod = (ElementImpl)prodNL.item(prodNL.getLength()-1);
																										prod.appendChild(arma);
																									}
																								}
																								else
																								{
																									if(tokens[0].trim().equalsIgnoreCase("3040"))
																									{
																										logger.info("Contem : 3040");
																										Element comb = (ElementImpl)xml.createElement("comb");
																										Element cProdANP = (ElementImpl)xml.createElement("cProdANP");
																										cProdANP.setTextContent(tokens[1].trim());
																										comb.appendChild(cProdANP);
																										Element codif = (ElementImpl)xml.createElement("CODIF");
																										codif.setTextContent(tokens[2].trim());
																										comb.appendChild(codif);
																										Element qTemp = (ElementImpl)xml.createElement("qTemp");
																										qTemp.setTextContent(tokens[3].trim());
																										comb.appendChild(qTemp);
																										Element UFCons = (ElementImpl)xml.createElement("UFCons");
																										UFCons.setTextContent(tokens[4].trim());
																										comb.appendChild(UFCons);
																										
																										NodeList prodNL = xml.getElementsByTagName("prod");
																										Element prod = null;
																										if(prodNL.getLength() > 0)
																										{
																											prod = (ElementImpl)prodNL.item(prodNL.getLength()-1);
																											prod.appendChild(comb);
																										}
																									}
																									else
																									{
																										if(tokens[0].trim().equalsIgnoreCase("3041"))
																										{
																											Element cide = (ElementImpl)xml.createElement("CIDE");
																											Element qBCProd = (ElementImpl)xml.createElement("qBCProd");
																											qBCProd.setTextContent(tokens[1].trim());
																											cide.appendChild(qBCProd);
																											Element vAliqProd = (ElementImpl)xml.createElement("vAliqProd");
																											vAliqProd.setTextContent(tokens[2].trim());
																											cide.appendChild(vAliqProd);
																											Element vCIDE = (ElementImpl)xml.createElement("vCIDE");
																											vCIDE.setTextContent(tokens[3].trim());
																											cide.appendChild(vCIDE);
																											
																											NodeList combNL = xml.getElementsByTagName("comb");
																											Element comb = null;
																											if(combNL.getLength() > 0)
																											{
																												comb = (ElementImpl)combNL.item(combNL.getLength()-1);
																												comb.appendChild(cide);
																											}
																										}
																										else
																										{
																											if(tokens[0].trim().equalsIgnoreCase("3100"))
																											{
																												logger.info("Contem : 3100");
																												Element imposto = (ElementImpl)xml.createElement("imposto");
																												Element icms = (ElementImpl)xml.createElement("ICMS");
																												Element icms00 = (ElementImpl)xml.createElement("ICMS00");
																												Element orig = (ElementImpl)xml.createElement("orig");
																												orig.setTextContent(tokens[1].trim());
																												icms00.appendChild(orig);
																												Element cst = (ElementImpl)xml.createElement("CST");
																												cst.setTextContent(tokens[2].trim());
																												icms00.appendChild(cst);
																												Element modBC = (ElementImpl)xml.createElement("modBC");
																												modBC.setTextContent(tokens[3].trim());
																												icms00.appendChild(modBC);
																												Element vBC = (ElementImpl)xml.createElement("vBC");
																												vBC.setTextContent(tokens[4].trim());
																												icms00.appendChild(vBC);
																												Element pICMS = (ElementImpl)xml.createElement("pICMS");
																												pICMS.setTextContent(tokens[5].trim());
																												icms00.appendChild(pICMS);
																												Element vICMS = (ElementImpl)xml.createElement("vICMS");
																												vICMS.setTextContent(tokens[6].trim());
																												icms00.appendChild(vICMS);
																												
																												icms.appendChild(icms00);
																												imposto.appendChild(icms);
																												NodeList detNL = xml.getElementsByTagName("det");
																												Element det = null;
																												if(detNL.getLength() > 0)
																												{
																													det = (ElementImpl)detNL.item(detNL.getLength()-1);
																													det.appendChild(imposto);
																												}
																											}
																											else
																											{
																												if(tokens[0].trim().equalsIgnoreCase("3103"))
																												{
																													logger.info("Contem : 3103");
																													
																													Element imposto = (ElementImpl)xml.createElement("imposto");
																													Element icms = (ElementImpl)xml.createElement("ICMS");
																													Element icms10 = (ElementImpl)xml.createElement("ICMS10");
																													Element orig = (ElementImpl)xml.createElement("orig");
																													orig.setTextContent(tokens[1].trim());
																													icms10.appendChild(orig);
																													Element cst = (ElementImpl)xml.createElement("CST");
																													cst.setTextContent(tokens[2].trim());
																													icms10.appendChild(cst);
																													Element modBC = (ElementImpl)xml.createElement("modBC");
																													modBC.setTextContent(tokens[3].trim());
																													icms10.appendChild(modBC);
																													Element vBC = (ElementImpl)xml.createElement("vBC");
																													vBC.setTextContent(tokens[4].trim());
																													icms10.appendChild(vBC);
																													Element pICMS = (ElementImpl)xml.createElement("pICMS");
																													pICMS.setTextContent(tokens[5].trim());
																													icms10.appendChild(pICMS);
																													Element vICMS = (ElementImpl)xml.createElement("vICMS");
																													vICMS.setTextContent(tokens[6].trim());
																													icms10.appendChild(vICMS);
																													Element modBCST = (ElementImpl)xml.createElement("modBCST");
																													modBCST.setTextContent(tokens[7].trim());
																													icms10.appendChild(modBCST);
																													if(!tokens[8].trim().equalsIgnoreCase(""))
																													{
																														Element pMVAST = (ElementImpl)xml.createElement("pMVAST");
																														pMVAST.setTextContent(tokens[8].trim());
																														icms10.appendChild(pMVAST);
																													}
																													if(!tokens[9].trim().equalsIgnoreCase(""))
																													{
																														Element pRedBCST = (ElementImpl)xml.createElement("pRedBCST");
																														pRedBCST.setTextContent(tokens[9].trim());
																														icms10.appendChild(pRedBCST);
																													}
																													Element vBCST = (ElementImpl)xml.createElement("vBCST");
																													vBCST.setTextContent(tokens[10].trim());
																													icms10.appendChild(vBCST);
																													Element pICMSST = (ElementImpl)xml.createElement("pICMSST");
																													pICMSST.setTextContent(tokens[11].trim());
																													icms10.appendChild(pICMSST);
																													Element vICMSST = (ElementImpl)xml.createElement("vICMSST");
																													vICMSST.setTextContent(tokens[12].trim());
																													icms10.appendChild(vICMSST);
																													
																													
																													icms.appendChild(icms10);
																													imposto.appendChild(icms);
																													NodeList detNL = xml.getElementsByTagName("det");
																													Element det = null;
																													if(detNL.getLength() > 0)
																													{
																														det = (ElementImpl)detNL.item(detNL.getLength()-1);
																														det.appendChild(imposto);
																													}
																												}
																												else
																												{
																													if(tokens[0].trim().equalsIgnoreCase("3106"))
																													{
																														logger.info("Contem : 3106");
																														Element imposto = (ElementImpl)xml.createElement("imposto");
																														Element icms = (ElementImpl)xml.createElement("ICMS");
																														Element icms20 = (ElementImpl)xml.createElement("ICMS20");
																														Element orig = (ElementImpl)xml.createElement("orig");
																														orig.setTextContent(tokens[1].trim());
																														icms20.appendChild(orig);
																														Element cst = (ElementImpl)xml.createElement("CST");
																														cst.setTextContent(tokens[2].trim());
																														icms20.appendChild(cst);
																														Element modBC = (ElementImpl)xml.createElement("modBC");
																														modBC.setTextContent(tokens[3].trim());
																														icms20.appendChild(modBC);
																														Element pRedBC = (ElementImpl)xml.createElement("pRedBC");
																														pRedBC.setTextContent(tokens[4].trim());
																														icms20.appendChild(pRedBC);
																														Element vBC = (ElementImpl)xml.createElement("vBC");
																														vBC.setTextContent(tokens[5].trim());
																														icms20.appendChild(vBC);
																														Element pICMS = (ElementImpl)xml.createElement("pICMS");
																														pICMS.setTextContent(tokens[6].trim());
																														icms20.appendChild(pICMS);
																														Element vICMS = (ElementImpl)xml.createElement("vICMS");
																														vICMS.setTextContent(tokens[7].trim());
																														icms20.appendChild(vICMS);
																														
																														icms.appendChild(icms20);
																														
																														imposto.appendChild(icms);
																														NodeList detNL = xml.getElementsByTagName("det");
																														Element det = null;
																														if(detNL.getLength() > 0)
																														{
																															det = (ElementImpl)detNL.item(detNL.getLength()-1);
																															det.appendChild(imposto);
																														}
																													}
																													else
																													{
																														//TODO: Continua
																														if(tokens[0].trim().equalsIgnoreCase("3110"))
																														{
																															logger.info("Contem : 3110");
																															Element imposto = (ElementImpl)xml.createElement("imposto");
																															Element icms = (ElementImpl)xml.createElement("ICMS");
																															Element icms30 = (ElementImpl)xml.createElement("ICMS30");
																															Element orig = (ElementImpl)xml.createElement("orig");
																															orig.setTextContent(tokens[1].trim());
																															icms30.appendChild(orig);
																															Element cst = (ElementImpl)xml.createElement("CST");
																															cst.setTextContent(tokens[2].trim());
																															icms30.appendChild(cst);
																															Element modBCST = (ElementImpl)xml.createElement("modBCST");
																															modBCST.setTextContent(tokens[3].trim());
																															icms30.appendChild(modBCST);
																															Element pMVAST = (ElementImpl)xml.createElement("pMVAST");
																															pMVAST.setTextContent(tokens[4].trim());
																															icms30.appendChild(pMVAST);
																															Element pRedBCST = (ElementImpl)xml.createElement("pRedBCST");
																															pRedBCST.setTextContent(tokens[5].trim());
																															icms30.appendChild(pRedBCST);
																															Element vBCST = (ElementImpl)xml.createElement("vBCST");
																															vBCST.setTextContent(tokens[6].trim());
																															icms30.appendChild(vBCST);
																															Element pICMSST = (ElementImpl)xml.createElement("pICMSST");
																															pICMSST.setTextContent(tokens[7].trim());
																															icms30.appendChild(pICMSST);
																															Element vICMSST = (ElementImpl)xml.createElement("vICMSST");
																															vICMSST.setTextContent(tokens[8].trim());
																															icms30.appendChild(vICMSST);
																															
																															icms.appendChild(icms30);
																															
																															imposto.appendChild(icms);
																															NodeList detNL = xml.getElementsByTagName("det");
																															Element det = null;
																															if(detNL.getLength() > 0)
																															{
																																det = (ElementImpl)detNL.item(detNL.getLength()-1);
																																det.appendChild(imposto);
																															}
																														}
																														else
																														{
																															if(tokens[0].trim().equalsIgnoreCase("3113"))
																															{
																																logger.info("Contem : 3113");
																																Element imposto = (ElementImpl)xml.createElement("imposto");;
																																Element icms = (ElementImpl)xml.createElement("ICMS");
																																Element icms40 = (ElementImpl)xml.createElement("ICMS40");
																																Element orig = (ElementImpl)xml.createElement("orig");
																																orig.setTextContent(tokens[1].trim());
																																icms40.appendChild(orig);
																																Element cst = (ElementImpl)xml.createElement("CST");
																																cst.setTextContent(tokens[2].trim());
																																icms40.appendChild(cst);
																																
																																icms.appendChild(icms40);
																																
																																imposto.appendChild(icms);
																																NodeList detNL = xml.getElementsByTagName("det");
																																Element det = null;
																																if(detNL.getLength() > 0)
																																{
																																	det = (ElementImpl)detNL.item(detNL.getLength()-1);
																																	det.appendChild(imposto);
																																}
																															}
																															else
																															{
																																if(tokens[0].trim().equalsIgnoreCase("3114"))
																																{
																																	logger.info("Contem : 3114");
																																	NodeList icms40NL = xml.getElementsByTagName("ICMS40");
																																	Element icms40 = null;
																																	if(icms40NL.getLength() > 0)
																																	{
																																		icms40 = (ElementImpl)icms40NL.item(icms40NL.getLength()-1);
																																	}
																																	Element vICMS = (ElementImpl)xml.createElement("vICMS");
																																	vICMS.setTextContent(tokens[1].trim());
																																	icms40.appendChild(vICMS);
																																	Element motDesICMS = (ElementImpl)xml.createElement("motDesICMS");
																																	motDesICMS.setTextContent(tokens[2].trim());
																																	icms40.appendChild(motDesICMS);
																																	
																																}
																																else
																																{
																																	if(tokens[0].trim().equalsIgnoreCase("3116"))
																																	{
																																		logger.info("Contem : 3116");
																																		Element imposto = (ElementImpl)xml.createElement("imposto");
																																		Element icms = (ElementImpl)xml.createElement("ICMS");
																																		Element icms51 = (ElementImpl)xml.createElement("ICMS51");
																																		Element orig = (ElementImpl)xml.createElement("orig");
																																		orig.setTextContent(tokens[1].trim());
																																		icms51.appendChild(orig);
																																		Element cst = (ElementImpl)xml.createElement("CST");
																																		cst.setTextContent(tokens[2].trim());
																																		icms51.appendChild(cst);
																																		Element modBC = (ElementImpl)xml.createElement("modBC");
																																		modBC.setTextContent(tokens[3].trim());
																																		icms51.appendChild(modBC);
																																		Element pRedBC = (ElementImpl)xml.createElement("pRedBC");
																																		pRedBC.setTextContent(tokens[4].trim());
																																		icms51.appendChild(pRedBC);
																																		Element vBC = (ElementImpl)xml.createElement("vBC");
																																		vBC.setTextContent(tokens[5].trim());
																																		icms51.appendChild(vBC);
																																		Element pICMS = (ElementImpl)xml.createElement("pICMS");
																																		pICMS.setTextContent(tokens[6].trim());
																																		icms51.appendChild(pICMS);
																																		Element vICMS = (ElementImpl)xml.createElement("vICMS");
																																		vICMS.setTextContent(tokens[7].trim());
																																		icms51.appendChild(vICMS);
																																		
																																		
																																		icms.appendChild(icms51);
																																		
																																		imposto.appendChild(icms);
																																		NodeList detNL = xml.getElementsByTagName("det");
																																		Element det = null;
																																		if(detNL.getLength() > 0)
																																		{
																																			det = (ElementImpl)detNL.item(detNL.getLength()-1);
																																			det.appendChild(imposto);
																																		}
																																	}
																																	else
																																	{
																																		if(tokens[0].trim().equalsIgnoreCase("3120"))
																																		{
																																			logger.info("Contem : 3120");
																																			Element imposto = (ElementImpl)xml.createElement("imposto");
																																			Element icms = (ElementImpl)xml.createElement("ICMS");
																																			Element icms60 = (ElementImpl)xml.createElement("ICMS60");
																																			Element orig = (ElementImpl)xml.createElement("orig");
																																			orig.setTextContent(tokens[1].trim());
																																			icms60.appendChild(orig);
																																			Element cst = (ElementImpl)xml.createElement("CST");
																																			cst.setTextContent(tokens[2].trim());
																																			icms60.appendChild(cst);
//																																			Element vBCSTRet = (ElementImpl)xml.createElement("vBCSTRet");
//																																			vBCSTRet.setTextContent(tokens[3].trim());
//																																			icms60.appendChild(vBCSTRet);
//																																			Element vICMSSTRet = (ElementImpl)xml.createElement("vICMSSTRet");
//																																			vICMSSTRet.setTextContent(tokens[4].trim());
//																																			icms60.appendChild(vICMSSTRet);
																																			
																																			icms.appendChild(icms60);
																																			
																																			imposto.appendChild(icms);
																																			NodeList detNL = xml.getElementsByTagName("det");
																																			Element det = null;
																																			if(detNL.getLength() > 0)
																																			{
																																				det = (ElementImpl)detNL.item(detNL.getLength()-1);
																																				det.appendChild(imposto);
																																			}
																																		}
																																		else
																																		{
																																			if(tokens[0].trim().equalsIgnoreCase("3123"))
																																			{
																																				logger.info("Contem : 3123");
																																				Element imposto = (ElementImpl)xml.createElement("imposto");
																																				Element icms = (ElementImpl)xml.createElement("ICMS");
																																				Element icms70 = (ElementImpl)xml.createElement("ICMS70");
																																				Element orig = (ElementImpl)xml.createElement("orig");
																																				orig.setTextContent(tokens[1].trim());
																																				icms70.appendChild(orig);
																																				Element cst = (ElementImpl)xml.createElement("CST");
																																				cst.setTextContent(tokens[2].trim());
																																				icms70.appendChild(cst);
																																				Element modBC = (ElementImpl)xml.createElement("modBC");
																																				modBC.setTextContent(tokens[3].trim());
																																				icms70.appendChild(modBC);
																																				Element pRedBC = (ElementImpl)xml.createElement("pRedBC");
																																				pRedBC.setTextContent(tokens[4].trim());
																																				icms70.appendChild(pRedBC);
																																				Element vBC = (ElementImpl)xml.createElement("vBC");
																																				vBC.setTextContent(tokens[5].trim());
																																				icms70.appendChild(vBC);
																																				Element pICMS = (ElementImpl)xml.createElement("pICMS");
																																				pICMS.setTextContent(tokens[6].trim());
																																				icms70.appendChild(pICMS);
																																				Element vICMS = (ElementImpl)xml.createElement("vICMS");
																																				vICMS.setTextContent(tokens[7].trim());
																																				icms70.appendChild(vICMS);
																																				Element modBCST = (ElementImpl)xml.createElement("modBCST");
																																				modBCST.setTextContent(tokens[8].trim());
																																				icms70.appendChild(modBCST);
																																				Element pMVAST = (ElementImpl)xml.createElement("pMVAST");
																																				pMVAST.setTextContent(tokens[9].trim());
																																				icms70.appendChild(pMVAST);
																																				Element pRedBCST = (ElementImpl)xml.createElement("pRedBCST");
																																				pRedBCST.setTextContent(tokens[10].trim());
																																				icms70.appendChild(pRedBCST);
																																				Element vBCST = (ElementImpl)xml.createElement("vBCST");
																																				vBCST.setTextContent(tokens[11].trim());
																																				icms70.appendChild(vBCST);
																																				Element pICMSST = (ElementImpl)xml.createElement("pICMSST");
																																				pICMSST.setTextContent(tokens[12].trim());
																																				icms70.appendChild(pICMSST);
																																				Element vICMSST = (ElementImpl)xml.createElement("vICMSST");
																																				vICMSST.setTextContent(tokens[13].trim());
																																				icms70.appendChild(vICMSST);
																																				
																																				icms.appendChild(icms70);
																																			
																																				imposto.appendChild(icms);
																																				NodeList detNL = xml.getElementsByTagName("det");
																																				Element det = null;
																																				if(detNL.getLength() > 0)
																																				{
																																					det = (ElementImpl)detNL.item(detNL.getLength()-1);
																																					det.appendChild(imposto);
																																				}
																																			}
																																			else
																																			{
																																				if(tokens[0].trim().equalsIgnoreCase("3126"))
																																				{
																																					logger.info("Contem : 3126");
																																					Element imposto = (ElementImpl)xml.createElement("imposto");
																																					Element icms = (ElementImpl)xml.createElement("ICMS");
																																					Element icms90 = (ElementImpl)xml.createElement("ICMS90");
																																					Element orig = (ElementImpl)xml.createElement("orig");
																																					orig.setTextContent(tokens[1].trim());
																																					icms90.appendChild(orig);
																																					Element cst = (ElementImpl)xml.createElement("CST");
																																					cst.setTextContent(tokens[2].trim());
																																					icms90.appendChild(cst);
																																					
																																					
																																					icms.appendChild(icms90);
																																					
																																					imposto.appendChild(icms);
																																					NodeList detNL = xml.getElementsByTagName("det");
																																					Element det = null;
																																					if(detNL.getLength() > 0)
																																					{
																																						det = (ElementImpl)detNL.item(detNL.getLength()-1);
																																						det.appendChild(imposto);
																																					}
																																				}
																																				else
																																				{
																																					if(tokens[0].trim().equalsIgnoreCase("3127"))
																																					{
																																						logger.info("Contem : 3127");
																																						NodeList icms90NL = xml.getElementsByTagName("ICMS90");
																																						Element icms90 = null;
																																						if(icms90NL.getLength() > 0)
																																						{
																																							icms90 = (ElementImpl)icms90NL.item(icms90NL.getLength()-1);
																																						}
																																						Element modBC = (ElementImpl)xml.createElement("modBC");
																																						modBC.setTextContent(tokens[1].trim());
																																						icms90.appendChild(modBC);
																																						Element vBC = (ElementImpl)xml.createElement("vBC");
																																						vBC.setTextContent(tokens[2].trim());
																																						icms90.appendChild(vBC);
																																						Element pRedBC = (ElementImpl)xml.createElement("pRedBC");
																																						pRedBC.setTextContent(tokens[3].trim());
																																						icms90.appendChild(pRedBC);
																																						Element pICMS = (ElementImpl)xml.createElement("pICMS");
																																						pICMS.setTextContent(tokens[4].trim());
																																						icms90.appendChild(pICMS);
																																						Element vICMS = (ElementImpl)xml.createElement("vICMS");
																																						vICMS.setTextContent(tokens[5].trim());
																																						icms90.appendChild(vICMS);
																																					}
																																					else
																																					{
																																						if(tokens[0].trim().equalsIgnoreCase("3128"))
																																						{
																																							logger.info("Contem : 3128");
																																							NodeList icms90NL = xml.getElementsByTagName("ICMS90");
																																							Element icms90 = null;
																																							if(icms90NL.getLength() > 0)
																																							{
																																								icms90 = (ElementImpl)icms90NL.item(icms90NL.getLength()-1);
																																							}
																																							Element modBCST = (ElementImpl)xml.createElement("modBCST");
																																							modBCST.setTextContent(tokens[1].trim());
																																							icms90.appendChild(modBCST);
																																							Element pMVAST = (ElementImpl)xml.createElement("pMVAST");
																																							pMVAST.setTextContent(tokens[2].trim());
																																							icms90.appendChild(pMVAST);
																																							Element pRedBCST = (ElementImpl)xml.createElement("pRedBCST");
																																							pRedBCST.setTextContent(tokens[3].trim());
																																							icms90.appendChild(pRedBCST);
																																							Element vBCST = (ElementImpl)xml.createElement("vBCST");
																																							vBCST.setTextContent(tokens[4].trim());
																																							icms90.appendChild(vBCST);
																																							Element pICMSST = (ElementImpl)xml.createElement("pICMSST");
																																							pICMSST.setTextContent(tokens[5].trim());
																																							icms90.appendChild(pICMSST);
																																							Element vICMSST = (ElementImpl)xml.createElement("vICMSST");
																																							vICMSST.setTextContent(tokens[6].trim());
																																							icms90.appendChild(vICMSST);
																																							
																																						}
																																						else
																																						{
																																							if(tokens[0].trim().equalsIgnoreCase("3130"))
																																							{
																																								logger.info("Contem : 3130");
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
																																								
																																								Element icmsPart = (ElementImpl)xml.createElement("ICMSPart");
																																								Element orig = (ElementImpl)xml.createElement("orig");
																																								orig.setTextContent(tokens[1].trim());
																																								icmsPart.appendChild(orig);
																																								Element cst = (ElementImpl)xml.createElement("CST");
																																								cst.setTextContent(tokens[2].trim());
																																								icmsPart.appendChild(cst);
																																								Element modBC = (ElementImpl)xml.createElement("modBC");
																																								modBC.setTextContent(tokens[3].trim());
																																								icmsPart.appendChild(modBC);
																																								Element vBC = (ElementImpl)xml.createElement("vBC");
																																								vBC.setTextContent(tokens[4].trim());
																																								icmsPart.appendChild(vBC);
																																								Element pRedBC = (ElementImpl)xml.createElement("pRedBC");
																																								pRedBC.setTextContent(tokens[5].trim());
																																								icmsPart.appendChild(pRedBC);
																																								Element pICMS = (ElementImpl)xml.createElement("pICMS");
																																								pICMS.setTextContent(tokens[6].trim());
																																								icmsPart.appendChild(pICMS);
																																								Element vICMS = (ElementImpl)xml.createElement("vICMS");
																																								vICMS.setTextContent(tokens[7].trim());
																																								icmsPart.appendChild(vICMS);
																																								Element modBCST = (ElementImpl)xml.createElement("modBCST");
																																								modBCST.setTextContent(tokens[8].trim());
																																								icmsPart.appendChild(modBCST);
																																								Element pMVAST = (ElementImpl)xml.createElement("pMVAST");
																																								pMVAST.setTextContent(tokens[9].trim());
																																								icmsPart.appendChild(pMVAST);
																																								Element pRedBCST = (ElementImpl)xml.createElement("pRedBCST");
																																								pRedBCST.setTextContent(tokens[10].trim());
																																								icmsPart.appendChild(pRedBCST);
																																								Element vBCST = (ElementImpl)xml.createElement("vBCST");
																																								vBCST.setTextContent(tokens[11].trim());
																																								icmsPart.appendChild(vBCST);
																																								Element pICMSST = (ElementImpl)xml.createElement("pICMSST");
																																								pICMSST.setTextContent(tokens[12].trim());
																																								icmsPart.appendChild(pICMSST);
																																								Element vICMSST = (ElementImpl)xml.createElement("vICMSST");
																																								vICMSST.setTextContent(tokens[13].trim());
																																								icmsPart.appendChild(vICMSST);
																																								Element pBCOp = (ElementImpl)xml.createElement("pBCOp");
																																								pBCOp.setTextContent(tokens[14].trim());
																																								icmsPart.appendChild(pBCOp);
																																								Element ufst = (ElementImpl)xml.createElement("UFST");
																																								ufst.setTextContent(tokens[15].trim());
																																								icmsPart.appendChild(ufst);
																																								
																																								icms.appendChild(icmsPart);
																																								
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
																																								if(tokens[0].trim().equalsIgnoreCase("3133"))
																																								{
																																									logger.info("Contem : 3133");
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
																																									
																																									Element icmsst = (ElementImpl)xml.createElement("ICMSST");
																																									Element orig = (ElementImpl)xml.createElement("orig");
																																									orig.setTextContent(tokens[1].trim());
																																									icmsst.appendChild(orig);
																																									Element cst = (ElementImpl)xml.createElement("CST");
																																									cst.setTextContent(tokens[2].trim());
																																									icmsst.appendChild(cst);
																																									Element vBCSTRet = (ElementImpl)xml.createElement("vBCSTRet");
																																									vBCSTRet.setTextContent(tokens[3].trim());
																																									icmsst.appendChild(vBCSTRet);
																																									Element vICMSSTRet = (ElementImpl)xml.createElement("vICMSSTRet");
																																									vICMSSTRet.setTextContent(tokens[4].trim());
																																									icmsst.appendChild(vICMSSTRet);
																																									Element vBCSTDest = (ElementImpl)xml.createElement("vBCSTDest");
																																									vBCSTDest.setTextContent(tokens[5].trim());
																																									icmsst.appendChild(vBCSTDest);
																																									Element vICMSSTDest = (ElementImpl)xml.createElement("vICMSSTDest");
																																									vICMSSTDest.setTextContent(tokens[6].trim());
																																									icmsst.appendChild(vICMSSTDest);
																																									
																																									icms.appendChild(icmsst);
																																									
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
																																									if(tokens[0].trim().equalsIgnoreCase("3136"))
																																									{
																																										logger.info("Contem : 3136");
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
																																										if(tokens[0].trim().equalsIgnoreCase("3140"))
																																										{
																																											logger.info("Contem : 3140");
																																											boolean impostoNovo = false;
																																											boolean icmsNovo = false;
//																																											NodeList impostoNL = xml.getElementsByTagName("imposto");
																																											Element imposto = null;
//																																											if(impostoNL.getLength() > 0)
//																																											{
//																																												imposto = (ElementImpl)impostoNL.item(impostoNL.getLength()-1);
//																																											}
//																																											else
//																																											{
																																												impostoNovo = true;
																																												imposto = (ElementImpl)xml.createElement("imposto");
//																																											}
//																																											NodeList icmsNL = xml.getElementsByTagName("ICMS");
																																											Element icms = null;
//																																											if(icmsNL.getLength() > 0)
//																																											{
//																																												icms = (ElementImpl)icmsNL.item(icmsNL.getLength()-1);
//																																											}
//																																											else
//																																											{
																																												icmsNovo = true;
																																												icms = (ElementImpl)xml.createElement("ICMS");
//																																											}
																																											
																																											Element icmsSn102 = (ElementImpl)xml.createElement("ICMSSN102");
																																											Element orig = (ElementImpl)xml.createElement("orig");
																																											orig.setTextContent(tokens[1].trim());
																																											icmsSn102.appendChild(orig);
																																											Element csosn = (ElementImpl)xml.createElement("CSOSN");
																																											csosn.setTextContent(tokens[2].trim());
																																											icmsSn102.appendChild(csosn);
																																											
																																											icms.appendChild(icmsSn102);
																																											
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
																																											if(tokens[0].trim().equalsIgnoreCase("3143"))
																																											{
																																												logger.info("Contem : 3143");
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
																																												
																																												Element icmsSn201 = (ElementImpl)xml.createElement("ICMSSN201");
																																												Element orig = (ElementImpl)xml.createElement("orig");
																																												orig.setTextContent(tokens[1].trim());
																																												icmsSn201.appendChild(orig);
																																												Element csosn = (ElementImpl)xml.createElement("CSOSN");
																																												csosn.setTextContent(tokens[2].trim());
																																												icmsSn201.appendChild(csosn);
																																												Element modBCST = (ElementImpl)xml.createElement("modBCST");
																																												modBCST.setTextContent(tokens[3].trim());
																																												icmsSn201.appendChild(modBCST);
																																												Element pMVAST = (ElementImpl)xml.createElement("pMVAST");
																																												pMVAST.setTextContent(tokens[4].trim());
																																												icmsSn201.appendChild(pMVAST);
																																												Element pRedBCST = (ElementImpl)xml.createElement("pRedBCST");
																																												pRedBCST.setTextContent(tokens[5].trim());
																																												icmsSn201.appendChild(pRedBCST);
																																												Element vBCST = (ElementImpl)xml.createElement("vBCST");
																																												vBCST.setTextContent(tokens[6].trim());
																																												icmsSn201.appendChild(vBCST);
																																												Element pICMSST = (ElementImpl)xml.createElement("pICMSST");
																																												pICMSST.setTextContent(tokens[7].trim());
																																												icmsSn201.appendChild(pICMSST);
																																												Element vICMSST = (ElementImpl)xml.createElement("vICMSST");
																																												vICMSST.setTextContent(tokens[8].trim());
																																												icmsSn201.appendChild(vICMSST);
																																												Element pCredSN = (ElementImpl)xml.createElement("pCredSN");
																																												pCredSN.setTextContent(tokens[9].trim());
																																												icmsSn201.appendChild(pCredSN);
																																												Element vCredICMSSN = (ElementImpl)xml.createElement("vCredICMSSN");
																																												vCredICMSSN.setTextContent(tokens[10].trim());
																																												icmsSn201.appendChild(vCredICMSSN);
																																												
																																												icms.appendChild(icmsSn201);
																																												
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
																																												if(tokens[0].trim().equalsIgnoreCase("3146"))
																																												{
																																													logger.info("Contem : 3146");
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
																																													
																																													Element icmsSn202 = (ElementImpl)xml.createElement("ICMSSN202");
																																													Element orig = (ElementImpl)xml.createElement("orig");
																																													orig.setTextContent(tokens[1].trim());
																																													icmsSn202.appendChild(orig);
																																													Element csosn = (ElementImpl)xml.createElement("CSOSN");
																																													csosn.setTextContent(tokens[2].trim());
																																													icmsSn202.appendChild(csosn);
																																													Element modBCST = (ElementImpl)xml.createElement("modBCST");
																																													modBCST.setTextContent(tokens[3].trim());
																																													icmsSn202.appendChild(modBCST);
																																													Element pMVAST = (ElementImpl)xml.createElement("pMVAST");
																																													pMVAST.setTextContent(tokens[4].trim());
																																													icmsSn202.appendChild(pMVAST);
																																													Element pRedBCST = (ElementImpl)xml.createElement("pRedBCST");
																																													pRedBCST.setTextContent(tokens[5].trim());
																																													icmsSn202.appendChild(pRedBCST);
																																													Element vBCST = (ElementImpl)xml.createElement("vBCST");
																																													vBCST.setTextContent(tokens[6].trim());
																																													icmsSn202.appendChild(vBCST);
																																													Element pICMSST = (ElementImpl)xml.createElement("pICMSST");
																																													pICMSST.setTextContent(tokens[7].trim());
																																													icmsSn202.appendChild(pICMSST);
																																													Element vICMSST = (ElementImpl)xml.createElement("vICMSST");
																																													vICMSST.setTextContent(tokens[8].trim());
																																													icmsSn202.appendChild(vICMSST);
																																													
																																													icms.appendChild(icmsSn202);
																																													
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
																																													if(tokens[0].trim().equalsIgnoreCase("3150"))
																																													{
																																														logger.info("Contem : 3150");
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
																																														
																																														Element icmsSn500 = (ElementImpl)xml.createElement("ICMSSN500");
																																														Element orig = (ElementImpl)xml.createElement("orig");
																																														orig.setTextContent(tokens[1].trim());
																																														icmsSn500.appendChild(orig);
																																														Element csosn = (ElementImpl)xml.createElement("CSOSN");
																																														csosn.setTextContent(tokens[2].trim());
																																														icmsSn500.appendChild(csosn);
																																														Element vBCSTRet = (ElementImpl)xml.createElement("vBCSTRet");
																																														vBCSTRet.setTextContent(tokens[3].trim());
																																														icmsSn500.appendChild(vBCSTRet);
																																														Element vICMSSTRet = (ElementImpl)xml.createElement("vICMSSTRet");
																																														vICMSSTRet.setTextContent(tokens[4].trim());
																																														icmsSn500.appendChild(vICMSSTRet);
																																														
																																														icms.appendChild(icmsSn500);
																																														
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
																																														if(tokens[0].trim().equalsIgnoreCase("3153"))
																																														{
																																															logger.info("Contem : 3153");
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
																																															
																																															Element icmsSn900 = (ElementImpl)xml.createElement("ICMSSN900");
																																															Element orig = (ElementImpl)xml.createElement("orig");
																																															orig.setTextContent(tokens[1].trim());
																																															icmsSn900.appendChild(orig);
																																															Element csosn = (ElementImpl)xml.createElement("CSOSN");
																																															csosn.setTextContent(tokens[2].trim());
																																															icmsSn900.appendChild(csosn);
																																															
																																															icms.appendChild(icmsSn900);
																																															
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
																																															if(tokens[0].trim().equalsIgnoreCase("3154"))
																																															{
																																																logger.info("Contem : 3154");
																																																NodeList icmsSN900NL = xml.getElementsByTagName("ICMSSN900");
																																																Element icmsSN900 = null;
																																																if(icmsSN900NL.getLength() > 0)
																																																{
																																																	icmsSN900 = (ElementImpl)icmsSN900NL.item(icmsSN900NL.getLength()-1);
																																																}
																																																Element modBC = (ElementImpl)xml.createElement("modBC");
																																																modBC.setTextContent(tokens[1].trim());
																																																icmsSN900.appendChild(modBC);
																																																Element vBC = (ElementImpl)xml.createElement("vBC");
																																																vBC.setTextContent(tokens[2].trim());
																																																icmsSN900.appendChild(vBC);
																																																Element pRedBC = (ElementImpl)xml.createElement("pRedBC");
																																																pRedBC.setTextContent(tokens[3].trim());
																																																icmsSN900.appendChild(pRedBC);
																																																Element pICMS = (ElementImpl)xml.createElement("pICMS");
																																																pICMS.setTextContent(tokens[4].trim());
																																																icmsSN900.appendChild(pICMS);
																																																Element vICMS = (ElementImpl)xml.createElement("vICMS");
																																																vICMS.setTextContent(tokens[5].trim());
																																																icmsSN900.appendChild(vICMS);
																																															}
																																															else
																																															{
																																																if(tokens[0].trim().equalsIgnoreCase("3155"))
																																																{
																																																	logger.info("Contem : 3155");
																																																	NodeList icmsSN900NL = xml.getElementsByTagName("ICMSSN900");
																																																	Element icmsSN900 = null;
																																																	if(icmsSN900NL.getLength() > 0)
																																																	{
																																																		icmsSN900 = (ElementImpl)icmsSN900NL.item(icmsSN900NL.getLength()-1);
																																																	}
																																																	Element modBCST = (ElementImpl)xml.createElement("modBCST");
																																																	modBCST.setTextContent(tokens[1].trim());
																																																	icmsSN900.appendChild(modBCST);
																																																	Element pMVAST = (ElementImpl)xml.createElement("pMVAST");
																																																	pMVAST.setTextContent(tokens[2].trim());
																																																	icmsSN900.appendChild(pMVAST);
																																																	Element pRedBCST = (ElementImpl)xml.createElement("pRedBCST");
																																																	pRedBCST.setTextContent(tokens[3].trim());
																																																	icmsSN900.appendChild(pRedBCST);
																																																	Element vBCST = (ElementImpl)xml.createElement("vBCST");
																																																	vBCST.setTextContent(tokens[4].trim());
																																																	icmsSN900.appendChild(vBCST);
																																																	Element pICMSST = (ElementImpl)xml.createElement("pICMSST");
																																																	pICMSST.setTextContent(tokens[5].trim());
																																																	icmsSN900.appendChild(pICMSST);
																																																	Element vICMSST = (ElementImpl)xml.createElement("vICMSST");
																																																	vICMSST.setTextContent(tokens[6].trim());
																																																	icmsSN900.appendChild(vICMSST);
																																																	
																																																}
																																																else
																																																{
																																																	if(tokens[0].trim().equalsIgnoreCase("3156"))
																																																	{
																																																		logger.info("Contem : 3156");
																																																		NodeList icmsSN900NL = xml.getElementsByTagName("ICMSSN900");
																																																		Element icmsSN900 = null;
																																																		if(icmsSN900NL.getLength() > 0)
																																																		{
																																																			icmsSN900 = (ElementImpl)icmsSN900NL.item(icmsSN900NL.getLength()-1);
																																																		}
																																																		Element pCredSN = (ElementImpl)xml.createElement("pCredSN");
																																																		pCredSN.setTextContent(tokens[1].trim());
																																																		icmsSN900.appendChild(pCredSN);
																																																		Element vCredICMSSN = (ElementImpl)xml.createElement("vCredICMSSN");
																																																		vCredICMSSN.setTextContent(tokens[2].trim());
																																																		icmsSN900.appendChild(vCredICMSSN);
																																																	}
																																																	else
																																																	{
																																																		if(tokens[0].trim().equalsIgnoreCase("3180"))
																																																		{
																																																			logger.info("Contem : 3180");
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
																																																			Element qSelo = (ElementImpl)xml.createElement("qSelo");
																																																			qSelo.setTextContent(tokens[4].trim());
																																																			ipi.appendChild(qSelo);
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
																																																			if(tokens[0].trim().equalsIgnoreCase("3181"))
																																																			{
																																																				logger.info("Contem : 3181");
																																																				NodeList ipiNL = xml.getElementsByTagName("IPI");
																																																				Element ipi = null;
																																																				if(ipiNL.getLength() > 0)
																																																				{
																																																					ipi = (ElementImpl)ipiNL.item(ipiNL.getLength()-1);
																																																				}
																																																				Element ipiTrib = (ElementImpl)xml.createElement("IPITrib");
																																																				Element cst = (ElementImpl)xml.createElement("CST");
																																																				cst.setTextContent(tokens[1].trim());
																																																				ipiTrib.appendChild(cst);
																																																				Element vBC = (ElementImpl)xml.createElement("vBC");
																																																				vBC.setTextContent(tokens[2].trim());
																																																				ipiTrib.appendChild(vBC);
																																																				Element pIPI = (ElementImpl)xml.createElement("pIPI");
																																																				pIPI.setTextContent(tokens[3].trim());
																																																				ipiTrib.appendChild(pIPI);
																																																				ipi.appendChild(ipiTrib);
																																																			}
																																																			else
																																																			{
																																																				if(tokens[0].trim().equalsIgnoreCase("3182"))
																																																				{
																																																					logger.info("Contem : 3182");
																																																					NodeList ipiTribNL = xml.getElementsByTagName("IPITrib");
																																																					Element ipiTrib = null;
																																																					if(ipiTribNL.getLength() > 0)
																																																					{
																																																						ipiTrib = (ElementImpl)ipiTribNL.item(ipiTribNL.getLength()-1);
																																																					}
																																																					NodeList vIPINL = ipiTrib.getElementsByTagName("vIPI");
																																																					Element vIPI = null;
																																																					if(ipiTribNL.getLength() > 0)
																																																					{
																																																						vIPI = (ElementImpl)vIPINL.item(vIPINL.getLength()-1);
																																																					}
																																																					
																																																					Element qUnid = (ElementImpl)xml.createElement("qUnid");
																																																					qUnid.setTextContent(tokens[2].trim());
																																																					ipiTrib.appendChild(qUnid);
																																																					Element vUnid = (ElementImpl)xml.createElement("vUnid");
																																																					vUnid.setTextContent(tokens[3].trim());
																																																					ipiTrib.appendChild(vUnid);
																																																					
																																																					ipiTrib.insertBefore(qUnid,vUnid);
																																																					ipiTrib.insertBefore(vUnid,vIPI);
																																																				}
																																																				else
																																																				{
																																																					if(tokens[0].trim().equalsIgnoreCase("3190"))
																																																					{
																																																						logger.info("Contem : 3190");
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
																																																						if(tokens[0].trim().equalsIgnoreCase("3195"))
																																																						{
																																																							logger.info("Contem : 3195");
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
																																																							
																																																							Element ii = (ElementImpl)xml.createElement("II");
																																																							Element vBC = (ElementImpl)xml.createElement("vBC");
																																																							vBC.setTextContent(tokens[1].trim());
																																																							ii.appendChild(vBC);
																																																							Element vDespAdu = (ElementImpl)xml.createElement("vDespAdu");
																																																							vDespAdu.setTextContent(tokens[2].trim());
																																																							ii.appendChild(vDespAdu);
																																																							Element vII = (ElementImpl)xml.createElement("vII");
																																																							vII.setTextContent(tokens[3].trim());
																																																							ii.appendChild(vII);
																																																							Element vIOF = (ElementImpl)xml.createElement("vIOF");
																																																							vIOF.setTextContent(tokens[4].trim());
																																																							ii.appendChild(vIOF);
																																																							
																																																							imposto.appendChild(ii);
																																																							
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
																																																							if(tokens[0].trim().equalsIgnoreCase("3197"))
																																																							{
																																																								logger.info("Contem : 3197");
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
																																																								Element issqn = (ElementImpl)xml.createElement("ISSQN");
																																																								Element vBC = (ElementImpl)xml.createElement("vBC");
																																																								vBC.setTextContent(tokens[1].trim());
																																																								issqn.appendChild(vBC);
																																																								Element vAliq = (ElementImpl)xml.createElement("vAliq");
																																																								vAliq.setTextContent(tokens[2].trim());
																																																								issqn.appendChild(vAliq);
																																																								Element vISSQN = (ElementImpl)xml.createElement("vISSQN");
																																																								vISSQN.setTextContent(tokens[3].trim());
																																																								issqn.appendChild(vISSQN);
																																																								Element cMunFG = (ElementImpl)xml.createElement("cMunFG");
																																																								cMunFG.setTextContent(tokens[4].trim());
																																																								issqn.appendChild(cMunFG);
																																																								Element cListServ = (ElementImpl)xml.createElement("cListServ");
																																																								cListServ.setTextContent(tokens[5].trim());
																																																								issqn.appendChild(cListServ);
																																																								Element cSitTrib = (ElementImpl)xml.createElement("cSitTrib");
																																																								cSitTrib.setTextContent(tokens[6].trim());
																																																								issqn.appendChild(cSitTrib);
																																																								
																																																								imposto.appendChild(issqn);
																																																								
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
																																																								if(tokens[0].trim().equalsIgnoreCase("3200"))
																																																								{
																																																									logger.info("Contem : 3200");
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
																																																									
																																																									Element pis = (ElementImpl)xml.createElement("PIS");
																																																									Element pisAliq = (ElementImpl)xml.createElement("PISAliq");
																																																									Element cst = (ElementImpl)xml.createElement("CST");
																																																									cst.setTextContent(tokens[1].trim());
																																																									pisAliq.appendChild(cst);
																																																									Element vBC = (ElementImpl)xml.createElement("vBC");
																																																									vBC.setTextContent(tokens[2].trim());
																																																									pisAliq.appendChild(vBC);
																																																									Element pPIS = (ElementImpl)xml.createElement("pPIS");
																																																									pPIS.setTextContent(tokens[3].trim());
																																																									pisAliq.appendChild(pPIS);
																																																									Element vPIS = (ElementImpl)xml.createElement("vPIS");
																																																									vPIS.setTextContent(tokens[4].trim());
																																																									pisAliq.appendChild(vPIS);
																																																									pis.appendChild(pisAliq);
																																																									
																																																									imposto.appendChild(pis);
																																																									
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
																																																									if(tokens[0].trim().equalsIgnoreCase("3210"))
																																																									{
																																																										logger.info("Contem : 3210");
																																																										NodeList pisNL = xml.getElementsByTagName("PIS");
																																																										Element pis = null;
																																																										if(pisNL.getLength() > 0)
																																																										{
																																																											pis = (ElementImpl)pisNL.item(pisNL.getLength()-1);
																																																										}
																																																										Element pisQtde = (ElementImpl)xml.createElement("PISQtde");
																																																										Element cst = (ElementImpl)xml.createElement("CST");
																																																										cst.setTextContent(tokens[1].trim());
																																																										pisQtde.appendChild(cst);
																																																										Element qBCProd = (ElementImpl)xml.createElement("qBCProd");
																																																										qBCProd.setTextContent(tokens[2].trim());
																																																										pisQtde.appendChild(qBCProd);
																																																										Element vAliqProd = (ElementImpl)xml.createElement("vAliqProd");
																																																										vAliqProd.setTextContent(tokens[3].trim());
																																																										pisQtde.appendChild(vAliqProd);
																																																										Element vPIS = (ElementImpl)xml.createElement("vPIS");
																																																										vPIS.setTextContent(tokens[4].trim());
																																																										pisQtde.appendChild(vPIS);
																																																										
																																																										pis.appendChild(pisQtde);
																																																										
																																																									}
																																																									else
																																																									{
																																																										if(tokens[0].trim().equalsIgnoreCase("3220"))
																																																										{
																																																											logger.info("Contem : 3220");
																																																											Element pis = (ElementImpl)xml.createElement("PIS");
																																																											Element pisNT = (ElementImpl)xml.createElement("PISNT");
																																																											Element cst = (ElementImpl)xml.createElement("CST");
																																																											cst.setTextContent(tokens[1].trim());
																																																											pisNT.appendChild(cst);
																																																											
																																																											pis.appendChild(pisNT);
																																																											NodeList impostoNL = xml.getElementsByTagName("imposto");
																																																											Element imposto = null;
																																																											if(impostoNL.getLength() > 0)
																																																											{
																																																												imposto = (ElementImpl)impostoNL.item(impostoNL.getLength()-1);
																																																											}
																																																											imposto.appendChild(pis);
																																																										}
																																																										else
																																																										{
																																																											if(tokens[0].trim().equalsIgnoreCase("3230"))
																																																											{
																																																												logger.info("Contem : 3230");
																																																												NodeList detNL = xml.getElementsByTagName("det");
																																																												Element det = null;
																																																												if(detNL.getLength() > 0)
																																																												{
																																																													det = (ElementImpl)detNL.item(detNL.getLength()-1);
																																																													
																																																												}
																																																												NodeList impostoNL = det.getElementsByTagName("imposto");
																																																												Element imposto = null;
																																																												if(impostoNL.getLength() > 0)
																																																												{
																																																													imposto = (ElementImpl)impostoNL.item(impostoNL.getLength()-1);
																																																												}
																																																												NodeList pisNL = imposto.getElementsByTagName("PIS");
																																																												boolean pisNovo = false;
																																																												Element pis = null;
																																																												if(pisNL.getLength() > 0)
																																																												{
																																																													pis = (ElementImpl)pisNL.item(pisNL.getLength()-1);
																																																												}
																																																												else
																																																												{
																																																													pisNovo = true;
																																																													pis = (ElementImpl)xml.createElement("PIS");
																																																												}
																																																												Element pisOutr = (ElementImpl)xml.createElement("PISOutr");
																																																												Element cst = (ElementImpl)xml.createElement("CST");
																																																												cst.setTextContent(tokens[1].trim());
																																																												pisOutr.appendChild(cst);
																																																												Element vBC = (ElementImpl)xml.createElement("vBC");
																																																												vBC.setTextContent(tokens[2].trim());
																																																												pisOutr.appendChild(vBC);
																																																												Element pPIS = (ElementImpl)xml.createElement("pPIS");
																																																												pPIS.setTextContent(tokens[3].trim());
																																																												pisOutr.appendChild(pPIS);
																																																												//Element qBCProd = (ElementImpl)xml.createElement("qBCProd");
																																																												//qBCProd.setTextContent(tokens[4].trim());
																																																												//pisOutr.appendChild(qBCProd);
																																																												//Element vAliqProd = (ElementImpl)xml.createElement("vAliqProd");
																																																												//vAliqProd.setTextContent(tokens[5].trim());
																																																												//pisOutr.appendChild(vAliqProd);
																																																												Element vPIS = (ElementImpl)xml.createElement("vPIS");
																																																												vPIS.setTextContent(tokens[4].trim());
																																																												pisOutr.appendChild(vPIS);
																																																												
																																																												pis.appendChild(pisOutr);
																																																												if(pisNovo)
																																																												{
																																																													imposto.appendChild(pis);
																																																												}
																																																											}
																																																											else
																																																											{
																																																												if(tokens[0].trim().equalsIgnoreCase("3231"))
																																																												{
																																																													logger.info("Contem : 3231");
																																																													NodeList pisNL = xml.getElementsByTagName("PIS");
																																																													Element pis = null;
																																																													if(pisNL.getLength() > 0)
																																																													{
																																																														pis = (ElementImpl)pisNL.item(pisNL.getLength()-1);
																																																													}
																																																													Element pisOutr = (ElementImpl)xml.createElement("PISOutr");
																																																													Element cst = (ElementImpl)xml.createElement("CST");
																																																													cst.setTextContent(tokens[1].trim());
																																																													pisOutr.appendChild(cst);
																																																													Element vBC = (ElementImpl)xml.createElement("vBC");
																																																													vBC.setTextContent(tokens[2].trim());
																																																													pisOutr.appendChild(vBC);
																																																													Element pPIS = (ElementImpl)xml.createElement("pPIS");
																																																													pPIS.setTextContent(tokens[3].trim());
																																																													pisOutr.appendChild(pPIS);
																																																													Element qBCProd = (ElementImpl)xml.createElement("qBCProd");
																																																													qBCProd.setTextContent(tokens[4].trim());
																																																													pisOutr.appendChild(qBCProd);
																																																													Element vAliqProd = (ElementImpl)xml.createElement("vAliqProd");
																																																													vAliqProd.setTextContent(tokens[5].trim());
																																																													pisOutr.appendChild(vAliqProd);
																																																													Element vPIS = (ElementImpl)xml.createElement("vPIS");
																																																													vPIS.setTextContent(tokens[6].trim());
																																																													pisOutr.appendChild(vPIS);
																																																													
																																																													pis.appendChild(pisOutr);
																																																												}
																																																												else
																																																												{
																																																													if(tokens[0].trim().equalsIgnoreCase("3240"))
																																																													{
																																																														logger.info("Contem : 3240");
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
																																																														
																																																														Element pisST = (ElementImpl)xml.createElement("PISST");
																																																														Element vBC = (ElementImpl)xml.createElement("vBC");
																																																														vBC.setTextContent(tokens[1].trim());
																																																														pisST.appendChild(vBC);
																																																														Element pPIS = (ElementImpl)xml.createElement("pPIS");
																																																														pPIS.setTextContent(tokens[2].trim());
																																																														pisST.appendChild(pPIS);
																																																														Element vPIS = (ElementImpl)xml.createElement("vPIS");
																																																														vPIS.setTextContent(tokens[3].trim());
																																																														pisST.appendChild(vPIS);
																																																														
																																																														imposto.appendChild(pisST);
																																																														
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
																																																														if(tokens[0].trim().equalsIgnoreCase("3250"))
																																																														{
																																																															logger.info("Contem : 3250");
																																																															NodeList pisSTNL = xml.getElementsByTagName("PISST");
																																																															Element pisST = null;
																																																															if(pisSTNL.getLength() > 0)
																																																															{
																																																																pisST = (ElementImpl)pisSTNL.item(pisSTNL.getLength()-1);
																																																															}
																																																															NodeList vPISNL = pisST.getElementsByTagName("vPIS");
																																																															Element vPIS = null;
																																																															if(vPISNL.getLength() > 0)
																																																															{
																																																																vPIS = (ElementImpl)vPISNL.item(vPISNL.getLength()-1);
																																																															}
																																																															
																																																															Element qBCProd = (ElementImpl)xml.createElement("qBCProd");
																																																															qBCProd.setTextContent(tokens[1].trim());
																																																															Element vAliqProd = (ElementImpl)xml.createElement("vAliqProd");
																																																															vAliqProd.setTextContent(tokens[2].trim());
																																																															
																																																															pisST.insertBefore(qBCProd, vAliqProd);
																																																															pisST.insertBefore(vAliqProd, vPIS);
																																																														}
																																																														else
																																																														{
																																																															if(tokens[0].trim().equalsIgnoreCase("3300"))
																																																															{
																																																																logger.info("Contem : 3300");
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
																																																																
																																																																Element cofins = (ElementImpl)xml.createElement("COFINS");
																																																																Element cofinsAliq = (ElementImpl)xml.createElement("COFINSAliq");
																																																																Element cst = (ElementImpl)xml.createElement("CST");
																																																																cst.setTextContent(tokens[1].trim());
																																																																cofinsAliq.appendChild(cst);
																																																																Element vBC = (ElementImpl)xml.createElement("vBC");
																																																																vBC.setTextContent(tokens[2].trim());
																																																																cofinsAliq.appendChild(vBC);
																																																																Element pCOFINS = (ElementImpl)xml.createElement("pCOFINS");
																																																																pCOFINS.setTextContent(tokens[3].trim());
																																																																cofinsAliq.appendChild(pCOFINS);
																																																																Element vCOFINS = (ElementImpl)xml.createElement("vCOFINS");
																																																																vCOFINS.setTextContent(tokens[4].trim());
																																																																cofinsAliq.appendChild(vCOFINS);
																																																																cofins.appendChild(cofinsAliq);
																																																																
																																																																imposto.appendChild(cofins);
																																																																
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
																																																																if(tokens[0].trim().equalsIgnoreCase("3310"))
																																																																{
																																																																	logger.info("Contem : 3310");
																																																																	NodeList cofinsNL = xml.getElementsByTagName("COFINS");
																																																																	Element cofins = null;
																																																																	if(cofinsNL.getLength() > 0)
																																																																	{
																																																																		cofins = (ElementImpl)cofinsNL.item(cofinsNL.getLength()-1);
																																																																	}
																																																																	Element cofinsQtde = (ElementImpl)xml.createElement("COFINSQtde");
																																																																	Element cst = (ElementImpl)xml.createElement("CST");
																																																																	cst.setTextContent(tokens[1].trim());
																																																																	cofinsQtde.appendChild(cst);
																																																																	Element qBCProd = (ElementImpl)xml.createElement("qBCProd");
																																																																	qBCProd.setTextContent(tokens[2].trim());
																																																																	cofinsQtde.appendChild(qBCProd);
																																																																	Element vAliqProd = (ElementImpl)xml.createElement("vAliqProd");
																																																																	vAliqProd.setTextContent(tokens[3].trim());
																																																																	cofinsQtde.appendChild(vAliqProd);
																																																																	Element vCOFINS = (ElementImpl)xml.createElement("vCOFINS");
																																																																	vCOFINS.setTextContent(tokens[4].trim());
																																																																	cofinsQtde.appendChild(vCOFINS);
																																																																	
																																																																	cofins.appendChild(cofinsQtde);
																																																																}
																																																																else
																																																																{
																																																																	if(tokens[0].trim().equalsIgnoreCase("3320"))
																																																																	{
																																																																		logger.info("Contem : 3320");
																																																																		Element cofins = (ElementImpl)xml.createElement("COFINS");
																																																																		Element cofinsNT = (ElementImpl)xml.createElement("COFINSNT");
																																																																		Element cst = (ElementImpl)xml.createElement("CST");
																																																																		cst.setTextContent(tokens[1].trim());
																																																																		cofinsNT.appendChild(cst);
																																																																		
																																																																		cofins.appendChild(cofinsNT);
																																																																		
																																																																		NodeList impostoNL = xml.getElementsByTagName("imposto");
																																																																		Element imposto = null;
																																																																		if(impostoNL.getLength() > 0)
																																																																		{
																																																																			imposto = (ElementImpl)impostoNL.item(impostoNL.getLength()-1);
																																																																		}
																																																																		imposto.appendChild(cofins);																																																																		
																																																																	}
																																																																	else
																																																																	{
																																																																		if(tokens[0].trim().equalsIgnoreCase("3330"))
																																																																		{
																																																																			logger.info("Contem : 3330");
																																																																			NodeList detNL = xml.getElementsByTagName("det");
																																																																			Element det = null;
																																																																			if(detNL.getLength() > 0)
																																																																			{
																																																																				det = (ElementImpl)detNL.item(detNL.getLength()-1);
																																																																				
																																																																			}
																																																																			NodeList impostoNL = det.getElementsByTagName("imposto");
																																																																			Element imposto = null;
																																																																			if(impostoNL.getLength() > 0)
																																																																			{
																																																																				imposto = (ElementImpl)impostoNL.item(impostoNL.getLength()-1);
																																																																			}
																																																																			NodeList cofinsNL = imposto.getElementsByTagName("COFINS");
																																																																			boolean cofinsNovo = false;
																																																																			Element cofins = null;
																																																																			if(cofinsNL.getLength() > 0)
																																																																			{
																																																																				cofins = (ElementImpl)cofinsNL.item(cofinsNL.getLength()-1);
																																																																			}
																																																																			else
																																																																			{
																																																																				cofinsNovo = true;
																																																																				cofins = (ElementImpl)xml.createElement("COFINS");
																																																																			}
																																																																			Element cofinsOutr = (ElementImpl)xml.createElement("COFINSOutr");
																																																																			Element cst = (ElementImpl)xml.createElement("CST");
																																																																			cst.setTextContent(tokens[1].trim());
																																																																			cofinsOutr.appendChild(cst);
																																																																			Element vBC = (ElementImpl)xml.createElement("vBC");
																																																																			vBC.setTextContent(tokens[2].trim());
																																																																			cofinsOutr.appendChild(vBC);
																																																																			Element pCOFINS = (ElementImpl)xml.createElement("pCOFINS");
																																																																			pCOFINS.setTextContent(tokens[3].trim());
																																																																			cofinsOutr.appendChild(pCOFINS);
																																																																			Element vCOFINS = (ElementImpl)xml.createElement("vCOFINS");
																																																																			vCOFINS.setTextContent(tokens[4].trim());
																																																																			cofinsOutr.appendChild(vCOFINS);
																																																																			
																																																																			cofins.appendChild(cofinsOutr);
																																																																			if(cofinsNovo)
																																																																			{
																																																																				imposto.appendChild(cofins);
																																																																			}
																																																																		}
																																																																		else
																																																																		{
																																																																			if(tokens[0].trim().equalsIgnoreCase("3331"))
																																																																			{
																																																																				logger.info("Contem : 3331");
																																																																				NodeList cofinsOutrNL = xml.getElementsByTagName("COFINSOutr");
																																																																				Element cofinsOutr = null;
																																																																				if(cofinsOutrNL.getLength() > 0)
																																																																				{
																																																																					cofinsOutr = (ElementImpl)cofinsOutrNL.item(cofinsOutrNL.getLength()-1);
																																																																				}
																																																																				NodeList vCOFINSNL = cofinsOutr.getElementsByTagName("vCOFINS");
																																																																				Element vCOFINS = null;
																																																																				if(vCOFINSNL.getLength() > 0)
																																																																				{
																																																																					vCOFINS = (ElementImpl)vCOFINSNL.item(vCOFINSNL.getLength()-1);
																																																																				}
																																																																				
																																																																				Element qBCProd = (ElementImpl)xml.createElement("qBCProd");
																																																																				qBCProd.setTextContent(tokens[2].trim());
																																																																				
																																																																				Element vAliqProd = (ElementImpl)xml.createElement("vAliqProd");
																																																																				vAliqProd.setTextContent(tokens[3].trim());
																																																																				
																																																																				cofinsOutr.insertBefore(qBCProd,vAliqProd);
																																																																				cofinsOutr.insertBefore(vAliqProd,vCOFINS);
																																																																			}
																																																																			else
																																																																			{
																																																																				if(tokens[0].trim().equalsIgnoreCase("3400"))
																																																																				{
																																																																					logger.info("Contem : 3400");
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
																																																																					
																																																																					Element cofinsST = (ElementImpl)xml.createElement("COFINSST");
																																																																					Element vBC = (ElementImpl)xml.createElement("vBC");
																																																																					vBC.setTextContent(tokens[1].trim());
																																																																					cofinsST.appendChild(vBC);
																																																																					Element pCOFINS = (ElementImpl)xml.createElement("pCOFINS");
																																																																					pCOFINS.setTextContent(tokens[2].trim());
																																																																					cofinsST.appendChild(pCOFINS);
																																																																					Element vCOFINS = (ElementImpl)xml.createElement("vCOFINS");
																																																																					vCOFINS.setTextContent(tokens[3].trim());
																																																																					cofinsST.appendChild(vCOFINS);
																																																																					
																																																																					imposto.appendChild(cofinsST);
																																																																					
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
																																																																					if(tokens[0].trim().equalsIgnoreCase("3401"))
																																																																					{
																																																																						logger.info("Contem : 3401");
																																																																						NodeList cofinsSTNL = xml.getElementsByTagName("COFINSST");
																																																																						Element cofinsST = null;
																																																																						if(cofinsSTNL.getLength() > 0)
																																																																						{
																																																																							cofinsST = (ElementImpl)cofinsSTNL.item(cofinsSTNL.getLength()-1);
																																																																						}
																																																																						NodeList vCOFINSNL = cofinsST.getElementsByTagName("vCOFINS");
																																																																						Element vCOFINS = null;
																																																																						if(vCOFINSNL.getLength() > 0)
																																																																						{
																																																																							
																																																																							vCOFINS = (ElementImpl)vCOFINSNL.item(vCOFINSNL.getLength()-1);
																																																																						}
																																																																						
																																																																						Element qBCProd = (ElementImpl)xml.createElement("qBCProd");
																																																																						qBCProd.setTextContent(tokens[1].trim());
																																																																						Element vAliqProd = (ElementImpl)xml.createElement("vAliqProd");
																																																																						vAliqProd.setTextContent(tokens[2].trim());
																																																																						
																																																																						cofinsST.insertBefore(vAliqProd,qBCProd);
																																																																						cofinsST.insertBefore(qBCProd,vCOFINS);
																																																																						
																																																																					}
																																																																					else
																																																																					{
																																																																						if(tokens[0].trim().equalsIgnoreCase("3600"))
																																																																						{
																																																																							logger.info("Contem : 3600");
																																																																							NodeList detNL = xml.getElementsByTagName("det");
																																																																							Element det = null;
																																																																							if(detNL.getLength() > 0)
																																																																							{
																																																																								det = (ElementImpl)detNL.item(detNL.getLength() - 1);
																																																																								
																																																																							}
																																																																							Element infAdProd = (ElementImpl)xml.createElement("infAdProd");
																																																																							infAdProd.setTextContent(tokens[1].trim());
																																																																							det.appendChild(infAdProd);
																																																																						}
																																																																						else
																																																																						{
																																																																							if(tokens[0].trim().equalsIgnoreCase("4000"))
																																																																							{
																																																																								logger.info("Contem : 4000");
																																																																								NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																																																																								Element infNFe = null;
																																																																								if(infNFeNL.getLength() > 0)
																																																																								{
																																																																									infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																																																																								}
																																																																								
																																																																								Element total = (ElementImpl)xml.createElement("total");
																																																																								Element icmsTot = (ElementImpl)xml.createElement("ICMSTot");
																																																																								Element vBC = (ElementImpl)xml.createElement("vBC");
																																																																								vBC.setTextContent(tokens[1].trim());
																																																																								icmsTot.appendChild(vBC);
																																																																								Element vICMS = (ElementImpl)xml.createElement("vICMS");
																																																																								vICMS.setTextContent(tokens[2].trim());
																																																																								icmsTot.appendChild(vICMS);
																																																																								Element vBCST = (ElementImpl)xml.createElement("vBCST");
																																																																								vBCST.setTextContent(tokens[3].trim());
																																																																								icmsTot.appendChild(vBCST);
																																																																								Element vST = (ElementImpl)xml.createElement("vST");
																																																																								vST.setTextContent(tokens[4].trim());
																																																																								icmsTot.appendChild(vST);
																																																																								Element vProd = (ElementImpl)xml.createElement("vProd");
																																																																								vProd.setTextContent(tokens[5].trim());
																																																																								icmsTot.appendChild(vProd);
																																																																								Element vFrete = (ElementImpl)xml.createElement("vFrete");
																																																																								vFrete.setTextContent(tokens[6].trim());
																																																																								icmsTot.appendChild(vFrete);
																																																																								if(!tokens[7].trim().equalsIgnoreCase(""))
																																																																								{
																																																																									Element vSeg = (ElementImpl)xml.createElement("vSeg");
																																																																									vSeg.setTextContent(tokens[7].trim());
																																																																									icmsTot.appendChild(vSeg);
																																																																								}
																																																																								if(!tokens[8].trim().equalsIgnoreCase(""))
																																																																								{
																																																																									Element vDesc = (ElementImpl)xml.createElement("vDesc");
																																																																									vDesc.setTextContent(tokens[8].trim());
																																																																									icmsTot.appendChild(vDesc);
																																																																								}
																																																																								Element vII = (ElementImpl)xml.createElement("vII");
																																																																								vII.setTextContent(tokens[9].trim());
																																																																								icmsTot.appendChild(vII);
																																																																								Element vIPI = (ElementImpl)xml.createElement("vIPI");
																																																																								vIPI.setTextContent(tokens[10].trim());
																																																																								icmsTot.appendChild(vIPI);
																																																																								Element vPIS = (ElementImpl)xml.createElement("vPIS");
																																																																								vPIS.setTextContent(tokens[11].trim());
																																																																								icmsTot.appendChild(vPIS);
																																																																								Element vCOFINS = (ElementImpl)xml.createElement("vCOFINS");
																																																																								vCOFINS.setTextContent(tokens[12].trim());
																																																																								icmsTot.appendChild(vCOFINS);
																																																																								if(!tokens[13].trim().equalsIgnoreCase(""))
																																																																								{
																																																																									Element vOutro = (ElementImpl)xml.createElement("vOutro");
																																																																									vOutro.setTextContent(tokens[13].trim());
																																																																									icmsTot.appendChild(vOutro);
																																																																								}
																																																																								Element vNF = (ElementImpl)xml.createElement("vNF");
																																																																								vNF.setTextContent(tokens[14].trim());
																																																																								icmsTot.appendChild(vNF);
																																																																								
																																																																								total.appendChild(icmsTot);
																																																																								infNFe.appendChild(total);
																																																																							}
																																																																							else
																																																																							{
																																																																								if(tokens[0].trim().equalsIgnoreCase("4100"))
																																																																								{
																																																																									logger.info("Contem : 4100");
																																																																									NodeList totalNL = xml.getElementsByTagName("total");
																																																																									Element total = null;
																																																																									if(totalNL.getLength() > 0)
																																																																									{
																																																																										total = (ElementImpl)totalNL.item(totalNL.getLength()-1);
																																																																									}
																																																																									Element ISSQNtot = (ElementImpl)xml.createElement("ISSQNtot");
																																																																									Element vServ = (ElementImpl)xml.createElement("vServ");
																																																																									vServ.setTextContent(tokens[1].trim());
																																																																									ISSQNtot.appendChild(vServ);
																																																																									Element vBC = (ElementImpl)xml.createElement("vBC");
																																																																									vBC.setTextContent(tokens[2].trim());
																																																																									ISSQNtot.appendChild(vBC);
																																																																									Element vISS = (ElementImpl)xml.createElement("vISS");
																																																																									vISS.setTextContent(tokens[3].trim());
																																																																									ISSQNtot.appendChild(vISS);
																																																																									Element vPIS = (ElementImpl)xml.createElement("vPIS");
																																																																									vPIS.setTextContent(tokens[4].trim());
																																																																									ISSQNtot.appendChild(vPIS);
																																																																									Element vCOFINS = (ElementImpl)xml.createElement("vCOFINS");
																																																																									vCOFINS.setTextContent(tokens[5].trim());
																																																																									ISSQNtot.appendChild(vCOFINS);
																																																																									
																																																																									total.appendChild(ISSQNtot);
																																																																								}
																																																																								else
																																																																								{
																																																																									if(tokens[0].trim().equalsIgnoreCase("4200"))
																																																																									{
																																																																										logger.info("4200");
																																																																										NodeList totalNL = xml.getElementsByTagName("total");
																																																																										Element total = null;
																																																																										if(totalNL.getLength() > 0)
																																																																										{
																																																																											total = (ElementImpl)totalNL.item(totalNL.getLength()-1);
																																																																										}
																																																																										Element retTrib = (ElementImpl)xml.createElement("retTrib");
																																																																										Element vRetPIS = (ElementImpl)xml.createElement("vRetPIS");
																																																																										vRetPIS.setTextContent(tokens[1].trim());
																																																																										retTrib.appendChild(vRetPIS);
																																																																										Element vRetCOFINS = (ElementImpl)xml.createElement("vRetCOFINS");
																																																																										vRetCOFINS.setTextContent(tokens[2].trim());
																																																																										retTrib.appendChild(vRetCOFINS);
																																																																										Element vRetCSLL = (ElementImpl)xml.createElement("vRetCSLL");
																																																																										vRetCSLL.setTextContent(tokens[3].trim());
																																																																										retTrib.appendChild(vRetCSLL);
																																																																										Element vBCIRRF = (ElementImpl)xml.createElement("vBCIRRF");
																																																																										vBCIRRF.setTextContent(tokens[4].trim());
																																																																										retTrib.appendChild(vBCIRRF);
																																																																										Element vIRRF = (ElementImpl)xml.createElement("vIRRF");
																																																																										vIRRF.setTextContent(tokens[5].trim());
																																																																										retTrib.appendChild(vIRRF);
																																																																										Element vBCRetPrev = (ElementImpl)xml.createElement("vBCRetPrev");
																																																																										vBCRetPrev.setTextContent(tokens[6].trim());
																																																																										retTrib.appendChild(vBCRetPrev);
																																																																										Element vRetPrev = (ElementImpl)xml.createElement("vRetPrev");
																																																																										vRetPrev.setTextContent(tokens[7].trim());
																																																																										retTrib.appendChild(vRetPrev);
																																																																										
																																																																										total.appendChild(retTrib);
																																																																									}
																																																																									else
																																																																									{
																																																																										if(tokens[0].trim().equalsIgnoreCase("5000"))
																																																																										{
																																																																											logger.info("Contem : 5000");
																																																																											NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																																																																											Element infNFe = null;
																																																																											if(infNFeNL.getLength() > 0)
																																																																											{
																																																																												infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																																																																											}
																																																																											Element transp = (ElementImpl)xml.createElement("transp");
																																																																											Element modFrete = (ElementImpl)xml.createElement("modFrete");
																																																																											modFrete.setTextContent(tokens[1].trim());
																																																																											transp.appendChild(modFrete);
																																																																											
																																																																											infNFe.appendChild(transp);
																																																																										}
																																																																										else
																																																																										{
																																																																											if(tokens[0].trim().equalsIgnoreCase("5100"))
																																																																											{
																																																																												logger.info("Contem : 5100");
																																																																												NodeList transpNL = xml.getElementsByTagName("transp");
																																																																												Element transp = null;
																																																																												if(transpNL.getLength() > 0)
																																																																												{
																																																																													transp = (ElementImpl)transpNL.item(transpNL.getLength()-1);
																																																																												}
																																																																												Element transporta = (ElementImpl)xml.createElement("transporta");
																																																																												if(tokens[1].trim().length() == 14)
																																																																												{
																																																																													Element cnpj = (ElementImpl)xml.createElement("CNPJ");
																																																																													cnpj.setTextContent(tokens[1].trim());
																																																																													transporta.appendChild(cnpj);
																																																																												}
																																																																												else
																																																																												{
																																																																													if(tokens[1].trim().length() == 11)
																																																																													{
																																																																														Element cpf = (ElementImpl)xml.createElement("CPF");
																																																																														cpf.setTextContent(tokens[1].trim());
																																																																														transporta.appendChild(cpf);
																																																																													}
																																																																												}
																																																																												Element xNome = (ElementImpl)xml.createElement("xNome");
																																																																												xNome.setTextContent(tokens[2].trim());
																																																																												transporta.appendChild(xNome);
																																																																												Element ie = (ElementImpl)xml.createElement("IE");
																																																																												ie.setTextContent(tokens[3].trim());
																																																																												transporta.appendChild(ie);
																																																																												if(!tokens[4].trim().equalsIgnoreCase(""))
																																																																												{
																																																																													Element xEnder = (ElementImpl)xml.createElement("xEnder");
																																																																													xEnder.setTextContent(tokens[4].trim());
																																																																													transporta.appendChild(xEnder);
																																																																												}
																																																																												Element xMun = (ElementImpl)xml.createElement("xMun");
																																																																												xMun.setTextContent(tokens[5].trim());
																																																																												transporta.appendChild(xMun);
																																																																												Element uf = (ElementImpl)xml.createElement("UF");
																																																																												uf.setTextContent(tokens[6].trim());
																																																																												transporta.appendChild(uf);
																																																																												
																																																																												transp.appendChild(transporta);
																																																																											}
																																																																											else
																																																																											{
																																																																												if(tokens[0].trim().equalsIgnoreCase("5200"))
																																																																												{
																																																																													logger.info("Contem : 5200");
																																																																													NodeList transpNL = xml.getElementsByTagName("transp");
																																																																													Element transp = null;
																																																																													if(transpNL.getLength() > 0)
																																																																													{
																																																																														transp = (ElementImpl)transpNL.item(transpNL.getLength()-1);
																																																																													}
																																																																													Element retTransp = (ElementImpl)xml.createElement("retTransp");
																																																																													Element vServ = (ElementImpl)xml.createElement("vServ");
																																																																													vServ.setTextContent(tokens[1].trim());
																																																																													retTransp.appendChild(vServ);
																																																																													Element vBCRet = (ElementImpl)xml.createElement("vBCRet");
																																																																													vBCRet.setTextContent(tokens[2].trim());
																																																																													retTransp.appendChild(vBCRet);
																																																																													Element pICMSRet = (ElementImpl)xml.createElement("pICMSRet");
																																																																													pICMSRet.setTextContent(tokens[3].trim());
																																																																													retTransp.appendChild(pICMSRet);
																																																																													Element vICMSRet = (ElementImpl)xml.createElement("vICMSRet");
																																																																													vICMSRet.setTextContent(tokens[4].trim());
																																																																													retTransp.appendChild(vICMSRet);
																																																																													Element cfop = (ElementImpl)xml.createElement("CFOP");
																																																																													cfop.setTextContent(tokens[5].trim());
																																																																													retTransp.appendChild(cfop);
																																																																													Element cMunFG = (ElementImpl)xml.createElement("cMunFG");
																																																																													cMunFG.setTextContent(tokens[6].trim());
																																																																													retTransp.appendChild(cMunFG);
																																																																													
																																																																													transp.appendChild(retTransp);
																																																																												}
																																																																												else
																																																																												{
																																																																													if(tokens[0].trim().equalsIgnoreCase("5300"))
																																																																													{
																																																																														logger.info("Contem : 5300");
																																																																														NodeList transpNL = xml.getElementsByTagName("transp");
																																																																														Element transp = null;
																																																																														if(transpNL.getLength() > 0)
																																																																														{
																																																																															transp = (ElementImpl)transpNL.item(transpNL.getLength()-1);
																																																																														}
																																																																														Element veicTransp = (ElementImpl)xml.createElement("veicTransp");
																																																																														Element placa = (ElementImpl)xml.createElement("placa");
																																																																														placa.setTextContent(tokens[1].trim());
																																																																														veicTransp.appendChild(placa);
																																																																														Element uf = (ElementImpl)xml.createElement("UF");
																																																																														uf.setTextContent(tokens[2].trim());
																																																																														veicTransp.appendChild(uf);
																																																																														if(!tokens[3].trim().equalsIgnoreCase(""))
																																																																														{
																																																																															Element rntc = (ElementImpl)xml.createElement("RNTC");
																																																																															rntc.setTextContent(tokens[3].trim());
																																																																															veicTransp.appendChild(rntc);
																																																																														}
																																																																														transp.appendChild(veicTransp);
																																																																													}
																																																																													else
																																																																													{
																																																																														if(tokens[0].trim().equalsIgnoreCase("5400"))
																																																																														{
																																																																															logger.info("5400");
																																																																															NodeList transpNL = xml.getElementsByTagName("transp");
																																																																															Element transp = null;
																																																																															if(transpNL.getLength() > 0)
																																																																															{
																																																																																transp = (ElementImpl)transpNL.item(transpNL.getLength()-1);
																																																																															}
																																																																															Element reboque = (ElementImpl)xml.createElement("reboque");
																																																																															Element placa = (ElementImpl)xml.createElement("placa");
																																																																															placa.setTextContent(tokens[1].trim());
																																																																															reboque.appendChild(placa);
																																																																															Element uf = (ElementImpl)xml.createElement("UF");
																																																																															uf.setTextContent(tokens[2].trim());
																																																																															reboque.appendChild(uf);
																																																																															if(!tokens[3].trim().equalsIgnoreCase(""))
																																																																															{
																																																																																Element rntc = (ElementImpl)xml.createElement("RNTC");
																																																																																rntc.setTextContent(tokens[3].trim());
																																																																																reboque.appendChild(rntc);
																																																																															}
																																																																															transp.appendChild(reboque);
																																																																														}
																																																																														else
																																																																														{
																																																																															if(tokens[0].trim().equalsIgnoreCase("5500"))
																																																																															{
																																																																																logger.info("Contem : 5500");
																																																																																NodeList transpNL = xml.getElementsByTagName("transp");
																																																																																Element transp = null;
																																																																																if(transpNL.getLength() > 0)
																																																																																{
																																																																																	transp = (ElementImpl)transpNL.item(transpNL.getLength()-1);
																																																																																}
																																																																																Element vagao = (ElementImpl)xml.createElement("vagao");
																																																																																vagao.setTextContent(tokens[1].trim());
																																																																																transp.appendChild(vagao);
																																																																															}
																																																																															else
																																																																															{
																																																																																if(tokens[0].trim().equalsIgnoreCase("5600"))
																																																																																{
																																																																																	logger.info("Contem : 5600");
																																																																																	NodeList transpNL = xml.getElementsByTagName("transp");
																																																																																	Element transp = null;
																																																																																	if(transpNL.getLength() > 0)
																																																																																	{
																																																																																		transp = (ElementImpl)transpNL.item(transpNL.getLength()-1);
																																																																																	}
																																																																																	Element balsa = (ElementImpl)xml.createElement("balsa");
																																																																																	balsa.setTextContent(tokens[1].trim());
																																																																																	transp.appendChild(balsa);
																																																																																}
																																																																																else
																																																																																{
																																																																																	if(tokens[0].trim().equalsIgnoreCase("5700"))
																																																																																	{
																																																																																		logger.info("Contem : 5700");
																																																																																		NodeList transpNL = xml.getElementsByTagName("transp");
																																																																																		Element transp = null;
																																																																																		if(transpNL.getLength() > 0)
																																																																																		{
																																																																																			transp = (ElementImpl)transpNL.item(transpNL.getLength()-1);
																																																																																		}
																																																																																		Element vol = (ElementImpl)xml.createElement("vol");
																																																																																		if(!tokens[1].trim().equalsIgnoreCase(""))
																																																																																		{
																																																																																			
																																																																																			Element qVol = (ElementImpl)xml.createElement("qVol");
																																																																																			qVol.setTextContent(tokens[1].trim());
																																																																																			vol.appendChild(qVol);
																																																																																		}
																																																																																		if(!tokens[2].trim().equalsIgnoreCase(""))
																																																																																		{
																																																																																			Element esp = (ElementImpl)xml.createElement("esp");
																																																																																			esp.setTextContent(tokens[2].trim());
																																																																																			vol.appendChild(esp);
																																																																																		}
																																																																																		if(!tokens[3].trim().equalsIgnoreCase(""))
																																																																																		{
																																																																																			Element marca = (ElementImpl)xml.createElement("marca");
																																																																																			marca.setTextContent(tokens[3].trim());
																																																																																			vol.appendChild(marca);
																																																																																		}
																																																																																		if(!tokens[4].trim().equalsIgnoreCase(""))
																																																																																		{
																																																																																			Element nVol = (ElementImpl)xml.createElement("nVol");
																																																																																			nVol.setTextContent(tokens[4].trim());
																																																																																			vol.appendChild(nVol);
																																																																																		}
																																																																																		Element pesoL = (ElementImpl)xml.createElement("pesoL");
																																																																																		pesoL.setTextContent(tokens[5].trim());
																																																																																		vol.appendChild(pesoL);
																																																																																		Element pesoB = (ElementImpl)xml.createElement("pesoB");
																																																																																		pesoB.setTextContent(tokens[6].trim());
																																																																																		vol.appendChild(pesoB);
																																																																																		
																																																																																		transp.appendChild(vol);
																																																																																	}
																																																																																	else
																																																																																	{
																																																																																		if(tokens[0].trim().equalsIgnoreCase("5710"))
																																																																																		{
																																																																																			logger.info("Contem : 5710");
																																																																																			NodeList volNL = xml.getElementsByTagName("vol");
																																																																																			Element vol = null;
																																																																																			if(volNL.getLength() > 0)
																																																																																			{
																																																																																				vol = (ElementImpl)volNL.item(volNL.getLength()-1);
																																																																																			}
																																																																																			Element lacres = (ElementImpl)xml.createElement("lacres");
																																																																																			Element nLacre = (ElementImpl)xml.createElement("nLacre");
																																																																																			nLacre.setTextContent(tokens[1].trim());
																																																																																			lacres.appendChild(nLacre);
																																																																																			vol.appendChild(lacres);
																																																																																		}
																																																																																		else
																																																																																		{
																																																																																			if(tokens[0].trim().equalsIgnoreCase("6000"))
																																																																																			{
																																																																																				logger.info("Contem : 6000");
																																																																																				NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																																																																																				Element infNFe = null;
																																																																																				if(infNFeNL.getLength() > 0)
																																																																																				{
																																																																																					infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																																																																																				}
																																																																																				Element cobr = (ElementImpl)xml.createElement("cobr");
																																																																																				Element fat = (ElementImpl)xml.createElement("fat");
																																																																																				Element nFat = (ElementImpl)xml.createElement("nFat");
																																																																																				nFat.setTextContent(tokens[1].trim());
																																																																																				fat.appendChild(nFat);
																																																																																				Element vOrig = (ElementImpl)xml.createElement("vOrig");
																																																																																				vOrig.setTextContent(tokens[2].trim());
																																																																																				fat.appendChild(vOrig);
																																																																																				if(!tokens[3].trim().equalsIgnoreCase(""))
																																																																																				{
																																																																																					Element vDesc = (ElementImpl)xml.createElement("vDesc");
																																																																																					vDesc.setTextContent(tokens[3].trim());
																																																																																					fat.appendChild(vDesc);
																																																																																				}
																																																																																				Element vLiq = (ElementImpl)xml.createElement("vLiq");
																																																																																				vLiq.setTextContent(tokens[4].trim());
																																																																																				fat.appendChild(vLiq);
																																																																																				cobr.appendChild(fat);
																																																																																				infNFe.appendChild(cobr);
																																																																																				
																																																																																			}
																																																																																			else
																																																																																			{
																																																																																				if(tokens[0].trim().equalsIgnoreCase("6100"))
																																																																																				{
																																																																																					logger.info("Contem : 6100");
																																																																																					boolean cobrNovo = false;
																																																																																					NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																																																																																					Element infNFe = null;
																																																																																					if(infNFeNL.getLength() > 0)
																																																																																					{
																																																																																						infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																																																																																					}
																																																																																					NodeList cobrNL = xml.getElementsByTagName("cobr");
																																																																																					Element cobr = null;
																																																																																					if(cobrNL.getLength() > 0)
																																																																																					{
																																																																																						cobr = (ElementImpl)cobrNL.item(cobrNL.getLength()-1);
																																																																																					}
																																																																																					else
																																																																																					{
																																																																																						cobrNovo = true;
																																																																																						cobr = (ElementImpl)xml.createElement("cobr");
																																																																																					}
																																																																																					Element dup = (ElementImpl)xml.createElement("dup");
																																																																																					Element nDup = (ElementImpl)xml.createElement("nDup");
																																																																																					nDup.setTextContent(tokens[1].trim());
																																																																																					dup.appendChild(nDup);
																																																																																					Element dVenc = (ElementImpl)xml.createElement("dVenc");
																																																																																					dVenc.setTextContent(tokens[2].trim());
																																																																																					dup.appendChild(dVenc);
																																																																																					Element vDup = (ElementImpl)xml.createElement("vDup");
																																																																																					vDup.setTextContent(tokens[3].trim());
																																																																																					dup.appendChild(vDup);
																																																																																					
																																																																																					cobr.appendChild(dup);
																																																																																					
																																																																																					if(cobrNovo)
																																																																																					{
																																																																																						infNFe.appendChild(cobr);
																																																																																					}
																																																																																				}
																																																																																				else
																																																																																				{
																																																																																					if(tokens[0].trim().equalsIgnoreCase("7000"))
																																																																																					{
																																																																																						logger.info("Contem : 7000");
																																																																																						NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																																																																																						Element infNFe = null;
																																																																																						if(infNFeNL.getLength() > 0)
																																																																																						{
																																																																																							infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																																																																																						}
																																																																																						Element infAdic = (ElementImpl)xml.createElement("infAdic");
																																																																																						if(!tokens[1].trim().equalsIgnoreCase(""))
																																																																																						{
																																																																																							Element infAdFisco = (ElementImpl)xml.createElement("infAdFisco");
																																																																																							infAdFisco.setTextContent(tokens[1].trim());
																																																																																							infAdic.appendChild(infAdFisco);
																																																																																						}
																																																																																						Element infCpl = (ElementImpl)xml.createElement("infCpl");
																																																																																						infCpl.setTextContent(tokens[2].trim());
																																																																																						infAdic.appendChild(infCpl);
																																																																																						infNFe.appendChild(infAdic);
																																																																																					}
																																																																																					else
																																																																																					{
																																																																																						if(tokens[0].trim().equalsIgnoreCase("7100"))
																																																																																						{
																																																																																							logger.info("7100");
																																																																																							NodeList infAdicNL = xml.getElementsByTagName("infAdic");
																																																																																							Element infAdic = null;
																																																																																							if(infAdicNL.getLength() > 0)
																																																																																							{
																																																																																								infAdic = (ElementImpl)infAdicNL.item(infAdicNL.getLength()-1);
																																																																																							}
																																																																																							Element obsCont = (ElementImpl)xml.createElement("obsCont");
																																																																																							Element xTexto = (ElementImpl)xml.createElement("xTexto");
																																																																																							xTexto.setTextContent(tokens[1].trim());
																																																																																							obsCont.appendChild(xTexto);
																																																																																							infAdic.appendChild(obsCont);
																																																																																						}
																																																																																						else
																																																																																						{
																																																																																							if(tokens[0].trim().equalsIgnoreCase("7200"))
																																																																																							{
																																																																																								logger.info("Contem : 7200");
																																																																																								NodeList infAdicNL = xml.getElementsByTagName("infAdic");
																																																																																								Element infAdic = null;
																																																																																								if(infAdicNL.getLength() > 0)
																																																																																								{
																																																																																									infAdic = (ElementImpl)infAdicNL.item(infAdicNL.getLength()-1);
																																																																																								}
																																																																																								Element obsFisco = (ElementImpl)xml.createElement("obsFisco");
																																																																																								Element xTexto = (ElementImpl)xml.createElement("xTexto");
																																																																																								xTexto.setTextContent(tokens[1].trim());
																																																																																								obsFisco.appendChild(xTexto);
																																																																																								infAdic.appendChild(obsFisco);
																																																																																							}
																																																																																							else
																																																																																							{
																																																																																								if(tokens[0].trim().equalsIgnoreCase("7300"))
																																																																																								{
																																																																																									logger.info("Contem : 7300");
																																																																																									NodeList infAdicNL = xml.getElementsByTagName("infAdic");
																																																																																									Element infAdic = null;
																																																																																									if(infAdicNL.getLength() > 0)
																																																																																									{
																																																																																										infAdic = (ElementImpl)infAdicNL.item(infAdicNL.getLength()-1);
																																																																																									}
																																																																																									Element procRef = (ElementImpl)xml.createElement("procRef");
																																																																																									Element nProc = (ElementImpl)xml.createElement("nProc");
																																																																																									nProc.setTextContent(tokens[1].trim());
																																																																																									procRef.appendChild(nProc);
																																																																																									Element indProc = (ElementImpl)xml.createElement("indProc");
																																																																																									indProc.setTextContent(tokens[2].trim());
																																																																																									procRef.appendChild(indProc);
																																																																																									infAdic.appendChild(procRef);
																																																																																									
																																																																																								}
																																																																																								else
																																																																																								{
																																																																																									if(tokens[0].trim().equalsIgnoreCase("8000"))
																																																																																									{
																																																																																										logger.info("Contem : 8000");
																																																																																										NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																																																																																										Element infNFe = null;
																																																																																										if(infNFeNL.getLength() > 0)
																																																																																										{
																																																																																											infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																																																																																										}
																																																																																										Element exporta = (ElementImpl)xml.createElement("exporta");
																																																																																										Element ufEmbarq = (ElementImpl)xml.createElement("UFEmbarq");
																																																																																										ufEmbarq.setTextContent(tokens[1].trim());
																																																																																										exporta.appendChild(ufEmbarq);
																																																																																										Element xLocEmbarq = (ElementImpl)xml.createElement("xLocEmbarq");
																																																																																										xLocEmbarq.setTextContent(tokens[2].trim());
																																																																																										exporta.appendChild(xLocEmbarq);
																																																																																										
																																																																																										infNFe.appendChild(exporta);
																																																																																									}
																																																																																									else
																																																																																									{
																																																																																										if(tokens[0].trim().equalsIgnoreCase("9000"))
																																																																																										{
																																																																																											logger.info("Contem : 9000");
																																																																																											NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																																																																																											Element infNFe = null;
																																																																																											if(infNFeNL.getLength() > 0)
																																																																																											{
																																																																																												infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																																																																																											}
																																																																																											Element compra = (ElementImpl)xml.createElement("compra");
																																																																																											Element xNEmp = (ElementImpl)xml.createElement("xNEmp");
																																																																																											xNEmp.setTextContent(tokens[1].trim());
																																																																																											compra.appendChild(xNEmp);
																																																																																											if(!tokens[2].trim().equalsIgnoreCase(""))
																																																																																											{
																																																																																												Element xPed = (ElementImpl)xml.createElement("xPed");
																																																																																												xPed.setTextContent(tokens[2].trim());
																																																																																												compra.appendChild(xPed);
																																																																																											}
																																																																																											Element xCont = (ElementImpl)xml.createElement("xCont");
																																																																																											xCont.setTextContent(tokens[3].trim());
																																																																																											compra.appendChild(xCont);
																																																																																											
																																																																																											infNFe.appendChild(compra);
																																																																																										}
																																																																																										else
																																																																																										{
																																																																																											if(tokens[0].trim().equalsIgnoreCase("9500"))
																																																																																											{
																																																																																												logger.info("Contem : 9500");
																																																																																												NodeList infNFeNL = xml.getElementsByTagName("infNFe");
																																																																																												Element infNFe = null;
																																																																																												if(infNFeNL.getLength() > 0)
																																																																																												{
																																																																																													infNFe = (ElementImpl)infNFeNL.item(infNFeNL.getLength()-1);
																																																																																												}
																																																																																												Element cana = (ElementImpl)xml.createElement("cana");
																																																																																												Element safra = (ElementImpl)xml.createElement("safra");
																																																																																												safra.setTextContent(tokens[1].trim());
																																																																																												cana.appendChild(safra);
																																																																																												Element ref = (ElementImpl)xml.createElement("ref");
																																																																																												ref.setTextContent(tokens[2].trim());
																																																																																												cana.appendChild(ref);
																																																																																												Element qTotMes = (ElementImpl)xml.createElement("qTotMes");
																																																																																												qTotMes.setTextContent(tokens[3].trim());
																																																																																												cana.appendChild(qTotMes);
																																																																																												Element qTotAnt = (ElementImpl)xml.createElement("qTotAnt");
																																																																																												qTotAnt.setTextContent(tokens[4].trim());
																																																																																												cana.appendChild(qTotAnt);
																																																																																												Element qTotGer = (ElementImpl)xml.createElement("qTotGer");
																																																																																												qTotGer.setTextContent(tokens[5].trim());
																																																																																												cana.appendChild(qTotGer);
																																																																																												Element vFor = (ElementImpl)xml.createElement("vFor");
																																																																																												vFor.setTextContent(tokens[6].trim());
																																																																																												cana.appendChild(vFor);
																																																																																												Element vTotDed = (ElementImpl)xml.createElement("vTotDed");
																																																																																												vTotDed.setTextContent(tokens[7].trim());
																																																																																												cana.appendChild(vTotDed);
																																																																																												Element vLiqFor = (ElementImpl)xml.createElement("vLiqFor");
																																																																																												vLiqFor.setTextContent(tokens[8].trim());
																																																																																												cana.appendChild(vLiqFor);
																																																																																												
																																																																																												infNFe.appendChild(cana);
																																																																																											}
																																																																																											else
																																																																																											{
																																																																																												if(tokens[0].trim().equalsIgnoreCase("9510"))
																																																																																												{
																																																																																													logger.info("Contem : 9510");
																																																																																													NodeList canaNL = xml.getElementsByTagName("cana");
																																																																																													Element cana = null;
																																																																																													if(canaNL.getLength() > 0)
																																																																																													{
																																																																																														cana = (ElementImpl)canaNL.item(canaNL.getLength()-1);
																																																																																													}
																																																																																													NodeList qTotMesNL = cana.getElementsByTagName("qTotMes");
																																																																																													Element qTotMes = null;
																																																																																													if(canaNL.getLength() > 0)
																																																																																													{
																																																																																														qTotMes = (ElementImpl)qTotMesNL.item(qTotMesNL.getLength()-1);
																																																																																													}
																																																																																													
																																																																																													Element forDia = (ElementImpl)xml.createElement("forDia");
																																																																																													Element qtde = (ElementImpl)xml.createElement("qtde");
																																																																																													qtde.setTextContent(tokens[1].trim());
																																																																																													forDia.appendChild(qtde);
																																																																																													
																																																																																													cana.insertBefore(forDia,qTotMes);
																																																																																													
																																																																																												}
																																																																																												else
																																																																																												{
																																																																																													if(tokens[0].trim().equalsIgnoreCase("9520"))
																																																																																													{
																																																																																														logger.info("Contem : 9520");
																																																																																														NodeList canaNL = xml.getElementsByTagName("cana");
																																																																																														Element cana = null;
																																																																																														if(canaNL.getLength() > 0)
																																																																																														{
																																																																																															cana = (ElementImpl)canaNL.item(canaNL.getLength()-1);
																																																																																														}
																																																																																														NodeList vForNL = cana.getElementsByTagName("vFor");
																																																																																														Element vFor = null;
																																																																																														if(canaNL.getLength() > 0)
																																																																																														{
																																																																																															vFor = (ElementImpl)vForNL.item(vForNL.getLength()-1);
																																																																																														}
																																																																																														
																																																																																														Element deduc = (ElementImpl)xml.createElement("deduc");
																																																																																														Element xDed = (ElementImpl)xml.createElement("xDed");
																																																																																														xDed.setTextContent(tokens[1].trim());
																																																																																														deduc.appendChild(xDed);
																																																																																														Element vDed = (ElementImpl)xml.createElement("vDed");
																																																																																														vDed.setTextContent(tokens[2].trim());
																																																																																														deduc.appendChild(vDed);
																																																																																														
																																																																																														cana.insertBefore(deduc,vFor);
																																																																																													}
																																																																																													else
																																																																																													{
																																																																																														if(tokens[0].trim().equalsIgnoreCase("10100"))
																																																																																														{
																																																																																															logger.info("Contem : 10100");
																																																																																															if(tokens[1].trim().equalsIgnoreCase("email"))
																																																																																															{
																																																																																																logger.info("contem : email");
																																																																																																if(params.containsKey(tokens[1].trim()))
																																																																																																{
																																																																																																	String emailTemp = "";
																																																																																																	emailTemp = params.get(tokens[1].trim());
																																																																																																	emailTemp += ";"+tokens[2].trim();
																																																																																																	params.put(tokens[1].trim(),emailTemp);
																																																																																																}
																																																																																																else
																																																																																																{
																																																																																																	params.put(tokens[1].trim(),tokens[2].trim());
																																																																																																}
																																																																																															}
																																																																																															else
																																																																																															{
																																																																																																
																																																																																															}
																																																																																														}
																																																																																														else
																																																																																														{
																																																																																															if(tokens[0].trim().equalsIgnoreCase("10200"))
																																																																																															{
																																																																																																if(tokens[1].trim().equalsIgnoreCase("PrintNumber"))
																																																																																																{
																																																																																																	params.put(tokens[1].trim(),tokens[2].trim());
																																																																																																}
																																																																																																else
																																																																																																{
																																																																																																	
																																																																																																}
																																																																																															}
																																																																																															else
																																																																																															{
																																																																																																if(tokens[0].trim().equalsIgnoreCase("3121"))
																																																																																																{
																																																																																																	NodeList icms60NL = xml.getElementsByTagName("ICMS60");
																																																																																																	Element icms60 = null;
																																																																																																	if(icms60NL.getLength() > 0)
																																																																																																	{
																																																																																																		icms60 = (ElementImpl)icms60NL.item(icms60NL.getLength()-1);
																																																																																																	}
																																																																																																	Element vBCSTRet = (ElementImpl)xml.createElement("vBCSTRet");
																																																																																																	vBCSTRet.setTextContent(tokens[1].trim());
																																																																																																	icms60.appendChild(vBCSTRet);
																																																																																																	Element vICMSSTRet = (ElementImpl)xml.createElement("vICMSSTRet");
																																																																																																	vICMSSTRet.setTextContent(tokens[2].trim());
																																																																																																	icms60.appendChild(vICMSSTRet);
																																																																																																}
																																																																																																else
																																																																																																{
																																																																																																	logger.info("Token inesperado "+tokens[0]);
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
