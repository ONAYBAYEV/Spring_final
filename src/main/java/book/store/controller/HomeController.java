package book.store.controller;

import book.store.model.AuthorModel;
import book.store.model.BookModel;
import book.store.model.GenreModel;
import book.store.model.User;
import book.store.service.*;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private final BookService bookService;
    private final AuthorService authorService; 
    private final GenreService genreService;
    private final UserService userService;




    @GetMapping(value = "/")
    public String indexPage(Model model,
                            @RequestParam(name = "key",required = false,defaultValue = "")String key){
        model.addAttribute("books",bookService.searchBook(key));
        return "index";
    }

    @GetMapping(value ="/details/{bookId}")
    public String  bookDetails(@PathVariable(name = "bookId")Long id, Model model){
        BookModel book=bookService.getBook(id);
        model.addAttribute("kitap",book);

        List<AuthorModel> authorModelList=authorService.getAuthors();
        model.addAttribute("authors",authorModelList);

        List< GenreModel> genreModelList= genreService.getGenres();
        genreModelList.removeAll(book.getGenres());
        model.addAttribute("genres",genreModelList);
        return "details";
    }
    @PostMapping(value = "/save-book")
    public String saveBook(BookModel book){
        bookService.saveBook(book);
        return "redirect:/";
    }



    @GetMapping(value = "/403-page")
    public String accessDenied(){

        return "403";
    }

    @GetMapping(value = "/sign-in-page")
    public String signinPage(){

        return "signin";
    }

    @GetMapping(value = "/sign-up-page")
    public String signupPage(){

        return "signup";
    }
    @GetMapping(value = "update-password-page")
    public String updatePasswordPage(){

        return "update_password";
    }

    @GetMapping(value = "/profile")
    public String profilePage(){
        return "profile";
    }
    @PostMapping(value = "/to-sign-up")
    public String toSignUp(
        @RequestParam(name = "user_email") String email,
        @RequestParam(name = "user_password") String password,
        @RequestParam(name = "user_repeat_password") String repeatPassword,
        @RequestParam(name = "user_full_name") String fullName){
       if (password.equals(repeatPassword)){
           User user= new User();
           user.setEmail(email);
           user.setIsBanned(false);
           user.setFullname(fullName);
           user.setPassword(password);
           User newUser =userService.addUser(user);
           if(newUser != null){
               return "redirect:/profile?success";
           }else {
               return "redirect:/sign-up-page?emailerror";
           }
        }else {
           return "redirect:/sign-up-page?passworderror";
       }
    }


    @PostMapping(value = "/to-update-password")
    public String toUpdatePassword(
            @RequestParam(name = "user_old_password") String oldPassword,
            @RequestParam(name = "user_new_password") String newPassword,
            @RequestParam(name = "user_repeat_new_password") String repeatNewPassword){

        if (newPassword.equals(repeatNewPassword)){

            User user = userService.updatePassword(newPassword,oldPassword);
            if (user != null){
                return "redirect:/update-password-page?success";
            }else{
                return "redirect:/update-password-page?oldpassworderror";

            }

        }else {
            return "redirect:/update-password-page?passwordmissmatch";
        }
    }

}
