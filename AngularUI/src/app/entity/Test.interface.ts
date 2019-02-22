import { User } from './User.interface';
import { Exam } from './Exam.interface';

export interface Test {
  data: [
    {
      test: {
        testID: number;
        testName: string;
        status: number;
        exam: Exam;
        semesterExam: {
          id: string;
          name: string;
          description: string;
          status: number;
          startTime: Date;
          endTime: Date;
          user: User;
          semesterExamCode: [
            {
              id: number;
              code: string;
              status: string;
              semesterExamId: string;
            }
          ]
        }
      }
      isTest: number;
    }
  ];
}
