import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { ApiClientService } from '../api-client.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
  providers: [HttpClient, ApiClientService],
})
export class SearchComponent {
  query = new FormControl('');
  docs$!: Observable<string[]>;
  error: string = '';

  constructor(private service: ApiClientService) {}

  submit(): void {
    if (!this.query.value) {
      this.error = 'Please enter a query';
    } else {
      this.docs$ = this.service.getDocs(this.query.value);
      if (!this.docs$) this.error = 'No matches found';
    }
  }
}
