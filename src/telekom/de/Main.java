package telekom.de;

public class Main {
    public static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }

    public static double arctg(double x, double eps) {
        double result = 0, prev = 0;

        if (Math.abs(x) < 1.0f) {
            for (int i = 0; ; i++) {
                double pow = Math.pow(x, 2 * i + 1);
                if (i % 2 == 0) {
                    result += pow / (2 * i + 1);
                } else {
                    result -= pow / (2 * i + 1);
                }

                if (Math.abs(result - prev) < eps) {
                    return result;
                } else {
                    prev = result;
                }
            }
        } else {

            result =  (Math.PI / 2 * Math.signum(x));
            for (int i = 0; ; i++) {
                double pow = Math.pow(x, -(2 * i + 1));
                if (i % 2 == 0) {
                    result -= pow / (2 * i + 1);
                } else {
                    result += pow / (2 * i + 1);
                }
                if (Math.abs(result - prev) < eps)
                    return result;
                else
                    prev = result;
            }
        }
    }

    public static double sin(double x, double eps) {
        double result = 0, prev = 0;

        for (int i = 0; ; i++) {
            double pow = Math.pow(x, 2 * i + 1);
            if (i % 2 == 0) {
                result += pow / factorial(2 * i + 1);
            } else {
                result -= pow / factorial(2 * i + 1);
            }
            if (Math.abs(result - prev) < eps) {
                return result;
            } else {
                prev = result;
            }
        }
    }

    public static double sqrt(double x, double eps) {
        double prev = 1, result;
        for (; ; ) {
            result =  (0.5 * (prev + x / prev));
            if (Math.abs(result - prev) < eps) {
                return result;
            } else
                prev = result;
        }
    }

    public static void main(String[] args) {
        for (double x = 0.01f; x <= 0.06f; x += 0.005f) {
            double f_1 = calculateWithMyMethods(x);
            double f_2 = calculateWithDefaultMethods(x);
            String res = String.format("%e %e %e %e", x, f_1, f_2, Math.abs(f_1 - f_2));
            System.out.print(res);
            System.out.println();
        }
    }

    private static double calculateWithDefaultMethods(double x) {
        double tg =  Math.atan(x * 6.4f + 1.1f);
        double numerator =  Math.sqrt(1f + tg);
        double denominator =  Math.sin(2f * x + 1.05f);
        return numerator / denominator;
    }

    private static double calculateWithMyMethods(double x) {
        double tg = arctg(x * 6.4f + 1.1f, 1e-6f / 1.23f);
        double numerator = sqrt(1 + tg, 1e-6f / 0.3f);
        double denominator = sin(2f * x + 1.05f, 1e-6f / 5.28f);
        return numerator / denominator;
    }
}
