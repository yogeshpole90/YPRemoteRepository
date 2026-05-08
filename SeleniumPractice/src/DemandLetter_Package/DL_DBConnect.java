package DemandLetter_Package;

import Utility_Package.DBUtil;

public class DL_DBConnect {
	public static void main(String[] args) {
		DBUtil.fetchData("D310046", "Demand Letter");
	}
}
