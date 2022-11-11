package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.BoardResDTO;
import com.cos.blog.dto.SaveReplyReqDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.dto.SaveBoardReqDTO;
import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<BoardResDTO> save(@RequestBody SaveBoardReqDTO reqDTO, @AuthenticationPrincipal PrincipalDetail principal) {
        BoardResDTO resDTO = boardService.글쓰기(reqDTO, principal.getUser());
        return new ResponseDto<BoardResDTO>(HttpStatus.OK.value(), resDTO);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        int deletedId = boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), deletedId);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<BoardResDTO> update(@PathVariable int id, @Valid @RequestBody SaveBoardReqDTO reqDTO){
        BoardResDTO resDTO = boardService.글수정하기(id, reqDTO);
        return new ResponseDto<BoardResDTO>(HttpStatus.OK.value(), resDTO);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody SaveReplyReqDto reqDto) {
        int rowCount = boardService.댓글쓰기(reqDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), rowCount);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
