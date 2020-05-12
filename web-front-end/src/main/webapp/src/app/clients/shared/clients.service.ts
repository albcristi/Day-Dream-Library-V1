import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Client} from "./clients.model";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable()
export class ClientsService{
  private clientsURL = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) {
  }

  getClients(): Observable<Client[]>{
    return this.httpClient
      .get<Array<Client>>(this.clientsURL);
  }

  getClient(id: number): Observable<Client> {
      return this.getClients()
        .pipe(
          map(clients => clients.find(client => client.id === id))
        );
  }

  deleteClient(id: number): Observable<any>{
    const deleteUrl = `${this.clientsURL}/${id}`;
    return this.httpClient.delete(deleteUrl);
  }

  updateClient(client: Client): Observable<Client>{
    const updateURL = `${this.clientsURL}/${client.id}`;
    return this.httpClient
      .put<Client>(updateURL, client);
  }

  addClientFct(client: Client): Observable<any>{
    return this.httpClient
      .post(this.clientsURL, client);
  }
}
