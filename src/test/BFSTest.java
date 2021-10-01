package test;

import static org.junit.Assert.*;

import java.util.Set;

//import java.util.Set;

import org.junit.Test;

import grafo.BFS;
import grafo.Grafo;

public class BFSTest 
{
	@Test (expected=IllegalArgumentException.class)
	public void testNull() 
	{
		BFS.esConexo(null);
	}
	
	@Test
	public void testVacio()
	{
		Grafo g = new Grafo(0);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void testNoConexo()
	{
		Grafo g = inicializarEjemplo();
		assertFalse(BFS.esConexo(g));
	}

	
	@Test
	public void testConexo()
	{
		Grafo g = inicializarEjemplo();
		g.agregarArista(3, 4, 4F);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesTest()
	{
		Grafo g = inicializarEjemplo();
		Set<Integer> alcanzables = BFS.alcanzables(g, 0);
		
		int[] esperado = {0, 1, 2, 3};
		Assert.iguales(esperado, alcanzables);
	}
	
	@Test
	public void alcanzablesTodosTest()
	{
		Grafo g = inicializarEjemplo();
		g.agregarArista(3, 4, 45F);
		
		Set<Integer> alcanzables = BFS.alcanzables(g, 0);
		
		int[] esperado = {0, 1, 2, 3, 4};
		Assert.iguales(esperado, alcanzables);
	}
	
	private Grafo inicializarEjemplo() 
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1, 8F);
		g.agregarArista(0, 2, 5F);
		g.agregarArista(2, 3, 3F);
		return g;
	}
	
}
