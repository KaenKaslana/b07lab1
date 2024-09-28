import java.io.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        // Test 1
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        
        // Test 2
        double[] c1 = {6, 0, 0, 5};
        int[] e1 = {0, 1, 2, 3};
        Polynomial p1 = new Polynomial(c1, e1);
        System.out.println(p1.evaluate(2));

        double[] c2 = {2, 4, 9, 7, 1};
        int[] e2 = {0, 1, 3, 5, 6};
        Polynomial p2 = new Polynomial(c2, e2);
        System.out.println(p2.evaluate(2));

        // Test 3
        Polynomial sum = p1.add(p2);
        for (double coef : sum.coefficients) {
            System.out.print(coef + " ");
        }
        System.out.println();
        for (int exp : sum.exponents) {
            System.out.print(exp + " ");
        }
        System.out.println();

        // Test 4
        Polynomial product = p1.multiply(p2);
        for (double coef : product.coefficients) {
            System.out.print(coef + " ");
        }
        System.out.println();
        for (int exp : product.exponents) {
            System.out.print(exp + " ");
        }
        System.out.println();

        // Test 5
        System.out.println(p1.hasRoot(0));

        // Test 6
        p1.saveToFile("polynomial.txt");

        // Test 7
        Polynomial loadedPoly = new Polynomial(new File("polynomial.txt"));
        for (double coef : loadedPoly.coefficients) {
            System.out.print(coef + " ");
        }
        System.out.println();
        for (int exp : loadedPoly.exponents) {
            System.out.print(exp + " ");
        }
    }
}
