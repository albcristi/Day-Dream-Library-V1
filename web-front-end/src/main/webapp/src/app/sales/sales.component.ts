import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToBuyBook(): void {
    this.router.navigate(['sales/buy']);
  }
}
