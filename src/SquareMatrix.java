public class SquareMatrix extends Matrix{
    int n;//方阵的阶

    SquareMatrix(double[][] matrix) throws Exception {
        super(matrix);
        if(matrix.length!=0){
            if(matrix.length!=matrix[0].length)throw new Exception();
        }
        n = matrix.length;
    }

    /**
     * 矩阵的迹(Trace)
     */
    public double trace(){
        double rst=0;
        for (int i = 0; i < n; i++) {
            rst += at(i, i);
        }
        return rst;
    }
    
    
    /**
     * 矩阵对应行列式求值
     */
    public double det(){
        double rst = 1;
        for (int i = 0; i < n; i++) {
            rst *= at(i, i);
        }
        if(rst==0)return 0;
        rst = 1;
        Matrix tmp = this.copy();
        tmp.REF();
        for (int i = 0; i < n; i++) {
            rst *= at(i, i);
        }
        return rst;
    }

    /**
     * 余子式
     */
    public SquareMatrix minor(int i, int j) throws Exception {
        double[][] m = new double[n-1][n-1];
        for (int k = 0; k < n; k++) {
            if(k==i)continue;
            for (int l = 0; l < n; l++) {
                if(l==j)continue;
                int x = k, y = l;
                if(x>i)x--;
                if(y>j)y--;
                m[x][y] = matrix[k][l];
            }
        }
        return new SquareMatrix(m);
    }

    /**
     * 代数余子式
     */
    public SquareMatrix complementMinor(int i, int j) throws Exception {
        if((i+j)%2==0){
            return minor(i, j);
        }else{
            SquareMatrix m =minor(i, j);
            if(m.getHeight()>0)m.rowMultiply(0, -1);
            return m;
        }
    }

    /**
     * 求伴随矩阵
     */
    public Matrix withMatrix() throws Exception {
        double[][] m = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.println(complementMinor(i, j));
                m[i][j] = complementMinor(i, j).det();
            }
        }
        return new Matrix(m).transpose();
    }

    /**
     * 求逆矩阵
     */
    public Matrix inverse() throws Exception {
        if(det()==0)throw new Exception("矩阵不可逆！");
        Matrix m = withMatrix();
        m.mulNum(1/det());
        return m;
    }
}
