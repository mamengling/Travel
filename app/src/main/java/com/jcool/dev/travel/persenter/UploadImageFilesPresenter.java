package com.jcool.dev.travel.persenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.UploadImageFilesView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.concurrent.Executors;

import cz.msebera.android.httpclient.Header;

public class UploadImageFilesPresenter {
    private Context mContext;
    private UploadImageFilesView mDatumCommitActivityView;

    public UploadImageFilesPresenter(Context mContext, UploadImageFilesView mDatumCommitActivityView) {
        this.mContext = mContext;
        this.mDatumCommitActivityView = mDatumCommitActivityView;
    }


    @SuppressLint("StaticFieldLeak")
    public void upLoadImage(final String filePath, final Context context) {
        mDatumCommitActivityView.showProgress();
        new AsyncTask<String, Integer, String>() {

            private String photoPath;

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(String... params) {
                String end = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                String[] strings = filePath.split("/");
                String str = null;
                int i = strings.length;
                try {
                    URL url = new URL(Constants.BASE_URL + Constants.APP_HOME_API_UPLOAD_IMAGE);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    /* 允许Input、Output，不使用Cache */
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setUseCaches(false);
                    // 设置http连接属性

                    con.setRequestMethod("POST");
                    con.setRequestProperty("Connection", "Keep-Alive");
                    con.setRequestProperty("Charset", "UTF-8");
//                    con.addRequestProperty("authorization", GlobalVar.token_type + GlobalVar.access_token);
                    con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                    DataOutputStream ds = new DataOutputStream(con.getOutputStream());
                    String name;
                    String uuid = UUID.randomUUID().toString();
                    if ("".equals(uuid) || uuid == null) {
                        name = URLEncodingString(strings[i - 1]);
                    } else {
                        name = URLEncodingString(uuid + "_" + strings[i - 1]);
                    }
                    photoPath = name;

                    ds.writeBytes(twoHyphens + boundary + end);
                    ds.writeBytes("Content-Disposition: form-data; " + "name=\"file\";filename=\"" + name + "\"" + end);
                    //fileType=\"" + GlobalVar.createThumbnail + "\";filepath=\"" + GlobalVar.fileFolder + "\";
                    ds.writeBytes(end);

                    File file = new File(filePath);
                    long file_length = file.length();
                    // 取得文件的FileInputStream
                    FileInputStream fStream = new FileInputStream(filePath);
                    /* 设置每次写入1024bytes */
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    int length = -1;
                    int count = 0;
                    int progress = 0;
                    /* 从文件读取数据至缓冲区 */
                    while ((length = fStream.read(buffer)) != -1) {
                        /* 将资料写入DataOutputStream中 */
                        ds.write(buffer, 0, length);
                        count += length;
                        if (file_length > 0) {
                            // 如果知道响应的长度，调用publishProgress（）更新进度
                            progress = (int) ((count / (float) file_length) * 100);
                            if (progress == 100) {
                                progress = 99;
                            }
                            publishProgress(progress);
                        }

                    }
                    ds.writeBytes(end);
                    ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
                    fStream.close();
                    ds.flush();
                    /* 取得Response内容 */
                    InputStream is = con.getInputStream();
                    int ch;
                    StringBuffer b = new StringBuffer();
                    while ((ch = is.read()) != -1) {
                        b.append((char) ch);
                    }
                    /* 将Response显示于Dialog */
                    // showToast("上传成功" +
                    str = b.toString();
                    /* 关闭DataOutputStream */
                    ds.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return str;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {

            }

            @Override
            protected void onPostExecute(String result) {
                mDatumCommitActivityView.closeProgress();
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, Constants.BASE_URL + Constants.APP_HOME_API_UPLOAD_IMAGE + "&" + filePath);
                if (result != null) {
                    try {
                        JSONObject job = new JSONObject(result);
                        CallBackVo<String> mCall = new CallBackVo<>();
                        if (job.getBoolean("success")) {
                            String uploadPath = job.optString("data");
                            mCall.setData(uploadPath);
                            mCall.setSuccess(true);
                            mCall.setMsg(job.optString("msg"));
                            mDatumCommitActivityView.excuteSuccessUploadCallBack(mCall);
                        } else {
                            mCall.setData("");
                            mCall.setSuccess(false);
                            mCall.setMsg(job.optString("msg"));
                            mDatumCommitActivityView.excuteFailedCallBack(mCall);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.executeOnExecutor(Executors.newCachedThreadPool());
    }

    public static String URLEncodingString(String code) {
        String URLCode = "";
        try {
            URLCode = URLEncoder.encode(code, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return URLCode;
    }


    public void uploadFiles(String path) {

        JSONObject params = new JSONObject();
        File file = new File(path);
        try {
            params.put("filename", file);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mDatumCommitActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_UPLOAD_IMAGE, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mDatumCommitActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mDatumCommitActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mDatumCommitActivityView.closeProgress();
                JSONObject job = null;
                try {
                    job = new JSONObject(result);
                    CallBackVo<String> mCall = new CallBackVo<>();
                    if (job.getBoolean("success")) {
                        String uploadPath = job.optString("data");
                        mCall.setData(uploadPath);
                        mCall.setSuccess(true);
                        mCall.setMsg(job.optString("msg"));
                        mDatumCommitActivityView.excuteSuccessUploadCallBack(mCall);
                    } else {
                        mCall.setData("");
                        mCall.setSuccess(false);
                        mCall.setMsg(job.optString("msg"));
                        mDatumCommitActivityView.excuteFailedCallBack(mCall);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mDatumCommitActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mDatumCommitActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
