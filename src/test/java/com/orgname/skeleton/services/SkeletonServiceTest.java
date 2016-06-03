package com.orgname.skeleton.services;

import com.orgname.skeleton.mapper.SkeletonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkeletonServiceTest {

    @Mock
    SkeletonMapper mapper;

    @InjectMocks
    SkeletonService service;

    @Test
    public void shouldReturnValueFromMapper() {
        String expectedString = "A String";
        when(mapper.getSingleString()).thenReturn(expectedString);

        assertThat(service.getAString(), is(expectedString));
    }
}