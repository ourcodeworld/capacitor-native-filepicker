# capacitor-native-filepicker

In most of the cases, you don't need this picker. Implementing your own filepicker with the [File System API](https://capacitorjs.com/docs/apis/filesystem) of CapacitorJS is quite [easy and convenient](https://www.youtube.com/watch?v=WNte407ArQ0) as its design will be totally yours and matches your application UI. I developed this plugin because I wanted to use a more native approach in my application that allows users to pick some files or folders from the storage (as well as learning how to develop CapacitorJS plugins for both platforms). 

For Android it uses under the hood the [AndroidFilePicker library by Rosuh](https://github.com/rosuH/AndroidFilePicker). For iOS, it uses the [UIDocumentPickerViewController](https://developer.apple.com/documentation/uikit/uidocumentpickerviewcontroller) view.

|   | Android  | iOS  |
|---|---|---|
| FilePicker  | ![android-filepicker](https://user-images.githubusercontent.com/11634719/131224924-b68e20d0-ca53-4869-8f5e-061fb5cda778.png)  | ![filepicker-ios](https://user-images.githubusercontent.com/11634719/131225974-1644f016-b150-4bcb-97af-ff69f55378e4.png)|
| FolderPicker  | ![folder-picker](https://user-images.githubusercontent.com/11634719/131225376-f1ff67a1-8190-4a74-92d0-4e544a8b2b38.png)| ![folder-picker-ios](https://user-images.githubusercontent.com/11634719/131225944-c398a160-48e1-4225-84d9-22cc21a9d64e.png)|


## Install

```bash
npm install https://github.com/ourcodeworld/capacitor-native-filepicker
npx cap sync
```

## How to use

```javascript
import { CapacitorNativeFilePicker } from "capacitor-native-filepicker"; 

// A. Select folders
let folders = await CapacitorNativeFilePicker.launchFolderPicker({
    limit: -1,
    showHiddenFiles: true
});

// Outputs: ["file://folder-path1", "file://folder-path2"]
console.log(folders);


// B. Select files
let files = await CapacitorNativeFilePicker.launchFilePicker({
    // No limit of files to select
    limit: -1,
    showHiddenFiles: true,
    allowedExtensions: ["jpg", "png", "gif"]
});

// Outputs: ["file://folder-path1/file.png", "file://folder-path2/file2.jpg"]
console.log(files);
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`launchFilePicker(...)`](#launchfilepicker)
* [`launchFolderPicker(...)`](#launchfolderpicker)
* [`shareFile(...)`](#sharefile)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => any
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>any</code>

--------------------


### launchFilePicker(...)

```typescript
launchFilePicker(options: FilePickerOptions) => any
```

| Param         | Type                                                                                 |
| ------------- | ------------------------------------------------------------------------------------ |
| **`options`** | <code>{ limit?: number; allowedExtensions?: any; showHiddenFiles?: boolean; }</code> |

**Returns:** <code>any</code>

--------------------


### launchFolderPicker(...)

```typescript
launchFolderPicker(options: FolderPickerOptions) => any
```

| Param         | Type                                                        |
| ------------- | ----------------------------------------------------------- |
| **`options`** | <code>{ limit?: number; showHiddenFiles?: boolean; }</code> |

**Returns:** <code>any</code>

--------------------


### shareFile(...)

```typescript
shareFile(options: { filepath: string; }) => any
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ filepath: string; }</code> |

**Returns:** <code>any</code>

--------------------

</docgen-api>
