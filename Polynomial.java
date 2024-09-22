public class Polynomial{
    double[] coefficients;
    public Polynomial(){
        coefficients = new double[]{0};
    }
    public Polynomial(double[] coef){
        coefficients = coef;
    }
    public Polynomial add(Polynomial p){
        int length;
        if (coefficients.length > p.coefficients.length){
            length = coefficients.length;
        }
        else {
            length = p.coefficients.length;
        }
        double[] arr = new double[length];
        for (int i = 0; i < coefficients.length; i++){
            arr[i] += coefficients[i];
        }
        for (int i = 0; i < p.coefficients.length; i++){
            arr[i] += p.coefficients[i];
        }
        Polynomial result = new Polynomial(arr);
        return result;
    }
    public double evaluate(double x){
        double result = 0;
        for (int i = 0; i < coefficients.length; i++){
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }
    public boolean hasRoot(double x){
        if (evaluate(x) == 0){
            return true;
        }
        return false;
    }
}