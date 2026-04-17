package config;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.io.InputStream;


public class ConfigLoader<T> {
    private final Class<T> type;
    private T config;
    private final YAMLMapper yamlMapper;

    public ConfigLoader(Class<T> type) {
        this.type = type;
        this.yamlMapper = new YAMLMapper();
    }

    public void loadFromFile(String fileName) throws IOException {
        try ( InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            config = yamlMapper.readValue(is, type);
        }
    }

    public T getConfig(){
        return this.config;
    }
}
