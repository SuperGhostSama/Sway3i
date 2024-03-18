import { Component } from '@angular/core';
import { TeacherDemandResponseDTO } from 'src/app/dto/TeacherDemand/responses/teacher-demand-response-DTO';
import { NotificationService } from 'src/app/services/Notification/notification.service';
import { TeacherDemandService } from 'src/app/services/TeacherDemand/teacher-demand.service';

@Component({
  selector: 'app-all-teacher-demands',
  templateUrl: './all-teacher-demands.component.html',
  styleUrls: ['./all-teacher-demands.component.css']
})
export class AllTeacherDemandsComponent {
  teacherDemands: TeacherDemandResponseDTO[] = [];

  constructor(
    private teacherDemandService: TeacherDemandService,
    private notificationService: NotificationService
    ) {}

  ngOnInit(): void {
    this.getAllTeacherDemands();
  }

  getAllTeacherDemands(): void {
    this.teacherDemandService.getAllTeacherDemands().subscribe(
      (teacherDemands: TeacherDemandResponseDTO[]) => {
        this.teacherDemands = teacherDemands;
        console.log('Teacher demands:', teacherDemands);
      },
      (error) => {

        console.error('Error fetching teacher demands:', error);
      }
    );
  }


  acceptDemand(id: number): void {
    this.teacherDemandService.acceptTeacherDemand(id).subscribe(
      () => {
        console.log('Demand accepted successfully');

        this.getAllTeacherDemands();
        this.notificationService.show(['Demand accepted successfully'], 'success');

      },
      (error) => {
        console.error('Error accepting demand:', error);
        this.notificationService.show([error.error.message], 'error');

      }
    );
  }

  rejectDemand(id: number): void {
    this.teacherDemandService.rejectTeacherDemand(id).subscribe(
      () => {

        this.getAllTeacherDemands();
        this.notificationService.show(['Demand rejected successfully'], 'success');


      },
      (error) => {
        console.error('Error rejecting demand:', error);

        this.notificationService.show([error.error.message], 'error');

      }
    );
  }
}
