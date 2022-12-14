package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PARAMETER})
public @interface RequiresPermission {
  String[] allOf() default {};
  
  String[] anyOf() default {};
  
  boolean conditional() default false;
  
  String value() default "";
  
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
  public static @interface Read {
    RequiresPermission value() default @RequiresPermission;
  }
  
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
  public static @interface Write {
    RequiresPermission value() default @RequiresPermission;
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/androidx/annotation/RequiresPermission.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */