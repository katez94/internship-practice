import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TestHelper {
    public static void printAsTable(List<? extends Object> list) {
        for (Object testModel : list) {
            log.info(testModel.toString());
        }
    }
}
