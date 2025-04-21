package tpfinal;

import java.util.Collections;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Torneo {
	private String nombre;
	private Equipo campeon = null;
	private LinkedList<Equipo> EquiposTorneo = new LinkedList<Equipo>();
	private LinkedList<Apuesta> Apuestas = new LinkedList<Apuesta>();
	private LinkedList<Equipo> EquiposGanadoresCuartos = new LinkedList<Equipo>();
	private LinkedList<Equipo> EquiposGanadoresSemis = new LinkedList<Equipo>();
	private LinkedList<Partido> Partidos = new LinkedList<Partido>();
	private int[] num;
	

	public Torneo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Equipo getCampeon() {
		return campeon;
	}

	public void setCampeon(Equipo campeon) {
		this.campeon = campeon;
	}

	public LinkedList<Equipo> getEquiposTorneo() {
		return EquiposTorneo;
	}

	public void setEquiposTorneo(LinkedList<Equipo> equiposTorneo) {
		EquiposTorneo = equiposTorneo;
	}

	public LinkedList<Partido> getPartidos() {
		return Partidos;
	}

	public void setPartidos(LinkedList<Partido> partidos) {
		Partidos = partidos;
	}

	public LinkedList<Equipo> getEquiposGanadoresCuartos() {
		return EquiposGanadoresCuartos;
	}

	public void setEquiposGanadoresCuartos(LinkedList<Equipo> equiposGanadoresCuartos) {
		EquiposGanadoresCuartos = equiposGanadoresCuartos;
	}

	public LinkedList<Equipo> getEquiposGanadoresSemis() {
		return EquiposGanadoresSemis;
	}

	public void setEquiposGanadoresSemis(LinkedList<Equipo> equiposGanadoresSemis) {
		EquiposGanadoresSemis = equiposGanadoresSemis;
	}

	public LinkedList<Apuesta> getApuestas() {
		return Apuestas;
	}

	public void setApuestas(LinkedList<Apuesta> apuestas) {
		Apuestas = apuestas;
	}

	public void SeleccionEquiposTorneo(GestorEquipo gestorEquipo) {
		LinkedList<Equipo> todosLosEquipos = gestorEquipo.getEquipos();
		LinkedList<Equipo> equiposDisponibles = new LinkedList<>();

		for (Equipo equipo : todosLosEquipos) {
		    if (equipo.getJugadores().size() >= 11) {
		        equiposDisponibles.add(equipo);
		    }
		}

	    String[] opcionesSeleccion = { "Automático", "Manual" };
	    int seleccionModo = JOptionPane.showOptionDialog(null,
	            "Seleccione el modo de selección de equipos para el torneo.",
	            "Modo de Selección", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
	            new ImageIcon(Main.class.getResource("/img/champions.png")), opcionesSeleccion, opcionesSeleccion[0]);


	    if (seleccionModo == 0) {

	        Collections.shuffle(equiposDisponibles);

	        EquiposTorneo.addAll(equiposDisponibles.subList(0, 8));
	    } else {
	        for (int i = 0; i < 8; i++) {
	            String[] equipos = new String[equiposDisponibles.size()];
	            for (int j = 0; j < equiposDisponibles.size(); j++) {
	                equipos[j] = equiposDisponibles.get(j).getNombre();
	            }

	            int seleccion = JOptionPane.showOptionDialog(null,
	                    "Seleccione un equipo para el torneo, recuerde que el torneo es de 8 equipos.\nCantidad de equipos seleccionados: "
	                            + i,
	                    "Selección de Equipos", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
	                    new ImageIcon(Main.class.getResource("/img/champions.png")), equipos, equipos[0]);

	            EquiposTorneo.add(equiposDisponibles.get(seleccion));
	            equiposDisponibles.remove(seleccion);
	        }
	    }
	}

	public void SorteoPartidos() {
		num = new int[EquiposTorneo.size()];
		for (int i = 0; i < EquiposTorneo.size(); i++) {
			boolean fueusado;
			int n;
			do {
				n = (int) (Math.random() * EquiposTorneo.size());
				fueusado = false;

				for (int j = 0; j < i; j++) {
					if (num[j] == n) {
						fueusado = true;
					}
				}
			} while (fueusado);
			num[i] = n;

		}
		for (int i = 0; i < 4; i++) {
			Partidos.add(new Partido(EquiposTorneo.get(num[i * 2]), EquiposTorneo.get(num[i * 2 + 1])));
		}
	}

	public void VerListaEquiposTorneo() {
		String[] detalles = new String[EquiposTorneo.size()];
		for (int i = 0; i < EquiposTorneo.size(); i++) {
			detalles[i] = "-Nombre: " + EquiposTorneo.get(i).getNombre() + "    -Ciudad: "
					+ EquiposTorneo.get(i).getCiudad() + "\n\n";
		}
		JOptionPane.showMessageDialog(null, detalles);
	}

	public void VerLlave() {
		String partido1 = "", partido2 = "", partido3 = "", partido4 = "", partido5 = "", partido6 = "", partido7 = "",
				ganador = "";

		partido1 = Partidos.get(0).getEquipoLocal().getNombre() + " vs "
				+ Partidos.get(0).getEquipoVisitante().getNombre();
		partido2 = Partidos.get(1).getEquipoLocal().getNombre() + " vs "
				+ Partidos.get(1).getEquipoVisitante().getNombre();
		partido3 = Partidos.get(2).getEquipoLocal().getNombre() + " vs "
				+ Partidos.get(2).getEquipoVisitante().getNombre();
		partido4 = Partidos.get(3).getEquipoLocal().getNombre() + " vs "
				+ Partidos.get(3).getEquipoVisitante().getNombre();
		if (Partidos.size() < 6) {
			partido5 = "Ganador partido 1 vs Ganador partido 2";
			partido6 = "Ganador partido 3 vs Ganador partido 4";
		} else {
			if (Partidos.get(0).getGolPenalLocal() == 0 && Partidos.get(0).getGolPenalLocal() == 0) {
				partido1 = "(" + Partidos.get(0).getGolLocal() + ")" + Partidos.get(0).getEquipoLocal().getNombre()
						+ " - " + Partidos.get(0).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(0).getGolVisitante() + ")";
			} else {
				partido1 = "(" + Partidos.get(0).getGolPenalLocal() + "-P)"
						+ Partidos.get(0).getEquipoLocal().getNombre() + " - "
						+ Partidos.get(0).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(0).getGolPenalVisitante() + "-P)";
			}
			if (Partidos.get(1).getGolPenalLocal() == 0 && Partidos.get(1).getGolPenalLocal() == 0) {
				partido2 = "(" + Partidos.get(1).getGolLocal() + ")" + Partidos.get(1).getEquipoLocal().getNombre()
						+ " - " + Partidos.get(1).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(1).getGolVisitante() + ")";
			} else {
				partido2 = "(" + Partidos.get(1).getGolPenalLocal() + "-P)"
						+ Partidos.get(1).getEquipoLocal().getNombre() + " - "
						+ Partidos.get(1).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(1).getGolPenalVisitante() + "-P)";
			}
			if (Partidos.get(2).getGolPenalLocal() == 0 && Partidos.get(2).getGolPenalLocal() == 0) {
				partido3 = "(" + Partidos.get(2).getGolLocal() + ")" + Partidos.get(2).getEquipoLocal().getNombre()
						+ " - " + Partidos.get(2).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(2).getGolVisitante() + ")";
			} else {
				partido3 = "(" + Partidos.get(2).getGolPenalLocal() + "-P)"
						+ Partidos.get(2).getEquipoLocal().getNombre() + " - "
						+ Partidos.get(2).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(2).getGolPenalVisitante() + "-P)";
			}
			if (Partidos.get(3).getGolPenalLocal() == 0 && Partidos.get(3).getGolPenalLocal() == 0) {
				partido4 = "(" + Partidos.get(3).getGolLocal() + ")" + Partidos.get(3).getEquipoLocal().getNombre()
						+ " - " + Partidos.get(3).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(3).getGolVisitante() + ")";
			} else {
				partido4 = "(" + Partidos.get(3).getGolPenalLocal() + "-P)"
						+ Partidos.get(3).getEquipoLocal().getNombre() + " - "
						+ Partidos.get(3).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(3).getGolPenalVisitante() + "-P)";
			}
			partido5 = Partidos.get(4).getEquipoLocal().getNombre() + " vs "
					+ Partidos.get(4).getEquipoVisitante().getNombre();
			partido6 = Partidos.get(5).getEquipoLocal().getNombre() + " vs "
					+ Partidos.get(5).getEquipoVisitante().getNombre();
		}
		if (Partidos.size() < 7) {
			partido7 = "Ganador semifinal 1 vs Ganador semifinal 2";
		} else {
			if (Partidos.get(4).getGolPenalLocal() == 0 && Partidos.get(4).getGolPenalLocal() == 0) {
				partido5 = "(" + Partidos.get(4).getGolLocal() + ")" + Partidos.get(4).getEquipoLocal().getNombre()
						+ " - " + Partidos.get(4).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(4).getGolVisitante() + ")";
			} else {
				partido5 = "(" + Partidos.get(4).getGolPenalLocal() + "-P)"
						+ Partidos.get(4).getEquipoLocal().getNombre() + " - "
						+ Partidos.get(4).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(4).getGolPenalVisitante() + "-P)";
			}
			if (Partidos.get(5).getGolPenalLocal() == 0 && Partidos.get(5).getGolPenalLocal() == 0) {
				partido6 = "(" + Partidos.get(5).getGolLocal() + ")" + Partidos.get(5).getEquipoLocal().getNombre()
						+ " - " + Partidos.get(5).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(5).getGolVisitante() + ")";
			} else {
				partido6 = "(" + Partidos.get(5).getGolPenalLocal() + "-P)"
						+ Partidos.get(5).getEquipoLocal().getNombre() + " - "
						+ Partidos.get(5).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(5).getGolPenalVisitante() + "-P)";
			}
			partido7 = Partidos.get(6).getEquipoLocal().getNombre() + " vs "
					+ Partidos.get(6).getEquipoVisitante().getNombre();
		}
		if (campeon == null) {
			ganador = "Equipo ganador de la semifinal";
		} else {
			if (Partidos.get(6).getGolPenalLocal() == 0 && Partidos.get(6).getGolPenalLocal() == 0) {
				partido7 = "(" + Partidos.get(6).getGolLocal() + ")" + Partidos.get(6).getEquipoLocal().getNombre()
						+ " - " + Partidos.get(6).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(6).getGolVisitante() + ")";
			} else {
				partido7 = "(" + Partidos.get(6).getGolPenalLocal() + "-P)"
						+ Partidos.get(6).getEquipoLocal().getNombre() + " - "
						+ Partidos.get(6).getEquipoVisitante().getNombre() + "("
						+ Partidos.get(6).getGolPenalVisitante() + "-P)";
			}
			ganador = campeon.getNombre();
		}

		JOptionPane.showMessageDialog(null,
				"----------------- CUARTOS DE FINAL -----------------" + "\n" + partido1 + "\n" + partido2 + "\n"
						+ partido3 + "\n" + partido4 + "\n----------------- SEMI FINAL -----------------" + "\n"
						+ partido5 + "\n" + partido6 + "\n----------------- FINAL -----------------" + "\n" + partido7
						+ "\n----------------- GANADOR -----------------" + "\n" + ganador);
	}

	public void SimularPartidos() {
		if (this.campeon != null) {

			JOptionPane.showMessageDialog(null,
					"Equipo seleccionado: \n-Nombre: " + campeon.getNombre() + "\n-Ciudad: " + campeon.getCiudad(),
					"seleccion", JOptionPane.DEFAULT_OPTION,
					new ImageIcon(Torneo.class.getResource("/img/" + campeon.getFoto())));
		} else if (EquiposGanadoresSemis.size() == 2) {
			simularFinal();
		} else if (EquiposGanadoresCuartos.size() == 4) {
			simularSemifinales();
		} else {
			simularCuartos();
		}
	}

	private void simularFinal() {
		JOptionPane.showMessageDialog(null, "Bienvenido a la final " + Partidos.get(6).getEquipoLocal().getNombre()
				+ " vs " + Partidos.get(6).getEquipoVisitante().getNombre());

		int golesLocal = 0, golesVisitante = 0, golesPenalLocal = 0, golesPenalVisitante = 0, tiroAlArcoLocal = 0,
				tiroAlArcoVisitante = 0, cornerLocal = 0, cornerVisitante = 0, posesionLocal = 0, posesionVisitante = 0,
				amarillaLocal = 0, amarillaVisitante = 0, rojaLocal = 0, rojaVisitante = 0;

		for (int minuto = 1; minuto <= 90; minuto++) {

			if (Math.random() < 0.03) {

				if (Math.random() < 0.5) {
					int numeroAleatorio = (int) (Math.random() * Partidos.get(6).getEquipoLocal().getJugadores().size());
					golesLocal++;
					JOptionPane.showMessageDialog(null, "¡Gol! de " + Partidos.get(6).getEquipoLocal().getNombre()
							+ " el jugador "+ Partidos.get(6).getEquipoLocal().getJugadores().get(numeroAleatorio).getNombre() + " marca en el minuto " + minuto);
				} else {
					int numeroAleatorio = (int) (Math.random() * Partidos.get(6).getEquipoVisitante().getJugadores().size());
					golesVisitante++;
					JOptionPane.showMessageDialog(null, "¡Gol! de " + Partidos.get(6).getEquipoVisitante().getNombre()
							+ " el jugador "+ Partidos.get(6).getEquipoVisitante().getJugadores().get(numeroAleatorio).getNombre() + " marca en el minuto " + minuto);
				}
			}

			if (Math.random() < 0.06) {
				if (Math.random() < 0.5) {
					cornerLocal++;
					tiroAlArcoLocal++;
				} else {
					cornerVisitante++;
					tiroAlArcoVisitante++;
				}
			}

			if (Math.random() < 0.02) {
				if (Math.random() < 0.5) {
					amarillaLocal++;
				} else {
					amarillaVisitante++;
				}
			}
			if (Math.random() < 0.01) {
				if (Math.random() < 0.5) {
					rojaLocal++;
				} else {
					rojaVisitante++;
				}
			}

			posesionLocal = (int) (Math.random() * 31) + 35;
			posesionVisitante = 100 - posesionLocal;

		}

		Partidos.get(6).setTarjetaAmarillaLocal(amarillaLocal);
		Partidos.get(6).setTarjetaAmarillaVisitante(amarillaVisitante);
		Partidos.get(6).setTarjetaRojaLocal(rojaLocal);
		Partidos.get(6).setTarjetaRojaVisitante(rojaVisitante);
		Partidos.get(6).setPosesionLocal(posesionLocal);
		Partidos.get(6).setPosesionVisitante(posesionVisitante);
		Partidos.get(6).setCornerLocal(cornerLocal);
		Partidos.get(6).setCornerVisitante(cornerVisitante);
		Partidos.get(6).setJugado(true);
		if (tiroAlArcoLocal < golesLocal) {
			Partidos.get(6).setTiroAlArcoLocal(golesLocal);
		} else {
			Partidos.get(6).setTiroAlArcoLocal(tiroAlArcoLocal);
		}

		if (tiroAlArcoVisitante < golesVisitante) {
			Partidos.get(6).setTiroAlArcoVisitante(golesVisitante);
		} else {
			Partidos.get(6).setTiroAlArcoVisitante(tiroAlArcoVisitante);
		}

		if (golesLocal == golesVisitante) {
			if (golesLocal == 0) {
				JOptionPane.showMessageDialog(null, "No hubo goles en el partido");
			}
			JOptionPane.showMessageDialog(null, "El partido va a penales.");
			int total = 5;
			for (int penal = 1; penal <= total; penal++) {
				if (Math.random() < 0.7) {
					golesPenalLocal++;
				}
				if (Math.random() < 0.7) {
					golesPenalVisitante++;
				}
			}
			while (golesPenalLocal == golesPenalVisitante) {
				total++;
				if (Math.random() < 0.7) {
					golesPenalLocal++;
				}
				if (Math.random() < 0.7) {
					golesPenalVisitante++;
				}
			}
			JOptionPane.showMessageDialog(null,
					"Fin de la tanda de penales: " + Partidos.get(6).getEquipoLocal().getNombre() + " "
							+ golesPenalLocal + " - " + golesPenalVisitante + " "
							+ Partidos.get(6).getEquipoVisitante().getNombre());
			if (golesPenalLocal > golesPenalVisitante) {
				campeon = Partidos.get(6).getEquipoLocal();
				JOptionPane.showMessageDialog(null, "El equipo ganador del torneo es " + campeon.getNombre());

			} else {
				campeon = Partidos.get(6).getEquipoVisitante();
				JOptionPane.showMessageDialog(null, "El equipo ganador del torneo es " + campeon.getNombre());
			}
			Partidos.get(6).setGolPenalLocal(golesPenalLocal);
			Partidos.get(6).setGolPenalVisitante(golesPenalVisitante);
		} else {
			Partidos.get(6).setGolLocal(golesLocal);
			Partidos.get(6).setGolVisitante(golesVisitante);
			JOptionPane.showMessageDialog(null, "Fin del partido: " + Partidos.get(6).getEquipoLocal().getNombre() + " "
					+ golesLocal + " - " + golesVisitante + " " + Partidos.get(6).getEquipoVisitante().getNombre());
			if (golesLocal > golesVisitante) {
				campeon = Partidos.get(6).getEquipoLocal();
				JOptionPane.showMessageDialog(null, "El equipo ganador del torneo es " + campeon.getNombre());
			} else {
				campeon = Partidos.get(6).getEquipoVisitante();
				JOptionPane.showMessageDialog(null, "El equipo ganador del torneo es " + campeon.getNombre());
			}
		}
	}

	private void simularSemifinales() {
		JOptionPane.showMessageDialog(null, "Se simulará la SEMIFINAL");
		for (int i = 4; i < 6; i++) {
			JOptionPane.showMessageDialog(null,
					"Partido número " + (i - 3) + " de SEMIFINALES " + Partidos.get(i).getEquipoLocal().getNombre()
							+ " vs " + Partidos.get(i).getEquipoVisitante().getNombre());

			int golesLocal = 0, golesVisitante = 0, golesPenalLocal = 0, golesPenalVisitante = 0, tiroAlArcoLocal = 0,
					tiroAlArcoVisitante = 0, cornerLocal = 0, cornerVisitante = 0, posesionLocal = 0,
					posesionVisitante = 0, amarillaLocal = 0, amarillaVisitante = 0, rojaLocal = 0, rojaVisitante = 0;

			for (int minuto = 1; minuto <= 90; minuto++) {

				if (Math.random() < 0.03) {

					if (Math.random() < 0.5) {
						int numeroAleatorio = (int) (Math.random() * Partidos.get(i).getEquipoLocal().getJugadores().size());
						golesLocal++;
						JOptionPane.showMessageDialog(null, "¡Gol! de " + Partidos.get(i).getEquipoLocal().getNombre()
								+ " el jugador "+ Partidos.get(i).getEquipoLocal().getJugadores().get(numeroAleatorio).getNombre() + " marca en el minuto " + minuto);
					} else {
						int numeroAleatorio = (int) (Math.random() * Partidos.get(i).getEquipoVisitante().getJugadores().size());
						golesVisitante++;
						JOptionPane.showMessageDialog(null, "¡Gol! de " + Partidos.get(i).getEquipoVisitante().getNombre()
								+ " el jugador "+ Partidos.get(i).getEquipoVisitante().getJugadores().get(numeroAleatorio).getNombre() + " marca en el minuto " + minuto);
					}
				}

				if (Math.random() < 0.05) {
					if (Math.random() < 0.5) {
						cornerLocal++;
						tiroAlArcoLocal++;
					} else {
						cornerVisitante++;
						tiroAlArcoVisitante++;
					}
				}

				if (Math.random() < 0.02) {
					if (Math.random() < 0.5) {
						amarillaLocal++;
					} else {
						amarillaVisitante++;
					}
				}
				if (Math.random() < 0.01) {
					if (Math.random() < 0.5) {
						rojaLocal++;
					} else {
						rojaVisitante++;
					}
				}

				posesionLocal = (int) (Math.random() * 31) + 35;
				posesionVisitante = 100 - posesionLocal;

			}

			Partidos.get(i).setTarjetaAmarillaLocal(amarillaLocal);
			Partidos.get(i).setTarjetaAmarillaVisitante(amarillaVisitante);
			Partidos.get(i).setTarjetaRojaLocal(rojaLocal);
			Partidos.get(i).setTarjetaRojaVisitante(rojaVisitante);
			Partidos.get(i).setPosesionLocal(posesionLocal);
			Partidos.get(i).setPosesionVisitante(posesionVisitante);
			Partidos.get(i).setCornerLocal(cornerLocal);
			Partidos.get(i).setCornerVisitante(cornerVisitante);
			Partidos.get(i).setJugado(true);
			if (tiroAlArcoLocal < golesLocal) {
				Partidos.get(i).setTiroAlArcoLocal(golesLocal);
			} else {
				Partidos.get(i).setTiroAlArcoLocal(tiroAlArcoLocal);
			}

			if (tiroAlArcoVisitante < golesVisitante) {
				Partidos.get(i).setTiroAlArcoVisitante(golesVisitante);
			} else {
				Partidos.get(i).setTiroAlArcoVisitante(tiroAlArcoVisitante);
			}

			if (golesLocal == golesVisitante) {
				if (golesLocal == 0) {
					JOptionPane.showMessageDialog(null, "No hubo goles en el partido");
				}
				JOptionPane.showMessageDialog(null, "El partido va a penales.");
				int total = 5;
				for (int penal = 1; penal <= total; penal++) {
					if (Math.random() < 0.7) {
						golesPenalLocal++;
					}
					if (Math.random() < 0.7) {
						golesPenalVisitante++;
					}
				}
				while (golesPenalLocal == golesPenalVisitante) {
					total++;
					if (Math.random() < 0.7) {
						golesPenalLocal++;
					}
					if (Math.random() < 0.7) {
						golesPenalVisitante++;
					}
				}
				if (golesPenalLocal > golesPenalVisitante) {
					EquiposGanadoresSemis.add(Partidos.get(i).getEquipoLocal());
				} else {
					EquiposGanadoresSemis.add(Partidos.get(i).getEquipoVisitante());
				}
				JOptionPane.showMessageDialog(null,
						"Fin de la tanda de penales: " + Partidos.get(i).getEquipoLocal().getNombre() + " "
								+ golesPenalLocal + " - " + golesPenalVisitante + " "
								+ Partidos.get(i).getEquipoVisitante().getNombre());
				Partidos.get(i).setGolPenalLocal(golesPenalLocal);
				Partidos.get(i).setGolPenalVisitante(golesPenalVisitante);
			} else {
				Partidos.get(i).setGolLocal(golesLocal);
				Partidos.get(i).setGolVisitante(golesVisitante);
				JOptionPane.showMessageDialog(null,
						"Fin del partido: " + Partidos.get(i).getEquipoLocal().getNombre() + " " + golesLocal + " - "
								+ golesVisitante + " " + Partidos.get(i).getEquipoVisitante().getNombre());
				if (golesLocal > golesVisitante) {
					EquiposGanadoresSemis.add(Partidos.get(i).getEquipoLocal());
				} else {
					EquiposGanadoresSemis.add(Partidos.get(i).getEquipoVisitante());
				}
			}

		}
		Partidos.add(new Partido(EquiposGanadoresSemis.get(0), EquiposGanadoresSemis.get(1)));
		JOptionPane.showMessageDialog(null, "Los equipos que avanzaron a la final son "
				+ EquiposGanadoresSemis.get(0).getNombre() + " - " + EquiposGanadoresSemis.get(1).getNombre());
	}

	private void simularCuartos() {
		JOptionPane.showMessageDialog(null, "Se simulará los CUARTOS DE FINAL");
		for (int i = 0; i < 4; i++) {
			JOptionPane.showMessageDialog(null,
					"Partido número " + (i + 1) + " de CUARTOS " + Partidos.get(i).getEquipoLocal().getNombre() + " vs "
							+ Partidos.get(i).getEquipoVisitante().getNombre());
			int golesLocal = 0, golesVisitante = 0, golesPenalLocal = 0, golesPenalVisitante = 0, tiroAlArcoLocal = 0,
					tiroAlArcoVisitante = 0, cornerLocal = 0, cornerVisitante = 0, posesionLocal = 0,
					posesionVisitante = 0, amarillaLocal = 0, amarillaVisitante = 0, rojaLocal = 0, rojaVisitante = 0;

			for (int minuto = 1; minuto <= 90; minuto++) {

				if (Math.random() < 0.03) {

					if (Math.random() < 0.5) {
						int numeroAleatorio = (int) (Math.random() * Partidos.get(i).getEquipoLocal().getJugadores().size());
						golesLocal++;
						JOptionPane.showMessageDialog(null, "¡Gol! de " + Partidos.get(i).getEquipoLocal().getNombre()
								+ " el jugador "+ Partidos.get(i).getEquipoLocal().getJugadores().get(numeroAleatorio).getNombre() + " marca en el minuto " + minuto);
					} else {
						int numeroAleatorio = (int) (Math.random() * Partidos.get(i).getEquipoVisitante().getJugadores().size());
						golesVisitante++;
						JOptionPane.showMessageDialog(null, "¡Gol! de " + Partidos.get(i).getEquipoVisitante().getNombre()
								+ " el jugador "+ Partidos.get(i).getEquipoVisitante().getJugadores().get(numeroAleatorio).getNombre() + " marca en el minuto " + minuto);
					}
				}

				if (Math.random() < 0.05) {
					if (Math.random() < 0.5) {
						cornerLocal++;
						tiroAlArcoLocal++;
					} else {
						cornerVisitante++;
						tiroAlArcoVisitante++;
					}
				}

				if (Math.random() < 0.02) {
					if (Math.random() < 0.5) {
						amarillaLocal++;
					} else {
						amarillaVisitante++;
					}
				}
				if (Math.random() < 0.01) {
					if (Math.random() < 0.5) {
						rojaLocal++;
					} else {
						rojaVisitante++;
					}
				}

				posesionLocal = (int) (Math.random() * 31) + 35;
				posesionVisitante = 100 - posesionLocal;
			}

			Partidos.get(i).setTarjetaAmarillaLocal(amarillaLocal);
			Partidos.get(i).setTarjetaAmarillaVisitante(amarillaVisitante);
			Partidos.get(i).setTarjetaRojaLocal(rojaLocal);
			Partidos.get(i).setTarjetaRojaVisitante(rojaVisitante);
			Partidos.get(i).setPosesionLocal(posesionLocal);
			Partidos.get(i).setPosesionVisitante(posesionVisitante);
			Partidos.get(i).setCornerLocal(cornerLocal);
			Partidos.get(i).setCornerVisitante(cornerVisitante);
			Partidos.get(i).setJugado(true);
			if (tiroAlArcoLocal < golesLocal) {
				Partidos.get(i).setTiroAlArcoLocal(golesLocal);
			} else {
				Partidos.get(i).setTiroAlArcoLocal(tiroAlArcoLocal);
			}

			if (tiroAlArcoVisitante < golesVisitante) {
				Partidos.get(i).setTiroAlArcoVisitante(golesVisitante);
			} else {
				Partidos.get(i).setTiroAlArcoVisitante(tiroAlArcoVisitante);
			}

			if (golesLocal == golesVisitante) {
				if (golesLocal == 0) {
					JOptionPane.showMessageDialog(null, "No hubo goles en el partido");
				}
				JOptionPane.showMessageDialog(null, "El partido va a penales.");
				int total = 5;
				for (int penal = 1; penal <= total; penal++) {
					if (Math.random() < 0.7) {
						golesPenalLocal++;
					}
					if (Math.random() < 0.7) {
						golesPenalVisitante++;
					}
				}
				while (golesPenalLocal == golesPenalVisitante) {
					total++;
					if (Math.random() < 0.7) {
						golesPenalLocal++;
					}
					if (Math.random() < 0.7) {
						golesPenalVisitante++;
					}
				}
				if (golesPenalLocal > golesPenalVisitante) {
					EquiposGanadoresCuartos.add(Partidos.get(i).getEquipoLocal());
				} else {
					EquiposGanadoresCuartos.add(Partidos.get(i).getEquipoVisitante());
				}
				JOptionPane.showMessageDialog(null,
						"Fin de la tanda de penales: " + Partidos.get(i).getEquipoLocal().getNombre() + " "
								+ golesPenalLocal + " - " + golesPenalVisitante + " "
								+ Partidos.get(i).getEquipoVisitante().getNombre());
				Partidos.get(i).setGolPenalLocal(golesPenalLocal);
				Partidos.get(i).setGolPenalVisitante(golesPenalVisitante);
			} else {
				Partidos.get(i).setGolLocal(golesLocal);
				Partidos.get(i).setGolVisitante(golesVisitante);
				JOptionPane.showMessageDialog(null,
						"Fin del partido: " + Partidos.get(i).getEquipoLocal().getNombre() + " " + golesLocal + " - "
								+ golesVisitante + " " + Partidos.get(i).getEquipoVisitante().getNombre());
				if (golesLocal > golesVisitante) {
					EquiposGanadoresCuartos.add(Partidos.get(i).getEquipoLocal());
				} else {
					EquiposGanadoresCuartos.add(Partidos.get(i).getEquipoVisitante());
				}
			}

		}
		Partidos.add(new Partido(EquiposGanadoresCuartos.get(0), EquiposGanadoresCuartos.get(1)));
		Partidos.add(new Partido(EquiposGanadoresCuartos.get(2), EquiposGanadoresCuartos.get(3)));
		JOptionPane.showMessageDialog(null,
				"Los equipos que avanzaron a la semifinales son " + EquiposGanadoresCuartos.get(0).getNombre() + " - "
						+ EquiposGanadoresCuartos.get(1).getNombre() + " - "
						+ EquiposGanadoresCuartos.get(2).getNombre() + " - "
						+ EquiposGanadoresCuartos.get(3).getNombre());
	}

	public Partido SeleccionarPartido() {
		int opcion;
		do {
			String[] opciones = { "Cuartos", "Semifinal", "Final", "Salir" };
			opcion = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
					"Selecciona una opción", 0, 0, null, opciones, opciones[0]);
			switch (opcion) {
			case 0:
				if (Partidos.size() > 4) {
					String[] partidos = new String[4];

					for (int i = 0; i < 4; i++) {
						partidos[i] = Partidos.get(i).getEquipoLocal().getNombre() + " vs "
								+ Partidos.get(i).getEquipoVisitante().getNombre();
					}
					String seleccion2 = (String) JOptionPane.showInputDialog(null, "Seleccione el partido", null, 0,
							null, partidos, partidos[0]);
					for (int i = 0; i < 4; i++) {
						if ((Partidos.get(i).getEquipoLocal().getNombre() + " vs "
								+ Partidos.get(i).getEquipoVisitante().getNombre()).equals(seleccion2)) {
							return this.getPartidos().get(i);

						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Todavía no se han jugado los partidos de esta fase.");
				}

				break;
			case 1:
				if (Partidos.size() > 6) {
					String[] partidos = new String[2];

					for (int i = 0; i < 2; i++) {
						partidos[i] = Partidos.get(i + 4).getEquipoLocal().getNombre() + " vs "
								+ Partidos.get(i + 4).getEquipoVisitante().getNombre();
					}
					String seleccion2 = (String) JOptionPane.showInputDialog(null, "Seleccione el partido", null, 0,
							null, partidos, partidos[0]);
					for (int i = 4; i < 6; i++) {
						if ((Partidos.get(i).getEquipoLocal().getNombre() + " vs "
								+ Partidos.get(i).getEquipoVisitante().getNombre()).equals(seleccion2)) {
							return this.getPartidos().get(i);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Todavía no se han jugado los partidos de esta fase.");
				}

				break;
			case 2:
				if (this.campeon != null) {
					String[] partidos = new String[1];
					partidos[0] = Partidos.get(6).getEquipoLocal().getNombre() + " vs "
							+ Partidos.get(6).getEquipoVisitante().getNombre();
					String seleccion2 = (String) JOptionPane.showInputDialog(null, "Seleccione el partido", null, 0,
							null, partidos, partidos[0]);

					if ((Partidos.get(6).getEquipoLocal().getNombre() + " vs "
							+ Partidos.get(6).getEquipoVisitante().getNombre()).equals(seleccion2)) {
						return this.getPartidos().get(6);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Todavía no se han jugado los partidos de esta fase.");
				}

				break;
			default:
				break;
			}
		} while (opcion != 3);
		return null;

	}

	public Partido SeleccionarPartidoPorJugar() {
		int opcion;
		do {
			String[] opciones = { "Cuartos", "Semifinal", "Final", "Salir" };
			opcion = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
					"Selecciona una opción", 0, 0, null, opciones, opciones[0]);
			switch (opcion) {
			case 0:
				if (Partidos.size() < 5) {
					String[] partidos = new String[4];

					for (int i = 0; i < 4; i++) {
						partidos[i] = Partidos.get(i).getEquipoLocal().getNombre() + " vs "
								+ Partidos.get(i).getEquipoVisitante().getNombre();
					}
					String seleccion2 = (String) JOptionPane.showInputDialog(null, "Seleccione el partido", null, 0,
							null, partidos, partidos[0]);
					for (int i = 0; i < 4; i++) {
						if ((Partidos.get(i).getEquipoLocal().getNombre() + " vs "
								+ Partidos.get(i).getEquipoVisitante().getNombre()).equals(seleccion2)) {
							return this.getPartidos().get(i);

						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Esta fase ya se jugó por lo tanto no podrás apostar.");
				}

				break;
			case 1:
				if (Partidos.size() == 6) {
					String[] partidos = new String[2];

					for (int i = 0; i < 2; i++) {
						partidos[i] = Partidos.get(i + 4).getEquipoLocal().getNombre() + " vs "
								+ Partidos.get(i + 4).getEquipoVisitante().getNombre();
					}
					String seleccion2 = (String) JOptionPane.showInputDialog(null, "Seleccione el partido", null, 0,
							null, partidos, partidos[0]);
					for (int i = 4; i < 6; i++) {
						if ((Partidos.get(i).getEquipoLocal().getNombre() + " vs "
								+ Partidos.get(i).getEquipoVisitante().getNombre()).equals(seleccion2)) {
							return this.getPartidos().get(i);
						}
					}
				} else if (Partidos.size() > 6) {
					JOptionPane.showMessageDialog(null, "Esta fase ya se jugó por lo tanto no podrás apostar.");
				} else {
					JOptionPane.showMessageDialog(null, "La fase anterior no se ha jugado.");
				}

				break;
			case 2:
				if (Partidos.size() == 7) {
					String[] partidos = new String[1];
					partidos[0] = Partidos.get(6).getEquipoLocal().getNombre() + " vs "
							+ Partidos.get(6).getEquipoVisitante().getNombre();
					String seleccion2 = (String) JOptionPane.showInputDialog(null, "Seleccione el partido", null, 0,
							null, partidos, partidos[0]);

					if ((Partidos.get(6).getEquipoLocal().getNombre() + " vs "
							+ Partidos.get(6).getEquipoVisitante().getNombre()).equals(seleccion2)) {
						return this.getPartidos().get(6);
					}

				} else if(this.campeon != null) {
					JOptionPane.showMessageDialog(null, "Esta fase ya se jugó por lo tanto no podrás apostar.");
				} else {
					JOptionPane.showMessageDialog(null, "La fase anterior no se ha jugado.");
				}

				break;
			default:
				break;
			}
		} while (opcion != 3);
		return null;

	}

	public void Apostar(Partido seleccionado) {
	    int opcion2;
	    double monto;
	    String[] opciones2 = { seleccionado.getEquipoLocal().getNombre(), seleccionado.getEquipoVisitante().getNombre(),
	            "Salir" };
	    opcion2 = JOptionPane.showOptionDialog(null, "Seleccione el equipo", "Selecciona una opción", 0, 0, null,
	            opciones2, opciones2[0]);
	    
	    if (opcion2 != 2) {
	    	String validar;
	    	do {	    		
	    		monto=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto que desea apostar.\nSi acierta el ganador ganará x2 sino perderá lo ingresado.\nEl monto minimo a apostar es 50$"));
	    		if (monto>=50) {
					validar="si";
				} else {
					JOptionPane.showMessageDialog(null, "Error, recuerda que debes ingresar un monto mayor o igual a 50");
					validar="no";
				}
	    	} while (validar=="no");
	    	
	        Apuesta nuevaApuesta = new Apuesta();
	        nuevaApuesta.setPartido(seleccionado);
	        nuevaApuesta.setMonto(monto);
	        nuevaApuesta.setEquipoSeleccionado(opciones2[opcion2]);
	        nuevaApuesta.setEstado("En espera");
	        Apuestas.add(nuevaApuesta);
	        JOptionPane.showMessageDialog(null,
	                "Apuesta generada con éxito:\nPartido: " + nuevaApuesta.getPartido().getEquipoLocal().getNombre() + " vs " + nuevaApuesta.getPartido().getEquipoVisitante().getNombre() + "\nEquipo seleccionado: "
	                        + nuevaApuesta.getEquipoSeleccionado());
	    }
	}
	
	public String VerificarApuesta(Apuesta apuesta) {
		String ganador;
		if (apuesta.getPartido().isJugado()) {
			if (apuesta.getPartido().getGolLocal()>apuesta.getPartido().getGolVisitante()) {
				ganador=apuesta.getPartido().getEquipoLocal().getNombre();
			} else if(apuesta.getPartido().getGolLocal()<apuesta.getPartido().getGolVisitante()) {
				ganador=apuesta.getPartido().getEquipoVisitante().getNombre();
			} else {
				if (apuesta.getPartido().getGolPenalLocal()>apuesta.getPartido().getGolPenalVisitante()) {
					ganador=apuesta.getPartido().getEquipoLocal().getNombre();
				} else {
					ganador=apuesta.getPartido().getEquipoVisitante().getNombre();
				}
			}
			if (apuesta.getEquipoSeleccionado()==ganador) {
				return "Ganada";
			} else {
				return "Perdida";
			}
		} else {
			return "En espera";
		}
	}
	
	
	public void BuscarApuesta() {
		String estado;
		double ganancia;
		if (this.getApuestas().size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay apuestas, por lo tanto no podras buscar ninguna");
		} else {
			String[] apuestas = new String[this.getApuestas().size()];
			for (int i = 0; i < this.getApuestas().size(); i++) {
				estado = this.VerificarApuesta(this.getApuestas().get(i));
				if (estado=="Ganada") {
					ganancia =this.getApuestas().get(i).getMonto()*2;
				} else {
					ganancia = 0;
				}
				apuestas[i] = "Apuesta "+(i+1)+": -Partido: " + this.getApuestas().get(i).getPartido().getEquipoLocal().getNombre() + " vs " + this.getApuestas().get(i).getPartido().getEquipoVisitante().getNombre() + " -Equipo seleccionado: " + this.getApuestas().get(i).getEquipoSeleccionado() + " -Monto apostado: " + this.getApuestas().get(i).getMonto() + "$" +" -Estado: " + estado + " -Ganancia: " + ganancia + "$\n";
			}
			JOptionPane.showMessageDialog(null, apuestas);
		}
	}
	
	public void verEstadisticas() {
	    if (Partidos.size() == 4) {
	        JOptionPane.showMessageDialog(null, "Todavía no se ha jugado ningún partido, por lo tanto no hay estadísticas para mostrar.");
	    } else {
	        String estadisticas;

	        int maxGoles = 0;
	        int maxTarjetasAmarillas = 0;
	        int maxTarjetasRojas = 0;
	        int maxTirosAlArco = 0;
	        int maxCorners = 0;

	        LinkedList<Equipo> equiposMasGoleadores = new LinkedList<Equipo>();
	        LinkedList<Equipo> equiposMasTarjetasAmarillas = new LinkedList<Equipo>();
	        LinkedList<Equipo> equiposMasTarjetasRojas = new LinkedList<Equipo>();
	        LinkedList<Equipo> equiposMasTirosAlArco = new LinkedList<Equipo>();
	        LinkedList<Equipo> equiposMasCorners = new LinkedList<Equipo>();

	        for (Equipo equipo : EquiposTorneo) {
	            int golesEquipos = contarGolesEquipo(equipo);
	            int tarjetasAmarillas = contarTarjetasAmarillasEquipo(equipo);
	            int tarjetasRojas = contarTarjetasRojasEquipo(equipo);
	            int tirosAlArco = contarTirosAlArcoEquipo(equipo);
	            int corners = contarCornersEquipo(equipo);

	            if (golesEquipos > maxGoles) {
	                maxGoles = golesEquipos;
	                equiposMasGoleadores.clear();
	                equiposMasGoleadores.add(equipo);
	            } else if (golesEquipos == maxGoles) {
	                equiposMasGoleadores.add(equipo);
	            }

	            if (tarjetasAmarillas > maxTarjetasAmarillas) {
	                maxTarjetasAmarillas = tarjetasAmarillas;
	                equiposMasTarjetasAmarillas.clear();
	                equiposMasTarjetasAmarillas.add(equipo);
	            } else if (tarjetasAmarillas == maxTarjetasAmarillas) {
	                equiposMasTarjetasAmarillas.add(equipo);
	            }

	            if (tarjetasRojas > maxTarjetasRojas) {
	                maxTarjetasRojas = tarjetasRojas;
	                equiposMasTarjetasRojas.clear();
	                equiposMasTarjetasRojas.add(equipo);
	            } else if (tarjetasRojas == maxTarjetasRojas) {
	                equiposMasTarjetasRojas.add(equipo);
	            }

	            if (tirosAlArco > maxTirosAlArco) {
	                maxTirosAlArco = tirosAlArco;
	                equiposMasTirosAlArco.clear();
	                equiposMasTirosAlArco.add(equipo);
	            } else if (tirosAlArco == maxTirosAlArco) {
	                equiposMasTirosAlArco.add(equipo);
	            }

	            if (corners > maxCorners) {
	                maxCorners = corners;
	                equiposMasCorners.clear();
	                equiposMasCorners.add(equipo);
	            } else if (corners == maxCorners) {
	                equiposMasCorners.add(equipo);
	            }
	        }

	        estadisticas = "El equipo más goleador del torneo es: ";
	        if (equiposMasGoleadores.size() == 1) {
	            estadisticas += equiposMasGoleadores.get(0).getNombre() + " con un total de " + maxGoles + " gol/es.\n";
	        } else {
	            estadisticas += "hay empate entre:\n";
	            for (Equipo equipo : equiposMasGoleadores) {
	                estadisticas += equipo.getNombre() + "\n";
	            }
	            estadisticas += "Con un total de " + maxGoles + " gol/es.\n";
	        }

	        estadisticas += "\nEl equipo con más tarjetas amarillas es: ";
	        if (equiposMasTarjetasAmarillas.size() == 1) {
	            estadisticas += equiposMasTarjetasAmarillas.get(0).getNombre() + " con un total de " + maxTarjetasAmarillas + " tarjetas amarillas.\n";
	        } else {
	            estadisticas += "hay empate entre:\n";
	            for (Equipo equipo : equiposMasTarjetasAmarillas) {
	                estadisticas += equipo.getNombre() + "\n";
	            }
	            estadisticas += "Con un total de " + maxTarjetasAmarillas + " tarjetas amarillas.\n";
	        }

	        estadisticas += "\nEl equipo con más tarjetas rojas es: ";
	        if (equiposMasTarjetasRojas.size() == 1) {
	            estadisticas += equiposMasTarjetasRojas.get(0).getNombre() + " con un total de " + maxTarjetasRojas + " tarjetas rojas.\n";
	        } else {
	            estadisticas += "hay empate entre:\n";
	            for (Equipo equipo : equiposMasTarjetasRojas) {
	                estadisticas += equipo.getNombre() + "\n";
	            }
	            estadisticas += "Con un total de " + maxTarjetasRojas + " tarjetas rojas.\n";
	        }

	        estadisticas += "\nEl equipo con más tiros al arco es: ";
	        if (equiposMasTirosAlArco.size() == 1) {
	            estadisticas += equiposMasTirosAlArco.get(0).getNombre() + " con un total de " + maxTirosAlArco + " tiros al arco.\n";
	        } else {
	            estadisticas += "hay empate entre:\n";
	            for (Equipo equipo : equiposMasTirosAlArco) {
	                estadisticas += equipo.getNombre() + "\n";
	            }
	            estadisticas += "Con un total de " + maxTirosAlArco + " tiros al arco.\n";
	        }

	        estadisticas += "\nEl equipo con más corners es: ";
	        if (equiposMasCorners.size() == 1) {
	            estadisticas += equiposMasCorners.get(0).getNombre() + " con un total de " + maxCorners + " corners.\n";
	        } else {
	            estadisticas += "hay empate entre:\n";
	            for (Equipo equipo : equiposMasCorners) {
	                estadisticas += equipo.getNombre() + "\n";
	            }
	            estadisticas += "Con un total de " + maxCorners + " corners.\n";
	        }

	        JOptionPane.showMessageDialog(null, estadisticas);
	    }
	}
	private int contarGolesEquipo(Equipo equipo) {
	    int totalGoles = 0;

	    for (Partido partido : Partidos) {
	        if (partido.getEquipoLocal().equals(equipo)) {
	            totalGoles += partido.getGolLocal();
	        } else if (partido.getEquipoVisitante().equals(equipo)) {
	            totalGoles += partido.getGolVisitante();
	        }
	    }

	    return totalGoles;
	}
	
	private int contarTarjetasAmarillasEquipo(Equipo equipo) {
	    int totalTarjetasAmarillas = 0;

	    for (Partido partido : Partidos) {
	        if (partido.getEquipoLocal().equals(equipo)) {
	            totalTarjetasAmarillas += partido.getTarjetaAmarillaLocal();
	        } else if (partido.getEquipoVisitante().equals(equipo)) {
	            totalTarjetasAmarillas += partido.getTarjetaAmarillaVisitante();
	        }
	    }

	    return totalTarjetasAmarillas;
	}

	private int contarTarjetasRojasEquipo(Equipo equipo) {
	    int totalTarjetasRojas = 0;

	    for (Partido partido : Partidos) {
	        if (partido.getEquipoLocal().equals(equipo)) {
	            totalTarjetasRojas += partido.getTarjetaRojaLocal();
	        } else if (partido.getEquipoVisitante().equals(equipo)) {
	            totalTarjetasRojas += partido.getTarjetaRojaVisitante();
	        }
	    }

	    return totalTarjetasRojas;
	}

	private int contarTirosAlArcoEquipo(Equipo equipo) {
	    int totalTirosAlArco = 0;

	    for (Partido partido : Partidos) {
	        if (partido.getEquipoLocal().equals(equipo)) {
	            totalTirosAlArco += partido.getTiroAlArcoLocal();
	        } else if (partido.getEquipoVisitante().equals(equipo)) {
	            totalTirosAlArco += partido.getTiroAlArcoVisitante();
	        }
	    }

	    return totalTirosAlArco;
	}

	private int contarCornersEquipo(Equipo equipo) {
	    int totalCorners = 0;

	    for (Partido partido : Partidos) {
	        if (partido.getEquipoLocal().equals(equipo)) {
	            totalCorners += partido.getCornerLocal();
	        } else if (partido.getEquipoVisitante().equals(equipo)) {
	            totalCorners += partido.getCornerVisitante();
	        }
	    }

	    return totalCorners;
	}
}
