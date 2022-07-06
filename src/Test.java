
public class Test {
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

        Matrix mat = new Matrix(new double[][]{{1, 2, 3, 9}, {2, -1, 1, 8}, {3, 0, -1, 3}});
        mat.matFormPrint();
        mat.transpose().matFormPrint();
        mat.minor(0, 0).minor(0, 0).transpose().matFormPrint();

        //SquareMatrix mat1 = new SquareMatrix(new double[][]{{2,2,3},{2,-1,1},{3,0,-1}});
        SquareMatrix mat1 = new SquareMatrix(new double[][]{{2, -2, -3, -4, 7}, {-2, -1, 1, -5, 7}, {3, 5, -1, 5, 7}, {-1, 1, 1, 1, 7}, {-1, 2, 4, 1, -7}});
        mat1.matFormPrint();
        System.out.println("×");
        mat1.matFormPrint();
        System.out.println("=");
        Matrix.multiply(mat1, mat1).matFormPrint();
    }
}




