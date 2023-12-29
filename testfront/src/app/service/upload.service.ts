import { HttpClient, HttpEvent, HttpEventType, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Page } from '../model/interfaces';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private readonly API = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient, private snackBar: MatSnackBar) { }

  upload(files: FileList): Observable<HttpEvent<any>>[] {
    const uploadObservables: Observable<HttpEvent<any>>[] = [];

    for (let i = 0; i < files.length; i++) {
      const uploadRequest = this.processAndUploadFile(files[i]);
      uploadObservables.push(uploadRequest);
    }

    return uploadObservables;
  }

  buscar(regiao: string, page: number, size: number): Observable<Page> {
    const url = `${this.API}/agentes/${regiao}`; 
    const params = new HttpParams().append('page', page).append('size', size)
    return this.http.get<Page>(url, {params});
  }

  private processAndUploadFile(file: File): Observable<HttpEvent<any>> {
    return new Observable(observer => {
      const reader = new FileReader();

      reader.onload = (evt: any) => {
        const xmlData: string = evt.target.result;
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
        const precoMedioTags = xmlDoc.getElementsByTagName('precoMedio');

        for (let i = 0; i < precoMedioTags.length; i++) {
          const valoresPrecoMedio = precoMedioTags[i].getElementsByTagName('valor');
          for (let j = 0; j < valoresPrecoMedio.length; j++) {
            valoresPrecoMedio[j].textContent = ''; 
          }
        }

        const modifiedXML = new XMLSerializer().serializeToString(xmlDoc);

        const formData = new FormData();
        formData.append('file', new Blob([modifiedXML], { type: 'text/xml' }), file.name);

        const url = `${this.API}/carregar`;
        const uploadRequest = this.http.post(url, formData, {
          reportProgress: true,
          observe: 'events'
        });

        uploadRequest.subscribe({
          next: event => {
            observer.next(event);
          },
          error: error => {
            observer.error(error);
          },
          complete: () => {
            observer.complete();
          }
        });
      };

      reader.readAsText(file);
    });
  }

  showSuccessNotification() {
    this.snackBar.open('Upload concluído com sucesso', 'Fechar', {
      duration: 3000
    });
  }

  showErrorNotification(erro: string) {
    this.snackBar.open('Erro ao fazer upload ' + erro, 'Fechar', {
      duration: 3000,
      panelClass: ['error-snackbar']
    });
  }
}