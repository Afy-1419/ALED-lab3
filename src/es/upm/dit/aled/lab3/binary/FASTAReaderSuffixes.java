	package es.upm.dit.aled.lab3.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.upm.dit.aled.lab3.FASTAReader;

/**
 * Reads a FASTA file containing genetic information and allows for the search
 * of specific patterns within these data. The information is stored as an array
 * of bytes that contain nucleotides in the FASTA format. Since this array is
 * usually created before knowing how many characters in the origin FASTA file
 * are valid, an int indicating how many bytes of the array are valid is also
 * stored. All valid characters will be at the beginning of the array.
 * 
 * This extension of the FASTAReader uses a sorted dictionary of suffixes to
 * allow for the implementation of binary search.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FASTAReaderSuffixes extends FASTAReader {
	protected Suffix[] suffixes;

	/**
	 * Creates a new FASTAReader from a FASTA file.
	 * 
	 * At the end of the constructor, the data is sorted through an array of
	 * suffixes.
	 * 
	 * @param fileName The name of the FASTA file.
	 */
	public FASTAReaderSuffixes(String fileName) {
		// Calls the parent constructor
		super(fileName);
		this.suffixes = new Suffix[validBytes];
		for (int i = 0; i < validBytes; i++)
			suffixes[i] = new Suffix(i); //1. creo un Suffix con indice 3 por ejemplo. 
		// Sorts the data
		sort();	// por ultimo se va a ordenar para obtener suffixes ordenado por orden alfabético. 
	}

	/*
	 * Helper method that creates a array of integers that contains the positions of
	 * all suffixes, sorted alphabetically by the suffix.
	 */
	private void sort() {
		// Instantiate the external SuffixComparator, passing 'this' (the reader)
		// so it can access the content and validBytes fields.
		SuffixComparator suffixComparator = new SuffixComparator(this);
		// Use the external Comparator for sorting.
		Arrays.sort(this.suffixes, suffixComparator);
	}

	/**
	 * Prints a list of all the suffixes and their position in the data array.
	 */
	public void printSuffixes() {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Index | Sequence");
		System.out.println("-------------------------------------------------------------------------");
		for (int i = 0; i < suffixes.length; i++) {
			int index = suffixes[i].suffixIndex;
			String ith = "\"" + new String(content, index, Math.min(50, validBytes - index)) + "\"";
			System.out.printf("  %3d | %s\n", index, ith);
		}
		System.out.println("-------------------------------------------------------------------------");
	}

	/**
	 * Implements a binary search to look for the provided pattern in the data
	 * array. Returns a List of Integers that point to the initial positions of all
	 * the occurrences of the pattern in the data.
	 * 
	 * @param pattern The pattern to be found.
	 * @return All the positions of the first character of every occurrence of the
	 *         pattern in the data.
	 */
	@Override
	
	
	
/**  A MI SE ME HABÍA OCURRIDO ASI. PERO HAY PROBLEMILLAS QUE LUEGO NO SE COMO GESTIONAR. ASI QUE VOY A VER QUE SE HA HECHO EN LA SOLUCION REAL Y ANALIZAR EL CODIGO CORRECTO 
 *   ESTO DA PROBLEMAS PORQUE NO HAY UN COMPARADOR ALFABÉTICO ASI QUE ES IMPOSIBLE DIVIDIR CUANDO QUIERES SALIR DE UN BUCLE NO COINCIDENTE.
 *   SI LO HAGO ASÍ ME DA PROBLEMAS LO BINARIO Y NO SE COMO SEGUIRLO
 *   
	public List<Integer> search(byte[] pattern) {
	List<Integer> posicionescoincidentes=new ArrayList<>();
	//ns exactamente cuantas iteraciones tiene que hacer. 
		binarySearch(content, pattern, lo, hi, posicionescoincidentes);
	return posicionescoincidentes;
	}
	
		

			private List<Integer> binarySearch(byte[] content, byte[] pattern, int lo, in hi, List<Integer> lista) {
				
				int lo= 0;
				int hi=suffixes.length;
				int me = lo + (hi - lo) /2;
				boolean found=false; 
				boolean coincidensufijos=true;
				
				
					//CUANDO TERMINO CASO BASE ( se termina cuando es mas pequeño o cuando ya se ha encontrado el pattern en suffixes.length.
					if ( hi-lo<=1 || found==true) {
							//found==true || hi- lo <= 1) { lo utilizo si en cuanto found sea true quiero returnear la posión y salir del bucle, pero no quiero eso
							//quiero que me returnee las posiciones y salir del bucle si ya no hay más opcion de seguir buscando porque o se termine hi-lo<=1;
							//o cuando he recorrido los sufijos que hay antes y despues y no coinciden. 
						return lista;
				}
						
					//PASO RECURSIVO
					else { 
						
						Suffix possuffix = suffixes[me];
						
						if(content[possuffix.getIndex()]==pattern[0]) { //si suffixes[me] pone lo mismo que el pattern, entonces
						for (int i=0; i<pattern.length; i++) //hago un bucle para comprobar el contenido 
							if (pattern[i] == content[possuffix.getIndex() + i]) { 
								coincidensufijos=true;}
							
							else {coincidensufijos=false; //si en algun momento no hay coincidencia poner a falso y salir del bucle
								break;}
								
								
						if ( coincidensufijos==true) {found=true;
						lista.add(possuffix.getIndex());
						
						
						// buscar hacia la izquierda y hacia la derecha NO SE COMO   ----> CLARO FALTA EL COMPARE SIN ES INVIABLE
						
						
						}
						}
						
						else {
						
							if (pattern[0]<content[possuffix.getIndex()]) { 
								hi = me-1;
								binarySearch( content, pattern, lo, hi, lista);
								
							}
							
							else if (pattern[0]>content[possuffix.getIndex()]) {
								lo=me+1;
								binarySearch( content, pattern,lo, hi, lista);
							}}
					}
						
			return lista;
						
					
			}

			*/
		
	
	//COMO PUEDO CAMBIARLO PARA QUE TENGA SENTIDO 
	
	public List<Integer> search(byte[] pattern) {
	    List<Integer> posicionescoincidentes = new ArrayList<>();
	    binarySearch(content, pattern, 0, suffixes.length, posicionescoincidentes);
	    return posicionescoincidentes;
	}
	
	private List<Integer> binarySearch(byte[] content, byte[] pattern, int lo, int hi, List<Integer> lista) {

		//CASO BASE no pongo el de found==true pq no quiero que se devuelva hasta el final 
		
	    if (lo > hi) return lista; 
	    int me = lo + (hi - lo) / 2;
	    int possuffix = suffixes[me].getIndex();
	    
	    
	    //METER UN COMPARE PARA EVITAR QUE SE COMPAREN MAL LAS COSAS 
	    int cmp = comparePatternWithSuffix(pattern, content, possuffix);
	   
	    if (cmp == 0) { //compruebo el patron desde dentro no desde fuera con bucle. 
	        // Coincidencia exacta
	        lista.add(possuffix);
	        // COMO HA COINCIDIDO LO AÑADO A LA LISTA PERO TENGO QUE TENER EN CUENTA DE QUE PUEDE HABER MÁS OPCIONES.
	       
	        
	        // buscar hacia la izquierda
	        int left = me - 1;
	        while (left >= lo && comparePatternWithSuffix(pattern, content, suffixes[left].getIndex()) == 0) {
	            lista.add(suffixes[left].getIndex());
	            left--;
	        }

	        // buscar hacia la derecha
	        int right = me + 1;
	        while (right <= hi && comparePatternWithSuffix(pattern, content, suffixes[right].getIndex()) == 0) {
	            lista.add(suffixes[right].getIndex());
	            right++;
	        }

	        return lista;
	    }

	    
	    else if (cmp < 0) { 
	        // patrón < sufijo -> izquierda
	    	hi= me-1;
	        return binarySearch(content, pattern, lo, me-1, lista);
	    }

	    else {
	        // patrón > sufijo -> derecha
	    	lo=me+1;
	        return binarySearch(content, pattern, me+1 , hi, lista);
	    }

	}    

	// UN METODO COMPARE 
	    
	    private int comparePatternWithSuffix(byte[] pattern, byte[] content, int start) {

	        for (int i = 0; i < pattern.length; i++) {
	            if (start + i >= content.length) return +1; // sufijo más corto
	            if (pattern[i] < content[start + i]) return -1;
	            if (pattern[i] > content[start + i]) return +1;
	        }

	        // si llegamos al final del patrón → coincide
	        return 0;
	    }


	
				
			
	public static void main(String[] args) {
		long t1 = System.nanoTime();
		FASTAReaderSuffixes reader = new FASTAReaderSuffixes(args[0]);
		if (args.length == 1)
			return;
		byte[] patron = args[1].getBytes();
		System.out.println("Tiempo de apertura de fichero: " + (System.nanoTime() - t1));
		long t2 = System.nanoTime();
		System.out.println("Tiempo de ordenación: " + (System.nanoTime() - t2));
		reader.printSuffixes();
		long t3 = System.nanoTime();
		List<Integer> posiciones = reader.search(patron);
		System.out.println("Tiempo de búsqueda: " + (System.nanoTime() - t3));
		if (posiciones.size() > 0) {
			for (Integer pos : posiciones)
				System.out.println("Encontrado " + args[1] + " en " + pos);
		} else
			System.out.println("No he encontrado " + args[1] + " en ningún sitio.");
		System.out.println("Tiempo total: " + (System.nanoTime() - t1));
	}
}
