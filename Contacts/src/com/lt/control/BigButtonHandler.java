package com.lt.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.lt.model.DBManager;
import com.lt.model.MultiCommponent;
import com.lt.model.PanelFactory;
import com.lt.res.DataSave;
import com.lt.res.Strings;

/**
 * @author Administrator
 *�ԡ�ȫ�����͡����顱��ť�¼����д���
 */
public class BigButtonHandler implements ActionListener{
	 DBManager db = new DBManager();
	public BigButtonHandler()
	{}
	@Override
	public void actionPerformed(ActionEvent e) {
		DataSave.LIST.clear();
		DataSave.GLIST.clear();
		if(e.getSource() == MultiCommponent.newInstance().returnJB(1))//ȫ��
		{
			MultiCommponent.newInstance().returnJB(1).setText(Strings.T_LEFT_TEXT + "("+db.getPersonCount()+")");
			MultiCommponent.newInstance().returnJB(2).setText(Strings.T_MIDDLE_TEXT+"("+db.getGroupCount()+")");
			this.getClass().getResource("fenzu.png");
			MultiCommponent.newInstance().returnJB(2).setIcon(new ImageIcon(this.getClass().getResource("fenzu.png")));
			MultiCommponent.newInstance().returnJB(4).setText(Strings.B_LEFT_TEXT);
			MultiCommponent.newInstance().returnJB(6).setText(Strings.B_RIGHT_TEXT);	
			PanelFactory.newInstance().createPanel(((JButton)e.getSource()).getText());
		}
		if(e.getSource() == MultiCommponent.newInstance().returnJB(2))//����
		{
			MultiCommponent.newInstance().returnJB(1).setText(Strings.T_LEFT_TEXT + "("+db.getPersonCount()+")");
			MultiCommponent.newInstance().returnJB(2).setText(Strings.T_MIDDLE_TEXT+"("+db.getGroupCount()+")");
			MultiCommponent.newInstance().returnJB(2).setIcon(new ImageIcon(this.getClass().getResource("fenzu.png")));
			MultiCommponent.newInstance().returnJB(4).setText(Strings.B_LEFT_TEXT_2);
			MultiCommponent.newInstance().returnJB(6).setText(Strings.B_RIGHT_TEXT_2);
			PanelFactory.newInstance().createPanel(((JButton)e.getSource()).getText());
		}
		if(e.getSource() == MultiCommponent.newInstance().returnJB(3))//����
		{
			MultiCommponent.newInstance().returnJB(1).setText(Strings.T_LEFT_TEXT + "("+db.getPersonCount()+")");
			MultiCommponent.newInstance().returnJB(2).setText(Strings.T_MIDDLE_TEXT+"("+db.getGroupCount()+")");
			MultiCommponent.newInstance().returnJB(2).setIcon(new ImageIcon(this.getClass().getResource("fenzu.png")));
			MultiCommponent.newInstance().returnJB(4).setText(Strings.B_LEFT_TEXT);
			MultiCommponent.newInstance().returnJB(6).setText(Strings.B_RIGHT_TEXT);
			PanelFactory.newInstance().createSearchPanel(((JButton)e.getSource()).getText());
		}
		if(e.getSource() == MultiCommponent.newInstance().returnJB(4))//������ϵ��or��������
		{
			MultiCommponent.newInstance().returnJB(1).setText(Strings.T_LEFT_TEXT + "("+db.getPersonCount()+")");
			MultiCommponent.newInstance().returnJB(2).setText(Strings.T_MIDDLE_TEXT+"("+db.getGroupCount()+")");
			if(((JButton)e.getSource()).getText().equals(Strings.B_LEFT_TEXT))
				PanelFactory.newInstance().createADDPersonPanel();
			else if(((JButton)e.getSource()).getText().equals(Strings.B_LEFT_TEXT_2))
				PanelFactory.newInstance().createADDGroupPanel();
		}
		if(e.getSource() == MultiCommponent.newInstance().returnJB(5))//�����Ȩ
		{
			MultiCommponent.newInstance().returnJB(1).setText(Strings.T_LEFT_TEXT + "("+db.getPersonCount()+")");
			MultiCommponent.newInstance().returnJB(2).setText(Strings.T_MIDDLE_TEXT+"("+db.getGroupCount()+")");
			PanelFactory.newInstance().createSoftWareCopyright();
		}
		if(e.getSource() == MultiCommponent.newInstance().returnJB(6))//������չ
		{
			MultiCommponent.newInstance().returnJB(1).setText(Strings.T_LEFT_TEXT + "("+db.getPersonCount()+")");
			MultiCommponent.newInstance().returnJB(2).setText(Strings.T_MIDDLE_TEXT+"("+db.getGroupCount()+")");
			if(((JButton)e.getSource()).getText().equals(Strings.B_RIGHT_TEXT))
			{
				PanelFactory.newInstance().createChoseDeletePersonPanel();
			}
			else if(((JButton)e.getSource()).getText().equals(Strings.B_RIGHT_TEXT_2))
			{
				PanelFactory.newInstance().createChoseDeleteGroupPanel();
			}
		}
	}

}
