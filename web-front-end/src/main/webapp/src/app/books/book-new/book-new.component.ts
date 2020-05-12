import { Component, OnInit } from '@angular/core';
import {BookService} from "../shared/boook.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-book-new',
  templateUrl: './book-new.component.html',
  styleUrls: ['./book-new.component.css']
})
export class BookNewComponent implements OnInit {

  constructor(private bookService: BookService,
              private location: Location) { }

  ngOnInit(): void {
  }

   ConvertStringToNumber(input: string): number {
    var numeric = Number(input);
    return numeric;
  }
  saveBook(title: string, author: string, price: string): void {
    if(isNaN(Number(price))){
      alert("Price not valid");
      return;
    }
    let priceValue = this.ConvertStringToNumber(price);
    if(priceValue<1){
      alert("Invalid Price");
      return;
    }
    this.bookService.addBookFct({
      id: 0,
      title: title,
      author: author,
      price: priceValue
    })
      .subscribe(r => alert("finished with message: "+r));
    this.location.back();
  }
}
