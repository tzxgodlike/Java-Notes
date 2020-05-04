package ChainOfResponsibility;

public class Client {

    public static void main(String[] args) {
        //创建请求
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 31000, 1);

        //创建审批人
        DepartmentApprover departmentApprover = new DepartmentApprover("张主任");
        CollegeApprover collegeApprover = new CollegeApprover("李院长");
        ViceSchoolMasterApprover viceSchoolMasterApprover = new ViceSchoolMasterApprover("王副校长");
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("唐校长");

        //创建责任链 [要写成一个环  不然直接1000给校长处理会报错]
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolMasterApprover);
        viceSchoolMasterApprover.setApprover(schoolMasterApprover);
        schoolMasterApprover.setApprover(departmentApprover);

        //
        departmentApprover.processRequest(purchaseRequest);
    }
}
