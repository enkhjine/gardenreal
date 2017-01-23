package hospital.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Label {

	//Answers
	public String[] answers() default "";
	//Label name
	public String label() default "";
	// 
	public String fieldType() default "text";
	// m - medicalHistory , p - pain , c - checkup
	public String labelType() default "";
}