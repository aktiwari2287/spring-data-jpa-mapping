import { Component, OnInit } from '@angular/core';
import { FetchLibraryService } from './fetch-library.service';
import { Book } from './book';
import { Library } from './library';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor(private fetchLibraryService:FetchLibraryService){

  }
  books:Book[];
  len:number;
  libraries:Library[];
  ngOnInit(): void {
   
  }
  title:string = "Online Library" 
  ngAfterViewInit(){
    this.fetchLibraryService.getAllBooks().subscribe((books: Book[])=>{
      this.books = books;
      this.len=books.length;
    }); 

    this.fetchLibraryService.getAllLibraries().subscribe((libraries: Library[])=>{
      this.libraries = libraries;
      console.log(this.libraries);
    });
  }

  getBooksForALibrary(event) {
    console.log(event.target.value);
    if(event.target.value==='Choose Library') {
      this.fetchLibraryService.getAllBooks().subscribe((books: Book[])=>{
        this.books = books;
        this.len=books.length;
      }); 
    }
    else {
      this.fetchLibraryService.getAllBooksForLibrary(event.target.value).subscribe((books: Book[])=>{
        this.books = books;
        this.len=books.length;
      }); 
    }
   
  }



}
