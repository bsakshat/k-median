public class Point {

    private int x_coordinate;
    private int y_coordinate;

    public Point(int x, int y) {
        this.x_coordinate = x;
        this.y_coordinate = y;
    }

    public int getX() {
        return this.x_coordinate;
    }

    public int getY() {
        return this.y_coordinate;
    }

    @Override
    public String toString() {
        return "( " + this.getX() + " , " + this.getY() + " )";
    }
}
