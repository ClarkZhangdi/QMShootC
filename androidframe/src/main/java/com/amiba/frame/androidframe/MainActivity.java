package com.amiba.frame.androidframe;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Toast;

import com.amiba.frame.androidframe.network.OkHttpUtils;
import com.amiba.frame.androidframe.network.callback.BitmapCallback;
import com.amiba.frame.androidframe.network.callback.FileCallBack;
import com.amiba.frame.androidframe.network.callback.JsonCallback;
import com.amiba.frame.androidframe.network.callback.StringCallback;
import com.amiba.frame.androidframe.util.PermissionUtils;
import com.amiba.frame.androidframe.util.gson.GsonUtil;
import com.amiba.frame.androidframe.util.log.DebugLog;
import com.amiba.frame.androidframe.util.security.DesUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();


    private final int GET_CODE = 0x1;
    private final int GET_IMG_TOKEN = 0x2;
    private final int POST_CODE = 0x3;

    private final int FORM_UPDALOAD = 0x4;
    private final int INSTREAM_UPDALOAD = 0x5;
    private final int GET_IMG = 0x6;
//    @BindView(R.id.textview)
//    TextView textview;
//    @BindView(R.id.img_test)
//    ImageView imgTest;
//    @BindView(R.id.progressBar)
//    ProgressBar progressBar;


    private Activity context;
    private String url;
    private TestBean bean;

    private String img_token;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case FORM_UPDALOAD:
                    File file = null;
                    try {
                        file = resToFile();
                        String url = "http://api.qm41.com/opphot/UploadImg";
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("Token", img_token);
                        url = OkHttpUtils.getUrl(url, hashMap);
                        OkHttpUtils
                                .post()
                                .url(url)
                                .id(FORM_UPDALOAD)
                                .addFile("qm41img1", "test.png", file)
                                .addFile("qm41img2", "test.png", file)
                                .build()
                                .execute(callback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case INSTREAM_UPDALOAD:
                    try {
                        file = resToFile();
                        String url = "http://api.qm41.com/opphot/UploadImgV";
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("Token", img_token);
                        url = OkHttpUtils.getUrl(url, hashMap);
                        OkHttpUtils
                                .postFile()
                                .url(url)
                                .id(INSTREAM_UPDALOAD)
                                .file(file)
                                .build()
                                .execute(callback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    private StringCallback callback = new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            switch (id) {
                case GET_CODE:
                    call.cancel();
                    DebugLog.e(TAG, "GET_CODE\n" + e.getMessage());
                    break;
                case POST_CODE:
                    call.cancel();
                    DebugLog.e(TAG, "POST_CODE\n" + e.getMessage());
                    break;
                case GET_IMG_TOKEN:
                    call.cancel();
                    DebugLog.e(TAG, "GET_IMG_TOKEN\n" + e.getMessage());
                    break;
                case FORM_UPDALOAD:
                    call.cancel();
                    DebugLog.e(TAG, "FORM_UPDALOAD\n" + e.getMessage());
                    break;
                case INSTREAM_UPDALOAD:
                    call.cancel();
                    DebugLog.e(TAG, "FORM_UPDALOAD\n" + e.getMessage());
                    break;
            }
        }

        @Override
        public void onResponse(String response, int id) {
            switch (id) {
                case GET_CODE:
                    DebugLog.i(TAG, "GET_CODE\n" + response);
                    break;
                case POST_CODE:
                    DebugLog.i(TAG, "POST_CODE\n" + response);
                    break;
                case GET_IMG_TOKEN:
                    DebugLog.i(TAG, "GET_IMG_TOKEN\n" + response);
                    TestBean bean = GsonUtil.toBean(response, TestBean.class);
                    img_token = bean.access_token;
                    Message msg = new Message();
                    msg.what = INSTREAM_UPDALOAD;
                    handler.sendMessage(msg);
                    break;
                case FORM_UPDALOAD:
                    DebugLog.i(TAG, "FORM_UPDALOAD\n" + response);
                    break;
                case INSTREAM_UPDALOAD:
                    DebugLog.i(TAG, "INSTREAM_UPDALOAD\n" + response);
                    break;
            }
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            PermissionUtils.verifyStoragePermissions(this);
        }
        Toast.makeText(this, "这是新的apk", Toast.LENGTH_SHORT).show();
//        imgTest.setImageResource(R.mipmap.ic_launcher);

        String json = "{\"pic_url\":\"https://img.alicdn.com/bao/uploaded/i6/TB1M7aIaO0TMKJjSZFNYXG_1FXa_M2.SS2\",\"zdid\":42}";
        bean = GsonUtil.toBean(json, TestBean.class);
        System.out.println("转换后：" + bean.pic_url);

        testGet();
        testPost();
        testImgToken();
        testImgDownload();
        testFileDownload();
        testRxJavaAndroid();

    }

    public void testRxJavaAndroid() {
        Observer<String> observable = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                DebugLog.i(TAG, "Item:" + s);
            }

            @Override
            public void onError(Throwable e) {
                DebugLog.e(TAG, "Error!");
            }

            @Override
            public void onComplete() {
                DebugLog.i(TAG, "Complete!");
            }
        };

        Observable observable1 = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                e.onNext("Hello");
                e.onNext("World");
                e.onComplete();
            }
        });

        observable1.subscribe(observable);
    }

    public void testGet() {
        String url = "http://api2.17zwd.com/rest/region/get_list";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("from", "android");
        hashMap.put("zdid", "42");
        OkHttpUtils
                .get()
                .tag(this)
                .id(GET_CODE)
                .params(hashMap)
                .url(url)
                .build()
                .execute(callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void testPost() {
        url = "http://vip.17zwd.com/Mobile/FavShops/List?token=b3N6Ku6XtazKCruQ1acMM2Q8BngjJuNrqUaZItmjjZDoWgHKSjDhgsHxxoTdnvrQSog9lzyU6hdO%0AwZMNB9cryMe88htAdm9lgN8VS7VG%2FC0%3D%0A";
        TestBean bean = new TestBean();
        bean.zdid = 42;
        String params = GsonUtil.toJsonExclude(bean, new String[]{"pic_url", "token"});
//        String params = "E84P3pPq+Q8czT6DliHmYw==";
        byte[] bytes = DesUtils.encrypt(params, "p-2#b'1Q");
        // Base64编码
        String base64Json = Base64.encodeToString(bytes,
                Base64.DEFAULT);
        DebugLog.d(TAG, "测试GsonUtil.toJsonExclude" + params);
        OkHttpUtils
                .postString()
                .tag(this)
                .id(POST_CODE)
                .url(url)
                .content(base64Json)
                .mediaType(OkHttpUtils.FROM_URLENCODED)
                .build()
                .execute(callback);
    }

    public void testImgToken() {
        JsonCallback<TestBean> jsonCallback = new JsonCallback<TestBean>(TestBean.class) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(TestBean response, int id) {
                DebugLog.i(TAG, "GET_IMG_TOKEN:access_toekn\n" + response.access_token);
            }
        };
        url = "http://api.qm41.com/opphot/GetImgToken?appid=hyc05968wd606efe07Ejf8549&appsecret=HNbwfjKd2iH57Y1P8jzx7Vq0KtWzF15n3LVNnNoL1ibEUGdlSR0OZVBdDJH4SFOL";
        OkHttpUtils
                .get()
                .tag(this)
                .id(GET_IMG_TOKEN)
                .url(url)
                .build()
                .execute(jsonCallback);
    }

    public void testImgDownload() {
        BitmapCallback bitmapCallback = new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(Bitmap response, int id) {
//                imgTest.setImageBitmap(response);
            }
        };

        url = "https://aims.qm41.com/imgextra/qm41/20180103/470fc763a4ec4cca852296194de9df7b.jpg";
        OkHttpUtils
                .get()
                .url(url)
                .tag(this)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(bitmapCallback);
    }

    public void testFileDownload() {
        FileCallBack fileCallBack = new FileCallBack(getApplicationContext().getExternalCacheDir().getAbsolutePath(), "zwd.apk") {

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
//                progressBar.setProgress((int) (100 * progress));
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                call.cancel();
                Toast.makeText(context, "下载文件出错", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(File response, int id) {
                DebugLog.i(TAG, "apk下载路径" + response.getAbsolutePath());
            }
        };

        url = "http://api2.17zwd.com/temp/f/apk/onlineproject_2.6.2.apk?8cdc0c73-4c0d-47b0-a6e1-02748872ad5d";
        OkHttpUtils
                .get()
                .url(url)
                .tag(this)
                .build()
                .connTimeOut(2000)
                .readTimeOut(10000)
                .writeTimeOut(2000)
                .execute(fileCallBack);
    }

    public Bitmap getRes() {
        Application application = getApplication();
        int resId = getResources().getIdentifier(/*"ic_launcher"*/"setup_guide_1", "mipmap", application.getPackageName());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        return bitmap;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File file = new File(context.getCacheDir(), "test.png");
        if (file != null) {
            file.delete();
        }
        OkHttpUtils.getInstance().cancelTag(this);
    }

    public File resToFile() throws IOException {
        File file = new File(context.getCacheDir(), "test.png");
        file.createNewFile();

        Bitmap bitmap = getRes();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
        return file;
    }


}
