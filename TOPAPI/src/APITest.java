import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserSellerGetResponse;

/**
 * TaobaoClient为SDK的入口类，其实现类为DefaultTaobaoClient UserSellerGetRequest为API的请求参数封装类 UserSellerGetResponse 为API的返回结果封装类
 * 
 * 注：该接口是在沙箱环境下调用，获取的数据，也是沙箱中数据。若要获取线上环境，请填写自己创建应用获取过来的appkey,appsecret,并更改调用接口的环境地址,同时修改nick为淘宝登陆名
 * 
 * @author Administrator
 *
 */
public class APITest {
	protected static String url = "http://gw.api.taobao.com/router/rest";// 沙箱环境调用地址
	// 正式环境需要设置为:http://gw.api.taobao.com/router/rest
	//"http://gw.api.tbsandbox.com/router/rest";// 沙箱环境调用地址
	protected static String appkey = "23014906";
	protected static String appSecret = "c7a694b5a74db07041a17bef3f1dca33";
	protected static String sessionkey = "6102811bd3b911d6bc413ceac990e3bbff3f7cc63b633ae608342247"; // 如
																										// 沙箱测试帐号sandbox_c_1授权后得到的sessionkey

	public static void testUserGet() {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);// 实例化TopClient类
		UserSellerGetRequest req = new UserSellerGetRequest();// 实例化具体API对应的Request类
		req.setFields("nick,user_id,type");
		//req.setNick("sandbox_c_1");
		
		UserSellerGetResponse response;
		try {
			response = client.execute(req, sessionkey); // 执行API请求并打印结果
			System.out.println("result:" + response.getBody());

		} catch (ApiException e) {
			// deal error
			System.err.println("error");
		}
	}

	public static void main(String[] args) {
		APITest.testUserGet();
	}

}