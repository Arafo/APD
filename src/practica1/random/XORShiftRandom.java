package practica1.random;

import java.util.Random;

/**
 * 
 * http://www.javamex.com/tutorials/random_numbers/xorshift.shtml#.WGVK_oU8gWI
 *
 */
public class XORShiftRandom extends Random {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long seed;

	public XORShiftRandom() {
		this(System.nanoTime());
	}

	public XORShiftRandom(long seed) {
		this.seed = seed;
	}

	public int nextInt(int n) {
		seed ^= (seed << 21);
		seed ^= (seed >>> 35);
		seed ^= (seed << 4);
		int out = (int) seed % n;
		return (out < 0) ? -out : out;
	}
}