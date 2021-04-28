package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.skytree.epub.Book;
import com.skytree.epub.KeyListener;
import com.skytree.epub.PageTransition;
import com.skytree.epub.PagingMode;
import com.skytree.epub.ReflowableControl;
import com.skytree.epub.SkyProvider;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class SkyepubBook extends AppCompatActivity {
    ConstraintLayout skyepubView;
    ReflowableControl rv;
    String fileName;
    int bookCode;
    double pagePositionInBook;
    final String TAG = "SKYEPUB";
    public String getStorageDirectory() {
        String res = "";
// All book related data will be stored /data/data/com....../files/appName/
        res = getFilesDir().getAbsolutePath()+"/"+getString(this.getApplicationInfo().labelRes);
        return res;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skyepub_book);
        this.makeFullscreen(this);
        skyepubView = (ConstraintLayout)this.findViewById(R.id.skyepubView);
        this.makeBookViewer();
    }
    public void makeBookViewer() {
        Bundle bundle = getIntent().getExtras();
        fileName = bundle.getString("BOOKNAME");
        bookCode = bundle.getInt("BOOKCODE");
        pagePositionInBook = 0;
        rv = new ReflowableControl(this);
        //rv.setId(7777);
// set the bookCode to identify the book file.
        rv.bookCode = this.bookCode;
// Be sure that the file exists before setting.
        String bookPath = this.getStorageDirectory() + "/books/" + fileName;
        rv.setBookPath(bookPath);
// if true, double pages will be displayed on landscape mode.
        rv.setDoublePagedForLandscape(true);
// set the initial font style for book.
        rv.setFont("Book Fonts", this.getRealFontSize(3));
// set the initial line space for book.
        rv.setLineSpacing(this.getRealLineSpace(2)); // the value is supposed to be percent(%).
// set the horizontal gap(margin) on both left and right side of each page.
        rv.setHorizontalGapRatio(0.30);
// set the vertical gap(margin) on both top and bottom side of each page.
        rv.setVerticalGapRatio(0.20);
// rv.setMarginTopRatio(0.7);
// SkyProvider is the default ContentProvider which is presented with SDK.
// SkyProvider can read the content of epub file without unzipping.
// SkyProvider is also fully integrated with SkyDRM solution.
        SkyProvider skyProvider = new SkyProvider();
        rv.setContentProvider(skyProvider);
// set the start positon to open the book.
        rv.setStartPositionInBook(pagePositionInBook);
// if true, globalPagination will be activated.
// this enables the calculation of page number based on entire book ,not on each chapter.
// this globalPagination consumes huge computing power.
// AVOID GLOBAL PAGINATION FOR LOW SPEC DEVICES.
        rv.setGlobalPagination(false);
// set the navigation area on both left and right side to go to the previous or next page whenthe area is clicked.
        rv.setNavigationAreaWidthRatio(0.2f); // both left and right side.
// set the navigation area enabled
        rv.setNavigationAreaEnabled(true);
// set the device locked to prevent Rotation.
        rv.setRotationLocked(true);

        // If you want to get the license key for commercial use, please email us
// Without the license key, watermark message will be shown in background.
        rv.setLicenseKey("0000-0000-0000-0000");
// set PageTransition Effect
        int transitionType = bundle.getInt("transitionType");
        if (transitionType == 0) {
            rv.setPageTransition(PageTransition.None);
        } else if (transitionType == 1) {
            rv.setPageTransition(PageTransition.Slide);
        } else if (transitionType == 2) {
            rv.setPageTransition(PageTransition.Curl);
        }
        rv.setSystemSelectionEnabled(true);
        rv.setForegroundColor(Color.BLACK);
        rv.keepBackgroundColor(true);
        rv.setLayoutParams(new
                ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT));
        skyepubView.addView(rv);
    }
    int getRealFontSize(int fontSizeIndex) {
        int rs = 0;
        switch (fontSizeIndex) {
            case 0:
                rs = 24;
                break;
            case 1:
                rs = 27;
                break;
            case 2:
                rs = 30;
                break;
            case 3:
                rs = 34;
                break;
            case 4:
                rs = 37;
                break;
            default:
                rs = 27;
        }
        rs = (int) ((double) rs * 0.75f);
        return rs;
    }
    public int getRealLineSpace(int lineSpaceIndex) {
        int rs = -1;
        if (lineSpaceIndex == 0) {
            rs = 125;
        } else if (lineSpaceIndex == 1) {
            rs = 150;
        } else if (lineSpaceIndex == 2) {
            rs = 165;
        } else if (lineSpaceIndex == 3) {
            rs = 180;
        } else if (lineSpaceIndex == 4) {
            rs = 200;
        } else {
            rs = 150;
        }
        return rs;
    }
    @SuppressLint("InlinedApi")
    public static void makeFullscreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT>=19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
        }else if (Build.VERSION.SDK_INT>=11) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        }
    }
}
