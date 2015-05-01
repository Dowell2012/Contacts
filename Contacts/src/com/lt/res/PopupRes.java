package com.lt.res;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

import com.lt.control.PopupMenuGroupItemHandler;
import com.lt.control.PopupMenuItemHandler;
import com.lt.model.DBManager;

public final class PopupRes {
	DBManager db = new DBManager();
	public static PopupRes popup = null;
	public JPopupMenu jPopupMenu = null;
	public JMenuItem seeingInfo = null;//�鿴��ϸ
	public JMenu transitionGroup = null;//�ƶ�����������
	public JMenuItem deletePerson = null;//ɾ����ϵ��
	public JMenuItem modifyPerson = null;//�޸�����
	public JMenuItem addPerson = null;//�����ϵ��
	public static String CELLPHONE_TEXT = null;
	
	public JPopupMenu groupPopup = null;
	public JMenuItem addNewGroup = null;
	public JMenuItem deleteGroup = null;
	public JMenuItem renameGroup = null;
	public static String GROUP_NAME = null;
	private PopupRes()
	{
		jPopupMenu = new JPopupMenu();
		seeingInfo = new JMenuItem("�鿴����");
		transitionGroup = new JMenu("�ƶ�����������");
		deletePerson = new JMenuItem("ɾ����ϵ��");
		modifyPerson = new JMenuItem("�޸�����");
		addPerson = new JMenuItem("����µ���ϵ��");
		jPopupMenu.add(seeingInfo);
		jPopupMenu.addSeparator();
		jPopupMenu.add(transitionGroup);
		jPopupMenu.addSeparator();
		jPopupMenu.add(deletePerson);
		jPopupMenu.addSeparator();
		jPopupMenu.add(modifyPerson);
		jPopupMenu.addSeparator();
		jPopupMenu.add(addPerson);
		jPopupMenu.setBorder(new BevelBorder(BevelBorder.RAISED));
		seeingInfo.setForeground(Color.BLUE);
		transitionGroup.setForeground(Color.BLUE);
		deletePerson.setForeground(Color.BLUE);
		modifyPerson.setForeground(Color.BLUE);
		addPerson.setForeground(Color.BLUE);
		seeingInfo.addActionListener(new PopupMenuItemHandler());
		deletePerson.addActionListener(new PopupMenuItemHandler());
		modifyPerson.addActionListener(new PopupMenuItemHandler());
		addPerson.addActionListener(new PopupMenuItemHandler());
		
		groupPopup = new JPopupMenu();
		addNewGroup = new JMenuItem("����·���");
		deleteGroup = new JMenuItem("ɾ������(����ɾ��)");
		renameGroup = new JMenuItem("������");
		groupPopup.add(addNewGroup);
		groupPopup.addSeparator();
		groupPopup.add(deleteGroup);
		groupPopup.addSeparator();
		groupPopup.add(renameGroup);
		groupPopup.setBorder(new BevelBorder(BevelBorder.RAISED));
		addNewGroup.setForeground(Color.RED);
		deleteGroup.setForeground(Color.RED);
		renameGroup.setForeground(Color.RED);
		addNewGroup.addActionListener(new PopupMenuGroupItemHandler());
		deleteGroup.addActionListener(new PopupMenuGroupItemHandler());
		renameGroup.addActionListener(new PopupMenuGroupItemHandler());
	}
	public static PopupRes newInstance()
	{
		if(popup == null)
		{
			popup = new PopupRes();
		}
		return popup;
	}
}
