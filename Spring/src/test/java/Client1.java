import com.tao.service.CglibProxy;
import com.tao.service.Host;
import com.tao.service.ProxyInvocationHandler;
import com.tao.service.Rent;

public class Client1 {
    public static void main(String[] args) {
        //真实角色
        Host host = new Host();
        //代理实例的调用处理程序
        CglibProxy cglibProxy = new CglibProxy(new Host());
        Rent proxy1 = (Rent) cglibProxy.getProxy();
        proxy1.rent();
    }
}