public class LC5H_2p_dp {
//    方法	时间	空间	判断回文的成本
//    枚举两端 (i,j) + 双指针从两端验证	O(n³)	O(1)	枚举 O (n²) × 验证 O (n)
//    DP	O(n²)	O(n²)	查 dp[i+1][j-1]，O(1)
//    中心扩散	O(n²)	O(1)	扩展过程自带验证
//    Manacher	O(n)	O(n)	利用回文半径

    // 中心扩散
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String r1 = expand(s, i, i);
            String r2 = expand(s, i, i + 1);
            res =  maxString(res, r1, r2);
        }
        return res;
    }
    // 从中心判断最大的回文串
    public String expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        // 符合条件的回文串是s[l + 1, r - 1], 但substring是左开右闭
        return s.substring(l + 1, r); // r取不到，只能取到r-1
    }

    public String maxString(String a, String b, String c) {
        String res;
        if (a.length() > b.length()) {
            res = a;
        } else {
            res = b;
        }
        if (res.length() < c.length()) {
            res = c;
        }
        return res;
    }


    // dp写法
    public String longestPalindrome2(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[n][n];   // 默认就是 false，不用显式初始化

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if ((j - 1) - (i + 1) + 1 < 2) {   // 中间子串长度 0 或 1，直接回文
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
