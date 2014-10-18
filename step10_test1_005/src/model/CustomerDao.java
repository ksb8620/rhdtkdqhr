package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustomerDao {
    private static DataSource source = null;
    static{	
		try{
		    Context ctx = new InitialContext();			
		    source = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");	
		    System.out.println(source);
		}catch(Exception e) {
		    e.printStackTrace();
		}			
    }
    public static void insert(CustomerVo cvo) throws SQLException{
		Connection con =  null;
		PreparedStatement pstmt = null;		
		try{
		    con = source.getConnection();
		    pstmt = con.prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");			
		    pstmt.setString(1, cvo.getId());
		    pstmt.setString(2, cvo.getPassword());
		    pstmt.setString(3, cvo.getName());
		    pstmt.setString(4, cvo.getEmail());			
		    pstmt.executeUpdate();			
		}finally{
		    close(con, pstmt);
		}	
    }
	
    /**
     * 특정 고객을 Database customr table에서 삭제한다.<br>
     * 호출 하는 곳으로 부터 삭제할 게시물의 id (PK)를 받아 Database에서 삭제한다..
     * 
     * Query : delete
     * 
     * 1. Connection 생성
     * 2. PreparedStatement 생성
     * 3. 쿼리 전송
     * 4. close()
     * @param id
     * @throws SQLException
     */
    public static void delete(String id) throws SQLException{
	Connection conn = null;
	PreparedStatement pstmt = null;
		try{
		    conn = source.getConnection();						
		    pstmt = conn.prepareStatement("DELETE FROM customer WHERE ID=?");
		    pstmt.setString(1, id);
		    pstmt.executeUpdate();
		}catch(SQLException sqle){
		    sqle.printStackTrace();
		    throw sqle;
		}finally{
		    close(conn, pstmt);
		}
    }
	
    /**
     * 특정 고객의 password와 email 정보를 Database customr table에서 갱신한다.<br>
     *  
     * Query : update
     * 
     * 1. Connection 생성
     * 2. PreparedStatement 생성
     * 3. 쿼리 전송
     * 4. close()
     * @param id
     * @throws SQLException
     */
    public static void update(CustomerVo cvo) throws SQLException{
	Connection con =  null;
	PreparedStatement pstmt = null;		
		try{		
		    con = source.getConnection();			
		    pstmt = con.prepareStatement("UPDATE customer SET password = ? , email = ? WHERE id = ?");
		    pstmt.setString(1,cvo.getPassword());
		    pstmt.setString(2,cvo.getEmail());
		    pstmt.setString(3,cvo.getId());		
			pstmt.executeQuery();
		}catch(SQLException s){
		    s.printStackTrace();
		    throw s;		
		}finally{
		    close(con, pstmt);
		}	
    }
    /**
     * 모든 고객의 정보를 Database customr table에서 검색한다.<br>
     *  
     * Query : select
     * 
     * 1. Connection 생성
     * 2. PreparedStatement 생성
     * 3. 쿼리 전송
     * 4. close()
     * @param id
     * @throws SQLException
     */
    public static ArrayList<CustomerVo> getCustomers() throws SQLException{		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CustomerVo> allList = null;
		try{
			conn = source.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM customer");
			rset = pstmt.executeQuery();
			allList =  new ArrayList<CustomerVo>();
			while(rset.next()){
				allList.add(new CustomerVo(rset.getString(1), 
									   rset.getString(2), 
									   rset.getString(3), 
									   rset.getString(4)));
			}
		}finally{
			close(conn, pstmt, rset);
		}
		return allList;
    }
    private static void close(Connection conn, Statement stmt, ResultSet rset) throws SQLException{
    	if(rset!=null){
    		rset.close();	
    		rset = null;
    	}
    	if(stmt!=null){
    		stmt.close();
    		stmt = null;
    	}
    	if(conn!=null){
    		conn.close();
    		conn = null;
    	}
    }
    private static void close(Connection conn, Statement stmt) throws SQLException{
    	if(stmt!=null){
    		stmt.close();	
    		stmt = null;
    	}
		if(conn!=null){
			conn.close();
			conn = null;
		}
    }
}