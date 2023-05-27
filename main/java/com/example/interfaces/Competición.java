package com.example.interfaces;


//Interface para poder aprovechar el strategy pattern
//Se trata de crear una interface para que diferentes clases realicen una acción de diferentes maneras.
public interface Competición {

	public void crearEnfrentamientos();
	public void establecerFechaInicio();
	public void establecerCompetidores();
	public void establecerPuntos();
	public void establecerGanador();
	public void establecerPerdedor();
	public void guardar();
}
