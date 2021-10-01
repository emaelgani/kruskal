package grafo;

public class Kruskal {

	private Float[][] pesosDeAristas;
	private Grafo nuevo;
	private Grafo grafo;
	private Integer padres[];

	public Kruskal(Grafo g) {
		nuevo = new Grafo(g.vertices());
		grafo = g;
		this.pesosDeAristas = new Float[g.vertices()][g.vertices()];
		
	}
	
	
	/*
	 * Con la funcion copiarPesosAristas, obtenemos la misma matriz que contiene los pesos,
	 * con el fin de poder modificarla y a su vez evitar ALIASING.
	 */
	private void copiarPesosDeAristas(Grafo grafo) {
		for(int fila=0; fila<grafo.vertices(); fila++) {
			for(int columna=0; columna<this.grafo.vertices(); columna++) {
				this.pesosDeAristas[fila][columna]=grafo.obtenerPesoDeTodasAristas()[fila][columna];
			}
		}
		
	}

	public Grafo arbolMinimoBFS() {
		if (!BFS.esConexo(this.grafo)) {
			throw new RuntimeException("El Grafo no es conexo!!");
		}
		this.copiarPesosDeAristas(this.grafo);

		while (obtenerMinimo()[0] != null) {
			Integer fila = obtenerMinimo()[1].intValue();
			Integer col = obtenerMinimo()[2].intValue();
			Float peso = obtenerMinimo()[0];
			if (!BFS.pertenece(this.nuevo, fila, col)) {
				//System.out.println("Agrego la arista: " + fila + "-" + col + ", de peso: " + peso);
				this.nuevo.agregarArista(fila, col, peso);
			}
			this.pesosDeAristas[fila][col] = null;
		}
		return this.nuevo;
	}

	private Float[] obtenerMinimo() {
		Float elMenor = 1000F;
		Float filaMenor = 0F;
		Float columnaMenor = 0F;
		for (int fila = 0; fila < this.grafo.vertices(); fila++) {
			for (int col = 0; col <  this.grafo.vertices(); col++) {
				if (this.pesosDeAristas[fila][col] != null && this.pesosDeAristas[fila][col] < elMenor) {
					elMenor = this.pesosDeAristas[fila][col];
					filaMenor = (float) fila;
					columnaMenor = (float) col;
				}

			}
		}
		if (elMenor == 1000) {
			elMenor = null;
		}
		Float[] arr = { elMenor, filaMenor, columnaMenor };
		return arr;
	}

	// --------------------------Implementacion Con union find----------------

	public Grafo arbolMinimoUnionFind() {
		if (!BFS.esConexo(this.grafo)) {
			throw new RuntimeException("El Grafo no es conexo!!");
		}
		
		this.copiarPesosDeAristas(this.grafo);
		
		Grafo arbMinUF = new Grafo(this.grafo.vertices());

		this.padres = new Integer[this.grafo.vertices()];

		while (obtenerMinimo()[0] != null) {
			Integer fila = obtenerMinimo()[1].intValue();
			Integer col = obtenerMinimo()[2].intValue();
			Float peso = obtenerMinimo()[0];

			//System.out.println(fila+ " " + col);
			//this.imprimirArr();
			if (find(fila, col) == -3) { // Si ningun vertice se encuentra asociado
				this.padres[col] = fila;
				this.padres[fila] = fila;
				arbMinUF.agregarArista(fila, col, peso);
				//System.out.println("Se agrega la arista: " + fila+ " - "+col+ ", de peso: "+ peso);

			}
			if (find(fila, col) == -2) {
				this.padres[fila]=col;
				arbMinUF.agregarArista(fila, col, peso);
				//System.out.println("Se agrega la arista: " + fila+ " - "+col+ ", de peso: "+ peso);
			}
			
			if (find(fila, col) == -1) {
				this.padres[col]=fila;
				arbMinUF.agregarArista(fila, col, peso);
				//System.out.println("Se agrega la arista: " + fila+ " - "+col+ ", de peso: "+ peso);
			}
			
			if(find(fila, col) == 0) {
				this.union(fila, col);
				arbMinUF.agregarArista(fila, col, peso);
				//System.out.println("Se agrega la arista: " + fila+ " - "+col+ ", de peso: "+ peso);
			}
			//this.imprimirArr();
			this.pesosDeAristas[fila][col] = null;

		}
		
		return arbMinUF;

	}

	/*
	 * -3 Cuando ninguno de los vertices tiene padre.
	 * -2 Cuando la posicion de la fila no tiene padre
	 * -1 Cuando la posicion de la columna no tiene padre
	 *  0 Cuando el padre de los vertices son distintos
	 *  1 Cuando los padres son iguales. O sea, que están en la misma componente conexa, NO SE AGREGA.
	 * */
	private int find(Integer i, Integer j) {

		if (this.padres[i] == null && this.padres[j] == null) {
			return -3;
		}
		if(this.padres[i]==null && this.padres[j]!=null) {
			return -2;
		}
		if(this.padres[i] != null && this.padres[j] == null) {
			return -1;
		}
		
		return root(i) == root(j) ? 1 : 0;

	}

	private Integer root(Integer i) {

		while (this.padres[i] != i) {
			i = this.padres[i];
		}
		return i;

	}

	private void union(int i, int j) {
		int raizI = this.root(i);
		int raizJ = this.root(j);
		this.padres[raizI] = raizJ;
	}
	
	
	
	/*
	private void imprimirArr() {
		
		String v="";
		System.out.print("[");
		for(int i=0; i<this.grafo.vertices(); i++) {
			
			if(padres[i]==null) {
				v= "-";
				}else {
					v= padres[i]+"";
				}
			System.out.print(v+", ");
		}
		System.out.println("]");
		}
	*/
	}




