package next.reflection;

import org.junit.Test;
import org.slf4j.Logger;

import java.lang.reflect.Method;

import static org.slf4j.LoggerFactory.getLogger;

public class Junit3TestRunner {
    private static final Logger log = getLogger(Junit3TestRunner.class);

    @Test
    public void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Junit3Test instance = clazz.newInstance();

        for (Method declaredMethod : declaredMethods) {
            String methodName = declaredMethod.getName();
            if (methodName.contains("test")) {
                declaredMethod.invoke(instance);
            }
        }
    }
}
