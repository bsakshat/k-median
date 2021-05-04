import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of points: ");
        int n = input.nextInt();
        System.out.print("Enter the number of median i.e. k: ");
        int k = input.nextInt();

        System.out.println("n = " + n + " k = " + k);

        if (k < 1 || k > n)
            System.out.println("k must be greater than or equal to 1 and less than or equal to n");

        Points p = new Points(n);
        Point[] samplePoints = p.generatePoints();
        int[][] distances = p.generateDistanceMatrix();
        p.drawPoints();

        GurobiModel gm = new GurobiModel();
        gm.solveLP(distances, k);

        gm.solveIP(distances, k);
    }
}
