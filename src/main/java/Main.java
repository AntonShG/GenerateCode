import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class Main {

    public static void main(String[] args) {
        MethodInterceptor handler = new Interceptor();
        Entity entityProxy = (Entity) Enhancer.create(Entity.class,handler);
        System.out.println(entityProxy.getName());
    }
}
