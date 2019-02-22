import { Category } from './Category.interface';
import { User } from './User.interface';
import { ExamQuestion } from './ExamQuestion.interface';

export interface Exam {
  examId: string;
  title: string;
  duration: number;
  note: string;
  status: string;
  categoryName: string;
  createAt: Date;
  modifiedAt: Date;
  numberOfQuestion: number;
  category: Category;
  userCreated: User;
  modifiedBy: User;
  examQuestions: ExamQuestion[];
  enable: boolean;
}
export interface lengthPagi {
  lengthPagi: number;
}

