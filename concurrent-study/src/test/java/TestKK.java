import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuntian
 * @date 2020/3/14 0014 17:06
 * @description
 */
public class TestKK {

    @Test
     void  testKK(){
        List<Integer> bigData= Arrays.asList(1,2,3,4);
        List<List<Integer>> parts = Lists.partition(bigData, bigData.size() / 10==0?bigData.size():bigData.size() / 10);
        System.out.println(parts.size());

        List<String> list=new ArrayList<>();

        List<String> list2=list.stream().collect(Collectors.toList());
        System.out.println(list2);
    }
}
