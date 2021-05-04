import java.util.*;

public class LocalSearch {

    private int k;
    private static Random rand;
    private int n;
    private int[] randomMedians;


    public LocalSearch(int n, int k){
        this.k = k;
        this.n = n;
        rand = new Random();
        randomMedians = new int[k];
        makeRandomMedians();
    }

    private void makeRandomMedians() {
        List<Integer> firstMedians = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            firstMedians.add(i);
        }
        Collections.shuffle(firstMedians);

        for (int j = 0; j < k; j++) {
            randomMedians[j] = firstMedians.get(j).intValue();
        }
    }

    public int[] getRandomMedians() {
        return this.randomMedians;
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

        try {
            Point[] samplePoints = p.generatePoints();
            int[][] distanceMatrix = p.generateDistanceMatrix();


            int k = 2;
            int n = 5;

            LocalSearch ls = new LocalSearch(n, k);

            int[] medians = ls.getRandomMedians();

            Integer[] mediansArray = Arrays.stream(medians).boxed().toArray(Integer[]::new);

            for (Point s : samplePoints) {
                System.out.println(s);
            }
            System.out.println(Arrays.deepToString(distanceMatrix));
            System.out.println("Medians: " + Arrays.toString(medians));

            System.out.println("The total distance is" + ls.distance(distanceMatrix, mediansArray));
        }
        catch (Exception e) {
            System.out.println("File not found");
        }

    }

}


