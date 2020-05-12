import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientsComponent } from './clients/clients.component';
import { BooksComponent } from './books/books.component';
import { ClientListComponent } from './clients/client-list/client-list.component';
import {ClientsService} from "./clients/shared/clients.service";
import {HttpClientModule} from "@angular/common/http";
import {ClientDetailComponent} from "./clients/client-detail/client-detail.component";
import {FormsModule} from "@angular/forms";
import { ClientNewComponent } from './clients/client-new/client-new.component';
import { BookListComponent } from './books/book-list/book-list.component';
import {BookService} from "./books/shared/boook.service";
import { SalesComponent } from './sales/sales.component';
import { BookDetailComponent } from './books/book-detail/book-detail.component';
import { BookNewComponent } from './books/book-new/book-new.component';
import { SalesListComponent } from './sales/sales-list/sales-list.component';
import {SalesService} from "./sales/shared/sales.service";
import { SalesBuyBookComponent } from './sales/sales-buy-book/sales-buy-book.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientsComponent,
    BooksComponent,
    ClientListComponent,
    ClientDetailComponent,
    ClientNewComponent,
    BookListComponent,
    SalesComponent,
    BookDetailComponent,
    BookNewComponent,
    SalesListComponent,
    SalesBuyBookComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [ClientsService, BookService, SalesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
