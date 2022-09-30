import com.tao.service.Host;
import com.tao.service.ProxyInvocationHandler;
import com.tao.service.Rent;

public class Client {
    public static void main(String[] args) {
        //真实角色
        Host host = new Host();
        //代理实例的调用处理程序
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(host);
        //将真实角色放置进去！
        Rent proxy = (Rent)pih.getProxy();
        //动态生成对应的代理类！
        proxy.rent();
    }
}