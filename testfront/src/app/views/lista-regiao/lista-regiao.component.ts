import { HttpEventType } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { UploadComponent } from 'src/app/componentes/upload/upload.component';
import { UploadService } from 'src/app/service/upload.service';
import { Subject, Subscription, debounceTime, map, switchMap, takeUntil, tap, throwError } from 'rxjs';
import { FormControl } from '@angular/forms';
import { Page } from 'src/app/model/interfaces';
import { PageEvent } from '@angular/material/paginator';

const PAUSA = 300

@Component({
  selector: 'app-lista-regiao',
  templateUrl: './lista-regiao.component.html',
  styleUrls: ['./lista-regiao.component.css'],
})
export class ListaRegiaoComponent implements OnInit, OnDestroy{
  uploadProgress = 0;
  showProgressBar = false;
  uploadSubscription: Subscription | undefined;
  mensagemErro = ''
  campoBusca = new FormControl()
  displayedColumns: string[] = ['geracao', 'compra', 'preco_medio']
  page: Page = {
    content: [], totalElements: 0, pageable: {
      pageNumber: 0,
      pageSize: 10
    },
    totalPages: 0
  }; 

  private destroy$ = new Subject<void>();

  constructor(private bottomSheet: MatBottomSheet, private service: UploadService) {}

  ngOnInit() {
    this.campoBusca.valueChanges.pipe(
      debounceTime(PAUSA),
      tap((valorDigitado) => {
        if (valorDigitado.length === 0) {
          this.page = {
            content: [],
            totalElements: 0,
            pageable: {
              pageNumber: 0,
              pageSize: 10
            },
            totalPages: 0
          };
        }
      }),
      switchMap((valorDigitado) => {
        if (valorDigitado.length === 2) {
          return this.service.buscar(valorDigitado.toUpperCase(), this.page.pageable.pageNumber, this.page.pageable.pageSize)
            .pipe(
              takeUntil(this.destroy$),
              map(resultado => this.page = resultado),
            );
        } else {
          return [];
        }
      })
    ).subscribe();
  }

  buscarComPaginacao(event: PageEvent) {
    this.page.pageable.pageSize = event.pageSize;
    this.page.pageable.pageNumber = event.pageIndex;

    this.campoBusca.setValue(this.campoBusca.value);
  }

  openUploadBottomSheet(): void {
    const bottomSheetRef = this.bottomSheet.open(UploadComponent);

    bottomSheetRef.instance.fileUploaded.subscribe((files: FileList) => {
      this.showProgressBar = true;

      const uploadObservables = this.service.upload(files);

      const totalFiles = files.length;
      let completedFiles = 0;

      const subscriptions: Subscription[] = [];
      uploadObservables.forEach((uploadObservable) => {
        const subscription = uploadObservable.subscribe({
          next: (event) => {
            if (event.type === HttpEventType.UploadProgress) {
              if (event.total !== undefined) {
                const progress = Math.round((100 * event.loaded) / event.total);
                this.uploadProgress = Math.ceil(((completedFiles + progress) / totalFiles));
              }
            } else if (event.type === HttpEventType.Response) {
              completedFiles++;
              if (completedFiles === totalFiles) {
                this.showProgressBar = false;
                this.campoBusca.setValue(this.campoBusca.value);
                this.service.showSuccessNotification();
              }
            }
          },
          error: (error) => {
            this.showProgressBar = false;
            this.service.showErrorNotification(error);
          },
        });
        subscriptions.push(subscription);
      });

      this.uploadSubscription = new Subscription();
      this.uploadSubscription.add(...subscriptions);
    });
  }

  ngOnDestroy() {
    if (this.uploadSubscription) {
      this.uploadSubscription.unsubscribe();
    }
  }
}
