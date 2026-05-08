package FollowUp_Package;

import Utility_Package.DBUtil;

public class FU_DBConnect {
	public static void main(String[] args) {
		DBUtil.fetchData("D310045", "Follow-Up History");
	}
}
