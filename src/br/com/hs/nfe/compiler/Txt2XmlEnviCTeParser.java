package br.com.hs.nfe.compiler;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Txt2XmlEnviCTeParser {
	public static final Logger logger = Logger.getLogger("Txt2XmlEnviCTeParser");
	public static synchronized void txt2XmlEnviCTeParser(String txtLine,Document xml)
	{
		String[] tokens = txtLine.split(";");
		if(tokens[0].trim().equalsIgnoreCase("00000"))
		{
			logger.info("Contem : 00000");
			if(tokens[1].trim().equalsIgnoreCase("1.03"))
			{
				logger.info("Contem : 1.03");
				if(tokens[2].trim().equalsIgnoreCase("ENVIO"))
				{
					logger.info("Contem : ENVIO");
					Element enviCTe = (org.apache.xerces.dom.ElementImpl)xml.createElement("enviCTe");
					enviCTe.setAttribute("versao","1.03");
					enviCTe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
					xml.appendChild(enviCTe);
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
			if(tokens[0].trim().equalsIgnoreCase("00001"))
			{
				logger.info("Contem : 00001");
				NodeList enviCTeNL = xml.getElementsByTagName("enviCTe");
				logger.info("idLote : "+tokens[1].trim());
				Element idLote = (ElementImpl)xml.createElement("idLote");
				idLote.setTextContent(tokens[1].trim());
				if(enviCTeNL.getLength() > 0)
				{
					Element enviCTe = (ElementImpl)enviCTeNL.item(enviCTeNL.getLength()-1);
					enviCTe.appendChild(idLote);
				}
			}
			else
			{
				if(tokens[0].trim().equalsIgnoreCase("10000"))
				{
					logger.info("Contem : 10000");
					
				}
				else
				{
					if(tokens[0].trim().equalsIgnoreCase("11000"))
					{
						logger.info("Contem : 11000");
						NodeList enviCTeNL = xml.getElementsByTagName("enviCTe");
						
						Element infCte = (ElementImpl)xml.createElement("infCte");
						infCte.setAttribute("versao", tokens[1].trim());
						infCte.setAttribute("Id", tokens[2].trim());
						
						if(enviCTeNL.getLength() > 0)
						{
							Element enviCTe = (ElementImpl)enviCTeNL.item(enviCTeNL.getLength()-1);
							enviCTe.appendChild(infCte);
						}
					}
					else
					{
						if(tokens[0].trim().equalsIgnoreCase("11100"))
						{
							logger.info("Contem : 11100");
							NodeList infCteNL = xml.getElementsByTagName("infCte");
							
							Element ide = (ElementImpl)xml.createElement("ide");
							
							Element cUF = (ElementImpl)xml.createElement("cUF");
							cUF.setTextContent(tokens[1].trim());
							ide.appendChild(cUF);
							Element cCT = (ElementImpl)xml.createElement("cCT");
							cCT.setTextContent(tokens[2].trim());
							ide.appendChild(cCT);
							Element CFOP = (ElementImpl)xml.createElement("CFOP");
							CFOP.setTextContent(tokens[3].trim());
							ide.appendChild(CFOP);
							Element natOp = (ElementImpl)xml.createElement("natOp");
							natOp.setTextContent(tokens[4].trim());
							ide.appendChild(natOp);
							Element forPag = (ElementImpl)xml.createElement("forPag");
							forPag.setTextContent(tokens[5].trim());
							ide.appendChild(forPag);
							Element mod = (ElementImpl)xml.createElement("mod");
							mod.setTextContent(tokens[6].trim());
							ide.appendChild(mod);
							Element serie = (ElementImpl)xml.createElement("serie");
							serie.setTextContent(tokens[7].trim());
							ide.appendChild(serie);
							Element nCT = (ElementImpl)xml.createElement("nCT");
							nCT.setTextContent(tokens[8].trim());
							ide.appendChild(nCT);
							Element dhEmi = (ElementImpl)xml.createElement("dhEmi");
							dhEmi.setTextContent(tokens[9].trim());
							ide.appendChild(dhEmi);
							Element tpImp = (ElementImpl)xml.createElement("tpImp");
							tpImp.setTextContent(tokens[10].trim());
							ide.appendChild(tpImp);
							Element tpEmis = (ElementImpl)xml.createElement("tpEmis");
							tpEmis.setTextContent(tokens[11].trim());
							ide.appendChild(tpEmis);
							Element cDV = (ElementImpl)xml.createElement("cDV");
							cDV.setTextContent(tokens[12].trim());
							ide.appendChild(cDV);
							Element tpAmb = (ElementImpl)xml.createElement("tpAmb");
							tpAmb.setTextContent(tokens[13].trim());
							ide.appendChild(tpAmb);
							Element tpCTe = (ElementImpl)xml.createElement("tpCTe");
							tpCTe.setTextContent(tokens[14].trim());
							ide.appendChild(tpCTe);
							Element procEmi = (ElementImpl)xml.createElement("procEmi");
							procEmi.setTextContent(tokens[15].trim());
							ide.appendChild(procEmi);
							Element verProc = (ElementImpl)xml.createElement("verProc");
							verProc.setTextContent(tokens[16].trim());
							ide.appendChild(verProc);
							Element refCTE = (ElementImpl)xml.createElement("refCTE");
							refCTE.setTextContent(tokens[17].trim());
							ide.appendChild(refCTE);
							Element cMunEmi = (ElementImpl)xml.createElement("cMunEmi");
							cMunEmi.setTextContent(tokens[18].trim());
							ide.appendChild(cMunEmi);
							Element xMunEmi = (ElementImpl)xml.createElement("xMunEmi");
							xMunEmi.setTextContent(tokens[19].trim());
							ide.appendChild(xMunEmi);
							Element UFEmi = (ElementImpl)xml.createElement("UFEmi");
							UFEmi.setTextContent(tokens[20].trim());
							ide.appendChild(UFEmi);
							Element modal = (ElementImpl)xml.createElement("modal");
							modal.setTextContent(tokens[21].trim());
							ide.appendChild(modal);
							Element tpServ = (ElementImpl)xml.createElement("tpServ");
							tpServ.setTextContent(tokens[22].trim());
							ide.appendChild(tpServ);
							Element cMunIni = (ElementImpl)xml.createElement("cMunIni");
							cMunIni.setTextContent(tokens[23].trim());
							ide.appendChild(cMunIni);
							Element xMunIni = (ElementImpl)xml.createElement("xMunIni");
							xMunIni.setTextContent(tokens[24].trim());
							ide.appendChild(xMunIni);
							Element UFIni = (ElementImpl)xml.createElement("UFIni");
							UFIni.setTextContent(tokens[25].trim());
							ide.appendChild(UFIni);
							Element cMunFim = (ElementImpl)xml.createElement("cMunFim");
							cMunFim.setTextContent(tokens[26].trim());
							ide.appendChild(cMunFim);
							Element xMunFim = (ElementImpl)xml.createElement("xMunFim");							
							xMunFim.setTextContent(tokens[27].trim());
							ide.appendChild(xMunFim);
							Element UFFim = (ElementImpl)xml.createElement("UFFim");
							UFFim.setTextContent(tokens[28].trim());
							ide.appendChild(UFFim);
							Element retira = (ElementImpl)xml.createElement("retira");
							retira.setTextContent(tokens[29].trim());
							ide.appendChild(retira);
							Element xDetRetira = (ElementImpl)xml.createElement("xDetRetira");
							xDetRetira.setTextContent(tokens[30].trim());
							ide.appendChild(xDetRetira);
							
							if(infCteNL.getLength() > 0)
							{
								Element infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
								infCte.appendChild(ide);
							}
						}
						else
						{
							if(tokens[0].trim().equalsIgnoreCase("11110"))
							{
								logger.info("Contem : 11110");
								NodeList ideNL = xml.getElementsByTagName("ide");
								
								Element toma03 = (ElementImpl)xml.createElement("toma03");
								Element toma = (ElementImpl)xml.createElement("toma");
								toma.setTextContent(tokens[1].trim());
								toma03.appendChild(toma);
								
								if(ideNL.getLength() > 0)
								{
									Element ide = (ElementImpl)ideNL.item(ideNL.getLength()-1);
									ide.appendChild(toma03);
								}
							}
							else
							{
								if(tokens[0].trim().equalsIgnoreCase("11120"))
								{
									logger.info("Contem : 11120");
									NodeList ideNL = xml.getElementsByTagName("ide");
									
									Element toma4 = (ElementImpl)xml.createElement("toma4");
									Element toma = (ElementImpl)xml.createElement("toma");
									toma.setTextContent(tokens[1].trim());
									toma4.appendChild(toma);
									
									if(tokens[2].trim().length() == 14)
									{
										Element CNPJ = (ElementImpl)xml.createElement("CNPJ");
										CNPJ.setTextContent(tokens[2].trim());
										toma4.appendChild(CNPJ);
									}
									else if(tokens[3].trim().length() == 11)
									{
										Element CPF = (ElementImpl)xml.createElement("CPF");
										CPF.setTextContent(tokens[3].trim());
										toma4.appendChild(CPF);
									}
									
									Element IE = (ElementImpl)xml.createElement("IE");
									IE.setTextContent(tokens[4].trim());
									toma4.appendChild(IE);
									Element xNome = (ElementImpl)xml.createElement("xNome");
									xNome.setTextContent(tokens[5].trim());
									toma4.appendChild(xNome);
									Element xFant = (ElementImpl)xml.createElement("xFant");
									xFant.setTextContent(tokens[6].trim());
									toma4.appendChild(xFant);
									Element fone = (ElementImpl)xml.createElement("fone");
									fone.setTextContent(tokens[7].trim());
									toma4.appendChild(fone);
									
									if(ideNL.getLength() > 0)
									{
										Element ide = (ElementImpl)ideNL.item(ideNL.getLength()-1);
										ide.appendChild(toma4);
									}
								}
								else
								{
									if(tokens[0].trim().equalsIgnoreCase("11121"))
									{
										logger.info("Contem : 11121");
										
										NodeList toma4NL = xml.getElementsByTagName("toma4");
										
										Element enderToma = (ElementImpl)xml.createElement("enderToma");
										Element xLgr = (ElementImpl)xml.createElement("xLgr");
										xLgr.setTextContent(tokens[1].trim());
										enderToma.appendChild(xLgr);
										Element nro = (ElementImpl)xml.createElement("nro");
										nro.setTextContent(tokens[2].trim());
										enderToma.appendChild(nro);
										Element xCpl = (ElementImpl)xml.createElement("xCpl");
										xCpl.setTextContent(tokens[3].trim());
										enderToma.appendChild(xCpl);
										Element xBairro = (ElementImpl)xml.createElement("xBairro");
										xBairro.setTextContent(tokens[4].trim());
										enderToma.appendChild(xBairro);
										Element cMun = (ElementImpl)xml.createElement("cMun");
										cMun.setTextContent(tokens[5].trim());
										enderToma.appendChild(cMun);
										Element xMun = (ElementImpl)xml.createElement("xMun");
										xMun.setTextContent(tokens[6].trim());
										enderToma.appendChild(xMun);
										Element CEP = (ElementImpl)xml.createElement("CEP");
										CEP.setTextContent(tokens[7].trim());
										enderToma.appendChild(CEP);
										Element UF = (ElementImpl)xml.createElement("UF");
										UF.setTextContent(tokens[8].trim());
										enderToma.appendChild(UF);
										Element cPais = (ElementImpl)xml.createElement("cPais");
										cPais.setTextContent(tokens[9].trim());
										enderToma.appendChild(cPais);
										Element xPais = (ElementImpl)xml.createElement("xPais");
										xPais.setTextContent(tokens[10].trim());
										enderToma.appendChild(xPais);
										
										if(toma4NL.getLength() > 0)
										{
											Element toma4 = (ElementImpl)toma4NL.item(toma4NL.getLength()-1);
											toma4.appendChild(toma4);
										}
									}
									else
									{
										if(tokens[0].trim().equalsIgnoreCase("11300"))
										{
											logger.info("Contem : 11300");
											NodeList infCteNL = xml.getElementsByTagName("infCte");
											
											Element compl = (ElementImpl)xml.createElement("compl");
											
											Element xCaracAd = (ElementImpl)xml.createElement("xCaracAd");
											xCaracAd.setTextContent(tokens[1].trim());
											compl.appendChild(xCaracAd);
											Element xCaracSer = (ElementImpl)xml.createElement("xCaracSer");
											xCaracSer.setTextContent(tokens[2].trim());
											compl.appendChild(xCaracSer);
											Element xEmi = (ElementImpl)xml.createElement("xEmi");
											xEmi.setTextContent(tokens[3].trim());
											compl.appendChild(xEmi);
											Element origCalc = (ElementImpl)xml.createElement("origCalc");
											origCalc.setTextContent(tokens[4].trim());
											compl.appendChild(origCalc);
											Element destCalc = (ElementImpl)xml.createElement("destCalc");
											destCalc.setTextContent(tokens[5].trim());
											compl.appendChild(destCalc);
											Element xObs = (ElementImpl)xml.createElement("xObs");
											xObs.setTextContent(tokens[6].trim());
											compl.appendChild(xObs);
											
											if(infCteNL.getLength() > 0)
											{
												Element infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
												infCte.appendChild(compl);
											}
										}
										else
										{
											if(tokens[0].trim().equalsIgnoreCase("11310"))
											{
												logger.info("Contem : 11310");
												NodeList complNL = xml.getElementsByTagName("compl");
												Element compl = null;
												if(complNL.getLength() > 0)
												{
													compl = (ElementImpl)complNL.item(complNL.getLength()-1);
												}
												NodeList origCalcNL = compl.getElementsByTagName("origCalc");
												Element origCalc = null;
												if(origCalcNL.getLength() > 0)
												{
													origCalc = (ElementImpl)origCalcNL.item(origCalcNL.getLength()-1);
												}
												Element fluxo = (ElementImpl)xml.createElement("fluxo");
												
												Element xOrig = (ElementImpl)xml.createElement("xOrig");
												xOrig.setTextContent(tokens[1].trim());
												fluxo.appendChild(xOrig);
												Element xDest = (ElementImpl)xml.createElement("xDest");
												xDest.setTextContent(tokens[2].trim());
												fluxo.appendChild(xDest);
												Element xRota = (ElementImpl)xml.createElement("xRota");
												xRota.setTextContent(tokens[3].trim());
												fluxo.appendChild(xRota);
												compl.insertBefore(fluxo, origCalc);
											}
											else
											{
												if(tokens[0].trim().equalsIgnoreCase("11311"))
												{
													logger.info("Contem : 11311");
													NodeList fluxoNL = xml.getElementsByTagName("fluxo");
													Element fluxo = null;
													if(fluxoNL.getLength() > 0)
													{
														fluxo = (ElementImpl)fluxoNL.item(fluxoNL.getLength()-1);
													}
													
													NodeList xDestNL = fluxo.getElementsByTagName("xDest");
													Element xDest = null;
													if(xDestNL.getLength() > 0)
													{
														xDest = (ElementImpl)xDestNL.item(xDestNL.getLength()-1);
													}
													Element pass = (ElementImpl)xml.createElement("pass");
													Element xPass = (ElementImpl)xml.createElement("xPass");
													xPass.setTextContent(tokens[1].trim());
													pass.appendChild(xPass);
													fluxo.insertBefore(pass, xDest);
												}
												else
												{
													if(tokens[0].trim().equalsIgnoreCase("11320"))
													{
														logger.info("Contem : 11320");
														NodeList complNL = xml.getElementsByTagName("compl");
														Element compl = null;
														if(complNL.getLength() > 0)
														{
															compl = (ElementImpl)complNL.item(complNL.getLength()-1);
														}
														Element Entrega = (ElementImpl)xml.createElement("Entrega");
														compl.appendChild(Entrega);
													}
													else
													{
														if(tokens[0].trim().equalsIgnoreCase("11321"))
														{
															logger.info("Contem : 11321");
															NodeList EntregaNL = xml.getElementsByTagName("Entrega");
															Element Entrega = null;
															if(EntregaNL.getLength() > 0)
															{
																Entrega = (ElementImpl)EntregaNL.item(EntregaNL.getLength()-1);
															}
															Element semData = xml.createElement("semData");
															Element tpPer = xml.createElement("tpPer");
															tpPer.setTextContent(tokens[1].trim());
															semData.appendChild(tpPer);
															Entrega.appendChild(semData);
														}
														else
														{
															if(tokens[0].trim().equalsIgnoreCase("11322"))
															{
																logger.info("Contem : 11322");
																NodeList EntregaNL = xml.getElementsByTagName("Entrega");
																Element Entrega = null;
																if(EntregaNL.getLength() > 0)
																{
																	Entrega = (ElementImpl)EntregaNL.item(EntregaNL.getLength()-1);
																}
																Element comData = xml.createElement("comData");
																Element tpPer = xml.createElement("tpPer");
																tpPer.setTextContent(tokens[1].trim());
																Element dProg = xml.createElement("dProg");
																dProg.setTextContent(tokens[2].trim());
																comData.appendChild(tpPer);
																comData.appendChild(dProg);
																Entrega.appendChild(comData);
															}
															else
															{
																if(tokens[0].trim().equalsIgnoreCase("11323"))
																{
																	logger.info("Contem : 11323");
																	NodeList EntregaNL = xml.getElementsByTagName("Entrega");
																	Element Entrega = null;
																	if(EntregaNL.getLength() > 0)
																	{
																		Entrega = (ElementImpl)EntregaNL.item(EntregaNL.getLength()-1);
																	}
																	Element noPeriodo = xml.createElement("noPeriodo");
																	Element tpPer = xml.createElement("tpPer");
																	tpPer.setTextContent(tokens[1].trim());
																	noPeriodo.appendChild(tpPer);
																	Element dIni = xml.createElement("dIni");
																	dIni.setTextContent(tokens[2].trim());
																	noPeriodo.appendChild(dIni);
																	Element dFim = xml.createElement("dFim");
																	dFim.setTextContent(tokens[3].trim());
																	noPeriodo.appendChild(dFim);
																	Entrega.appendChild(noPeriodo);
																}
																else
																{
																	if(tokens[0].trim().equalsIgnoreCase("11327"))
																	{
																		logger.info("Contem : 11327");
																		NodeList EntregaNL = xml.getElementsByTagName("Entrega");
																		Element Entrega = null;
																		if(EntregaNL.getLength() > 0)
																		{
																			Entrega = (ElementImpl)EntregaNL.item(EntregaNL.getLength()-1);
																		}
																		Element semHora = xml.createElement("semHora");
																		Element tpHor = xml.createElement("tpHor");
																		tpHor.setTextContent(tokens[1].trim());
																		semHora.appendChild(tpHor);
																		Entrega.appendChild(semHora);
																	}
																	else
																	{
																		if(tokens[0].trim().equalsIgnoreCase("11328"))
																		{
																			logger.info("Contem : 11328");
																			NodeList EntregaNL = xml.getElementsByTagName("Entrega");
																			Element Entrega = null;
																			if(EntregaNL.getLength() > 0)
																			{
																				Entrega = (ElementImpl)EntregaNL.item(EntregaNL.getLength()-1);
																			}
																			Element comHora = (ElementImpl)xml.createElement("comHora");
																			Element tpHor = (ElementImpl)xml.createElement("tpHor");
																			tpHor.setTextContent(tokens[1].trim());
																			comHora.appendChild(tpHor);
																			Element hProg = (ElementImpl)xml.createElement("hProg");
																			hProg.setTextContent(tokens[2].trim());
																			comHora.appendChild(hProg);
																			Entrega.appendChild(comHora);
																		}
																		else
																		{
																			if(tokens[0].trim().equalsIgnoreCase("11329"))
																			{
																				logger.info("Contem : 11329");
																				NodeList EntregaNL = xml.getElementsByTagName("Entrega");
																				Element Entrega = null;
																				if(EntregaNL.getLength() > 0)
																				{
																					Entrega = (ElementImpl)EntregaNL.item(EntregaNL.getLength()-1);
																				}
																				Element noInter = xml.createElement("noInter");
																				Element tphor = xml.createElement("tphor");
																				tphor.setTextContent(tokens[1].trim());
																				noInter.appendChild(tphor);
																				Element hIni = xml.createElement("hIni");
																				hIni.setTextContent(tokens[2].trim());
																				noInter.appendChild(hIni);
																				Element hFim = xml.createElement("hFim");
																				hFim.setTextContent(tokens[3].trim());
																				noInter.appendChild(hFim);
																				Entrega.appendChild(noInter);
																			}
																			else
																			{
																				if(tokens[0].trim().equalsIgnoreCase("11340"))
																				{
																					logger.info("Contem : 11340");
																					NodeList complNL = xml.getElementsByTagName("compl");
																					Element compl = null;
																					if(complNL.getLength() > 0)
																					{
																						compl = (ElementImpl)complNL.item(complNL.getLength()-1);
																					}
																					Element ObsCont = xml.createElement("ObsCont");
																					Element xTexto = xml.createElement("xTexto");
																					xTexto.setTextContent(tokens[1].trim());
																					ObsCont.appendChild(xTexto);
																					compl.appendChild(ObsCont);
																				}
																				else
																				{
																					if(tokens[0].trim().equalsIgnoreCase("11350"))
																					{
																						logger.info("Contem : 11350");
																						NodeList complNL = xml.getElementsByTagName("compl");
																						Element compl = null;
																						if(complNL.getLength() > 0)
																						{
																							compl = (ElementImpl)complNL.item(complNL.getLength()-1);
																						}
																						Element ObsFisco = xml.createElement("ObsFisco");
																						Element xTexto = xml.createElement("xTexto");
																						xTexto.setTextContent(tokens[1].trim());
																						ObsFisco.appendChild(xTexto);
																						compl.appendChild(ObsFisco);
																					}
																					else
																					{
																						if(tokens[0].trim().equalsIgnoreCase("12000"))
																						{
																							NodeList infCteNL = xml.getElementsByTagName("infCte");
																							Element infCte = null;
																							if(infCteNL.getLength() > 0)
																							{
																								infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																							}
																							Element emit = xml.createElement("emit");
																							Element CNPJ = xml.createElement("CNPJ");
																							CNPJ.setTextContent(tokens[1].trim());
																							emit.appendChild(CNPJ);
																							Element IE = xml.createElement("IE");
																							IE.setTextContent(tokens[2].trim());
																							emit.appendChild(IE);
																							Element xNome = xml.createElement("xNome");
																							xNome.setTextContent(tokens[3].trim());
																							emit.appendChild(xNome);
																							Element xFant = xml.createElement("xFant");
																							xFant.setTextContent(tokens[4].trim());
																							emit.appendChild(xFant);
																							infCte.appendChild(emit);
																						}
																						else
																						{
																							if(tokens[0].trim().equalsIgnoreCase("12010"))
																							{
																								logger.info("Contem : 12010");
																								NodeList emitNL = xml.getElementsByTagName("emit");
																								Element emit = null;
																								if(emitNL.getLength() > 0)
																								{
																									emit = (ElementImpl)emitNL.item(emitNL.getLength()-1);
																								}
																								Element enderEmit = xml.createElement("enderEmit");
																								Element xLgr = xml.createElement("xLgr");
																								xLgr.setTextContent(tokens[1].trim());
																								enderEmit.appendChild(xLgr);
																								Element nro = xml.createElement("nro");
																								nro.setTextContent(tokens[2].trim());
																								enderEmit.appendChild(nro);
																								Element xCpl = xml.createElement("xCpl");
																								xCpl.setTextContent(tokens[3].trim());
																								enderEmit.appendChild(xCpl);
																								Element xBairro = xml.createElement("xBairro");
																								xBairro.setTextContent(tokens[4].trim());
																								enderEmit.appendChild(xBairro);
																								Element cMun = xml.createElement("cMun");
																								cMun.setTextContent(tokens[5].trim());
																								enderEmit.appendChild(cMun);
																								Element xMun = xml.createElement("xMun");
																								xMun.setTextContent(tokens[6].trim());
																								enderEmit.appendChild(xMun);
																								Element CEP = xml.createElement("CEP");
																								CEP.setTextContent(tokens[7].trim());
																								enderEmit.appendChild(CEP);
																								Element UF = xml.createElement("UF");
																								UF.setTextContent(tokens[8].trim());
																								enderEmit.appendChild(UF);
																								Element cPais = xml.createElement("cPais");
																								cPais.setTextContent(tokens[9].trim());
																								enderEmit.appendChild(cPais);
																								Element xPais = xml.createElement("xPais");
																								xPais.setTextContent(tokens[10].trim());
																								enderEmit.appendChild(xPais);
																								Element fone = xml.createElement("fone");
																								fone.setTextContent(tokens[11].trim());
																								enderEmit.appendChild(fone);
																								emit.appendChild(enderEmit);
																							}
																							else
																							{
																								if(tokens[0].trim().equalsIgnoreCase("12100"))
																								{
																									logger.info("Contem : 12100");
																									NodeList infCteNL = xml.getElementsByTagName("infCte");
																									Element infCte = null;
																									if(infCteNL.getLength() > 0)
																									{
																										infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																									}
																									Element rem = xml.createElement("rem");
																									if(tokens[1].trim().length() == 14)
																									{
																										Element CNPJ = xml.createElement("CNPJ");
																										CNPJ.setTextContent(tokens[1].trim());
																										rem.appendChild(CNPJ);
																									}
																									else if(tokens[2].trim().length() == 11)
																									{
																										Element CPF = xml.createElement("CPF");
																										CPF.setTextContent(tokens[2].trim());
																										rem.appendChild(CPF);
																									}
																									Element IE = xml.createElement("IE");
																									IE.setTextContent(tokens[3].trim());
																									rem.appendChild(IE);
																									Element xNome = xml.createElement("xNome");
																									xNome.setTextContent(tokens[4].trim());
																									rem.appendChild(xNome);
																									Element xFant = xml.createElement("xFant");
																									xFant.setTextContent(tokens[5].trim());
																									rem.appendChild(xFant);
																									Element fone = xml.createElement("fone");
																									fone.setTextContent(tokens[6].trim());
																									rem.appendChild(fone);
																									infCte.appendChild(rem);
																								}
																								else
																								{
																									if(tokens[0].trim().equalsIgnoreCase("12110"))
																									{
																										logger.info("Contem : 12110");
																										NodeList remNL = xml.getElementsByTagName("rem");
																										Element rem = null;
																										if(remNL.getLength() > 0)
																										{
																											rem = (ElementImpl)remNL.item(remNL.getLength()-1);
																										}
																										Element enderReme = xml.createElement("enderReme");
																										Element xLgr = xml.createElement("xLgr");
																										xLgr.setTextContent(tokens[1].trim());
																										enderReme.appendChild(xLgr);
																										Element nro = xml.createElement("nro");
																										nro.setTextContent(tokens[2].trim());
																										enderReme.appendChild(nro);
																										Element xCpl = xml.createElement("xCpl");
																										xCpl.setTextContent(tokens[3].trim());
																										enderReme.appendChild(xCpl);
																										Element xBairro = xml.createElement("xBairro");
																										xBairro.setTextContent(tokens[4].trim());
																										enderReme.appendChild(xBairro);
																										Element cMun = xml.createElement("cMun");
																										cMun.setTextContent(tokens[5].trim());
																										enderReme.appendChild(cMun);
																										Element xMun = xml.createElement("xMun");
																										xMun.setTextContent(tokens[6].trim());
																										enderReme.appendChild(xMun);
																										Element CEP = xml.createElement("CEP");
																										CEP.setTextContent(tokens[7].trim());
																										enderReme.appendChild(CEP);
																										Element UF = xml.createElement("UF");
																										UF.setTextContent(tokens[8].trim());
																										enderReme.appendChild(UF);
																										Element cPais = xml.createElement("cPais");
																										cPais.setTextContent(tokens[9]);
																										enderReme.appendChild(cPais);
																										Element xPais = xml.createElement("xPais");
																										xPais.setTextContent(tokens[10].trim());
																										enderReme.appendChild(xPais);
																										rem.appendChild(enderReme);
																									}
																									else
																									{
																										if(tokens[0].trim().equalsIgnoreCase("12120"))
																										{
																											logger.info("Contem : 12120");
																											
																											NodeList remNL = xml.getElementsByTagName("rem");
																											Element rem = null;
																											if(remNL.getLength() > 0)
																											{
																												rem = (ElementImpl)remNL.item(remNL.getLength()-1);
																											}
																											
																											Element infNF = xml.createElement("infNF");
																											Element nRoma = xml.createElement("nRoma");
																											nRoma.setTextContent(tokens[1].trim());
																											infNF.appendChild(nRoma);
																											Element nPed = xml.createElement("nPed");
																											nPed.setTextContent(tokens[2].trim());
																											infNF.appendChild(nPed);
																											Element serie = xml.createElement("serie");
																											serie.setTextContent(tokens[3].trim());
																											infNF.appendChild(serie);
																											Element nDoc = xml.createElement("nDoc");
																											nDoc.setTextContent(tokens[4].trim());
																											infNF.appendChild(nDoc);
																											Element dEmi = xml.createElement("dEmi");
																											dEmi.setTextContent(tokens[5].trim());
																											infNF.appendChild(dEmi);
																											Element vBC = xml.createElement("vBC");
																											vBC.setTextContent(tokens[6].trim());
																											infNF.appendChild(vBC);
																											Element vICMS = xml.createElement("vICMS");
																											vICMS.setTextContent(tokens[7].trim());
																											infNF.appendChild(vICMS);
																											Element vBCST = xml.createElement("vBCST");
																											vBCST.setTextContent(tokens[8].trim());
																											infNF.appendChild(vBCST);
																											Element vST = xml.createElement("vST");
																											vST.setTextContent(tokens[9].trim());
																											infNF.appendChild(vST);
																											Element vProd = xml.createElement("vProd");
																											vProd.setTextContent(tokens[10].trim());
																											infNF.appendChild(vProd);
																											Element vNF = xml.createElement("vNF");
																											vNF.setTextContent(tokens[11].trim());
																											infNF.appendChild(vNF);
																											Element nCFOP = xml.createElement("nCFOP");
																											nCFOP.setTextContent(tokens[12].trim());
																											infNF.appendChild(nCFOP);
																											Element nPeso = xml.createElement("nPeso");
																											nPeso.setTextContent(tokens[13].trim());
																											infNF.appendChild(nPeso);
																											Element PIN = xml.createElement("PIN");
																											PIN.setTextContent(tokens[14].trim());
																											infNF.appendChild(PIN);
																											
																											rem.appendChild(infNF);
																										}
																										else
																										{
																											if(tokens[0].trim().equalsIgnoreCase("12121"))
																											{
																												logger.info("Contem : 12121");
																												
																												NodeList infNFNL = xml.getElementsByTagName("infNF");
																												Element infNF = null;
																												if(infNFNL.getLength() > 0)
																												{
																													infNF = (ElementImpl)infNFNL.item(infNFNL.getLength()-1);
																												}
																												Element locRet = xml.createElement("locRet");
																												if(tokens[1].trim().length() == 14)
																												{
																													Element CNPJ = xml.createElement("CNPJ");
																													CNPJ.setTextContent(tokens[1].trim());
																													locRet.appendChild(CNPJ);
																												}
																												else if(tokens[1].trim().length() == 11)
																												{
																													Element CPF = xml.createElement("CPF");
																													CPF.setTextContent(tokens[1].trim());
																													locRet.appendChild(CPF);
																												}
																												Element xNome = xml.createElement("xNome");
																												xNome.setTextContent(tokens[2].trim());
																												locRet.appendChild(xNome);
																												Element xLgr = xml.createElement("xLgr");
																												xLgr.setTextContent(tokens[3].trim());
																												locRet.appendChild(xLgr);
																												Element nro = xml.createElement("nro");
																												nro.setTextContent(tokens[4].trim());
																												locRet.appendChild(nro);
																												Element xCpl = xml.createElement("xCpl");
																												xCpl.setTextContent(tokens[5].trim());
																												locRet.appendChild(xCpl);
																												Element xBairro = xml.createElement("xBairro");
																												xBairro.setTextContent(tokens[6].trim());
																												locRet.appendChild(xBairro);
																												Element cMun = xml.createElement("cMun");
																												cMun.setTextContent(tokens[7].trim());
																												locRet.appendChild(cMun);
																												Element xMun = xml.createElement("xMun");
																												xMun.setTextContent(tokens[8].trim());
																												locRet.appendChild(xMun);
																												Element UF = xml.createElement("UF");
																												UF.setTextContent(tokens[9].trim());
																												locRet.appendChild(UF);
																												
																												infNF.appendChild(locRet);
																											}
																											else
																											{
																												if(tokens[0].trim().equalsIgnoreCase("12130"))
																												{
																													logger.info("Contem : 12130");
																													
																													NodeList remNL = xml.getElementsByTagName("rem");
																													Element rem = null;
																													if(remNL.getLength() > 0)
																													{
																														rem = (ElementImpl)remNL.item(remNL.getLength()-1);
																													}
																													Element infNFe = xml.createElement("infNFe");
																													Element chave = xml.createElement("chave");
																													chave.setTextContent(tokens[1].trim());
																													infNFe.appendChild(chave);
																													Element PIN = xml.createElement("PIN");
																													PIN.setTextContent(tokens[2].trim());
																													infNFe.appendChild(PIN);
																													
																													rem.appendChild(infNFe);
																												}
																												else
																												{
																													if(tokens[0].trim().equalsIgnoreCase("12140"))
																													{
																														logger.info("Contem : 12140");
																														NodeList remNL = xml.getElementsByTagName("rem");
																														Element rem = null;
																														if(remNL.getLength() > 0)
																														{
																															rem = (ElementImpl)remNL.item(remNL.getLength()-1);
																														}
																														Element infOutros = xml.createElement("infOutros");
																														Element tpDoc = xml.createElement("tpDoc");
																														tpDoc.setTextContent(tokens[1].trim());
																														infOutros.appendChild(tpDoc);
																														if(tokens[1].trim().equalsIgnoreCase("99"))
																														{
																															Element descOutros = xml.createElement("descOutros");
																															descOutros.setTextContent(tokens[2].trim());
																															infOutros.appendChild(descOutros);
																														}
																														Element nDoc = xml.createElement("nDoc");
																														nDoc.setTextContent(tokens[3].trim());
																														infOutros.appendChild(nDoc);
																														Element dEmi = xml.createElement("dEmi");
																														dEmi.setTextContent(tokens[4].trim());
																														infOutros.appendChild(dEmi);
																														Element vDocFisc = xml.createElement("vDocFisc");
																														vDocFisc.setTextContent(tokens[5].trim());
																														infOutros.appendChild(vDocFisc);
																														
																														rem.appendChild(rem);
																													}
																													else
																													{
																														if(tokens[0].trim().equalsIgnoreCase("12200"))
																														{
																															logger.info("Contem : 12200");
																															NodeList infCteNL = xml.getElementsByTagName("infCte");
																															Element infCte = null;
																															if(infCteNL.getLength() > 0)
																															{
																																infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																															}
																															
																															Element exped = xml.createElement("exped");
																															if(tokens[1].trim().length() == 14)
																															{
																																Element CNPJ = xml.createElement("CNPJ");
																																CNPJ.setTextContent(tokens[1].trim());
																																exped.appendChild(CNPJ);
																															}
																															else if(tokens[1].trim().length() == 11)
																															{
																																Element CPF = xml.createElement("CPF");
																																CPF.setTextContent(tokens[1].trim());
																																exped.appendChild(CPF);
																															}
																															Element IE = xml.createElement("IE");
																															IE.setTextContent(tokens[2].trim());
																															exped.appendChild(IE);
																															Element xNome = xml.createElement("xNome");
																															xNome.setTextContent(tokens[3].trim());
																															exped.appendChild(xNome);
																															Element fone = xml.createElement("fone");
																															fone.setTextContent(tokens[4].trim());
																															exped.appendChild(fone);
																															
																															infCte.appendChild(exped);
																														}
																														else
																														{
																															if(tokens[0].trim().equalsIgnoreCase("12210"))
																															{
																																logger.info("Contem : 12210");
																																NodeList expedNL = xml.getElementsByTagName("exped");
																																Element exped = null;
																																if(expedNL.getLength() > 0)
																																{
																																	exped = (ElementImpl)expedNL.item(expedNL.getLength()-1);
																																}
																																Element enderExped = xml.createElement("enderExped");
																																Element xLgr = xml.createElement("xLgr");
																																xLgr.setTextContent(tokens[1].trim());
																																enderExped.appendChild(xLgr);
																																Element nro = xml.createElement("nro");
																																nro.setTextContent(tokens[2].trim());
																																enderExped.appendChild(nro);
																																Element xCpl = xml.createElement("xCpl");
																																xCpl.setTextContent(tokens[3].trim());
																																enderExped.appendChild(xCpl);
																																Element xBairro = xml.createElement("xBairro");
																																xBairro.setTextContent(tokens[4].trim());
																																enderExped.appendChild(xBairro);
																																Element cMun = xml.createElement("cMun");
																																cMun.setTextContent(tokens[5].trim());
																																enderExped.appendChild(cMun);
																																Element xMun = xml.createElement("xMun");
																																xMun.setTextContent(tokens[6].trim());
																																enderExped.appendChild(xMun);
																																Element CEP = xml.createElement("CEP");
																																CEP.setTextContent(tokens[7].trim());
																																enderExped.appendChild(CEP);
																																Element UF = xml.createElement("UF");
																																UF.setTextContent(tokens[8].trim());
																																enderExped.appendChild(UF);
																																Element cPais = xml.createElement("cPais");
																																cPais.setTextContent(tokens[9].trim());
																																enderExped.appendChild(cPais);
																																Element xPais = xml.createElement("xPais");
																																xPais.setTextContent(tokens[10].trim());
																																enderExped.appendChild(xPais);
																																
																																exped.appendChild(enderExped);
																															}
																															else
																															{
																																if(tokens[0].trim().equalsIgnoreCase("12300"))
																																{
																																	logger.info("Contem : 12300");
																																	NodeList infCteNL = xml.getElementsByTagName("infCte");
																																	Element infCte = null;
																																	if(infCteNL.getLength() > 0)
																																	{
																																		infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																																	}
																																	Element receb = xml.createElement("receb");
																																	if(tokens[1].trim().length() == 14)
																																	{
																																		Element CNPJ = xml.createElement("CNPJ");
																																		CNPJ.setTextContent(tokens[1].trim());
																																		receb.appendChild(CNPJ);
																																	}
																																	else if(tokens[1].trim().length() == 11)
																																	{
																																		Element CPF = xml.createElement("CPF");
																																		CPF.setTextContent(tokens[1].trim());
																																		receb.appendChild(CPF);
																																	}
																																	Element IE = xml.createElement("IE");
																																	IE.setTextContent(tokens[2].trim());
																																	receb.appendChild(IE);
																																	Element xNome = xml.createElement("xNome");
																																	xNome.setTextContent(tokens[3].trim());
																																	receb.appendChild(xNome);
																																	Element fone = xml.createElement("fone");
																																	fone.setTextContent(tokens[4].trim());
																																	receb.appendChild(fone);
																																	
																																	infCte.appendChild(receb);
																																}
																																else
																																{
																																	if(tokens[0].trim().equalsIgnoreCase("12310"))
																																	{
																																		logger.info("Contem : 12310");
																																		NodeList recebNL = xml.getElementsByTagName("receb");
																																		Element receb = null;
																																		if(recebNL.getLength() > 0)
																																		{
																																			receb = (ElementImpl)recebNL.item(recebNL.getLength()-1);
																																		}
																																		Element enderReceb = xml.createElement("enderReceb");
																																		Element xLgr = xml.createElement("xLgr");
																																		xLgr.setTextContent(tokens[1].trim());
																																		enderReceb.appendChild(xLgr);
																																		Element nro = xml.createElement("nro");
																																		nro.setTextContent(tokens[2].trim());
																																		enderReceb.appendChild(nro);
																																		Element xCpl = xml.createElement("xCpl");
																																		xCpl.setTextContent(tokens[3].trim());
																																		enderReceb.appendChild(xCpl);
																																		Element xBairro = xml.createElement("xBairro");
																																		xBairro.setTextContent(tokens[4].trim());
																																		enderReceb.appendChild(xBairro);
																																		Element cMun = xml.createElement("cMun");
																																		cMun.setTextContent(tokens[5].trim());
																																		enderReceb.appendChild(cMun);
																																		Element xMun = xml.createElement("xMun");
																																		xMun.setTextContent(tokens[6].trim());
																																		enderReceb.appendChild(xMun);
																																		Element CEP = xml.createElement("CEP");
																																		CEP.setTextContent(tokens[7].trim());
																																		enderReceb.appendChild(CEP);
																																		Element UF = xml.createElement("UF");
																																		UF.setTextContent(tokens[8].trim());
																																		enderReceb.appendChild(UF);
																																		Element cPais = xml.createElement("cPais");
																																		cPais.setTextContent(tokens[9].trim());
																																		enderReceb.appendChild(cPais);
																																		Element xPais = xml.createElement("xPais");
																																		xPais.setTextContent(tokens[10].trim());
																																		enderReceb.appendChild(xPais);
																																		
																																		receb.appendChild(enderReceb);
																																	}
																																	else
																																	{
																																		if(tokens[0].trim().equalsIgnoreCase("12400"))
																																		{
																																			logger.info("Contem : 12400");
																																			NodeList infCteNL = xml.getElementsByTagName("infCte");
																																			Element infCte = null;
																																			if(infCteNL.getLength() > 0)
																																			{
																																				infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																																			}
																																			
																																			Element dest = xml.createElement("dest");
																																			if(tokens[1].trim().length() == 14)
																																			{
																																				Element CNPJ = xml.createElement("CNPJ");
																																				CNPJ.setTextContent(tokens[1].trim());
																																				dest.appendChild(CNPJ);
																																			}
																																			else if(tokens[1].trim().length() == 11)
																																			{
																																				Element CPF = xml.createElement("CPF");
																																				CPF.setTextContent(tokens[1].trim());
																																				dest.appendChild(CPF);
																																			}
																																			Element IE = xml.createElement("IE");
																																			IE.setTextContent(tokens[2].trim());
																																			dest.appendChild(IE);
																																			Element xNome = xml.createElement("xNome");
																																			xNome.setTextContent(tokens[3].trim());
																																			dest.appendChild(xNome);
																																			Element fone = xml.createElement("fone");
																																			fone.setTextContent(tokens[4].trim());
																																			dest.appendChild(fone);
																																			Element ISUF = xml.createElement("ISUF");
																																			ISUF.setTextContent(tokens[5].trim());
																																			dest.appendChild(ISUF);
																																			
																																			infCte.appendChild(dest);
																																		}
																																		else
																																		{
																																			if(tokens[0].trim().equalsIgnoreCase("12410"))
																																			{
																																				logger.info("Contem : 12410");
																																				NodeList destNL = xml.getElementsByTagName("dest");
																																				Element dest = null;
																																				if(destNL.getLength() > 0)
																																				{
																																					dest = (ElementImpl)destNL.item(destNL.getLength()-1);
																																				}
																																				Element enderDest = xml.createElement("enderDest");
																																				Element xLgr = xml.createElement("xLgr");
																																				xLgr.setTextContent(tokens[1].trim());
																																				enderDest.appendChild(xLgr);
																																				Element nro = xml.createElement("nro");
																																				nro.setTextContent(tokens[2].trim());
																																				enderDest.appendChild(nro);
																																				Element xCpl = xml.createElement("xCpl");
																																				xCpl.setTextContent(tokens[3].trim());
																																				enderDest.appendChild(xCpl);
																																				Element xBairro = xml.createElement("xBairro");
																																				xBairro.setTextContent(tokens[4].trim());
																																				enderDest.appendChild(xBairro);
																																				Element cMun = xml.createElement("cMun");
																																				cMun.setTextContent(tokens[5].trim());
																																				enderDest.appendChild(cMun);
																																				Element xMun = xml.createElement("xMun");
																																				xMun.setTextContent(tokens[6].trim());
																																				enderDest.appendChild(xMun);
																																				Element CEP = xml.createElement("CEP");
																																				CEP.setTextContent(tokens[7].trim());
																																				enderDest.appendChild(CEP);
																																				Element UF = xml.createElement("UF");
																																				UF.setTextContent(tokens[8].trim());
																																				enderDest.appendChild(UF);
																																				Element cPais = xml.createElement("cPais");
																																				cPais.setTextContent(tokens[9].trim());
																																				enderDest.appendChild(cPais);
																																				Element xPais = xml.createElement("xPais");
																																				xPais.setTextContent(tokens[10].trim());
																																				enderDest.appendChild(xPais);
																																				
																																				dest.appendChild(dest);
																																			}
																																			else
																																			{
																																				if(tokens[0].trim().equalsIgnoreCase("12420"))
																																				{
																																					logger.info("Contem : 12420");
																																					NodeList destNL = xml.getElementsByTagName("dest");
																																					Element dest = null;
																																					if(destNL.getLength() > 0)
																																					{
																																						dest = (ElementImpl)destNL.item(destNL.getLength()-1);
																																					}
																																					Element locEnt = xml.createElement("locEnt");
																																					if(tokens[1].trim().length() == 14)
																																					{
																																						Element CNPJ = xml.createElement("CNPJ");
																																						CNPJ.setTextContent(tokens[1].trim());
																																						locEnt.appendChild(CNPJ);
																																					}
																																					else if(tokens[1].trim().length() == 11)
																																					{
																																						Element CPF = xml.createElement("CPF");
																																						CPF.setTextContent(tokens[1].trim());
																																						locEnt.appendChild(CPF);
																																					}
																																					Element xNome = xml.createElement("xNome");
																																					xNome.setTextContent(tokens[2].trim());
																																					locEnt.appendChild(xNome);
																																					Element xLgr = xml.createElement("xLgr");
																																					xLgr.setTextContent(tokens[3].trim());
																																					locEnt.appendChild(xLgr);
																																					Element nro = xml.createElement("nro");
																																					nro.setTextContent(tokens[4].trim());
																																					locEnt.appendChild(nro);
																																					Element xCpl = xml.createElement("xCpl");
																																					xCpl.setTextContent(tokens[5].trim());
																																					locEnt.appendChild(xCpl);
																																					Element xBairro = xml.createElement("xBairro");
																																					xBairro.setTextContent(tokens[6].trim());
																																					locEnt.appendChild(xBairro);
																																					Element cMun = xml.createElement("cMun");
																																					cMun.setTextContent(tokens[7].trim());
																																					locEnt.appendChild(cMun);
																																					Element xMun = xml.createElement("xMun");
																																					xMun.setTextContent(tokens[8].trim());
																																					locEnt.appendChild(xMun);
																																					Element UF = xml.createElement("UF");
																																					UF.setTextContent(tokens[9]);
																																					locEnt.appendChild(UF);
																																					
																																					dest.appendChild(locEnt);
																																				}
																																				else
																																				{
																																					if(tokens[0].trim().equalsIgnoreCase("13000"))
																																					{
																																						logger.info("Contem : 13000");
																																						NodeList infCteNL = xml.getElementsByTagName("infCte");
																																						Element infCte = null;
																																						if(infCteNL.getLength() > 0)
																																						{
																																							infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																																						}
																																						Element vPrest = xml.createElement("vPrest");
																																						Element vTPrest = xml.createElement("vTPrest");
																																						vTPrest.setTextContent(tokens[1].trim());
																																						vPrest.appendChild(vTPrest);
																																						Element vRec = xml.createElement("vRec");
																																						vRec.setTextContent(tokens[2].trim());
																																						vPrest.appendChild(vRec);
																																						
																																						infCte.appendChild(vPrest);
																																					}
																																					else
																																					{
																																						if(tokens[0].trim().equalsIgnoreCase("13110"))
																																						{
																																							logger.info("Contem : 13110");
																																							NodeList vPrestNL = xml.getElementsByTagName("vPrest");
																																							Element vPrest = null;
																																							if(vPrestNL.getLength() > 0)
																																							{
																																								vPrest = (ElementImpl)vPrestNL.item(vPrestNL.getLength()-1);
																																							}
																																							Element Comp = xml.createElement("Comp");
																																							Element xNome = xml.createElement("xNome");
																																							xNome.setTextContent(tokens[1].trim());
																																							Comp.appendChild(xNome);
																																							Element vComp = xml.createElement("vComp");
																																							vComp.setTextContent(tokens[2].trim());
																																							Comp.appendChild(vComp);
																																							vPrest.appendChild(Comp);
																																						}
																																						else
																																						{
																																							if(tokens[0].trim().equalsIgnoreCase("14000"))
																																							{
																																								logger.info("Contem : 14000");
																																								NodeList infCteNL = xml.getElementsByTagName("infCte");
																																								Element infCte = null;
																																								if(infCteNL.getLength() > 0)
																																								{
																																									infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																																								}
																																								Element imp = xml.createElement("imp");
																																								Element ICMS = xml.createElement("ICMS");
																																								imp.appendChild(ICMS);
																																								infCte.appendChild(imp);
																																							}
																																							else
																																							{
																																								if(tokens[0].trim().equalsIgnoreCase("14100"))
																																								{
																																									logger.info("Contem : 14100");
																																									NodeList ICMSNL = xml.getElementsByTagName("ICMS");
																																									Element ICMS = null;
																																									if(ICMSNL.getLength() > 0)
																																									{
																																										ICMS = (ElementImpl)ICMSNL.item(ICMSNL.getLength()-1);
																																									}
																																									Element CST00 = xml.createElement("CST00");
																																									Element CST = xml.createElement("CST");
																																									CST.setTextContent(tokens[1].trim());
																																									CST00.appendChild(CST);
																																									Element vBC = xml.createElement("vBC");
																																									vBC.setTextContent(tokens[2].trim());
																																									CST00.appendChild(vBC);
																																									Element pICMS = xml.createElement("pICMS");
																																									pICMS.setTextContent(tokens[3].trim());
																																									CST00.appendChild(pICMS);
																																									Element vICMS = xml.createElement("vICMS");
																																									vICMS.setTextContent(tokens[4].trim());
																																									CST00.appendChild(vICMS);
																																									
																																									ICMS.appendChild(CST00);
																																								}
																																								else
																																								{
																																									if(tokens[0].trim().equalsIgnoreCase("14120"))
																																									{
																																										logger.info("Contem : 14120");
																																										NodeList ICMSNL = xml.getElementsByTagName("ICMS");
																																										Element ICMS = null;
																																										if(ICMSNL.getLength() > 0)
																																										{
																																											ICMS = (ElementImpl)ICMSNL.item(ICMSNL.getLength()-1);
																																										}
																																										Element CST20 = xml.createElement("CST20");
																																										Element CST = xml.createElement("CST");
																																										CST.setTextContent(tokens[1].trim());
																																										CST20.appendChild(CST);
																																										Element pRedBC = xml.createElement("pRedBC");
																																										pRedBC.setTextContent(tokens[2].trim());
																																										CST20.appendChild(pRedBC);
																																										Element vBC = xml.createElement("vBC");
																																										vBC.setTextContent(tokens[3].trim());
																																										CST20.appendChild(vBC);
																																										Element pICMS = xml.createElement("pICMS");
																																										pICMS.setTextContent(tokens[4].trim());
																																										CST20.appendChild(pICMS);
																																										Element vICMS = xml.createElement("vICMS");
																																										vICMS.setTextContent(tokens[5].trim());
																																										CST20.appendChild(vICMS);
																																										
																																										ICMS.appendChild(CST20);
																																									}
																																									else
																																									{
																																										if(tokens[0].trim().equalsIgnoreCase("14145"))
																																										{
																																											logger.info("Contem : 14145");
																																											NodeList ICMSNL = xml.getElementsByTagName("ICMS");
																																											Element ICMS = null;
																																											if(ICMSNL.getLength() > 0)
																																											{
																																												ICMS = (ElementImpl)ICMSNL.item(ICMSNL.getLength()-1);
																																											}
																																											Element CST45 = xml.createElement("CST45");
																																											Element CST = xml.createElement("CST");
																																											CST.setTextContent(tokens[1].trim());
																																											CST45.appendChild(CST);
																																											ICMS.appendChild(CST45);
																																										}
																																										else
																																										{
																																											if(tokens[0].trim().equalsIgnoreCase("14180"))
																																											{
																																												logger.info("Contem : 14180");
																																												NodeList ICMSNL = xml.getElementsByTagName("ICMS");
																																												Element ICMS = null;
																																												if(ICMSNL.getLength() > 0)
																																												{
																																													ICMS = (ElementImpl)ICMSNL.item(ICMSNL.getLength()-1);
																																												}
																																												Element CST80 = xml.createElement("CST80");
																																												Element CST = xml.createElement("CST");
																																												CST.setTextContent(tokens[1].trim());
																																												CST80.appendChild(CST);
																																												Element vBC = xml.createElement("vBC");
																																												vBC.setTextContent(tokens[2].trim());
																																												CST80.appendChild(vBC);
																																												Element pICMS = xml.createElement("pICMS");
																																												pICMS.setTextContent(tokens[3].trim());
																																												CST80.appendChild(pICMS);
																																												Element vICMS = xml.createElement("vICMS");
																																												vICMS.setTextContent(tokens[4].trim());
																																												CST80.appendChild(vICMS);
																																												Element vCred = xml.createElement("vCred");
																																												vCred.setTextContent(tokens[5].trim());
																																												CST80.appendChild(vCred);
																																												
																																												ICMS.appendChild(CST80);
																																											}
																																											else
																																											{
																																												if(tokens[0].trim().equalsIgnoreCase("14181"))
																																												{
																																													logger.info("Contem : 14181");
																																													NodeList ICMSNL = xml.getElementsByTagName("ICMS");
																																													Element ICMS = null;
																																													if(ICMSNL.getLength() > 0)
																																													{
																																														ICMS = (ElementImpl)ICMSNL.item(ICMSNL.getLength()-1);
																																													}
																																													Element CST81 = xml.createElement("CST81");
																																													Element CST = xml.createElement("CST");
																																													CST.setTextContent(tokens[1].trim());
																																													CST81.appendChild(CST);
																																													Element pRedBC = xml.createElement("pRedBC");
																																													pRedBC.setTextContent(tokens[2].trim());
																																													CST81.appendChild(pRedBC);
																																													Element vBC = xml.createElement("vBC");
																																													vBC.setTextContent(tokens[3].trim());
																																													CST81.appendChild(vBC);
																																													Element pICMS = xml.createElement("pICMS");
																																													pICMS.setTextContent(tokens[4].trim());
																																													CST81.appendChild(pICMS);
																																													Element vICMS = xml.createElement("vICMS");
																																													vICMS.setTextContent(tokens[5].trim());
																																													CST81.appendChild(vICMS);
																																													
																																													ICMS.appendChild(CST81);
																																												}
																																												else
																																												{
																																													if(tokens[0].trim().equalsIgnoreCase("14190"))
																																													{
																																														logger.info("Contem : 14190");
																																														NodeList ICMSNL = xml.getElementsByTagName("ICMS");
																																														Element ICMS = null;
																																														if(ICMSNL.getLength() > 0)
																																														{
																																															ICMS = (ElementImpl)ICMSNL.item(ICMSNL.getLength()-1);
																																														}
																																														Element CST90 = xml.createElement("CST90");
																																														Element CST = xml.createElement("CST");
																																														CST.setTextContent(tokens[1].trim());
																																														CST90.appendChild(CST);
																																														Element pRedBC = xml.createElement("pRedBC");
																																														pRedBC.setTextContent(tokens[2].trim());
																																														CST90.appendChild(pRedBC);
																																														Element vBC = xml.createElement("vBC");
																																														vBC.setTextContent(tokens[3].trim());
																																														CST90.appendChild(vBC);
																																														Element pICMS = xml.createElement("pICMS");
																																														pICMS.setTextContent(tokens[4].trim());
																																														CST90.appendChild(pICMS);
																																														Element vICMS = xml.createElement("vICMS");
																																														vICMS.setTextContent(tokens[5].trim());
																																														CST90.appendChild(vICMS);
																																														Element vCred = xml.createElement("vCred");
																																														vCred.setTextContent(tokens[6].trim());
																																														CST90.appendChild(vCred);
																																														
																																														ICMS.appendChild(CST90);
																																													}
																																													else
																																													{
																																														if(tokens[0].trim().equalsIgnoreCase("14300"))
																																														{
																																															logger.info("Contem : 14300");
																																															NodeList impNL = xml.getElementsByTagName("imp");
																																															Element imp = null;
																																															if(impNL.getLength() > 0)
																																															{
																																																imp = (ElementImpl)impNL.item(impNL.getLength()-1);
																																															}
																																															Element infAdFisco = xml.createElement("infAdFisco");
																																															infAdFisco.setTextContent(tokens[1].trim());
																																															imp.appendChild(infAdFisco);
																																														}
																																														else
																																														{
																																															if(tokens[0].trim().equalsIgnoreCase("15000"))
																																															{
																																																logger.info("Contem : 15000");
																																																NodeList infCteNL = xml.getElementsByTagName("infCte");
																																																Element infCte = null;
																																																if(infCteNL.getLength() > 0)
																																																{
																																																	infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																																																}
																																																Element infCTeNorm = xml.createElement("infCTeNorm");
																																																infCte.appendChild(infCTeNorm);
																																															}
																																															else
																																															{
																																																if(tokens[0].trim().equalsIgnoreCase("15100"))
																																																{
																																																	logger.info("Contem : 15100");
																																																	NodeList infCTeNormNL = xml.getElementsByTagName("infCTeNorm");
																																																	Element infCTeNorm = null;
																																																	if(infCTeNormNL.getLength() > 0)
																																																	{
																																																		infCTeNorm = (ElementImpl)infCTeNormNL.item(infCTeNormNL.getLength()-1);
																																																	}
																																																	Element infCarga = xml.createElement("infCarga");
																																																	Element vMerc = xml.createElement("vMerc");
																																																	vMerc.setTextContent(tokens[1].trim());
																																																	infCarga.appendChild(vMerc);
																																																	Element proPred = xml.createElement("proPred");
																																																	proPred.setTextContent(tokens[2].trim());
																																																	infCarga.appendChild(proPred);
																																																	Element xOutCat = xml.createElement("xOutCat");
																																																	xOutCat.setTextContent(tokens[3].trim());
																																																	infCarga.appendChild(xOutCat);
																																																	
																																																	infCTeNorm.appendChild(infCarga);
																																																}
																																																else
																																																{
																																																	if(tokens[0].trim().equalsIgnoreCase("15110"))
																																																	{
																																																		logger.info("Contem : 15110");
																																																		NodeList infCargaNL = xml.getElementsByTagName("infCarga");
																																																		Element infCarga = null;
																																																		if(infCargaNL.getLength() > 0)
																																																		{
																																																			infCarga = (ElementImpl)infCargaNL.item(infCargaNL.getLength()-1);
																																																		}
																																																		Element infQ = xml.createElement("infQ");
																																																		Element cUnid = xml.createElement("cUnid");
																																																		cUnid.setTextContent(tokens[1].trim());
																																																		infQ.appendChild(cUnid);
																																																		Element tpMed = xml.createElement("tpMed");
																																																		tpMed.setTextContent(tokens[2].trim());
																																																		infQ.appendChild(tpMed);
																																																		Element qCarga = xml.createElement("qCarga");
																																																		qCarga.setTextContent(tokens[3].trim());
																																																		infQ.appendChild(qCarga);
																																																		
																																																		infCarga.appendChild(infQ);
																																																	}
																																																	else
																																																	{
																																																		if(tokens[0].trim().equalsIgnoreCase("15200"))
																																																		{
																																																			logger.info("Contem : 15200");
																																																			NodeList infCTeNormNL = xml.getElementsByTagName("infCTeNorm");
																																																			Element infCTeNorm = null;
																																																			if(infCTeNormNL.getLength() > 0)
																																																			{
																																																				infCTeNorm = (ElementImpl)infCTeNormNL.item(infCTeNormNL.getLength()-1);
																																																			}
																																																			Element contQt = xml.createElement("contQt");
																																																			Element nCont = xml.createElement("nCont");
																																																			nCont.setTextContent(tokens[1].trim());
																																																			contQt.appendChild(nCont);
																																																			Element dPrev = xml.createElement("dPrev");
																																																			dPrev.setTextContent(tokens[2].trim());
																																																			contQt.appendChild(dPrev);
																																																			
																																																			infCTeNorm.appendChild(contQt);
																																																		}
																																																		else
																																																		{
																																																			if(tokens[0].trim().equalsIgnoreCase("15210"))
																																																			{
																																																				logger.info("Contem : 15210");
																																																				NodeList contQtNL = xml.getElementsByTagName("contQt");
																																																				Element contQt = null;
																																																				if(contQtNL.getLength() > 0)
																																																				{
																																																					contQt = (ElementImpl)contQtNL.item(contQtNL.getLength()-1);
																																																				}
																																																				
																																																				NodeList dPrevNL = contQt.getElementsByTagName("dPrev");
																																																				Element dPrev = null;
																																																				if(dPrevNL.getLength() > 0)
																																																				{
																																																					dPrev = (ElementImpl)dPrevNL.item(dPrevNL.getLength()-1);
																																																				}
																																																				Element lacContQt = xml.createElement("lacContQt");
																																																				Element nLacre = xml.createElement("nLacre");
																																																				nLacre.setTextContent(tokens[1].trim());
																																																				lacContQt.appendChild(nLacre);
																																																				contQt.insertBefore(lacContQt,dPrev);
																																																			}
																																																			else
																																																			{
																																																				if(tokens[0].trim().equalsIgnoreCase("15300"))
																																																				{
																																																					logger.info("Contem : 15300");
																																																					NodeList infCTeNormNL = xml.getElementsByTagName("infCTeNorm");
																																																					Element infCTeNorm = null;
																																																					if(infCTeNormNL.getLength() > 0)
																																																					{
																																																						infCTeNorm = (ElementImpl)infCTeNormNL.item(infCTeNormNL.getLength()-1);
																																																					}
																																																					Element emiDocAnt = xml.createElement("emiDocAnt");
																																																					infCTeNorm.appendChild(emiDocAnt);
																																																				}
																																																				else
																																																				{
																																																					if(tokens[0].trim().equalsIgnoreCase("15310"))
																																																					{
																																																						logger.info("Contem : 15310");
																																																						NodeList emiDocAntNL = xml.getElementsByTagName("emiDocAnt");
																																																						Element emiDocAnt = null;
																																																						if(emiDocAntNL.getLength() > 0)
																																																						{
																																																							emiDocAnt = (ElementImpl)emiDocAntNL.item(emiDocAntNL.getLength()-1);
																																																						}
																																																						if(tokens[1].trim().length() == 14)
																																																						{
																																																							Element CNPJ = xml.createElement("CNPJ");
																																																							CNPJ.setTextContent(tokens[1].trim());
																																																							emiDocAnt.appendChild(CNPJ);
																																																						}
																																																						else if(tokens[1].trim().length() == 11)
																																																						{
																																																							Element CPF = xml.createElement("CPF");
																																																							CPF.setTextContent(tokens[1].trim());
																																																							emiDocAnt.appendChild(CPF);
																																																						}
																																																						Element IE = xml.createElement("IE");
																																																						IE.setTextContent(tokens[2].trim());
																																																						emiDocAnt.appendChild(IE);
																																																						Element UF = xml.createElement("UF");
																																																						UF.setTextContent(tokens[3].trim());
																																																						emiDocAnt.appendChild(UF);
																																																						Element xNome = xml.createElement("xNome");
																																																						xNome.setTextContent(tokens[4].trim());
																																																						emiDocAnt.appendChild(xNome);
																																																						
																																																					}
																																																					else
																																																					{
																																																						if(tokens[0].trim().equalsIgnoreCase("15320"))
																																																						{
																																																							logger.info("Contem : 15320");
																																																							NodeList emiDocAntNL = xml.getElementsByTagName("emiDocAnt");
																																																							Element emiDocAnt = null;
																																																							if(emiDocAntNL.getLength() > 0)
																																																							{
																																																								emiDocAnt = (ElementImpl)emiDocAntNL.item(emiDocAntNL.getLength()-1);
																																																							}
																																																							Element idDocAnt = xml.createElement("idDocAnt");
																																																							emiDocAnt.appendChild(idDocAnt);
																																																						}
																																																						else
																																																						{
																																																							if(tokens[0].trim().equalsIgnoreCase("15321"))
																																																							{
																																																								logger.info("Contem : 15321");
																																																								NodeList idDocAntNL = xml.getElementsByTagName("idDocAnt");
																																																								Element idDocAnt = null;
																																																								if(idDocAntNL.getLength() > 0)
																																																								{
																																																									idDocAnt = (ElementImpl)idDocAntNL.item(idDocAntNL.getLength()-1);
																																																								}
																																																								Element idDocAntPap = xml.createElement("idDocAntPap");
																																																								Element tpDoc = xml.createElement("tpDoc");
																																																								tpDoc.setTextContent(tokens[1].trim());
																																																								idDocAntPap.appendChild(tpDoc);
																																																								Element serie = xml.createElement("serie");
																																																								serie.setTextContent(tokens[2].trim());
																																																								idDocAntPap.appendChild(serie);
																																																								Element subser = xml.createElement("subser");
																																																								subser.setTextContent(tokens[3].trim());
																																																								idDocAntPap.appendChild(subser);
																																																								Element nDoc = xml.createElement("nDoc");
																																																								nDoc.setTextContent(tokens[4].trim());
																																																								idDocAntPap.appendChild(nDoc);
																																																								Element dEmi = xml.createElement("dEmi");
																																																								dEmi.setTextContent(tokens[5].trim());
																																																								idDocAntPap.appendChild(dEmi);
																																																								
																																																								idDocAnt.appendChild(idDocAntPap);
																																																							}
																																																							else
																																																							{
																																																								if(tokens[0].trim().equalsIgnoreCase("15322"))
																																																								{
																																																									NodeList idDocAntNL = xml.getElementsByTagName("idDocAnt");
																																																									Element idDocAnt = null;
																																																									if(idDocAntNL.getLength() > 0)
																																																									{
																																																										idDocAnt = (ElementImpl)idDocAntNL.item(idDocAntNL.getLength()-1);
																																																									}
																																																									Element idDocAntEle = xml.createElement("idDocAntEle");
																																																									Element chave = xml.createElement("chave");
																																																									chave.setTextContent(tokens[1].trim());
																																																									idDocAntEle.appendChild(chave);
																																																									idDocAnt.appendChild(idDocAntEle);
																																																								}
																																																								else
																																																								{
																																																									if(tokens[0].trim().equalsIgnoreCase("15400"))
																																																									{
																																																										logger.info("Contem : 15400");
																																																										NodeList infCTeNormNL = xml.getElementsByTagName("infCTeNorm");
																																																										Element infCTeNorm = null;
																																																										if(infCTeNormNL.getLength() > 0)
																																																										{
																																																											infCTeNorm = (ElementImpl)infCTeNormNL.item(infCTeNormNL.getLength()-1);
																																																										}
																																																										Element seg = xml.createElement("seg");
																																																										Element respSeg = xml.createElement("respSeg");
																																																										respSeg.setTextContent(tokens[1].trim());
																																																										seg.appendChild(respSeg);
																																																										Element xSeg = xml.createElement("xSeg");
																																																										xSeg.setTextContent(tokens[2].trim());
																																																										seg.appendChild(xSeg);
																																																										Element nApol = xml.createElement("nApol");
																																																										nApol.setTextContent(tokens[3].trim());
																																																										seg.appendChild(nApol);
																																																										Element nAver = xml.createElement("nAver");
																																																										nAver.setTextContent(tokens[4].trim());
																																																										seg.appendChild(nAver);
																																																										Element vMerc = xml.createElement("vMerc");
																																																										vMerc.setTextContent(tokens[5].trim());
																																																										seg.appendChild(vMerc);
																																																										
																																																										infCTeNorm.appendChild(seg);
																																																									}
																																																									else
																																																									{
																																																										if(tokens[0].trim().equalsIgnoreCase("16000"))
																																																										{
																																																											logger.info("Contem : 16000");
																																																											NodeList infCTeNormNL = xml.getElementsByTagName("infCTeNorm");
																																																											Element infCTeNorm = null;
																																																											if(infCTeNormNL.getLength() > 0)
																																																											{
																																																												infCTeNorm = (ElementImpl)infCTeNormNL.item(infCTeNormNL.getLength()-1);
																																																											}
																																																											Element rodo = xml.createElement("rodo");
																																																											Element RNTRC = xml.createElement("RNTRC");
																																																											RNTRC.setTextContent(tokens[1].trim());
																																																											rodo.appendChild(RNTRC);
																																																											Element dPrev = xml.createElement("dPrev");
																																																											dPrev.setTextContent(tokens[2].trim());
																																																											rodo.appendChild(dPrev);
																																																											infCTeNorm.appendChild(rodo);
																																																										}
																																																										else
																																																										{
																																																											if(tokens[0].trim().equalsIgnoreCase("16100"))
																																																											{
																																																												logger.info("Contem : 16100");
																																																												NodeList rodoNL = xml.getElementsByTagName("rodo");
																																																												Element rodo = null;
																																																												if(rodoNL.getLength() > 0)
																																																												{
																																																													rodo = (ElementImpl)rodoNL.item(rodoNL.getLength()-1);
																																																												}
																																																												Element CTRB = xml.createElement("CTRB");
																																																												Element serie = xml.createElement("serie");
																																																												serie.setTextContent(tokens[1].trim());
																																																												CTRB.appendChild(serie);
																																																												Element nCTRB = xml.createElement("nCTRB");
																																																												nCTRB.setTextContent(tokens[2].trim());
																																																												CTRB.appendChild(nCTRB);
																																																												rodo.appendChild(CTRB);
																																																											}
																																																											else
																																																											{
																																																												if(tokens[0].trim().equalsIgnoreCase("16200"))
																																																												{
																																																													logger.info("Contem : 16200");
																																																													NodeList rodoNL = xml.getElementsByTagName("rodo");
																																																													Element rodo = null;
																																																													if(rodoNL.getLength() > 0)
																																																													{
																																																														rodo = (ElementImpl)rodoNL.item(rodoNL.getLength()-1);
																																																													}
																																																													Element occ = xml.createElement("occ");
																																																													Element serie = xml.createElement("serie");
																																																													serie.setTextContent(tokens[1].trim());
																																																													occ.appendChild(serie);
																																																													Element nOcc = xml.createElement("nOcc");
																																																													nOcc.setTextContent(tokens[2].trim());
																																																													occ.appendChild(nOcc);
																																																													Element dEmi = xml.createElement("dEmi");
																																																													dEmi.setTextContent(tokens[3].trim());
																																																													occ.appendChild(dEmi);
																																																													
																																																													rodo.appendChild(occ);
																																																												}
																																																												else
																																																												{
																																																													if(tokens[0].trim().equalsIgnoreCase("16210"))
																																																													{
																																																														logger.info("Contem : 16210");
																																																														NodeList occNL = xml.getElementsByTagName("occ");
																																																														Element occ = null;
																																																														if(occNL.getLength() > 0)
																																																														{
																																																															occ = (ElementImpl)occNL.item(occNL.getLength()-1);
																																																														}
																																																														Element emiOcc = xml.createElement("emiOcc");
																																																														Element CNPJ = xml.createElement("CNPJ");
																																																														CNPJ.setTextContent(tokens[1].trim());
																																																														emiOcc.appendChild(CNPJ);
																																																														Element cInt = xml.createElement("cInt");
																																																														cInt.setTextContent(tokens[2].trim());
																																																														emiOcc.appendChild(cInt);
																																																														Element IE = xml.createElement("IE");
																																																														IE.setTextContent(tokens[3].trim());
																																																														emiOcc.appendChild(IE);
																																																														Element UF = xml.createElement("UF");
																																																														UF.setTextContent(tokens[4].trim());
																																																														emiOcc.appendChild(UF);
																																																														Element fone = xml.createElement("fone");
																																																														fone.setTextContent(tokens[5].trim());
																																																														emiOcc.appendChild(fone);
																																																														
																																																														occ.appendChild(emiOcc);
																																																													}
																																																													else
																																																													{
																																																														if(tokens[0].trim().equalsIgnoreCase("16300"))
																																																														{
																																																															logger.info("Contem : 16300");
																																																															NodeList rodoNL = xml.getElementsByTagName("rodo");
																																																															Element rodo = null;
																																																															if(rodoNL.getLength() > 0)
																																																															{
																																																																rodo = (ElementImpl)rodoNL.item(rodoNL.getLength()-1);
																																																															}
																																																															Element valePed = xml.createElement("valePed");
																																																															Element nroRE = xml.createElement("nroRE");
																																																															nroRE.setTextContent(tokens[1].trim());
																																																															valePed.appendChild(nroRE);
																																																															Element vTValePed = xml.createElement(tokens[2].trim());
																																																															vTValePed.setTextContent(tokens[2].trim());
																																																															valePed.appendChild(vTValePed);
																																																															Element respPg = xml.createElement("respPg");
																																																															respPg.setTextContent(tokens[3].trim());
																																																															valePed.appendChild(respPg);
																																																															
																																																															rodo.appendChild(valePed);
																																																														}
																																																														else
																																																														{
																																																															if(tokens[0].trim().equalsIgnoreCase("16310"))
																																																															{
																																																																logger.info("Contem : 16310");
																																																																NodeList valePedNL = xml.getElementsByTagName("valePed");
																																																																Element valePed = null;
																																																																if(valePedNL.getLength() > 0)
																																																																{
																																																																	valePed = (ElementImpl)valePedNL.item(valePedNL.getLength()-1);
																																																																}
																																																																Element disp = xml.createElement("disp");
																																																																Element tpDisp = xml.createElement("tpDisp");
																																																																tpDisp.setTextContent(tokens[1].trim());
																																																																disp.appendChild(tpDisp);
																																																																Element xEmp = xml.createElement("xEmp");
																																																																xEmp.setTextContent(tokens[2].trim());
																																																																disp.appendChild(xEmp);
																																																																Element dVig = xml.createElement("dVig");
																																																																dVig.setTextContent(tokens[3].trim());
																																																																disp.appendChild(dVig);
																																																																Element nDisp = xml.createElement("nDisp");
																																																																nDisp.setTextContent(tokens[4].trim());
																																																																disp.appendChild(nDisp);
																																																																Element nCompC = xml.createElement("nCompC");
																																																																nCompC.setTextContent(tokens[5].trim());
																																																																disp.appendChild(nCompC);
																																																																
																																																																valePed.appendChild(disp);
																																																															}
																																																															else
																																																															{
																																																																if(tokens[0].trim().equalsIgnoreCase("16400"))
																																																																{
																																																																	logger.info("Contem : 16400");
																																																																	NodeList rodoNL = xml.getElementsByTagName("rodo");
																																																																	Element rodo = null;
																																																																	if(rodoNL.getLength() > 0)
																																																																	{
																																																																		rodo = (ElementImpl)rodoNL.item(rodoNL.getLength()-1);
																																																																	}
																																																																	Element veic = xml.createElement("veic");
																																																																	Element cInt = xml.createElement("cInt");
																																																																	cInt.setTextContent(tokens[1].trim());
																																																																	veic.appendChild(cInt);
																																																																	Element RENAVAM = xml.createElement("RENAVAM");
																																																																	RENAVAM.setTextContent(tokens[2].trim());
																																																																	veic.appendChild(RENAVAM);
																																																																	Element placa = xml.createElement("placa");
																																																																	placa.setTextContent(tokens[3].trim());
																																																																	veic.appendChild(placa);
																																																																	Element tara = xml.createElement("tara");
																																																																	tara.setTextContent(tokens[4].trim());
																																																																	veic.appendChild(tara);
																																																																	Element capKG = xml.createElement("capKG");
																																																																	capKG.setTextContent(tokens[5].trim());
																																																																	veic.appendChild(capKG);
																																																																	Element capM3 = xml.createElement("capM3");
																																																																	capM3.setTextContent(tokens[6].trim());
																																																																	veic.appendChild(capM3);
																																																																	Element tpProp = xml.createElement("tpProp");
																																																																	tpProp.setTextContent(tokens[7].trim());
																																																																	veic.appendChild(tpProp);
																																																																	Element tpVeic = xml.createElement("tpVeic");
																																																																	tpVeic.setTextContent(tokens[8].trim());
																																																																	veic.appendChild(tpVeic);
																																																																	Element tpRod = xml.createElement("tpRod");
																																																																	tpRod.setTextContent(tokens[9].trim());
																																																																	veic.appendChild(tpRod);
																																																																	Element tpCar = xml.createElement("tpCar");
																																																																	tpCar.setTextContent(tokens[10].trim());
																																																																	veic.appendChild(tpCar);
																																																																	Element UF = xml.createElement("UF");
																																																																	UF.setTextContent(tokens[11].trim());
																																																																	veic.appendChild(UF);
																																																																	
																																																																	rodo.appendChild(veic);
																																																																}
																																																																else
																																																																{
																																																																	if(tokens[0].trim().equalsIgnoreCase("16410"))
																																																																	{
																																																																		logger.info("Contem : 16410");
																																																																		NodeList veicNL = xml.getElementsByTagName("veic");
																																																																		Element veic = null;
																																																																		if(veicNL.getLength() > 0)
																																																																		{
																																																																			veic = (ElementImpl)veicNL.item(veicNL.getLength()-1);
																																																																		}
																																																																		Element prop = xml.createElement("prop");
																																																																		if(tokens[1].trim().length() == 14)
																																																																		{
																																																																			Element CNPJ = xml.createElement("CNPJ");
																																																																			CNPJ.setTextContent(tokens[1].trim());
																																																																			prop.appendChild(CNPJ);
																																																																		}
																																																																		else if(tokens[1].trim().length() == 11)
																																																																		{
																																																																			Element CPF = xml.createElement("CPF");
																																																																			CPF.setTextContent(tokens[1].trim());
																																																																			prop.appendChild(CPF);
																																																																		}
																																																																		Element RNTRC = xml.createElement("RNTRC");
																																																																		RNTRC.setTextContent(tokens[2].trim());
																																																																		prop.appendChild(RNTRC);
																																																																		Element xNome = xml.createElement("xNome");
																																																																		xNome.setTextContent(tokens[3].trim());
																																																																		prop.appendChild(xNome);
																																																																		Element IE = xml.createElement("IE");
																																																																		IE.setTextContent(tokens[4].trim());
																																																																		prop.appendChild(IE);
																																																																		Element UF = xml.createElement("UF");
																																																																		UF.setTextContent(tokens[5].trim());
																																																																		prop.appendChild(UF);
																																																																		Element tpProp = xml.createElement("tpProp");
																																																																		tpProp.setTextContent(tokens[6].trim());
																																																																		prop.appendChild(tpProp);
																																																																		
																																																																		veic.appendChild(prop);
																																																																	}
																																																																	else
																																																																	{
																																																																		if(tokens[0].trim().equalsIgnoreCase("16500"))
																																																																		{
																																																																			logger.info("Contem : 16500");
																																																																			NodeList rodoNL = xml.getElementsByTagName("rodo");
																																																																			Element rodo = null;
																																																																			if(rodoNL.getLength() > 0)
																																																																			{
																																																																				rodo = (ElementImpl)rodoNL.item(rodoNL.getLength()-1);
																																																																			}
																																																																			Element lacRodo = xml.createElement("lacRodo");
																																																																			Element nLacre = xml.createElement("nLacre");
																																																																			nLacre.setTextContent(tokens[1].trim());
																																																																			lacRodo.appendChild(nLacre);
																																																																			rodo.appendChild(lacRodo);
																																																																		}
																																																																		else
																																																																		{
																																																																			if(tokens[0].trim().equalsIgnoreCase("16600"))
																																																																			{
																																																																				logger.info("Contem : 16600");
																																																																				NodeList rodoNL = xml.getElementsByTagName("rodo");
																																																																				Element rodo = null;
																																																																				if(rodoNL.getLength() > 0)
																																																																				{
																																																																					rodo = (ElementImpl)rodoNL.item(rodoNL.getLength()-1);
																																																																				}
																																																																				Element moto = xml.createElement("moto");
																																																																				Element xNome = xml.createElement("xNome");
																																																																				xNome.setTextContent(tokens[1].trim());
																																																																				moto.appendChild(xNome);
																																																																				Element CPF = xml.createElement("CPF");
																																																																				CPF.setTextContent(tokens[2].trim());
																																																																				moto.appendChild(CPF);
																																																																				
																																																																				rodo.appendChild(moto);
																																																																			}
																																																																			else
																																																																			{
																																																																				if(tokens[0].trim().equalsIgnoreCase("21000"))
																																																																				{
																																																																					logger.info("Contem : 21000");
																																																																					NodeList infCTeNormNL = xml.getElementsByTagName("infCTeNorm");
																																																																					Element infCTeNorm = null;
																																																																					if(infCTeNormNL.getLength() > 0)
																																																																					{
																																																																						infCTeNorm = (ElementImpl)infCTeNormNL.item(infCTeNormNL.getLength()-1);
																																																																					}
																																																																					Element peri = xml.createElement("peri");
																																																																					Element nONU = xml.createElement("nONU");
																																																																					nONU.setTextContent(tokens[1].trim());
																																																																					peri.appendChild(nONU);
																																																																					Element xNomeAE = xml.createElement("xNomeAE");
																																																																					xNomeAE.setTextContent(tokens[2].trim());
																																																																					peri.appendChild(xNomeAE);
																																																																					Element xClaRisco = xml.createElement("xClaRisco");
																																																																					xClaRisco.setTextContent(tokens[3].trim());
																																																																					peri.appendChild(xClaRisco);
																																																																					Element grEmb = xml.createElement("grEmb");
																																																																					grEmb.setTextContent(tokens[4].trim());
																																																																					peri.appendChild(grEmb);
																																																																					Element qTotProd = xml.createElement("qTotProd");
																																																																					qTotProd.setTextContent(tokens[5].trim());
																																																																					peri.appendChild(qTotProd);
																																																																					Element qVolTipo = xml.createElement("qVolTipo");
																																																																					qVolTipo.setTextContent(tokens[6].trim());
																																																																					peri.appendChild(qVolTipo);
																																																																					Element pontoFulgor = xml.createElement("pontoFulgor");
																																																																					pontoFulgor.setTextContent(tokens[7].trim());
																																																																					peri.appendChild(pontoFulgor);
																																																																					
																																																																					infCTeNorm.appendChild(peri);
																																																																				}
																																																																				else
																																																																				{
																																																																					if(tokens[0].trim().equalsIgnoreCase("22000"))
																																																																					{
																																																																						logger.info("Contem : 22000");
																																																																						NodeList infCTeNormNL = xml.getElementsByTagName("infCTeNorm");
																																																																						Element infCTeNorm = null;
																																																																						if(infCTeNormNL.getLength() > 0)
																																																																						{
																																																																							infCTeNorm = (ElementImpl)infCTeNormNL.item(infCTeNormNL.getLength()-1);
																																																																						}
																																																																						Element veicNovos = xml.createElement("veicNovos");
																																																																						Element chassi = xml.createElement("chassi");
																																																																						chassi.setTextContent(tokens[1].trim());
																																																																						veicNovos.appendChild(chassi);
																																																																						Element cCor = xml.createElement("cCor");
																																																																						cCor.setTextContent(tokens[2].trim());
																																																																						veicNovos.appendChild(cCor);
																																																																						Element xCor = xml.createElement("xCor");
																																																																						xCor.setTextContent(tokens[3].trim());
																																																																						veicNovos.appendChild(xCor);
																																																																						Element cMod = xml.createElement("cMod");
																																																																						cMod.setTextContent(tokens[4].trim());
																																																																						veicNovos.appendChild(cMod);
																																																																						Element vUnit = xml.createElement("vUnit");
																																																																						vUnit.setTextContent(tokens[5].trim());
																																																																						veicNovos.appendChild(vUnit);
																																																																						Element vFrete = xml.createElement("vFrete");
																																																																						vFrete.setTextContent(tokens[6].trim());
																																																																						veicNovos.appendChild(vFrete);
																																																																						
																																																																						infCTeNorm.appendChild(veicNovos);
																																																																					}
																																																																					else
																																																																					{
																																																																						if(tokens[0].trim().equalsIgnoreCase("22100"))
																																																																						{
																																																																							logger.info("Contem : 22100");
																																																																							NodeList infCTeNormNL = xml.getElementsByTagName("infCTeNorm");
																																																																							Element infCTeNorm = null;
																																																																							if(infCTeNormNL.getLength() > 0)
																																																																							{
																																																																								infCTeNorm = (ElementImpl)infCTeNormNL.item(infCTeNormNL.getLength()-1);
																																																																							}
																																																																							Element infCteSub = xml.createElement("infCteSub");
																																																																							Element chCte = xml.createElement("chCte");
																																																																							chCte.setTextContent(tokens[1].trim());
																																																																							infCteSub.appendChild(chCte);
																																																																							
																																																																							infCTeNorm.appendChild(infCteSub);
																																																																						}
																																																																						else
																																																																						{
																																																																							if(tokens[0].trim().equalsIgnoreCase("22110"))
																																																																							{
																																																																								logger.info("Contem : 22110");
																																																																								NodeList infCteSubNL = xml.getElementsByTagName("infCteSub");
																																																																								Element infCteSub = null;
																																																																								if(infCteSubNL.getLength() > 0)
																																																																								{
																																																																									infCteSub = (ElementImpl)infCteSubNL.item(infCteSubNL.getLength()-1);
																																																																								}
																																																																								Element tomaICMS = xml.createElement("tomaICMS");
																																																																								infCteSub.appendChild(tomaICMS);
																																																																							}
																																																																							else
																																																																							{
																																																																								if(tokens[0].trim().equalsIgnoreCase("22111"))
																																																																								{
																																																																									logger.info("Contem : 22111");
																																																																									NodeList tomaICMSNL = xml.getElementsByTagName("tomaICMS");
																																																																									Element tomaICMS = null;
																																																																									if(tomaICMSNL.getLength() > 0)
																																																																									{
																																																																										tomaICMS = (ElementImpl)tomaICMSNL.item(tomaICMSNL.getLength()-1);
																																																																									}
																																																																									Element refNFe = xml.createElement("refNFe");
																																																																									refNFe.setTextContent(tokens[1].trim());
																																																																									tomaICMS.appendChild(refNFe);
																																																																									
																																																																								}
																																																																								else
																																																																								{
																																																																									if(tokens[0].trim().equalsIgnoreCase("22112"))
																																																																									{
																																																																										logger.info("Contem : 22112");
																																																																										NodeList tomaICMSNL = xml.getElementsByTagName("tomaICMS");
																																																																										Element tomaICMS = null;
																																																																										if(tomaICMSNL.getLength() > 0)
																																																																										{
																																																																											tomaICMS = (ElementImpl)tomaICMSNL.item(tomaICMSNL.getLength()-1);
																																																																										}
																																																																										Element refNF = xml.createElement("refNF");
																																																																										Element CNPJ = xml.createElement("CNPJ");
																																																																										CNPJ.setTextContent(tokens[1].trim());
																																																																										refNF.appendChild(CNPJ);
																																																																										Element mod = xml.createElement("mod");
																																																																										mod.setTextContent(tokens[2].trim());
																																																																										refNF.appendChild(mod);
																																																																										Element serie = xml.createElement("serie");
																																																																										serie.setTextContent(tokens[3].trim());
																																																																										refNF.appendChild(serie);
																																																																										Element subserie = xml.createElement("subserie");
																																																																										subserie.setTextContent(tokens[4].trim());
																																																																										refNF.appendChild(subserie);
																																																																										Element nro = xml.createElement("nro");
																																																																										nro.setTextContent(tokens[5].trim());
																																																																										refNF.appendChild(nro);
																																																																										Element valor = xml.createElement("valor");
																																																																										valor.setTextContent(tokens[6].trim());
																																																																										refNF.appendChild(valor);
																																																																										Element dEmi = xml.createElement("dEmi");
																																																																										dEmi.setTextContent(tokens[7].trim());
																																																																										refNF.appendChild(dEmi);
																																																																										
																																																																										tomaICMS.appendChild(refNF);
																																																																									}
																																																																									else
																																																																									{
																																																																										if(tokens[0].trim().equalsIgnoreCase("22113"))
																																																																										{
																																																																											logger.info("Contem : 22113");
																																																																											NodeList tomaICMSNL = xml.getElementsByTagName("tomaICMS");
																																																																											Element tomaICMS = null;
																																																																											if(tomaICMSNL.getLength() > 0)
																																																																											{
																																																																												tomaICMS = (ElementImpl)tomaICMSNL.item(tomaICMSNL.getLength()-1);
																																																																											}
																																																																											Element refCte = xml.createElement("refCte");
																																																																											refCte.setTextContent(tokens[1].trim());
																																																																											tomaICMS.appendChild(refCte);
																																																																										}
																																																																										else
																																																																										{
																																																																											if(tokens[0].trim().equalsIgnoreCase("22120"))
																																																																											{
																																																																												logger.info("Contem : 22120");
																																																																												NodeList infCteSubNL = xml.getElementsByTagName("infCteSub");
																																																																												Element infCteSub = null;
																																																																												if(infCteSubNL.getLength() > 0)
																																																																												{
																																																																													infCteSub = (ElementImpl)infCteSubNL.item(infCteSubNL.getLength()-1);
																																																																												}
																																																																												Element tomaNaoICMS = xml.createElement("tomaNaoICMS");
																																																																												Element refCteAnu = xml.createElement("refCteAnu");
																																																																												refCteAnu.setTextContent(tokens[1].trim());
																																																																												tomaNaoICMS.appendChild(refCteAnu);
																																																																												infCteSub.appendChild(tomaNaoICMS);
																																																																											}
																																																																											else
																																																																											{
																																																																												if(tokens[0].trim().equalsIgnoreCase("23000"))
																																																																												{
																																																																													logger.info("Contem : 23000");
																																																																													NodeList infCteNL = xml.getElementsByTagName("infCte");
																																																																													Element infCte = null;
																																																																													if(infCteNL.getLength() > 0)
																																																																													{
																																																																														infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																																																																													}
																																																																													Element infCteComp = xml.createElement("infCteComp");
																																																																													Element chave = xml.createElement("chave");
																																																																													chave.setTextContent(tokens[1].trim());
																																																																													infCteComp.appendChild(chave);
																																																																													infCte.appendChild(infCteComp);
																																																																												}
																																																																												else
																																																																												{
																																																																													if(tokens[0].trim().equalsIgnoreCase("23100"))
																																																																													{
																																																																														logger.info("Contem : 23100");
																																																																														NodeList infCteCompNL = xml.getElementsByTagName("infCteComp");
																																																																														Element infCteComp = null;
																																																																														if(infCteCompNL.getLength() > 0)
																																																																														{
																																																																															infCteComp = (ElementImpl)infCteCompNL.item(infCteCompNL.getLength()-1);
																																																																														}
																																																																														Element vPresComp = xml.createElement("vPresComp");
																																																																														Element vTPrest = xml.createElement("vTPrest");
																																																																														vTPrest.setTextContent(tokens[1].trim());
																																																																														vPresComp.appendChild(vTPrest);
																																																																														infCteComp.appendChild(vPresComp);
																																																																													}
																																																																													else
																																																																													{
																																																																														if(tokens[0].trim().equalsIgnoreCase("23110"))
																																																																														{
																																																																															logger.info("Contem : 23110");
																																																																															NodeList vPresCompNL = xml.getElementsByTagName("vPresComp");
																																																																															Element vPresComp = null;
																																																																															if(vPresCompNL.getLength() > 0)
																																																																															{
																																																																																vPresComp = (ElementImpl)vPresCompNL.item(vPresCompNL.getLength()-1);
																																																																															}
																																																																															Element compComp = xml.createElement("compComp");
																																																																															Element xNome = xml.createElement("xNome");
																																																																															xNome.setTextContent(tokens[1].trim());
																																																																															compComp.appendChild(xNome);
																																																																															Element vComp = xml.createElement("vComp");
																																																																															vComp.setTextContent(tokens[2].trim());
																																																																															compComp.appendChild(vComp);
																																																																															vPresComp.appendChild(compComp);
																																																																														}
																																																																														else
																																																																														{
																																																																															if(tokens[0].trim().equalsIgnoreCase("24000"))
																																																																															{
																																																																																logger.info("Contem : 24000");
																																																																																NodeList vPresCompNL = xml.getElementsByTagName("vPresComp");
																																																																																Element vPresComp = null;
																																																																																if(vPresCompNL.getLength() > 0)
																																																																																{
																																																																																	vPresComp = (ElementImpl)vPresCompNL.item(vPresCompNL.getLength()-1);
																																																																																}
																																																																																Element impComp = xml.createElement("impComp");
																																																																																Element ICMSComp = xml.createElement("ICMSComp");
																																																																																impComp.appendChild(ICMSComp);
																																																																																vPresComp.appendChild(impComp);
																																																																															}
																																																																															else
																																																																															{
																																																																																if(tokens[0].trim().equalsIgnoreCase("24100"))
																																																																																{
																																																																																	logger.info("Contem : 24100");
																																																																																	NodeList ICMSCompNL = xml.getElementsByTagName("ICMSComp");
																																																																																	Element ICMSComp = null;
																																																																																	if(ICMSCompNL.getLength() > 0)
																																																																																	{
																																																																																		ICMSComp = (ElementImpl)ICMSCompNL.item(ICMSCompNL.getLength()-1);
																																																																																	}
																																																																																	Element CST00 = xml.createElement("CST00");
																																																																																	Element CST = xml.createElement("CST");
																																																																																	CST.setTextContent(tokens[1].trim());
																																																																																	CST00.appendChild(CST);
																																																																																	Element vBC = xml.createElement("vBC");
																																																																																	vBC.setTextContent(tokens[2].trim());
																																																																																	CST00.appendChild(vBC);
																																																																																	Element pICMS = xml.createElement("pICMS");
																																																																																	pICMS.setTextContent(tokens[3].trim());
																																																																																	CST00.appendChild(pICMS);
																																																																																	Element vICMS = xml.createElement("vICMS");
																																																																																	vICMS.setTextContent(tokens[4].trim());
																																																																																	CST00.appendChild(vICMS);
																																																																																	ICMSComp.appendChild(CST00);
																																																																																}
																																																																																else
																																																																																{
																																																																																	if(tokens[0].trim().equalsIgnoreCase("24120"))
																																																																																	{
																																																																																		logger.info("Contem : 24120");
																																																																																		NodeList ICMSCompNL = xml.getElementsByTagName("ICMSComp");
																																																																																		Element ICMSComp = null;
																																																																																		if(ICMSCompNL.getLength() > 0)
																																																																																		{
																																																																																			ICMSComp = (ElementImpl)ICMSCompNL.item(ICMSCompNL.getLength()-1);
																																																																																		}
																																																																																		Element CST20 = xml.createElement("CST20");
																																																																																		Element CST = xml.createElement("CST");
																																																																																		CST.setTextContent(tokens[1].trim());
																																																																																		CST20.appendChild(CST);
																																																																																		Element pRedBC = xml.createElement("pRedBC");
																																																																																		pRedBC.setTextContent(tokens[2].trim());
																																																																																		CST20.appendChild(pRedBC);
																																																																																		Element vBC = xml.createElement("vBC");
																																																																																		vBC.setTextContent(tokens[3].trim());
																																																																																		CST20.appendChild(vBC);
																																																																																		Element pICMS = xml.createElement("pICMS");
																																																																																		pICMS.setTextContent(tokens[4].trim());
																																																																																		CST20.appendChild(pICMS);
																																																																																		Element vICMS = xml.createElement("vICMS");
																																																																																		vICMS.setTextContent(tokens[5].trim());
																																																																																		CST20.appendChild(vICMS);
																																																																																		
																																																																																		ICMSComp.appendChild(CST20);
																																																																																	}
																																																																																	else
																																																																																	{
																																																																																		if(tokens[0].trim().equalsIgnoreCase("24145"))
																																																																																		{
																																																																																			logger.info("Contem : 24145");
																																																																																			NodeList ICMSCompNL = xml.getElementsByTagName("ICMSComp");
																																																																																			Element ICMSComp = null;
																																																																																			if(ICMSCompNL.getLength() > 0)
																																																																																			{
																																																																																				ICMSComp = (ElementImpl)ICMSCompNL.item(ICMSCompNL.getLength()-1);
																																																																																			}
																																																																																			Element CST45 = xml.createElement("CST45");
																																																																																			Element CST = xml.createElement("CST");
																																																																																			CST.setTextContent(tokens[1].trim());
																																																																																			CST45.appendChild(CST);
																																																																																			ICMSComp.appendChild(CST45);
																																																																																		}
																																																																																		else
																																																																																		{
																																																																																			if(tokens[0].trim().equalsIgnoreCase("24180"))
																																																																																			{
																																																																																				logger.info("Contem : 24180");
																																																																																				NodeList ICMSCompNL = xml.getElementsByTagName("ICMSComp");
																																																																																				Element ICMSComp = null;
																																																																																				if(ICMSCompNL.getLength() > 0)
																																																																																				{
																																																																																					ICMSComp = (ElementImpl)ICMSCompNL.item(ICMSCompNL.getLength()-1);
																																																																																				}
																																																																																				Element CST80 = xml.createElement("CST80");
																																																																																				Element CST = xml.createElement("CST");
																																																																																				CST.setTextContent(tokens[1].trim());
																																																																																				CST80.appendChild(CST);
																																																																																				Element vBC = xml.createElement("vBC");
																																																																																				vBC.setTextContent(tokens[2].trim());
																																																																																				CST80.appendChild(vBC);
																																																																																				Element pICMS = xml.createElement("pICMS");
																																																																																				pICMS.setTextContent(tokens[3].trim());
																																																																																				CST80.appendChild(pICMS);
																																																																																				Element vICMS = xml.createElement("vICMS");
																																																																																				vICMS.setTextContent(tokens[4].trim());
																																																																																				CST80.appendChild(vICMS);
																																																																																				Element vCred = xml.createElement("vCred");
																																																																																				vCred.setTextContent(tokens[5].trim());
																																																																																				CST80.appendChild(vCred);
																																																																																				
																																																																																				ICMSComp.appendChild(CST80);
																																																																																			}
																																																																																			else
																																																																																			{
																																																																																				if(tokens[0].trim().equalsIgnoreCase("24181"))
																																																																																				{
																																																																																					logger.info("Contem : 24181");
																																																																																					NodeList ICMSCompNL = xml.getElementsByTagName("ICMSComp");
																																																																																					Element ICMSComp = null;
																																																																																					if(ICMSCompNL.getLength() > 0)
																																																																																					{
																																																																																						ICMSComp = (ElementImpl)ICMSCompNL.item(ICMSCompNL.getLength()-1);
																																																																																					}
																																																																																					Element CST81 = xml.createElement("CST81");
																																																																																					Element CST = xml.createElement("CST");
																																																																																					CST.setTextContent(tokens[1].trim());
																																																																																					CST81.appendChild(CST);
																																																																																					Element pRedBC = xml.createElement("pRedBC");
																																																																																					pRedBC.setTextContent(tokens[2].trim());
																																																																																					CST81.appendChild(pRedBC);
																																																																																					Element vBC = xml.createElement("vBC");
																																																																																					vBC.setTextContent(tokens[3].trim());
																																																																																					CST81.appendChild(vBC);
																																																																																					Element pICMS = xml.createElement("pICMS");
																																																																																					pICMS.setTextContent(tokens[4].trim());
																																																																																					CST81.appendChild(pICMS);
																																																																																					Element vICMS = xml.createElement("vICMS");
																																																																																					vICMS.setTextContent(tokens[5].trim());
																																																																																					CST81.appendChild(vICMS);
																																																																																					
																																																																																					ICMSComp.appendChild(CST81);
																																																																																				}
																																																																																				else
																																																																																				{
																																																																																					if(tokens[0].trim().equalsIgnoreCase("24190"))
																																																																																					{
																																																																																						logger.info("Contem : 24190");
																																																																																						NodeList ICMSCompNL = xml.getElementsByTagName("ICMSComp");
																																																																																						Element ICMSComp = null;
																																																																																						if(ICMSCompNL.getLength() > 0)
																																																																																						{
																																																																																							ICMSComp = (ElementImpl)ICMSCompNL.item(ICMSCompNL.getLength()-1);
																																																																																						}
																																																																																						Element CST90 = xml.createElement("CST90");
																																																																																						Element CST = xml.createElement("CST");
																																																																																						CST.setTextContent(tokens[1].trim());
																																																																																						CST90.appendChild(CST);
																																																																																						Element pRedBC = xml.createElement("pRedBC");
																																																																																						pRedBC.setTextContent(tokens[2].trim());
																																																																																						CST90.appendChild(pRedBC);
																																																																																						Element vBC = xml.createElement("vBC");
																																																																																						vBC.setTextContent(tokens[3].trim());
																																																																																						CST90.appendChild(vBC);
																																																																																						Element pICMS = xml.createElement("pICMS");
																																																																																						pICMS.setTextContent(tokens[4].trim());
																																																																																						CST90.appendChild(pICMS);
																																																																																						Element vICMS = xml.createElement("vICMS");
																																																																																						vICMS.setTextContent(tokens[5].trim());
																																																																																						CST90.appendChild(vICMS);
																																																																																						Element vCred = xml.createElement("vCred");
																																																																																						vCred.setTextContent(tokens[6].trim());
																																																																																						CST90.appendChild(vCred);
																																																																																						
																																																																																						ICMSComp.appendChild(CST90);
																																																																																					}
																																																																																					else
																																																																																					{
																																																																																						if(tokens[0].trim().equalsIgnoreCase("24300"))
																																																																																						{
																																																																																							logger.info("Contem : 24300");
																																																																																							NodeList impCompNL = xml.getElementsByTagName("impComp");
																																																																																							Element impComp = null;
																																																																																							if(impCompNL.getLength() > 0)
																																																																																							{
																																																																																								impComp = (ElementImpl)impCompNL.item(impCompNL.getLength()-1);
																																																																																							}
																																																																																							Element infAdFisco = xml.createElement("infAdFisco");
																																																																																							infAdFisco.setTextContent(tokens[1].trim());
																																																																																							impComp.appendChild(infAdFisco);
																																																																																						}
																																																																																						else
																																																																																						{
																																																																																							if(tokens[0].trim().equalsIgnoreCase("25000"))
																																																																																							{
																																																																																								logger.info("Contem : 25000");
																																																																																								NodeList infCteNL = xml.getElementsByTagName("infCte");
																																																																																								Element infCte = null;
																																																																																								if(infCteNL.getLength() > 0)
																																																																																								{
																																																																																									infCte = (ElementImpl)infCteNL.item(infCteNL.getLength()-1);
																																																																																								}
																																																																																								Element infCteAnu = xml.createElement("infCteAnu");
																																																																																								Element chCte = xml.createElement("chCte");
																																																																																								chCte.setTextContent(tokens[1].trim());
																																																																																								infCteAnu.appendChild(chCte);
																																																																																								Element dEmi = xml.createElement("dEmi");
																																																																																								dEmi.setTextContent(tokens[2].trim());
																																																																																								infCteAnu.appendChild(dEmi);
																																																																																								
																																																																																								infCte.appendChild(infCteAnu);
																																																																																							}
																																																																																							else
																																																																																							{
																																																																																								logger.info("Tokens inesperado : "+tokens[0].trim());
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
