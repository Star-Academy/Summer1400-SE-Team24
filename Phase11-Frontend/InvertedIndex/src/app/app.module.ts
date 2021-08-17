import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchComponent } from './search/search.component';
import { ResultComponent } from './result/result.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ApiClientService } from './api-client.service';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule, MatIcon } from '@angular/material/icon';

@NgModule({
  declarations: [AppComponent, SearchComponent, ResultComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [ApiClientService],
  bootstrap: [AppComponent],
})
export class AppModule {}
