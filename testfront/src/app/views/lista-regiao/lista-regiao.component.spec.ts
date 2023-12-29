import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaRegiaoComponent } from './lista-regiao.component';

describe('ListaRegiaoComponent', () => {
  let component: ListaRegiaoComponent;
  let fixture: ComponentFixture<ListaRegiaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaRegiaoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaRegiaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
