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
    public ResponseDto<BoardResDTO> createBoard(@RequestBody SaveBoardReqDTO reqDTO, @AuthenticationPrincipal PrincipalDetail principal) {
        BoardResDTO resDTO = boardService.createBoard(reqDTO, principal.getUser());
        return new ResponseDto<BoardResDTO>(HttpStatus.OK.value(), resDTO);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteBoard(@PathVariable int id){
        int deletedId = boardService.deleteBoardById(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), deletedId);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<BoardResDTO> modifyBoard(@PathVariable int id, @Valid @RequestBody SaveBoardReqDTO reqDTO){
        BoardResDTO resDTO = boardService.modifyBoard(id, reqDTO);
        return new ResponseDto<BoardResDTO>(HttpStatus.OK.value(), resDTO);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> createReply(@RequestBody SaveReplyReqDto reqDto) {
        int rowCount = boardService.modifyBoard(reqDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), rowCount);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> deleteReply(@PathVariable int replyId) {
        int resReplyId = boardService.deleteReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), resReplyId);
    }

}
