package com.clayoverwind.toolbox.string;

/**
 * @author clayoverwind
 * @version 2017/4/12
 * @E-mail clayanddev@163.com
 */
public class StringQueryUtil {
    /**
     * 最长回文子串
     * @param s
     * @return
     */
    private static int manacher(String s) {
        int maxLen = 0;
        String newString = joinWithChar(s, (char)0);
        int newLen = newString.length();

        int[] maxR = new int[newLen];
        int maxRight = 0;
        int pivot = 0;

        for (int current = 0; current < newLen; ++current) {
            if (current < maxRight) {
                maxR[current] = Math.min(maxR[2 * pivot - current], maxRight - current);
            } else {
                maxR[current] = 1;
            }
            // start expending
            while (current - maxR[current] >= 0 && current + maxR[current] < newLen && newString.charAt(current - maxR[current]) == newString.charAt(current + maxR[current])) {
                maxR[current]++;
            }
            // update maxRight and pivot
            if (maxR[current] + current - 1 > maxRight) {
                maxRight = maxR[current] + current - 1;
                pivot = current;
            }
            maxLen = Math.max(maxLen, maxR[current]);
        }

        return maxLen - 1;
    }

    private static String joinWithChar(String s, char c) {
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < s.length();) {
            sb.append(flag ? c : s.charAt(i++));
            flag = !flag;
        }
        sb.append(c);
        return sb.toString();
    }
}
