package mapper;

import consts.ColumnLabel;
import model.TestAfterDateModel;
import model.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestsAfterDateMapper {

    public static List<TestAfterDateModel> toTestAfterDateModels(Map<Integer, Map<String, Field>> source){
        List<TestAfterDateModel> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Field>> pair : source.entrySet()) {
            resultList.add(toTestAfterDateModel(pair.getValue()));
        }
        return resultList;
    }

    public static TestAfterDateModel toTestAfterDateModel(Map<String, Field> source){
        final TestAfterDateModel testAfterDateModel = new TestAfterDateModel();
        testAfterDateModel.setProject(source.get(ColumnLabel.PROJECT).getValue());
        testAfterDateModel.setTest(source.get(ColumnLabel.TEST).getValue());
        testAfterDateModel.setDate(source.get(ColumnLabel.DATE).getValue());
        return testAfterDateModel;
    }
}
