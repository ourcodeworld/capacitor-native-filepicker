'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var core = require('@capacitor/core');

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
            folders: ["/example-directory1", "/example-directory2"]
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
    async getFileUrlForUri(options) {
        console.log('getFileUrlForUri', options);
        return {
            filepath: "./example-converted-file.txt"
        };
    }
    async createFile(options) {
        console.log('createFile', options);
        return {
            filepath: "./example-converted-file.txt"
        };
    }
    async writeToFile(options) {
        console.log('writeToFile', options);
        return {
            filepath: "./example-converted-file.txt"
        };
    }
    async fileStat(options) {
        console.log('fileStat', options);
        return {
            mimeType: "text/plain",
            fileSize: 100,
            fileName: "example-file.txt"
        };
    }
    async deleteFile(options) {
        console.log('deleteFile', options);
        return;
    }
    async renameFile(options) {
        console.log('renameFile', options);
        return {
            filepath: "./example-renamed-file.txt",
            filename: "example-renamed-file.txt"
        };
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    CapacitorNativeFilePickerWeb: CapacitorNativeFilePickerWeb
});

exports.CapacitorNativeFilePicker = CapacitorNativeFilePicker;
//# sourceMappingURL=plugin.cjs.js.map
