import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Book} from "./book.model";
import {map} from "rxjs/operators";

@Injectable()
export class BookService {
  private bookURL = "http://localhost:8080/api/books";

  constructor(private httpClient: HttpClient) {
  }

  getBooks(): Observable<Book[]>{
    return this.httpClient
      .get<Array<Book>>(this.bookURL);
  }

  getBook(id: number): Observable<Book>{
    return this.getBooks()
      .pipe(
        map( books => books.find(
          book => book.id === id
        ))
      );
  }

  deleteBook(id: number): Observable<any>{
    const deleteURL = `${this.bookURL}/${id}`;
    return this.httpClient
      .delete(deleteURL);
  }

  addBookFct(book: Book): Observable<any>{
    return this.httpClient
      .post(this.bookURL, book);
  }

  updateBookFct(book: Book): Observable<any>{
    const updateURL = `${this.bookURL}/${book.id}`;
    return this.httpClient
      .put(updateURL, book);
  }
}
