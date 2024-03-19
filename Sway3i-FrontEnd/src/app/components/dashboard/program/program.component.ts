import { Component } from '@angular/core';
import { ProgramRequestDTO } from 'src/app/dto/Program/requests/program-request-DTO';
import { ProgramResponseDTO } from 'src/app/dto/Program/responses/program-response-DTO';
import { Days } from 'src/app/modal/enums/days-enum';
import { NotificationService } from 'src/app/services/Notification/notification.service';
import { ProgramService } from 'src/app/services/Program/program.service';

@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrls: ['./program.component.css']
})
export class ProgramComponent {
  programs: ProgramResponseDTO[] = [];
  showForm: boolean = false;
  day: string = '';
  time: string = '';

  constructor(
    private programService: ProgramService,
    private notificationService: NotificationService
    ) { }

  ngOnInit(): void {
    this.getAllPrograms();
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  getAllPrograms(): void {
    this.programService.getAllPrograms().subscribe(
      (programs: ProgramResponseDTO[]) => {
        this.programs = programs;
      },
      (error) => {
        console.error('Error fetching programs:', error);
      }
    );
  }

  onSubmit(): void {
    const programRequest: ProgramRequestDTO = {
      day: this.day as unknown as Days,
      time: this.time
    };

    this.programService.createProgram(programRequest).subscribe(
      (response) => {

        // Reset form fields
        this.day = '';
        this.time = '';

        // Hide the form
        this.showForm = false;
        
        // Fetch all programs
        this.getAllPrograms();

        this.notificationService.show(['Program added successfully'], 'success');
      },
      (error) => {
        
        this.notificationService.show([error.error.message], 'error');
      }
    );
  }

  deleteProgram(program: ProgramResponseDTO): void {
    if (confirm('Are you sure you want to delete this program?')) {
      this.programService.deleteProgram(program.id).subscribe(
        () => {
          // Remove the deleted program from the array
          this.programs = this.programs.filter(p => p !== program);
          this.notificationService.show(['Program deleted successfully'], 'success');
        },
        (error) => {
          this.notificationService.show([error.error.message], 'error');
        }
      );
    }
  }
}
