public class LC73_mt {
    public void setZeroes(int[][] matrix) {
        //用数组记录零的位置
        int m = matrix.length;
        int n = matrix[0].length;
        //true表示此行需设0
        boolean row[] = new boolean[m];
        //true表示此行需设0
        boolean col[] = new boolean[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        //置零
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(row[i] || col[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        //原地交换,用第0行和第0列分别表示这一行这一列是否置零,额外声明zeroRow表示第0行是否为0
        int zeroRow = 1;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    //行
                    if (i > 0) {
                        matrix[i][0] = 0;
                    } else {
                        zeroRow = 0;
                    }
                    //列
                    matrix[0][j] = 0;
                }
            }
        }

        //置零,一定不能先处理第0行第0列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //单独处理第0列,一定先处理列,否则matrix[0][0]被zeroRow会有逻辑问题
        for (int i = 0; i < m; i++) {
            if (matrix[0][0] == 0) {
                matrix[i][0] = 0;
            } else {
                break;
            }
        }
        //单独处理第0行
        for (int i = 0; i < n; i++) {
            if (zeroRow == 0) {
                matrix[0][i] = 0;
            } else {
                break;
            }
        }

        // for (int i = 0; i < matrix.length; i++) {
        //     for (int j = 0; j < matrix[0].length; j++) {
        //         System.out.print(matrix[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

    }
}
