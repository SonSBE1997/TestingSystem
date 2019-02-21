import { Category } from 'src/app/category/category.interface';
import { User } from '../user/user.interface';

export interface Exam {
  Constructor()
  examId: string;
  title: string;
  duration: number;
  note: string;
  category: Category;
  status: string;
  numberOfQuestion: number;
  enable: boolean;
  createAt: Date;
  userCreated: User;
  modifiedAt: Date;
  modifiedBy: User;
  caterogyName: string;
}
