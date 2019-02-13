export interface Exam {
  id: string;
  title: string;
  duration: number;
  category: [
    {
      id: number;
      name: string;
    }
  ];
  note: string;
  numberOfQuestion: number;
  created_at: Date;
  create_by: string;
  status: string;
  examQuestions: [
    {
      choiceOrder: string;
      question: {
        question_id: string;
        content: string;
        answers: [
          {
            content: string;
            is_true: number;
          }
        ];
      };
    }
  ];
}

export interface Question {
  id: string;
  content: string;
  answers: [
    {
      content: string;
      is_true: number;
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
