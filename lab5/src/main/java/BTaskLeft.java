public class BTaskLeft implements Runnable {

    private int i;
    private int j;
    private int k;
    private double[][] m;
    private double c;

    BTaskLeft(int i, int j, int k, double[][] m, double c) {
        this.i = i;
        this.j = j;
        this.m = m;
        this.k = k;
        this.c = c;
    }



    public void run() {
        m[j][k] -= c * m[i][k];
    }
}
