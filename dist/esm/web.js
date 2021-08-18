import { WebPlugin } from '@capacitor/core';
export class CapacitorNativeFilePickerWeb extends WebPlugin {
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
//# sourceMappingURL=web.js.map