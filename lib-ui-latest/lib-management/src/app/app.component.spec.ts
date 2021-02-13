import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { AppComponent } from './app.component';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { FetchLibraryService } from './fetch-library.service';
describe('AppComponent', () => {
  let service: FetchLibraryService;
  let app: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let h1:HTMLElement
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
      imports:[HttpClientTestingModule],
      providers:[FetchLibraryService]
      
    }).compileComponents();
    fixture = TestBed.createComponent(AppComponent);
    app = fixture.componentInstance; 
    h1 = fixture.nativeElement.querySelector('h1');
  }));

  it('should create the app', () => {
    expect(app).toBeTruthy();
  });

  it('should verify the title', ()=> {
    expect(app.title).toEqual('Online Library');
  })
});
