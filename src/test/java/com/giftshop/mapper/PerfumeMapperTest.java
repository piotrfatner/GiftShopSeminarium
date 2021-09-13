package com.giftshop.mapper;

import com.giftshop.domain.Perfume;
import com.giftshop.dto.perfume.PerfumeRequest;
import com.giftshop.dto.perfume.PerfumeResponse;
import com.giftshop.util.TestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PerfumeMapperTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void convertToEntity() {
        PerfumeRequest perfumeRequest = new PerfumeRequest();
        perfumeRequest.setPerfumer(TestConstants.PERFUMER_CHANEL);
        perfumeRequest.setPerfumeTitle(TestConstants.PERFUME_TITLE);
        perfumeRequest.setYear(TestConstants.YEAR);
        perfumeRequest.setCountry(TestConstants.COUNTRY);
        perfumeRequest.setPerfumeGender(TestConstants.PERFUME_GENDER);
        perfumeRequest.setFragranceTopNotes(TestConstants.FRAGRANCE_TOP_NOTES);
        perfumeRequest.setFragranceMiddleNotes(TestConstants.FRAGRANCE_MIDDLE_NOTES);
        perfumeRequest.setFragranceBaseNotes(TestConstants.FRAGRANCE_BASE_NOTES);
        perfumeRequest.setPrice(TestConstants.PRICE);
        perfumeRequest.setVolume(TestConstants.VOLUME);
        perfumeRequest.setType(TestConstants.TYPE);

        Perfume perfume = modelMapper.map(perfumeRequest, Perfume.class);
        assertEquals(perfumeRequest.getPerfumer(), perfume.getPerfumer());
        assertEquals(perfumeRequest.getPerfumeTitle(), perfume.getPerfumeTitle());
        assertEquals(perfumeRequest.getYear(), perfume.getYear());
        assertEquals(perfumeRequest.getCountry(), perfume.getCountry());
        assertEquals(perfumeRequest.getPerfumeGender(), perfume.getPerfumeGender());
        assertEquals(perfumeRequest.getFragranceTopNotes(), perfume.getFragranceTopNotes());
        assertEquals(perfumeRequest.getFragranceMiddleNotes(), perfume.getFragranceMiddleNotes());
        assertEquals(perfumeRequest.getFragranceBaseNotes(), perfume.getFragranceBaseNotes());
        assertEquals(perfumeRequest.getPrice(), perfume.getPrice());
        assertEquals(perfumeRequest.getVolume(), perfume.getVolume());
        assertEquals(perfumeRequest.getType(), perfume.getType());
    }

    @Test
    public void convertToResponseDto() {
        Perfume perfume = new Perfume();
        perfume.setId(1L);
        perfume.setPerfumer(TestConstants.PERFUMER_CHANEL);
        perfume.setPerfumeTitle(TestConstants.PERFUME_TITLE);
        perfume.setYear(TestConstants.YEAR);
        perfume.setCountry(TestConstants.COUNTRY);
        perfume.setPerfumeGender(TestConstants.PERFUME_GENDER);
        perfume.setFragranceTopNotes(TestConstants.FRAGRANCE_TOP_NOTES);
        perfume.setFragranceMiddleNotes(TestConstants.FRAGRANCE_MIDDLE_NOTES);
        perfume.setFragranceBaseNotes(TestConstants.FRAGRANCE_BASE_NOTES);
        perfume.setPrice(TestConstants.PRICE);
        perfume.setVolume(TestConstants.VOLUME);
        perfume.setType(TestConstants.TYPE);

        PerfumeResponse perfumeResponse = modelMapper.map(perfume, PerfumeResponse.class);
        assertEquals(perfume.getId(), perfumeResponse.getId());
        assertEquals(perfume.getPerfumer(), perfumeResponse.getPerfumer());
        assertEquals(perfume.getPerfumeTitle(), perfumeResponse.getPerfumeTitle());
        assertEquals(perfume.getYear(), perfumeResponse.getYear());
        assertEquals(perfume.getCountry(), perfumeResponse.getCountry());
        assertEquals(perfume.getPerfumeGender(), perfumeResponse.getPerfumeGender());
        assertEquals(perfume.getFragranceTopNotes(), perfumeResponse.getFragranceTopNotes());
        assertEquals(perfume.getFragranceMiddleNotes(), perfumeResponse.getFragranceMiddleNotes());
        assertEquals(perfume.getFragranceBaseNotes(), perfumeResponse.getFragranceBaseNotes());
        assertEquals(perfume.getPrice(), perfumeResponse.getPrice());
        assertEquals(perfume.getVolume(), perfumeResponse.getVolume());
        assertEquals(perfume.getType(), perfumeResponse.getType());
    }
}
