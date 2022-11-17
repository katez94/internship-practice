package mapper;

import consts.ColumnLabel;
import model.UniqueTestsAmountModel;
import model.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UniqueTestsAmountMapper {
    public static List<UniqueTestsAmountModel> toUniqueTestsAmountModels(Map<Integer, Map<String, Field>> source) {
        List<UniqueTestsAmountModel> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Field>> pair : source.entrySet()) {
            resultList.add(toUniqueTestsAmountModel(pair.getValue()));
        }
        return resultList;
    }

    public static UniqueTestsAmountModel toUniqueTestsAmountModel(Map<String, Field> source) {
        final UniqueTestsAmountModel uniqueTestsAmountModel = new UniqueTestsAmountModel();
        uniqueTestsAmountModel.setProject(source.get(ColumnLabel.PROJECT).getValue());
        uniqueTestsAmountModel.setTestsAmount(source.get(ColumnLabel.TESTS_AMOUNT).getValue());
        return uniqueTestsAmountModel;
    }
}
