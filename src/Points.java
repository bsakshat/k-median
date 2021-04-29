import java.util.ArrayList;
import java.util.Random;

public class Points {

    private int n;
    private int[] x_coordinate;
    private int[] y_coordinate;

    public Points(int n){
        this.n = n;
        x_coordinate = new int[n];
        y_coordinate = new int[n];
    }

    public int[][] generatePoints() {
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            x_coordinate[i] = rand.nextInt(1000) % n;
            y_coordinate[i] = rand.nextInt(1000) % n;
        }

        int[][] c = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = Math.abs(x_coordinate[i] - x_coordinate[j]) + Math.abs(y_coordinate[i] - y_coordinate[j]);
            }
        }

        return c;
    }

    public void drawPoints() {

        for (int i = 0; i < n; i++) {
            System.out.println( "("+ x_coordinate[i] +"," + y_coordinate[i] + ")");
        }
    }

}
