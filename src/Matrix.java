public class Matrix {
    protected double[][] matrix;
    protected int row;
    protected int col;

    Matrix(double[][] matrix) {
        this.matrix = matrix;
        row = matrix.length;
        if (matrix.length == 0) col = 0;
        else col = matrix[0].length;
    }

    /**
     * 构造m×n元素全相同的矩阵
     *
     * @param m 行
     * @param n 列
     * @param k 元素k
     */
    Matrix(int m, int n, int k) {
        matrix = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = k;
            }
        }
        row = m;
        col = n;
    }

    /**
     * 构造m×n零矩阵
     *
     * @param m 行
     * @param n 列
     */
    Matrix(int m, int n) {
        matrix = new double[m][n];
        row = m;
        col = n;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getHeight() {
        return row;
    }

    public int getWidth() {
        return col;
    }

    public int getSize() {
        return row * col;
    }

    public Vector getRow(int i) {
        return new Vector(matrix[i]);
    }

    public Vector getColumn(int j) {
        double[] tmp = new double[row];
        for (int i = 0; i < row; ++i) {
            tmp[i] = matrix[i][j];
        }
        return new Vector(tmp);
    }

    public double at(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, double val) {
        matrix[i][j] = val;
    }


    //矩阵初等变换
    public void rowMultiply(int row, double k) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] *= k;
        }
    }

    public void rowMulAddTo(int row, int rowTarget, double k) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[rowTarget][i] += k * matrix[row][i];
        }
    }

    public void rowAddTo(int row, int rowTarget) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[rowTarget][i] += matrix[row][i];
        }
    }

    public void rowExchange(int row1, int row2) {
        for (int i = 0; i < matrix[row1].length; i++) {
            double tmp = matrix[row1][i];
            matrix[row1][i] = matrix[row2][i];
            matrix[row2][i] = tmp;
        }
    }

    /**
     * 化为行阶梯型(Row Echelon Form)
     */
    public void REF() {
        int nr = getHeight();
        int nc = getWidth();
        for (int r = 0; r < nr; r++) {
            boolean allZeros = true;
            for (int c = 0; c < nc; c++) {
                if (matrix[r][c] != 0) {
                    allZeros = false;
                    break;
                }
            }
            if (allZeros) {
                rowExchange(r, nr);
                nr--;
            }
        }
        int p = 0;
        while (p < nr && p < nc) {
            int r = 1;
            while (matrix[p][p] == 0) {
                if (p + r <= nr) {
                    p++;
                    continue;
                }
                r++;
            }
            for (r = 1; r < nr - p; r++) {
                if (matrix[p + r][p] != 0) {
                    double x = -1 * (matrix[p + r][p] / matrix[p][p]);
                    for (int c = p; c < nc; c++) {
                        matrix[p + r][c] = matrix[p][c] * x + matrix[p + r][c];
                    }
                }
            }
            p++;
        }
    }

    /**
     * 化为行最简形(Reduced Row Echelon Form)
     */
    public void RREF() {
        int lead = 0;
        for (int r = 0; r < row; r++) {
            if (col <= lead) return;
            int i = r;
            while (matrix[i][lead] == 0) {
                i++;
                if (row == i) {
                    i = r;
                    lead++;
                    if (col == lead) return;
                }
            }
            rowExchange(i, r);
            rowMultiply(r, (1 / matrix[r][lead]));
            for (int j = 0; j < row; j++) {
                if (j == r) continue;
                rowMulAddTo(r, j, -1 * matrix[j][lead]);
            }
            lead++;
        }
    }


    /**
     * 是否为同型矩阵
     */
    public boolean isHomomorphic(Matrix m1, Matrix m2) {
        return m2.getHeight() == m1.getHeight() && m2.getWidth() == m1.getWidth();
    }

    /**
     * 矩阵加法
     */
    public Matrix add(Matrix m1, Matrix m2) throws Exception {
        if (!isHomomorphic(m1, m2)) throw new Exception("非同型矩阵不可相加!");
        int row = m1.getHeight();
        int col = m1.getWidth();
        double[][] m = new double[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; j++) {
                m[i][j] = m1.at(i, j) + m2.at(i, j);
            }
        }
        return new Matrix(m);
    }

    /**
     * 矩阵乘法
     */
    public static Matrix multiply(Matrix m1, Matrix m2) throws Exception {
        if (m1.getWidth() != m2.getHeight()) throw new Exception("两矩阵不可相乘!");
        double[][] m = new double[m1.getHeight()][m2.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m2.getWidth(); j++) {
                for (int k = 0; k < m1.getWidth(); k++) {
                    m[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                }
            }
        }
        return new Matrix(m);
    }

    /**
     * 矩阵数乘
     */
    public void mulNum(double k) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] *= k;
            }
        }
    }

    /**
     * 矩阵转置
     */
    public Matrix transpose() {
        double[][] m = new double[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                m[j][i] = matrix[i][j];
            }
        }
        return new Matrix(m);
    }

    /**
     * 余子式
     */
    public Matrix minor(int i, int j) throws Exception {
        double[][] m = new double[row - 1][col - 1];
        for (int k = 0; k < row; k++) {
            if (k == i) continue;
            for (int l = 0; l < col; l++) {
                if (l == j) continue;
                int x = k, y = l;
                if (x > i) x--;
                if (y > j) y--;
                m[x][y] = matrix[k][l];
            }
        }
        return new Matrix(m);
    }

    /**
     * 代数余子式
     */
    public Matrix complementMinor(int i, int j) throws Exception {
        if ((i + j) % 2 == 0) {
            return minor(i, j);
        } else {
            Matrix m = minor(i, j);
            if (m.getHeight() > 0) m.rowMultiply(0, -1);
            return m;
        }
    }

    public Matrix copy() {
        double[][] newmat = new double[row][col];
        for (int i = 0; i < row; i++) {
            if (col >= 0) System.arraycopy(matrix[i], 0, newmat[i], 0, col);
        }
        return new Matrix(newmat);
    }

    public void print(int n) {
        System.out.println(toStringAccurate(n));
    }

    public void print() {
        print(2);
    }

    public void matFormPrint() {
        matFormPrint(8, 2);
    }

    public void matFormPrint(int colWidth, int decimal) {
        if (row == 1) {
            System.out.print("[");
            for (int j = 0; j < col; j++) {
                System.out.printf("%-" + colWidth + "." + decimal + "f", matrix[0][j]);
            }
            System.out.println("]");
            return;
        }

        for (int i = 0; i < row; i++) {
            if (i == 0) {
                System.out.print("┌ ");
            } else if (i == row - 1) {
                System.out.print("└ ");
            } else {
                System.out.print("│ ");
            }

            for (int j = 0; j < col; j++) {
                System.out.printf("%-" + colWidth + "." + decimal + "f", matrix[i][j]);
            }

            if (i == 0) {
                System.out.print("┐");
            } else if (i == row - 1) {
                System.out.print("┘");
            } else {
                System.out.print("│");
            }
            System.out.println();
        }
    }


    public String toStringAccurate(int decimal) {
        StringBuilder rst = new StringBuilder("[");
        for (int i = 0; i < matrix.length; i++) {
            if (i != 0) rst.append(" ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != matrix[i].length - 1)
                    rst.append(String.format("%." + decimal + "f", matrix[i][j])).append("\t");
                else rst.append(String.format("%." + decimal + "f", matrix[i][j]));
            }
            if (i != matrix.length - 1) rst.append("\n");
        }
        rst.append("]");
        return rst.toString();
    }

    @Override
    public String toString() {
        StringBuilder rst = new StringBuilder("{");
        for (int i = 0; i < matrix.length; i++) {
            if (i != 0) rst.append(" ");
            rst.append("{");
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != matrix[i].length - 1) rst.append(String.format("%.3f", matrix[i][j])).append(", ");
                else rst.append(String.format("%.3f", matrix[i][j]));
            }
            rst.append("}");
            if (i != matrix.length - 1) rst.append(",\n");
        }
        rst.append("}");
        return rst.toString();
    }
}
