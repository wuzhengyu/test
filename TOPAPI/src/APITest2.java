import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.HotelsSearchRequest;
import com.taobao.api.response.HotelsSearchResponse;

/**
 * TaobaoClient为SDK的入口类，其实现类为DefaultTaobaoClient UserSellerGetRequest为API的请求参数封装类 UserSellerGetResponse 为API的返回结果封装类
 * 
 * 注：该接口是在沙箱环境下调用，获取的数据，也是沙箱中数据。若要获取线上环境，请填写自己创建应用获取过来的appkey,appsecret,并更改调用接口的环境地址,同时修改nick为淘宝登陆名
 * 
 * @author Administrator
 *
 */
public class APITest2 {
	protected static String url = "http://gw.api.taobao.com/router/rest";
	// 正式环境需要设置为:http://gw.api.taobao.com/router/rest
	// "http://gw.api.tbsandbox.com/router/rest";// 沙箱环境调用地址
	protected static String appkey = "23014906";
	protected static String secret = "c7a694b5a74db07041a17bef3f1dca33";
	protected static String sessionKey = "6102811bd3b911d6bc413ceac990e3bbff3f7cc63b633ae608342247"; // 如

	public static void testUserGet() throws ApiException {
		TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		HotelsSearchRequest req=new HotelsSearchRequest();
		req.setDomestic(true);
		req.setName("广州嘉逸皇冠酒店");
		req.setProvince(440000L);
		req.setCity(440100L);
		req.setDistrict(440106L);
		HotelsSearchResponse response = client.execute(req , sessionKey);
		System.out.println(response.getBody());
		
	}

	public static void main(String[] args) throws ApiException {
		APITest2.testUserGet();
	}

}