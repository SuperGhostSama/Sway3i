import { Component } from '@angular/core';
import { TeacherDemandResponseDTO } from 'src/app/dto/TeacherDemand/responses/teacher-demand-response-DTO';
import { TeacherDemandService } from 'src/app/services/TeacherDemand/teacher-demand.service';

@Component({
  selector: 'app-all-teacher-demands',
  templateUrl: './all-teacher-demands.component.html',
  styleUrls: ['./all-teacher-demands.component.css']
})
export class AllTeacherDemandsComponent {
  teacherDemands: TeacherDemandResponseDTO[] = [];

  constructor(private teacherDemandService: TeacherDemandService) {}

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
}
