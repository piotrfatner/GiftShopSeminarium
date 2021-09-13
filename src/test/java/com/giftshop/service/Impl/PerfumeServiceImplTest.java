package com.giftshop.service.Impl;

import com.giftshop.util.TestConstants;
import com.giftshop.domain.Perfume;
import com.giftshop.repository.PerfumeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PerfumeServiceImplTest {

    @Autowired
    private PerfumeServiceImpl perfumeService;

    @MockBean
    private PerfumeRepository perfumeRepository;

    @Test
    public void findPerfumeById() {
        Perfume perfume = new Perfume();
        perfume.setId(123L);

        when(perfumeRepository.findById(123L)).thenReturn(java.util.Optional.of(perfume));
        perfumeService.findPerfumeById(123L);
        assertEquals(123L, perfume.getId());
        assertNotEquals(1L, perfume.getId());
        verify(perfumeRepository, times(1)).findById(123L);
    }

    @Test
    public void findAllPerfumes() {
        List<Perfume> perfumeList = new ArrayList<>();
        perfumeList.add(new Perfume());
        perfumeList.add(new Perfume());

        when(perfumeRepository.findAllByOrderByIdAsc()).thenReturn(perfumeList);
        perfumeService.findAllPerfumes();
        assertEquals(2, perfumeList.size());
        verify(perfumeRepository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    public void filter() {
        Perfume perfumeChanel = new Perfume();
        perfumeChanel.setPerfumer(TestConstants.PERFUMER_CHANEL);
        perfumeChanel.setPerfumeGender(TestConstants.PERFUME_GENDER);
        perfumeChanel.setPrice(101);
        Perfume perfumeCreed = new Perfume();
        perfumeCreed.setPerfumer(TestConstants.PERFUMER_CREED);
        perfumeCreed.setPerfumeGender(TestConstants.PERFUME_GENDER);
        perfumeCreed.setPrice(102);

        List<Perfume> perfumeList = new ArrayList<>();
        perfumeList.add(perfumeChanel);
        perfumeList.add(perfumeCreed);

        List<String> perfumers = new ArrayList<>();
        perfumers.add(TestConstants.PERFUMER_CHANEL);
        perfumers.add(TestConstants.PERFUMER_CREED);

        List<String> genders = new ArrayList<>();
        genders.add(TestConstants.PERFUME_GENDER);

        when(perfumeRepository.findByPerfumerIn(perfumers)).thenReturn(perfumeList);
        perfumeService.filter(perfumers, new ArrayList<>(), new ArrayList<>(), false);
        assertEquals(2, perfumeList.size());
        assertEquals(perfumeList.get(0).getPerfumer(), TestConstants.PERFUMER_CHANEL);
        verify(perfumeRepository, times(1)).findByPerfumerIn(perfumers);

        when(perfumeRepository.findByPerfumeGenderIn(genders)).thenReturn(perfumeList);
        perfumeService.filter(new ArrayList<>(), genders, new ArrayList<>(), false);
        assertEquals(2, perfumeList.size());
        verify(perfumeRepository, times(1)).findByPerfumeGenderIn(genders);
    }

    @Test
    public void findByPerfumerOrderByPriceDesc() {
        Perfume perfumeChanel = new Perfume();
        perfumeChanel.setPerfumer(TestConstants.PERFUMER_CHANEL);
        Perfume perfumeCreed = new Perfume();
        perfumeCreed.setPerfumer(TestConstants.PERFUMER_CREED);
        List<Perfume> perfumeList = new ArrayList<>();
        perfumeList.add(perfumeChanel);
        perfumeList.add(perfumeCreed);

        when(perfumeRepository.findByPerfumerOrderByPriceDesc(TestConstants.PERFUMER_CHANEL)).thenReturn(perfumeList);
        perfumeService.findByPerfumerOrderByPriceDesc(TestConstants.PERFUMER_CHANEL);
        assertEquals(perfumeList.get(0).getPerfumer(), TestConstants.PERFUMER_CHANEL);
        assertNotEquals(perfumeList.get(0).getPerfumer(), TestConstants.PERFUMER_CREED);
        verify(perfumeRepository, times(1)).findByPerfumerOrderByPriceDesc(TestConstants.PERFUMER_CHANEL);
    }

    @Test
    public void findByPerfumeGenderOrderByPriceDesc() {
        Perfume perfumeChanel = new Perfume();
        perfumeChanel.setPerfumeGender(TestConstants.PERFUME_GENDER);
        List<Perfume> perfumeList = new ArrayList<>();
        perfumeList.add(perfumeChanel);

        when(perfumeRepository.findByPerfumeGenderOrderByPriceDesc(TestConstants.PERFUME_GENDER)).thenReturn(perfumeList);
        perfumeService.findByPerfumeGenderOrderByPriceDesc(TestConstants.PERFUME_GENDER);
        assertEquals(perfumeList.get(0).getPerfumeGender(), TestConstants.PERFUME_GENDER);
        assertNotEquals(perfumeList.get(0).getPerfumeGender(), "male");
        verify(perfumeRepository, times(1)).findByPerfumeGenderOrderByPriceDesc(TestConstants.PERFUME_GENDER);
    }

    @Test
    public void savePerfume() {
        MultipartFile multipartFile = new MockMultipartFile(TestConstants.FILE_NAME, TestConstants.FILE_NAME, "multipart/form-data", TestConstants.FILE_PATH.getBytes());
        Perfume perfume = new Perfume();
        perfume.setId(1L);
        perfume.setPerfumer(TestConstants.PERFUMER_CHANEL);
        perfume.setFilename(multipartFile.getOriginalFilename());

        when(perfumeRepository.save(perfume)).thenReturn(perfume);
        perfumeService.savePerfume(perfume, multipartFile);
        verify(perfumeRepository, times(1)).save(perfume);
    }
}
