package tpfinal;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		GestorEquipo admin = new GestorEquipo();
		admin.cargarEquipos();
		admin.cargarJugadores();
		int opcion, opcion2, opcion3, opcion4, opcion5, opcion6, opcion7;
		do {
			String[] opciones6 = { "Usuario", "Administrador", "Salir" };
			opcion6 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
					"Selecciona una opción", 0, 0, null, opciones6, opciones6[0]);
			switch (opcion6) {
			case 0:

				do {
					String[] opciones5 = { "Crear torneo", "Ver torneos", "Salir" };
					opcion5 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
							"Selecciona una opción", 0, 0, null, opciones5, opciones5[0]);
					switch (opcion5) {
					case 0:
						String nombre="";
                        do {
                        nombre = JOptionPane.showInputDialog("Ingrese nombre del torneo");
                        } while (!ValidarNombre(nombre));
						Torneo nuevoTorneo = new Torneo(nombre);
						admin.getTorneos().add(nuevoTorneo);
						nuevoTorneo.SeleccionEquiposTorneo(admin);
						nuevoTorneo.SorteoPartidos();
						break;

					case 1:
						if (admin.getTorneos().isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay torneos en la lista.");
						} else {
							Torneo seleccionado2 = admin.SeleccionarTorneo();
							do {
								String fase="";
								if (seleccionado2.getPartidos().size()==4) {
									fase="Simular CUARTOS";
								} else if (seleccionado2.getPartidos().size()==6) {
									fase="Simular SEMIFINAL";
								} else if (seleccionado2.getCampeon()!=null) {
									fase="Ver CAMPEON";
								} else {
									fase="Simular FINAL";
								}
								String[] opciones4 = { "Ver equipos del torneo", "Ver llaves", fase,
										"Ver partidos jugados","Apuestas","Estadisticas del torneo", "Salir" };
								opcion4 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
										"Selecciona una opción", 0, 0, null, opciones4, opciones4[0]);
								switch (opcion4) {
								case 0:
									seleccionado2.VerListaEquiposTorneo();
									break;
								case 1:
									seleccionado2.VerLlave();
									break;
								case 2:
									seleccionado2.SimularPartidos();
									break;
								case 3:
								    Partido seleccionado3 = seleccionado2.SeleccionarPartido();
								    if (seleccionado3 != null) {
								        seleccionado3.verEstadisticaPartido();
								    } 
								    break;
								case 4:
									do {
										String[] opciones7 = { "Apostar", "Ver Apuestas", "Salir" };
										opcion7 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
												"Selecciona una opción", 0, 0, null, opciones7, opciones7[0]);
										switch (opcion7) {
										case 0:
										    Partido seleccionado4 = seleccionado2.SeleccionarPartidoPorJugar();
										    if (seleccionado4 != null) {
										    	seleccionado2.Apostar(seleccionado4);
										    } 
											break;
										case 1:
											seleccionado2.BuscarApuesta();
											break;

										default:
											break;
										}
									} while (opcion7!=2);
								    break;
								case 5:
										seleccionado2.verEstadisticas();
								    break;
								default:
									break;
								}
							} while (opcion4 != 6);
						}

						break;

					default:
						break;
					}
				} while (opcion5 != 2);

				break;
			case 1:
				do {
					String[] opciones = { "Equipos", "Jugadores", "Salir" };
					opcion = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
							"Selecciona una opción", 0, 0, null, opciones, opciones[0]);
					switch (opcion) {
					case 0:
						do {
							String[] opciones3 = { "Agregar equipo", "Eliminar equipo", "Buscar equipo",
									"Ver cantidad de equipos", "Ver lista de equipos", "Salir" };
							opcion3 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
									"Selecciona una opción", 0, 0, null, opciones3, opciones3[0]);
							switch (opcion3) {
							case 0:
								admin.AgregarEquipo();
								break;
							case 1:
								admin.EliminarEquipo();
								break;
							case 2:
								admin.BuscarEquipo();
								break;
							case 3:
								admin.VerCantidadEquipos();
								break;
							case 4:
								admin.VerListaEquipos();
								break;

							default:
								break;
							}
						} while (opcion3 != 5);
						break;
					case 1:
						Equipo seleccionado = admin.SeleccionarEquipo();
						do {
							String[] opciones2 = { "Agregar jugador", "Eliminar jugador", "Buscar jugador",
									"Ver cantidad de jugadores", "Ver lista de jugadores", "Salir" };
							opcion2 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
									"Selecciona una opción", 0, 0, null, opciones2, opciones2[0]);
							switch (opcion2) {
							case 0:
								seleccionado.AgregarJugador();
								break;
							case 1:
								seleccionado.EliminarJugador();
								break;
							case 2:
								seleccionado.BuscarJugador();
								break;
							case 3:
								seleccionado.VerCantidadJugadores();
								break;
							case 4:
								seleccionado.VerListaJugadores();
								break;

							default:
								break;
							}
						} while (opcion2 != 5);
						break;
					default:
						break;
					}
				} while (opcion != 2);
				break;

			default:
				break;
			}
		} while (opcion6!=2);
		
	}
	public static boolean ValidarNombre(String caracteres) {
        boolean valido = true;
        if (!caracteres.isEmpty()) {
            for (int i = 0; i < caracteres.length(); i++) {
                if (!Character.isLetter(caracteres.charAt(i))) {
                    JOptionPane.showMessageDialog(null,
                            "No es una letra " + caracteres.charAt(i) + "\nIngresalo de nuevo");

                    valido = false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error, no puedes dejar un campo vacío");
            valido = false;
        }
        return valido;
    }
}
