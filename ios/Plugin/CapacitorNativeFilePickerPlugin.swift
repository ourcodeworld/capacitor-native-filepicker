import Foundation
import Capacitor
import MobileCoreServices

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorNativeFilePickerPlugin)
public class CapacitorNativeFilePickerPlugin: CAPPlugin, UIDocumentPickerDelegate {
    private var pickerType: String = ""
    public var _call: CAPPluginCall? = nil
    
    @objc func launchFilePicker(_ call: CAPPluginCall){
        let allowMultipleSelection = ((call.getInt("limit") ?? -1) == -1) ? true: false
        
        let this = self
        
        self._call = call
        self.pickerType = "files"
         
        DispatchQueue.main.async { [weak self] in
            let documentPicker = UIDocumentPickerViewController(
                documentTypes: [String(kUTTypeItem)],
                in: UIDocumentPickerMode.open
            )
            
            documentPicker.allowsMultipleSelection = allowMultipleSelection
            
            if #available(iOS 13.0, *) {
                documentPicker.shouldShowFileExtensions = true
            }
            
            documentPicker.delegate = this
            documentPicker.modalPresentationStyle = UIModalPresentationStyle.fullScreen
            
            self?.bridge?.viewController?.present(
                documentPicker,
                animated: true,
                completion: nil
            )
        }
    }
    
    @objc func launchFolderPicker(_ call: CAPPluginCall){
        let this = self
        self._call = call
        
        self.pickerType = "folders"
        
        DispatchQueue.main.async { [weak self] in
            let documentPicker = UIDocumentPickerViewController(
                documentTypes: [String(kUTTypeFolder)],
                in: UIDocumentPickerMode.open
            )
            
            documentPicker.allowsMultipleSelection = true
            documentPicker.delegate = this
            documentPicker.modalPresentationStyle = UIModalPresentationStyle.fullScreen
            
            self?.bridge?.viewController?.present(
                documentPicker,
                animated: true,
                completion: nil
            )
        }
    }
    
    /// This method responds when the user selects the file(s) or folder(s).
    /// If the user selects something, the Capacitor plugin will return the array with the defined key to the view.
    /// - Parameters:
    ///   - controller: <#controller description#>
    ///   - urls: urls contains an array of URL objects with the selected elements (files or folders).
    public func documentPicker(
        _ controller: UIDocumentPickerViewController,
        didPickDocumentsAt urls: [URL]
    ){
        var items: [String] = []
        
        for url in urls {
            items.append(url.absoluteString)
        }
        
        self._call?.resolve([
            self.pickerType: items
        ])
    }
}
