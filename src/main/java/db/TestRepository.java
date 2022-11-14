package db;

import model.TestModel;
import java.util.List;
import java.util.Map;

public interface TestRepository {

    List<TestModel> getMinWorkingTimeForEachTest();
    Map<String, Integer> getUniqueTestsAmountForEachProject();
    List<TestModel> getBrowsersExecutionAmount();
    List<TestModel> getTestsAfterDate(String date);

}
