package com.yundong.milk.util;


import com.yundong.milk.user.model.GroupMemberBean;

import java.util.Comparator;

/**
 * 
 * Created by lj on 2016/9/20.
 * pinyin 比较 
 *
 */
public class PinyinComparator implements Comparator<GroupMemberBean> {

	public int compare(GroupMemberBean o1, GroupMemberBean o2) {
		if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#") || o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
