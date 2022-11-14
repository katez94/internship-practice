package repository;

import db.ColumnLabel;
import db.DbUtil;
import db.TestRepository;
import model.TestModel;

import java.sql.*;
import java.util.*;

public class TestRepositoryImpl implements TestRepository {

    @Override
    public List<TestModel> getMinWorkingTimeForEachTest() {
        List<TestModel> resultList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection(); final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT project.name AS 'PROJECT', test.name AS 'TEST', timestampdiff(SECOND, test.start_time, test.end_time) AS 'MIN_WORKING_TIME' FROM test JOIN project ON project.id = test.project_id");
            while (resultSet.next()) {
                TestModel testModel = new TestModel();
                testModel.setProject(resultSet.getString(ColumnLabel.PROJECT));
                testModel.setTest(resultSet.getString(ColumnLabel.TEST));
                testModel.setMinWorkingTime(resultSet.getLong(ColumnLabel.MIN_WORKING_TIME));
                resultList.add(testModel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Map<String, Integer> getUniqueTestsAmountForEachProject() {
        Map<String, Integer> resultMap = new HashMap<>();
        try (Connection connection = DbUtil.getConnection(); final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT project.name AS 'PROJECT', count(test.name) AS 'TESTS_COUNT' FROM test JOIN project ON project.id = test.project_id GROUP BY project.name");
            while (resultSet.next()) {
                final TestModel testModel = new TestModel();
                testModel.setProject(resultSet.getString(ColumnLabel.PROJECT));
                testModel.setTestCount(resultSet.getInt(ColumnLabel.TESTS_COUNT));
                resultMap.put(testModel.getProject(), testModel.getTestCount());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public List<TestModel> getTestsAfterDate(String date) {
        List<TestModel> resultList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection(); final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(String.format("SELECT project.name AS 'PROJECT', test.name AS 'TEST', test.start_time AS 'DATE' FROM test JOIN project ON project.id = test.project_id WHERE (test.start_time >= '%1$s') ORDER BY test.name AND project.name", date));
            while (resultSet.next()) {
                TestModel testModel = new TestModel();
                testModel.setProject(resultSet.getString(ColumnLabel.PROJECT));
                testModel.setTest(resultSet.getString(ColumnLabel.TEST));
                testModel.setDate(resultSet.getDate(ColumnLabel.DATE));
                resultList.add(testModel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<TestModel> getBrowsersExecutionAmount() {
        List<TestModel> resultList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection(); final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT count(browser) AS 'BROWSERS' FROM test where browser = 'chrome' UNION SELECT count(browser) FROM test where browser = 'firefox'");
            while (resultSet.next()) {
                TestModel testModel = new TestModel();
                testModel.setBrowserUsageAmount(resultSet.getInt(ColumnLabel.BROWSERS));
                resultList.add(testModel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }
}
