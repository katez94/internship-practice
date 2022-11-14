import db.TestRepository;
import model.TestModel;
import org.testng.annotations.Test;
import repository.TestRepositoryImpl;

import java.util.List;
import java.util.Map;

public class SQLTest {
    private static final String DATE = "2015-11-07";

    @Test
    public void minWorkingTimeForEachTestTest() {
        final TestRepository testRepository = new TestRepositoryImpl();
        final List<TestModel> minWorkingTimeForEachTest = testRepository.getMinWorkingTimeForEachTest();
        TestHelper.printAsTable(minWorkingTimeForEachTest);
    }

    @Test
    public void uniqueTestsAmountForEachProjectTest() {
        final TestRepository testRepository = new TestRepositoryImpl();
        final Map<String, Integer> map = testRepository.getUniqueTestsAmountForEachProject();
        TestHelper.printAsTable(map);
    }

    @Test
    public void getTestsAfterDate() {
        final TestRepository testRepository = new TestRepositoryImpl();
        final List<TestModel> testsAfterDate = testRepository.getTestsAfterDate(DATE);
        TestHelper.printAsTable(testsAfterDate);
    }

    @Test
    public void getBrowsersExecutionAmountTest() {
        final TestRepository testRepository = new TestRepositoryImpl();
        final List<TestModel> browsersExecutionAmount = testRepository.getBrowsersExecutionAmount();
        TestHelper.printAsTable(browsersExecutionAmount);
    }
}
