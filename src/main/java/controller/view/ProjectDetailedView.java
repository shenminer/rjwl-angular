package controller.view;

import org.glassfish.hk2.api.AnnotationLiteral;
import org.glassfish.jersey.message.filtering.EntityFiltering;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hhx on 2017/4/5.
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EntityFiltering
public @interface ProjectDetailedView {
    public static class Factory extends AnnotationLiteral<ProjectDetailedView> implements ProjectDetailedView {

        private Factory() {
        }

        public static ProjectDetailedView get() {
            return new Factory();
        }
    }
}
