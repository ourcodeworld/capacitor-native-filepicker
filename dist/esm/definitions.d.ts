export declare type FilePickerOptions = {
    limit?: number;
    allowedExtensions?: Array<string>;
    showHiddenFiles?: boolean;
};
export declare type FolderPickerOptions = {
    limit?: number;
    showHiddenFiles?: boolean;
};
export declare type CreateFileOptions = {
    directory: string;
    filename: string;
    content: string;
};
export declare type WriteToFileOptions = {
    filepath: string;
    content: string;
};
export interface CapacitorNativeFilePickerPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    launchFilePicker(options: FilePickerOptions): Promise<{
        files: Array<string>;
    }>;
    launchFolderPicker(options: FolderPickerOptions): Promise<{
        folders: Array<string>;
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
}
