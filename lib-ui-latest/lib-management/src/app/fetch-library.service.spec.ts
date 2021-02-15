import { TestBed } from '@angular/core/testing';

import { FetchLibraryService } from './fetch-library.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { Book } from './book';
import { Library } from './library';
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

      const request = httpTestingController.expectOne(service.ROOT_URL+'books/all');
      expect(request.request.method).toBe('GET');
      request.flush(dummyBooks);

  });
 
  it('should fetch all the Libraries', ()=>{
    const dummyLibraries:Library[] = [{
                                        "lib_id":"1",
                                        "name":"html"
                                      }
                                  ];
    service.getAllLibraries().subscribe(libraries=>{
      expect(libraries.length).toBe(1);
      expect(libraries).toEqual(dummyLibraries);;
    })

    const request = httpTestingController.expectOne(service.ROOT_URL+'libraries/all');
    expect(request.request.method).toBe('GET');
    request.flush(dummyLibraries);
  });

  it('should fetch all the books for a given Library', ()=>{
      const dummyBooks: Book[] = [
        {
          "book_id": "100",
          "isbn": "12345",
          "title": "GoLang",
          "cover": "GoLang2",
          "publisher": "Anand kumar",
          "pages": "500",
          "available": "105",
          "library": {
            "name": "GoLang"
          }
        }
    ];

    service.getAllBooksForLibrary('1').subscribe(books=>{
      expect(books.length).toBe(1);
      expect(books).toEqual(dummyBooks);
    });

    const request = httpTestingController.expectOne(service.ROOT_URL+`books/all/1`);
    expect(request.request.method).toBe('GET');
    request.flush(dummyBooks);
    })
});
