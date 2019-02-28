import Jama.Matrix;

import java.io.*;

public class Checker {

    static boolean compare(double a, double b, double epsilon) {
        double c = a - b;
        if (c < 0.0) {
            c *= -1.0;
        }
        if (c < epsilon) {
            return true;
        }
        return false;
    }

    public void check() throws  IOException {

        double epsilon = 0.0001;


        int N = 15;
        //read source file
       /* File fil = new File("/out10.txt");
        FileReader inputFil = new FileReader(fil);
        BufferedReader in = new BufferedReader(inputFil);*/

        BufferedReader in = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/" + "out10.txt"), "UTF-8"));
        String s = in.readLine();


        //int size = Integer.parseInt(s);
        int size = N;
        double[][] lhs = new double[size][size];
        double[][] rhs = new double[size][1];

        for (int i = 0; i < size; i++) {
            s = in.readLine();
            String[] sp = s.split(" ");
            for (int j = 0; j < size; j++) {
                lhs[i][j] = Double.parseDouble(sp[j]);
            }
        }
        s = in.readLine();
        String[] sp = s.split(" ");
        for (int j = 0; j < size; j++) {
            rhs[j][0] = Double.parseDouble(sp[j]);
        }

        //solve
        Matrix A = new Matrix(lhs);
        Matrix b = new Matrix(rhs);
        Matrix x = A.solve(b);

        //read output file
        File fil = new File("./out.txt");
        FileReader inputFil = new FileReader(fil);
        in = new BufferedReader(inputFil);

        s = in.readLine();

        //    size = Integer.parseInt(s);
        size = N;

        double[][] lhs_r = new double[size][size];
        double[][] rhs_r = new double[size][1];

        for (int i = 0; i < size; i++) {
            s = in.readLine();
            sp = s.split(" ");
            for (int j = 0; j < size; j++) {
                lhs_r[i][j] = Double.parseDouble(sp[j]);
            }
        }
        s = in.readLine();
        sp = s.split(" ");
        for (int j = 0; j < size; j++) {
            rhs_r[j][0] = Double.parseDouble(sp[j]);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    if (!compare(1., lhs_r[i][j], epsilon)) {
                        System.out.println("Error1 " + i + " " + j);
                        System.exit(0);
                    }
                } else if (!compare(0., lhs_r[i][j], epsilon)) {
                    System.out.println("Error2 " + i + " " + j);
                    System.exit(0);
                }
            }
        }

        for (int j = 0; j < size; j++) {
            if (!compare(x.getArray()[j][0], rhs_r[j][0], epsilon)) {
                System.out.println("Error3 " + (size+1) + " " + j);
                System.exit(0);
            }
        }

        System.out.println("ok");

    }


}
