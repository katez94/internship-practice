import lombok.extern.slf4j.Slf4j;
import model.TestModel;

import java.util.List;
import java.util.Map;

@Slf4j
public class TestHelper {
    public static void printAsTable(List<TestModel> list) {
        for (TestModel testModel : list) {
            log.info(testModel.toString());
        }
    }

    public static void printAsTable(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            log.info(entry.getKey() + "   " + entry.getValue());
        }
    }
}
