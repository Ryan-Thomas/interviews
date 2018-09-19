import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BracketMatcher {

    public static boolean isValid(String code) {
        Stack<Character> s = new Stack<>();
        for (char c : code.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                s.push(c);
            } else if (c == '}') {
                if (s.isEmpty() || s.pop() != '{')
                    return false;
            } else if (c == ']') {
                if (s.isEmpty() || s.pop() != '[')
                    return false;
            } else if (c == ')') {
                if (s.isEmpty() || s.pop() != '(')
                    return false;
            }
        }
        return s.isEmpty();
    }


















    // tests

    @Test
    public void validShortCodeTest() {
        final boolean result = isValid("()");
        assertTrue(result);
    }

    @Test
    public void validLongerCodeTest() {
        final boolean result = isValid("([]{[]})[]{{}()}");
        assertTrue(result);
    }

    @Test
    public void mismatchedOpenerAndCloserTest() {
        final boolean result = isValid("([][]}");
        assertFalse(result);
    }

    @Test
    public void interleavedOpenersAndClosersTest() {
        final boolean result = isValid("([)]");
        assertFalse(result);
    }

    @Test
    public void missingCloserTest() {
        final boolean result = isValid("[[]()");
        assertFalse(result);
    }

    @Test
    public void extraCloserTest() {
        final boolean result = isValid("[[]]())");
        assertFalse(result);
    }

    @Test
    public void emptyStringTest() {
        final boolean result = isValid("");
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BracketMatcher.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}