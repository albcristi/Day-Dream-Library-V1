import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../shared/clients.model";
import {ClientsService} from "../shared/clients.service";
import {ActivatedRoute, Params} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {Location} from '@angular/common';

@Component({
  selector: 'app-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.css']
})
export class ClientDetailComponent implements OnInit {
  @Input() client: Client;

  constructor(private clientsService: ClientsService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route
      .params
      .pipe(switchMap((param: Params) => this.clientsService.getClient(+param['id'])))
      .subscribe(client => this.client = client);
  }

  goBack(): void{
    this.location.back();
  }

  save(): void{
    this.clientsService.updateClient(this.client)
      .subscribe(_ =>{
        this.goBack();
      })
  }
}
