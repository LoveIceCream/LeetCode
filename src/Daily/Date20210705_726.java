import java.util.*;

/**
 * TODO 未实际完成 周末待做
 * @author wangzhen
 * @version 1.0
 * @description 今天还刷了个困难题
 * @date 2021/7/5 9:26 上午
 */
public class Date20210705_726 {
    public Date20210705_726() {
    }

    public static void main(String[] args) {

        Date20210705_726 demo = new Date20210705_726();
        String formula = "H2O";
//        String formula = "Mg(OH)2";
//        String formula = "K4(ON(SO3)2)2";
        System.out.println(demo.countOfAtoms(formula));
    }

    TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();

    /**
     * 我还有一个思路是 记录好对应原子 是属于第几对(层)括号之间的，这样就能获取到他们的系数
     * <p>
     * 按照这个思路 只需要 对左括号进行压栈处理，每压入一个左括号，层级加一，pop 一个左括号 层级减一，
     */
    int deep = 1;
    LinkedHashMap<Integer, LinkedHashMap<String, Integer>> deepAndAutomsMulriple = new LinkedHashMap<>();

    /**
     * 给定一个化学式formula（作为字符串），返回每种原子的数量。
     * <p>
     * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
     * <p>
     * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
     * <p>
     * 两个化学式连在一起是新的化学式。例如H2O2He3Mg4 也是化学式。
     * <p>
     * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
     * <p>
     * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * formula = "H2O"
     * 输出: "H2O"
     * 解释:
     * 原子的数量是 {'H': 2, 'O': 1}。
     * 示例 2:
     * <p>
     * 输入:
     * formula = "Mg(OH)2"
     * 输出: "H2MgO2"
     * 解释:
     * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
     * 示例 3:
     * <p>
     * 输入:
     * formula = "K4(ON(SO3)2)2"
     * 输出: "K4N2O14S4"
     * 解释:
     * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
     * 注意:
     * <p>
     * 所有原子的第一个字母为大写，剩余字母都是小写。
     * formula的长度在[1, 1000]之间。
     * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
     *
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {

        Stack<String> leftBracketStack = new Stack<>();
        // 记录临时的原子
        String autom = "";
        // 当前层级的原子的系数 数量
        int mulriple = 1;
        // 当前层级 或者叫括号之间的原子列表 右括号结束之后，将这里的原子 乘上 系数 加入到 treemap里
        ArrayList<String> automsArrayList = new ArrayList<>();
        // 遍历 formula 依次判断大小写，左括号 右括号
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if (Character.isUpperCase(c)) {
                if (autom.equals("")) {
                    autom += c;
                } else {
                    automsArrayList.add(autom);
                }
            }
            if (Character.isLowerCase(c)) {
                autom += c;
            }
            if (Character.isDigit(c)) {
                // 是不是 应该需要判断 之后的字符
            }
            if (c == '(') {
                //  左括号，压入栈，之后匹配的 原子 深度加一
//                leftBracketStack.push("(");
                deep++;
            }
            if (c == ')') {
                deep--;
            }
        }


        // 需要一个栈
        // 遇到左括号的时候，将内容压入栈，右括号的时候，读取下一个数字，如果下一个不是数字，数字默认为1

        // 先试试递归 每一个括号里的内容，作为 formula 传入，括号外的作为系数 初始默认为1
//        countOfAtoms(formula, 1);

        return null;
    }

    public void countOfAtoms(String formula, int mulriple) {
        // 如果第一个字符就是括号开始的
        if (formula.substring(0, 1).equals("(")) {

        } else {
            // 从元素开始，获取 遇到的第一个左括号外的元素
            // 获取 最后一个右括号外的 元素
            // 记录器数量

            // 将 两个括号之间的内容拿出来，递归，系数想乘
            //
            // 还有可能是 多个同级括号 并列的  这种情况先不管
        }
    }


}
