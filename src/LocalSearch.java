import java.util.Arrays;
import java.util.Random;

public class LocalSearch {

    private int k;
    private static Random rand;

    public LocalSearch(int k){
        this.k = k;
        rand = new Random();
    }

    public float distance(int[][] distanceMatrix, Integer[] medians) {
        int minDistance = Integer.MAX_VALUE;
        float totalCost = 0;
        for (int i = 0; i < distanceMatrix.length; i++) {
            if (Arrays.asList(medians).contains(i)) {
                continue;
            }
            for (int j = 0; j < distanceMatrix[0].length; j++) {
                if (Arrays.asList(medians).contains(j)) {
                    if (distanceMatrix[i][j] < minDistance) {
                        minDistance = distanceMatrix[i][j];
                    }
                }
            }
            totalCost += minDistance;
        }
        return totalCost;
    }


    public static void main(String[] args) {


        Points p = new Points(5);
        Point[] samplePoints = p.generatePoints();
        int[][] distanceMatrix = p.generateDistanceMatrix();
        int k = 2;

        LocalSearch ls = new LocalSearch(k);

        int[] medians = new int[k];

        for (int i = 0; i < k; i++){
            medians[i] = rand.nextInt(distanceMatrix.length);
        }

        Integer[] mediansArray = Arrays.stream( medians ).boxed().toArray( Integer[]::new );

        for (Point s: samplePoints) {
            System.out.println(s);
        }
        System.out.println(Arrays.deepToString(distanceMatrix));
        System.out.println("Medians: " + Arrays.toString(medians));

        System.out.println("The total distance is" + ls.distance(distanceMatrix, mediansArray));
    }
}


