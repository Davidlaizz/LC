import javafx.scene.control.Alert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class testany {
    public static void main(String[] args) {
        String s = "adf";
        List<Integer> l = new ArrayList<>();
        l.add(1);
        System.out.println(l);
        modify(s, l);
        System.out.println(s);
        System.out.println(l);
    }
    public static void modify (String tmp, List<Integer> l) {
        tmp = "a";
//        l = new ArrayList<>();
        l.add(2);
        System.out.println(l);
    }
}
