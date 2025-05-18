public class Polynomial {
    double[] coefficients;

    public Polynomial() {
		double[] arr = new double[1];
		arr[0] = 0;
        coefficients = arr;
    }

    public Polynomial(double[] coeff) {
		coefficients = new double[coeff.length];
		for (int i = 0; i < coeff.length; i++) {
			this.coefficients[i] = coeff[i];
		}
	}

    public Polynomial add(Polynomial poly) {
        int length = Math.max(coefficients.length, poly.coefficients.length);
        double[] result = new double[length];
		double a,b;
        for (int i = 0; i < length; i++) {
			if (i < coefficients.length) {
				a = coefficients[i];
			}
			else {
				a = 0;
			}
			if (i < poly.coefficients.length) {
				b = poly.coefficients[i];
			}
			else {
				b = 0;
			}
			result[i] = a + b;
        }
		Polynomial resPoly = new Polynomial(result);
        return resPoly;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result = result + coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        if (evaluate(x) == 0) {
			return true;
		}
		else {
			return false;
		}
    }
}
