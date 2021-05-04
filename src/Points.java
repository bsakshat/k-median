import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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

    public Point[] generatePoints() throws IOException {
        int x, y;

        File file = new File("points.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        //List<String> lines = Files.readAllLines(Paths.get("points.txt", ""));
        for (int i = 0; i < n; i++) {
            List<String> items = Arrays.asList(br.readLine().split("\\s*,\\s*"));
            x = Integer.parseInt(items.get(0)) % n;
            y = Integer.parseInt(items.get(1)) % n;
            this.samplePoints[i] = new Point(x, y);
        }

        return this.samplePoints;
    }

    public int[][] generateDistanceMatrix() {
        try {
            if (samplePoints == null) {
                this.generatePoints();
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distanceMatrix[i][j] = Math.abs(samplePoints[i].getX() - samplePoints[j].getX()) + Math.abs(samplePoints[i].getY() - samplePoints[j].getY());
                }
            }
        }
        catch (IOException e) {
            System.out.println("File not found");
        }
        return distanceMatrix;
    }

    public void drawPoints() {

        for (int i = 0; i < n; i++) {
            System.out.println( "("+ samplePoints[i].getX() +"," + samplePoints[i].getY() + ")");
        }
    }

}
