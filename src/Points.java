import java.util.ArrayList;
import java.util.Random;

public class Points {

    private int n;
    private Point[] samplePoints = null;
    private int[][] distanceMatrix;

    private Random rand;

    public Points(int n){
        this.n = n;
        this.samplePoints = new Point[n];
        this.distanceMatrix = new int[n][n];
        rand = new Random();

    }

    public Point[] generatePoints() {
        int x, y;

        for (int i = 0; i < n; i++) {
            x = rand.nextInt(n);
            y = rand.nextInt() % n;
            this.samplePoints[i] = new Point(x, y);
        }

        return this.samplePoints;
    }

    public int[][] generateDistanceMatrix() {

        if (samplePoints == null) {
            this.generatePoints();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distanceMatrix[i][j] = Math.abs(samplePoints[i].getX() - samplePoints[j].getX()) + Math.abs(samplePoints[i].getY() - samplePoints[j].getY());
            }
        }
        return distanceMatrix;
    }

    public void drawPoints() {

        for (int i = 0; i < n; i++) {
            System.out.println( "("+ samplePoints[i].getX() +"," + samplePoints[i].getY() + ")");
        }
    }

}
