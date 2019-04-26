package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Spider {
	// Variables de instancia
	private double x;
	private double y;
	private double angulo;
	private double radio;
	private double velocidad;
	
	private boolean estadoVida;
	public Image imgSpider;
	

	public Spider(double x, double y,double angulo, double radio, double velocidad) {
		this.x = x;
		this.y = y;
		this.angulo= angulo;
		this.radio =radio;
		this.velocidad = velocidad /7;
		
		this.estadoVida =true;
		imgSpider = Herramientas.cargarImagen("spider.png");
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public boolean chocasteCon(Entorno e) {
		return x <= radio || y <= radio || x >= e.ancho() - radio || y >= e.alto() - radio;
	}

	public boolean colisionCon(Bala[] balas) {
		for (int i = 0; i < balas.length; i++) {
			if (balas[i] != null) {
				double distancia = Math.sqrt(
						(x - balas[i].getX()) * (x - balas[i].getX()) + (y - balas[i].getY()) * (y - balas[i].getY()));
				if (distancia < radio + balas[i].getRadio()) {
					return true;
				}
			}
		}
		return false;

	}

	public boolean colisionCon(Mina[] minas) {
		for (int i = 0; i < minas.length; i++) {
			if (minas[i] != null) {
				double distancia = Math.sqrt(
						(x - minas[i].getX()) * (x - minas[i].getX()) + (y - minas[i].getY()) * (y - minas[i].getY()));
				if (distancia < radio + minas[i].getRadio()) {
					return true;
				}
			}
		}
		return false;
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

	public void cambiarTrayectoria() {
		angulo += Math.PI / 2;
	}

	public void moverAdelante() {
		this.x += Math.cos(this.angulo) * velocidad;

		this.y += Math.sin(this.angulo) * velocidad;
	}

	public void moverAtras() {
		this.x -= Math.cos(this.angulo) * velocidad;

		this.y -= Math.sin(this.angulo) * velocidad;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public boolean isEstadoVida() {
		return estadoVida;
	}

	public void setEstadoVida(boolean estadoVida) {

		this.estadoVida = estadoVida;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imgSpider, x, y, 0, 0.08);

		// e.dibujarCirculo(x, y, radio*2, Color.BLUE);
	}

}
