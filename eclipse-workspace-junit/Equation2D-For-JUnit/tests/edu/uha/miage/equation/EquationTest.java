package edu.uha.miage.equation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.uha.miage.NullCoefException;

class EquationTest {
	private static Random R = new Random();

	private static class Coef {
		public final int a, b, c;

		public Coef(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

	private static List<Coef> coefsWhereAis0;

	@BeforeAll
	static void setUpCoefsWhereAis0() throws Exception {
		coefsWhereAis0 = new LinkedList<>();
		for (int i = 0; i < 20; ++i) {
			coefsWhereAis0.add(new Coef(0, R.nextInt(-50, 50), R.nextInt(-50, 50)));
		}
	}

	private void aIs0(Coef c) {
		try {
			Equation e = new Equation(c.a, c.b, c.c);
			fail(String.format("%s devrait lancer une exception", e));
		} catch (NullCoefException ex) {
			assertTrue(c.a == 0, "Le coef a devrait être égal à 0 si NullPointerException");
		}
	}

	@Test
	void testCoefsWhereAis0() {
		for (Coef c : coefsWhereAis0) {
			aIs0(c);
		}
	}

	private static List<Coef> coefsWith2roots;

	@BeforeAll
	static void setUpCoefsWith2roots() throws Exception {
		coefsWith2roots = new LinkedList<>();
		for (int i = 0; i < 20; ++i) {
			int x1 = R.nextInt(-50, 50);
			int x2 = R.nextInt(-50, 50);
			while (x1 == x2) {
				x2 = R.nextInt(-50, 50);
			}

			int mult = R.nextInt(1, 5) * (R.nextBoolean() ? -1 : 1);
			coefsWith2roots.add(new Coef(mult, -mult * (x1 + x2), mult * x1 * x2));
		}
	}

	private void with2roots(Coef c) {
		try {
			Equation e = new Equation(c.a, c.b, c.c);
			assertEquals(2, e.getRootsCount(), String.format("%s devrait avoir deux racines", e));
			assertEquals(0.0, eval(e, e.getX1()), 1e-7,
					String.format("Eval de %s avec la racine trouvée x1 = %f", e, e.getX1()));

			assertEquals(0.0, eval(e, e.getX2()), 1e-7,
					String.format("Eval de %s avec la racine trouvée x2 = %f", e, e.getX2()));

		} catch (NullCoefException ex) {
			assertTrue(c.a == 0, "Le coef a devrait être égal à 0 si NullCoefException");
		}
	}

	@Test
	public void testCoefsWith2roots() {
		for (Coef c : coefsWith2roots) {
			with2roots(c);
		}
	}

	private static List<Coef> coefsWith1root;

	@BeforeAll
	static void setUpCoefsWith1roots() throws Exception {
		coefsWith1root = new LinkedList<>();
		for (int i = 0; i < 20; ++i) {
			int x1 = R.nextInt(-50, 50);

			int mult = R.nextInt(1, 5) * (R.nextBoolean() ? -1 : 1);
			coefsWith1root.add(new Coef(mult, -mult * (2 * x1), mult * x1 * x1));
		}
		// 100(x-1/2)^2 = 100x^2 - 100x + 100(1/4)
		coefsWith1root.add(new Coef(100, -100, 25));
		// -16(x-3/4)^2 = -16(x^2 - 3/2 + 9/16) = (-16x^2 + 24x - 9)
		coefsWith1root.add(new Coef(-16, 24, -9));
	}

	void with1root(Coef c) {
		try {
			Equation e = new Equation(c.a, c.b, c.c);
			assertEquals(1, e.getRootsCount(), String.format("%s devrait avoir une racine", e));
			assertEquals(0.0, eval(e, e.getX1()), 1e-7,
					String.format("Eval de %s avec la racine trouvée x1 = %f", e, e.getX1()));

			assertEquals(0.0, eval(e, e.getX2()), 1e-7,
					String.format("Eval de %s avec la racine trouvée x2 = %f", e, e.getX2()));

		} catch (NullCoefException ex) {
			assertTrue(c.a == 0, "Le coef a devrait être égal à 0 si NullCoefException");
		}
	}

	@Test
	void testCoefsWith1roots() {
		for (Coef c : coefsWith1root) {
			with1root(c);
		}
	}

	private double eval(Equation e, double x) {
		return e.getA() * x * x + e.getB() * x + e.getC();
	}
	
	private void testAll(int max) {

		int nb0root = 0;
		int nb1root = 0;
		int nb2roots = 0;
		int nba0 = 0;

		int i = 0;

		while (i < 1000 || (nb0root < 5 || nb1root < 5 || nb2roots < 5 || nba0 < 5)) {
			int a = R.nextInt(-max, max);
			int b = R.nextInt(-max, max);
			int c = R.nextInt(-max, max);
			try {
				Equation e = new Equation(a, b, c);
				double delta = b * b - 4 * a * c;
				if (delta < 0) {
					nb0root++;
					assertEquals(0, e.getRootsCount(), String.format("%s devrait avoir 0 racine", e));
				} else if (delta > 0) {
					nb2roots++;
					assertEquals(2, e.getRootsCount(), String.format("%s devrait avoir une racine", e));
					assertEquals(0.0, eval(e, e.getX1()), 1e-7,
							String.format("Eval de %s avec la racine trouvée x1 = %f", e, e.getX1()));
					assertEquals(0.0, eval(e, e.getX2()), 1e-7,
							String.format("Eval de %s avec la racine trouvée x2 = %f", e, e.getX2()));
				} else {
					nb1root++;
					assertEquals(1, e.getRootsCount(), String.format("%s devrait avoir une racine", e));
					assertEquals(0.0, eval(e, e.getX1()), 1e-7,
							String.format("Eval de %s avec la racine trouvée x1 = %f", e, e.getX1()));
					assertEquals(0.0, eval(e, e.getX2()), 1e-7,
							String.format("Eval de %s avec la racine trouvée x2 = %f", e, e.getX2()));
				}

			} catch (NullCoefException ex) {
				nba0++;
				assertTrue(a == 0, "Le coef a devrait être égal à 0 si NullCoefException");
			}
			++i;
		}

		System.out.printf("Sur %d tests (Coefs entre -%d et %d)\n"
				+ "\t a = 0 \t\t=> %d,\n"
				+ "\t 0 racine \t=> %d,\n"
				+ "\t 1 racine \t=> %d,\n"
				+ "\t 2 racines \t=> %d\n\n", i, max, max, nba0,	nb0root, nb1root, nb2roots);

	}

	@Test
	void testAll() {
		testAll(10);
		testAll(50);
		testAll(100);
		testAll(500);
		testAll(1000);
	}
}
