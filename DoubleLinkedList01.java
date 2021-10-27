package linkedLitst.doubleLinkedList;

public class DoubleLinkedList01 {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(01, "张三");
        HeroNode h2 = new HeroNode(02, "李四");
        HeroNode h3 = new HeroNode(03, "王二");
        HeroNode h4 = new HeroNode(04, "Jack");
        HeroNode h5 = new HeroNode(05, "Rose");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //添加节点
        doubleLinkedList.addByOrder(h1);
        doubleLinkedList.addByOrder(h3);
        doubleLinkedList.addByOrder(h5);
        doubleLinkedList.addByOrder(h4);
        doubleLinkedList.addByOrder(h2);
        System.out.println("初始链表为：");
        doubleLinkedList.list();

        //修改节点
        doubleLinkedList.update(new HeroNode(02, "赵六"));
        System.out.println("修改后的链表为：");
        doubleLinkedList.list();

        //删除节点
        doubleLinkedList.delete(03);
        System.out.println("删除后的链表为：");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //创建头节点
    HeroNode headNode = new HeroNode(0, "");

    /**
     * 获取头节点
     * @return 头节点
     */
    public HeroNode getHeadNode() {
        return headNode;
    }

    /**
     * 添加节点
     * @param heroNode 新节点
     */
    public void add(HeroNode heroNode) {
        HeroNode tempNode = headNode;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            tempNode = tempNode.next;
        }
        tempNode.next = heroNode;
        heroNode.pre = tempNode;
    }

    /**
     * 按顺序添加节点
     * @param heroNode 新节点
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode tempNode = headNode; //辅助节点
        boolean flag = true;
        //遍历链表，找到合适位置
        while (true) {
            if (tempNode.next == null) { //链表为空时直接添加
                break;
            }
            if (tempNode.next.no > heroNode.no) { //当辅助节点编号大于新节点时，进行添加
                break;
            } else if (tempNode.next.no == heroNode.no) { //当节点一样时，输出提示语句
                flag = false;
                break;
            }
            //节点后移，继续遍历
            tempNode = tempNode.next;
        }
        if (flag) {
            heroNode.next = tempNode.next;
            tempNode.next = heroNode;
            heroNode.pre = tempNode;
        } else {
            System.out.println("节点已存在，无法添加！");
        }
    }

    /**
     * 删除节点
     * @param no 要删除的节点编号
     */
    public void delete(int no) {
        HeroNode tempNode = headNode.next; //辅助节点
        boolean flag = false;
        if (tempNode == null) { //链表为空时，进行提示
            System.out.println("链表为空，无法删除！");
        }
        //遍历链表
        while (true) {
            if (tempNode == null) { //链表为空，停止遍历
                break;
            }
            if (tempNode.no == no) { //找到要删除的节点
                flag = true;
                break;
            }
            tempNode = tempNode.next; //还未找到节点，节点后移，继续遍历
        }
        if (flag) {
            tempNode.pre.next = tempNode.next;
            //如果删除最后一个节点就不执行下面的语句，否则会报空指针异常
            if (tempNode.next != null) {
                tempNode.next.pre = tempNode.pre;
            }
        } else {
            System.out.println("未找到编号为" + no + "的节点！");
        }
    }

    /**
     * 修改节点
     * @param heroNode 要修改的节点
     */
    public void update(HeroNode heroNode) {
        HeroNode tempNode = headNode.next; //辅助节点
        boolean flag = false; //布尔标记
        if (tempNode == null) {
            System.out.println("链表为空，无法显示！");
            return;
        }
        //遍历链表
        while (true) {
            if (tempNode == null) {
                break;
            }
            if (tempNode.no == heroNode.no) { //找到节点，flag变为true
                flag = true;
                break;
            }
            tempNode = tempNode.next; //未找到节点，节点后移，继续遍历
        }
        if (flag) {
            tempNode.name = heroNode.name;
        } else {
            System.out.println("未找到该节点" + heroNode.no + "，无法修改！");
        }
    }

    /**
     * 显示链表
     */
    public void list() {
        HeroNode tempNode = headNode.next;
        if (tempNode == null) {
            System.out.println("链表为空，无法显示！");
            return;
        }
        while (true) {
            if (tempNode == null) {
                break;
            }
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public HeroNode next;
    public HeroNode pre;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
