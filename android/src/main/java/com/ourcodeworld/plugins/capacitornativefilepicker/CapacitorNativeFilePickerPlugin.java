package com.ourcodeworld.plugins.capacitornativefilepicker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.getcapacitor.FileUtils;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import org.json.JSONException;

import java.io.File;
import java.io.InputStream;

@CapacitorPlugin(
    name = "CapacitorNativeFilePicker",
    permissions = {
        @Permission(
            alias = "storage",
            strings = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            }
        )
    }
)

public class CapacitorNativeFilePickerPlugin extends Plugin {
    private PluginCall PUBLIC_CALL = null;

    @PluginMethod
    public void echo(PluginCall call) {
    }

    @PluginMethod
    public void getFileUrlForUri(PluginCall call){
        JSObject response = new JSObject();

        try {
            String filepath = FileUtils.getFileUrlForUri(getContext(), Uri.parse(call.getString("uri")));

            response.put("filepath", filepath);

            call.resolve(response);
        }catch (Exception err){
            call.reject(err.getMessage());
        }
    }

    @PluginMethod
    public void shareFile(PluginCall call) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Uri fileURI = FileProvider.getUriForFile(
                getContext(),
                getContext().getApplicationContext().getPackageName() + ".provider",
                new File(Uri.parse(call.getString("filepath")).getPath())
            );

            intent.putExtra(Intent.EXTRA_STREAM, fileURI);
            intent.setType("*/*");
            getActivity().startActivity(Intent.createChooser(intent, "Share file via..."));
        }catch (Exception ex){}
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @PluginMethod
    public void launchFilePicker(PluginCall call){
        if (getPermissionState("storage") != PermissionState.GRANTED) {
            requestPermissionForAlias("storage", call, "pickerPermsCallback");
        } else {
            try {
                if (Environment.isExternalStorageManager()) {
                    launchPicker(call);
                } else {
                    launchManageAllFilesAccessIntent();
                }
            } catch (Exception | Error exception) {
                launchPicker(call);
            }
        }
    }

    @PluginMethod
    public void launchFolderPicker(PluginCall call){
        if (getPermissionState("storage") != PermissionState.GRANTED) {
            requestPermissionForAlias("storage", call, "pickerPermsCallback");
        } else {
            try {
                if (Environment.isExternalStorageManager()) {
                    launchPickerFolder(call);
                } else {
                    launchManageAllFilesAccessIntent();
                }
            } catch (Exception | Error exception) {
                launchPickerFolder(call);
            }
        }
    }

    @PluginMethod
    public void launchPicker(PluginCall call) {
        Intent intent = new Intent("com.ourcodeworld.plugins.capacitornativefilepicker.DialogShowPicker");

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra("pickertype", "files");
        intent.putExtra("limit", call.getInt("limit", -1));
        intent.putExtra("showHiddenFiles", call.getBoolean("showHiddenFiles", false));
        intent.putExtra("allowedExtensions", call.getArray("allowedExtensions", new JSArray()).toString());

        startActivityForResult(call, intent, "pickFilesResult");
    }

    @PluginMethod
    public void launchPickerFolder(PluginCall call) {
        Intent intent = new Intent("com.ourcodeworld.plugins.capacitornativefilepicker.DialogShowPicker");

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra("pickertype", "folders");
        intent.putExtra("limit", call.getInt("limit", -1));
        intent.putExtra("showHiddenFiles", call.getBoolean("showHiddenFiles", false));

        startActivityForResult(call, intent, "pickFoldersResult");
    }

    /**
     * This method is called when the user has not granted access to the external storage.
     */
    public void launchManageAllFilesAccessIntent(){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        Uri uri = Uri.fromParts(
            "package",
            getContext().getPackageName(),
            null
        );
        intent.setData(uri);
        getActivity().startActivity(intent);
    }

    @PermissionCallback
    private void pickerPermsCallback(PluginCall call) {
        if (getPermissionState("storage") == PermissionState.GRANTED) {
            try {
                if (Environment.isExternalStorageManager()) {
                    launchPicker(call);
                } else {
                    launchManageAllFilesAccessIntent();
                }
            } catch (Exception | Error exception) {
                launchPicker(call);
            }
        } else {
            call.reject("Permission is required to take a picture");
        }
    }

    @ActivityCallback
    private void pickFilesResult(PluginCall call, ActivityResult result) {
        JSObject ret = new JSObject();

        if (result.getResultCode() == Activity.RESULT_CANCELED) {
            call.reject("Activity canceled");
        } else {
            Intent data = result.getData();

            try{
                JSArray files = new JSArray(data.getStringExtra("result"));
                
                ret.put("files", files);

                call.resolve(ret);
            }catch(JSONException e){}
        }
    }

    @ActivityCallback
    private void pickFoldersResult(PluginCall call, ActivityResult result) {
        JSObject ret = new JSObject();

        if (result.getResultCode() == Activity.RESULT_CANCELED) {
            call.reject("Activity canceled");
        } else {
            Intent data = result.getData();

            try{
                JSArray files = new JSArray(data.getStringExtra("result"));

                ret.put("folders", files);

                call.resolve(ret);
            }catch(JSONException e){}
        }
    }
}