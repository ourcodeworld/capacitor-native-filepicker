var capacitorCapacitorNativeFilePicker = (function (exports, core) {
    'use strict';

    const CapacitorNativeFilePicker = core.registerPlugin('CapacitorNativeFilePicker', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.CapacitorNativeFilePickerWeb()),
    });

    class CapacitorNativeFilePickerWeb extends core.WebPlugin {
        async echo(options) {
            console.log('ECHO', options);
            return options;
        }
        async launchFolderPicker(options) {
            console.log(`FOLDER PICKER`, options);
            return {
                folders: ["/testing"]
            };
        }
        async launchFilePicker(options) {
            console.log('LAUNCH FILE PICKER', options);
            return {
                files: ["./example-file1.txt", "./example-file2.txt", "./example-file3.txt"]
            };
        }
        async shareFile(options) {
            console.log(`File ${options.filepath} shared!`);
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        CapacitorNativeFilePickerWeb: CapacitorNativeFilePickerWeb
    });

    exports.CapacitorNativeFilePicker = CapacitorNativeFilePicker;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

}({}, capacitorExports));
//# sourceMappingURL=plugin.js.map
