import { Question } from './Question.interface';

export interface ExamQuestion {
  id: number;
  question: Question;
  choiceOrder: string;
}
