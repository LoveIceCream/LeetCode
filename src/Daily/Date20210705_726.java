import java.util.*;

/**
 * TODO δʵ����� ��ĩ����
 * @author wangzhen
 * @version 1.0
 * @description ���컹ˢ�˸�������
 * @date 2021/7/5 9:26 ����
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
     * �һ���һ��˼·�� ��¼�ö�Ӧԭ�� �����ڵڼ���(��)����֮��ģ��������ܻ�ȡ�����ǵ�ϵ��
     * <p>
     * �������˼· ֻ��Ҫ �������Ž���ѹջ����ÿѹ��һ�������ţ��㼶��һ��pop һ�������� �㼶��һ��
     */
    int deep = 1;
    LinkedHashMap<Integer, LinkedHashMap<String, Integer>> deepAndAutomsMulriple = new LinkedHashMap<>();

    /**
     * ����һ����ѧʽformula����Ϊ�ַ�����������ÿ��ԭ�ӵ�������
     * <p>
     * ԭ��������һ����д��ĸ��ʼ�����Ÿ���0���������Сд��ĸ����ʾԭ�ӵ����֡�
     * <p>
     * ����������� 1��ԭ�Ӻ��������ֱ�ʾԭ�ӵ������������������ 1 �򲻻�����֡����磬H2O �� H2O2 �ǿ��еģ��� H1O2 �������ǲ����еġ�
     * <p>
     * ������ѧʽ����һ�����µĻ�ѧʽ������H2O2He3Mg4 Ҳ�ǻ�ѧʽ��
     * <p>
     * һ�������еĻ�ѧʽ�����֣���ѡ������ӣ�Ҳ�ǻ�ѧʽ������ (H2O2) �� (H2O2)3 �ǻ�ѧʽ��
     * <p>
     * ����һ����ѧʽ���������ԭ�ӵ���������ʽΪ����һ�������ֵ���ԭ�ӵ����ӣ�������������������������� 1����Ȼ���ǵڶ���ԭ�ӵ����֣����ֵ��򣩣�������������������������� 1�����Դ����ơ�
     * <p>
     * ʾ�� 1:
     * <p>
     * ����:
     * formula = "H2O"
     * ���: "H2O"
     * ����:
     * ԭ�ӵ������� {'H': 2, 'O': 1}��
     * ʾ�� 2:
     * <p>
     * ����:
     * formula = "Mg(OH)2"
     * ���: "H2MgO2"
     * ����:
     * ԭ�ӵ������� {'H': 2, 'Mg': 1, 'O': 2}��
     * ʾ�� 3:
     * <p>
     * ����:
     * formula = "K4(ON(SO3)2)2"
     * ���: "K4N2O14S4"
     * ����:
     * ԭ�ӵ������� {'K': 4, 'N': 2, 'O': 14, 'S': 4}��
     * ע��:
     * <p>
     * ����ԭ�ӵĵ�һ����ĸΪ��д��ʣ����ĸ����Сд��
     * formula�ĳ�����[1, 1000]֮�䡣
     * formulaֻ������ĸ�����ֺ�Բ���ţ�������Ŀ�и������ǺϷ��Ļ�ѧʽ��
     *
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {

        Stack<String> leftBracketStack = new Stack<>();
        // ��¼��ʱ��ԭ��
        String autom = "";
        // ��ǰ�㼶��ԭ�ӵ�ϵ�� ����
        int mulriple = 1;
        // ��ǰ�㼶 ���߽�����֮���ԭ���б� �����Ž���֮�󣬽������ԭ�� ���� ϵ�� ���뵽 treemap��
        ArrayList<String> automsArrayList = new ArrayList<>();
        // ���� formula �����жϴ�Сд�������� ������
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
                // �ǲ��� Ӧ����Ҫ�ж� ֮����ַ�
            }
            if (c == '(') {
                //  �����ţ�ѹ��ջ��֮��ƥ��� ԭ�� ��ȼ�һ
//                leftBracketStack.push("(");
                deep++;
            }
            if (c == ')') {
                deep--;
            }
        }


        // ��Ҫһ��ջ
        // ���������ŵ�ʱ�򣬽�����ѹ��ջ�������ŵ�ʱ�򣬶�ȡ��һ�����֣������һ���������֣�����Ĭ��Ϊ1

        // �����Եݹ� ÿһ������������ݣ���Ϊ formula ���룬���������Ϊϵ�� ��ʼĬ��Ϊ1
//        countOfAtoms(formula, 1);

        return null;
    }

    public void countOfAtoms(String formula, int mulriple) {
        // �����һ���ַ��������ſ�ʼ��
        if (formula.substring(0, 1).equals("(")) {

        } else {
            // ��Ԫ�ؿ�ʼ����ȡ �����ĵ�һ�����������Ԫ��
            // ��ȡ ���һ����������� Ԫ��
            // ��¼������

            // �� ��������֮��������ó������ݹ飬ϵ�����
            //
            // ���п����� ���ͬ������ ���е�  ��������Ȳ���
        }
    }


}
