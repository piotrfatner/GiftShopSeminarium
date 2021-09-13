package com.giftshop.mapper;

import com.giftshop.dto.RegistrationRequest;
import com.giftshop.dto.user.UserRequest;
import com.giftshop.service.UserService;
import com.giftshop.domain.Review;
import com.giftshop.domain.User;
import com.giftshop.dto.perfume.PerfumeResponse;
import com.giftshop.dto.review.ReviewRequest;
import com.giftshop.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final PerfumeMapper perfumeMapper;
    private final UserService userService;

    private User convertToEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    User convertToEntity(RegistrationRequest registrationRequest) {
        return modelMapper.map(registrationRequest, User.class);
    }

    private Review convertToEntity(ReviewRequest reviewRequest) {
        return modelMapper.map(reviewRequest, Review.class);
    }

    UserResponse convertToResponseDto(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public UserResponse findUserById(Long userId) {
        return convertToResponseDto(userService.findUserById(userId));
    }

    public UserResponse findUserByEmail(String email) {
        return convertToResponseDto(userService.findUserByEmail(email));
    }

    public List<PerfumeResponse> getCart(List<Long> perfumesIds) {
        return perfumeMapper.convertListToResponseDto(userService.getCart(perfumesIds));
    }

    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponse updateProfile(String email, UserRequest userRequest) {
        return convertToResponseDto(userService.updateProfile(email, convertToEntity(userRequest)));
    }

    public PerfumeResponse addReviewToPerfume(ReviewRequest reviewRequest, Long perfumeId) {
        return perfumeMapper.convertToResponseDto(userService.addReviewToPerfume(convertToEntity(reviewRequest), perfumeId));
    }
}
