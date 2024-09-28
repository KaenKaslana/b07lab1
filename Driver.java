import java.io.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        // Test 1
        System.out.println("Test 1: Evaluate default polynomial p(x = 3)");
        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3));
        
        // Test 2
        System.out.println("\nTest 2: Create and evaluate Polynomial p1 and p2");
        double[] c1 = {6, 0, 0, 5};
        int[] e1 = {0, 1, 2, 3};
        Polynomial p1 = new Polynomial(c1, e1);
        System.out.println("p1(2) = " + p1.evaluate(2));

        double[] c2 = {2, 4, 9, 7, 1};
        int[] e2 = {0, 1, 3, 5, 6};
        Polynomial p2 = new Polynomial(c2, e2);
        System.out.println("p2(2) = " + p2.evaluate(2));

        // Test 3
        System.out.println("\nTest 3: Add p1 and p2");
        Polynomial sum = p1.add(p2);
        System.out.print("Sum coefficients: ");
        for (double coef : sum.coefficients) {
            System.out.print(coef + " ");
        }
        System.out.println();
        System.out.print("Sum exponents: ");
        for (int exp : sum.exponents) {
            System.out.print(exp + " ");
        }
        System.out.println();

        // Test 4
        System.out.println("\nTest 4: Multiply p1 and p2");
        Polynomial product = p1.multiply(p2);
        System.out.print("Product coefficients: ");
        for (double coef : product.coefficients) {
            System.out.print(coef + " ");
        }
        System.out.println();
        System.out.print("Product exponents: ");
        for (int exp : product.exponents) {
            System.out.print(exp + " ");
        }
        System.out.println();

        // Test 5
        System.out.println("\nTest 5: Check if p1 has root at x = 0");
        System.out.println("p1 has root at x = 0: " + p1.hasRoot(0));

        // Test 6
        System.out.println("\nTest 6: Save polynomial p1 to file 'polynomial.txt'");
        p1.saveToFile("polynomial.txt");
        System.out.println("Polynomial p1 saved to 'polynomial.txt'");

        // Test 7
        System.out.println("\nTest 7: Load polynomial from file");
        Polynomial loadedPoly = new Polynomial(new File("polynomial.txt"));
        System.out.print("Loaded polynomial coefficients: ");
        for (double coef : loadedPoly.coefficients) {
            System.out.print(coef + " ");
        }
        System.out.println();
        System.out.print("Loaded polynomial exponents: ");
        for (int exp : loadedPoly.exponents) {
            System.out.print(exp + " ");
        }
    }
}
