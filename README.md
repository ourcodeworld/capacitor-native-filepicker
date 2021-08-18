# capacitor-native-filepicker

A native file picker implementation for CapacitorJS projects.

## Install

```bash
npm install capacitor-native-filepicker
npx cap sync
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
