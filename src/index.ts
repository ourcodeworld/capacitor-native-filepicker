import { registerPlugin } from '@capacitor/core';

import type { CapacitorNativeFilePickerPlugin } from './definitions';

const CapacitorNativeFilePicker = registerPlugin<CapacitorNativeFilePickerPlugin>('CapacitorNativeFilePicker', {
  web: () => import('./web').then(m => new m.CapacitorNativeFilePickerWeb()),
});

export * from './definitions';
export { CapacitorNativeFilePicker };
