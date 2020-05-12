import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {SalesService} from "../shared/sales.service";
import {Sales} from "../shared/sales.model";

@Component({
  selector: 'app-sales-list',
  templateUrl: './sales-list.component.html',
  styleUrls: ['./sales-list.component.css']
})
export class SalesListComponent implements OnInit {
  errorMessage: string;
  salesArr: Array<Sales>;
  selectedSale: Sales;
  constructor(private salesService: SalesService,
              private router: Router) { }


  ngOnInit(): void {

    this.getSales();
  }

   getSales(): void {
    this.salesService
      .getSales()
      .subscribe(
        response => {
          this.salesArr = response;
        }
      )
  }

}
