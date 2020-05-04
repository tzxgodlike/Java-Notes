package ChainOfResponsibility;

public abstract class Approver {

    Approver approver;  //下一个处理者

    String name;    //名字

    public Approver(String name) {
        //this.approver = approver;
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    //处理审批请求的方法 由子类完成  做成抽象

    public abstract void processRequest(PurchaseRequest purchaseRequest);
}
