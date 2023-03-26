/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import database.tables.EditBooksTable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mainClasses.Book;

/**
 * REST Web Service
 *
 * @author marilena_sch
 */
@Path("elibrary")
public class BooksAPI {

    @POST
    @Path("/book")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addNewBook(String ebook) throws ClassNotFoundException, SQLException {

        Gson gson = new Gson();
        Book book = gson.fromJson(ebook, Book.class);
        EditBooksTable newBook = new EditBooksTable();

        if (book.getIsbn().length() != 10 && book.getIsbn().length() != 13) {
            return Response.status(Response.Status.FORBIDDEN).type("application/json").entity("Error:Invalid ISBN.").build();
        }

        if (book.getPages() <= 0) {
            return Response.status(Response.Status.FORBIDDEN).type("application/json").entity("Error:Page Number is zero.").build();
        }

        if (book.getPublicationyear() < 1200) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Publication year before 1200.").build();
        }

        if (!(book.getUrl().startsWith("http")) || !(book.getPhoto().startsWith("http"))) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Not valid link.").build();
        }
        if (newBook.databaseToBook(book.getIsbn()) != null) {
            return Response.status(Response.Status.FORBIDDEN).type("application/json").entity("Error:Book already exists.").build();
        } else {
            newBook.addBookFromJSON(ebook);
            return Response.status(Response.Status.OK).type("application/json").entity("Success:New Book Added.").build();
        }
    }
//    @GET
//    @Path("/books/{genre}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getBooks(@PathParam("genre") String genre,
//            @QueryParam("fromYear") String fromYear, @QueryParam("toYear") String toYear) throws SQLException, ClassNotFoundException {
//
//        int from_year = 0;
//        int to_year = 0;
//        if (fromYear != null) {
//            from_year = Integer.parseInt(fromYear);
//        }
//        if (toYear != null) {
//            to_year = Integer.parseInt(toYear);
//        }
//        EditBooksTable book = new EditBooksTable();
//        ArrayList<Book> booksList = new ArrayList<Book>();
//
//        if (fromYear != null && toYear != null) {
//
//            if (from_year > to_year) {
//                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:fromYear can't be greaten than toYear.").build();
//            }
//
//            if (from_year < 1200 || to_year < 1200) {
//                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Publication Year must be after 1200.").build();
//            }
//
//            if (genre.equals("all")) {
//                if ((booksList = book.databaseToBookFromYearToYear(fromYear, toYear)) == null) {
//                    return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book in this range.").build();
//                } else {
//                    Gson gson = new Gson();
//                    JsonArray jsonDoc = gson.toJsonTree(booksList).getAsJsonArray();
//                    return Response.status(Response.Status.OK).type("application/json").entity(jsonDoc.toString()).build();
//                }
//            } else {
//                if ((booksList = book.databaseToBookGenreFromYearToYear(genre, fromYear, toYear)) == null) {
//                    return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book of this genre in this range.").build();
//                } else {
//                    Gson gson = new Gson();
//                    JsonArray jsonDoc = gson.toJsonTree(booksList).getAsJsonArray();
//                    return Response.status(Response.Status.OK).type("application/json").entity(jsonDoc.toString()).build();
//                }
//            }
//        } else if (fromYear != null) {
//            if (from_year < 1200) {
//                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Publication Year must be after 1200.").build();
//            }
//            if (genre.equals("all")) {
//                if ((booksList = book.databaseToBookFromYear(fromYear)) == null) {
//                    return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book in this range.").build();
//                } else {
//                    Gson gson = new Gson();
//                    JsonArray jsonDoc = gson.toJsonTree(booksList).getAsJsonArray();
//                    return Response.status(Response.Status.OK).type("application/json").entity(jsonDoc.toString()).build();
//                }
//            } else {
//                if ((booksList = book.databaseToBookGenreFromYear(genre, fromYear)) == null) {
//                    return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book of this genre in this range.").build();
//                } else {
//                    Gson gson = new Gson();
//                    JsonArray jsonDoc = gson.toJsonTree(booksList).getAsJsonArray();
//                    return Response.status(Response.Status.OK).type("application/json").entity(jsonDoc.toString()).build();
//                }
//            }
//        } else if (toYear != null) {
//            if (to_year < 1200) {
//                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Publication Year must be after 1200.").build();
//            }
//            if (genre.equals("all")) {
//                if ((booksList = book.databaseToBookToYear(toYear)) == null) {
//                    return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book in this range.").build();
//                } else {
//                    Gson gson = new Gson();
//                    JsonArray jsonDoc = gson.toJsonTree(booksList).getAsJsonArray();
//                    return Response.status(Response.Status.OK).type("application/json").entity(jsonDoc.toString()).build();
//                }
//            } else {
//                if ((booksList = book.databaseToBookGenreToYear(genre, toYear)) == null) {
//                    return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book of this genre in this range.").build();
//                } else {
//                    Gson gson = new Gson();
//                    JsonArray jsonDoc = gson.toJsonTree(booksList).getAsJsonArray();
//                    return Response.status(Response.Status.OK).type("application/json").entity(jsonDoc.toString()).build();
//                }
//            }
//        } else {
//            if (genre.equals("all")) {
//                if ((booksList = book.databaseToBook()) == null) {
//                    return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book.").build();
//                } else {
//                    Gson gson = new Gson();
//                    JsonArray jsonDoc = gson.toJsonTree(booksList).getAsJsonArray();
//                    return Response.status(Response.Status.OK).type("application/json").entity(jsonDoc.toString()).build();
//                }
//            } else {
//                if ((booksList = book.databaseToBookGenre(genre)) == null) {
//                    return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book of this genre.").build();
//                } else {
//                    Gson gson = new Gson();
//                    JsonArray jsonDoc = gson.toJsonTree(booksList).getAsJsonArray();
//                    return Response.status(Response.Status.OK).type("application/json").entity(jsonDoc.toString()).build();
//                }
//            }
//        }
//    }

    @GET
    @Path("/books/{genre}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBookgerne(@PathParam("genre") String genre,
            @QueryParam("fromYear") String from,
            @QueryParam("toYear") String to,
            @QueryParam("title") String title,
            @QueryParam("authors") String authors,
            @QueryParam("fromPageNumber") String frompage,
            @QueryParam("toPageNumber") String topage) throws SQLException, ClassNotFoundException {
        EditBooksTable eut = new EditBooksTable();
        ArrayList<Book> books = new ArrayList<Book>();

        int fr = 0;
        int t = 0;
        int pf = 0;
        int pt = 0;
        if (from != null) {
            fr = Integer.parseInt(from);
            if (fr < 1200) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Publication Year must be after 1200.").build();
            }
        }

        if (to != null) {
            t = Integer.parseInt(to);
            if (t < 1200) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Publication Year must be after 1200.").build();
            }

        }
        if (to != null && from != null) {
            if (t < fr) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:fromYear can't be greaten than toYear.").build();
            }
        }
        if (frompage != null) {
            pf = Integer.parseInt(frompage);
            if (pf <= 0) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Number of pages cannot be zero.").build();
            }
        }

        if (topage != null) {
            pt = Integer.parseInt(topage);
            if (pt <= 0) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Number of pages cannot be zero.").build();
            }

        }
        if (topage != null && frompage != null) {
            if (pt < pf) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:pageTo must be greater than pageFrom.").build();
            }
        }
        books = eut.databaseToBooks_specific(genre, fr, t, title, authors, pf, pt);

        if (!books.isEmpty()) {
            String json = new Gson().toJson(books);
            return Response.status(Response.Status.OK).type("application/json").entity(json).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Wrong input!").build();
    }

    @PUT
    @Path("/bookpages/{isbn}/{pages}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBookPages(@PathParam("isbn") String isbn, @PathParam("pages") int pages) throws SQLException, ClassNotFoundException {

        Book book = new Book();
        EditBooksTable newBook = new EditBooksTable();

        if (newBook.databaseToBookISBN(isbn) == null) {
            return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book with this isbn.").build();
        }

        if (pages <= 0) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).type("application/json").entity("Error:Number of pages cannot be zero.").build();
        }

        book = newBook.databaseToBookISBN(isbn);
        newBook.updateBookPages(isbn, pages);
        return Response.status(Response.Status.OK).type("application/json").entity("Success:Book pages were updated").build();
    }

    @DELETE
    @Path("/bookdeletion/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBookIsbn(@PathParam("isbn") String isbn) throws SQLException, ClassNotFoundException {
        EditBooksTable deletedBook = new EditBooksTable();

        if (deletedBook.databaseToBookISBN(isbn) == null) {
            return Response.status(Response.Status.NOT_FOUND).type("application/json").entity("Error:There is no book with this isbn.").build();
        }

        deletedBook.deleteBook(isbn);
        return Response.status(Response.Status.OK).type("application/json").entity("Success:Book with this isbn was successfully deleted.").build();
    }
}
