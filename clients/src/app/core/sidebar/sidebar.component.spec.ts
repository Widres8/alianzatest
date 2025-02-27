import { TestBed } from '@angular/core/testing';

import { RouterLink } from '@angular/router';
import { SidebarComponent } from './sidebar.component';

describe('SidebarComponent', () => {
  let component: SidebarComponent;
  beforeEach(async () => {
    component = TestBed.configureTestingModule({
      providers: [SidebarComponent, RouterLink],
    }).inject(SidebarComponent);
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
