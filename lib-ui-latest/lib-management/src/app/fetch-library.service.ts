import { Injectable, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Book } from './book';
@Injectable({
  providedIn: 'root'
})

export class FetchLibraryService implements OnInit{

  ROOT_URL: string = 'http://localhost:8080/books/all';
  constructor(private http: HttpClient) { }
  ngOnInit(): void {
  
  }

  getAllBooks(){
    return this.http.get<Book[]>(this.ROOT_URL);
  }
  
}
