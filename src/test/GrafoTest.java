package test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import grafo.Grafo;

public class GrafoTest 
{
	
	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaVerticeIgual()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 0, 1F);
	}

	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoSuperior()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(5, 0, 1F);
	}

	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoSuperiorJ()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 5, 1F);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoInferior()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(-1, 0, 1F);
	}

	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoInferiorJ()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, -1, 1F);
	}

	@Test
	public void agregarAristaTest() 
	{
		Grafo g = new Grafo(5); // Setup
		g.agregarArista(0, 1, 1F); // Exercise
		assertTrue(g.existeArista(0, 1)); // Verify
	}
	
	@Test
	public void agregarAristaSimetriaTest() 
	{
		Grafo g = new Grafo(5); // Setup
		g.agregarArista(0, 1, 1F); // Exercise
		assertTrue(g.existeArista(1, 0)); // Verify
	}
	
	@Test
	public void eliminarAristaTest() 
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1, 1F);
		g.eliminarArista(0, 1);
		assertFalse(g.existeArista(0, 1));
	}
	
	@Test
	public void gradoTest()
	{
		Grafo g = grafoEjemplo();
		assertEquals(2, g.grado(1));
	}

	@Test
	public void gradoCeroTest()
	{
		Grafo g = grafoEjemplo();
		assertEquals(0, g.grado(4));
	}
	
	@Test
	public void vecinoTest()
	{
		Grafo g = new Grafo(4);
		g.agregarArista(0, 1, 2F);
		g.agregarArista(0, 2, 4F);
		g.agregarArista(0, 3, 6F);
		
		HashSet<Integer> vecinos = (HashSet<Integer>)g.vecinos(0);
		
		assertEquals(3, vecinos.size());
		assertTrue(g.vecinos(0).contains(1));
		assertTrue(g.vecinos(0).contains(2));
		assertTrue(g.vecinos(0).contains(3));
	}
	
	private Grafo grafoEjemplo()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1, 3F);
		g.agregarArista(1, 2, 9F);
		g.agregarArista(2, 3, 4F);
		g.agregarArista(3, 0, 1F);
		return g;
	}
	

}
