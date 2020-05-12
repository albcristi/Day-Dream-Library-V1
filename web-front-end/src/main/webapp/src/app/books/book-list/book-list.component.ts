import { Component, OnInit } from '@angular/core';
import {Book} from "../shared/book.model";
import {BookService} from "../shared/boook.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  errorMessage: string;
  books: Array<Book>;
  selectedBook: Book;
  constructor(private bookService: BookService,
              private router: Router) { }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(){
    this.bookService.getBooks()
      .subscribe(
        books => this.books = books,
        error => this.errorMessage = error
      );
  }

  onSelect(book: Book){
    this.selectedBook = book;
  }

  removeBook(book: Book){
    this.bookService
      .deleteBook(book.id)
      .subscribe( _ => {
        this.books = this.books.filter(b => b.id !== book.id);
      });
  }

  goToBookDetail(){
    this.router.navigate(['/books/detail', this.selectedBook.id]);
  }
}
