import { Component, OnInit } from '@angular/core';
import {ClientsService} from "../shared/clients.service";
import {Client} from "../shared/clients.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent implements OnInit {

  constructor(private clientsService: ClientsService,
              private location: Location) { }

  ngOnInit(): void {
  }

  saveClient(clientName: string){
    let client = new Client();
    client.id = 0;
    client.name = clientName;
    client.moneySpent = 0;

    this.clientsService
      .addClientFct(client)
      .subscribe( o => alert("Operation finished with status: "+o));
    this.location.back();
  }
}
