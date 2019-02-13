import { Category } from './Category.interface';
import { User } from './User.interface';
import { Question } from '../exam/update-content/update-content.interface';

export interface Exam {
  examId: string;
  title: string;
  duration: number;
  note: string;
  status: string;
  categoryName: string;
  createAt: Date;
  modifiedAt: DataCue;
  numberOfQuestion: number;
  category: Category;
  userCreated: User;
  modifiedBy: User;
  examQuestions: [
    {
      id: number;
      question: Question;
      choiceOrder: string;
    }
  ];
  enable: boolean;
}
