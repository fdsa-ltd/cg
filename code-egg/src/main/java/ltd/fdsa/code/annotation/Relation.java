//package ltd.fdsa.code.annotation;
//
//import ltd.fdsa.code.model.Association;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Target({ElementType.METHOD, ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface Relation {
//    String value() default "";
//
//    String name() default "";
//
//    String remark() default "";
//
//    Class<?> entity() default void.class;
//
//    String field() default "id";
//
//    Association.Type type() default Association.Type.One2One;
//}
