package day5.데이터내보내기;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataExporter {
    private ExportStrategy exportStrategy;
    private Map<String, ExportStrategy> formatStrategies = new HashMap<>();

    public DataExporter(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    public void registerFormatStrategy(String format, ExportStrategy strategy){
        formatStrategies.put(format, strategy);
    }

    private String getFileExtension(String filePath){
        int lastDoPos = filePath.lastIndexOf('.');
        if (lastDoPos > 0 && lastDoPos < filePath.length() - 1){
            return filePath.substring(lastDoPos + 1).toLowerCase();
        }
        return "";
    }

    public boolean exportData(List<Map<String, Object>> data, String filePath){
        String extension  =  getFileExtension(filePath);
        ExportStrategy strategy = formatStrategies.getOrDefault(extension, exportStrategy);
        return strategy.export(data, filePath);
    }
}
