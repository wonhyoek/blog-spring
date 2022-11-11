package com.cos.blog.controller;


import com.cos.blog.service.BoardService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {
   @InjectMocks
    private BoardController boardController;

   @Mock
    private BoardService boardService;

   

}
