package modelo;

import java.util.ArrayList;

public class Subtitulos {
	ArrayList<String> listSubtitulos;
	ArrayList<String> listSubResaltados;
	ArrayList<Integer> posicion;
	ArrayList<Integer> tiempoHoras;
	ArrayList<Integer> tiempoMinutos;
	ArrayList<Double> tiempoSegundos;
	ArrayList<Integer> posicionResaltado;
	
	
	public Subtitulos(ArrayList<Integer> posicion, ArrayList<String> listSubtitulos,
			ArrayList<Integer> tiempoHoras, ArrayList<Integer> tiempoMinutos, 
			ArrayList<Double> tiempoSegundos, ArrayList<Integer> posicionResaltado) {
		this.posicion = posicion;
		this.listSubtitulos = listSubtitulos;
		this.tiempoHoras = tiempoHoras;
		this.tiempoMinutos = tiempoMinutos;
		this.tiempoSegundos = tiempoSegundos;
		this.posicionResaltado = posicionResaltado;
	}
	public ArrayList<Integer> getPosicionResaltado() {
		return posicionResaltado;
	}
	public void setPosicionResaltado(ArrayList<Integer> posicionResaltado) {
		this.posicionResaltado = posicionResaltado;
	}
	
	public void setTiempoHoras(ArrayList<Integer> tiempoHoras) {
		this.tiempoHoras = tiempoHoras;
	}
	public ArrayList<Integer> getTiempoHoras() {
		return tiempoHoras;
	}
	public ArrayList<Integer> getPosicion() {
		return posicion;
	}
	public ArrayList<String> getListSubtitulos() {
		return listSubtitulos;
	}
	public ArrayList<Integer> getTiempoMinutos() {
		return tiempoMinutos;
	}
	public ArrayList<Double> getTiempoSegundos() {
		return tiempoSegundos;
	}
	public void setPosicion(ArrayList<Integer> posicion) {
		this.posicion = posicion;
	}
	public void setListSubtitulos(ArrayList<String> listSubtitulos) {
		this.listSubtitulos = listSubtitulos;
	}
	public void setTiempoMinutos(ArrayList<Integer> tiempoMinutos) {
		this.tiempoMinutos = tiempoMinutos;
	}
	public void setTiempoSegundos(ArrayList<Double> tiempoSegundos) {
		this.tiempoSegundos = tiempoSegundos;
	}
}
