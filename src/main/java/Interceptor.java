
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Interceptor implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("getName")){
            return String.valueOf(methodProxy.invokeSuper(methodProxy,objects)).toUpperCase();
        }
        return methodProxy.invokeSuper(methodProxy,objects);
    }
}
