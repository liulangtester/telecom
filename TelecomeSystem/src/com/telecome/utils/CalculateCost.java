package com.telecome.utils;

import com.telecome.po.Bill;
import com.telecome.po.User;

/**
 * 计算单次通话产生的话费
 * @author Administrator
 *
 */
public class CalculateCost {
	public float calculate(Bill bill, User user) {
		//产生的话费
		float cost = 0;
		// 00:00:00  小时：分钟：秒
		/*计算规则：主叫号默认都是电信的
		   		      电信  --> 电信	0.16/分钟
				      电信  --> 移动	0.20/分钟
				      电信  --> 联通	0.18/分钟
		 
		 手机号供应商判断规则：手机号为11位
		 					131或132开头是联通
		 					
		 					135或136开头是移动
		 					
		 					188或189开头是电信
		 					
		 					其他都按0.5每分钟。
		*/
		String[] time = bill.getTime().split(":");
		//通话时长的分钟数
		int millions = 0;
		String str0 = time[0];
		String str1 = time[1];
		String str2 = time[2];
		millions = Integer.parseInt(str0) * 60 + Integer.parseInt(str1);
		//秒数大于0都按一分钟算
		millions = Integer.parseInt(str2)>0 ? millions+1 : millions+0;
		
		
		//利用正则表达式判断被叫号是哪个运营商
		if((""+bill.getBeCalledNumber()).matches("13[1-2][0-9]{8}")) {
			//联通
			cost = (float) (millions * 0.18);
		}else if((""+bill.getBeCalledNumber()).matches("13[5-6][0-9]{8}")) {
			//移动
			cost = (float) (millions * 0.20);
		}else if((""+bill.getBeCalledNumber()).matches("18[8-9][0-9]{8}")) {
			//电信
			cost = (float) (millions * 0.16);
		}else {
			cost = (float) (millions * 0.5);
		}
		
		/*
		  四种类型的客户分别有不同的优惠
		 */
		if("公客".equals(user.getUser_type())) {
			//没有优惠
			cost = cost;
		}else if("商客".equals(user.getUser_type())) {
			//95折
			cost = (float) (0.95*cost);
		}else if("大客".equals(user.getUser_type())) {
			//9折
			cost = (float) (0.9*cost);
		}else if("专客".equals(user.getUser_type())) {
			//8折
			cost = (float) (0.8*cost);
		}
		return cost; 
	}
}

