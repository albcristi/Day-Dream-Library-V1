import { Component, OnInit } from '@angular/core';
import {Client} from "../shared/clients.model";
import {ClientsService} from "../shared/clients.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  errorMessage: string;
  clients: Array<Client>;
  selectedClient: Client;
  constructor(private clientService: ClientsService,
              private router: Router) { }

  ngOnInit(): void {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients()
      .subscribe(
        clients => {console.log(clients);
          this.clients = clients},
        error => this.errorMessage = <any> error
      );
  }

  onSelect(client: Client): void{
    this.selectedClient = client;
  }

  goToClientDetail(){
    this.router.navigate(['/clients/detail', this.selectedClient.id]);
  }

  removeClient(client: Client){
    this.clientService.deleteClient(client.id)
      .subscribe(_ =>{
        this.clients = this.clients.filter(c=> c.id !== client.id);
      })
  }
}
