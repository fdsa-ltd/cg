package ltd.fdsa.code.extension;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.List;
import java.util.Map;

@Slf4j
public class StringDictMethod implements TemplateMethodModelEx {
    final Map<String, String> properties;

    public StringDictMethod(Map<String, String> properties) {
        this.properties = properties;
        log.info("------------------------------------------------------------------------");
        for (var entry : this.properties.entrySet()) {
            log.info("key:{}, value:{}", entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        switch (list.size()) {
            case 1:
                try {
                    return this.properties.get(list.get(0).toString());
                } catch (Exception ex) {
                    throw new TemplateModelException("No key: " + list.get(0));
                }
            case 2:
                return this.properties.getOrDefault(list.get(0).toString(), list.get(1).toString());
            default:
                throw new TemplateModelException("Wrong arguments");
        }
    }
}


