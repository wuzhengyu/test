import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserSellerGetResponse;

/**
 * TaobaoClientΪSDK������࣬��ʵ����ΪDefaultTaobaoClient UserSellerGetRequestΪAPI�����������װ�� UserSellerGetResponse ΪAPI�ķ��ؽ����װ��
 * 
 * ע���ýӿ�����ɳ�价���µ��ã���ȡ�����ݣ�Ҳ��ɳ�������ݡ���Ҫ��ȡ���ϻ���������д�Լ�����Ӧ�û�ȡ������appkey,appsecret,�����ĵ��ýӿڵĻ�����ַ,ͬʱ�޸�nickΪ�Ա���½��
 * 
 * @author Administrator
 *
 */
public class APITest {
	protected static String url = "http://gw.api.taobao.com/router/rest";// ɳ�价�����õ�ַ
	// ��ʽ������Ҫ����Ϊ:http://gw.api.taobao.com/router/rest
	//"http://gw.api.tbsandbox.com/router/rest";// ɳ�价�����õ�ַ
	protected static String appkey = "23014906";
	protected static String appSecret = "c7a694b5a74db07041a17bef3f1dca33";
	protected static String sessionkey = "6102811bd3b911d6bc413ceac990e3bbff3f7cc63b633ae608342247"; // ��
																										// ɳ������ʺ�sandbox_c_1��Ȩ��õ���sessionkey

	public static void testUserGet() {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);// ʵ����TopClient��
		UserSellerGetRequest req = new UserSellerGetRequest();// ʵ��������API��Ӧ��Request��
		req.setFields("nick,user_id,type");
		//req.setNick("sandbox_c_1");
		
		UserSellerGetResponse response;
		try {
			response = client.execute(req, sessionkey); // ִ��API���󲢴�ӡ���
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