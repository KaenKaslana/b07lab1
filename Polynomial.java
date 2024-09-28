import java.io.*;
import java.util.*;

public class Polynomial{
    double[] coefficients;
    int[] exponents;
    public Polynomial(){
        coefficients = new double[]{0};
        exponents = new int[]{0};
    }
    public Polynomial(double[] coef, int[] exp){
        coefficients = coef;
        exponents = exp;
    }
    public Polynomial(File file) throws Exception{
        Scanner sc = new Scanner(file);
        String poly = sc.nextLine();
        sc.close();
        String[] arr = poly.split("(?=[+-])");
        coefficients = new double[arr.length];
        exponents = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains("x")) {
                int xIndex = arr[i].indexOf("x");
                if (xIndex == 0 || (xIndex == 1 && arr[i].charAt(0) == '+')) {
                    coefficients[i] = 1;
                }
                else if (xIndex == 1 && arr[i].charAt(0) == '-'){
                    coefficients[i] = -1;
                }
                else{
                    coefficients[i] = Double.parseDouble(arr[i].substring(0, xIndex));
                }

                if (arr[i].length() > xIndex + 1) {
                    exponents[i] = Integer.parseInt(arr[i].substring(xIndex + 1));
                }
                else{
                    exponents[i] = 1;
                }
                
            }
            else{
                coefficients[i] = Double.parseDouble(arr[i]);
                exponents[i] = 0;
            }
        }

    }

    public void saveToFile(String filename) throws Exception{
        FileWriter file = new FileWriter(filename);
        boolean flag = true;
        double coef;
        int exp;
        for (int i = 0; i < coefficients.length; i++) {
            coef = coefficients[i];
            exp = exponents[i];
    
            if (coef == 0) {
                continue;
            }
            if (coef > 0 && !flag) {
                file.write("+");
            } else {
                flag = false;
            }
            if (exp == 0) {
                file.write(coef + "");
            } else if (exp == 1) {
                if (coef == 1) {
                    file.write("x");
                } else if (coef == -1) {
                    file.write("-x");
                } else {
                    file.write(coef + "x");
                }
            } else {
                if (coef == 1) {
                    file.write("x" + exp);
                } else if (coef == -1) {
                    file.write("-x" + exp);
                } else {
                    file.write(coef + "x" + exp);
                }
            }
        }
        file.close();
    }
    public Polynomial add(Polynomial p){
        int length = 0;
        double[] arrCoeff = new double[coefficients.length + p.coefficients.length];
        int[] arrExp = new int[exponents.length + p.exponents.length];  
        for (int i = 0; i < this.exponents.length; i++) {
            arrCoeff[length] = this.coefficients[i];
            arrExp[length] = this.exponents[i];
            length++;
        }
        for (int i = 0; i < p.exponents.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arrExp.length; j++) {
                if (p.exponents[i] == arrExp[j]) {
                    arrCoeff[j] += p.coefficients[i];
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                arrCoeff[length] = p.coefficients[i];
                arrExp[length] = p.exponents[i];
                length++;
            }
        }
        int finalLength = 0;
        for (int i = 0; i < length; i++) {
            if (arrCoeff[i] != 0) finalLength++;
        }
        double[] finalCoefficients = new double[finalLength];
        int[] finalExponents = new int[finalLength];
        int n=0;
        for (int i = 0; i < length; i++) {
            if (arrCoeff[i] != 0) {
                finalCoefficients[n] = arrCoeff[i];
                finalExponents[n] = arrExp[i];
                n++;
            }
        }
        Polynomial result = new Polynomial(finalCoefficients, finalExponents);
        return result;
    }

    public Polynomial multiply(Polynomial p){
        int length = 0;
        double[] arrCoeff = new double[coefficients.length * p.coefficients.length];
        int[] arrExp = new int[exponents.length * p.exponents.length];
        for (int i = 0; i < exponents.length; i++) {
            for (int j = 0; j < p.exponents.length; j++) {
                arrCoeff[length] = this.coefficients[i] * p.coefficients[j];
                arrExp[length] = this.exponents[i] + p.exponents[j];
                length++;
            }
        }
        int finalLength = 0;
        for (int i = 0; i < length; i++) {
            if (arrCoeff[i] != 0) finalLength++;
        }
        double[] finalCoefficients = new double[finalLength];
        int[] finalExponents = new int[finalLength];
        int n=0;
        for (int i = 0; i < length; i++) {
            if (arrCoeff[i] != 0) {
                finalCoefficients[n] = arrCoeff[i];
                finalExponents[n] = arrExp[i];
                n++;
            }
        }
        Polynomial result = new Polynomial(finalCoefficients, finalExponents);
        return result;
    }

    public double evaluate(double x){
        double result = 0;
        for (int i = 0; i < coefficients.length; i++){
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }
    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}