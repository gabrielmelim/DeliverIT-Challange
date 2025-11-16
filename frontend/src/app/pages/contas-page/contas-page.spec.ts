import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContasPage } from './contas-page';

describe('ContasPage', () => {
  let component: ContasPage;
  let fixture: ComponentFixture<ContasPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContasPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContasPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
