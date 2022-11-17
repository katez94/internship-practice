package mapper;

import consts.ColumnLabel;
import model.BrowserExecutionAmountModel;
import model.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrowserExecutionAmountMapper {
    public static List<BrowserExecutionAmountModel> toBrowserExecutionAmountModels(Map<Integer, Map<String, Field>> source) {
        List<BrowserExecutionAmountModel> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Field>> pair : source.entrySet()) {
            resultList.add(toBrowserExecutionAmountModel(pair.getValue()));
        }
        return resultList;
    }

    public static BrowserExecutionAmountModel toBrowserExecutionAmountModel(Map<String, Field> source) {
        final BrowserExecutionAmountModel browserExecutionAmountModel = new BrowserExecutionAmountModel();
        browserExecutionAmountModel.setBrowserUsageAmount(source.get(ColumnLabel.BROWSERS).getValue());
        return browserExecutionAmountModel;
    }
}
