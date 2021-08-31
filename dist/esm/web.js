import { WebPlugin } from '@capacitor/core';
export class CapacitorNativeFilePickerWeb extends WebPlugin {
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
}
//# sourceMappingURL=web.js.map