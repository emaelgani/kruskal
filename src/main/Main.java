package main;

import grafo.*;


import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int vertices = 0;
		int aristas = 0;
		Grafo grafoAleatorio;
		
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Ingrese la cantidad de vertices del grafo: ");
			vertices = sc.nextInt();

			System.out.println("Ingrese la cantidad de aristas del grafo: ");
			aristas = sc.nextInt();

			Helpers.verificarDatos(vertices, aristas);
			
			grafoAleatorio = Helpers.grafoAleatorio(vertices, aristas);
			
			while (!BFS.esConexo(grafoAleatorio)) {
				System.out.println("El grafo generado no es conexo, intente de nuevo");
				
				System.out.println("Ingrese la cantidad de vertices del grafo: ");
				vertices = sc.nextInt();

				System.out.println("Ingrese la cantidad de aristas del grafo: ");
				aristas = sc.nextInt();

				Helpers.verificarDatos(vertices, aristas);
				
				grafoAleatorio = Helpers.grafoAleatorio(vertices, aristas);

				System.out.println("grafo no conexo!");

			}
		}

		System.out.println("El Grafo generado es conexo!");
		
		
		System.out.print("El tiempo que tarda con Kruskal implementado con BFS es: ");
		long startTime = System.nanoTime();
		Kruskal krs = new Kruskal(grafoAleatorio);
		krs.arbolMinimoBFS();
		long endTime = System.nanoTime(); System.out.println( + (endTime-startTime)/1e6 + " ms");
	
		System.out.print("El tiempo que tarda con Kruskal implementado con Union-Find es: ");
		long startTime2 = System.nanoTime();
		
		krs.arbolMinimoUnionFind();
		long endTime2 = System.nanoTime(); System.out.println( + (endTime2-startTime2)/1e6 + " ms");
		
		
		
	}

}
