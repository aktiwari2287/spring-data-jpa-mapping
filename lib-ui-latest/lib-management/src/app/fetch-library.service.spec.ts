import { TestBed } from '@angular/core/testing';

import { FetchLibraryService } from './fetch-library.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { Book } from './book';
describe('FetchLibraryService', () => {
  let service: FetchLibraryService,
      httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(FetchLibraryService);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  afterEach(()=>{
    httpTestingController.verify();
  })
  
  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch all the books', ()=>{
      const dummyBooks: Book[] = [
          {
            "book_id": "2",
            "isbn": "123",
            "title": "html",
            "cover": "html 5",
            "publisher": "Anand kumar",
            "pages": "500",
            "available": "105",
            "library": {
              "name": "html"
            }
          }
      ];

      service.getAllBooks().subscribe(books=>{
        expect(books.length).toBe(1);
        expect(books).toEqual(dummyBooks);
      });

      const request = httpTestingController.expectOne(service.ROOT_URL);
      expect(request.request.method).toBe('GET');
      request.flush(dummyBooks);

  });
 
});
