import { Component, EventEmitter, Output } from '@angular/core';
import { MatBottomSheetRef } from '@angular/material/bottom-sheet';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  @Output() fileUploaded = new EventEmitter<FileList>();

  constructor(private bottomSheetRef: MatBottomSheetRef<UploadComponent>) {}
  
  onFileSelected(event: any) {
    const files: FileList = event.target.files;
    this.fileUploaded.emit(files);
    this.bottomSheetRef.dismiss();
  }
}
