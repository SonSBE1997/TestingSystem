import { User } from './User.interface';

export interface Semester {
  data: [
    {
      id: string;
      name: string;
      description: string;
      status: number;
      startTime: Date;
      endTime: Date;
      user: User;
    }
  ];
}
