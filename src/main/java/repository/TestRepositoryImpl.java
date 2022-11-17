package repository;

import consts.SqlQueries;
import mapper.BrowserExecutionAmountMapper;
import mapper.MinWorkingTimeMapper;
import mapper.TestsAfterDateMapper;
import mapper.UniqueTestsAmountMapper;
import model.*;

import java.util.List;
import java.util.Map;

import static config.DbUtil.executeQuery;

public class TestRepositoryImpl implements TestRepository {

    @Override
    public List<MinWorkingTimeModel> getMinWorkingTimeForEachTest() {
        Map<Integer, Map<String, Field>> queryResult = executeQuery(SqlQueries.getMinWorkingTimeForEachTestQuery());
        return MinWorkingTimeMapper.toMinWorkingTimeModels(queryResult);
    }

    @Override
    public List<UniqueTestsAmountModel> getUniqueTestsAmountForEachProject() {
        Map<Integer, Map<String, Field>> queryResult = executeQuery(SqlQueries.getUniqueTestAmountForEachProjectQuery());
        return UniqueTestsAmountMapper.toUniqueTestsAmountModels(queryResult);
    }

    @Override
    public List<TestAfterDateModel> getTestsAfterDate(String date) {
        final Map<Integer, Map<String, Field>> queryResult = executeQuery(SqlQueries.getTestsAfterDateQuery(date));
        return TestsAfterDateMapper.toTestAfterDateModels(queryResult);
    }

    @Override
    public List<BrowserExecutionAmountModel> getBrowsersExecutionAmount() {
        final Map<Integer, Map<String, Field>> queryResult = executeQuery(SqlQueries.getBrowserExecutionAmountQuery());
        return BrowserExecutionAmountMapper.toBrowserExecutionAmountModels(queryResult);
    }
}
