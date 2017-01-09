/**
* La clase HighQualityRandom genera numeros
* aleatorios mediante dos generadores XORShift
* combinados con un LCG y un generador de 
* multiplicacion con carry.
* http://www.javamex.com/tutorials/random_numbers/numerical_recipes.shtml
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    09-01-2017
*/

package practica1.random;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HighQualityRandom extends Random {

	private static final long serialVersionUID = 1L;
	private Lock l = new ReentrantLock();
	private long u;
	private long v = 4101842887655102017L;
	private long w = 1;

	/**
	 * Constructor
	 */
	public HighQualityRandom() {
		this(System.nanoTime());
	}

	/**
	 * Constructor
	 * @param seed Semilla
	 */
	public HighQualityRandom(long seed) {
		l.lock();
		u = seed ^ v;
		nextLong();
		v = u;
		nextLong();
		w = v;
		nextLong();
		l.unlock();
	}
	
	/**
	 * Devuelve un numero long de forma aleatorio
	 */
	public long nextLong() {
		l.lock();
		try {
			u = u * 2862933555777941757L + 7046029254386353087L;
			v ^= v >>> 17;
			v ^= v << 31;
			v ^= v >>> 8;
			w = 4294957665L * (w & 0xffffffff) + (w >>> 32);
			long x = u ^ (u << 21);
			x ^= x >>> 35;
			x ^= x << 4;
			long ret = (x + v) ^ w;
			return ret;
		} finally {
			l.unlock();
		}
	}

	/**
	 * Devuelve un numero entero aleatorio comprendido entre 0 y <n>
	 */
	public int nextInt(int n) {
		long result = ((nextLong() >>> 32) * n) >> 32;
		return (int)result;	
	}
}