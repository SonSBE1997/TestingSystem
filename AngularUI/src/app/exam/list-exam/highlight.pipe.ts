import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import {isUndefined} from 'util';

@Pipe({name: 'highlight'})
export class HighlightPipe implements PipeTransform {

  constructor(private _sanitizer: DomSanitizer) { }
  transform(text: string, search): string {
      try {
          if (text && search && !isUndefined(text) && !isUndefined(search)) {
              text = text.toString(); // sometimes comes in as number
              search = search.trim();
              if (search.length > 0) {
                  let pattern = search.trim().replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
                  pattern = pattern.split(' ').filter((t) => {
                      return t.length > 0;
                  }).join('|');
                  let regex = new RegExp(pattern, 'gi');

                  text = text.replace(regex, (match) => `<span style='background-color:yellow'>${match}</span>`);
              }
          }
      }
      catch (exception) {
          console.error('error in highlight: ', exception);
      }
      return this._sanitizer.bypassSecurityTrustHtml(text);
  }
}
