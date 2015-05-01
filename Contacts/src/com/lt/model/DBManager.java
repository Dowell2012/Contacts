package com.lt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lt.res.AddJTFRes;

/**
 * @author Administrator
 *
 */
public class DBManager {
	private Connection conn = null;
	private Statement stmt = null;
	/**
	 * @return void
	 * Description �����ݿ⽨������
	 */
	public void getConn()
	{
		try {
			//�������ݿ�����
			Class.forName(DBConst.CLASSNAME).newInstance(); 
			//��ʼ�������ݿ�
			conn = DriverManager.getConnection(DBConst.URL, DBConst.NAME, DBConst.PASSWORD);
			//�õ�SQLִ�ж���
			stmt = conn.createStatement();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		 
	}
	
	/**
	 * @return ArrayList<Person>
	 * Description ���������ϵ����Ϣ
	 */
	public ArrayList<Person> getAllResult()
	{
		ArrayList<Person> list = new ArrayList<Person>();
		
		getConn();
		try {
			ResultSet rs = stmt.executeQuery("select * from person");
			while(rs.next())
			{
				Person person = new Person();
				person.setCellphone(rs.getString(1));
				person.setName(rs.getString(2));
				person.setSex(rs.getString(3));
				person.setQq(rs.getString(4));
				person.setEmail(rs.getString(5));
				person.setGroupnum(rs.getInt(6));
				list.add(person);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		closeConn();
		return list;
	}
	/**
	 * @returnArrayList<Group>
	 * Description ������еķ���
	 */
	public ArrayList<Group> getAllGroup()
	{
		ArrayList<Group> list = new ArrayList<Group>();
		getConn();
		try {
			ResultSet rs = stmt.executeQuery("select * from fenlei");
			while(rs.next())
			{
				Group group = new Group();
				group.setId(rs.getInt(1));
				group.setGroupname(rs.getString(2));
				list.add(group);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		closeConn();
		return list;
	}
	
	/**
	 * @param str
	 * @return ArrayList<Person>
	 * Description ÿ������������ϵ��
	 */
	public ArrayList<Person> getInnerGroup(String str)
	{
		ArrayList<Person> list = new ArrayList<>();
		getConn();
		try {
			 
			ResultSet rs = stmt.executeQuery("select * from person where groupnum in (select id from fenlei where groupname = '"+str+"')" );
			while(rs.next())
			{
				Person person = new Person();
				person.setCellphone(rs.getString(1));
				person.setName(rs.getString(2));
				person.setSex(rs.getString(3));
				person.setQq(rs.getString(4));
				person.setEmail(rs.getString(5));
				person.setGroupnum(rs.getInt(6));
				list.add(person);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		closeConn();
		return list;
	}
	
	/**
	 * @return length
	 * Description �õ���ϵ������
	 */
	public int getPersonCount()
	{
		getConn();
		ResultSet rs;
		int length = 0;
		try {
			rs = stmt.executeQuery("select * from person");
			rs.last();
			length = rs.getRow();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConn();
		return length;
	}
	/**
	 * @return length
	 * Description �õ��������
	 */
	public int getGroupCount()
	{
		getConn();
		ResultSet rs;
		int length = 0;
		try {
			rs = stmt.executeQuery("select * from fenlei");
			rs.last();
			length = rs.getRow();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConn();
		return length;
	}
	
	/**
	 * @param str
	 * @return length
	 * Description �õ�ÿ��������ϵ�˵�����
	 */
	public int getSingleGroupCount(String str)
	{
		getConn();
		ResultSet rs;
		int length = 0;
		try {
			rs = stmt.executeQuery("select * from person where groupnum in (select id from fenlei where groupname = '"+str+"')" );
			rs.last();
			length = rs.getRow();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConn();
		return length;
	}

	/**
	 * @param str
	 * @return ArrayList<Person>
	 * ͨ��������ѯ��ϵ��
	 */
	public ArrayList<Person> getSearchResultByName(String str)
	{
		ArrayList<Person> list = new ArrayList<>();
		getConn();
		try {
			ResultSet rs = stmt.executeQuery("select * from person where name like '%"+str+"%'");
			while(rs.next())
			{
				Person person = new Person();
				person.setCellphone(rs.getString(1));
				person.setName(rs.getString(2));
				list.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
		return list;
	}
	
	/**
	 * @param str
	 * @returnArrayList<Person>
	 * ͨ���ֻ��Ų�ѯ��ϵ��
	 */
	public ArrayList<Person> getSearchResultByCellPhone(String str)
	{
		ArrayList<Person> list = new ArrayList<>();
		getConn();
		try {
			ResultSet rs = stmt.executeQuery("select * from person where cellphone like '"+str+"%'");
			while(rs.next())
			{
				Person person = new Person();
				person.setCellphone(rs.getString(1));
				person.setName(rs.getString(2));
				list.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
		return list;
	}
	/**
	 * @param person
	 * @return boolean
	 * ����µ���ϵ����Ϣ
	 */
	public boolean InsertInfo(Person person)
	{
		getConn();
		String sql = "insert into person values('"+person.getCellphone()+"','"
				+person.getName()+"','"+person.getSex()+"','"+person.getQq()+"','"
				+person.getEmail()+"','"+person.getGroupnum()+"')";
		try {
				stmt.execute(sql);
		} catch (SQLException e) {
			AddJTFRes.newInstance().CELLPHONE.setText("�˺����Ѵ���");
			AddJTFRes.newInstance().CELLPHONE.updateUI();
			return false;
		}
		closeConn();
		return true;
	}
	/**
	 * @param group
	 * @return boolean
	 * ����µķ�������
	 */
	public boolean InsertNewFenLei(Group group)
	{
		getConn();
		String sql = "insert into fenlei values('"+group.getId()+"','"+group.getGroupname()+"')";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			 return false;
		}
		closeConn();
		return true;
	}
	
	/**
	 * @param str
	 * @return Person
	 * �õ�������ϵ�˵���ϸ��Ϣͨ���ֻ��������ʽ
	 */
	public Person getOnePersonInfo(String str)
	{
		getConn();
		ResultSet rs;
		Person person = new Person();
		try {
			rs = stmt.executeQuery("select * from person where cellphone = '"+str+"'");
			while(rs.next())
			{
				person.setCellphone(rs.getString(1));
				person.setName(rs.getString(2));
				person.setSex(rs.getString(3));
				person.setQq(rs.getString(4));
				person.setEmail(rs.getString(5));
				person.setGroupnum(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
		return person;
	}
	/**
	 * @param id
	 * @return String
	 * �����ID�õ��������
	 */
	
	public void deletePersonByCell(String str)
	{
		getConn();
		try {
			stmt.executeUpdate("delete from person where cellphone = '"+str+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
	}
	/**
	 * @param id
	 * @return String
	 * ͨ��ID�õ�����
	 */
	public String getGroupName(int id)
	{
		getConn();
		String str = null;
		try {
			ResultSet rs = stmt.executeQuery("select groupname from fenlei where id = " + id);
			while(rs.next())
			{
				str = rs.getString("groupname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
		return str;
	}
	/**
	 * @param cellphone
	 * @param person
	 * 
	 * ͨ���ֻ���ԭʼ�����޸���ϵ�˵���ϸ��Ϣ
	 */
	public void modifyPersonByCell(String cellphone, Person person) {
		getConn();
		try {
			stmt.executeUpdate("update person set cellphone = '"+person.getCellphone()+"',name = '"
					+person.getName()+"', sex = '" + person.getSex()
					+"', qq = '"+person.getQq()+"', email = '" + 
					person.getEmail()+"', groupnum = "+person.getGroupnum()
					+" where cellphone = '" +cellphone+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
	}
	public void changeGroup(String string, String actionCommand) {
		
		int id = getGroupIDbyGroupName(actionCommand);
		getConn();
		try {
			stmt.executeUpdate("update person set groupnum = " + id+" where cellphone = '"+string+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
	}
	
	/**
	 * @param str
	 * @return integer
	 * ͨ�������������
	 */
	public int getGroupIDbyGroupName(String str)
	{
		int id = 0;
		getConn();
		try {
			ResultSet rs =stmt.executeQuery("select id from fenlei where groupname = '"+str+"'");
			while(rs.next())
			{
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
		return id;
	}
	public void deleteGroupRelativePerson(String groupname) {
		int id = getGroupIDbyGroupName(groupname);
		getConn();
		try {
			stmt.execute("delete from fenlei where groupname = '" + groupname + "'");
			stmt.execute("delete from person where groupnum = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return void
	 * Description �ر����ݿ�����
	 */
	public void closeConn()
	{
		if(stmt != null)
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("SQLִ�ж���stmt�ر�ʧ��");
				e.printStackTrace();
			}
		}
		if(conn != null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("SQL���Ӷ���conn�ر�ʧ��");
				e.printStackTrace();
			}
		}
	}

	public void renameGroupName(String oldName, String newName) {
		getConn();
		try {
			stmt.executeUpdate("update fenlei set groupname = '"+newName+"' where groupname = '"+oldName+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn();
	}

	

	
}
