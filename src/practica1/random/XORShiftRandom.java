/**
* La clase XORShiftRandom genera numeros
* aleatorios mediante un generadores XORShift.
* http://www.javamex.com/tutorials/random_numbers/xorshift.shtml#.WGVK_oU8gWI
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @since   07-01-2017
*/

package practica1.random;

import java.util.Random;

public class XORShiftRandom extends Random {

	private static final long serialVersionUID = 1L;
	private long seed;

	/**
	 * Constructor
	 */
	public XORShiftRandom() {
		this(System.nanoTime());
	}
	
	/**
	 * Constructor
	 * @param seed
	 */
	public XORShiftRandom(long seed) {
		this.seed = seed;
	}
	
	/**
	 * Devuelve un numero entero aleatorio comprendido entre 0 y <n>
	 */
	public int nextInt(int n) {
		seed ^= (seed << 21);
		seed ^= (seed >>> 35);
		seed ^= (seed << 4);
		int out = (int) seed % n;
		return (out < 0) ? -out : out;
	}
}