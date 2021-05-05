package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.folioreader.Config;
import com.folioreader.FolioReader;
import com.folioreader.util.AppUtil;
import com.folioreader.model.HighLight;
import com.folioreader.model.locators.ReadLocator;
import com.folioreader.ui.base.OnSaveHighlight;
import com.folioreader.util.OnHighlightListener;
import com.folioreader.util.ReadLocatorListener;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import javax.xml.parsers.ParserConfigurationException;


public class GotoEpubFile extends AppCompatActivity
        implements OnHighlightListener, ReadLocatorListener, FolioReader.OnClosedListener {
    LinearLayout linlayout;
    private FolioReader folioReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto_epub_file);

        folioReader =  FolioReader
                .get()
                .setOnHighlightListener(this)
                .setReadLocatorListener(this)
                .setOnClosedListener(this);

        Config config = AppUtil.getSavedConfig(getApplicationContext());
        if (config == null)
            config = new Config();
        config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL);


        folioReader.setConfig(config, true)
                .openBook(R.raw.astronomy);


        //Log.d("mytag", "seacrh"+folioReader.r2StreamerApi.search(1, "title"));
        //getBook(config);
        //ReadLocator new_readlocator = new ReadLocator();
        Log.d("mytag", "readlocator"+config.toString());

    }

    private ReadLocator getLastReadLocator() {

        File file = new File(new File("/sdcard/Locators/LastReadLocators/"), "last_read_locator_1.json");

        Log.d("mytag", file.toString());

        String path = Environment.getExternalStorageDirectory().getPath().toString();
        Log.d("mytag", path);
        String jsonString = loadAssetTextAsString("/sdcard/Locators/LastReadLocators/last_read_locator_1.json");
        return ReadLocator.fromJson(jsonString);
    }

    private String loadAssetTextAsString(String name) {

        BufferedReader in = null;

        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e("HomeActivity", "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e("HomeActivity", "Error closing asset " + name);
                }
            }
        }
        return null;
    }

    public void getBook(Config config){
        try {
            folioReader.setConfig(config, true)
                    .openBook(R.raw.astronomy);
        } catch (NullPointerException e) {
            TextView text = new TextView(getApplicationContext());
            text.setText("Книга не найдена");
            linlayout.addView(text);
        }

    }

    @Override
    public void onFolioReaderClosed() {
        //Log.v("my_tag", "-> onFolioReaderClosed");
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void saveReadLocator(ReadLocator readLocator) {


        File direct = new File(Environment.getExternalStorageDirectory() + "/Locators/LastReadLocators/");
        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Locators/LastReadLocators/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/Locators/LastReadLocators/"), "last_read_locator_1.json");
        Log.d("mytag", file.getPath());
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(readLocator.toJson().getBytes());
            out.close();
        } catch (IOException e) {
        e.printStackTrace();
        }

        Log.d("mytag", "-> saveReadLocator -> " + readLocator.toJson());
    }

    @Override
    public void onHighlight(HighLight highlight, HighLight.HighLightAction type) {

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        FolioReader.clear();
//    }
}
