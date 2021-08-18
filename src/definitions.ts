export type FilePickerOptions = {
    limit?: number;
    allowedExtensions?: Array<string>;
    showHiddenFiles?: boolean;
}

export type FolderPickerOptions = {
    limit?: number;
    showHiddenFiles?: boolean;
}

export interface CapacitorNativeFilePickerPlugin {
    echo(options: { value: string }): Promise<{ value: string }>;
    launchFilePicker(options: FilePickerOptions): Promise<{ files: Array<string> }>;
    launchFolderPicker(options: FolderPickerOptions): Promise<{ folders: Array<string> }>;
    shareFile(options: { filepath: string }): Promise<void>;
}