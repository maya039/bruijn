package bruijn;

public class PrimeBruijn {

	private int field;
	private int[] polynomial;
	private int[] register = { 0, 0, 0, 1 };

	public PrimeBruijn(int field, int[] polynomial) {
		this.field = field;
		this.polynomial = polynomial;

	}

	private int arithmetic() {
		int i = 0;
		int s1 = 0;
		for (i = 0; i < register.length; i++) {
			if (polynomial[i] != 0) {
				s1 += (register[i] * polynomial[i]) % field;
				s1 = s1 % field;
			}
		}
		int nonlinear = Nonlinear();
		return ((s1 + nonlinear)*polynomial[4]) % field; 
	}

	private int Nonlinear() {
		int nonlinear = 0;
		if (field == 2) {
			if (register[0] == 1 && register[1] == 0 && register[2] == 0 && register[3] == 0) {
				nonlinear = 1;
			} else if (register[0] == 0 && register[1] == 0 && register[2] == 0 && register[3] == 0) {
				nonlinear = 1;
			} else {
				nonlinear = 0;
			}
		} else if (field == 5) {
			if (register[0] == 2 && register[1] == 0 && register[2] == 0 && register[3] == 0) {
				nonlinear = 3;// (2 + X)*3 % 5 = 0 -> x =3
			} else if (register[0] == 0 && register[1] == 0 && register[2] == 0 && register[3] == 0) {
				nonlinear = 2; // (0 + X)*3 % 5 = 1 -> x=2
			} else {
				nonlinear = 0;
			}
		}

		return nonlinear;
	}

	public int shift() {
		int i = 0;
		int newS1 = arithmetic();
		int value = register[i];
		for (i = 0; i < register.length - 1; i++) {
			register[i] = register[i + 1];
		}
		register[i] = newS1;

		
		return value;
	}

	public void shiftCykle() {
		int i = 0;
		for (i = 0; i < Math.pow(field, register.length); i++) {
			shift();
		}
	}

}
