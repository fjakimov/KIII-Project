package mk.finki.ukim.mk.lab.service.Impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ReviewRepository;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Review> listAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review addReviewToBook(Long reviewId, Long bookId) {
        Review review = reviewRepository.findById(reviewId).get();
        Book book = bookRepository.findById(bookId).get();
        book.addReview(review);
        bookRepository.save(book);
        return review;
    }

    @Override
    public Optional<Review> save(Long bookId, Long reviewId, Integer score, String description, LocalDateTime localDateTime) {
        Book book = bookRepository.findById(bookId).get();
        Review review = reviewRepository.findById(reviewId).get();
        addReviewToBook(bookId, reviewId);
        reviewRepository.save(review);
        return Optional.of(new Review(score, description, book, localDateTime));
    }
}
