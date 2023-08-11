package edu.zut.cn.stack;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        boolean a1 = pdfunction("aba");
        boolean a2 = pdfunction("abcd");
        int jsfunction = jsfunction("1+12+2+4/2");
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(jsfunction);
    }

    public static boolean pdfunction(String val){//输入字符串判断是否为回文字符串
        ArrayStack arrayStack = new ArrayStack(10);

        for(int i = 0;i<val.length();i++){//将字符挨个压栈
            try {
                arrayStack.push(val.charAt(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String val2 = "";
        int length1 = arrayStack.length();//因为length函数是依托于top的值，而pop函数执行下去会减少top从而减少length，故需要提取出
        for(int i = 0;i<length1;i++){//挨个弹栈并拼入val2中
            try {
                char n = (char)arrayStack.pop();
                val2 = val2+n;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return val2.equals(val);//判断压栈前和弹栈后两个字符串是否相等
    }

    public static int jsfunction(String val){//使用栈解决运算问题
        ArrayStack numStack = new ArrayStack(10);//创建数字栈
        ArrayStack symStack = new ArrayStack(10);//创建运算符栈
        int num1 = 0;
        int num2 = 0;
        int symChar = 0;
        String value = "";
        for(int i = 0;i<val.length();i++){//for循环遍历所有字符
            if(symStack.isOper(val.charAt(i))){//判断该字符是否为运算符，如果是，则进入
                if(!symStack.isEmpty()){//判断运算符栈是否为空，如果不是，则进入
                    if(symStack.yxj(val.charAt(i)) <= symStack.yxj((char) symStack.pep())){//判断当前字符串与栈顶字符串的优先级，若小于等于，则进入
                        try {//拿出数字栈中两个数据与运算符栈的一个数据
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            symChar = symStack.pop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        int calculate = numStack.calculate(num1, num2, symChar);//计算

                        try {//将计算结果压入数字栈，并将当前字符压入运算符栈
                            numStack.push(calculate);
                            symStack.push(val.charAt(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {//如果当前字符优先级小于栈顶字符优先级，则直接将当前字符压入运算符栈
                        try {
                            symStack.push(val.charAt(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else {//如果运算符栈为空，则直接将当前字符压入运算符栈
                    try {
                        symStack.push(val.charAt(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }else {
                //比如：33+44
                value += val.charAt(i);//若为数字，则让value拼接
                if(i==val.length()-1){//若为字符串末尾，则直接压栈入数字栈
                    try {
                        numStack.push(Integer.parseInt(value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {//若不在末尾，则继续判断
                    if(symStack.isOper(val.charAt(i+1))){//若下一个字符为运算符，直接将value压栈并将value清空
                        try {
                            numStack.push(Integer.parseInt(value));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        value = "";
                    }
                    //若下一个不为运算符，继续向下走让value继续拼接
                }
            }
        }
        int result = 0;
        while(true){
            if(symStack.isEmpty()){//如果运算符栈空了，则跳出循环
                break;
            }
            try {//拿出数字栈中两个数据与运算符栈的一个数据
                num1 = numStack.pop();
                num2 = numStack.pop();
                symChar = symStack.pop();
            } catch (Exception e) {
                e.printStackTrace();
            }

            result = numStack.calculate(num1, num2, symChar);//计算
            try {
                numStack.push(result);//计算结果继续压入数字栈
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;//得到结果
    }

}
