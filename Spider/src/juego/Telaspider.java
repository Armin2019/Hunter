package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Telaspider {
	private double x;
	private double y;
	private double radio;
	public Image imgTelas;
	
	public Telaspider(double x, double y, double radio) {
		
		this.x = x;
		this.y = y;
		this.radio = radio;
		imgTelas = Herramientas.cargarImagen("spidertela.png");
	}
	
	public void dibujarTela(Entorno tela) {
		tela.dibujarImagen(imgTelas, x, y, 0, 0.5);
		// tela.dibujarCirculo(x, y, radio*2, Color.GREEN);

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

}
