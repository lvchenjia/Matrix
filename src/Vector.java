import java.util.Arrays;

import static java.lang.Math.sqrt;

public class Vector {
    private double[] vector;

    Vector(double[] vector) {
        this.vector = vector;
    }

    public int getLength() {
        return vector.length;
    }

    public double intAt(int i) {
        return vector[i];
    }

    public void setAt(int i, double num) {
        vector[i] = num;
    }

    public static Vector add(Vector v1, Vector v2) throws Exception {
        if (v1.getLength() != v2.getLength()) throw new Exception();
        double[] v = new double[v1.getLength()];
        for (int i = 0; i < v1.getLength(); i++) {
            v[i] = v1.intAt(i) + v2.intAt(i);
        }
        return new Vector(v);
    }

    public static int multiply(Vector v1, Vector v2) throws Exception {
        if (v1.getLength() != v2.getLength()) throw new Exception();
        int rst = 0;
        for (int i = 0; i < v1.getLength(); i++) {
            rst += v1.intAt(i) * v2.intAt(i);
        }
        return rst;
    }

    public double length() {
        double rst = 0;
        for (double i : vector) {
            rst += i * i;
        }
        return sqrt(rst);
    }

    public double length(Vector v) {
        double rst = 0;
        for (double i : v.vector) {
            rst += i * i;
        }
        return sqrt(rst);
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }
}