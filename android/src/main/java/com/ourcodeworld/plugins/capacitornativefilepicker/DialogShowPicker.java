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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        firstTime = false;

        JSArray jsonArray = new JSArray();

        // Retrieve file, folders paths
        if (requestCode == FilePickerManager.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            for (String path: FilePickerManager.INSTANCE.obtainData()) {
                // TODO: https://stackoverflow.com/questions/38200282/android-os-fileuriexposedexception-file-storage-emulated-0-test-txt-exposed
                Uri uri = Uri.fromFile(new File(path));

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