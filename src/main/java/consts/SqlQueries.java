package consts;

public class SqlQueries {
    private static final String MIN_WORKING_TIME_FOR_EACH_TEST_QUERY = "SELECT project.name AS 'PROJECT', test.name AS 'TEST', timestampdiff(SECOND, test.start_time, test.end_time) AS 'MIN_WORKING_TIME' FROM test JOIN project ON project.id = test.project_id";
    private static final String UNIQUE_TEST_AMOUNT_FOR_EACH_PROJECT_QUERY = "SELECT project.name AS 'PROJECT', count(test.name) AS 'TESTS_AMOUNT' FROM test JOIN project ON project.id = test.project_id GROUP BY project.name";
    private static final String BROWSER_EXECUTION_AMOUNT_QUERY = "SELECT count(browser) AS 'BROWSERS' FROM test where browser = 'chrome' UNION SELECT count(browser) FROM test where browser = 'firefox'";
    private static final String TESTS_AFTER_DATE_QUERY = "SELECT project.name AS 'PROJECT', test.name AS 'TEST', test.start_time AS 'DATE' FROM test JOIN project ON project.id = test.project_id WHERE (test.start_time >= '%1$s') ORDER BY test.name AND project.name";

    public static String getTestsAfterDateQuery(String date){
        return String.format(TESTS_AFTER_DATE_QUERY,date);
    }

    public static String getMinWorkingTimeForEachTestQuery() {
        return MIN_WORKING_TIME_FOR_EACH_TEST_QUERY;
    }

    public static String getUniqueTestAmountForEachProjectQuery() {
        return UNIQUE_TEST_AMOUNT_FOR_EACH_PROJECT_QUERY;
    }

    public static String getBrowserExecutionAmountQuery() {
        return BROWSER_EXECUTION_AMOUNT_QUERY;
    }
}
