package com.amiba.frame.androidframe.util.phoneFacility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.widget.Button;

import com.amiba.frame.androidframe.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 语音识别
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author youjw
 * @version [版本号, 2014-11-28]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class VoiceRecActivity extends Activity {
    Button mBtnSound;
    public static final int REQUEST_CODE_DOUND_REQ = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_voicerec);
        mBtnSound = findViewById(R.id.btnSound);
        mBtnSound.setOnClickListener(v -> {
            try {
                Intent i = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(i, REQUEST_CODE_DOUND_REQ);
            } catch (Exception e) {
                new AlertDialog.Builder(VoiceRecActivity.this)
                        .setTitle("NotFound")
                        .setMessage("没有找到谷歌语音识别模块，是否安装？")
                        .setPositiveButton("是", (dialog, which) -> {
                            FileOutputStream out = null;
                            InputStream in = null;
                            try {
                                // 获取输入流
                                in = getAssets()
                                        .open("goolesearch.apk");
                                // 获取本地文件夹目录
                                File dir = Environment
                                        .getExternalStorageDirectory();
                                File file = new File(dir,
                                        "/goolesearch.apk");
                                // File file=new
                                // File("/mnt/goolesearch.apk");
                                out = new FileOutputStream(file);
                                int len;
                                byte[] buf = new byte[1024];
                                while ((len = in.read(buf)) != -1) {
                                    out.write(buf, 0, len);
                                }
                                Intent i = new Intent();
                                // 设置启动模式
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                //
                                i.setAction(Intent.ACTION_VIEW);
                                i.setDataAndType(Uri.fromFile(file),
                                        "application/vnd.android.package-archive");
                                startActivity(i);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } finally {
                                try {
                                    if (out != null) {
                                        out.close();
                                    }
                                    if (in != null) {
                                        in.close();
                                    }
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }).setNegativeButton("否", null).create().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_DOUND_REQ:
                    ArrayList<String> speeks = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // for (int i = 0; i < speeks.size(); i++) {
                    // Log.i("speek", speeks.get(i));
                    // }
                    retResult(speeks.get(0));
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 返回结果
     *
     * @param string 识别结果
     * @see [类、类#方法、类#成员]
     */
    private void retResult(String string) {
        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("result", string);
        resultIntent.putExtras(bundle);
        this.setResult(RESULT_OK, resultIntent);
    }

}