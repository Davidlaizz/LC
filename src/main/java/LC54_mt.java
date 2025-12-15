import java.util.ArrayList;
import java.util.List;

public class LC54_mt {
    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0;
        int but = matrix.length - 1;
        int lef = 0;
        int rig = matrix[0].length - 1;
        List<Integer> res = new ArrayList<>();
        //模拟螺旋
        while(true){
            //左到右
            for(int i = lef; i <= rig; i++){
                res.add(matrix[top][i]);
            }
            top++;
            if(top > but) break;

            //上到下
            for(int i = top; i <= but; i++){
                res.add(matrix[i][rig]);
            }
            rig--;
            if(rig < lef) break;

            //右到左
            for(int i = rig; i >= lef; i--){
                res.add(matrix[but][i]);
            }
            but--;
            if(top > but) break;

            //下到上
            for(int i = but; i >= top; i--){
                res.add(matrix[i][lef]);
            }
            lef++;
            if(rig < lef) break;

        }
        return res;
    }
}
