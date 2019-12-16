import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: yuntian
 * @Date: 2019/8/24 0024 23:15
 * @Description:
 */
public class TestY {

    private  int i;

    public TestY(int i) {
        this.i = i;
    }

    public static void main(String[] args)   {


        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        long start=System.currentTimeMillis();
        testLinkedList(1000000);
        System.out.println(System.currentTimeMillis()-start+"ms");
    }


    /**
     *  1000000  5ms   1000000 29ms   1000000 2144ms
     * @param count
     */
    public static void  testArryList(int count){
        List<TestY> list=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new TestY(i));
        }
    }

    /**
     *  1000000 9ms     1000000 26ms   1000000 6888ms
     * @param count
     */
    public static void  testLinkedList(int count){
        List<TestY> list=new LinkedList<>();
        for (int i = 0; i < count; i++) {
            list.add(new TestY(i));
        }
    }
}
