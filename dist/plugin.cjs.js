'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var core = require('@capacitor/core');

const CapacitorNativeFilePicker = core.registerPlugin('CapacitorNativeFilePicker', {
    web: () => Promise.resolve().then(function () { return web; }).then(m => new m.CapacitorNativeFilePickerWeb()),
});

class CapacitorNativeFilePickerWeb extends core.WebPlugin {
    async echo(options) {
        console.log('ECHO', options);
        return options;
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    CapacitorNativeFilePickerWeb: CapacitorNativeFilePickerWeb
});

exports.CapacitorNativeFilePicker = CapacitorNativeFilePicker;
//# sourceMappingURL=plugin.cjs.js.map
