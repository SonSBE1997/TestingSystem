export interface DetailExam {
  id: string;
  title: string;
  duration: number;
  category_id: string;
  note: string;
  number_of_question: number;
  created_at: Date;
  create_by: string;
  questions: [
    {
      question_id: number;
      content: string;
      answers: [
        {
          content: string;
          is_true: number;
        }
      ]
    }
  ];
}
