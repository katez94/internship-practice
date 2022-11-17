package repository;

import model.BrowserExecutionAmountModel;
import model.MinWorkingTimeModel;
import model.TestAfterDateModel;
import model.UniqueTestsAmountModel;

import java.sql.SQLException;
import java.util.List;

public interface TestRepository {
    List<MinWorkingTimeModel> getMinWorkingTimeForEachTest();
    List<UniqueTestsAmountModel> getUniqueTestsAmountForEachProject();
    List<BrowserExecutionAmountModel> getBrowsersExecutionAmount();
    List<TestAfterDateModel> getTestsAfterDate(String date) throws SQLException;
}
