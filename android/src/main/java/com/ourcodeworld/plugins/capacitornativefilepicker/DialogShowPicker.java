package com.ourcodeworld.plugins.capacitornativefilepicker;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.net.Uri;

import java.io.File;
import java.util.Map;
import java.util.ArrayList;
import com.nononsenseapps.filepicker.FilePickerActivity;
import android.os.Environment;
import android.os.Build;
import android.content.ClipData;
import android.os.Build;
import android.content.ClipData;
import com.getcapacitor.JSArray;
import android.net.Uri;
import java.util.Map;
import java.util.ArrayList;
import android.util.Log;

import org.json.JSONException;

import me.rosuh.filepicker.bean.FileItemBeanImpl;
import me.rosuh.filepicker.config.AbstractFileFilter;
import me.rosuh.filepicker.config.FilePickerConfig;
import me.rosuh.filepicker.config.FilePickerManager;

public class DialogShowPicker extends Activity{
    private boolean firstTime = true;
    static final int FILE_CODE = 1;

    @Override
    public void onStart() {
        super.onStart();

        if(firstTime == true) {
            Bundle extras = getIntent().getExtras();

            if(extras.getString("pickertype").equals("folders")){
                FilePickerManager.INSTANCE
                    .from(this)
                    .showHiddenFiles(extras.getBoolean("showHiddenFiles"))
                    .maxSelectable(extras.getInt("limit"))
                    .skipDirWhenSelect(false)
                    .filter(new FilterDirectoryOnly())
                    .setTheme(R.style.FilePickerThemeRail)
                    .forResult(FilePickerManager.REQUEST_CODE);
            }else{
                Log.e("Allowed Extensions", extras.getString("allowedExtensions"));

                try {
                    JSArray allowedExtensions = new JSArray(extras.getString("allowedExtensions"));

                    FilePickerManager.INSTANCE
                        .from(this)
                        .showHiddenFiles(extras.getBoolean("showHiddenFiles"))
                        .filter(new FilterByExtension(allowedExtensions.toList()))
                        .maxSelectable(extras.getInt("limit"))
                        .forResult(FilePickerManager.REQUEST_CODE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            /* Funciona always :$
            FilePickerManager.INSTANCE
                .from(this)
                .showHiddenFiles(true)
                .forResult(FilePickerManager.REQUEST_CODE);*/




        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        firstTime = false;

        JSArray jsonArray = new JSArray();

        // Retrieve file, folders paths
        if (requestCode == FilePickerManager.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            for (String path: FilePickerManager.INSTANCE.obtainData()) {
                // todo: https://stackoverflow.com/questions/38200282/android-os-fileuriexposedexception-file-storage-emulated-0-test-txt-exposed
                Uri uri = Uri.fromFile(new File(path));

                Log.e("Demo", uri.toString());
                jsonArray.put(uri.toString());
            }
        }

        // Send information
        Intent intent = new Intent();
        intent.putExtra("result", jsonArray.toString());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}

/*

package com.ourcodeworld.plugins.capacitornativefilepicker;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.net.Uri;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import com.nononsenseapps.filepicker.FilePickerActivity;
import android.os.Environment;
import android.os.Build;
import android.content.ClipData;
import android.os.Build;
import android.content.ClipData;
import com.getcapacitor.JSArray;
import com.nononsenseapps.filepicker.Utils;

import android.net.Uri;
import java.util.Map;
import java.util.ArrayList;
import android.util.Log;

public class DialogShowPicker extends Activity{
    private boolean firstTime = true;
    static final int FILE_CODE = 1;

    @Override
    public void onStart() {
        super.onStart();

        if(firstTime == true) {
            Bundle extras = getIntent().getExtras();

            Log.e("cheese", extras.getString("allowMultipleSelection"));



            Context context = getApplicationContext();
            Intent i = new Intent(context, FilePickerActivity.class);
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, true);

            if(extras.getString("allowMultipleSelection") == "true"){
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, true);
            }else{
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
            }

            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(i, FILE_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        firstTime = false;

        JSArray jsonArray = new JSArray();


        // Retrieve file, folders paths
        if (requestCode == FILE_CODE && resultCode == Activity.RESULT_OK) {
            if (data.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
                // For JellyBean and above
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ClipData clip = data.getClipData();

                    if (clip != null) {
                        for (int i = 0; i < clip.getItemCount(); i++) {
                            jsonArray.put(clip.getItemAt(i).getUri().toString());
                            //jsonArray.put(clip.getItemAt(i).getUri().get());
                            //jsonArray.put("quesito.txt");
                        }
                    }
                    // For Ice Cream Sandwich
                } else {
                    ArrayList<String> paths = data.getStringArrayListExtra(FilePickerActivity.EXTRA_PATHS);

                    if (paths != null) {
                        for (String path: paths) {
                            jsonArray.put(Uri.parse(path).toString());
                            //jsonArray.put(Uri.parse(path).getPath());
                            //jsonArray.put("quesito2.txt");
                        }
                    }
                }

            } else {
                jsonArray.put(data.getData().toString());
                ///jsonArray.put(data.getData().getPath());
                //jsonArray.put("solito.txt");
            }
        }

        // Send information
        Intent intent = new Intent();
        intent.putExtra("result", jsonArray.toString());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}*/