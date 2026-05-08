import java.util.ArrayList;
import java.util.List;

public class LC54_mt {
    // 方法: 模拟螺旋遍历(四边界)
    //   思路：维护top/but/lef/rig四个边界，按四个方向循环收缩
    //   顺序：左→右，上→下，右→左，下→上
    //   关键：每走完一个方向就收缩对应边界，并立即判断是否越界
    //   终止：任一边界交错(top > but 或 lef > rig)时结束
    //   复杂度：时间O(mn)，空间O(1)
    //          结果列表res不计入额外空间
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
