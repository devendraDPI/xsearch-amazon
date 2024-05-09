package xsearchamazon;

public class App {
    public void getGreeting() {
        TestCases tests = new TestCases(); // Initialize your test class

        // TODO: call your test case functions one after other here
        // START Tests
        tests.testCase01();
        tests.testCase02();
        tests.testCase03();

        // END Tests
        tests.endTest(); // End your test by clearing connections and closing browser
    }

    public static void main(String[] args) {
        new App().getGreeting();
    }
}
