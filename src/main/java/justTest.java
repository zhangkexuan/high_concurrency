import java.sql.PseudoColumnUsage;

/**
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/9 10:49
 */
public class justTest {
    public Object instance = null;

    public static void main(String[] args) {
        justTest oA = new justTest();
        justTest oB = new justTest();
        oA.instance = oB;
        oB.instance = oA;

        oA=null;
        oB=null;

        System.gc();
    }
}
