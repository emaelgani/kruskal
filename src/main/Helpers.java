package main;

import java.util.Random;


import grafo.Grafo;

public class Helpers {

	public static boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}

	// Numero aleatorio entre 0 y 1
	public static Float getNumeroAleatorio() {
		Random random = new Random();
		return random.nextFloat();
	}

	public static Grafo grafoAleatorio(int vertices, int aristas) {

		int cont = aristas;
		Grafo grafo = new Grafo(vertices);
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {

				if (cont == 0) {
					return grafo;
				}

				if (Helpers.getRandomBoolean() && cont >= 1 && j != i) {
					Float peso = Helpers.getNumeroAleatorio();
					//System.out.println("Se agrega la arista: " + i + "-" + j +", de peso; "+peso);
					grafo.agregarArista(i, j, peso);
					cont--;
				}
			}
		}

		return grafo;

	}

	public static void verificarDatos(int v, int a) {

		int vertices = v;
		int aristas = a;

		if (vertices <= 0) {
			throw new RuntimeException("La cantidad de vertices no puede ser menor o igual que 0");
		}

		if (aristas <= 0) {
			throw new RuntimeException("La cantidad de aristas no puede ser menor o igual que 0");
		}

		int aristasMaxima = (vertices * (vertices - 1) / 2);
		if (aristas > aristasMaxima) {
			throw new RuntimeException(
					"Un grafo con :" + vertices + " vertices, no puede tener más de: " + aristasMaxima + "aristas");
		}

	}
}