import { WebPlugin } from '@capacitor/core';
import type { CapacitorNativeFilePickerPlugin, FilePickerOptions, CreateFileOptions, WriteToFileOptions } from './definitions';
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
    createFile(options: CreateFileOptions): Promise<{
        filepath: string;
    }>;
    writeToFile(options: WriteToFileOptions): Promise<{
        filepath: string;
    }>;
    fileStat(options: {
        filepath: string;
    }): Promise<{
        mimeType: string;
        fileSize: number;
        fileName: string;
    }>;
    deleteFile(options: {
        filepath: string;
    }): Promise<void>;
    renameFile(options: {
        filepath: string;
        newFilename: string;
    }): Promise<{
        filepath: string;
        filename: string;
    }>;
}
