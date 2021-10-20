public class Q14 {
    public static String longestCommonPrefix(String[] strs) {
        StringBuffer sbf = new StringBuffer("");
        int maxLen = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            int tempLen = strs[i].length();
            if (tempLen<maxLen){
                maxLen = tempLen;
            }
        }

        int flag = 1;
        for (int i = 0; i < maxLen; i++) {
            char c = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != c){
                    flag = 0 ;
                    break;
                }
            }
            if (flag == 0)
                break;
            sbf.append(c);
        }
        return sbf.toString();
    }

    public static String longestCommonPrefix_hengxiang(String[] strs){
        String sbf =  strs[0];
        for (int i = 1; i < strs.length; i++) {
            sbf = getMaxSubStr(sbf,strs[i]);
            if (sbf.length() == 0){
                return "";
            }
        }
        return sbf;
    }

    public static  String getMaxSubStr(String oldStr,String newStr){
        while (oldStr.length()>0){
            if(newStr.indexOf(oldStr) == 0){
                return oldStr;
            }
            oldStr = oldStr.substring(0,oldStr.length()-1);
        }
        return "";
    }

    /**
     * Divide-and-Conquer Method
     * @return
     */
    public static String longestCommonPrefix_DaC(String[] strs){
        if (strs == null || strs.length == 0 ){
            return "";
        }
        return DaC(strs,0,strs.length-1);
    }

    // 分治
    public static String DaC(String[] strs, int start, int end) {
        if (start == end){
            return strs[start];
        }else {
            int mid = ( end - start)/2 +start;
            String leftStr = DaC(strs,start,mid);
            String rightStr = DaC(strs,mid+1,end);
            return getMaxSubStr(leftStr,rightStr);
        }
    }



    /**
     * bisection method
     * @return
     */
    public static String longestCommonPrefix_Bi(String[] strs){
        StringBuffer sbf = new StringBuffer("");
        int maxLen = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            int tempLen = strs[i].length();
            if (tempLen<maxLen){
                maxLen = tempLen;
            }
        }
        // 得到了最短的串长（最长公共子串的可能长度
        int start = 0;
        int end = maxLen-1;
        int mid = (end - start)/2;

        int flag = 1;
        while (start<end){
            System.out.println(start);
            flag = 1;
            char c = strs[0].charAt(mid);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(mid) != c){
                    flag = 0 ;
                    break;
                }
            }
            if (flag == 1){
                start = mid+1;
                mid = (end - start)/2 +start;
            }else {
                end = mid -1;
                mid = (end - start)/2 + start;
            }
        }

        for (int i = 0; i < start; i++) {
            char c = strs[0].charAt(i);
            sbf.append(c);
        }
        return sbf.toString();
    }

    public static void main(String[] args) {
        String[] strs = new  String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix_Bi(strs));
    }
}
