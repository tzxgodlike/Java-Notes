-- lc.595
-- 如果一个国家的面积超过300万平方公里，或者人口超过2500万，那么这个国家就是大国家。
-- 编写一个SQL查询，输出表中所有大国家的名称、人口和面积。

select name,population,area from world where area > 3000000 or population > 25000000

-- 使用union
-- union使用之后会重复的值会只出现一行  要允许重复应该使用union all
select name,population,area from world where area > 3000000
UNION select name,population,area from world whenever population > 25000000
-- 使用union比or快  or会使索引失效

-- lc.182
-- 编写一个SQL查询 查找person表中所重复的电子邮箱
--此题的关键就是找重复 即count(email)>1
-- 使用having 和 conut
select email from person group by email having count(email) > 1
-- 使用group by 和临时表  先查出一张表 列为email和count(email) 再查count(email)大于1的
-- 临时表需要别名 所以加as person1
select email from select
{
    select email,count(email) as num from person group by email
}as person1
where num >1

-- lc.627 交换工资
--给定一个 salary 表，如下所示，有 m = 男性 和 f = 女性 的值。交换所有的 f 和 m 值
-- （例如，将所有 f 值更改为 m，反之亦然）。要求只使用一个更新（Update）语句，并且没有中间的临时表。

--1. if二值判断  类似于三元表达式
update salary set sex = if(sex='m','f','m')

-- 2. case
update salary set sex = (
    case
    when sex = 'm' then 'f'
    else 'm'
    end
)
-- lc.620 有趣的电影
--某城市开了一家新的电影院，吸引了很多人过来看电影。该电影院特别注意用户体验，专门有个 LED显示板做电影推荐，
-- 上面公布着影评和相关电影描述。作为该电影院的信息部主管，您需要编写一个 SQL查询，
-- 找出所有影片描述为非 boring (不无聊) 的并且 id 为奇数 的影片，结果请按等级 rating 排列。
select * from cinema where description <> 'boring' and id%2=1 order by rating DESC

-- mysql中<>即！=   %2可以用mod(id,2)代替 表示id/2的余数

-- lc.175 组合两个表
--编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息

-- 链接就是两个表做笛卡尔积
-- inner join 就是只保留满足条件的行 可简写为join
-- left out join 就是返回inner join的行 加上左表有 右表没有匹配(则置为null)的行  简写为left join
-- join ..on 后面接的是链接的筛选条件 后面可再加where

-- 论 person 是否有地址信息， 即使用左连接
select Person.FirstName, Person.LastName, Address.City, Address.State
from Person left join address
on  Person.PersonId = Address.PersonId

-- lc.181 超过经理收入的员工
-- Employee 表包含所有员工，他们的经理也属于员工。每个员工都有一个 Id，此外还有一列对应员工的经理的 Id。
-- 给定 Employee 表，编写一个 SQL 查询，该查询可以获取收入超过他们经理的员工的姓名。在上面的表格中，
-- Joe 是唯一一个收入超过他的经理的员工。

-- 要查询 xx和它自己比 一般都是自连接

-- 隐式内连接 [自连接]  select两张表 会生成笛卡尔积 再用where筛选
select t1.name as Employee
from Employee as t1,Employee as t2
where t1.ManagerId = t2.id and t1.salary > t2.salary

-- 显式左连接
select t1.name as Employee
from Employee as t1 join Employee as t2
on t1.ManagerId = t2.id and t1.salary > t2.salary

-- lc.183 从不订购的客户
-- 某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。

-- 1.not in ()  耗时长
select Customers.name as Customers
from Customers
where Customers.id not in (
    select CustomerId from Orders
)
-- 2. join 耗时短 join联合表之后列名用原来的表名.列名表示
select Customers.name as Customers
from Customers left join Orders
on Customers.id = Orders.CustomerId
where  Orders.CustomerId is null

-- lc.196 删除重复的电子邮箱  又是和自己比
-- 编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个

-- 先select显示 再把select改成delect即可
-- 所以，官方sql中，DELETE p1就表示从p1表中删除满足WHERE条件的记录
delete p1 from person p1,person p2
where p1.Email = p2.Email and p1.id > p2.id

-- lc.1179 重新格式化部门表    [行转列]
-- 编写一个 SQL 查询来重新格式化表，使得新的表中有一个部门 id 列和一些对应 每个月 的收入（revenue）列

--GROUP BY 语句用于结合聚合函数，根据一个或多个列对结果集进行分组。
-- 先groupby 分组 聚合函数对每个组操作
-- 今后但凡使用group by，前面一定要有聚合函数（MAX /MIN / SUM /AVG / COUNT）

--看表格可以知道，每个部门每个月只有一个数据，所以不存在用max()来选取最大值的想法，
-- 只是作为一个获取revenue的工具。和max()相比，sum()性能更高一点。 随便用一个聚合函数就行
SELECT
    id,
    MIN(IF(`month` = 'Jan', revenue, NULL)) AS Jan_Revenue,
    MIN(IF(`month` = 'Feb', revenue, NULL)) AS Feb_Revenue,
    MIN(IF(`month` = 'Mar', revenue, NULL)) AS Mar_Revenue,
    MIN(IF(`month` = 'Apr', revenue, NULL)) AS Apr_Revenue,
    MIN(IF(`month` = 'May', revenue, NULL)) AS May_Revenue,
    MIN(IF(`month` = 'Jun', revenue, NULL)) AS Jun_Revenue,
    MIN(IF(`month` = 'Jul', revenue, NULL)) AS Jul_Revenue,
    MIN(IF(`month` = 'Aug', revenue, NULL)) AS Aug_Revenue,
    MIN(IF(`month` = 'Sep', revenue, NULL)) AS Sep_Revenue,
    MIN(IF(`month` = 'Oct', revenue, NULL)) AS Oct_Revenue,
    MIN(IF(`month` = 'Nov', revenue, NULL)) AS Nov_Revenue,
    MIN(IF(`month` = 'Dec', revenue, NULL)) AS Dec_Revenue
FROM
    Department
GROUP BY id;


-- lc.197 上升的温度  又是与自己比 直接where自连
-- 给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。
select w1.id
from weather w1, weather w2
where datediff(w1.RecordDate,w2.RecordDate) = 1 and w1.Temperature>w2.Temperature

-- lc.596
--有一个courses 表 ，有: student (学生) 和 class (课程)。
-- 请列出所有超过或等于5名学生的课。  重复的学生只算一次 即要去重
select courses.class
from  courses
group by class
having count(distinct(student))>=5

--或者使用子查询
SELECT
    class
FROM
    (SELECT
        class, COUNT(DISTINCT student) AS num
    FROM
        courses
    GROUP BY class) AS temp_table
WHERE
    num >= 5

-- lc.176 第二高的薪水
-- 编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。不存在则返回null
-- 可以查出 limit来限制范围  但不能排除空值

select distinct salary from empolyee order by  salary DESC Limit 1 offset 1
-- 用ifnull函数排除空值  ifnull(..,null)
select ifnull(select distinct salary from empolyee order by  salary DESC Limit 1 offset 1,null) as SecondHighestSalary

-- lc.626 换座位
--小美是一所中学的信息科技老师，她有一张 seat 座位表，平时用来储存学生名字和与他们相对应的座位 id。
-- 其中纵列的 id 是连续递增的-- 小美想改变相邻俩学生的座位。你能不能帮她写一个 SQL query 来输出小美想要的结果呢？

SELECT
    (CASE
        WHEN MOD(id, 2) != 0 AND counts != id THEN id + 1
        WHEN MOD(id, 2) != 0 AND counts = id THEN id
        ELSE id - 1
    END) AS id,
    student
FROM
    seat,
    (SELECT
        COUNT(*) AS counts
    FROM
        seat) AS seat_counts
ORDER BY id ASC;

-- lc.178 分数排名
--编写一个 SQL 查询来实现分数排名。
-- 如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。
-- 换句话说，名次之间不应该有“间隔”。

--排名 使用窗口函数   rank是mysql的关键字 所以用''转义
select * ,rank() over (order by 成绩 desc) as 'rank' from 班级

-- lc.184 部门工资最高的员工  IN的用法 ！！
select Department.name AS 'Department',
    Employee.name AS 'Employee',
    Salary
from Employee join Department on Employee.DepartmentId = Department.Id
where (Employee.DepartmentId,salary) IN
(   select DepartmentId,max(salary)
    from Employee GROUP BY DepartmentId
)



https://mybatis.org/mybatis-3/zh/sqlmap-xml.html  MYBATIS

## sql函数

1. 函数结果一般作为一个结果列 再select处使用

2. group by() 必须搭配聚合函数使用 见lc.1179  

    1.聚合函数可以再select处使用作为列

    2.聚合函数也可以搭配having使用

3. DATEDIFF() 返回两个日期的天数差

4. select 后面不仅可以接列名，还可以赋值

如 select 1 as num 这句话意思就是增加一个Num列 值为1

select 'tzx' as name 



5.排序时使用窗口函数

    1.rank()         若有两个第一名 序号为1 1 3

    2.dense_rank()   若有两个第一名 序号为1 1 2

    3.row_number()   若有两个第一名 序号为1 2 3

   

6.

## sql函数
1. 函数结果一般作为一个结果列 再select处使用
2. group by() 必须搭配聚合函数使用 见lc.1179 
    1.聚合函数可以再select处使用作为列
    2.聚合函数也可以搭配having使用
3. DATEDIFF() 返回两个日期的天数差
4. select 后面不仅可以接列名，还可以赋值
如 select 1 as num 这句话意思就是增加一个Num列 值为1
select 'tzx' as name

5.排序时使用窗口函数
    1.rank()         若有两个第一名 序号为1 1 3
    2.dense_rank()   若有两个第一名 序号为1 1 2
    3.row_number()   若有两个第一名 序号为1 2 3



















