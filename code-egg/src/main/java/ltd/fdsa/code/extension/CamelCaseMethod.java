package ltd.fdsa.code.extension;

import com.google.common.base.Strings;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.List;

@Slf4j
public class CamelCaseMethod implements TemplateMethodModelEx {

    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list.size() != 1) {
            throw new TemplateModelException("Wrong arguments");
        }
        var input = list.get(0).toString();
        return camel_case(input);
    }

    String camel_case(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        var ch = str.charAt(0);
        if (Character.isAlphabetic(ch)) {
            result.append(Character.toLowerCase(ch));
        }
        for (int i = 1; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch == '-' || ch == '_') {
                i++;
                result.append(Character.toUpperCase(str.charAt(i)));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }


}


