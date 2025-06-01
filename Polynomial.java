import java.io.*;
import java.util.*;
public class Polynomial {
    double[] coefficients;
	int[] exponents;

    public Polynomial() {
		double[] arr = new double[1];
		arr[0] = 0;
        coefficients = arr;
		int[] arr2 = new int[1];
		arr2[0] = 0;
		exponents = arr2;
    }

    public Polynomial(double[] coeff, int[] expo) {
		coefficients = new double[coeff.length];
		for (int i = 0; i < coeff.length; i++) {
			coefficients[i] = coeff[i];
		}
		exponents = new int[expo.length];
		for (int i = 0; i < expo.length; i++) {
			exponents[i] = expo[i];
		}
	}

	public Polynomial(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = br.readLine();
		if (str.charAt(0) != '-') {
    		str = "+" + str;
		}
		String[] polyTerm = str.split("(?=[+-])");
		exponents = new int[polyTerm.length];
		coefficients = new double[polyTerm.length];
		for (int i = 0; i < polyTerm.length; i++) {
			if (polyTerm[i].contains("x")) {
				String[] coeffExpo = polyTerm[i].split("x");
				coefficients[i] = Double.parseDouble(coeffExpo[0]);
				if (coeffExpo.length > 1) {
					exponents[i] = Integer.parseInt(coeffExpo[1]);
				}
			}
			else {
				coefficients[i] = Double.parseDouble(polyTerm[i]);
				exponents[i] = 0;
			}
		}
		br.close();
	}

	public void saveToFile(File file) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String res = "";
		for (int i = 0; i < coefficients.length; i++) {
			if (coefficients[i] > 0 && res.length() != 0) {
				res += "+";
			}
			if (exponents[i] != 0) {
				res += coefficients[i] + "x" + exponents[i];
			}
			else {
				res += Double.toString(coefficients[i]);
			}
		}
		bw.write(res);
		bw.close();
	}

    public Polynomial add(Polynomial poly) {
        int maxExpo = 0;
		for (int i = 0; i < exponents.length; i++) {
			if (exponents[i] > maxExpo) {
				maxExpo = exponents[i];
			}
		}
		for (int i = 0; i < poly.exponents.length; i++) {
			if (poly.exponents[i] > maxExpo) {
				maxExpo = poly.exponents[i];
			}
		}
		double[] newCoefficients = new double[maxExpo + 1];
		for (int i = 0; i < coefficients.length; i++) {
			newCoefficients[exponents[i]] += coefficients[i];
		}
		for (int i = 0; i < poly.coefficients.length; i++) {
			newCoefficients[poly.exponents[i]] += poly.coefficients[i];
		}
		int numNonZero = 0;
		for (int i = 0; i < newCoefficients.length; i++) {
			if (newCoefficients[i] != 0) {
				numNonZero++;
			}
		}
		double[] resCoeff = new double[numNonZero];
		int[] resExpo = new int[numNonZero];
		int index = 0;
		for (int i = 0; i <= maxExpo; i++) {
			if (newCoefficients[i] != 0) {
				resCoeff[index] = newCoefficients[i];
				resExpo[index] = i;
				index++;
			}
		}
		return new Polynomial(resCoeff, resExpo);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result = result + coefficients[i] * Math.pow(x, exponents[i]);
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

	public Polynomial multiply(Polynomial poly) {
		int maxExpo = 0;
		int tempExpo = 0;
		for (int i = 0; i < exponents.length; i++) {
			for (int j = 0; j < poly.exponents.length; j++) {
				tempExpo = exponents[i] + poly.exponents[j];
				if (tempExpo > maxExpo) {
					maxExpo = tempExpo;
				}
			}
		}
		double[] newCoefficients = new double[maxExpo + 1];
		for (int i = 0; i < coefficients.length; i++) {
			for (int j = 0; j < poly.coefficients.length; j++) {
				newCoefficients[exponents[i] + poly.exponents[j]] += coefficients[i] * poly.coefficients[j];
			}
		}
		int numNonZero = 0;
		for (int i = 0; i < newCoefficients.length; i++) {
			if (newCoefficients[i] != 0) {
				numNonZero++;
			}
		}
		double[] resCoeff = new double[numNonZero];
		int[] resExpo = new int[numNonZero];
		int index = 0;
		for (int i = 0; i <= maxExpo; i++) {
			if (newCoefficients[i] != 0) {
				resCoeff[index] = newCoefficients[i];
				resExpo[index] = i;
				index++;
			}
		}
		return new Polynomial(resCoeff, resExpo);
	}
}
