import { Component, OnInit } from '@angular/core';
import {Client} from "../../clients/shared/clients.model";
import {ClientsService} from "../../clients/shared/clients.service";
import {Router} from "@angular/router";
import {BookService} from "../../books/shared/boook.service";
import {Book} from "../../books/shared/book.model";
import {SalesService} from "../shared/sales.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-sales-buy-book',
  templateUrl: './sales-buy-book.component.html',
  styleUrls: ['./sales-buy-book.component.css']
})
export class SalesBuyBookComponent implements OnInit {

  errorMessage: string;
  clients: Array<Client>;
  selectedClient: Client;
  selectedBook: Book;
  booksArr: Array<Book>;

  constructor(private clientService: ClientsService,
              private router: Router,
              private bookService: BookService,
              private salesService: SalesService,
              private location: Location) { }


  ngOnInit(): void {
    this.getClients();
    document
      .getElementById("book-cont-buy")
      .style
      .display ="none";
  }

  getClients(): void {
    this.clientService
      .getClients()
      .subscribe(result =>{
        this.clients = result;
      },
      error => {
        this.errorMessage = error;
      });
  }

  getBooks(): void{
    this.bookService
      .getBooks()
      .subscribe(result =>{
          this.booksArr = result;
        },
        error => {
          this.errorMessage = error;
        });
  }


  onSelect(client: Client) {
    document
      .getElementById("cl-cont-buy")
      .style
      .display = "none";
    this.selectedClient = client;
    document
      .getElementById("book-cont-buy")
      .style
      .display ="block";
    this.getBooks();
  }

  buyBook(): void{
      this.salesService
        .addSale(this.selectedBook.id, this.selectedClient.id)
        .subscribe(result =>{
          alert("Operation result: "+result);
        });
      this.location.back();
  }

  onSelectBook(book: Book): void {
    this.selectedBook = book;
  }
}



