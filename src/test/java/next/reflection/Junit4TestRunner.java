package next.reflection;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Junit4Test instance = clazz.newInstance();
        Arrays.stream(declaredMethods)
              .filter(m -> m.isAnnotationPresent(MyTest.class))
              .forEach(m -> {
                  try {
                      m.invoke(instance);
                  } catch (IllegalAccessException e) {
                      e.printStackTrace();
                  } catch (InvocationTargetException e) {
                      e.printStackTrace();
                  }
              });
    }
}
