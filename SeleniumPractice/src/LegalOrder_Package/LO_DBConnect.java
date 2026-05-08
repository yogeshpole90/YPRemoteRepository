package LegalOrder_Package;

import Utility_Package.DBUtil;

public class LO_DBConnect {
	public static void main(String[] args) {
		DBUtil.fetchData("D310209", "Legal Order Detail");
	}
}
