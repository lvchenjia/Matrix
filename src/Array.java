import java.util.Arrays;

public class Array {
    public static void main(String[] args) throws Exception {
//        double[] arr = {2, 5, 46, 7, 4, 5, 7, 5};
//        Vector v1 = new Vector(new double[]{1, 2, 2});
//        Vector v2 = new Vector(new double[]{3, 2, 1});
//
//        System.out.println(v1 + " + " + v2 + " = " + Vector.add(v1, v2));
//        System.out.println(v1);
//        System.out.println(v1 + " · " + v2 + " = " + Vector.multiply(v1, v2));
//        System.out.println(v1.length());
//        System.out.println(new Vector(arr).length());

        Matrix mat = new Matrix(new double[][]{{1,2,3,9},{2,-1,1,8},{3,0,-1,3}});
        System.out.println(mat);
//        System.out.println(mat.getSize());
//        System.out.println(mat.getColomn(1));
//        System.out.println(mat.getRow(1));
        System.out.println(mat.transpose());
        System.out.println(mat.multiply(mat,mat.transpose()));

        mat.mulNum(2);
        System.out.println(mat);
        mat.RREF();
        System.out.println(mat);

        //SquareMatrix mat1 = new SquareMatrix(new double[][]{{2,2,3},{2,-1,1},{3,0,-1}});
        SquareMatrix mat1 = new SquareMatrix(new double[][]{{2,-2,-3,-4,7},{-2,-1,1,-5,7},{3,5,-1,5,7},{-1,1,1,1,7},{-1,2,4,1,-7}});
        System.out.println(mat1);
        System.out.println(mat1.det());
        //mat1.REF();
        //System.out.println(mat1);
        System.out.println(mat1.withMatrix());
        System.out.println(mat1.inv());
//        SquareMatrix mat2 = new SquareMatrix(new double[][]{{0.0, -3.0, -2.0, -9.0},
//                {0.0, 0.0, -1.8, -13.0},
//                {0.0, 0.0, 0.0, 2.5},
//                {0.0, 0.0, 0.0, 0.0}});
//        System.out.println(mat2.det());
        System.out.println(mat1.multiply(mat1, mat1.inv()));
        Matrix m1 = new SquareMatrix(new double[][]{{0}}).transpose();
        System.out.println(m1);

    }
}




