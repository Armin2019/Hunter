package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bala {
	private double x;
	private double y;
	private double angulo;
	double radio;
	private double velocidad;
	private boolean estado;
	
	public Image imgBala;

	public Bala(double x, double y, double angulo, double radio) {
		this.x= x;
		this.y = y;
		this.angulo = angulo;
		this.radio = radio;
		this.velocidad = 6;
		this.estado = true;
		imgBala = Herramientas.cargarImagen("Bala.png");
	}

	public void moverAdelante() {
		this.x += velocidad * Math.cos(this.angulo);
		this.y += velocidad * Math.sin(this.angulo);
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imgBala, x, y, angulo);
		// e.dibujarCirculo(x, y, 2 * radio, Color.CYAN);
	}

	public boolean colisionCon(Edificio[] edificios) {
		for (int i = 0; i < edificios.length; i++) {
			if (edificios[i] != null) {
				double px = x;
				double py = y;
				if (px < edificios[i].getX() - edificios[i].getAncho() / 2) {
					px = edificios[i].getX();
				}
				if (px > edificios[i].getX() - edificios[i].getAncho() / 2 + edificios[i].getAncho()) {
					px = edificios[i].getX() - edificios[i].getAncho() / 2 + edificios[i].getAncho();
				}
				if (py < edificios[i].getY() - edificios[i].getAlto() / 2) {
					py = edificios[i].getY() - edificios[i].getAlto() / 2;
				}
				if (py > edificios[i].getY() - edificios[i].getAlto() / 2 + edificios[i].getAlto()) {
					py = edificios[i].getY() - edificios[i].getAlto() / 2 + edificios[i].getAlto();
				}
				double distancia = Math.sqrt((x - px) * (x - px) + (y - py) * (y - py));
				if (distancia < radio) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {

		this.estado = estado;
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

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	// TODO Auto-generated method stub

}
