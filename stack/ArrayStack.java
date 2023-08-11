package edu.zut.cn.stack;

public class ArrayStack {
    private int maxStack;//设置栈的最大容量
    private int[] stack;//数组模拟栈
    private int top = -1;//表示栈顶所在位置，默认为-1表示空栈

    public ArrayStack(int maxStack) {
        this.maxStack = maxStack;
        stack = new int[maxStack];//初始化数组
    }

    public boolean isFull(){//判断栈是否已满，若满，返回true
        return top == maxStack-1;
    }

    public boolean isEmpty(){//判断栈是否为空，若为空，返回true
        return top == -1;
    }

    public void push(int val) throws Exception {//压栈
        if(isFull()){//判断是否已满
            throw new Exception("栈已满，无法压栈");
        }
        top++;
        stack[top] = val;
    }

    public int pop() throws Exception {//弹栈
        if(isEmpty()){//判断是否为空
            throw new Exception("栈为空，无法弹栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() throws Exception {//查看栈中所有元素
        if(isEmpty()){//判断是否为空
            throw new Exception("栈为空，无法弹栈");
        }
        for(int i = 0;i<stack.length;i++){
            System.out.println("stack["+i+"]="+stack[i]);
        }
    }

    public int length(){//获取栈的当前长度
        return top+1;
    }


    public int pep(){//获取栈顶数据
        return stack[top];
    }

    public int stackLength(){//获取栈容量
        return stack.length;
    }

    public boolean isOper(char x){//判断是否为运算符
        return x=='+'||x=='-'||x=='*'||x=='/';
    }

    public int yxj(char x){//输出运算符的优先级
        if(x=='*'||x=='/'){
            return 1;
        }else if(x=='+'||x=='-'){
            return 0;
        }else {
            return -1;
        }
    }

    public int calculate(int num1,int num2,int symChar){//返回计算值
        int result = 0;
        switch (symChar){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num2-num1;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num2/num1;
                break;
            default:
                break;
        }
        return result;
    }


}



















