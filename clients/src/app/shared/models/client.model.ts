export interface Client {
  id: number;
  username: string;
  name: string;
  email: string;
  phone: string;
  startDate: Date;
  endDate: Date | null;
}
