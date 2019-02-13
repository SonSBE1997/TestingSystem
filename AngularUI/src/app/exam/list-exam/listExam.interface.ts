export interface ListExams {
  exam_id: string;
  title: string;
  categoryName: string;
  duration: number;
  numberOfQuestion: number;
  create_by: string;
  status: string;
  create_at: Date;
}
export interface ListExamsApi {
  items: ListExams[];
  total_count: number;
}
