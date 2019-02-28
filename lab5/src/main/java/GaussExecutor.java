import java.io.*;

public class GaussExecutor {

    public void execute(String filename) {


        try {
            // the slash '/' is required
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + filename), "UTF-8"));
            String s = br.readLine();

            int size = Integer.parseInt(s);
            double[][] M = new double[size][size];
            double[] R = new double[size];


            for (int i = 0; i < size; i++) {
                s = br.readLine();
                String[] sp = s.split(" ");
                for (int j = 0; j < size; j++) {
                    M[i][j] = Double.parseDouble(sp[j]);
                }
            }
            s = br.readLine();
            String[] sp = s.split(" ");
            for (int j = 0; j < size; j++) {
                R[j] = Double.parseDouble(sp[j]);
            }

            for (int j = 0; j < size; j++) {
                double[] sValues = new double[size];
                Thread[] AThrs = new Thread[size];
                for (int i = 0; i < size; i++) {
                    if (i == j) {
                        continue;
                    }
                    AThrs[i] = new Thread(
                            new ATask(i, j, M, sValues)
                    );
                }
                for (int i = 0; i < size; i++) {
                    if (i == j) {
                        continue;
                    }
                    AThrs[i].start();
                }
                for (int i = 0; i < size; i++) {
                    if (i == j) {
                        continue;
                    }
                    AThrs[i].join();
                }

                Thread[][] BThrs = new Thread[size][size + 1];
                for (int i = 0; i < size; i++) {
                    if (i == j) {
                        continue;
                    }
                    for (int k = 0 ; k < size; k++) {
                        BThrs[i][k] = new Thread(
                                new BTaskLeft(j, i, k, M, sValues[i])
                        );
                    }
                    BThrs[i][size] = new Thread(
                            new BTaskRight(j, i, sValues[i], R)
                    );
                }

                for (int i = 0; i < size; i++) {
                    if (i == j) {
                        continue;
                    }
                    for (int k = 0 ; k < size + 1; k++) {
                        BThrs[i][k].start();
                    }
                }
                for (int i = 0; i < size; i++) {
                    if (i == j) {
                        continue;
                    }
                    for (int k = 0 ; k < size + 1; k++) {
                        BThrs[i][k].join();
                    }
                }
            }

            Thread[] CTasks = new Thread[size];
            for (int j = 0; j < size; j++) {
                CTasks[j] = new Thread(
                        new CTask(j, M, R)
                );
            }
            for (int j = 0; j < size; j++) {
                CTasks[j].start();
            }
            for (int j = 0; j < size; j++) {
                CTasks[j].join();
            }


            //printing result to console and file
            try {
                PrintStream output = new PrintStream(new File("out.txt"));

                System.out.println(size);
                output.println(size);
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        System.out.print(M[i][j] + " ");
                        output.print(M[i][j] + " ");
                    }
                    System.out.println();
                    output.println();
                }
                for (int j = 0; j < size; j++) {
                    System.out.print(R[j] + " ");
                    output.print(R[j] + " ");
                }
                System.out.println();
                output.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

