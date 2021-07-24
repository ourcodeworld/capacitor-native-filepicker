import { WebPlugin } from '@capacitor/core';
import type { CapacitorNativeFilePickerPlugin } from './definitions';
export declare class CapacitorNativeFilePickerWeb extends WebPlugin implements CapacitorNativeFilePickerPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
}
