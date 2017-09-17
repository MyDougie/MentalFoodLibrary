package MFLP.model.service;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.board.dao.BoardDAOImpl;
import MFLP.model.comment.dao.BoardCommentDAOImpl;
import MFLP.model.dto.BoardCommentDTO;

public class CommentService {
   private static BoardDAOImpl boardDao = new BoardDAOImpl();
   private static BoardCommentDAOImpl commentDao = new BoardCommentDAOImpl();
   
   
   public static List<BoardCommentDTO> selectAllBoardComment( int boardNo) throws SQLException {
      List<BoardCommentDTO> list = commentDao.selectAllBoardComment( boardNo);
      //System.out.println(list + " boardservice");
      return list;
   }

   
   public static int insertBoardComment(BoardCommentDTO boardCommentDTO) throws SQLException {
      int result = commentDao.insertBoardComment(boardCommentDTO);
      return result;
   }

   
   public static int deleteBoardComment(String id, int commentNo) throws SQLException {
      int result = commentDao.deleteBoardComment(id, commentNo);
      return result;
   }

   public static int updateBoardComment(BoardCommentDTO boardCommentDTO) throws SQLException {
      int result = commentDao.updateBoardComment(boardCommentDTO);
      return result;
   }
   
   public static  BoardCommentDTO selectByNoComment(int commentNo) throws SQLException {
       BoardCommentDTO dto = commentDao.selectByNoComment(commentNo);
      return dto;
   }
}