#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(CapacitorNativeFilePickerPlugin, "CapacitorNativeFilePicker",
           CAP_PLUGIN_METHOD(echo, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(launchFolderPicker, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(launchFilePicker, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(shareFile, CAPPluginReturnPromise);
)
