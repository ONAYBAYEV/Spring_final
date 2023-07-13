package book.store.controller;

import book.store.model.BookModel;
import book.store.model.User;
import book.store.service.BookService;
import book.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class AdminController {
    private final BookService bookService;

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-panel")
    public String adminPanel(Model model){
        List<BookModel> books = bookService.getAllBooks();
        List<User> users = userService.getAllUsers();
        model.addAttribute("books", books);
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping(value ="/assign-genre")
    public String assignGenre(@RequestParam(name = "book_id")Long bookId,
                              @RequestParam(name = "genre_id")Long genreId){

        bookService.assignGenre(bookId,genreId);
        return "redirect:/details/"+bookId;
    }

    @PostMapping(value ="/unassign-genre")
    public String unassignGenre(@RequestParam(name = "book_id")Long bookId,
                                @RequestParam(name = "genre_id")Long genreId){
        bookService.unassignGenre(bookId,genreId);
        return "redirect:/details/"+bookId;
    }

    @PostMapping(value = "/user/toggle/ban")
    public String banUser(@RequestParam(name = "user_id") Long userId,
                                     @RequestParam(name = "ban") Boolean ban) {

        userService.toggleBan(userId, ban);

        return "redirect:/admin-panel";

    }
}
