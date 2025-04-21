package tpfinal;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GestorEquipo {
	private LinkedList<Equipo> Equipos = new LinkedList<Equipo>();
	private LinkedList<Torneo> Torneos = new LinkedList<Torneo>();

	public LinkedList<Equipo> getEquipos() {
		return Equipos;
	}

	public void setEquipos(LinkedList<Equipo> equipos) {
		Equipos = equipos;
	}

	public LinkedList<Torneo> getTorneos() {
		return Torneos;
	}

	public void setTorneos(LinkedList<Torneo> torneos) {
		Torneos = torneos;
	}

	public Equipo SeleccionarEquipo() {
		String[] equipos = new String[this.getEquipos().size()];

		for (int i = 0; i < this.getEquipos().size(); i++) {
			equipos[i] = this.getEquipos().get(i).getNombre();
		}
		String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione el equipo del jugador", null, 0, null,
				equipos, equipos[0]);
		for (int i = 0; i < this.getEquipos().size(); i++) {
			if (this.getEquipos().get(i).getNombre().equals(seleccion)) {
				return this.getEquipos().get(i);
			}
		}
		return null;
	}

	public Torneo SeleccionarTorneo() {
		String[] torneos = new String[this.getTorneos().size()];

		for (int i = 0; i < this.getTorneos().size(); i++) {
			torneos[i] = this.getTorneos().get(i).getNombre();
		}
		String seleccion2 = (String) JOptionPane.showInputDialog(null, "Seleccione el torneo", null, 0, null, torneos,
				torneos[0]);
		for (int i = 0; i < this.getTorneos().size(); i++) {
			if (this.getTorneos().get(i).getNombre().equals(seleccion2)) {
				return this.getTorneos().get(i);
			}
		}
		return null;

	}

	public void AgregarEquipo() {
        String nombre, ciudad, repeticion;
        do {
            repeticion="no";
            do {
            nombre = JOptionPane.showInputDialog("Ingrese nombre del equipo");
            } while (!ValidarNombre(nombre));
            for (Equipo equipo : Equipos) {
                if (equipo.getNombre().equals(nombre)) {
                    JOptionPane.showMessageDialog(null, "Ya existe un equipo con ese nombre, ingrese otro.");
                    repeticion="si";
                    break;
                }

            }
        } while (repeticion.equals("si"));
        do {
        ciudad = JOptionPane.showInputDialog("Ingrese la ciudad del equipo");
        } while (!ValidarNombre(ciudad));
        Equipos.add(new Equipo(nombre, ciudad,"predeterminado.jpg"));
    }

	public void EliminarEquipo() {
		if (Equipos.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay equipos, por lo tanto no podras eliminar ninguno");
		} else {
			String[] equipos = new String[Equipos.size()];
			for (int i = 0; i < Equipos.size(); i++) {
				equipos[i] = Equipos.get(i).getNombre();
			}
			String equiposelect = (String) JOptionPane.showInputDialog(null, "", null, 1, null, equipos, equipos[0]);
			for (Equipo equipo : Equipos) {
				if (equipo.getNombre() == equiposelect) {

					Equipos.remove(Equipos.indexOf(equipo));
					JOptionPane.showMessageDialog(null, "Equipo eliminado con éxito");
					break;
				}
			}
		}
	}

	public void BuscarEquipo() {
		if (Equipos.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay equipos, por lo tanto no podras buscar ninguno");
		} else {
			String[] equipos = new String[Equipos.size()];
			for (int i = 0; i < Equipos.size(); i++) {
				equipos[i] = Equipos.get(i).getNombre();
			}
			String equiposelect = (String) JOptionPane.showInputDialog(null, "", null, 1, null, equipos, equipos[0]);
			for (Equipo equipo : Equipos) {
				if (equipo.getNombre() == equiposelect) {
					JOptionPane.showMessageDialog(null,
							"Equipo seleccionado: \n-Nombre: " + equipo.getNombre() + "\n-Ciudad: "
									+ equipo.getCiudad(),
							"seleccion", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(GestorEquipo.class.getResource("/img/" + equipo.getFoto())));
					break;
				}
			}
		}
	}

	public void VerCantidadEquipos() {
		JOptionPane.showMessageDialog(null, "La cantidad de equipos de la lista es " + Equipos.size());
	}

	public void VerListaEquipos() {
		String[] detalles = new String[Equipos.size()];
		for (int i = 0; i < Equipos.size(); i++) {
			detalles[i] = "-Nombre: " + Equipos.get(i).getNombre() + "    -Ciudad: " + Equipos.get(i).getCiudad()
					+ "\n\n";
		}
		JOptionPane.showMessageDialog(null, detalles);
	}

	public void cargarEquipos() {
		this.getEquipos().add(new Equipo("Inter", "Milan, Italia", "Inter.png"));
		this.getEquipos().add(new Equipo("Chelsea", "Londres, Inglaterra", "Chelsea.png"));
		this.getEquipos().add(new Equipo("Barcelona", "Barcelona, España", "Barcelona.png"));
		this.getEquipos().add(new Equipo("Real Madrid", "Madrid, España", "Real.png"));
		this.getEquipos().add(new Equipo("Manchester City", "Londres, Inglaterra", "City.png"));
		this.getEquipos().add(new Equipo("Manchester United", "Londres, Inglaterra", "United.png"));
		this.getEquipos().add(new Equipo("Arsenal", "Londres, Inglaterra", "Arsenal.jpg"));
		this.getEquipos().add(new Equipo("Bayern Munchen", "Munchen, Alemania", "Bayern.png"));
		this.getEquipos().add(new Equipo("Borussia Dortmund", "Dortmund, Alemania", "Dortmund.png"));
		this.getEquipos().add(new Equipo("Napoli FC", "Napoles, Italia", "Napoli.png"));
		this.getEquipos().add(new Equipo("Juventus", "Turin, Italia", "Juventus.png"));
	}

	public void cargarJugadores() {

		// Inter de Milan
		this.getEquipos().get(0).getJugadores().add(new Jugador("Lautaro Martínez", 22, "Delantero", 23));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Edin Džeko", 9, "Delantero", 36));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Alessandro Bastoni", 3, "Defensa", 23));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Milan Škriniar", 37, "Defensa", 27));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Stefan de Vrij", 6, "Defensa", 30));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Nicolò Barella", 23, "Centrocampista", 25));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Marcelo Brozović", 77, "Centrocampista", 29));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Hakan Çalhanoğlu", 20, "Centrocampista", 28));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Denzel Dumfries", 2, "Extremo", 26));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Ivan Perišić", 4, "Extremo", 33));
		this.getEquipos().get(0).getJugadores().add(new Jugador("Andre Onana", 1, "Arquero", 26));
		// Chelsea
		this.getEquipos().get(1).getJugadores().add(new Jugador("Reece James", 2, "Lateral derecho", 23));
		this.getEquipos().get(1).getJugadores().add(new Jugador("César Azpilicueta", 3, "Defensa central", 33));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Andreas Christensen", 15, "Defensa central", 26));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Nuno Mendes", 18, "Lateral izquierdo", 20));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Mykhailo Mudryk", 10, "Extremo izquierdo", 21));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Carney Chukwuemeka", 17, "Centrocampista", 19));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Cole Palmer", 20, "Mediapunta", 21));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Raheem Sterling", 7, "Segundo delantero", 28));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Marcus Bettinelli", 13, "Arquero", 31));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Lucas Bergström", 47, "Delantero", 21));
		this.getEquipos().get(1).getJugadores().add(new Jugador("Enzo Fernandez", 8, "Centrocampista", 22));
		// Barcelona
		this.getEquipos().get(2).getJugadores().add(new Jugador("Marc-André ter Stegen", 1, "Arquero", 31));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Alejandro Balde", 3, "Lateral", 18));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Ronald Araujo", 4, "Defensa central", 23));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Íñigo Martínez", 5, "Defensa central", 32));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Andreas Christensen", 15, "Defensa central", 26));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Marcos Alonso", 17, "Lateral", 31));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Sergi Roberto", 20, "Lateral", 30));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Jules Koundé", 23, "Defensa central", 23));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Ferran Torres", 7, "Mediapunta", 23));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Robert Lewandowski", 9, "Delantero centro", 34));
		this.getEquipos().get(2).getJugadores().add(new Jugador("Raphinha", 11, "Extremo", 27));
		// Real Madrid
		this.getEquipos().get(3).getJugadores().add(new Jugador("Thibaut Courtois", 1, "Arquero", 30));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Dani Carvajal", 2, "Lateral derecho", 29));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Eder Militão", 3, "Defensa central", 27));
		this.getEquipos().get(3).getJugadores().add(new Jugador("David Alaba", 4, "Defensa central", 29));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Ferland Mendy", 23, "Lateral izquierdo", 27));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Eduardo Camavinga", 12, "Centrocampista", 19));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Aurélien Tchouaméni", 18, "Centrocampista", 22));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Luka Modric", 10, "Mediapunta", 37));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Karim Benzema", 9, "Delantero centro", 35));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Álvaro Odriozola", 17, "Lateral derecho", 27));
		this.getEquipos().get(3).getJugadores().add(new Jugador("Lucas Vázquez", 21, "Extremo", 31));
		// Manchester City
		this.getEquipos().get(4).getJugadores().add(new Jugador("Ederson Moraes", 1, "Arquero", 29));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Kyle Walker", 2, "Lateral derecho", 32));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Ruben Dias", 3, "Defensa central", 24));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Aymeric Laporte", 5, "Defensa central", 28));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Joao Cancelo", 27, "Lateral izquierdo", 28));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Rodri", 16, "Centrocampista", 26));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Kevin De Bruyne", 17, "Centrocampista", 30));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Bernardo Silva", 20, "Mediapunta", 28));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Jack Grealish", 7, "Extremo", 27));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Erling Haaland", 9, "Delantero centro", 22));
		this.getEquipos().get(4).getJugadores().add(new Jugador("Julian Alvarez", 19, "Delantero centro", 23));
		// Manchester United
		this.getEquipos().get(5).getJugadores().add(new Jugador("David de Gea", 1, "Arquero", 31));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Diogo Dalot", 2, "Lateral derecho", 23));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Victor Lindelöf", 3, "Defensa central", 27));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Harry Maguire", 5, "Defensa central", 29));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Luke Shaw", 23, "Lateral izquierdo", 27));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Bruno Fernandes", 18, "Centrocampista", 28));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Nemanja Matic", 21, "Centrocampista", 33));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Marcus Rashford", 10, "Extremo", 25));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Jadon Sancho", 7, "Extremo", 22));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Tom Heaton", 22, "Delantero", 37));
		this.getEquipos().get(5).getJugadores().add(new Jugador("Andre Onana", 24, "Delantero", 27));
		// Arsenal
		this.getEquipos().get(6).getJugadores().add(new Jugador("Aaron Ramsdale", 1, "Arquero", 23));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Takehiro Tomiyasu", 2, "Lateral derecho", 23));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Ben White", 4, "Defensa central", 24));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Gabriel Magalhães", 6, "Defensa central", 24));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Kieran Tierney", 3, "Lateral izquierdo", 24));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Thomas Partey", 5, "Centrocampista", 29));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Martin Ødegaard", 8, "Centrocampista", 23));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Bukayo Saka", 7, "Extremo", 20));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Gabriel Martinelli", 11, "Extremo", 21));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Eddie Nketiah", 14, "Delantero centro", 22));
		this.getEquipos().get(6).getJugadores().add(new Jugador("Karl Hein", 31, "Delantero centro", 21));
		// Bayern
		this.getEquipos().get(7).getJugadores().add(new Jugador("Manuel Neuer", 1, "Arquero", 35));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Benjamin Pavard", 2, "Lateral derecho", 26));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Dayot Upamecano", 3, "Defensa central", 23));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Lucas Hernández", 4, "Defensa central", 27));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Alphonso Davies", 19, "Lateral izquierdo", 21));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Joshua Kimmich", 6, "Centrocampista", 27));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Leon Goretzka", 8, "Centrocampista", 27));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Thomas Müller", 25, "Mediapunta", 33));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Serge Gnabry", 7, "Extremo", 27));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Robert Lewandowski", 9, "Delantero centro", 34));
		this.getEquipos().get(7).getJugadores().add(new Jugador("Jamal Musiala", 42, "Delantero centro", 20));
		// Dortmund
		this.getEquipos().get(8).getJugadores().add(new Jugador("Gregor Kobel", 1, "Arquero", 24));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Mateu Morey", 2, "Lateral derecho", 22));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Nico Schlotterbeck", 4, "Defensa central", 23));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Ramy Bensebaini", 5, "Defensa central", 27));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Mats Hummels", 15, "Defensa central", 33));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Marius Wolf", 17, "Lateral izquierdo", 27));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Jude Bellingham", 22, "Centrocampista", 19));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Marco Reus", 11, "Mediapunta", 33));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Donyell Malen", 21, "Delantero centro", 24));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Sébastien Haller", 9, "Delantero centro", 28));
		this.getEquipos().get(8).getJugadores().add(new Jugador("Youssoufa Moukoko", 18, "Delantero centro", 19));
		// Napoli
		this.getEquipos().get(9).getJugadores().add(new Jugador("David Ospina", 15, "Delantero", 34));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Alex Meret", 25, "Arquero", 25));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Kalidou Koulibaly", 2, "Defensa central", 30));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Juan Jesus", 5, "Defensa central", 34));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Mario Rui", 6, "Lateral izquierdo", 31));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Giovanni Di Lorenzo", 22, "Lateral derecho", 29));
		this.getEquipos().get(9).getJugadores()
				.add(new Jugador("André-Frank Zambo Anguissa", 18, "Centrocampista", 27));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Diego Demme", 4, "Centrocampista", 28));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Piotr Zielinski", 20, "Centrocampista", 28));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Eljif Elmas", 7, "Centrocampista", 23));
		this.getEquipos().get(9).getJugadores().add(new Jugador("Victor Osimhen", 9, "Delantero centro", 23));
		// juventus
		this.getEquipos().get(10).getJugadores().add(new Jugador("Matthijs de Ligt", 4, "Defensa central", 23));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Giorgio Chiellini", 3, "Defensa central", 38));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Federico Gatti", 24, "Defensa central", 24));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Alex Sandro", 12, "Lateral izquierdo", 32));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Juan Cuadrado", 17, "Lateral derecho", 34));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Fabio Miretti", 20, "Centrocampista", 20));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Manuel Locatelli", 5, "Centrocampista", 24));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Adrien Rabiot", 25, "Centrocampista", 28));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Filip Kostic", 11, "Extremo", 29));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Dusan Vlahovic", 9, "Delantero centro", 23));
		this.getEquipos().get(10).getJugadores().add(new Jugador("Wojciech Szczęsny", 1, "Arquero", 32));
	}
	public boolean ValidarNombre(String caracteres) {
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
