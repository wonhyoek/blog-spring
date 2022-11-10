package com.cos.blog.service;

import com.cos.blog.dto.BoardListWrapperDTO;
import com.cos.blog.dto.saveReplyDto.saveReplyReqDto;
import com.cos.blog.dto.saveBoardDTO.SaveBoardReqDTO;
import com.cos.blog.dto.BoardResDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public BoardResDTO 글쓰기(SaveBoardReqDTO reqDTO, User user) {
        Board board = Board.builder()
                .title(reqDTO.getTitle())
                .content(reqDTO.getContent())
                .build();
        board.setCount(0);
        board.setUser(user);

        Board boardPS = boardRepository.save(board);

        return boardPS.toResDTO();
    }

    @Transactional(readOnly = true)
    public BoardListWrapperDTO 글목록(Pageable pageable){

        Page<Board> boardsPS = boardRepository.findAll(pageable);
        BoardListWrapperDTO boardListWrapperDTO = new BoardListWrapperDTO();
        boardListWrapperDTO.setItems(boardsPS);

        return boardListWrapperDTO;
    }

    @Transactional(readOnly = true)
    public BoardResDTO 글상세보기(int id) {
        Board boardPS = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });

        return boardPS.toResDTO();
    }

    @Transactional
    public int 글삭제하기(int id) {
        boardRepository.deleteById(id);
        return id;
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    @Transactional
    public void 댓글쓰기(saveReplyReqDto replySaveRequestDto) {
        int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
        System.out.println("BoardService : "+result);
    }

    @Transactional
    public void 댓글삭제(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
