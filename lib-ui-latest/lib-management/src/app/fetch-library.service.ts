import { Injectable, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Book } from './book';
import { Library } from './library';
@Injectable({
  providedIn: 'root'
})

export class FetchLibraryService implements OnInit{

  ROOT_URL: string = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }
  ngOnInit(): void {
  
  }

  getAllBooks(){
    return this.http.get<Book[]>(this.ROOT_URL+'books/all');
  }
  
  getAllLibraries() {
    return this.http.get<Library[]>(this.ROOT_URL+'libraries/all');
  }

  getAllBooksForLibrary(lib_id:string){
    return this.http.get<Book[]>(this.ROOT_URL+`books/all/${lib_id}`);
  }
}
