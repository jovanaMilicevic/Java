package projekat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, KnjigaPostojiException
	{
		ArrayList<Knjiga> listaKnjiga = new ArrayList<>();

		ArrayList<Kupac> listaLogin = new ArrayList<>();

		ArrayList<Izdavanje> listaIzdavanje = new ArrayList<>();




		Scanner scanner = new Scanner(System.in);
		int br, br2, br3, br4, br5, br6, br7, br8, br9;

		listaKnjiga = Knjiga.citajDat();
		listaLogin = Admin.citajKorisnika();
		listaIzdavanje = Izdavanje.citajIzdavanja();

		System.out.println("Dobro dosli u biblioteku E-ZNANJE\n");
		System.out.println("Za logovanje ukucajte 1 a za registraciju ukucajte 2");
		while (true)
		{
			listaKnjiga = Knjiga.citajDat();
			if(scanner.hasNextInt()) {
				br = scanner.nextInt();
				scanner.nextLine();
				if (br == 1) {
					while(true) {
						String korIme, pass;

						System.out.println("Unesite korisnicko ime");
						korIme = scanner.nextLine();

						System.out.println("Unesite lozinku");
						pass = scanner.nextLine();

						if (korIme.equals("admin") && pass.equals("admin")) {
							System.out.println("Dobro dosli admine");

							while (true) {
								listaKnjiga = Knjiga.citajDat();
								System.out.println("\nUnesite 1 da dodate knjigu ili 2 da udjete u administrativni plan ili 3 da pogledate listu iznajmljenih knjiga ili 4 da izadjete iz programa");
								if (scanner.hasNextInt()) {
									br3 = scanner.nextInt();
									scanner.nextLine();
									if (br3 == 1) {
										String naslov, zanr, autor;
										int god_iz = 0, kolicina = 0;

										System.out.println("Unesite naziv knjige");
										naslov = scanner.nextLine();

										System.out.println("Unesite zanr knjige");
										while (true) {
											zanr = scanner.nextLine();
											if (Provere.proveraBroj(zanr)) {
												System.out.println("Uneli ste broj, treba da unesete zanr");
											} else
												break;
										}
										System.out.println("Unesite autora knjige");
										while (true) {
											autor = scanner.nextLine();
											if (Provere.proveraBroj(autor)) {
												System.out.println("Uneli ste broj, treba da unesete ime autora");
											} else
												break;
										}


										System.out.println("Unesite godinu izdavanja knjige");
										while (true) {

											if (scanner.hasNextInt()) {
												god_iz = scanner.nextInt();
												break;
											}
											scanner.nextLine();
											System.out.println("Niste uneli broj za godinu");
										}

										System.out.println("Unesite kolicinu");
										while (true) {
											if (scanner.hasNextInt()) {
												kolicina = scanner.nextInt();
												break;
											}
											scanner.nextLine();
											System.out.println("Niste uneli broj za kolicinu knjiga koje donirate");
										}
										if (Provere.proveriKnjigu(naslov)) {
											for (int i = 0; i < listaKnjiga.size(); i++) {
												if (listaKnjiga.get(i).getNaziv().equals(naslov))
													listaKnjiga.get(i).setKolicina(listaKnjiga.get(i).getKolicina() + kolicina);
											}
											Knjiga.upisiDat(listaKnjiga);
										} else {
											listaKnjiga.add(new Knjiga(listaKnjiga.get(listaKnjiga.size() - 1).getId_knjige() + 1, naslov, zanr, autor, god_iz, kolicina));
											Knjiga.upisiDat(listaKnjiga);

											for (Knjiga knj : listaKnjiga)
												System.out.println(knj);
										}
									}
									if (br3 == 2) {
										while (true) {
											listaKnjiga = Knjiga.citajDat();
											System.out.println("\nUnesite 1 da obrisete knjigu ili 2 da izmenite knjigu ili 3 da obrisete korisnika ili 4 da se vratite u glavni meni administratora");
											if (scanner.hasNextInt()) {
												br5 = scanner.nextInt();
												scanner.nextLine();
												if (br5 == 1) {
													listaKnjiga = Knjiga.citajDat();

													for (Knjiga a : listaKnjiga)
														System.out.println(a);

													System.out.println("\nUnesite broj pod kojim je zavedena knjiga koju zelite da obrisete");
													while (true) {
														if (scanner.hasNextInt()) {
															br6 = scanner.nextInt();
															scanner.nextLine();

															boolean iznajmljeno = false;

															if (Provere.proveriIdKnjige(br6)) {
																for (int i = 0; i < listaKnjiga.size(); i++) {
																	if (listaKnjiga.get(i).getId_knjige() == br6) {
																		for (Izdavanje iz : listaIzdavanje) {
																			if (iz.getId_knjige() == br6) {
																				System.out.println("Knjiga je iznajmljena, ne moze da se obrise");
																				iznajmljeno = true;
																				break;
																			}

																		}
																		if (iznajmljeno == false) {
																			System.out.println("Knjiga je uspesno obrisana");
																			listaKnjiga.remove(i);
																		}

																		Knjiga.upisiDat(listaKnjiga);
																	}
																}

																break;
															}

														} else
															System.out.println("Knjiga sa tim brojem ne postoji");
													}

												} else if (br5 == 2) {
													listaKnjiga = Knjiga.citajDat();

													for (Knjiga a : listaKnjiga)
														System.out.println(a);

													System.out.println("\nUnesite broj pod kojim je zavedena knjiga koju zelite da izmenite");
													while (true) {
														listaKnjiga = Knjiga.citajDat();
														if (scanner.hasNextInt()) {
															br7 = scanner.nextInt();
															scanner.nextLine();

															if (Provere.proveriIdKnjige(br7)) {
																for (int i = 0; i < listaKnjiga.size(); i++) {
																	if (listaKnjiga.get(i).getId_knjige() == br7) {
																		Knjiga.upisiDat(listaKnjiga);
																		Knjiga.citajDat();


																		while (true) {
																			listaKnjiga = Knjiga.citajDat();
																			System.out.println("Da promenite naslov unesite 1, da promenite zanr unesite 2, da promenite autora unesite 3, da promenite godinu izdavanja unesite 4, da promenite kolicinu unesite 5, da izadjete iz dela za izmenu unesite 6");
																			if (scanner.hasNextInt()) {
																				br8 = scanner.nextInt();
																				scanner.nextLine();

																				String naslov, zanr, autor;
																				int god_iz = 0, kolicina = 0;


																				if (br8 == 1) {
																					while (true) {
																						System.out.println("Izmenite naziv knjige");
																						naslov = scanner.nextLine();
																						if (Provere.proveraBroj(naslov)) {
																							System.out.println("Uneli ste broj, treba da unesete naslov knjige");
																						} else {
																							listaKnjiga.get(i).setNaziv(naslov);
																							break;
																						}
																					}
																					Knjiga.upisiDat(listaKnjiga);
																				} else if (br8 == 2) {
																					System.out.println("Izmenite zanr knjige");
																					while (true) {
																						zanr = scanner.nextLine();
																						if (Provere.proveraBroj(zanr)) {
																							System.out.println("Uneli ste broj, treba da unesete zanr");
																						} else {
																							listaKnjiga.get(i).setZanr(zanr);
																							break;
																						}
																					}
																					Knjiga.upisiDat(listaKnjiga);
																				} else if (br8 == 3) {
																					System.out.println("Izmenite ime autora");
																					while (true) {
																						autor = scanner.nextLine();
																						if (Provere.proveraBroj(autor)) {
																							System.out.println("Uneli ste broj, treba da unesete ime autora");
																						} else {
																							listaKnjiga.get(i).setAutor(autor);
																							break;
																						}
																					}
																					Knjiga.upisiDat(listaKnjiga);
																				} else if (br8 == 4) {
																					System.out.println("Unesite izmenjenu godinu izdavanja knjige");
																					while (true) {
																						if (scanner.hasNextInt()) {
																							god_iz = scanner.nextInt();
																							break;
																						}
																						scanner.nextLine();
																						System.out.println("Niste uneli broj za godinu");
																					}
																					listaKnjiga.get(i).setGod_izdavanja(god_iz);
																					Knjiga.upisiDat(listaKnjiga);
																				} else if (br8 == 5) {
																					System.out.println("Izmenite kolicinu knjiga");
																					while (true) {
																						if (scanner.hasNextInt()) {
																							kolicina = scanner.nextInt();
																							break;
																						}
																						scanner.nextLine();
																						System.out.println("Niste uneli broj za kolicinu knjiga koje donirate");
																					}
																					listaKnjiga.get(i).setKolicina(kolicina);
																					Knjiga.upisiDat(listaKnjiga);
																				} else if (br8 == 6)
																					break;
																				else {
																					System.out.println("Da promenite naslov unesite 1, da promenite zanr unesite 2, da promenite autora unesite 3, da promenite godinu izdavanja unesite 4, da promenite kolicinu unesite 5, da izadjete iz dela za izmenu unesite 6");
																					scanner.nextLine();
																				}
																			}
																		}
																	}
																}

																break;
															} else
																System.out.println("Knjiga sa tim brojem ne postoji");
														}

													}
												} else if (br5 == 3) {
													listaLogin = Admin.citajKorisnika();

													for (Kupac k : listaLogin)
														System.out.println(k);

													System.out.println("\nUnesite broj pod kojim je zaveden korisnik koga zelite da obrisete");
													while (true) {
														if (scanner.hasNextInt()) {
															br9 = scanner.nextInt();
															scanner.nextLine();

															boolean iznajmljeno = false;

															if (Provere.proveriIdKupca(br9)) {
																for (int i = 0; i < listaLogin.size(); i++) {
																	if (listaLogin.get(i).getId_kupca() == br9) {
																		for (Izdavanje iz : listaIzdavanje) {
																			if (iz.getId_korisnika() == br9) {
																				System.out.println("Korisnik ima iznajmljene knjige, ne moze da se obrise");
																				iznajmljeno = true;
																				break;
																			}

																		}
																		if (iznajmljeno == false) {
																			System.out.println("Korisnik je uspesno obrisan");
																			listaLogin.remove(i);
																		}

																		Admin.upisiKorisnika(listaLogin);
																		Admin.citajKorisnika();
																	}
																}

																break;
															} else
																System.out.println("Korisnik sa tim brojem ne postoji");
														}

													}
												} else if (br5 == 4) {
													break;
												} else {
													System.out.println("\nUnesite 1 da obrisete knjigu ili 2 da izmenite knjigu ili 3 da obrisete korisnika ili 4 da se vratite u glavni meni administratora");
													scanner.hasNextLine();
												}
											}
										}


									}
									if (br3 == 3) {
										for (Kupac ku : listaLogin) {
											System.out.println("\n" + ku.getIme() + " je iznajmio knjige: ");
											for (Izdavanje iz : listaIzdavanje) {
												if (ku.getId_kupca() == iz.getId_korisnika()) {
													for (Knjiga knj : listaKnjiga) {
														if (knj.getId_knjige() == iz.getId_knjige()) {
															System.out.println(knj.getNaziv() + " , vratiti do: " + iz.getDatum_vracanja());

														}
													}
												}
											}
										}
									}
									if (br3 == 4)
										break;
								} else {
									System.out.println("\nUnesite 1 da dodate knjigu ili 2 da udjete u administrativni plan ili 3 da pogledate listu iznajmljenih knjiga ili 4 da izadjete iz programa");
									scanner.nextLine();
								}
							}


						} else if (Provere.ulogujKorisnika(korIme, pass)) {
							System.out.println("Uspesno ste se ulogovali");

							while (true) {
								System.out.println("\nUkucajte 1 za iznajmljivanje knjige ili 2 za doniranje knjige ili unesite 3 da izadjete iz programa");

								if (scanner.hasNextInt()) {
									br2 = scanner.nextInt();
									scanner.nextLine();

									if (br2 == 1) {

										listaKnjiga = Knjiga.citajDat();

										for (Knjiga a : listaKnjiga)
											System.out.println(a);

										System.out.println("\nUnesite broj pod kojim je zavedena knjiga");

										while (true) {
											if (scanner.hasNextInt()) {
												br4 = scanner.nextInt();
												scanner.nextLine();


												if (Provere.proveriIdKnjige(br4)) {
													for (int i = 0; i < listaKnjiga.size(); i++) {
														if (listaKnjiga.get(i).getId_knjige() == br4) {
															for (Kupac k : listaLogin) {
																if (korIme.equals(k.kor_ime)) {
																	if (listaKnjiga.get(i).Prvr(br4)) {
																		listaIzdavanje.add(new Izdavanje(k.getId_kupca(), br4, "10/12/2021"));
																		listaKnjiga.get(i).setKolicina(listaKnjiga.get(i).getKolicina() - 1);
																		System.out.println("Uspesno ste iznajmili knjigu\n");
																		break;
																	} else {
																		System.out.println("Knjiga nije na stanju");
																		break;
																	}
																}
															}
															Izdavanje.upisiIzdavanja(listaIzdavanje);
															Knjiga.upisiDat(listaKnjiga);
														}
													}

													break;
												} else
													System.out.println("Knjiga sa tim brojem ne postoji");


											} else {
												System.out.println("Unesite broj pod kojim je zavedena knjiga");
												scanner.nextLine();
											}
										}


									}
									else if (br2 == 2) {

										String naslov, zanr, autor;
										int god_iz = 0, kolicina = 0;

										System.out.println("Unesite naziv knjige");
										naslov = scanner.nextLine();

										System.out.println("Unesite zanr knjige");
										while (true) {
											zanr = scanner.nextLine();
											if (Provere.proveraBroj(zanr)) {
												System.out.println("Uneli ste broj, treba da unesete zanr knjige");
											} else
												break;
										}


										System.out.println("Unesite autora knjige");
										while (true) {
											autor = scanner.nextLine();
											if (Provere.proveraBroj(autor)) {
												System.out.println("Uneli ste broj, treba da unesete ime autora");
											} else
												break;
										}

										System.out.println("Unesite godinu izdavanja knjige");
										while (true) {

											if (scanner.hasNextInt()) {
												god_iz = scanner.nextInt();
												break;
											}
											scanner.nextLine();
											System.out.println("Niste uneli broj za godinu");
										}

										System.out.println("Unesite kolicinu koju donirate");
										while (true) {
											if (scanner.hasNextInt()) {
												kolicina = scanner.nextInt();
												break;
											}
											scanner.nextLine();
											System.out.println("Niste uneli broj za kolicinu knjiga koje donirate");
										}
										if (Provere.proveriKnjigu(naslov)) {
											for (int i = 0; i < listaKnjiga.size(); i++) {
												listaKnjiga.get(i).getNaziv().equals(naslov);
												listaKnjiga.get(i).setKolicina(listaKnjiga.get(i).getKolicina() + kolicina);
											}
											Knjiga.upisiDat(listaKnjiga);
										} else {
											listaKnjiga.add(new Knjiga(listaKnjiga.get(listaKnjiga.size() - 1).getId_knjige() + 1, naslov, zanr, autor, god_iz, kolicina));
											Knjiga.upisiDat(listaKnjiga);

											for (Knjiga knj : listaKnjiga)
												System.out.println(knj);
										}

									}
									else if (br2 == 3)
										return;

								} else {
									System.out.println("Ukucajte 1 za iznajmljivanje knjige ili 2 za doniranje knjige ili unesite 3 da izadjete iz programa");
									scanner.nextLine();
								}
							}

						} else {
							System.out.println("Korisnik ne postoji");
							System.out.println("Za logovanje ukucajte 1 a za registraciju ukucajte 2");
							break;
						}
					}
				}
				else if (br == 2)
				{

					String ime, prezime, adresa, tel, korIme, pass;
					//listaLogin2 = Admin.citajKorisnika();

					System.out.println("Unesite vase ime");
					while(true) {
						ime = scanner.nextLine();
						if (Provere.proveraBroj(ime)) {
							System.out.println("Uneli ste broj, treba da unesete vase ime");
						}
						else
							break;
					}

					System.out.println("Unesite vase prezime");
					while(true) {
						prezime = scanner.nextLine();
						if (Provere.proveraBroj(prezime)) {
							System.out.println("Uneli ste broj, treba da unesete vase prezime");
						}
						else
							break;
					}

					System.out.println("Unesite vasu adresu");
						adresa = scanner.nextLine();


					System.out.println("Unesite vas broj telefona");
					while(true) {
						//scanner.nextLine();
						tel = scanner.nextLine();
						if (!(Provere.proveraTelefon(tel))) {
							System.out.println("Broj nije validan");
						}
						else
							break;
					}


					System.out.println("Unesite vase korisnicko ime");
					while (true)
					{
						korIme = scanner.nextLine();

						if(Provere.korImePostoji(korIme))
						{
							System.out.println("Korisnicko ime vec postoji, molimo vas promenite");
						}
						else
							break;
					}


					System.out.println("Unesite vasu lozinku");
					pass = scanner.nextLine();

					System.out.println("Uneli ste: " + ime + " " + prezime + " " + adresa + " " + tel + " " + korIme + " " + pass);

					listaLogin.add(new Kupac(korIme,pass,ime,prezime,adresa,tel,listaLogin.get(listaLogin.size()-1).getId_kupca()+1));
					Admin.upisiKorisnika(listaLogin);

					listaLogin = Admin.citajKorisnika();

					System.out.println("Za logovanje ukucajte 1 a za registraciju ukucajte 2");


				}

				else {
					System.out.println("Za logovanje ukucajte 1 a za registraciju ukucajte 2");
					scanner.nextLine();

				}
			}
			else {
				System.out.println("Niste uneli broj");
				scanner.nextLine();
			}
		}

	}
}
