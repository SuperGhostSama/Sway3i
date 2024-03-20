export interface CourseResponseDTO {
    id: number;
    createdAt: string;
    createdByUserId: number;
    subject: string;
    courseName: string;
    courseDetails: string;
    courseIsFor: string;
    price: number;
    city: string;
    educationLevel: string;
    type: string;
    maxStudents: number;
    programIds: number[];
    link: string;
    address: string;
  }
  