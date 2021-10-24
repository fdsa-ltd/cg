package ltd.fdsa.code.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Column {
    String value() default "";

    String name() default "";

    String remark() default "";

    boolean nullable() default true;

    boolean primary() default false;

    int length() default 0;

    int scale() default 0;
}
