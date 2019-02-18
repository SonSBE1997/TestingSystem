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

  constructor(private uploadService: UploadserviceService, private http: HttpClient, private notifierService: NotifierService) { }

  ngOnInit() {

  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.currentFileUpload = this.selectedFiles.item(0);
    this.currentFileInport = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload);

    console.log("imp" + this.currentFileInport);
    console.log("up: " + this.currentFileUpload);
    setTimeout(() => {
    }, 3000);

    this.uploadService.importToServer(this.currentFileInport)
      .subscribe(
        // event => {
        //   console.log("Event: " + event);
        //   if (event instanceof HttpResponse) {
        //     this.notifierService.notify('success', 'Import exam successfully');
        //     console.log('File is completely uploaded!');
        //    }else {
        //      this.notifierService.notify('error', 'Import exam Failed');
        //    }
        //  }

        success => {
        this.notifierService.notify('success', 'Import exam successfully');
        },
        error => {
            console.log(error.error.text);
           if (error.error.text !== 'Ok') {
             this.notifierService.notify('error', 'Import exam Failed');
           }
         }
      );
  }
}
