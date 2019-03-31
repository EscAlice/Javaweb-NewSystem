package com.ns.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC_util {
	    //���ݿ�����ӵ�ַ
		private static String url =null;
		//���ݿ��˺�
		private static String userName =null;
		//���ݿ�����
		private static String passWord =null;
		//���ݿ�����
		private static String dirver =null;
		static{
			try {

				//����һ��properties��
				Properties prop = new  Properties();
				//����һ��������
				InputStream in;

				in = new  FileInputStream("D:\\��ҵ\\JAVA_SCHOOL\\news_system\\src\\com\\ns\\util\\JDBC_info.properties");
				//�����ļ�
				prop.load(in);
				//��ȡ�ļ�
				url = prop.getProperty("url");
				userName = prop.getProperty("userName");
				passWord = prop.getProperty("passWord");
				dirver = prop.getProperty("dirver");
				Class.forName(dirver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		/**
		 * ��ȡ���ӵķ���
		 * 
		 */
		public static Connection getConnection(){
			try {
				Connection conn = DriverManager.getConnection(url,userName,passWord);
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * �ͷ���Դ�ķ���
		 */
		public static void close(Statement stmt,Connection conn){
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("stmt��Դ�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
		}


		/**
		 * �ͷ���Դ�ķ���
		 */
		public static void close(Statement stmt,Connection conn,ResultSet rs){
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("stmt��Դ�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("rs�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * ��ӡ�ɾ�����޸�
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public static boolean executeUpdate(String sql,Object...parameters){
			boolean isFlag = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				for (int i=0; i<parameters.length; i++) {
					pstmt.setObject(i+1, parameters[i]);
				}
				if(pstmt.executeUpdate()>0){
					isFlag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt,conn,rs);
			}
			return isFlag;
		}
		
		/**
		 * ����
		 * @param sql
		 * @return
		 */
		public static int countRows(String sql,Object...parameters){
			int num = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				for (int i=0; i<parameters.length; i++) {
					pstmt.setObject(i+1, parameters[i]);
				}
				rs = pstmt.executeQuery();
				if(rs.next()){
					num=rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt,conn,rs);
			}
			return num;
		}
		
		/**
		 * �жϸ����������Ƿ����
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public static boolean isExist(String sql,Object...parameters){
			boolean isFlag = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				for (int i=0; i<parameters.length; i++) {
					pstmt.setObject(i+1, parameters[i]);
				}
				rs = pstmt.executeQuery();
				if(rs.next()){
					isFlag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt,conn,rs);
			}
			return isFlag;
		}
		
		public static void main(String[] args) {
			
		}
}

