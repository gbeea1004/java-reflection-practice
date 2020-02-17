package next.reflection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug("<클래스 명>");
        logger.debug(clazz.getName());

        logger.debug("<필드 정보>");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            logger.debug("접근제어자 : {}, 타입 : {}, 필드 명 : {}",field.getModifiers(), field.getType(), field.getName());
        }

        logger.debug("<생성자 정보>");
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            logger.debug(declaredConstructor.getName());
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                logger.debug("파라미터 타입 : {}", parameterType.getTypeName());
            }
        }

        logger.debug("<메서드 정보>");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            logger.debug("접근제어자 : {}, 메서드 명 : {}", declaredMethod.getModifiers(), declaredMethod.getName());
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                logger.debug("파라미터 타입 : {}", parameterType.getTypeName());
                logger.debug("리턴 타입 : {}", declaredMethod.getReturnType().getTypeName());
            }
        }
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }
    }

    @Test
    public void privateFieldAccess() throws Exception {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
        Student student = clazz.newInstance();
        Field name = clazz.getDeclaredField("name");
        Field age = clazz.getDeclaredField("age");

        name.setAccessible(true);
        age.setAccessible(true);

        name.set(student, "건희");
        age.set(student, 28);

        logger.debug("이름 : {}", student.getName());
        logger.debug("나이 : {}", student.getAge());
    }
}
