import { Component, OnInit } from '@angular/core';
import { FeesRequestDTO } from 'src/app/dto/Fees/requests/fees-request-DTO';
import { FeesResponseDTO } from 'src/app/dto/Fees/responses/fees-response-DTO';
import { FeesService } from 'src/app/services/Fees/fees.service';
import { NotificationService } from 'src/app/services/Notification/notification.service';

@Component({
  selector: 'app-fees',
  templateUrl: './fees.component.html',
  styleUrls: ['./fees.component.css']
})
export class FeesComponent implements OnInit {
  fees: FeesResponseDTO[] = [];
  showForm: boolean = false;
  showUpdateForm: boolean = false;
  name: string = '';
  percentage: number = 0;
  
  feeId: number = 0;
  updatedName: string = '';
  updatedPercentage: number = 0;

  constructor(
    private feesService: FeesService,
    private notificationService: NotificationService,
  ) { }

  ngOnInit(): void {
    this.getAllFees();
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
  }

  closeUpdateForm(): void {
    this.showUpdateForm = false;
}


  onSubmit(): void {
    const feesRequest: FeesRequestDTO = {
      name: this.name,
      percentage: this.percentage
    };

    this.feesService.createFees(feesRequest).subscribe(
      (response) => {
        console.log('Fees created:', response);

        // Reset form fields
        this.name = '';
        this.percentage = 0;

        // Hide the form
        this.showForm = false;

        // Fetch all fees
        this.getAllFees();

        this.notificationService.show(['Fee successfully added'], 'success');
      },
      (error) => {
        this.notificationService.show([error.error.message], 'error');
      }
    );
  }

  onUpdate(): void {
    const feesRequest: FeesRequestDTO = {
      name: this.updatedName,
      percentage: this.updatedPercentage
    };
  
    this.feesService.updateFees(this.feeId, feesRequest).subscribe(
      (response) => {
        console.log('Fees updated:', response);
  
        // Reset form fields
        this.updatedName = '';
        this.updatedPercentage = 0;
  
        // Hide the form
        this.showUpdateForm = false;
  
        // Fetch all fees
        this.getAllFees();
  
        this.notificationService.show(['Fee successfully updated'], 'success');
      },
      (error) => {
        this.notificationService.show([error.error.message], 'error');
      }
    );
  }
  

  getAllFees(): void {
    this.feesService.getAllFees().subscribe(
      (fees: FeesResponseDTO[]) => {
        this.fees = fees;
      },
      (error) => {
        console.error('Error fetching fees:', error);
      }
    );
  }
}
