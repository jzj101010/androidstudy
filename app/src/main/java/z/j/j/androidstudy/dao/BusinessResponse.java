package z.j.j.androidstudy.dao;



import org.json.JSONException;
import org.json.JSONObject;

public interface BusinessResponse {
	public abstract void OnMessageResponse(String url, JSONObject jo)
			throws JSONException;
}