package ltd.fdsa.code.extension;

import com.google.common.base.Strings;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.List;

@Slf4j
public class SnakeCaseMethod implements TemplateMethodModelEx {

    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list.size() != 1) {
            throw new TemplateModelException("Wrong arguments");
        }
        var input = list.get(0).toString();
        return snake_case(input);
    }

    String snake_case(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        char ch = str.charAt(0);
        if (Character.isAlphabetic(ch)) {
            result.append(Character.toLowerCase(ch));
        }
        for (int i = 1; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append("_").append(Character.toLowerCase(ch));
            } else if (ch == '-') {
                result.append("_");
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }


}


