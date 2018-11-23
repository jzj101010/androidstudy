package com.suishou.ems.dao;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.suishou.ems.Util.Utils;
import com.suishou.ems.service.ApiInterFace;
import com.suishou.ems.view.MyDialog;
import com.suishou.ems.view.ProgressDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 新文件上传
 * Created by j on 2018/7/19 0019.
 */

public class NewUploadDao extends BaseDao {

    public List<String> fileList = new ArrayList<>();       //保存最开始的 fileList  用再次提交失败文件
    public List<String> imgUrllist = new ArrayList<>();       //保存返回的多个图片路径（按上传顺序） 上传失败位置为空字符串
    public List<Integer> errorList = new ArrayList<>();       //保存上传 失败list的位置
    public List<Integer> beforeErrorList = new ArrayList<>(); //上传失败重新上传 保存上一次失败list的位置
    private ProgressDialog myProgressDialog = new ProgressDialog(mContext, "图片上传中请稍等");
    public  String exceptionStr;
    private int pos;

    //若接口要求token  记录多次循环中是否是第一次返回登录超时 防止多次跳转到登录页面
    private boolean isfirstLogin=true;
    public NewUploadDao(Context context) {
        super(context);
    }



    /**
     * 上传图片
     * @param fileList
     * @throws JSONException
     */
    public void   upLoadFile(List<String> fileList)  {
        imgUrllist.clear();
        this.fileList.clear();
        this.fileList.addAll(fileList);
        upLoadFile(fileList,false);
    }

    /**
     * upLoadFile执行后 判断errorList是否有部分文件上传失败
     * 再调用此方法
     *  在有图片上传失败时 重新上传错误图片
     * @throws JSONException
     */
    public void   reUpLoadErrorFile()  {
        //重新上传失败图片  将失败位置放入beforeErrorList
        beforeErrorList.clear();
        beforeErrorList.addAll(errorList);
        List<String> list=new ArrayList<>();
        for(int i:errorList){
            list.add(this.fileList.get(i));
        }
        upLoadFile(list,true);
    }

    /**
     *   循环上传多张图片（后台一次只接收一张），
     *  类变量pos记录返回结果个数 达到上传个数后才执行回调
     *  上传成功返回的路径保存在imgUrllist 中
     * @param fileList       多张图片路径
     * @param isReupload      true 是   是否为是上传失败重新上传图片
     * @throws JSONException   一般不会抛出
     */
    private void upLoadFile(final List<String> fileList, final boolean isReupload ){
        try {
            if(fileList.size()==0){
                return;
            }
            isfirstLogin=true;
            errorList.clear();
            exceptionStr=null;
            myProgressDialog.show();
            myProgressDialog.setCanceledOnTouchOutside(false);
            final String url = ApiInterFace.FILEUPLOAD;
            //记录上传的总数对比结果  判断是否为最后一个结果
            final int account = fileList.size();
            //记录当前返回第几个结果
            pos=0;
            //循环出http 设置pos
            for (int i = 0; i < fileList.size(); i++) {
                if(!isReupload){
                    //不是再次上传失败 ，需要add
                    imgUrllist.add("");
                }
                if(fileList.get(i).startsWith("http")){
                    pos++;
                    imgUrllist.set(i,fileList.get(i));
                }
            }
            if (pos == account) {
                NewUploadDao.this.OnMessageResponse(url, new JSONObject());
                myProgressDialog.dismiss();
                return;
            }
            //循环上传
            for (int i = 0; i < fileList.size(); i++) {

                File file;
                if (fileList.get(i).startsWith("file")) {
                    String filePath = fileList.get(i).substring(5);
                    file = new File(filePath);
                }else if(fileList.get(i).startsWith("http")){
                    continue;
                }
                else {
                    file = new File(fileList.get(i));
                }
                if (!file.exists()) {
                    myProgressDialog.dismiss();
                    Utils.showToastView(mContext, "第" + (i + 1) + "张图片获取有问题，请重新选择");
                    return;
                }


                //设置图片参数
                String fileName=  file.getName();
                String fileTyle=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
                if("gif,jpg,jpeg,png,bmp".contains(fileTyle)){
                    fileTyle="images";
                }else if("swf,flv".contains(fileTyle)){
                    fileTyle="flashs";
                }else if("swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb".contains(fileTyle)){
                    fileTyle="medias";
                }else if("doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2".contains(fileTyle)){
                    fileTyle="files";
                }else {
                    fileTyle="images";
                }
                String fol="";
                if(fileTyle.equals("images")){
                    fol="photo";
                }else{
                    fol="shHouse";
                }


                final int finalI = i;
                OkHttpUtils.post().addHeader("Authorization", Utils.usertoken(mContext))
                        .url(Utils.SERVER_PRODUCTION + url)
                        .addFile("file", fileName, file)
                        .addParams("type", fileTyle)
                        .addParams("folder", fol)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int j) {
                        pos++;
                        if(isReupload){
                            //重新上传失败图片  失败后重新定位是之前的第几个位置
                            errorList.add(beforeErrorList.get(finalI));
                        }else{
                            errorList.add(finalI);
                        }
                        if (pos == account){
                            myProgressDialog.dismiss();
                            try {
                                NewUploadDao.this.OnMessageResponse(url, new JSONObject());
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if("request failed , reponse's code is : 401".equals(e.getMessage())){
                            if(isfirstLogin) {
                                isfirstLogin=false;
                                NewUploadDao.this.onError(e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int j) {
                        try {
                            pos++;
                            JSONObject jo = new JSONObject(s);
                            NewUploadDao.this.callbak(jo, "");
                            if (jo.optString("succeed").equals("1")) {
                                String imgurl = jo.optString("data");
                                if(isReupload){
                                    //重新上传失败图片  成功后放入之前失败的位置
                                    imgUrllist.set(beforeErrorList.get(finalI),  imgurl);
                                }else{
                                    imgUrllist.set(finalI, imgurl);
                                }

                            }else{
                                if(isReupload) {
                                    //重新上传失败图片  失败后重新定位是之前的第几个位置
                                    errorList.add(beforeErrorList.get(finalI));
                                }else{
                                    errorList.add(finalI);
                                }
                            }

                            if (pos == account) {
                                NewUploadDao.this.OnMessageResponse(url, jo);
                                myProgressDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            Log.e("emsClass:"+this.getClass().getName(), e.getMessage(),e);
                            if(isReupload) {
                                //重新上传失败图片  失败后重新定位是之前的第几个位置
                                errorList.add(beforeErrorList.get(finalI));
                            }else{
                                errorList.add(finalI);
                            }
                        }
                    }
                });
            }

            } catch (Exception e) {
            Log.e("emsClass:"+this.getClass().getName(), e.getMessage(),e);
            exceptionStr=e.toString();
            try {
                NewUploadDao.this.OnMessageResponse(ApiInterFace.FILEUPLOAD,new JSONObject());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 重新提交新一轮  图片初始化
      */
    public void inilist(){
        fileList.clear();
        errorList.clear();
        beforeErrorList.clear();
        imgUrllist.clear();
    }

    public boolean isAllSuccess(){
        if (exceptionStr != null) {
           return false;
        }
        if(errorList.size()>0){
            return  false;
        }
        return true;
    }

    public void showErrorDialog(String exceptionTos, String errorDialogTitle, String errorDialogPosStr, String errorDialognegStr, final View.OnClickListener errorDialogPosLis, final View.OnClickListener errorDialognegLis){
        if (exceptionStr != null) {
            Utils.showToastView(mContext,exceptionTos );
            return;
        }else if(errorList.size() > 0){
            //又个别图片上传失败
            String str = "";
            for (int i : errorList) {
                str += "，" + (i + 1);
            }
            str = str.substring(1, str.length());

            final MyDialog myDialog = new MyDialog(mContext, "", "第" + str + "张图片上传失败,"+errorDialogTitle);
            myDialog.positive.setText(errorDialogPosStr);
            myDialog.negative.setText(errorDialognegStr);
            myDialog.positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(errorDialogPosLis!=null)
                    errorDialogPosLis.onClick(view);
                    myDialog.dismiss();
                }
            });
            myDialog.negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(errorDialognegLis!=null)
                        errorDialognegLis.onClick(view);
                    myDialog.dismiss();
                }
            });
            myDialog.show();
        }
    }
    public void showErrorDialog( View.OnClickListener errorDialogPosLis, View.OnClickListener errorDialognegLis){
        showErrorDialog("图片上传失败，请重新提交","是否重新上传这些图片","是","否,提交请求",errorDialogPosLis,errorDialognegLis);
    }

}
