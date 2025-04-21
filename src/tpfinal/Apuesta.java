package tpfinal;


	public class Apuesta {
	private Partido partido;
    private String equipoSeleccionado;
    private String estado;
    private double monto;
    
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Partido getPartido() {
		return partido;
	}
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	public String getEquipoSeleccionado() {
		return equipoSeleccionado;
	}
	public void setEquipoSeleccionado(String equipoSeleccionado) {
		this.equipoSeleccionado = equipoSeleccionado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

    
	}
	
	

