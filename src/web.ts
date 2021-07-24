import { WebPlugin } from '@capacitor/core';

import type { CapacitorNativeFilePickerPlugin } from './definitions';

export class CapacitorNativeFilePickerWeb extends WebPlugin implements CapacitorNativeFilePickerPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
