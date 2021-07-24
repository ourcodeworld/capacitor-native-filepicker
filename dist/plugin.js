var capacitorCapacitorNativeFilePicker = (function (exports, core) {
    'use strict';

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

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

}({}, capacitorExports));
//# sourceMappingURL=plugin.js.map
