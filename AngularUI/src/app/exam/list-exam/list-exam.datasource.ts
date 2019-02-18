

import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';
import { Exam } from 'src/app/entity/Exam.interface';
import { ListExamService } from 'src/app/exam/list-exam/list-exam.service';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {catchError, finalize} from 'rxjs/operators';
import {of} from 'rxjs/observable/of';



export class ListExamDataSource implements DataSource<Exam> {


    private examSubject = new BehaviorSubject<Exam[]>([]);

    private loadingSubject = new BehaviorSubject<boolean>(false);

    public loading$ = this.loadingSubject.asObservable();

    constructor(private listExamService: ListExamService) {

    }

    public loadExams(  sortOrder: string,
                pageIndex: number,
                pageSize: number) {

        this.loadingSubject.next(true);

        this.listExamService.findExams(sortOrder,
            pageIndex, pageSize).pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe(listExam => this.examSubject.next(listExam));
    }

    connect(collectionViewer: CollectionViewer): Observable<Exam[]> {
        console.log('Connecting data source');

        return this.examSubject.asObservable();

    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.examSubject.complete();
        this.loadingSubject.complete();
    }

}
