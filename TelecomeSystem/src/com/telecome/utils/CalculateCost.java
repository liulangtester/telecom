package com.telecome.utils;

import com.telecome.po.Bill;
import com.telecome.po.User;

/**
 * ���㵥��ͨ�������Ļ���
 * @author Administrator
 *
 */
public class CalculateCost {
	public float calculate(Bill bill, User user) {
		//�����Ļ���
		float cost = 0;
		// 00:00:00  Сʱ�����ӣ���
		/*����������к�Ĭ�϶��ǵ��ŵ�
		   		      ����  --> ����	0.16/����
				      ����  --> �ƶ�	0.20/����
				      ����  --> ��ͨ	0.18/����
		 
		 �ֻ��Ź�Ӧ���жϹ����ֻ���Ϊ11λ
		 					131��132��ͷ����ͨ
		 					
		 					135��136��ͷ���ƶ�
		 					
		 					188��189��ͷ�ǵ���
		 					
		 					��������0.5ÿ���ӡ�
		*/
		String[] time = bill.getTime().split(":");
		//ͨ��ʱ���ķ�����
		int millions = 0;
		String str0 = time[0];
		String str1 = time[1];
		String str2 = time[2];
		millions = Integer.parseInt(str0) * 60 + Integer.parseInt(str1);
		//��������0����һ������
		millions = Integer.parseInt(str2)>0 ? millions+1 : millions+0;
		
		
		//����������ʽ�жϱ��к����ĸ���Ӫ��
		if((""+bill.getBeCalledNumber()).matches("13[1-2][0-9]{8}")) {
			//��ͨ
			cost = (float) (millions * 0.18);
		}else if((""+bill.getBeCalledNumber()).matches("13[5-6][0-9]{8}")) {
			//�ƶ�
			cost = (float) (millions * 0.20);
		}else if((""+bill.getBeCalledNumber()).matches("18[8-9][0-9]{8}")) {
			//����
			cost = (float) (millions * 0.16);
		}else {
			cost = (float) (millions * 0.5);
		}
		
		/*
		  �������͵Ŀͻ��ֱ��в�ͬ���Ż�
		 */
		if("����".equals(user.getUser_type())) {
			//û���Ż�
			cost = cost;
		}else if("�̿�".equals(user.getUser_type())) {
			//95��
			cost = (float) (0.95*cost);
		}else if("���".equals(user.getUser_type())) {
			//9��
			cost = (float) (0.9*cost);
		}else if("ר��".equals(user.getUser_type())) {
			//8��
			cost = (float) (0.8*cost);
		}
		return cost; 
	}
}

