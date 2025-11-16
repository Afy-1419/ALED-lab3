package es.upm.dit.aled.lab3.binary;

/**
 * Represents a suffix in the FASTA data by storing its starting index. This
 * class is designed to be sorted by an external Comparator that uses the actual
 * sequence data.
 *
 * @author mmiguel, rgarciacarmona
 */
public class Suffix {
	public final int suffixIndex; //2. este es el indice que va asociado al sufijo. 

	/**
	 * Creates a new Suffix.
	 * 
	 * @param index The starting position of the suffix in the data array.
	 */
	public Suffix(int index) {
		suffixIndex = index;
	}
	
	public int getIndex() {return suffixIndex;}
}