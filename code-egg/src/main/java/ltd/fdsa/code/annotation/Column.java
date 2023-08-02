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

    Class<?> r() default void.class;

    String rid() default "id";

    String rnm() default "";

    Relation rtp() default Relation.One2One;

    public enum Relation {
        One2One,
        One2Many,
        Many2One,
        Many2Many,
    }
}
