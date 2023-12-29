import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaRegiaoComponent } from './views/lista-regiao/lista-regiao.component';
import { UploadComponent } from './componentes/upload/upload.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import ptBr from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { PaginatorService } from './service/paginator.service';

registerLocaleData(ptBr);

@NgModule({
  declarations: [
    AppComponent,
    UploadComponent,
    ListaRegiaoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatProgressBarModule,
    MatBottomSheetModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatTableModule,
    MatPaginatorModule,
  ],
  providers: [{ provide: LOCALE_ID, useValue: 'pt' }, {provide: MatPaginatorIntl, useClass: PaginatorService}],
  bootstrap: [AppComponent]
})
export class AppModule { }
