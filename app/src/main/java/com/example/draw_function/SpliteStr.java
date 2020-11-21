package com.example.draw_function;

import androidx.arch.core.util.Function;

import java.util.ArrayList;
import java.util.Stack;

//对函数字符串进行切割
public class SpliteStr {
    public String Expression;
    public String[] Function={"sin","cos","tan"};
    public String Now="";
    public String Pan="";
    public String su="";


    public SpliteStr() {

    }

    public SpliteStr(String expression) {
        Expression = expression;
        if(Expression!=""){
            setsu();
        }

    }

    public void setsu(){
        this.su=Expression.substring(4,Expression.length()-1);
    }


    //设置当前的
    public void SetNow(int i){
        if(i!=Expression.length()){
            this.Now=Expression.substring(i,i+1);
        }else{
            this.Now=Expression.substring(i);
        }

    }


   public boolean Pan(){
      if(su.length()==1){
          return true;
      }
      return false;
   }

    public double get(double n){
        if(getResult(Expression,"sin")){
            if(Pan()){
                double result1 =0;
                result1=Sin(n)*10;
                return result1;
            }else{
                String w=n+"";
                su=su.replace("x",w);
                double result1 = compute(su);
                result1=Sin(result1)*10;
                return result1;
            }

        }else
        if(getResult(Expression,"cos")){
            if(Pan()){
                double result1 =0;
                result1=Cos(n)*10;
                return result1;
            }else{
                String w=n+"";
                su=su.replace("x",w);
                double result1 = compute(su);
                result1=Cos(result1)*10;
                return result1;
            }

        }else
        if(getResult(Expression,"tan")){
            if(Pan()){
                double result1 =0;
                result1=Tan(n)*10;
                return result1;
            }else{
                String w=n+"";
                su=su.replace("x",w);
                double result1 = compute(su);
                result1=Tan(result1)*10;
                return result1;
            }

        }
        return 1;

    }

    public double Sin(double n){
        double a=Math.sin(3.14*n);
        return a;
    }
    public double Cos(double n){
        double a=Math.cos(n/180*Math.PI);
        return a;
    }

    public double Tan(double n){
        double a=Math.tan(n/180*Math.PI);
        return a;
    }


    public static boolean getResult(String targetStr, String str) {
        if (targetStr.contains(str)) {
            return true;
        } else {
            return false;
        }
    }


    public static int priority(char s) {
        switch (s) {
            case '(':
            case ')':
                return 0;
            case '-':
            case '+':
                return 1;
            case '*':
            case '%':
            case '/':
                return 2;
            default:
                return -1;

        }
    }

    public static double compute(double num1, double num2, char s) {
        switch (s) {
            case '(':
            case ')':
                return 0;
            case '-':
                return num1 - num2;
            case '+':
                return num1 + num2;
            case '%':
                return num1 % num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0;

        }
    }

    public static double compute(String str) {
        double num[] = new double[20];
        int flag = 0, begin = 0, end = 0, now;
        now = -1;
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            if (s == ' ') {

            } else if (s == '+' || s == '-' || s == '*' || s == '/' || s == '(' || s == ')' || s == '%') {
                if (flag == 1) {
                    now += 1;
                    if (end < begin) {
                        num[now] = Integer.valueOf(str.substring(begin, begin + 1));
                    } else {
                        num[now] = Integer.valueOf(str.substring(begin, end + 1));
                    }
                    // System.out.println(num[now]);
                    flag = 0;
                }
                if (s == '-') {
                    if (i == 0) {
                        flag = 1;
                        begin = 0;
                    } else if (str.charAt(i - 1) == '(' || str.charAt(i - 1) == '*'
                            || str.charAt(i - 1) == '/') {
                        flag = 1;
                        begin = i;
                    }
                    else {
                        if (st.empty()) {
                            st.push(s);
                        } else if (s == ')') {
                            num[now - 1] = compute(num[now - 1], num[now], st.pop());
                            now -= 1;
                            st.pop();
                        } else if (s == '(') {
                            st.push(s);
                        } else if (priority(s) <= priority(st.peek())) {
                            num[now - 1] = compute(num[now - 1], num[now], st.pop());
                            now -= 1;
                            st.push(s);
                        } else {
                            st.push(s);
                        }
                    }
                } else if (st.empty()) {
                    st.push(s);
                } else if (s == ')') {
                    num[now - 1] = compute(num[now - 1], num[now], st.pop());
                    now -= 1;
                    st.pop();
                } else if (s == '(') {
                    st.push(s);
                } else if (priority(s) <= priority(st.peek())) {
                    num[now - 1] = compute(num[now - 1], num[now], st.pop());
                    now -= 1;
                    st.push(s);
                } else {
                    st.push(s);
                }

            } else if (flag == 0) {
                flag = 1;
                begin = i;
            } else {
                end = i;
            }

        }
        if (flag == 1) {
            now += 1;
            if (end < begin) {
                num[now] = Integer.valueOf(str.substring(begin, begin + 1));
            } else {
                num[now] = Integer.valueOf(str.substring(begin, end + 1));
            }
            // System.out.println(num[now]);
        }
        while (now > 0) {
            num[now - 1] = compute(num[now - 1], num[now], st.pop());
            now -= 1;
        }
        return num[0];
    }

}
