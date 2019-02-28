public class BTaskRight implements Runnable {

    private int i;
    private int j;
    private double[] r;
    private double c;
    BTaskRight(int i, int j, double c, double[] r) {
        this.i = i;
        this.j = j;
        this.c = c;
        this.r = r;
    }



    public void run() {
        r[j] -= c * r[i];
    }
}
