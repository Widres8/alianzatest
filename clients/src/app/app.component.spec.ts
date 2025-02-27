import { TestBed } from '@angular/core/testing';
import { RouterOutlet } from '@angular/router';
import { SidebarComponent } from '@core/sidebar/sidebar.component';
import { AppComponent } from './app.component';

describe('AppComponent', () => {
  let component: AppComponent;
  beforeEach(async () => {
    component = TestBed.configureTestingModule({
      providers: [AppComponent, RouterOutlet, SidebarComponent],
    }).inject(AppComponent);
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
