package z.j.j.androidstudy.dao;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Gravity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class BaseDao implements BusinessResponse {
    protected Context mContext;
//    protected SharedPreferences shared;
//    protected SharedPreferences.Editor editor;
//    protected List<BusinessResponse> businessResponses = new ArrayList<BusinessResponse>();
////    protected ProgressDialog myProgressDialog ;
//    private JSONObject jo;
//
    public BaseDao(Context context) {
        mContext = context;
//        myProgressDialog = new ProgressDialog(mContext, "请稍等");
    }
//
//    protected void saveCache() {
//        return;
//    }
//
//    protected void cleanCache() {
//        return;
//    }
//
//    public void addResponseListener(BusinessResponse listener) {
//        if (!businessResponses.contains(listener)) {
//            businessResponses.add(listener);
//        }
//    }
//
//    public void removeResponseListener(BusinessResponse listener) {
//        businessResponses.remove(listener);
//    }
//
//    public void callbak(JSONObject jo, String e) {
//
//        if (jo == null) {
//            ToastView toast = new ToastView(mContext, "网络错误，请检查网络设置");
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
//            return;
//        } else {
//
//            if (jo.optString("succeed").equals("0")) {
//                if (jo.optString("error_desc") != null && !jo.optString("error_desc").equals("")) {
//                    ToastView toast = new ToastView(mContext, jo.optString("error_desc"));
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
//                    return;
//                }
//
//            }
////			if(jo.optString("succeed").equals("0")){
////				if(jo.optString("error_desc")!=null&&!jo.optString("error_desc").equals("")){
////					ToastView toast = new ToastView(mContext, jo.optString("error_desc"));
////					toast.setGravity(Gravity.CENTER, 0, 0);
////					toast.show();
////					return;
////				}
////
////			}
//        }
//    }
//
//    public void onError(String ercode) {
//        if ("request failed , reponse's code is : 401".equals(ercode)) {
////            ToastView toast = new ToastView(mContext, "请登录后再进行操作");
////            toast.setGravity(Gravity.CENTER, 0, 0);
////            toast.show();
////            Intent intent = new Intent(mContext, LoginActivity.class);
////            mContext.startActivity(intent);
//            shared = mContext.getSharedPreferences("usertoken", 0);
//            editor = shared.edit();
//            shared.edit().putString("token", "").commit();
//            editor.commit();
//        } else {
////            ToastView toast = new ToastView(mContext, "数据获取失败,请检查网络设置");
////            toast.setGravity(Gravity.CENTER, 0, 0);
////            toast.show();
//        }
//    }
//
    @Override
    public void OnMessageResponse(String url, JSONObject jo)
            throws JSONException {
//        for (BusinessResponse businessResponse : businessResponses) {
//            businessResponse.OnMessageResponse(url, jo);
//        }
    }
//
//	public void post(final String url, String content) {
//        myProgressDialog.show();
//        		OkHttpUtils.postString().mediaType(MediaType.parse("application/json"))
//				.url(Utils.SERVER_PRODUCTION + url)
//				.addHeader("Authorization", Utils.usertoken(mContext))
//				.content(content).build().execute(new StringCallback() {
//			@Override
//			public void onError(Call call, Exception e, int i) {
//				BaseDao.this.onError(e.getMessage());
//                myProgressDialog.dismiss();
//			}
//
//			@Override
//			public void onResponse(String s, int i) {
//				try {
//					JSONObject jsonObject = new JSONObject(s);
//					BaseDao.this.callbak(jsonObject,"");
//					BaseDao.this.OnMessageResponse(url, jsonObject);
//                    myProgressDialog.dismiss();
//				} catch (Exception e) {
//					e.printStackTrace();
//                    myProgressDialog.dismiss();
//				}
//			}
//		});
//	}
//    public void post(final String url, String content,StringCallback callback) {
//        myProgressDialog.show();
//        OkHttpUtils.postString().mediaType(MediaType.parse("application/json"))
//                .url(Utils.SERVER_PRODUCTION + url)
//                .addHeader("Authorization", Utils.usertoken(mContext))
//                .content(content).build().execute(callback);
//    }
//    public void get(final String url, Map map,StringCallback callback) {
//        myProgressDialog.show();
//        OkHttpUtils.get().addHeader("content-type", "application/json;charset=UTF-8")
//                .addHeader("authorization", Utils.usertoken(mContext))
//                .url(Utils.SERVER_PRODUCTION + url).params(map).build()
//                .execute(callback);
//    }
//    public void get(final String url, Map map) {
//        myProgressDialog.show();
//        OkHttpUtils.get().addHeader("content-type", "application/json;charset=UTF-8")
//                .addHeader("authorization", Utils.usertoken(mContext))
//                .url(Utils.SERVER_PRODUCTION + url).params(map).build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int i) {
//                        BaseDao.this.onError(e.getMessage());
//                        myProgressDialog.dismiss();
//                    }
//
//                    @Override
//                    public void onResponse(String s, int i) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(s);
//                            BaseDao.this.callbak(jsonObject,"");
//                            BaseDao.this.OnMessageResponse(url, jsonObject);
//                            myProgressDialog.dismiss();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            myProgressDialog.dismiss();
//                        }
//                    }
//                });
//    }
//    public void requestSuccessAction(String result, String url) {
//        if (checkResult(result)) {
//            try {
//                JSONObject obj = new JSONObject(result);
//                OnMessageResponse(url, obj);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    protected boolean checkResult(String result) {
//        BaseBean baseBean = GsonUtil.toBean(result, BaseBean.class);
//        if (baseBean != null) {
//            if (BaseBean.State.REQUEST_FAILURE.equals(baseBean.getSucceed())) {
//                if (BaseBean.ErrorCode.COMMON_ERROR.equals(baseBean.getError_code())) {
//                    if (!TextUtils.isEmpty(baseBean.getError_desc())) {
//                        Utils.showToast(baseBean.getError_desc());
//                    }
//                } else if (BaseBean.ErrorCode.LOGOUT.equals(baseBean.getError_code())) {
//                    Utils.showToast("请登录后再进行操作");
//                    SpHelper.getUser().edit().clear().apply();
//                    SpHelper.getSaveData().edit().clear().apply();
//                    final BaseActivity activity = AppManager.getAppManager().currentActivity();
//                    if (activity instanceof LoginActivity){
//                        return false;
//                    }
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(mContext, LoginActivity.class);
//                            mContext.startActivity(intent);
//                        }
//                    });
//                } else {
//                    Utils.showToast("服务器错误");
//                }
//                return false;
//            }
//        } else {
//            Utils.showToast("服务器错误");
//            return false;
//        }
//        return true;
//    }
//
//    public void setCanceledOnTouchOutside(boolean cancel) {
//        myProgressDialog.setCanceledOnTouchOutside(cancel);
//    }
}
