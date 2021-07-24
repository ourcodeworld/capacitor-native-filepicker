export interface CapacitorNativeFilePickerPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
}
