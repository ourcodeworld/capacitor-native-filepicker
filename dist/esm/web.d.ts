import { WebPlugin } from '@capacitor/core';
import type { CapacitorNativeFilePickerPlugin, FilePickerOptions } from './definitions';
export declare class CapacitorNativeFilePickerWeb extends WebPlugin implements CapacitorNativeFilePickerPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    launchFolderPicker(options: {
        limit: number;
    }): Promise<{
        folders: Array<string>;
    }>;
    launchFilePicker(options: FilePickerOptions): Promise<{
        files: Array<string>;
    }>;
    shareFile(options: {
        filepath: string;
    }): Promise<void>;
    getFileUrlForUri(options: {
        uri: string;
    }): Promise<{
        filepath: string;
    }>;
}
