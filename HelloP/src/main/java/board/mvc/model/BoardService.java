package board.mvc.model;

import java.util.ArrayList;

import mvc.domain.Board;

public class BoardService {
	private BoardDAO dao;
	
	//SingleTon Object Model - start!
	private static final BoardService instance = new BoardService();
	private BoardService() {
		dao = new BoardDAO();
	}
	public static BoardService getInstance() {
		return instance;
	}
	public ArrayList<Board> listS(){
		return dao.list();
	}
	public boolean insertS(Board dto) {//��ũ��Ʈ ����� ����,���� ���ҰŸ� �Ҹ��� �ʿ����.
		return dao.insert(dto);
	} 
	public ArrayList<Board> list_conS(int seq){
		return dao.list_con(seq);
	}
	public ArrayList<Board> update_listS(int seq){
		return dao.update_list(seq);
	}
	public void update_updateS(Board dto) {
		dao.update_update(dto);
	}
	public void deleteS(int seq) {
		dao.delete(seq);
	}
}
