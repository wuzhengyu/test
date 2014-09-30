import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.HotelsSearchRequest;
import com.taobao.api.response.HotelsSearchResponse;

/**
 * TaobaoClientΪSDK������࣬��ʵ����ΪDefaultTaobaoClient UserSellerGetRequestΪAPI�����������װ�� UserSellerGetResponse ΪAPI�ķ��ؽ����װ��
 * 
 * ע���ýӿ�����ɳ�价���µ��ã���ȡ�����ݣ�Ҳ��ɳ�������ݡ���Ҫ��ȡ���ϻ���������д�Լ�����Ӧ�û�ȡ������appkey,appsecret,�����ĵ��ýӿڵĻ�����ַ,ͬʱ�޸�nickΪ�Ա���½��
 * 
 * @author Administrator
 *
 */
public class APITest2 {
	protected static String url = "http://gw.api.taobao.com/router/rest";
	// ��ʽ������Ҫ����Ϊ:http://gw.api.taobao.com/router/rest
	// "http://gw.api.tbsandbox.com/router/rest";// ɳ�价�����õ�ַ
	protected static String appkey = "23014906";
	protected static String secret = "c7a694b5a74db07041a17bef3f1dca33";
	protected static String sessionKey = "6102811bd3b911d6bc413ceac990e3bbff3f7cc63b633ae608342247"; // ��

	public static void testUserGet() throws ApiException {
		TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		HotelsSearchRequest req=new HotelsSearchRequest();
		req.setDomestic(true);
		req.setName("���ݼ��ݻʹھƵ�");
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