package com.zachcarrera.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zachcarrera.bookclub.models.Book;
import com.zachcarrera.bookclub.services.BookService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	// ----- ALL BOOKS -----
	@GetMapping("")
	public String allBooks(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		model.addAttribute("books", bookService.allBooks());
		return "allBooks.jsp";
	}

	// ----- ONE BOOK -----
	@GetMapping("/{id}")
	public String showBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		model.addAttribute("book", bookService.findBook(id));
		return "showBook.jsp";
	}

	// ----- NEW BOOK -----
	@GetMapping("/new")
	public String renderNewBook(@ModelAttribute("newBook") Book newBook, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		return "newBook.jsp";
	}
	
	@PostMapping("/new")
	public String processNewBook(
			@Valid @ModelAttribute("newBook") Book newBook,
			BindingResult result,
			HttpSession session
			) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			return "newBook.jsp";
		}

		bookService.createBook(newBook);
		return "redirect:/books";
	}
	
	// ----- EDIT BOOK -----
	@GetMapping("/{id}/edit")
	public String renderEditBook(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session
			) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		
		Book oneBook = bookService.findBook(id);
		
		if (!userId.equals(oneBook.getUser().getId())) {
			return "redirect:/books/" + oneBook.getId();
		}

		model.addAttribute("oneBook", oneBook);
		return "editBook.jsp";
	}
	
	@PutMapping("/{id}/edit")
	public String processEditBook(
			@Valid @ModelAttribute("oneBook") Book oneBook,
			BindingResult result,
			HttpSession session
			) {
		
		Long userId = (Long) session.getAttribute("userId");
		
		if (userId == null ) {
			return "redirect:/";
		}
		if (!userId.equals(oneBook.getUser().getId())) {
			return "redirect:/books/" + oneBook.getId();
		}
		
		if (result.hasErrors()) {
			return "editBook.jsp";
		}

		Book updatedBook = bookService.updateBook(oneBook);
		return "redirect:/books/" + updatedBook.getId();
	}
	
	
	// ----- DELETE BOOK -----
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		
		Book oneBook = bookService.findBook(id);
		
		if (!userId.equals(oneBook.getUser().getId())) {
			return "redirect:/books/" + oneBook.getId();
		}

		bookService.removeBook(id);
		return "redirect:/books";
	}

}
