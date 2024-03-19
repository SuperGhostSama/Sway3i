import { Component, OnInit } from '@angular/core';
import { FeesResponseDTO } from 'src/app/dto/Fees/responses/fees-response-DTO';
import { FeesService } from 'src/app/services/Fees/fees.service';

@Component({
  selector: 'app-fees',
  templateUrl: './fees.component.html',
  styleUrls: ['./fees.component.css']
})
export class FeesComponent implements OnInit{
  fees: FeesResponseDTO[] = [];
  showForm: boolean = false;

  constructor(
    private feesService: FeesService
    ) { }

  ngOnInit(): void {
    this.getAllFees();
  }

  toggleForm() {
    this.showForm = !this.showForm;
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
