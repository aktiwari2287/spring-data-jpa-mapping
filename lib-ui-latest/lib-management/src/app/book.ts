export interface Book {
    available: string;
    book_id: string;
    cover: string;
    isbn: string;
    pages: string;
    publisher: string;
    title: string
    library:{
        name:string
    }
  }