public class CTask implements Runnable {

    private int i;
    private double[][] m;
    private double[] r;

    CTask(int i, double[][] m, double[] r) {
        this.i = i;
        this.m = m;
        this.r = r;
    }



    public void run() {
        r[i] /= m[i][i];
        m[i][i] = 1;
    }
}
