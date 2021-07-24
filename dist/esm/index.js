import { registerPlugin } from '@capacitor/core';
const CapacitorNativeFilePicker = registerPlugin('CapacitorNativeFilePicker', {
    web: () => import('./web').then(m => new m.CapacitorNativeFilePickerWeb()),
});
export * from './definitions';
export { CapacitorNativeFilePicker };
//# sourceMappingURL=index.js.map