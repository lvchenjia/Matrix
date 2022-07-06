public class SquareMatrix extends Matrix{
    int n;//方阵的阶

    SquareMatrix(double[][] matrix) throws Exception {
        super(matrix);
        if(matrix.length!=0){
            if(matrix.length!=matrix[0].length)throw new Exception("矩阵不为方阵！");
        }
        n = matrix.length;
    }

    SquareMatrix(Matrix matrix)throws Exception{
        super(matrix.matrix);
        if(matrix.getWidth()!=matrix.getHeight())throw new Exception("矩阵不为方阵！");
        n = matrix.getHeight();
    }

    /**
     * 构造n阶单位矩阵
     * @param n 阶
     */
    SquareMatrix(int n){
        super(n,n);
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 1;
        }
        this.n = n;
    }

    /**
     * 构造n阶纯量矩阵
     * @param n 阶
     * @param k 量
     */
    SquareMatrix(int n, int k){
        super(n,n);
        for (int i = 0; i < n; i++) {
            matrix[i][i] = k;
        }
        this.n = n;
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
        Matrix tmp = copy();
        tmp.REF();
        for (int i = 0; i < n; i++) {
            rst *= tmp.at(i, i);
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
    public SquareMatrix withMatrix() throws Exception {
        double[][] m = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = complementMinor(i, j).det();
            }
        }
        return new SquareMatrix(new Matrix(m).transpose());
    }

    /**
     * 求逆矩阵
     */
    public SquareMatrix inv() throws Exception {
        if(det()==0)throw new Exception("矩阵不可逆！");
        Matrix m = withMatrix();
        m.mulNum(1/det());
        return new SquareMatrix(m);
    }

    /**
     *  求特征值
     */
    public Matrix eig(SquareMatrix matrix){
        return MatrixEigenValue.EigenValue(matrix);
    }

    /**
     * 方阵的n次方
     */
    public SquareMatrix power(int n){
        if(n==0)return new SquareMatrix(this.n);
        Matrix rst = new Matrix(this.matrix);
        for (int i = 1; i < n; i++) {
            try {
                rst = rst.multiply(rst,this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return new SquareMatrix(rst);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
