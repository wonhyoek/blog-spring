package com.cos.blog.service;

import com.cos.blog.dto.BoardListWrapperDTO;
import com.cos.blog.dto.SaveReplyReqDto;
import com.cos.blog.dto.SaveBoardReqDTO;
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


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public BoardResDTO createBoard(SaveBoardReqDTO reqDTO, User user) {
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
    public BoardListWrapperDTO getBoardList(Pageable pageable){

        Page<Board> boardsPS = boardRepository.findAll(pageable);
        BoardListWrapperDTO boardListWrapperDTO = new BoardListWrapperDTO();
        boardListWrapperDTO.setItems(boardsPS);

        return boardListWrapperDTO;
    }

    @Transactional(readOnly = true)
    public BoardResDTO getBoardDetailById(int id) {
        Board boardPS = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });

        return boardPS.toResDTO();
    }

    @Transactional
    public int deleteBoardById(int id) {
        boardRepository.deleteById(id);
        return id;
    }

    @Transactional
    public BoardResDTO modifyBoard(int id, SaveBoardReqDTO reqDTO) {
        Board boardPS = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        boardPS.setTitle(reqDTO.getTitle());
        boardPS.setContent(reqDTO.getContent());

        return boardPS.toResDTO();
    }

    @Transactional
    public int createReply(SaveReplyReqDto replySaveRequestDto) {
        int rowCount = replyRepository.mSave(
                replySaveRequestDto.getUserId(),
                replySaveRequestDto.getBoardId(),
                replySaveRequestDto.getContent()
        );
        return rowCount;
    }

    @Transactional
    public int deleteReply(int replyId) {
        replyRepository.deleteById(replyId);
        return replyId;
    }
}
