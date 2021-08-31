import { WebPlugin } from '@capacitor/core';

import type { CapacitorNativeFilePickerPlugin, FilePickerOptions } from './definitions';

export class CapacitorNativeFilePickerWeb extends WebPlugin implements CapacitorNativeFilePickerPlugin 
{
    async echo(options: { value: string }): Promise<{ value: string }> {
        console.log('ECHO', options);
        return options;
    }

    async launchFolderPicker(options: { limit: number }): Promise<{ folders: Array<string> }> {
        console.log(`FOLDER PICKER`, options);

        return {
            folders: ["/example-directory1", "/example-directory2"]
        };
    }
    
    async launchFilePicker(options: FilePickerOptions): Promise<{ files: Array<string> }> {
        console.log('LAUNCH FILE PICKER', options);
        
        return {
            files: ["./example-file1.txt", "./example-file2.txt", "./example-file3.txt"]
        };
    }

    async shareFile(options: { filepath: string }): Promise<void> {
        console.log(`File ${options.filepath} shared!`);
    }

    async getFileUrlForUri(options: { uri: string }): Promise<{ filepath: string }> {
        console.log('getFileUrlForUri', options);

        return {
            filepath: "./example-converted-file.txt"
        };
    }
}