import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-update-common',
  templateUrl: './update-common.component.html',
  styleUrls: ['./update-common.component.css']
})
export class UpdateCommonComponent implements OnInit {
  public Editor = ClassicEditor;
  constructor() {}

  ngOnInit() {}
}
