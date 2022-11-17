package mapper;

import consts.ColumnLabel;
import model.MinWorkingTimeModel;
import model.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MinWorkingTimeMapper {
    public static List<MinWorkingTimeModel> toMinWorkingTimeModels(Map<Integer, Map<String, Field>> source) {
        List<MinWorkingTimeModel> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Field>> pair : source.entrySet()) {
            resultList.add(toMinWorkingTimeModel(pair.getValue()));
        }
        return resultList;
    }

    public static MinWorkingTimeModel toMinWorkingTimeModel(Map<String, Field> source) {
        final MinWorkingTimeModel minWorkingTimeModel = new MinWorkingTimeModel();
        minWorkingTimeModel.setProject(source.get(ColumnLabel.PROJECT).getValue());
        minWorkingTimeModel.setTest(source.get(ColumnLabel.TEST).getValue());
        minWorkingTimeModel.setMinWorkingTime(source.get(ColumnLabel.MIN_WORKING_TIME).getValue());
        return minWorkingTimeModel;
    }
}
