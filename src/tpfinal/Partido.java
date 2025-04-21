package tpfinal;

import javax.swing.JOptionPane;

public class Partido {
	private Equipo equipoLocal, equipoVisitante;
	private int golLocal;
	private int golVisitante;
	private int golPenalLocal;
	private int golPenalVisitante;
	private int tiroAlArcoLocal;
	private int tiroAlArcoVisitante;
	private int posesionLocal;
	private int posesionVisitante;
	private int cornerLocal;
	private int cornerVisitante;
	private int tarjetaAmarillaLocal;
	private int tarjetaAmarillaVisitante;
	private int tarjetaRojaLocal;
	private int tarjetaRojaVisitante;
	private boolean jugado = false;

	public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
		super();
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
	}

	public boolean isJugado() {
		return jugado;
	}

	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}



	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public int getGolLocal() {
		return golLocal;
	}

	public void setGolLocal(int golLocal) {
		this.golLocal = golLocal;
	}

	public int getGolVisitante() {
		return golVisitante;
	}

	public void setGolVisitante(int golVisitante) {
		this.golVisitante = golVisitante;
	}

	public int getGolPenalLocal() {
		return golPenalLocal;
	}

	public void setGolPenalLocal(int golPenalLocal) {
		this.golPenalLocal = golPenalLocal;
	}

	public int getGolPenalVisitante() {
		return golPenalVisitante;
	}

	public void setGolPenalVisitante(int golPenalVisitante) {
		this.golPenalVisitante = golPenalVisitante;
	}

	public int getTiroAlArcoLocal() {
		return tiroAlArcoLocal;
	}

	public void setTiroAlArcoLocal(int tiroAlArcoLocal) {
		this.tiroAlArcoLocal = tiroAlArcoLocal;
	}

	public int getTiroAlArcoVisitante() {
		return tiroAlArcoVisitante;
	}

	public void setTiroAlArcoVisitante(int tiroAlArcoVisitante) {
		this.tiroAlArcoVisitante = tiroAlArcoVisitante;
	}

	public int getPosesionLocal() {
		return posesionLocal;
	}

	public void setPosesionLocal(int posesionLocal) {
		this.posesionLocal = posesionLocal;
	}

	public int getPosesionVisitante() {
		return posesionVisitante;
	}

	public void setPosesionVisitante(int posesionVisitante) {
		this.posesionVisitante = posesionVisitante;
	}

	public int getCornerLocal() {
		return cornerLocal;
	}

	public void setCornerLocal(int cornerLocal) {
		this.cornerLocal = cornerLocal;
	}

	public int getCornerVisitante() {
		return cornerVisitante;
	}

	public void setCornerVisitante(int cornerVisitante) {
		this.cornerVisitante = cornerVisitante;
	}

	public int getTarjetaAmarillaLocal() {
		return tarjetaAmarillaLocal;
	}

	public void setTarjetaAmarillaLocal(int tarjetaAmarillaLocal) {
		this.tarjetaAmarillaLocal = tarjetaAmarillaLocal;
	}

	public int getTarjetaAmarillaVisitante() {
		return tarjetaAmarillaVisitante;
	}

	public void setTarjetaAmarillaVisitante(int tarjetaAmarillaVisitante) {
		this.tarjetaAmarillaVisitante = tarjetaAmarillaVisitante;
	}

	public int getTarjetaRojaLocal() {
		return tarjetaRojaLocal;
	}

	public void setTarjetaRojaLocal(int tarjetaRojaLocal) {
		this.tarjetaRojaLocal = tarjetaRojaLocal;
	}

	public int getTarjetaRojaVisitante() {
		return tarjetaRojaVisitante;
	}

	public void setTarjetaRojaVisitante(int tarjetaRojaVisitante) {
		this.tarjetaRojaVisitante = tarjetaRojaVisitante;
	}

	public void verEstadisticaPartido() {
		String mensaje = "";
		mensaje = "----------- ESTADISTICAS DEL PARTIDO -----------\n" 
				+ this.getEquipoLocal().getNombre() + "   Equipo   " + this.getEquipoVisitante().getNombre()+ "\n--------" 
				+ this.getGolLocal() + "-----------Goles--------------------" + this.getGolVisitante()+ "\n--------" ;
		if (this.getGolPenalLocal() > 1 || this.getGolPenalVisitante() > 1) {
			mensaje += this.getGolPenalLocal()
					+ "-----------Goles de PENAL-----" + this.getGolPenalVisitante()+ "\n--------" ;
		}
		mensaje +=  this.getTiroAlArcoLocal() + "-----------Tiros al arco----------"
				+ this.getTiroAlArcoVisitante() + "\n--------" + this.getCornerLocal()
				+ "-----------Tiros de esquina----" + this.getCornerVisitante() + "\n--------"
				+ this.getPosesionLocal() + "%-------" + "Posesión--------------" + this.getPosesionVisitante() + "%"
				+ "\n--------" + this.getTarjetaAmarillaLocal()
				+ "-----------Tarjeta amarilla-----" + this.getTarjetaAmarillaVisitante()
				+ "\n--------" + this.getTarjetaRojaLocal() + "-----------Tarjeta roja-----------"
				+ this.getTarjetaRojaVisitante();

		JOptionPane.showMessageDialog(null, mensaje);
	}
	

}
