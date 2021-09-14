package com.giftshop.service.Impl;

import com.giftshop.domain.Perfume;
import com.giftshop.domain.Product;
import com.giftshop.domain.Review;
import com.giftshop.domain.User;
import com.giftshop.repository.PerfumeRepository;
import com.giftshop.repository.ProductRepository;
import com.giftshop.repository.ReviewRepository;
import com.giftshop.repository.UserRepository;
import com.giftshop.service.UserService;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PerfumeRepository perfumeRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public DataFetcher<User> getUserByQuery() {
        return dataFetchingEnvironment -> {
            Long userId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return userRepository.findById(userId).get();
        };
    }

    @Override
    public DataFetcher<List<User>> getAllUsersByQuery() {
        return dataFetchingEnvironment -> userRepository.findAllByOrderByIdAsc();
    }

    /*@Override
    public List<Perfume> getCart(List<Long> perfumeIds) {
        return perfumeRepository.findByIdIn(perfumeIds);
    }*/

    @Override
    public List<Product> getCart(List<Long> productIds) {
        return productRepository.findByIdIn(productIds);
    }

    @Override
    public User updateProfile(String email, User user) {
        User userFromDb = userRepository.findByEmail(email);
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setCity(user.getCity());
        userFromDb.setAddress(user.getAddress());
        userFromDb.setPhoneNumber(user.getPhoneNumber());
        userFromDb.setPostIndex(user.getPostIndex());
        userRepository.save(userFromDb);
        return userFromDb;
    }

    @Override
    public Perfume addReviewToPerfume(Review review, Long perfumeId) {
        Perfume perfume = perfumeRepository.getOne(perfumeId);
        List<Review> reviews = perfume.getReviews();
        reviews.add(review);
        double totalReviews = reviews.size();
        double sumRating = reviews.stream().mapToInt(Review::getRating).sum();
        perfume.setPerfumeRating(sumRating / totalReviews);
        reviewRepository.save(review);
        return perfume;
    }
}
