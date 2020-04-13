package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface
{

	@Override
	public void insertBlog(Blog blog) throws Exception {
		
		ConnectionManager con = new ConnectionManager();
		System.out.println(blog.getBlogid() + " " + blog.getBlogTitle() + " " + blog.getBlogDescrptn() + " " + blog.getPostedOn());
		String sql = "insert into blogdet values(?,?,?,?)";
		PreparedStatement st = con.getConnection().prepareStatement(sql);
		st.setInt(1, blog.getBlogid());
		st.setString(2, blog.getBlogTitle());
		st.setString(3, blog.getBlogDescrptn());
		st.setDate(4, Date.valueOf(blog.getPostedOn()));
		
		int result = st.executeUpdate();
		System.out.println(result);
		st.close();
		
	}

	@Override
	public Blog selectBlog(int blogid) throws Exception {
		Blog blog = null;
		ConnectionManager cn = new ConnectionManager();
		Connection con =cn.getConnection();
		Statement stmt = con.createStatement();
		String sql = "select * from blogdet where id="+blogid;
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			blog = new Blog(0, sql, sql, null);
			blog.setBlogid(rs.getInt("id"));
			blog.setBlogTitle("title");
			blog.setBlogDescrptn("blog_descrptn");
			java.time.LocalDate localDate = rs.getDate("postedOn").toLocalDate();
			blog.setPostedOn(localDate);
		}
		rs.close();
		stmt.close();
		con.close();
		return blog;
	}

	@Override
	public List<Blog> selectAllBlogs() throws Exception {
		
		List<Blog> list = new ArrayList<Blog>();
		Blog blog = null;
		ConnectionManager cn = new ConnectionManager();
		Connection con =cn.getConnection();
		Statement stmt = con.createStatement();
		String sql = "select * from blogdet";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			java.time.LocalDate localDate = rs.getDate("dt").toLocalDate();
			blog = new Blog(rs.getInt("id"), rs.getString("title"), rs.getString("blog_descrptn"), localDate);
			list.add(blog);
		}
	
		stmt.close();
		con.close();
		return list;
	}

	@Override
	public boolean deleteBlog(int id) throws Exception {
	    
		ConnectionManager cn = new ConnectionManager();
		Connection con =cn.getConnection();
		String sql = "delete from blogdet where id="+id;
		Statement st = con.createStatement();
		
		if(st.execute(sql))
		{
			st.close();
			return true;
		}
		else
		{
			st.close();
			return false;
		}
		
	}

	@Override
	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		
		String sql = "update blogdet set title=?, blog_descrptn=?, dt=? where id=?";
		ConnectionManager con = new ConnectionManager();
		Connection cm = con.getConnection();
		
		PreparedStatement st = cm.prepareStatement(sql);
		
		st.setString(1, blog.getBlogTitle());
		st.setString(2, blog.getBlogDescrptn());
		st.setDate(3, Date.valueOf(blog.getPostedOn()));
		st.setInt(4, blog.getBlogid());
		st.executeUpdate();
		st.close();
		cm.close();
		
		return false;
	}
	
}

