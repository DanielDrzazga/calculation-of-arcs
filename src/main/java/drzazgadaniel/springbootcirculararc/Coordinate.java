package drzazgadaniel.springbootcirculararc;

public class Coordinate {
    private int nr;
    private Double x;
    private Double y;

    public Coordinate(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return nr + "," + x + "," + y;
    }
}
