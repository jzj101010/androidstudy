package z.j.j.androidstudy.dao.strust1;



import org.json.JSONException;
import org.json.JSONObject;

public interface BusinessResponse {
	public abstract void OnMessageResponse(String url, JSONObject jo)
			throws JSONException;
}