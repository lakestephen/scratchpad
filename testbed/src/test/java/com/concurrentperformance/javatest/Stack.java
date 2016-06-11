package com.concurrentperformance.javatest;

/**
 * Stack implementation
 *
 * @author Steve Lake
 */
public class Stack {

    private long[] stackArray = new long[10];
    private int top = -1;

    public void push(long value) {
        if (++top > stackArray.length) {
            growStack();
        }
        stackArray[top] = value;
    }

    public long pop() {
        return stackArray[top--];
    }

    public long peek() {
        return stackArray[top];
    }

    private void growStack() {
        long[] oldData = stackArray;
        stackArray = new long[oldData.length + 10];
        for (int i=0;i<=stackArray.length;i++) {
            stackArray[i] = oldData[i];
        }
    }

}