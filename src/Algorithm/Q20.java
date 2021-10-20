import java.util.Stack;

public class Q20 {
    public boolean isValid(String s) {

        String l_ = "([{";
        String r_ = ")]}";
        // 创建一个存储左括号的栈
        Stack<Character> leftStack = new Stack<>();

        // 遍历s 左括号 push 右括号 pop
        for (int i = 0; i < s.length(); i++) {
            if (l_.indexOf(s.charAt(i))>-1){
                leftStack.push(s.charAt(i));
                continue;
            }
            if (r_.indexOf(s.charAt(i))>-1){
                if (leftStack.size() == 0){return false;}
                Character temp = leftStack.pop();
                if (l_.indexOf(temp) != r_.indexOf(s.charAt(i))){
                    return false;
                }
            }
        }
        if (leftStack.size()>0){
            return false;
        }
        return true;
    }


}
