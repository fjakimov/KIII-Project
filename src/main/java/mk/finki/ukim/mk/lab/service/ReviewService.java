package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> listAll();
    Review addReviewToBook(Long reviewId, Long bookId);
    Optional<Review> save(Long bookId, Long reviewId, Integer score, String description, LocalDateTime localDateTime);

}
