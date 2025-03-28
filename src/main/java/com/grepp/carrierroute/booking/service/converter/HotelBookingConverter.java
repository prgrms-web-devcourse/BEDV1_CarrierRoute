package com.grepp.carrierroute.booking.service.converter;

import com.grepp.carrierroute.booking.domain.HotelBooking;
import com.grepp.carrierroute.booking.dto.HotelBookingDetailsDto;
import com.grepp.carrierroute.booking.dto.HotelBookingRequestDto;
import com.grepp.carrierroute.booking.dto.HotelBookingResponseDto;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelBookingConverter {
    public HotelBookingResponseDto convertToHotelBookingResponseDto(long totalPrice,
                                                                    List<HotelBooking> bookings,
                                                                    HotelBookingRequestDto requestDto)
    {
        return HotelBookingResponseDto.builder()
                .checkInDate(requestDto.getCheckInDate())
                .checkOutDate(requestDto.getCheckOutDate())
                .totalPrice(totalPrice)
                .numberOfGuestPerRoom(requestDto.getNumOfGuestPerRoom())
                .bookingIdList(
                        bookings.stream()
                                .map(HotelBooking::getId)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public HotelBooking convertToHotelBooking(User user, HotelRoom room, long totalPrice, HotelBookingRequestDto requestDto){
        return HotelBooking.builder()
                .user(user)
                .hotelRoom(room)
                .price(totalPrice)
                .guestNumber(requestDto.getNumOfGuestPerRoom())
                .checkInDate(requestDto.getCheckInDate())
                .checkOutDate(requestDto.getCheckOutDate())
                .additionalRequest(requestDto.getAdditionalRequest())
                .build();
    }

    public HotelBookingDetailsDto convertToHotelBookingDetailsDto(HotelBooking hotelBooking){
        return HotelBookingDetailsDto.builder()
                .bookingId(hotelBooking.getId())
                .hotelRoomId(hotelBooking.getHotelRoom().getId())
                .hotelId(hotelBooking.getHotelRoom().getHotel().getId())
                .hotelName(hotelBooking.getHotelRoom().getHotel().getName())
                .hotelAddress(hotelBooking.getHotelRoom().getHotel().getAddress())
                .roomType(hotelBooking.getHotelRoom().getRoomType())
                .guestNumber(hotelBooking.getGuestNumber())
                .checkInDate(hotelBooking.getCheckInDate())
                .checkOutDate(hotelBooking.getCheckOutDate())
                .price(hotelBooking.getPrice())
                .additionalRequest(hotelBooking.getAdditionalRequest())
                .bookingDate(hotelBooking.getCreatedDate())
                .build();
    }
}
