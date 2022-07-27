import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import { Repartidor } from '../repartidor';
import { RepartidorService } from '../repartidor.service';

@Component({
  selector: 'app-repartidores-search',
  templateUrl: './repartidores-search.component.html',
  styleUrls: ['./repartidores-search.component.css']
})
export class RepartidoresSearchComponent implements OnInit {

  repartidores$!: Observable<Repartidor[]>;
  private searchTerms = new Subject<string>();

  constructor(private repartidorService: RepartidorService) { }

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.repartidores$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.repartidorService.searchRepartidores(term)),
    );
  }

}
