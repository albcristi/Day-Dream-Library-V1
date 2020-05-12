import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ClientsComponent} from "./clients/clients.component";
import {ClientDetailComponent} from "./clients/client-detail/client-detail.component";
import {ClientNewComponent} from "./clients/client-new/client-new.component";
import {BooksComponent} from "./books/books.component";
import {BookDetailComponent} from "./books/book-detail/book-detail.component";
import {BookNewComponent} from "./books/book-new/book-new.component";
import {SalesComponent} from "./sales/sales.component";
import {SalesBuyBookComponent} from "./sales/sales-buy-book/sales-buy-book.component";


const routes: Routes = [
  {path: "clients", component: ClientsComponent},
  {path: "clients/detail/:id", component: ClientDetailComponent},
  {path: "clients/new", component: ClientNewComponent},
  {path: "books", component: BooksComponent},
  {path: "books/detail/:id", component: BookDetailComponent},
  {path: "books/new", component: BookNewComponent},
  {path: "sales", component: SalesComponent},
  {path: "sales/buy", component: SalesBuyBookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
