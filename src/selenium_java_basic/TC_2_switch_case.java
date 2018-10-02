package selenium_java_basic;

public class TC_2_switch_case {
	public static void main(String[] args) {
		String browser_ =  "safari";
		switch (browser_) {
		case "chrome":
			System.out.println("khoi tao trinh duyet CHROME");
			break;
		case "ie":
			System.out.println("khoi tao trinh duyet IE");
			break;
		default:
			System.out.println("khoi tao trinh duyet SAFARI");
			break;
		}
	}

}

