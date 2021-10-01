package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grafo.Grafo;
import grafo.Kruskal;

public class KruskalTest {
	Grafo arbolMinimoBFS;
	Grafo arbolMinimoUnionFind;
	
	@Before
	public void inicializarArbolMinimoBFS() {
		Grafo g = new Grafo(9);
		g.agregarArista(0, 1, 4F);
		g.agregarArista(0, 7, 8F);
		g.agregarArista(1, 2, 8F);
		g.agregarArista(1, 7, 12F);
		g.agregarArista(7, 6, 1F);
		g.agregarArista(7, 8, 6F);
		g.agregarArista(2, 8, 3F);
		g.agregarArista(2, 3, 6F);
		g.agregarArista(2, 5, 4F);
		g.agregarArista(8, 6, 5F);
		g.agregarArista(3, 4, 9F);
		g.agregarArista(3, 5, 13F);
		g.agregarArista(4, 5, 10F);
		g.agregarArista(6, 5, 3F);
		Kruskal ks = new Kruskal(g);
		arbolMinimoBFS = ks.arbolMinimoBFS();
		arbolMinimoUnionFind = ks.arbolMinimoUnionFind();
	}
	
	@Test
	public void testExisteAristaArbolMinimo1() {
		assertTrue(this.arbolMinimoBFS.existeArista(2, 8));
		assertTrue(this.arbolMinimoUnionFind.existeArista(2, 8));
	}
	
	@Test
	public void testExisteAristaArbolMinimo2() {
		assertTrue(this.arbolMinimoBFS.existeArista(3, 4));
		assertTrue(this.arbolMinimoUnionFind.existeArista(3, 4));
	}
	
	@Test
	public void testNoExisteAristaArbolMinimo3() {
		assertFalse(this.arbolMinimoBFS.existeArista(6, 8));
		assertFalse(this.arbolMinimoUnionFind.existeArista(6, 8));
	}
	
	@Test
	public void testNoExisteAristaArbolMinimo4() {
		assertFalse(this.arbolMinimoBFS.existeArista(7, 8));
		assertFalse(this.arbolMinimoUnionFind.existeArista(7, 8));
	}
	

	//Si no es conexo, arroja exception
	@Test(expected=RuntimeException.class)
	public void testGrafoNoConexoBFS() {
		Grafo g = new Grafo(9);
		g.agregarArista(0, 1, 4F);
		g.agregarArista(0, 7, 8F);
		g.agregarArista(1, 2, 8F);
		g.agregarArista(1, 7, 12F);
		g.agregarArista(6, 5, 1F);
		Kruskal ks = new Kruskal(g);
		ks.arbolMinimoBFS();
	}
	
	//Si no es conexo, arroja exception
		@Test(expected=RuntimeException.class)
		public void testGrafoNoConexoUnionFind() {
			Grafo g = new Grafo(9);
			g.agregarArista(0, 1, 4F);
			g.agregarArista(0, 7, 8F);
			g.agregarArista(1, 2, 8F);
			g.agregarArista(1, 7, 12F);
			g.agregarArista(6, 5, 1F);
			Kruskal ks = new Kruskal(g);
			ks.arbolMinimoUnionFind();
		}

}
