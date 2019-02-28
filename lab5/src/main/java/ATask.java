public class ATask implements Runnable {

    private int i;
    private int j;
    private double[][] m;
    private double[] sValues;

    ATask(int i, int j, double[][] m, double[] sValues) {
        this.i = i;
        this.j = j;
        this.m = m;
        this.sValues = sValues;
    }



    public void run() {
        sValues[i] = m[i][j] / m[j][j];
    }
}
