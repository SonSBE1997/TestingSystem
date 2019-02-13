export interface Exam {
  examId: string;
  title: string;
  duration: number;
  category: {
    id: number;
    categoryName: string;
  };
  note: string;
  numberOfQuestion: number;
  created_at: Date;
  create_by: string;
  status: string;
  examQuestions: ExamQuestion[];
}

export interface ExamQuestion {
  id: number;
  choiceOrder: string;
  question: Question;
}

export interface Question {
  questionId: string;
  content: string;
  answers: [
    {
      content: string;
      true: number;
    }
  ];
}

export interface Selection {
  id: string;
  checked: boolean;
}

export interface TabInfo {
  currentPage: number;
  entities: number;
  sizeOfPage: number;
}
