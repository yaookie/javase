package singleLinkedLitst.Review;

import java.util.Stack;

public class LinkedList01 {
    public static void main(String[] args) {
        //创建学生节点对象
        StudentNode s1 = new StudentNode(01, "Jack", "male");
        StudentNode s2 = new StudentNode(02, "Rose", "female");
        StudentNode s3 = new StudentNode(03, "Alice", "female");
        StudentNode s4 = new StudentNode(04, "Anderson", "female");

        //创建链表对象
        LinkedList linkedList = new LinkedList();

        //向链表中添加节点
        /*linkedList.add(s1);
        linkedList.add(s2);
        linkedList.add(s3);
        linkedList.add(s4);*/

        //显示链表
        //linkedList.list();

        //按顺序向链表添加节点
        linkedList.addByOrder(s3);
        linkedList.addByOrder(s2);
        linkedList.addByOrder(s4);
        linkedList.addByOrder(s1);
        System.out.println("按顺序添加的节点：");
        linkedList.list();

        //修改节点
        linkedList.update(new StudentNode(03, "Alan", "male"));
        System.out.println("修改后的节点为：");
        linkedList.list();

        //删除节点
        linkedList.delete(02);
        System.out.println("删除后的节点为：");
        linkedList.list();

        //获取链表长度
        System.out.println("链表长度为：" + getLength(linkedList.headNode));

        //获取倒数第index个节点
        System.out.println("倒数第1个节点为：" + getReverseIndex(linkedList.headNode, 1));

        //反转链表
        reverseList(linkedList.headNode);
        System.out.println("第一种方式反转链表：");
        linkedList.list();

        //第二种方式反转链表（栈的方式）
        System.out.println("第二种方式反转链表（栈的方式）：");
        reversePrint(linkedList.getHeadNode());
    }

    /**
     * 获取链表长度
     * @param headNode 头节点
     * @return 返回链表长度
     */
    public static int getLength(StudentNode headNode) {
        StudentNode tempNode = headNode;
        if (tempNode.next == null) {
            return 0;
        }
        int length = 0;
        while (tempNode.next != null) {
            length++;
            tempNode = tempNode.next;
        }
        return length;
    }

    /**
     * 获取倒数第index个节点
     * @param headNode 头节点
     * @param index 倒数值
     * @return 返回找到的节点
     */
    public static StudentNode getReverseIndex(StudentNode headNode, int index) {
        StudentNode tempNode = headNode.next;
        int size = getLength(headNode);
        //判断链表是否为空
        if (tempNode == null) {
            System.out.println("链表为空！");
        }
        //输入的数不能小于等于0 或者大于链表长度
        if (index <= 0 || index > size) {
            return null;
        }

        for (int i = 0; i < size - index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    /**
     * 反转链表（会改变链表结构）
     * @param headNode 头节点
     */
    public static void reverseList(StudentNode headNode) {
        //如果链表为空或只有一个节点，直接返回
        if (headNode.next == null || headNode.next.next == null) {
            return;
        }
        StudentNode tempNode = headNode.next;
        StudentNode next1 = null;
        StudentNode reverseHead = new StudentNode(0, "", "");

        while (tempNode != null) {
            next1 = tempNode.next;
            tempNode.next = reverseHead.next;
            reverseHead.next = tempNode;
            tempNode = next1;
        }
        headNode.next = reverseHead.next;
    }

    /**
     * 反转链表（栈的方式） 可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     * @param headNode 头节点
     */
    public static void reversePrint(StudentNode headNode) {
        StudentNode tempNode = headNode.next;
        Stack<StudentNode> stack = new Stack<>(); //创建栈对象
        if (tempNode == null) { //空链表
            return;
        }

        while (tempNode != null) {
            stack.push(tempNode); //把节点压入栈中
            tempNode = tempNode.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop()); //将栈中数据弹出
        }
    }
}

//创建链表类
class LinkedList {
    //创建头节点
    StudentNode headNode = new StudentNode(0, "", "");
    public StudentNode getHeadNode() {
        return headNode;
    }

    /**
     * 添加节点方法
     * @param studentNode 学生节点
     */
    public void add(StudentNode studentNode) {
        //创建辅助节点
        StudentNode tempNode = headNode;

        while (true) {
            if (tempNode.next == null) { //遍历到最后一个节点时退出
                break;
            }
            tempNode = tempNode.next;
        }
        tempNode.next = studentNode; //把添加的节点赋给最后一个节点的next
    }

    /**
     * 按顺序添加节点
     * @param studentNode 学生节点
     */
    public void addByOrder(StudentNode studentNode) {
        //创建辅助节点
        StudentNode tempNode = headNode;
        boolean flag = true; //flag标记，默认为false

        while (true) {
            if (tempNode.next == null) { //遍历到最后一个节点退出
                break;
            }

            if (tempNode.next.no > studentNode.no) {
                break;
            } else if (tempNode.next.no == studentNode.no) {
                flag = false;
                break;
            }
            tempNode = tempNode.next;
        }

        if (flag) {
            studentNode.next = tempNode.next;
            tempNode.next = studentNode;
        } else {
            System.out.println("节点" + studentNode + "已存在");

        }
    }


    /**
     * 修改节点方法
     * @param studentNode 学生节点
     */
    public void update(StudentNode studentNode) {
        //创建辅助节点
        StudentNode tempNode = headNode.next;
        boolean flag = true;
        if (tempNode == null) {
            System.out.println("链表为空！");
            return;
        }

        while (true) {
            if (tempNode == null) {
                flag = false;
                break;
            }
            if (tempNode.no == studentNode.no) {
                break;
            }
            tempNode = tempNode.next;
        }
        if (flag) {
            tempNode.name = studentNode.name;
            tempNode.gender = studentNode.gender;
        } else {
            System.out.println("未找到节点" + studentNode);
        }
    }

    /**
     * 删除节点方法
     * @param no 节点编号
     */
    public void delete(int no) {
        //创建辅助节点
        StudentNode tempNode = headNode;
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }

            if (tempNode.next.no == no) {
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }
        if (flag) {
            tempNode.next = tempNode.next.next;
        } else {
            System.out.println("未找到编号为" + no + "的节点。");
        }
    }

    /**
     * 显示链表的方法
     */
    public void list() {
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return;
        }

        StudentNode tempNode = headNode.next;
        while (true) {
            if (tempNode == null) {
                break;
            }
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }
}

//创建学生节点类
class StudentNode {
    public int no; //编号
    public String name; //姓名
    public String gender; //性别
    public StudentNode next;

    public StudentNode(int no, String name, String gender) {
        this.no = no;
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StudentNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}