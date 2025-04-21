package tpfinal;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Equipo {
	private String nombre;
	private String ciudad;
	private String foto;
	private LinkedList<Jugador> jugadores = new LinkedList<Jugador>();

	public Equipo(String nombre, String ciudad, String foto) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.foto=foto;

	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public LinkedList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(LinkedList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void AgregarJugador() {
        String nombre, posicion, repeticion, numeroCamiseta, edad;
        int numeroCamiseta2, edad2;
        do {
        nombre = JOptionPane.showInputDialog("Ingrese nombre del jugador");
        } while (!ValidarNombre(nombre));
        do {
            repeticion="no";
            do {
            numeroCamiseta = JOptionPane.showInputDialog("Ingrese número de camiseta del jugador");
            } while (!ValidarNumero(numeroCamiseta));

            numeroCamiseta2 = Integer.parseInt(numeroCamiseta);
            for (Jugador jugador : jugadores) {
                if (jugador.getNumeroCamiseta() == numeroCamiseta2) {
                    JOptionPane.showMessageDialog(null, "Se repite numero de camiseta, ingrese otro");
                    repeticion="si";
                break;
                }
            }
        } while (repeticion.equals("si"));
        String[] posiciones = { "Arquero", "Delantero", "Mediocampista", "Defensor" };
        posicion = (String) JOptionPane.showInputDialog(null, "Posición", null, 0, null,
                posiciones, posiciones[0]);
        do {
        edad = JOptionPane.showInputDialog("Ingrese edad");
        } while (!ValidarNumero(edad));
        edad2 = Integer.parseInt(edad);
        this.getJugadores().add(new Jugador(nombre, numeroCamiseta2, posicion, edad2));
    }
	public void EliminarJugador() {
		if (this.getJugadores().size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay jugadores en el equipo, por lo tanto no podras eliminar ninguno");
		} else {
			String[] jugadores = new String[this.getJugadores().size()];
			for (int i = 0; i < this.getJugadores().size(); i++) {
				jugadores[i] = Integer.toString(this.getJugadores().get(i).getNumeroCamiseta());
			}
			String jugadorselect = (String) JOptionPane.showInputDialog(null, "", null, 1, null,
					jugadores, jugadores[0]);
			for (Jugador jugador : this.getJugadores()) {
				if (jugador.getNumeroCamiseta() == Integer.parseInt(jugadorselect)) {

					this.getJugadores().remove(this.getJugadores().indexOf(jugador));
					JOptionPane.showMessageDialog(null, "Jugador eliminado con éxito");
				break;
				}
			}
		}
	}
	
	public void BuscarJugador() {
		if (this.getJugadores().size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay jugadores en el equipo, por lo tanto no podras buscar ninguno");
		} else {
			String[] jugadores = new String[this.getJugadores().size()];
			for (int i = 0; i < this.getJugadores().size(); i++) {
				jugadores[i] = this.getJugadores().get(i).getNombre();
			}
			String jugadorselect = (String) JOptionPane.showInputDialog(null, "", null, 1, null,
					jugadores, jugadores[0]);
			for (Jugador jugador : this.getJugadores()) {
				if (jugador.getNombre() == jugadorselect) {
					JOptionPane.showMessageDialog(null, "Jugador seleccionado: \n-Nombre: " + jugador.getNombre() + "\n-Edad: " + jugador.getEdad() + "\n-Posición: " + jugador.getPosicion() + "\n-Número de camiseta: " + jugador.getNumeroCamiseta());
				break;
				}

			} 
		}
	}
	
	public void  VerCantidadJugadores() {
		JOptionPane.showMessageDialog(null, "La cantidad de jugadores del equipo " + this.getNombre() + " es de " + this.getJugadores().size());
	}
	
	public void VerListaJugadores() {
        if (this.getJugadores().size() == 0) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en el equipo, por lo tanto no podrás ver una lista de jugadores.");
        } else {
        String[] detalles = new String[jugadores.size()];
        for (int i = 0; i < this.getJugadores().size(); i++) {
            detalles[i] = "-Nombre: " + this.getJugadores().get(i).getNombre() + " -Edad: " + this.getJugadores().get(i).getEdad() + " -Posición: " + this.getJugadores().get(i).getPosicion() + " -Número de camiseta: " + this.getJugadores().get(i).getNumeroCamiseta() + "\n\n";
        }
        JOptionPane.showMessageDialog(null, detalles);
        }
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

    public boolean ValidarNumero(String numero) {
        boolean valido = true;


        if (!numero.isEmpty()) {
            for (int i = 0; i < numero.length(); i++) {
                if (!Character.isDigit(numero.charAt(i))) {
                    JOptionPane.showMessageDialog(null,
                            "No es un número válido: " + numero.charAt(i) + "\nIngresalo de nuevo");
                    valido = false;
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error, no puedes dejar un campo vacío");
            valido = false;
        }

        return valido;
    }
}
