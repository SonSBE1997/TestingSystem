import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpEventType, HttpClient } from '@angular/common/http';
import { UploadserviceService } from 'src/app/service/upload/uploadservice.service';
import { NotifierService } from 'angular-notifier';
import { error } from 'util';


@Component({
  selector: 'app-list-exam',
  templateUrl: './list-exam.component.html',
  styleUrls: ['./list-exam.component.css']
})

export class ListExamComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  currentFileInport: File;
  mess: string;
  check: boolean;

  constructor(private uploadService: UploadserviceService, private http: HttpClient, private notifierService: NotifierService) { }

  ngOnInit() {

  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event instanceof HttpResponse) {
        this.notifierService.notify('error', 'Upload failed!')
        console.log('upload is failed!')
        console.log("sfsff: " + event.type)
      }

      this.notifierService.notify('success', 'File is completely uploaded!');
      console.log('File is completely uploaded!')

      this.uploadService.importToServer(this.currentFileUpload)
        .subscribe(
          //   event => {
          //   if (event instanceof HttpResponse) {
          //     this.notifierService.notify('success', 'Import exam successfully');
          //   } else {
          //     this.notifierService.notify('error', 'Import failed!');
          //   }
          // }
           success => {
           },
           error => {
               console.log("error: " + error.error.text);
              if (error.error.text === 'Ok') {
                this.notifierService.notify('success', 'Import exam successfully');
              }else if(error.error.text  === 'not Ok'){
                this.notifierService.notify('error', 'Import exam Failed');
              }
            }
        );

    });
    this.selectedFiles = undefined;

    // success => {
    // this.notifierService.notify('success', 'Import exam successfully');
    // },
    // error => {
    //     console.log(error.error.text);
    //    if (error.error.text !== 'Ok') {
    //      this.notifierService.notify('error', 'Import exam Failed');
    //    }
    //  }
  }
}
