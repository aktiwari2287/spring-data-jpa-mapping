import { Component, OnInit } from '@angular/core';
import { FetchLibraryService } from './fetch-library.service';
import { Book } from './book';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor(private fetchLibraryService:FetchLibraryService){

  }
  books:Book[];
  ngOnInit(): void {
   
  }
  title:string = "Online Library" 
  ngAfterViewInit(){
    this.fetchLibraryService.getAllBooks().subscribe((books: Book[])=>{
      this.books = books;
    }); 
  }
}
