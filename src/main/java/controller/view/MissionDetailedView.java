package controller.view;

import org.glassfish.hk2.api.AnnotationLiteral;
import org.glassfish.jersey.message.filtering.EntityFiltering;

import java.lang.annotation.*;

/**
 * Created by hhx on 2017/4/5.
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EntityFiltering
public @interface MissionDetailedView {
    public static class Factory extends AnnotationLiteral<ProjectDetailedView> implements MissionDetailedView {

        private Factory() {
        }

        public static MissionDetailedView get() {
            return new Factory();
        }
    }
}
