package action.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import action.ActionForward;
import model.Board;
import model.BoardDao;
import model.Comment;
import model.CommentDao;
import model.Icon;
import model.IconDao;
import model.Member;
import model.MemberDao;
import model.Question;
import model.QuestionDao;
import model.Warning;
import model.WarningDao;

public class BoardAllAction {
	private BoardDao dao = new BoardDao();
	private MemberDao mdao = new MemberDao();
	private CommentDao cdao = new CommentDao();
	private QuestionDao qdao = new QuestionDao();
	private WarningDao wdao = new WarningDao();
	private IconDao idao = new IconDao();
	protected String login;  
	protected String id;
	
	//로그인
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		String msg = "해당 id가 존재하지 않습니다.";
		String url = "loginForm.do";
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		Member mem = new MemberDao().selectOne(id);
		if(mem != null) {
			if(pass.equals(mem.getPass())) {
				request.getSession().setAttribute("login",id);
				msg = mem.getName() +"님이 로그인 하셨습니다.";
				url = "../board/list3.do?type=3";
			} else {
				msg = "비밀번호가 틀립니다.";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	//회원가입
	public ActionForward join(HttpServletRequest request, HttpServletResponse response) {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setTel(request.getParameter("tel"));
		mem.setEmail(request.getParameter("email"));
		String msg = "회원가입 실패";
		String url = "joinForm.do";
		if(mdao.insert(mem) > 0) {
			msg = "회원가입 성공";
			url = "loginForm.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	//메인
	public ActionForward main(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
		id = request.getParameter("id");
		if(login == null || login.trim().equals("")) {
			request.setAttribute("msg", "로그인 후 거래하세요");
			request.setAttribute("url", "loginForm.do");
			return new ActionForward(false,"../alert.jsp");
		}
		return new ActionForward();
	}

	public ActionForward bmain(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
		id = request.getParameter("id");
		if(login == null || login.trim().equals("")) {
			request.setAttribute("msg", "로그인 후 거래하세요");
			request.setAttribute("url", "loginForm.do");
			return new ActionForward(false,"../alert.jsp");
		}
		return new ActionForward();
	}
	//로그아웃
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		String msg = "로그아웃되었습니다.";
		String url = "loginForm.do";
		request.getSession().invalidate();
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	//비밀번호 찾기
	public ActionForward pw(HttpServletRequest request, HttpServletResponse response) {
	MemberDao dao = new MemberDao();
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String pw = dao.pwSearch(id,name);
	if(pw != null) {  //비밀번호 찾기 성공
		request.setAttribute("pass", pw.substring(2,pw.length()));
		return new ActionForward();
	} else {   //비밀번호 찾기 실패
	  request.setAttribute("msg", "정보에 맞는 비밀번호를 찾을 수 없습니다.");
	  request.setAttribute("url", "pwForm.do");
	  return new ActionForward(false,"../alert.jsp");
		}
	}
	//아이디 찾기
	public ActionForward id(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = dao.idSearch(name,email);
		String msg = null;
		String url = null;
		if(id != null) { //id 찾기 성공
			id = id.substring(0,id.length()-2);
			request.setAttribute("id", id);
			return new ActionForward();
		}else { //id 찾기 실패
			msg = "해당 정보에 맞는 아이디가 없습니다";
			url = "idForm.do";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		 return new ActionForward(false,"../alert.jsp");
		}
	}
	
	//회원정보보기
	public ActionForward meminfo(HttpServletRequest request, HttpServletResponse response) {
		id = request.getParameter("id");
		Member info = new MemberDao().selectOne(id);
		request.setAttribute("info", info);
		return new ActionForward();
	}
	
	//회원정보수정
	public ActionForward memupdate(HttpServletRequest request, HttpServletResponse response) {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setTel(request.getParameter("tel"));
		mem.setEmail(request.getParameter("email"));
		MemberDao dao = new MemberDao();
		Member dbmem = dao.selectOne(mem.getId());
		String msg = "비밀번호가 틀렸습니다.";
		String url = "updateForm.do?id="+mem.getId();
		if(login.equals("admin") || mem.getPass().equals(dbmem.getPass())) {
			if(dao.update(mem) > 0) {
				  msg = "수정완료";
				  url = "meminfo.do?id="+mem.getId();
			  } else {
				  msg = "수정실패";
				  url = "main.do";
			  }
		}
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	//회원 비밀번호 수정하기
	public ActionForward password(HttpServletRequest request, HttpServletResponse response) {
		 String login = (String)request.getSession().getAttribute("login");
			boolean  opener = false;
			boolean  closer = false;
			String  msg="비밀번호 오류 입니다. 확인하세요.";
			String  url = "passwordForm.do";
			if(login == null || login.trim().equals("")) {  //로그아웃 상태
				opener = true;
			    closer = true;
				msg = "로그인 하세요.";
				url = "loginForm.do";
			} else { //로그인 상태
			  String pass= request.getParameter("pass");
			  String chgpass= request.getParameter("chgpass");
			  MemberDao dao = new MemberDao();
			  Member mem = dao.selectOne(login);
			  if(pass.equals(mem.getPass())) {//입력된 비밀번호와 db에 저장된 비밀번호가 같은 경우
				  closer = true;
				  if(dao.updatePass(login,chgpass) > 0) {
					  opener = true;
					  msg="비밀번호가 변경되었습니다.";
					  url = "meminfo.do?id=" + login;
				  } else {
					  msg="비밀번호 변경시 오류가 발생 했습니다.";
				  }
			  }
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			request.setAttribute("closer", closer);
			request.setAttribute("opener", opener);
			return new ActionForward();
		}
	
	//회원목록보기 (관리자만)
	public ActionForward memlist(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
	    if(login==null||login.trim().equals("")){
	    	request.setAttribute("msg", "로그인이 필요합니다.");
	    	request.setAttribute("url", "loginForm.do");
	    	return new ActionForward(false,"../alert.jsp");
	    } else if(!login.equals("admin")){
	    	request.setAttribute("msg", "관리자만 가능한 거래 입니다.");
	    	request.setAttribute("url", "../board/list3.do?type=3");
	    	return new ActionForward(false,"../alert.jsp");
	    }
	    MemberDao dao = new MemberDao();
		request.setAttribute("list", dao.list());
		return new ActionForward();
	}
	
	//회원탈퇴하기
	public ActionForward memdelete(HttpServletRequest request, HttpServletResponse response) {
		id=request.getParameter("id");
		if(id.equals("admin")) {
			request.setAttribute("msg","관리자는 탈퇴가 되지 않습니다.");
			request.setAttribute("url", "list.do");
			return new ActionForward(false,"../alert.jsp");
		}
		String pass = request.getParameter("pass");
	    MemberDao dao = new MemberDao();
		Member mem = dao.selectOne(id); //db 정보 조회
		String msg = id + "님의 비밀번호가 틀립니다.";
        String url = "deleteForm.do?id=" + id;
        //탈퇴 대상 조건
		if(login.equals("admin") || pass.equals(mem.getPass())) {
		   if(dao.delete(id) > 0) { 
			 if(login.equals("admin")) {  //관리자인 경우
				msg =id + " 사용자를 강제 탈퇴 성공";
			    url = "list.do";
			 } else {  //일반사용자인 경우
				msg =id + "님의  회원 탈퇴가 완료되었습니다.";
			    url = "loginForm.do";
			    request.getSession().invalidate();
			 }
		   } else {  //삭제 실패
			 msg = id +"님의 탈퇴시 오류 발생.";
			 if(login.equals("admin")) {  //관리자인 경우
				url = "list.do";
			 } else {  //일반사용자인 경우
				url = "meminfo.do?id="+id;
			 }
		   }
	   }
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		return new ActionForward(false,"../alert.jsp");
    }
	/**
	 * 1. multipart/form-data 타입의 전송이므로 
	 *    MultipartRequest 객체 생성 
	 * 2. 파라미터 값을* model.Board 객체 저장. 
	 * 3. 게시물 번호 num 현재 등록된 num의 최대값을 조회. 
	 *    최대값 +1 등록된 게시물의 번호.
	 *    db에서 maxnum 을 구해서 +1 값으로 num 설정하기 
	 * 4. board 내용을 db에 등록하기. 
	 *    등록성공 : 메세지 출력.list.do 페이지 이동 
	 *    등록 실패 : 메세지 출력. writeForm.me 페이지 이동
	 */
	//일기장게시판쓰기
	public ActionForward write(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		type = 1;
		Member write = new MemberDao().selectOne(id);
		request.setAttribute("write", write);
		String msg = "게시물 등록 실패";
		String url = "writeForm.do?id="+id+"&type="+1;
		String path = request.getServletContext().getRealPath("/") + "model2/board/file/";
		try {
			File f = new File(path);
			if (!f.exists())
				f.mkdirs();
			MultipartRequest multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "euc-kr");
			Board b = new Board();
			b.setId(multi.getParameter("id"));
			b.setType(Integer.parseInt(multi.getParameter("type")));
			b.setCheckpass(multi.getParameter("checkpass"));
			b.setSubject(multi.getParameter("subject"));
			b.setContent(multi.getParameter("content"));
			b.setFile1(multi.getFilesystemName("file1"));
			int num = dao.maxnum();
			b.setNum(++num);
			b.setGrp(num);
			if (dao.insert(b)) {
				msg = "게시물 등록 성공";
				url = "list.do?id="+id+"&type="+1;
			}
		} catch (IOException e) {
			throw new ServletException(e);
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}

	/**
	 * 1. 한페이지당 10건의 게시물을 출력하기. 
	 *    pageNum 파라미터값을 저장 => 없는 경우는 1로 설정하기. 
	 *  2. 최근 등록된 게시물이 가장 위에 배치함. 
	 *  3. 화면에 필요한 정보를 속성으로 등록.
	 */
	//일기장게시판목록보기
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("-------------------" + id);
		int limit = 10;
		int pageNum = 1;
		type = 1;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = dao.boardCount(column,find, id, type);
		List<Board> list = dao.list(pageNum, limit,column,find, id,type);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardcnt", boardcnt);
		String today = 
		   new SimpleDateFormat("yyyyMMdd").format(new Date());
		request.setAttribute("today", today);
		return new ActionForward();
	}
	
	//일기장 상세보기
	public ActionForward info(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		Board b = dao.selectOne(num, id, type);
		// request.getRequestURI() :
		// /jspstudy2/model2/board/info.do
		if (request.getRequestURI().contains("/board/info.do")) {
			dao.readcntadd(num);
		}
		request.setAttribute("b", b);
		return new ActionForward();
	}
	
	/*
	 * CKEDITOR에서 이미지를 게시판내용에 추가하기
	 */
	public ActionForward imgupload
	   (HttpServletRequest request, HttpServletResponse response)
			   throws IOException {
		String path = request.getServletContext().getRealPath("/")
				+ "model2/board/imgfile/";
		File f = new File(path);
		if (!f.exists())	f.mkdirs();
		MultipartRequest multi = new MultipartRequest
				   (request, path, 10 * 1024 * 1024, "euc-kr");
		String fileName = multi.getFilesystemName("upload");
		request.setAttribute("fileName", fileName);
		request.setAttribute("CKEditorFuncNum",
				request.getParameter("CKEditorFuncNum"));
		return new ActionForward(false, "ckeditor.jsp");
	}	
	/**
	 * 1. 파라미터정보들을 Board 객체 저장. 
	 * 2. 비밀번호 검증
	 *    비밀번호 일치 하는 경우 
	 *       수정 파라미터의 내용으로 해당 게시물의 내용을수정하기. 
	 *       첨부파일의 변경이 없는 경우 file2 파라미터의 내용을 다시 저장하기 
	 *    비밀번호 불일치 
	 *       비밀번호 오류 메세지 출력하고,updateForm.do로 페이지 이동 
	 * 3. 수정성공 :
	 *     수정성공 메시지 출력 후 info.do 페이지 이동 
	 *    수정실패 :
	 *     수정실패 메시지 출력 후 updateForm.do 페이지 이동
	 */
	//일기장 수정
	public ActionForward update(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		String path = request.getServletContext().getRealPath("/")
				+ "model2/board/file/";
		MultipartRequest multi = new MultipartRequest
				(request, path, 10 * 1024 * 1024, "euc-kr");
		Board board = new Board();
		board.setId(multi.getParameter("id"));
		board.setNum(Integer.parseInt(multi.getParameter("num")));
		board.setType(Integer.parseInt(multi.getParameter("type")));
		board.setCheckpass(multi.getParameter("checkpass"));
		board.setSubject(multi.getParameter("subject"));
		board.setContent(multi.getParameter("content"));
		board.setFile1(multi.getFilesystemName("file1"));
		//첨부파일 수정이 안된 경우
		if (board.getFile1() == null || 
				                board.getFile1().equals("")) {
			board.setFile1(multi.getParameter("file2"));
		}
		Board dbBoard = dao.selectOne(board.getNum(),board.getId(),board.getType());
		String msg = "비밀번호가 틀렸습니다.";
		String url = "updateForm.do?num=" + board.getNum()+"&id="+board.getId()+"&type="+1;
		if (board.getCheckpass().equals(dbBoard.getCheckpass())) {
			if (dao.update(board)) {
				msg = "게시물 수정 완료";
				url = "info.do?num=" + board.getNum()+"&id="+board.getId()+"&type="+1;
			} else {
				msg = "게시물 수정 실패";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
/**
   1. num,pass 파라미터를 변수에 저장.
   2. 입력된 비밀번호와 db 비밀번호 검증
            틀린경우 : 비밀번호 오류 메시지 출력, deleteForm.do 페이지 이동
   3. 비밀번호가 맞는 경우 : 게시물 삭제.
           삭제 성공 : 삭제 성공 메시지 출력, list.do 페이지 이동
           삭제 실패 : 삭제 실패 메시지 출력, info.do 페이지 이동
 */
	//일기장 삭제
	public ActionForward delete(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int type = Integer.parseInt(request.getParameter("type"));
		String id = request.getParameter("id"); 
		String pass = request.getParameter("pass"); 
		String msg = "게시글의 비밀번호가 틀렸습니다";
		String url = "deleteForm.do?num=" + num + "&id=" + id + "&type=" + 1;
		Board board = dao.selectOne(num, id, type);
		if(board == null) {
			msg = "없는 게시글입니다.";
			url = "list.do?num=" + num + "&id=" + id + "&type=" + 1;
		} else {
		  if (pass.equals(board.getCheckpass())) {
			if (dao.delete(num)) {
				msg = "게시글 삭제 성공";
				url = "list.do?num=" + num + "&id=" + id + "&type=" + 1;
			} else {
				msg = "게시글 삭제 실패";
				url = "info.do?num=" + num + "&id=" + id + "&type=" + 1;
			}
		  }
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//Q&A 일기장 목록보기
	public ActionForward list2(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("-------------------" + id);
		int limit = 10;
		int pageNum = 1;
		type=2;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = dao.boardCount(column,find, id, type);
		List<Board> list = dao.list(pageNum, limit,column,find, id, type);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		String subject = dao.selectOne3();
		System.out.println(subject);
		System.out.println(subject);
		System.out.println(subject);
		System.out.println(subject);
		System.out.println(subject);
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("subject", subject);
		String today = 
		   new SimpleDateFormat("yyyyMMdd").format(new Date());
		request.setAttribute("today", today);
		return new ActionForward();
	}
	//Q&A 일기장 글쓰기
	public ActionForward write2(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		type = 2;
		Member write = new MemberDao().selectOne(id);
		request.setAttribute("write2", write);
		String msg = "게시물 등록 실패";
		String url = "writeForm2.do?id="+id+"&type="+2;
		Board b = new Board();
		b.setId(request.getParameter("id"));
		b.setType(Integer.parseInt(request.getParameter("type")));
		b.setCheckpass(request.getParameter("checkpass"));
		b.setSubject(request.getParameter("subject"));
		b.setContent(request.getParameter("content"));
		int num = dao.maxnum();
		b.setNum(++num);
		b.setGrp(num);
		if (dao.insert(b)) {
			msg = "게시물 등록 성공";
			url = "list2.do?id="+id+"&type="+2;
		} 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//Q&A 일기장 상세보기
	public ActionForward info2(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		Board b = dao.selectOne(num, id, type);
		String subject = dao.selectOne3();
		System.out.println(subject);
		System.out.println(subject);
		System.out.println(subject);
		// request.getRequestURI() :
		// /jspstudy2/model2/board/info.do
		if (request.getRequestURI().contains("/board/info2.do")) {
			dao.readcntadd(num);
		}
		request.setAttribute("b", b);
		request.setAttribute("subject", subject);
		return new ActionForward();
	}
	//Q&A 일기장 수정
	public ActionForward update2(HttpServletRequest request, 
	HttpServletResponse response) throws IOException {
		Board board = new Board();
		board.setId(request.getParameter("id"));
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setType(Integer.parseInt(request.getParameter("type")));
		board.setCheckpass(request.getParameter("checkpass"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		Board dbBoard = dao.selectOne(board.getNum(),board.getId(),board.getType());
		String msg = "비밀번호가 틀렸습니다.";
		String url = "updateForm2.do?num=" + board.getNum()+"&id="+board.getId()+"&type="+2;
		if (board.getCheckpass().equals(dbBoard.getCheckpass())) {
			if (dao.update(board)) {
				msg = "게시물 수정 완료";
				url = "info2.do?num=" + board.getNum()+"&id="+board.getId()+"&type="+2;
			} else {
				msg = "게시물 수정 실패";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//Q&A 일기장 삭제
	public ActionForward delete2(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int type = Integer.parseInt(request.getParameter("type"));
		String id = request.getParameter("id"); 
		String pass = request.getParameter("pass"); 
		String msg = "게시글의 비밀번호가 틀렸습니다";
		String url = "deleteForm2.do?num=" + num + "&id=" + id + "&type=" + 2;
		Board board = dao.selectOne(num, id, type);
		if(board == null) {
			msg = "없는 게시글입니다.";
			url = "list2.do?num=" + num + "&id=" + id + "&type=" + 2;
		} else {
		  if (pass.equals(board.getCheckpass())) {
			if (dao.delete(num)) {
				msg = "게시글 삭제 성공";
				url = "list2.do?num=" + num + "&id=" + id + "&type=" + 2;
			} else {
				msg = "게시글 삭제 실패";
				url = "info2.do?num=" + num + "&id=" + id + "&type=" + 2;
			}
		  }
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//고민게시판 목록보기
	public ActionForward list3(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		System.out.println("-------------------" + id);
		int type = Integer.parseInt(request.getParameter("type"));
		int limit = 10;
		int pageNum = 1;
		type = 3;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = dao.boardCount2(column,find, type);
		List<Board> list = dao.list2(pageNum, limit,column,find, type);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		String today = 
		   new SimpleDateFormat("yyyyMMdd").format(new Date());
		request.setAttribute("today", today);
		return new ActionForward();
	}
	//고민게시판 글쓰기
	public ActionForward write3(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		type = 3;
		String msg = "게시물 등록 실패";
		String url = "writeForm3.do?type="+3;
		Board b = new Board();
		b.setId(request.getParameter("id"));
		b.setType(Integer.parseInt(request.getParameter("type")));
		b.setKeyword(Integer.parseInt(request.getParameter("keyword")));
		b.setCheckpass(request.getParameter("checkpass"));
		b.setSubject(request.getParameter("subject"));
		b.setContent(request.getParameter("content"));
		int num = dao.maxnum();
		b.setNum(++num);
		b.setGrp(num);
		if (dao.insert(b)) {
			msg = "게시물 등록 성공";
			url = "list3.do?type="+3;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//고민 게시판 상세보기
	public ActionForward info3(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		Board b = dao.selectOne2(num, type);
		List<Comment> c = cdao.list(num);
		if (request.getRequestURI().contains("/board/info3.do")) {
			dao.readcntadd(num);
		}
		request.setAttribute("b", b);
		request.setAttribute("c", c);
		return new ActionForward();
	}
	//고민 게시판 수정
	public ActionForward update3(HttpServletRequest request, 
	HttpServletResponse response) throws IOException {
		Board board = new Board();
		board.setId(request.getParameter("id"));
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setType(Integer.parseInt(request.getParameter("type")));
		board.setKeyword(Integer.parseInt(request.getParameter("keyword")));
		board.setCheckpass(request.getParameter("checkpass"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		Board dbBoard = dao.selectOne2(board.getNum(),board.getType());
		String msg = "비밀번호가 틀렸습니다.";
		String url = "updateForm3.do?num=" + board.getNum()+"&type="+3;
		if (board.getCheckpass().equals(dbBoard.getCheckpass())) {
			if (dao.update(board)) {
				msg = "게시물 수정 완료";
				url = "info3.do?num=" + board.getNum()+"&type="+3;
			} else {
				msg = "게시물 수정 실패";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//고민 게시판 삭제
	public ActionForward delete3(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int type = Integer.parseInt(request.getParameter("type"));
		String id = request.getParameter("id"); 
		String pass = request.getParameter("pass"); 
		String msg = "게시글의 비밀번호가 틀렸습니다";
		String url = "deleteForm3.do?num=" + num + "&type=" + 3;
		Board board = dao.selectOne2(num, type);
		if(board == null) {
			msg = "없는 게시글입니다.";
			url = "list3.do?num=" + num + "&type=" + 3;
		} else {
		  if (pass.equals(board.getCheckpass())) {
			if (dao.delete(num)) {
				msg = "게시글 삭제 성공";
				url = "list3.do?num=" + num + "&type=" + 3;
			} else {
				msg = "게시글 삭제 실패";
				url = "info3.do?num=" + num + "&type=" + 3;
			}
		  }
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	/**
	 * 1. 파라미터 값을 Board 객체에 저장하기 
	 *    원글정보 : num, grp, grplevel, grpstep 
	 *    답글정보 : name,pass, subject, content => 등록정보 
	 * 2. 같은 grp 값을 사용하는 게시물들의 grpstep 값을 1 증가 하기.
	 *     void BoardDao.grpStepAdd(grp,grpstep)
	 * 3. Board 객체를 db에 insert 하기. 
	 *    num : maxnum + 1 
	 *    grp : 원글과 동일. 
	 *    grplevel : 원글 + 1 
	 *    grpstop : 원글 + 1
	 */
	//고민 게시판 댓글쓰기
	public ActionForward reply(HttpServletRequest request, HttpServletResponse response) {
		Board b = new Board();
		Comment c = new Comment();
		b.setType(Integer.parseInt(request.getParameter("type")));
		c.setId(request.getParameter("id"));
		c.setNum(Integer.parseInt(request.getParameter("num")));
		c.setComment(request.getParameter("comment"));
		int seq = cdao.maxseq();
		String url = "info3.do?type="+3+"&num="+c.getNum();
		c.setSeq(++seq);
		if (cdao.insert(c)) {
			url = "info3.do?type="+3+"&num="+c.getNum();
		}
		request.setAttribute("url", url);
		return new ActionForward(false, "../url.jsp");
	}
	
	//고민게시판 댓글 목록
//	public ActionForward list4(HttpServletRequest request, HttpServletResponse response) {
//		int seq = Integer.parseInt(request.getParameter("seq"));
//		int num = Integer.parseInt(request.getParameter("num"));
//		String column = request.getParameter("column");
//		String find = request.getParameter("find");
//		if(column != null && column.trim().equals("")) 
//			 column = null;
//		if(find != null && find.trim().equals("")) 
//			find = null;
//		if(column == null || find == null) {
//			column = null;
//			find = null;
//		}
//		int boardcnt = cdao.boardCount(column,find, seq ,num);
//		List<Comment> list = cdao.list(column,find ,seq ,num);
//		request.setAttribute("boardcnt", boardcnt);
//		request.setAttribute("list", list);
//		return new ActionForward();
//	}
	
	//Q&A 질문 관리 목록
	public ActionForward list5(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
	    if(login==null||login.trim().equals("")){
	    	request.setAttribute("msg", "로그인이 필요합니다.");
	    	request.setAttribute("url", "loginForm.do");
	    	return new ActionForward(false,"../alert.jsp");
	    } else if(!login.equals("admin")){
	    	request.setAttribute("msg", "관리자만 가능한 거래 입니다.");
	    	request.setAttribute("url", "../board/list3.do?type=3");
	    	return new ActionForward(false,"../alert.jsp");
	    }
		int limit = 10;
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = qdao.boardCount(column,find);
		List<Question> list = qdao.list(pageNum, limit,column,find);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		return new ActionForward();
	}
	//Q&A 질문 쓰기
	public ActionForward write5(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String msg = "게시물 등록 실패";
		String url = "writeForm5.do";
		Question q = new Question();
		q.setQuestion(request.getParameter("question"));
		q.setPw(request.getParameter("pw"));
		q.setToday(request.getParameter("today"));
		int num = qdao.maxnum();
		q.setNum(++num);
		if (qdao.insert(q)) {
			msg = "게시물 등록 성공";
			url = "list5.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//Q&A 질문 상세 보기
	public ActionForward info5(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		Question q = new QuestionDao().selectOne(num);
		request.setAttribute("q", q);
		return new ActionForward();
	}
	//Q&A 질문 수정
	public ActionForward update5(HttpServletRequest request, 
	HttpServletResponse response) throws IOException {
		Question q = new Question();
		q.setNum(Integer.parseInt(request.getParameter("num")));
		q.setPw(request.getParameter("pw"));
		q.setQuestion(request.getParameter("question"));
		q.setToday(request.getParameter("today"));
		Question qboard = qdao.selectOne(q.getNum());
		String msg = "비밀번호가 틀렸습니다.";
		String url = "updateForm5.do?num=" + q.getNum();
		if (q.getPw().equals(qboard.getPw())) {
			if (qdao.update(q)) {
				msg = "게시물 수정 완료";
				url = "info5.do?num=" + q.getNum();
			} else {
				msg = "게시물 수정 실패";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//신고하기
	public ActionForward warn(HttpServletRequest request, HttpServletResponse response) {
		Warning w = new Warning();
		w.setNum(Integer.parseInt(request.getParameter("num")));
		w.setSeq(Integer.parseInt(request.getParameter("seq")));
		w.setId(request.getParameter("id"));
		w.setWarnid(request.getParameter("warnid"));
		w.setReason(request.getParameter("reason"));
		String msg = "신고 등록 실패";
		String url = "warnwrite.do?seq="+w.getSeq()+"&num="+w.getNum()+"&warnid="+w.getWarnid();
		if (wdao.insert(w)) {
			msg = "신고 등록 성공";
			url = "warnForm.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	public ActionForward warnForm(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
	    if(login==null||login.trim().equals("")){
	    	request.setAttribute("msg", "로그인이 필요합니다.");
	    	request.setAttribute("url", "loginForm.do");
	    	return new ActionForward(false,"../alert.jsp");
	    } else if(!login.equals("admin")){
	    	request.setAttribute("msg", "관리자만 가능한 거래 입니다.");
	    	request.setAttribute("url", "../board/list3.do?type=3");
	    	return new ActionForward(false,"../alert.jsp");
	    }
		int limit = 10;
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = wdao.boardCount(column,find);
		List<Warning> list = wdao.list(pageNum, limit,column,find);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		return new ActionForward();
	}
	//신고 완료 목록 삭제
	public ActionForward warndelete(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String msg = "신고 완료 목록 삭제 실패";
		String url = "warnForm.do";
		Warning w = wdao.selectOne(seq);
		if(w == null) {
			msg = "없는 게시글입니다.";
			url = "warnForm.do";
		} else {
			if (wdao.delete(seq)) {
				msg = "신고 완료 목록 삭제 성공";
				url = "warnForm.do";
			} 
		  }
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward infocalForm(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		return new ActionForward();
	}
	//출석체크 감정 쓰기
	public ActionForward infocal(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		boolean opener = false;
		boolean closer = true;
		login = (String)request.getSession().getAttribute("login");
		String msg = "다시 입력해주세요";
		String url = "infocalForm.do";
		Icon i = new Icon();
		i.setId(login);
		i.setIcon(Integer.parseInt(request.getParameter("icon")));
		if (idao.insert(i)) {
			msg = "게시물 등록 성공";
			url = "cal.do";
			opener = true;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("opener", opener);
		request.setAttribute("closer", closer);
		return new ActionForward(false, "infoCal.jsp");
	}
}
