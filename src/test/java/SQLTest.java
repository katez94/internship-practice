import repository.TestRepository;
import model.BrowserExecutionAmountModel;
import model.MinWorkingTimeModel;
import model.TestAfterDateModel;
import model.UniqueTestsAmountModel;
import org.testng.annotations.Test;
import repository.TestRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class SQLTest {
    private static final String DATE = "2015-11-07";

    @Test
    public void minWorkingTimeForEachTestTest() {
        final TestRepository testRepository = new TestRepositoryImpl();
        final List<MinWorkingTimeModel> minWorkingTimeForEachTest = testRepository.getMinWorkingTimeForEachTest();
        TestHelper.printAsTable(minWorkingTimeForEachTest);
    }

    @Test
    public void uniqueTestsAmountForEachProjectTest() {
        final TestRepository testRepository = new TestRepositoryImpl();
        final List<UniqueTestsAmountModel> uniqueTestsAmountModels = testRepository.getUniqueTestsAmountForEachProject();
        TestHelper.printAsTable(uniqueTestsAmountModels);
    }

    @Test
    public void getTestsAfterDate() throws SQLException {
        final TestRepository testRepository = new TestRepositoryImpl();
        final List<TestAfterDateModel> testsAfterDate = testRepository.getTestsAfterDate(DATE);
        TestHelper.printAsTable(testsAfterDate);
    }

    @Test
    public void getBrowsersExecutionAmountTest() {
        final TestRepository testRepository = new TestRepositoryImpl();
        final List<BrowserExecutionAmountModel> browsersExecutionAmount = testRepository.getBrowsersExecutionAmount();
        TestHelper.printAsTable(browsersExecutionAmount);
    }
}
